
#ifndef HOME_ICE
#define HOME_ICE

module Devices
{

  struct Time
  {
    int hour;
    int minute;
  }

  exception TimeError
  {
    string message = "Wrong time value!";
  }

  struct Program
  {
    int number;
    string name;
    int time;
    int temperature;
  }

  exception ProgramError
  {
    string message = "Specified program does not exist!";
  }

  struct PlannedProgram
  {
    Program program;
    Time time;
  }

  interface Washer
  {
    int runWashing(int programId);
    int planWashing(int programId, Time time);
    int cancelPlanedWashing();
    idempotent PlannedProgram getPlannedProgram();
    idempotent Program programDetails(int programId);
  };


  interface TemperatureDevice{
    idempotent int getCurrentTemperature();
    idempotent int setTemperature(int temperature);
    int increaseTemperature();
    int decreaseTemperature();
  }

  exception TemperatureError
  {
    string message = "Temperature out of range!";
  }


  struct Color
  {
    int R;
    int G;
    int B;
  }

  exception ColorError
  {
    string message = "Wrong color value!";
  }

  struct LampSetting
  {
    float power;
    Color color;
  }

  exception LampError
  {
    string message = "Wrong power value!";
  }

  interface Lamp
  {
    idempotent LampSetting getCurrentSetting();
    idempotent void setLamp(LampSetting setting);
    Time setDisconnection(int minutes);
    int powerOn();
    int powerOff();
  }

};

#endif