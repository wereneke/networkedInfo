package com.weratom;

import com.weratom.modes.Client;
import com.weratom.modes.server.Server;

public class App
{
    public static void main(String[] args) {

        int port = 9999;

        if (args[0].equals("server")){
            Thread server = new Thread(new Server());
            server.start();
        }
        else if (args[0].equals("client")) {
            Client client = new Client("localhost");
            for (int i=0; i<5; i++) {
                client.askServerForInfo();
            }
            client.stopaskingServer();
        }
    }
}
