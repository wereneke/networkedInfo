package com.weratom.modes.service;

import java.util.Scanner;

public class InputCollector implements Runnable {

    Scanner scanner = new Scanner(System.in);

    @Override
    public void run() {
        String input;
        while (!Thread.interrupted()) {
            input = scanner.nextLine();
            if (input.equals(".exit")) Thread.interrupted();
        }
    }
}
