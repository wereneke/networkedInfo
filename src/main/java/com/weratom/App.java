package com.weratom;

import com.weratom.modes.Client;
import com.weratom.modes.server.Server;

public class App
{
    public static void main(String[] args) {

        String mode = args[0];
        int port = 9999;

        if (mode.equals("server")){
            Thread server = new Thread(new Server());
            server.start();
        }
        else if (args[0].equals("client")) {
            Client client = new Client("localhost");

                client.askServerForInfo();

            client.stopaskingServer();
        }
    }
}
