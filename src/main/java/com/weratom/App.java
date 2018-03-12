package com.weratom;

import com.weratom.modes.Client;
import com.weratom.modes.server.Server;

import java.util.Scanner;

public class App
{

    private static Scanner in = new Scanner(System.in);
    public static void main( String[] args ) {

        Server server = new Server();
        new Thread(server).start();

        Client client = new Client("localhost");
        boolean toRun = true;
        while (toRun) {
            try {
                client.askServerForInfo();
                Thread.sleep(1000);
                System.out.println("exit? y/n");
                toRun = (in.nextLine().toLowerCase().equals("y")) ? false : true;

                if(!toRun) server.stop();

            }catch (InterruptedException e) {
                e.printStackTrace();
            }
        }


    }
}
