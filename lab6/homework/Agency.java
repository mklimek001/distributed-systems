package homework;

import com.rabbitmq.client.*;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;


public class Agency {

    public static void main(String[] argv) throws Exception {
        // info
        System.out.println("AGENCY");
        int counter = 0;
        String key = "";

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        System.out.println("Enter ID of Agency: ");
        int id = Integer.parseInt(br.readLine());

        // connection
        ConnectionFactory factory = new ConnectionFactory();
        factory.setHost("localhost");
        Connection connection = factory.newConnection();
        Channel channel = connection.createChannel();

        // exchange
        String EXCHANGE_NAME = KeysGenerator.getExchange_key();
        channel.exchangeDeclare(EXCHANGE_NAME, BuiltinExchangeType.TOPIC);

        String admin_queue = channel.queueDeclare().getQueue();
        channel.queueBind(admin_queue, EXCHANGE_NAME, KeysGenerator.getNotification_key("agency"));

        Consumer admin_consumer = new DefaultConsumer(channel) {
            @Override
            public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body) throws IOException {
                String message = new String(body, StandardCharsets.UTF_8);
                System.out.println("******************************* \n"
                        + "** ADMIN " + message
                        + "\n******************************* ");
            }
        };

        channel.basicConsume(admin_queue, true, admin_consumer);

        requests:
        while (true) {
            // read msg
            System.out.println("Enter requested service [person, load, satellite]: ");
            String message = br.readLine();
            counter++;

            // message sending
            switch (message) {
                case "person":
                    key = KeysGenerator.getPerson_key(id, counter);
                    break;
                case "load":
                    key = KeysGenerator.getLoad_key(id, counter);
                    break;
                case "satellite":
                    key = KeysGenerator.getSatellite_key(id, counter);
                    break;
                case "exit":
                    channel.close();
                    connection.close();
                    break requests;
                default:
                    key = null;
            }

            // publish
            if(key != null) {
                channel.basicPublish(EXCHANGE_NAME, key, null, key.getBytes(StandardCharsets.UTF_8));
                channel.basicPublish(EXCHANGE_NAME, "copy." + key, null, key.getBytes(StandardCharsets.UTF_8));
                System.out.println("Requested: " + key);
            }
        }
    }
}
