package com.automation.develop.utilities;

import java.io.PrintWriter;
import java.util.concurrent.TimeUnit;

public class my_main {

    public static void main(String[] args) {
        String[] command =
                {
                        "cmd",
                };
        Process p;
        try {
            p = Runtime.getRuntime().exec(command);
            new Thread(new SyncPipe(p.getErrorStream(), System.err)).start();
            new Thread(new SyncPipe(p.getInputStream(), System.out)).start();
            PrintWriter stdin = new PrintWriter(p.getOutputStream());
            stdin.println("taskkill /im chromedriver.exe /f");
            stdin.println("cd src\\test\\resources\\Executables");
            stdin.println("java -jar selenium-server-4.1.1.jar hub");
            stdin.close();
            p.waitFor(2, TimeUnit.SECONDS);
            PrintWriter stdin2 = new PrintWriter(p.getOutputStream());
            stdin2.println("cd src\\test\\resources\\Executables");
            stdin2.println("java -jar selenium-server-4.1.1.jar node");
            stdin2.close();


        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
