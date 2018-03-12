package com.weratom.modes.server;

import java.io.*;
import java.net.Socket;

public class ServerWorker implements Runnable {

    private Socket clientSocket;

    public ServerWorker(Socket clientSocket) {
        this.clientSocket = clientSocket;
    }

    @Override
    public void run() {
        try {
            DataInputStream input = new DataInputStream(clientSocket.getInputStream());
            DataOutputStream output = new DataOutputStream(clientSocket.getOutputStream());

            output.writeUTF("information about cpu etc.");
            output.flush();

            input.close();
            output.close();

        }catch (IOException e) {
            e.printStackTrace();
        }
    }
}
