package org.example;

import org.apache.zookeeper.*;
import org.apache.zookeeper.data.Stat;

import java.io.IOException;
import java.util.List;

public class ZKManager {
    private static ZooKeeper zkeeper;
    private static ZKConnection zkConnection;
    int nodesNum;
    String command;

    public ZKManager(String command) throws IOException, InterruptedException, KeeperException {
        zkConnection = new ZKConnection();
        zkeeper = zkConnection.connect("127.0.0.1:2181");
        this.command = command;
        zkeeper.getChildren("/", zkConnection.createParentWatcher(command));
    }


    public void close() throws InterruptedException {
        zkConnection.close();
    }

    public void create(String path, byte[] data) throws Exception {
        zkeeper.create(
                path,
                data,
                ZooDefs.Ids.OPEN_ACL_UNSAFE,
                CreateMode.PERSISTENT);

        zkeeper.getChildren("/", zkConnection.createParentWatcher(command));
        zkeeper.getChildren(path, zkConnection.createDeleteWatcher("/z"));

        countAllNodes();
    }

    public void delete(String path) throws InterruptedException, KeeperException {
        Stat stat = zkeeper.exists(path, false);
        if (stat != null) {
            zkeeper.delete(path, -1);
        }

        zkeeper.getChildren("/", zkConnection.createParentWatcher(command));
    }


    public void update(String path, byte[] data) throws KeeperException, InterruptedException {
        int version = zkeeper.exists(path, true).getVersion();
        zkeeper.setData(path, data, version);
    }


    private void countAllNodes() throws Exception {
        nodesNum = -1;
        countNodes("/");
        System.out.println("----------------------------");
        System.out.println("--- NODES IN SYSTEM : " + nodesNum + " ----" );
        System.out.println("----------------------------");
    }

    private void countNodes(String path) throws Exception {
        nodesNum++;
        for (String child : zkeeper.getChildren(path, false)) {
            if(path.equals("/")) {
                countNodes("/" + child);
            }else{
                countNodes(path + "/" + child);
            }
        }
    }


    public void printAllNodes(String path) throws InterruptedException, KeeperException {
        System.out.println("+++++++++++++++++++++");
        showChilds(path);
        System.out.println("+++++++++++++++++++++");
    }


    private void showChilds(String path) throws InterruptedException, KeeperException {
        List<String> childs = zkeeper.getChildren(path, false);
        System.out.println("+++ " + path + " -> " + childs);
        for (String child : childs){
            if(path.equals("/")) {
                showChilds("/" + child);
            }else{
                showChilds(path + "/" + child);
            }
        }
    }
}