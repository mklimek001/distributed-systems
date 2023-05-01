package sr.ice.server;

import Devices.TemperatureDevice;
import Devices.TemperatureError;
import com.zeroc.Ice.Current;

public class Heater implements TemperatureDevice {
    int currentTemperature = 20;
    @Override
    public int getCurrentTemperature(Current current) {
        return currentTemperature;
    }

    @Override
    public int setTemperature(int temperature, Current current) {
        try{
            if(temperature < 15 || temperature > 35){
                throw new TemperatureError("Heater temperature must be in range [15 - 35 degrees]");
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
            if(currentTemperature >= 35){
                throw new TemperatureError("Heater temperature must be in range [15 - 35 degrees]");
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
            if(currentTemperature <= 15){
                throw new TemperatureError("Heater temperature must be in range [15 - 35 degrees]");
            }
            currentTemperature -= 1;
        }catch (TemperatureError e){
            System.out.println(e.message);
        }
        return currentTemperature;
    }
}
