package com.weratom.modes;

import java.io.*;
import java.net.Socket;

public class Client {

    private Socket socket;
    private DataInputStream inputStream;
    private DataOutputStream outputStream;

    public Client(String ip) {
        connectSocket(ip);
    }

    private void connectSocket(String ip) {
        try {
            this.socket = new Socket(ip, 9999);
            this.inputStream = new DataInputStream(socket.getInputStream());
            this.outputStream = new DataOutputStream(socket.getOutputStream());

        }catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void askServerForInfo() {

        try {
            outputStream.writeUTF("start");
            outputStream.flush();
            System.out.println(inputStream.readUTF());

        } catch (IOException e) {
            e.printStackTrace();
            closeConnection();
        }
    }

    public void stopAskingServer() {

        try {
            outputStream.writeUTF("stop");
            outputStream.flush();
            closeConnection();

        } catch (IOException e) {
            e.printStackTrace();
            closeConnection();
        }
    }

    public void closeConnection() {
        try {
            this.inputStream.close();
            this.outputStream.close();
            this.socket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
