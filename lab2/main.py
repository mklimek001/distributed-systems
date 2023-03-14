from fastapi import FastAPI, HTTPException
from fastapi.middleware.cors import CORSMiddleware
from pydantic import BaseModel
import httpx
import json


app = FastAPI()
client = httpx.AsyncClient()

origins = [
    "http://localhost",
    "http://localhost:8080",
    "http://localhost:5500"
]

app.add_middleware(
    CORSMiddleware,
    allow_origins=origins,
    allow_credentials=True,
    allow_methods=["*"],
    allow_headers=["*"],
)


class SimpleMenuPosition(BaseModel):
    name: str
    id: int
    kind: str
    img: str


def prepare_url_for_details(kind, id):
    if kind == "Drink":
        return f"https://www.thecocktaildb.com/api/json/v1/1/lookup.php?i={id}"
    elif kind == "Meal":
        return f"https://www.themealdb.com/api/json/v1/1/lookup.php?i={id}"


async def call_api(url: str):
    try:
        r = await client.get(url)
    except httpx.HTTPError:
        raise HTTPException(status_code=504, detail="Outer api connection error")

    r = r.text
    if r == "":
        return None
    return json.loads(r)


async def get_meals_from_category(category: str):
    drinks_url_cat = f"https://www.thecocktaildb.com/api/json/v1/1/filter.php?c={category}"
    meals_url_cat = f"https://www.themealdb.com/api/json/v1/1/filter.php?c={category}"

    drinks = await call_api(drinks_url_cat)
    meals = await call_api(meals_url_cat)

    menu = []

    if drinks is not None and drinks['drinks'] is not None:
        for drink in drinks['drinks']:
            new_drink = SimpleMenuPosition(
                name=drink["strDrink"],
                id=drink["idDrink"],
                kind="Drink",
                img=drink["strDrinkThumb"])

            menu.append(new_drink)

    if meals is not None and meals['meals'] is not None:
        for meal in meals['meals']:
            new_meal = SimpleMenuPosition(
                name=meal["strMeal"],
                id=meal["idMeal"],
                kind="Meal",
                img=meal["strMealThumb"])

            menu.append(new_meal)

    return menu


async def get_meals_without_ingredient(ingredient, chosen_dishes):
    drinks_url_ingredient = f"https://www.thecocktaildb.com/api/json/v1/1/filter.php?i={ingredient}"
    meals_url_ingredient = f"https://www.themealdb.com/api/json/v1/1/filter.php?i={ingredient}"

    drinks = await call_api(drinks_url_ingredient)
    meals = await call_api(meals_url_ingredient)

    forbidden_drinks_ids = []
    if drinks is not None and drinks['drinks'] is not None:
        for drink in drinks['drinks']:
            forbidden_drinks_ids.append(drink["idDrink"])

    forbidden_meals_ids = []
    if meals is not None and meals['meals'] is not None:
        for meal in meals['meals']:
            forbidden_meals_ids.append(meal["idMeal"])

    for dish in chosen_dishes:
        if dish.kind == "Drink" and str(dish.id) in forbidden_drinks_ids:
            chosen_dishes.remove(dish)

        elif dish.kind == "Meal" and str(dish.id) in forbidden_meals_ids:
            chosen_dishes.remove(dish)

    return


@app.get("/")
async def root():
    return {"message": "Hello Dishes"}


@app.get("/hello/{name}")
async def say_hello(name: str):
    return {"message": f"Hello {name}"}


@app.get("/categories")
async def list_all_categories():
    categories = []
    drinks_categories = await call_api(f"https://www.thecocktaildb.com/api/json/v1/1/list.php?c=list")
    meals_categories = await call_api(f"https://www.themealdb.com/api/json/v1/1/list.php?c=list")

    if drinks_categories is not None and drinks_categories['drinks'] is not None:
        for cat in drinks_categories['drinks']:
            categories.append(cat['strCategory'])

    if meals_categories is not None and meals_categories['meals'] is not None:
        for cat in meals_categories['meals']:
            categories.append(cat['strCategory'])

    return {"categories": categories}


@app.get("/menu")
async def get_menu(category: str = None, ingredient: str = None):
    if category is None:
        raise HTTPException(status_code=422, detail="Category is required")

    categories = await list_all_categories()

    if category not in categories['categories']:
        raise HTTPException(status_code=404, detail="Wrong category")

    menu = await get_meals_from_category(category)

    if ingredient is not None:
        await get_meals_without_ingredient(ingredient, menu)

    return {"category": category,
            "ingredient": ingredient,
            "dishes": menu,
            "count": len(menu)}


# http://127.0.0.1:8000/menu?category=Chicken&ingredient=chicken_breast
# http://127.0.0.1:8000/menu?category=Ordinary%20Drink&ingredient=vodka
