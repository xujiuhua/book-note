# Java并发编程的艺术

## 并发编程的挑战

### 如何减少上下文切换
- 无锁并发编程：如将数据的id按照Hash算法取模分段，不同的线程处理不同段的数据
- CAS算法
- 使用最少线程
- 协程：在单线程里实现多任务的调度

### 避免死锁的常见方法
- 避免一个线程同时获得多个锁
- 避免一个线程在锁内同时占用多个资源，尽量保证每个所只占用一个资源
- 尝试使用定时锁，lock.tryLock(timeout)
- 对于数据库锁，加锁和解锁必须在一个数据库连接里

### 资源限制的挑战
例如服务器贷款2Mb/s,某个资源的下载速度是1Mb/s,系统启动10个线程，现在速度也不会达到10Mb/s
- 对于硬件资源限制，可以考虑使用集群并发执行程序；比如hadoop、ODPS
- 对于软件资源限制，可以考虑使用资源池将资源复用；比如数据库连接池，socket连接池等


## Java并发编程的底层实现原理
> Java中所使用的并发机制y依赖于JVM的实现和CPU的指令

### volatile 的两条实现原理
> 为了提高处理速度，处理器不直接和内存进行通信，而是先将系统内存的数据读到内部缓存（L1、L2或其他）后再进行操作，_但操作完，不知道何时会写入内存_。
如果对申明了volatile的变量进行写操作，JVM会向处理器发送一条Lock前缀的指令，将这个变量所在内存行的数据写回到内存。但是就算写回到内存，其他处理器缓存的值还是旧的。
所以多处理器实现了缓存一致性，每个处理器会检查自己的缓存值是不是过期了，如发现地址被修改，则设置为无效状态，并重新从系统内存中把数据读到处理器缓存里

1. Lock前缀指令会引起处理器缓存回写到内存
2. 一个处理器的缓存回写内存，会导致其它处理的缓存无效

### 处理器如何实现原子操作
> Java中通过锁和循环CAS的方式来实现原子操作

1. 使用总线锁保证原子性
2. 使用缓存锁保证原子性

## Java内存模型

### happens-before
两个操作之间具有happens-before关系，并不意味前一个操作必须要在后一个操作之前执行！
happens-before仅仅要求前一个操作对（执行的结果）后一个操作可见

1. 程序顺序规则
2. 监视器锁规则：对一个锁的解锁, happens-before于随后这个锁的加锁
3. volatile规则: 对一个volatile域的写，happens-before 任意后续对这个volatile域的读
4. 传递性：A happens-before B ,B happens-before C --> A happens-before C

### 重排序-三种数据依赖性
- 写后读: a = 1; b = a;
- 写后写: a = 1; a = 2;
- 读后写: a = b; b = 1;

只要重排序两个操作的执行顺序，程序的执行结果就会被改变

在单线程中，对存在控制依赖的操作重排序，不会改变执行结果（as-if-serial语义允许）；
在多线程中，对存在控制依赖的操作重排序，可能会改变程序的执行结果。

### 双重检验锁定和延迟初始化
见`DoubleCheckedLocking`
问题根源在第7行`instance = new Instance();`，可以分解为如下：
```
memory = allocate();
ctorInstance(memory);
instance = memory;
```
然后重排序为,单线程不会改变程序的执行结果，以下重排序允许，例如第4步调用instance,无论如何2肯定是在4前面，合理；
但是线程会导致从步骤3多读取到一个不完整的对象。
```
memory = allocate(); // 1分配内存空间
instance = memory; // 3指向内存地址
ctorInstance(memory); // 2初始化对象
```

解决办法：1、使用volatile 2、基于类的初始化

## Java并发编程基础
### Java线程状态
> ThreadState.java, 使用java自带工具查看， jps, jstack pid
- NEW: 初始状态，但是还乜有调用start()方法
- RUNNABLE: 运行状态
- BLOCKED: 阻塞状态,表示线程阻塞于锁
- WAITING: 等待状态，需要其他线程做出动作（通知或中断）e.g.  Waiting.class.wait()
- TIME_WAITING: 超时等待，可以在指定时间自行返回，e.g. Thread.sleep(100)
- TERMINATED: 终止状态，线程已执行完毕

### 安全地终止线程
> Shutdown.java

通过标识位或者中断操作的方式，能够使线程在终止时有机会去清理资源，而不是武断的将线程停止

### 等待通知机制
> WaitNotify.java
- notify() 通知一个在对象上等待的线程，使其从wait方法返回，而返回的前提是该线程获取到了对象的锁
- notifyAll() 通知所有等待在该对象上的线程
- wait() 调用该方法线程进入WAITING状态，只有等待另外线程的通知或中断才会返回，注意调用此方法，会释放对象锁
- wait(long) 超时等待
- wait(long, int) 超时等待，时间更细粒

## Java中的锁

- 独占锁：锁在一个时间点只能被一个线程锁占有。根据锁的获取机制，它又划分为“公平锁”和“非公平锁”。公平锁，是按照通过CLH等待线程按照先来先得的规则，公平的获取锁；而非公平锁，则当线程要获取锁时，它会无视CLH等待队列而直接获取锁。独占锁的典型实例子是ReentrantLock，此外，ReentrantReadWriteLock.WriteLock也是独占锁。
- 共享锁：能被多个线程同时拥有，能被共享的锁。JUC包中的ReentrantReadWriteLock.ReadLock，CyclicBarrier， CountDownLatch和Semaphore都是共享锁。这些锁的用途和原理，在以后的章节再详细介绍。
- 重入锁：该锁能够支持一个线程对资源的重复加锁(1、线程再次获取锁，线程需要去识别获取锁的线程是否是的当前线程；2、锁的最终释放，线程重复n次获取锁，随后n次释放锁，其他线程才能获取锁，计数器)

源码 ReentrantLock
```java
final boolean nonfairTryAcquire(int acquires) {
    final Thread current = Thread.currentThread();
    int c = getState();
    if (c == 0) {
        if (compareAndSetState(0, acquires)) {
            setExclusiveOwnerThread(current);
            return true;
        }
    }
    else if (current == getExclusiveOwnerThread()) {
        int nextc = c + acquires;
        if (nextc < 0) // overflow
            throw new Error("Maximum lock count exceeded");
        setState(nextc);
        return true;
    }
    return false;
}
```

example1:
```java
class MyClass {
    public synchronized void method1() {
        method2();
    }
 
    public synchronized void method2() {
 
    }
}
```
example2:
```java
public class ReentrantLockTest {

    private static Lock lock = new ReentrantLock();

    public static void main(String[] args) {
        m1();
    }

    private static void m1() {
        lock.lock();
        System.out.println("m1");
        m2();
        lock.unlock();
    }

    private static void m2() {
        lock.lock();
        System.out.println("m2");
        lock.unlock();
    }

}
```
上述代码中的两个方法method1和method2都用synchronized修饰了，假如某一时刻，线程A执行到了method1，此时线程A获取了这个对象的锁，而由于method2也是synchronized方法，假如synchronized不具备可重入性，此时线程A需要重新申请锁。但是这就会造成一个问题，因为线程A已经持有了该对象的锁，而又在申请获取该对象的锁，这样就会线程A一直等待永远不会获取到的锁。

而由于synchronized和Lock都具备可重入性，所以不会发生上述现象。

- 读写锁：在同一时刻允许多个【读】线程同时访问，但是在写线程时，所有读线程和其他写线程都被阻塞；维护了一对锁，一个读锁和一个写锁
> 一般情况下，读写锁的性能都比排他锁好，因为大多数场景读是多于写的，读写锁能够提供比排他锁更好的并发性和吞吐量



## Java中的并发工具类
- CountDownLatch 允许一个或者多个线程等待其他线程完成工作
- CyclicBarrier 是一个同步辅助类，允许一组线程互相等待，直到到达某个公共屏障点 (common barrier point)。因为该 barrier 在释放等待线程后可以重用，所以称它为循环 的 barrier
- Semaphore 控制并发线程数
- Exchanger 线程间交换数据 (Exchanger类仅可用作两个线程的信息交换，当超过两个线程调用同一个exchanger对象时，得到的结果是随机的)

## Java中的线程池

### 线程池的3个好处
1. 降低资源消耗：可以重复利用已创建的线程，降低线程创建和销毁
2. 提高响应速度：当任务到达时，因为线程已创建好，可以立即运行
3. 提高线程的可管理性：调优、监控等

```
如果当前线程池中的线程数目小于corePoolSize，则每来一个任务，就会创建一个线程去执行这个任务；
如果当前线程池中的线程数目>=corePoolSize，则每来一个任务，会尝试将其添加到任务缓存队列当中，若添加成功，则该任务会等待空闲线程将其取出去执行；若添加失败（一般来说是任务缓存队列已满），则会尝试创建新的线程去执行这个任务；
如果当前线程池中的线程数目达到maximumPoolSize，则会采取任务拒绝策略进行处理；
如果线程池中的线程数量大于 corePoolSize时，如果某线程空闲时间超过keepAliveTime，线程将被终止，直至线程池中的线程数目不大于corePoolSize；如果允许为核心池中的线程设置存活时间，那么核心池中的线程空闲时间超过keepAliveTime，线程也会被终止。
```