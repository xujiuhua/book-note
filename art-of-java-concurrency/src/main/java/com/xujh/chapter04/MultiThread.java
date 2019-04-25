package com.xujh.chapter04;

import java.lang.management.ManagementFactory;
import java.lang.management.ThreadInfo;
import java.lang.management.ThreadMXBean;

/**
 * <p>
 *
 *     一个Java程序的运行部仅仅是main()方法的运行，而是main线程和多个线程同时运行
 *
 * </p>
 *
 * @author jiuhua.xu
 * @version 1.0
 * @since JDK 1.8
 */
public class MultiThread {

    public static void main(String[] args) {

        // 获取Java线程管理MXBean
        ThreadMXBean threadMXBean = ManagementFactory.getThreadMXBean();

        // 不需要获取同步的monitor和synchronize信息仅获取线程和线程堆信息
        ThreadInfo[] threadInfos = threadMXBean.dumpAllThreads(false, false);

        for (ThreadInfo threadInfo : threadInfos) {
            System.out.println("[" + threadInfo.getThreadId() + "] " + threadInfo.getThreadName());
        }
    }

}
