package com.weratom.modes.server;

import com.weratom.modes.service.StatsChecker;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Server implements Runnable {

    private int serverPort = 9999;
    private ServerSocket serverSocket;
    private boolean isStopped = false;
    private ExecutorService threadPool = Executors.newFixedThreadPool(4);
    private StatsChecker stats = new StatsChecker();

    @Override
    public void run() {

        Thread statsThread = new Thread(stats);
        statsThread.start();
        openServerSocket();

        while (!isStopped) {
            Socket clientSocket;
            try {
                clientSocket = this.serverSocket.accept();
            } catch (IOException e) {
                if (isStopped) {
                    System.out.println("server stopped");
                    break;
                }
                throw new RuntimeException("Error accepting client connection", e);
            }
            this.threadPool.execute(new ServerWorker(clientSocket, stats));
        }
        this.threadPool.shutdown();
        this.stop();
    }

    public synchronized void stop(){
        this.isStopped = true;
        try {
            this.serverSocket.close();
        } catch (IOException e) {
            throw new RuntimeException("Error closing server", e);
        }
    }

    private void openServerSocket() {
        try {
            this.serverSocket = new ServerSocket(this.serverPort);
        } catch (IOException e) {
            throw new RuntimeException("Cannot open port 9999", e);
        }
    }
}
