package org.example;

import org.apache.zookeeper.KeeperException;
import org.apache.zookeeper.WatchedEvent;
import org.apache.zookeeper.Watcher;
import org.apache.zookeeper.ZooKeeper;

import java.io.IOException;

public class ZKConnection {
    private ZooKeeper zoo;

    boolean createdZ = false;

    public Watcher createDeleteWatcher(String path) {
        return new Watcher() {
            @Override
            public void process(WatchedEvent event) {
                if (event.getType() == Event.EventType.NodeDeleted && event.getPath().equals(path) && createdZ) {
                    createdZ = false;
                    System.out.println("#####################");
                    System.out.println("##### Z DELETED #####");
                    System.out.println("#####################");
                    ExternalApp.stop();
                }
            }
        };
    }
    

    public Watcher createParentWatcher(String command) {
        return new Watcher() {
            @Override
            public void process(WatchedEvent event) {
                try {
                    if (event.getType() == Watcher.Event.EventType.NodeChildrenChanged && zoo.getChildren(event.getPath(), false).contains("z") && !createdZ) {
                        createdZ = true;
                        System.out.println("#####################");
                        System.out.println("##### Z CREATED #####");
                        System.out.println("#####################");
                        ExternalApp.start(command);
                    }
                } catch (KeeperException | InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        };
    }

    public Watcher createSimpleWatcher(){
        return event -> {
            if (event.getType() == Watcher.Event.EventType.None) {
                System.out.println("|||||  CONNECTION " + event.getState().toString().toUpperCase() + " |||||");
            }
        };
    }


    public ZooKeeper connect(String host) throws IOException{
        zoo = new ZooKeeper(host, 5000, createSimpleWatcher());
        return zoo;
    }


    public void close() throws InterruptedException {
        zoo.close();
    }
}