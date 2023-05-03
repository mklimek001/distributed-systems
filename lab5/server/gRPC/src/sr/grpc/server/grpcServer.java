package sr.grpc.server;

import io.grpc.Server;
import io.grpc.ServerBuilder;

import java.io.IOException;
//import io.grpc.netty.shaded.io.grpc.netty.NettyServerBuilder;


public class grpcServer {

	public static void main(String[] args) throws IOException, InterruptedException {

		System.out.println("Building serer...");
		Server server = ServerBuilder.forPort(6565)
				.addService(new EratosthenesSieveImpl())
				.build();

		System.out.println("Server is running");

		server.start();

		Runtime.getRuntime().addShutdownHook(new Thread(() -> {
			System.out.println("Server is shutting down");
			server.shutdown();
		}));

		server.awaitTermination();

	}

}
