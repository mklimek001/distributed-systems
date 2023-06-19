package org.example;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;


public class Main {

    private static void printInfo(){
        System.out.println("> choose option: ");
        System.out.println("    > create");
        System.out.println("    > delete");
        System.out.println("    > show");
        System.out.println("    > close");
    }


    public static void main(String[] args) throws Exception {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        System.out.println("Apache zookeeper");

        System.out.println("> command to run if z created: ");
        String command = br.readLine();
        ZKManager manager = new ZKManager(command);

        String input = "";
        String path;


        while(!input.equals("close")){
            printInfo();
            input = br.readLine();
            switch (input) {
                case "create":
                    System.out.println("> znode path :");
                    path = br.readLine();
                    manager.create(path, "Hello world".getBytes(StandardCharsets.UTF_8));
                    break;
                case "delete":
                    System.out.println("> znode path :");
                    path = br.readLine();
                    manager.delete(path);
                    break;
                case "show":
                    System.out.println("> znode path :");
                    path = br.readLine();
                    manager.printAllNodes(path);
                    break;
                case "close":
                    break;
                default:
                    System.out.println("> wrong command!");
            }

        }

        /*
        manager.create("/z", "Hello world".getBytes(StandardCharsets.UTF_8));
        Thread.sleep(5000);
        manager.create("/z/zz", "Hello world".getBytes(StandardCharsets.UTF_8));
        Thread.sleep(5000);
        manager.printAllNodes("/z");
        manager.delete("/z/zz");
        Thread.sleep(5000);

         */


        manager.delete("/z");
        manager.close();

    }
}