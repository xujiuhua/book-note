package com.xujh.task;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * <p>
 *     AA:
 *     为每个任务启动一个新线程处理请求，类似单线程--主线程仍然不断地交替运行“接受外部连接”和“转发请求”
 *
 *     注意：任务处理代码必须是线程安全的，因为多个任务会并发调用
 *
 *     缺点：没有对已创建线程的数量进行任务限制，存在资源管理缺陷
 * </p>
 *
 * @author jiuhua.xu
 * @version 1.0
 * @since JDK 1.8
 */
public class ThreadPerTaskWebServer {
    public static void main(String[] args) throws IOException {
        ServerSocket socket = new ServerSocket(7777);
        while (true) {
            Socket connection = socket.accept();
            new Thread(() -> handleRequest(connection)).start();
        }
    }

    private static void handleRequest(Socket connection) {
        // request-handling logic here
    }
}
