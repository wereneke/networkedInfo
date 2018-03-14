package com.weratom.modes.service;

import com.weratom.modes.Client;

import java.util.Scanner;

public class Listener implements Runnable {

//    private Thread printingThread;

//    public Listener(Thread tPr) {
//        this.printingThread = tPr;
//    }
    Client client;

    public Listener(Client client) {
        this.client = client;
    }

    public void run() {

        Scanner scanner = new Scanner(System.in);
        boolean listening = true;
        while (listening) {

            if (scanner.hasNextLine()) {
                System.out.println("Enter Key pressed.");
                client.stopAskingServer();
                scanner.close();
                listening = false;
                Thread.currentThread().interrupt();
            }
//                Thread.sleep(1000);
        }

    }
}
