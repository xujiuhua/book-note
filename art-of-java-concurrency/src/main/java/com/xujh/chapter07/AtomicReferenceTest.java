package com.xujh.chapter07;

import lombok.Data;

import java.util.concurrent.atomic.AtomicReference;

/**
 * <p></p>
 *
 * @author jiuhua.xu
 * @version 1.0
 * @since JDK 1.8
 */
public class AtomicReferenceTest {

    private static AtomicReference<User> atomicReference =  new AtomicReference<>();

    public static void main(String[] args) {
        User user = new User("Tom", 18);
        atomicReference.set(user);

        // success
        User updateUser = new User("Jack", 20);
        atomicReference.compareAndSet(user, updateUser);

        System.out.println(atomicReference.get().getName());
        System.out.println(atomicReference.get().getAge());

        // fail
        User updateUser2 = new User("Jack2", 22);
        atomicReference.compareAndSet(user, updateUser2);

        System.out.println(atomicReference.get().getName());
        System.out.println(atomicReference.get().getAge());


    }

    @Data
    static class User {
        private String name;
        private int age;

        User(String name, int age) {
            this.name = name;
            this.age = age;
        }


    }
}
