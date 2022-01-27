# java核心技术卷10


## 第15章 泛型

> 泛型概述在Java中实现泛型机制的目标是为了可以把发现 bug 的时机提前到编程源码时，而不是运行时。如果在编译时就可以发现 bug ，就可以节省大量的调试Java程序的时间，因为编译错误可以比较容易和比较快速地被发现和修复。而且，泛型仅仅只存在于编译时，这个事实也是我们学习Java泛型的过程中必须记住的最重要的一个事实。

- 泛型类
- 泛型接口
- 泛型方法

### 泛型类

> 格式：Pair类引入了一个类型变量T，用尖括号（< >）括起来，并放在类后面

```java
public class Pair<T> {

    private T first;
    private T second;

    public Pair() {
        first = null;
        second = null;
    }

    public Pair(T first, T second) {
        this.first = first;
        this.second = second;
    }

    public T getFirst() {
        return first;
    }

    public T getSecond() {
        return second;
    }

    public void setFirst(T first) {
        this.first = first;
    }

    public void setSecond(T second) {
        this.second = second;
    }
}
```

### 泛型方法

> 格式：类型变量放在修饰符的后面，返回类型的前面；
> 泛型方法可以定义在普通类中， 也可以定义在泛型类中

```java
public class ArrayAlg {
    public static <T> T getMiddle(T... a) {
        return a[a.length / 2];
    }
}
```

```java
// 调用格式
final String m1 = ArrayAlg.<String>getMiddle("hello", "world");

// 调用格式可以类型推导，省略类型
final String m2 = ArrayAlg.getMiddle("hello", "world");

final double middle = ArrayAlg.getMiddle(3.14, 33.45, 0.67);
```

### 泛型接口

> 格式：修饰符 interface接口名<代表泛型的变量> { }

```
使用方式1：实现类实现接口、同时指定泛型类型。

使用方式2：实现类实现接口但不指定泛型，这个类也就成了泛型类。ArrayList类本质上也就是这种情况，它实现了List<E>接口，但是没有指定泛型。
```

### 类型变量的限定
```java
public static <T extends Comparable & Serializable> T min(T[]a) ...
```

### 通配符类型

> 带有超类型限定的通配符可以向泛型对象写入，带有子类型限定的通配符可以从泛型对象读取


#### 子类型限定
```java
// 合法  返回的类型肯定属于Employee
? extends Employee getFirst()

// 编译器只知道需要某个Employee的子类型,但不知道具体是什么类型； 拒绝传递任何特定的类型，毕竟?不能用来匹配
void setFirst(? extends Employee)
```

#### 超类型限定
```java
// 合法，只能传递Manager类型或子类型。 不能传递Employee, Object类型
void setFirst(? super Manager)
        
// 不能保证返回对象类型，只能赋值给一个Object对象
? super Manager getFirst()
```

#### 无限定通配符

对于一些简单的操作非常有用

```java
public class TestQuestion {
    public static void main(String[] args) {
        ArrayList<Integer> a = new ArrayList<>();
        ArrayList<String> b = new ArrayList<>();

        method(a);
        method(b);
    }

    public static void method(ArrayList<?> m) {
        for (Object o : m) {
            System.out.println(o);
        }
    }
}
```