package org.example;

import java.io.IOException;

public class ExternalApp {
    private static Process appProcess;

    public static void start(String appName) {
        try {
            appProcess = Runtime.getRuntime().exec(appName);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void stop() {
        if (appProcess != null) {
            appProcess.destroy();
            appProcess = null;
        }
    }
}
