package com.xujh.task;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * <p>
 *     A：
 *     一次只能处理一个请求，主线程不断地在“接受连接”和“处理请求”之间交替运行，
 *     直到主线程完成了完成的当前请求并再次调用accept,此前的请求都必须等待
 *
 *     缺点：无高并发，性能瓶颈
 * </p>
 *
 * @author jiuhua.xu
 * @version 1.0
 * @since JDK 1.8
 */
public class SingleThreadWebServer {
    public static void main(String[] args) throws IOException {
        ServerSocket socket = new ServerSocket(7777);
        while (true) {
            Socket connection = socket.accept();
            handleRequest(connection);
        }
    }

    private static void handleRequest(Socket connection) {
        // request-handling logic here
    }
}
