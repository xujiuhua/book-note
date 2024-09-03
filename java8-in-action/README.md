# java8 in action

## lambda
```
Passing a lambda, a one-off piece of code such as 
apple -> apple.getWeight() > 150
```
```
Passing a method reference, to an existing method, as code such as 
Apple::isHeavy
```

Real-World(现实中的确存在的)

#### 1. Comparator
```java
public interface Comparator<T> {
    int compare(T o1, T o2); 
}
```
prior java8
```java
inventory.sort(new Comparator<Apple>() { 
    public int compare(Apple a1, Apple a2){
        return a1.getWeight().compareTo(a2.getWeight()); 
    }
});
```
java8
```java
inventory.sort(
(Apple a1, Apple a2) -> a1.getWeight().compareTo(a2.getWeight()));
```

#### 2.  Runnable
```java
public interface Runnable{ 
    void run();
}
```

prior java8
```java
Thread t = new Thread(new Runnable() { 
    public void run(){
        System.out.println("Hello world"); 
    }
});
```

java8
```java
Thread t = new Thread(() -> System.out.println("Hello world"));
```

#### 3.  GUI event
```java
Button button = new Button("Send"); 
button.setOnAction(new EventHandler<ActionEvent>() {
    public void handle(ActionEvent event) { 
        label.setText("Sent!!");
    } 
});
```

java8
```java
button.setOnAction((ActionEvent event) -> label.setText("Sent!!"));
```

## todo list

- [ ] date&time

- [ ] stream

- [ ] defaultMethod

- [ ] optional

- [ ] collection



