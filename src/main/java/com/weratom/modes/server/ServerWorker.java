package com.weratom.modes.server;

import com.weratom.modes.service.StatsChecker;

import java.io.*;
import java.net.Socket;

public class ServerWorker implements Runnable {

    private Socket clientSocket;
    private StatsChecker statsChecker;

    public ServerWorker(Socket clientSocket, StatsChecker statsChecker) {

        this.clientSocket = clientSocket;
        this.statsChecker = statsChecker;
    }

    @Override
    public void run() {
        try {
            DataInputStream input = new DataInputStream(clientSocket.getInputStream());
            DataOutputStream output = new DataOutputStream(clientSocket.getOutputStream());

                while (input.readUTF().equals("start")) {
                    output.writeUTF(statsChecker.getResponse());
                    output.flush();
                }

        }catch (IOException e) {
            e.printStackTrace();
        }
    }
}
