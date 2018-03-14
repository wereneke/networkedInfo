package com.weratom.modes.service;

import com.weratom.modes.Client;

import java.util.Scanner;

public class Listener implements Runnable {

    private Thread client;

    public Listener(Thread client) {
        this.client = client;
    }

    public void run() {

        Scanner scanner = new Scanner(System.in);
        boolean listening = true;
        while (listening) {

            if (scanner.hasNextLine()) {
                System.out.println("Enter Key pressed.");
                client.interrupt();
                scanner.close();
                listening = false;
                Thread.currentThread().interrupt();
            }
        }
    }
}
