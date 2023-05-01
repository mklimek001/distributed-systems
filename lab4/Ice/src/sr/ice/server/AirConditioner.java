package sr.ice.server;

import Devices.TemperatureDevice;
import Devices.TemperatureError;
import com.zeroc.Ice.Current;

public class AirConditioner implements TemperatureDevice {
    int currentTemperature = 20;
    @Override
    public int getCurrentTemperature(Current current) {
        return currentTemperature;
    }

    @Override
    public int setTemperature(int temperature, Current current) {
        try{
            if(temperature < 10 || temperature > 25){
                throw new TemperatureError("Heater temperature must be in range [10 - 25 degrees]");
            }
            currentTemperature = temperature;
        }catch (TemperatureError e){
            System.out.println(e.message);
        }
        return currentTemperature;
    }

    @Override
    public int increaseTemperature(Current current) {
        try{
            if(currentTemperature >= 25){
                throw new TemperatureError("Heater temperature must be in range [10 - 25 degrees]");
            }
            currentTemperature += 1;
        }catch (TemperatureError e){
            System.out.println(e.message);
        }
        return currentTemperature;
    }

    @Override
    public int decreaseTemperature(Current current) {
        try{
            if(currentTemperature <= 10){
                throw new TemperatureError("Heater temperature must be in range [10 - 25 degrees]");
            }
            currentTemperature -= 1;
        }catch (TemperatureError e){
            System.out.println(e.message);
        }
        return currentTemperature;
    }
}
