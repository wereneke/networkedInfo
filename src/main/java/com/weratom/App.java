package com.weratom;

import com.weratom.modes.Client;
import com.weratom.modes.server.Server;


public class App
{
    public static void main(String[] args) {

        String mode = args[0];

        if (mode.equals("server")){
            Thread server = new Thread(new Server());
            server.start();
        }
        else if (mode.equals("client")) {
            Client client = new Client("localhost");
            Thread clientThread = new Thread(client);
            clientThread.start();

        }
    }
}
