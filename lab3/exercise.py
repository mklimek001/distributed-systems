import pandas as pd
import logging
import ray
import os
import math
from sklearn.model_selection import train_test_split

# diamonds dataset downloaded from https://www.kaggle.com/datasets/shivam2503/diamonds

if ray.is_initialized:
    print("Ray shutting down...")
    ray.shutdown()
print("Ray initializing...")
ray.init(logging_level=logging.ERROR)


diamonds = pd.read_csv("./diamonds.csv")
print(diamonds.head(10))
print(diamonds.info())


# string values from worst to best
cut_vals = ["Fair", "Good", "Very Good", "Premium", "Ideal"]
color_vals = ["J", "I", "H", "G", "F", "E", "D"]
clarity_vals = ["I1", "SI2", "SI1", "VS2", "VS1", "VVS2", "VVS1", "IF"]


@ray.remote
def categorical_to_num(column: pd.Series, values: list) -> pd.Series:
    new_column = column.copy()
    for indx, elem in column.items():
        new_column[indx] = values.index(elem)
    return new_column.astype("int64")


cols = ["cut", "color", "clarity"]
col_vals = [cut_vals, color_vals, clarity_vals]
new_cols = pd.DataFrame()

for i in range(len(cols)):
    diamonds[cols[i]] = ray.get(categorical_to_num.remote(diamonds[cols[i]], col_vals[i]))

print(diamonds.head(10))
print(diamonds.info())

diamonds.pop("Unnamed: 0")
X = diamonds.copy()
y = X.pop('price')

print(X.head())
print(y[:10])

X_train, X_test, y_train, y_test = train_test_split(X, y, test_size=0.2)

#scaling with ray dedicated scaler
from ray.data.preprocessors.scaler import StandardScaler

X_train_ray = ray.data.from_pandas(X_train)
X_test_ray = ray.data.from_pandas(X_test)

scaler_ray = StandardScaler(list(X_train.columns))

X_train_scaled = scaler_ray.fit_transform(X_train_ray)
X_test_scaled = scaler_ray.transform(X_test_ray)

print(len(X_train_scaled.take_all()))
print(pd.DataFrame(X_train_scaled.take(10)))


#grid search cross validation with ray tune
from ray.tune.sklearn import TuneGridSearchCV
from sklearn.tree import DecisionTreeRegressor

param_grid = {'criterion': ['squared_error', 'absolute_error'],
              'max_depth' : [2, 4, 7, 10, 15, 25],
              'min_samples_leaf' : [2, 5, 15, 50, 150]
              }

ray_search = TuneGridSearchCV(estimator=DecisionTreeRegressor(),
                            param_grid=param_grid,
                            cv=10,
                            verbose=2)

#ray_search.fit(pd.DataFrame(X_train_scaled.take_all()), y_train)
#smaller part for testing purposes
TRAIN_LIMIT = 6000
ray_search.fit(pd.DataFrame(X_train_scaled.take_all()).head(TRAIN_LIMIT), y_train[:TRAIN_LIMIT])

print(ray_search.best_params)

TEST_LIMIT = 3000
y_predicted = ray_search.predict(pd.DataFrame(X_test_scaled.take_all()).head(TEST_LIMIT))

result = pd.DataFrame({
    'y_true': y_test[:TEST_LIMIT],
    'y_pred': y_predicted
})

print('\n', result)


@ray.remote
def mean_absolute_error(to_compare: pd.DataFrame) -> float:
    errors = abs(to_compare['y_true'] - to_compare['y_pred'])
    result = sum(errors)
    return result


processes_num = os.cpu_count()
positions_num = len(result.index)
part_size = positions_num//processes_num
total_error = 0.0


for i in range(processes_num):
    to_calculate = result.iloc[(i*part_size):((i+1)*part_size), :]
    total_error += ray.get(mean_absolute_error.remote(to_calculate))

print("MAE: ", total_error/(part_size*processes_num))


from sklearn.metrics import mean_absolute_error
print("\nsklearn MAE: ", mean_absolute_error(result['y_pred'], result['y_true']))

