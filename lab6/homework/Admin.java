package homework;

import com.rabbitmq.client.*;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;

public class Admin {

    public static void main(String[] argv) throws Exception {
        // info
        System.out.println("Admin");
        int counter = 0;
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));


        // connection
        ConnectionFactory factory = new ConnectionFactory();
        factory.setHost("localhost");
        Connection connection = factory.newConnection();
        Channel channel = connection.createChannel();

        // exchange
        String EXCHANGE_NAME = KeysGenerator.getExchange_key();
        channel.exchangeDeclare(EXCHANGE_NAME, BuiltinExchangeType.TOPIC);

        // all messages copies queue
        String notification_queue = channel.queueDeclare("copy", false, false, false, null).getQueue();
        channel.queueBind(notification_queue, EXCHANGE_NAME, "copy.transport.#");
        System.out.println("created queue: " + notification_queue);

        Consumer consumer = new DefaultConsumer(channel) {
            @Override
            public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body) throws IOException {
                String message = new String(body, StandardCharsets.UTF_8);
                System.out.println("Received: " + message);
                channel.basicAck(envelope.getDeliveryTag(), false);
            }
        };

        channel.basicConsume(notification_queue, false, consumer);


        sending:
        while (true) {
            System.out.println("Choose recipient [carrier, agency, both]: ");
            String recipient = br.readLine();

            System.out.println("Enter message: ");
            String message = br.readLine();
            counter++;

            System.out.println(message + " will be send to " + recipient);

            switch (recipient) {
                case "carrier":
                    channel.basicPublish(EXCHANGE_NAME, KeysGenerator.getNotification_key("carrier", counter), null, message.getBytes(StandardCharsets.UTF_8));
                    break;
                case "agency":
                    channel.basicPublish(EXCHANGE_NAME, KeysGenerator.getNotification_key("agency", counter), null, message.getBytes(StandardCharsets.UTF_8));
                    break;
                case "both":
                    channel.basicPublish(EXCHANGE_NAME, KeysGenerator.getNotification_key("carrier", counter), null, message.getBytes(StandardCharsets.UTF_8));
                    channel.basicPublish(EXCHANGE_NAME, KeysGenerator.getNotification_key("agency", counter), null, message.getBytes(StandardCharsets.UTF_8));
                    break;
                case "exit":
                    channel.close();
                    connection.close();
                    break sending;

            }


        }
    }
}
