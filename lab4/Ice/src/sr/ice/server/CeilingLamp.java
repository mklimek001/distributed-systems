package sr.ice.server;

import Devices.*;
import com.zeroc.Ice.Current;

import java.time.LocalDateTime;

public class CeilingLamp implements Lamp {
    private LampSetting lampSetting;
    private Time disconnectedTime;
    private Time currentTime;

    public CeilingLamp(){
        lampSetting = new LampSetting(0F, new Color(255, 255, 255));
        disconnectedTime = new Time(24,0);
        currentTime = new Time(LocalDateTime.now().getHour(), LocalDateTime.now().getMinute());
    }

    @Override
    public LampSetting getCurrentSetting(Current current) {
        checkTime();
        return this.lampSetting;
    }

    @Override
    public void setLamp(LampSetting setting, Current current) {
        checkTime();
        try{
            if(setting.color.R < 0 || setting.color.R > 255
            || setting.color.G < 0 || setting.color.G > 255
            || setting.color.B < 0 || setting.color.B > 255){
                throw new ColorError();
            }
            this.lampSetting = setting;
        }catch (ColorError e){
            System.out.println(e.message);
        }
    }

    @Override
    public Time setDisconnection(int minutes, Current current) {
        checkTime();
        disconnectedTime.minute += minutes;
        disconnectedTime.hour += disconnectedTime.minute/60;
        disconnectedTime.hour %= 24;
        disconnectedTime.minute = disconnectedTime.minute%60;
        return disconnectedTime;
    }

    @Override
    public int powerOn(Current current) {
        this.lampSetting.power = 1F;
        return 1;
    }

    @Override
    public int powerOff(Current current) {
        this.lampSetting.power = 0F;
        return 0;
    }

    private void checkTime(){
        currentTime = new Time(LocalDateTime.now().getHour(), LocalDateTime.now().getMinute());
        if(currentTime.hour >= disconnectedTime.hour && currentTime.minute >= disconnectedTime.minute){
            this.lampSetting.power = 0F;
        }
    }
}
