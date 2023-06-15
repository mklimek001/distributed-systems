package homework;

import com.rabbitmq.client.AMQP;
import com.rabbitmq.client.BuiltinExchangeType;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import com.rabbitmq.client.Consumer;
import com.rabbitmq.client.DefaultConsumer;
import com.rabbitmq.client.Envelope;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;


public class Carrier {

    static String service_key_1 = null;
    static String queue_name_1 = null;
    static String service_key_2 = null;
    static String queue_name_2 = null;

    public static void setServiceKeys() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        System.out.println("Enter first service type [person, load, satellite]: ");
        String service_type_1 = br.readLine();
        System.out.println("Enter second service type [person, load, satellite]: ");
        String service_type_2 = br.readLine();


        System.out.println("Chosen service types: ");
        if(service_type_1.equals("person") || service_type_2.equals("person")){
            System.out.println("  -> person transport");
            service_key_1 = KeysGenerator.getPerson_key();
            queue_name_1 = "person";
        }

        if(service_type_1.equals("load") || service_type_2.equals("load")){
            System.out.println("  -> load transport");
            if(service_key_1 == null){
                service_key_1 = KeysGenerator.getLoad_key();
                queue_name_1 = "load";
            }else {
                service_key_2 = KeysGenerator.getLoad_key();
                queue_name_2 = "load";
            }
        }

        if(service_type_1.equals("satellite") || service_type_2.equals("satellite")){
            System.out.println("  -> satellite transport");

            if(service_key_1 == null){
                service_key_1 = KeysGenerator.getSatellite_key();
                queue_name_1 = "satellite";
            }else {
                service_key_2 = KeysGenerator.getSatellite_key();
                queue_name_2 = "satellite";
            }
        }

    }

    public static void main(String[] argv) throws Exception {

        // info
        System.out.println("CARRIER");
        setServiceKeys();

        // connection
        ConnectionFactory factory = new ConnectionFactory();
        factory.setHost("localhost");
        Connection connection = factory.newConnection();
        Channel channel = connection.createChannel();

        // exchange
        String EXCHANGE_NAME = KeysGenerator.getExchange_key();
        channel.exchangeDeclare(EXCHANGE_NAME, BuiltinExchangeType.TOPIC);
        channel.basicQos(1);

        // queue & bind
        String queue_service_1 = channel.queueDeclare(queue_name_1, false, false, false, null).getQueue();
        channel.queueBind(queue_service_1, EXCHANGE_NAME, service_key_1);
        System.out.println("created queue: " + queue_service_1);

        String queue_service_2 = channel.queueDeclare(queue_name_2, false, false, false, null).getQueue();
        channel.queueBind(queue_service_2, EXCHANGE_NAME, service_key_2);
        System.out.println("created queue: " + queue_service_2);

        String admin_queue = channel.queueDeclare().getQueue();
        channel.queueBind(admin_queue, EXCHANGE_NAME, KeysGenerator.getNotification_key("carrier"));

        // requests handling
        Consumer consumer = new DefaultConsumer(channel) {
            @Override
            public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body) throws IOException {
                String message = new String(body, StandardCharsets.UTF_8);
                System.out.println("------------------------------- \n"
                        + "-- START " + message);
                try {
                    Thread.sleep(5000L);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
                System.out.println("-- STOP " + message +
                        "\n------------------------------- ");
                channel.basicAck(envelope.getDeliveryTag(), false);
            }
        };

        Consumer admin_consumer = new DefaultConsumer(channel) {
            @Override
            public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body) throws IOException {
                String message = new String(body, StandardCharsets.UTF_8);
                System.out.println("******************************* \n"
                                    + "** ADMIN " + message
                                    + "\n******************************* ");
            }
        };

        // start listening
        System.out.println("Waiting for tasks...");
        channel.basicConsume(queue_service_1, false, consumer);
        channel.basicConsume(queue_service_2, false, consumer);
        channel.basicConsume(admin_queue, true, admin_consumer);
    }
}
