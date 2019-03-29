# java concurrency in practice

> 编写正确的并发程序的关键是对共享变量、可变的状态进行访问管理

- 因为程序调度的基本单元是线程，一个单线程应用一次只能运行在一个处理器上，
在双处理器系统中，一个单线程程序，放弃了其中一半的空闲资源；
在拥有100个处理器的系统中，这个单线程程序放弃了90%的资源；

- 无论何时，只要有多余一个的线程访问指定的状态变量，而且其中某个线程会写入该变量，此时必须使用同步来协调线程对变量的访问

- 一个类线程安全的，是指在被多个线程范围访问时，类可以持续进行正确的行为

- check-then-act: 你观察一些事情为真（文件X不存在），然后（then）基于你的观察去执行一些动作（创建文件X）；
但事实上，从观察到执行操作的这段时间内，观察结果可能已经无效了（有人在此期间创建了文件X），从而引发错误（非预期的异常，
重写数据或者破坏文件）

- 惰性初始化中的竞争条件

- 当一个不变约束涉及多个变量时，变量间不是彼此独立的；某变量的值会制约其他几个变量的值。
因此，更新一个变量的时候，要在统一原子操作中更新其他几个。

- 内部锁

- 可重入锁：https://blog.csdn.net/yanyan19880509/article/details/52345422/

- 不可以将一个原子操作分解到多个synchronized中，不过应尽量从synchronized中分离出耗时且不影响共享状态的操作

### 共享对象

#### 可见性
- 最低限的安全性应用于所有变量，除了一个例外，没有声明volatile的64位数变量(long,double),高32位和低32位
```java
public class SynchronizedInteger {
    @GuardedBy("this") private int value;

    public synchronized int get() {
        return value;
    }

    public synchronized void set(int value) {
        this.value = value;
    }
}
```
- 当方位一个共享变量时，为什么要求所有线程都由同一个锁进行同步？第一保证一个线程第变量的写入，其他线程也都可见；
第二如果一个线程没有使用恰当的锁读取了变量，这个变量可能是一个过期的数据

- volatile,一种同步的弱形式，当一个域申明为volatile类型后，编译器与运行时会监视这个变量（它是共享的）；
而且对他的操作不会与其他内存操作一起被重排序。

- 正确使用volatile的方式包括：用于确保它们所引用对象状态的可见性，或者用于标识重要的生命周期事件（比如初始化或关闭）的发生

> 加锁可以保证可见性和原子性；volatile只能保存可见性

- 使用volatile变量需要满足所有标准：1.写入变量不依赖变量的当前值 2.变量不需要其他的状态变量共同参与不变约束 3.访问变量没有其他的原因需要加锁

#### 发布和溢出

- 最常见的发布对象的方式是将对象的引用存储到公共静态域中，任何类和线程都看见此域
```java
public class UnsafeStates {
    private String[] states = new String[]{
            "AK", "AL"
    };

    public String[] getStates() {
        return states;
    }

    public static void main(String[] args) {
        UnsafeStates unsafeStates = new UnsafeStates();
        unsafeStates.states = new String[]{"hello", "world"};
    }
}
```