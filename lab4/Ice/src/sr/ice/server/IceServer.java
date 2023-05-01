package sr.ice.server;

import com.zeroc.Ice.Communicator;
import com.zeroc.Ice.Identity;
import com.zeroc.Ice.ObjectAdapter;
import com.zeroc.Ice.Util;

public class IceServer {
	public void t1(String[] args) {
		int status = 0;
		Communicator communicator = null;

		try {

			communicator = Util.initialize(args);

			//ObjectAdapter adapter = communicator.createObjectAdapter("Adapter1");
			ObjectAdapter adapter = communicator.createObjectAdapterWithEndpoints("Adapter1", "tcp -h 127.0.0.2 -p 10000 -z : udp -h 127.0.0.2 -p 10000 -z");

			Dishwasher dishwasher = new Dishwasher();
			ClothesWasher clotheswasher = new ClothesWasher();

			CeilingLamp kitchenlamp = new CeilingLamp();
			CeilingLamp roomlamp = new CeilingLamp();
			CeilingLamp bathroomlamp = new CeilingLamp();

			Heater roomheater = new Heater();
			Heater bathroomheater = new Heater();
			AirConditioner airconditioner = new AirConditioner();


			adapter.add(dishwasher, new Identity("dishwasher1", "washer"));
			adapter.add(clotheswasher, new Identity("clotheswasher1", "washer"));

			adapter.add(kitchenlamp, new Identity("kitchenlamp1", "lamp"));
			adapter.add(roomlamp, new Identity("roomlamp1", "lamp"));
			adapter.add(bathroomlamp, new Identity("bathroomlamp1", "lamp"));

			adapter.add(roomheater, new Identity("roomheater1", "temperature"));
			adapter.add(bathroomheater, new Identity("bathroomheater1", "temperature"));
			adapter.add(airconditioner, new Identity("airconditioner1", "temperature"));


			// 5. Aktywacja adaptera i wejście w pętlę przetwarzania żądań
			adapter.activate();

			System.out.println("Entering event processing loop...");

			communicator.waitForShutdown();

		} catch (Exception e) {
			e.printStackTrace(System.err);
			status = 1;
		}
		if (communicator != null) {
			try {
				communicator.destroy();
			} catch (Exception e) {
				e.printStackTrace(System.err);
				status = 1;
			}
		}
		System.exit(status);
	}


	public static void main(String[] args) {
		IceServer app = new IceServer();
		app.t1(args);
	}
}