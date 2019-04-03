package com.xujh.task;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

/**
 * <p>
 * AAA:
 *
 * </p>
 *
 * @author jiuhua.xu
 * @version 1.0
 * @since JDK 1.8
 */
public class TaskExecutionWebServer {

     private static final Executor exec = Executors.newFixedThreadPool(100);

    public static void main(String[] args) throws IOException {
        ServerSocket socket = new ServerSocket(7777);
        while (true) {
            Socket connection = socket.accept();
            exec.execute(() -> handleRequest(connection));
        }
    }

    private static void handleRequest(Socket connection) {
        // request-handling logic here
    }
}
