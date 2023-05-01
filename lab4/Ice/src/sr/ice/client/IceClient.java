package sr.ice.client;

import Devices.*;
import com.zeroc.Ice.*;

import java.io.BufferedReader;
import java.io.IOException;
import java.lang.Exception;
import java.util.Objects;

public class IceClient {
	public static void main(String[] args) {
		int status = 0;
		Communicator communicator = null;

		try {
			communicator = Util.initialize(args);

			ObjectPrx base_dishwasher = communicator.stringToProxy("washer/dishwasher1:tcp -h 127.0.0.2 -p 10000 -z : udp -h 127.0.0.2 -p 10000 -z");
			ObjectPrx base_clotheswasher = communicator.stringToProxy("washer/clotheswasher1:tcp -h 127.0.0.2 -p 10000 -z : udp -h 127.0.0.2 -p 10000 -z");

			ObjectPrx base_kitchenlamp = communicator.stringToProxy("lamp/kitchenlamp1:tcp -h 127.0.0.2 -p 10000 -z : udp -h 127.0.0.2 -p 10000 -z");
			ObjectPrx base_roomlamp = communicator.stringToProxy("lamp/roomlamp1:tcp -h 127.0.0.2 -p 10000 -z : udp -h 127.0.0.2 -p 10000 -z");
			ObjectPrx base_bathroomlamp = communicator.stringToProxy("lamp/bathroomlamp1:tcp -h 127.0.0.2 -p 10000 -z : udp -h 127.0.0.2 -p 10000 -z");

			ObjectPrx base_roomheater = communicator.stringToProxy("temperature/roomheater1:tcp -h 127.0.0.2 -p 10000 -z : udp -h 127.0.0.2 -p 10000 -z");
			ObjectPrx base_bathroomheater = communicator.stringToProxy("temperature/bathroomheater1:tcp -h 127.0.0.2 -p 10000 -z : udp -h 127.0.0.2 -p 10000 -z");
			ObjectPrx base_ac = communicator.stringToProxy("temperature/airconditioner1:tcp -h 127.0.0.2 -p 10000 -z : udp -h 127.0.0.2 -p 10000 -z");


			WasherPrx dishwasher = WasherPrx.checkedCast(base_dishwasher);
			WasherPrx clotheswasher = WasherPrx.checkedCast(base_clotheswasher);

			LampPrx kitchenlamp = LampPrx.checkedCast(base_kitchenlamp);
			LampPrx roomlamp = LampPrx.checkedCast(base_roomlamp);
			LampPrx bathroomlamp = LampPrx.checkedCast(base_bathroomlamp);

			TemperatureDevicePrx roomheater = TemperatureDevicePrx.checkedCast(base_roomheater);
			TemperatureDevicePrx bathroomheater = TemperatureDevicePrx.checkedCast(base_bathroomheater);
			TemperatureDevicePrx ac = TemperatureDevicePrx.checkedCast(base_ac);

			if (dishwasher == null || clotheswasher == null
			|| kitchenlamp == null || roomlamp == null || bathroomlamp == null
			|| roomheater == null || bathroomheater == null || ac == null)
				throw new Error("Invalid proxy");

			String device = null;
			java.io.BufferedReader in = new java.io.BufferedReader(new java.io.InputStreamReader(System.in));

			do {
				try {
					System.out.println("""
							--------------\s
							 Available devices:\s
							 dishwasher\s
							 clothes washer\s
							 kitchen lamp\s
							 room lamp\s
							 bathroom lamp\s
							 room heater\s
							 bathroom heater\s
							 ac\s
							--------------""");

					System.out.print("DEVICE: ");

					device = in.readLine();
					switch (device) {
						case "dishwasher" -> washerDevice(dishwasher, in);
						case "clothes washer" -> washerDevice(clotheswasher, in);
						case "kitchen lamp" -> lampDevice(kitchenlamp, in);
						case "room lamp" -> lampDevice(roomlamp, in);
						case "bathroom lamp" -> lampDevice(bathroomlamp, in);
						case "room heater" -> temperatureDevice(roomheater, in);
						case "bathroom heater" -> temperatureDevice(bathroomheater, in);
						case "ac" -> temperatureDevice(ac, in);
					}
				} catch (IOException | TwowayOnlyException ex) {
					ex.printStackTrace(System.err);
				}
			}
			while (!Objects.equals(device, "x"));


		} catch (LocalException e) {
			e.printStackTrace();
			status = 1;
		} catch (Exception e) {
			System.err.println(e.getMessage());
			status = 1;
		}
		if (communicator != null) { //clean
			try {
				communicator.destroy();
			} catch (Exception e) {
				System.err.println(e.getMessage());
				status = 1;
			}
		}
		System.exit(status);
	}

	private static void washerDevice(WasherPrx washer, BufferedReader in) throws IOException {
		String command;
		String temp;
		int program, start_h, start_m;
		PlannedProgram plannedProgram;
		Program programDetails;

		System.out.println("""
							--------------\s
							    Available commands:\s
							    run\s
							    plan washing\s
							    cancel planned\s
							    get planned\s
							    program details\s
							--------------""");
		System.out.println("  COMMAND: ");
		command = in.readLine();

		switch (command) {
			case "run":
				System.out.println("    PROGRAM NUMBER: ");
				temp = in.readLine();

				program = Integer.parseInt(temp);
				program = washer.runWashing(program);
				if(program == 0){
					System.out.println("Error occurred while running program.");
				}else {
					System.out.println("Program " + program + " run successfully");
				}
				break;

			case "plan washing":
				System.out.println("    PROGRAM NUMBER: ");
				temp = in.readLine();
				program = Integer.parseInt(temp);
				System.out.println("    START TIME: ");
				temp = in.readLine();
				start_h = Integer.parseInt(temp.split(":")[0]);
				start_m = Integer.parseInt(temp.split(":")[1]);
				program = washer.planWashing(program, new Time(start_h, start_m));
				if(program == 0){
					System.out.println("Error occurred while running program.");
				}else {
					System.out.println("Program " + program + " planned successfully");
				}
				break;

			case "cancel planned":
				program = washer.cancelPlanedWashing();
				if(program == 0){
					System.out.println("Nothing to cancel");
				}else {
					System.out.println("Program " + program + " cancelled successfully");
				}
				break;

			case "get planned":
				plannedProgram = washer.getPlannedProgram();
				if(plannedProgram.program.number == 0){
					System.out.println("Nothing is planned");
				}else {
					System.out.println("Program " + plannedProgram.program.name + " planned for " + plannedProgram.time.hour + ":" + plannedProgram.time.minute);
				}
				break;

			case "program details":
				System.out.println("    PROGRAM NUMBER: ");
				temp = in.readLine();
				program = Integer.parseInt(temp);
				programDetails = washer.programDetails(program);
				if(programDetails.number == 0){
					System.out.println("No such program");
				}else {
					System.out.println("Program: " + programDetails.name + " duration: " + programDetails.time + " temperature : " + programDetails.temperature);
				}
				break;
		}
	}

	private static void lampDevice(LampPrx lamp, BufferedReader in) throws IOException {
		String command;
		String temp;
		int state, minutes;
		Time time;
		LampSetting lampSetting = new LampSetting(0F, new Color(255, 255, 255));

		System.out.println("""
							--------------\s
							    Available commands:\s
							    get settings\s
							    set lamp\s
							    set disconnection\s
							    power on\s
							    power off\s
							--------------""");
		System.out.println("  COMMAND: ");
		command = in.readLine();

		switch (command) {
			case "get settings" :
				lampSetting = lamp.getCurrentSetting();
				System.out.println("Power: " + lampSetting.power + " Color (R: " + lampSetting.color.R
						+ ", G: " + lampSetting.color.G + ", B: " + lampSetting.color.B + ")");
				break;

			case "set lamp" :
				System.out.println("    POWER: ");
				temp = in.readLine();
				lampSetting.power = Float.parseFloat(temp);
				System.out.println("    COLOR RED: ");
				temp = in.readLine();
				lampSetting.color.R = Integer.parseInt(temp);
				System.out.println("    COLOR GREEN: ");
				temp = in.readLine();
				lampSetting.color.G = Integer.parseInt(temp);
				System.out.println("    COLOR BLUE: ");
				temp = in.readLine();
				lampSetting.color.B = Integer.parseInt(temp);
				lamp.setLamp(lampSetting);
				System.out.println("Power: " + lampSetting.power + " Color (R: " + lampSetting.color.R
						+ ", G: " + lampSetting.color.G + ", B: " + lampSetting.color.B + ")");
				break;

			case "set disconnection":
				System.out.println("    COLOR RED: ");
				temp = in.readLine();
				minutes = Integer.parseInt(temp);
				time = lamp.setDisconnection(minutes);
				System.out.println("Disconnection set at " + time.hour + ":" + time.minute);
				break;

			case "power on":
				state = lamp.powerOn();
				if (state == 1) {
					System.out.println("Lamp is on");
				}
				break;

			case "power off":
				state = lamp.powerOff();
				if (state == 0) {
					System.out.println("Lamp is off");
				}
				break;
		}

	}


	private static void temperatureDevice(TemperatureDevicePrx device, BufferedReader in) throws IOException {
		String command;
		String temp;
		int temperature;

		System.out.println("""
							--------------\s
							    Available commands:\s
							    get temperature\s
							    set temperature\s
							    hotter\s
							    colder\s
							--------------""");

		System.out.println("  COMMAND: ");
		command = in.readLine();

		switch (command) {
			case "get temperature" :
				temperature = device.getCurrentTemperature();
				System.out.println("Temperature: " + temperature);
				break;

			case "set temperature" :
				System.out.println("    TEMPERATURE: ");
				temp = in.readLine();
				temperature = Integer.parseInt(temp);
				temperature = device.setTemperature(temperature);
				System.out.println("Temperature: " + temperature);
				break;

			case "hotter":
				temperature = device.increaseTemperature();
				System.out.println("Temperature: " + temperature);
				break;

			case "colder":
				temperature = device.decreaseTemperature();
				System.out.println("Temperature: " + temperature);
				break;

		}

	}

}


