package com.github.book.optional;

import java.time.LocalDateTime;
import java.util.Optional;

/**
 * <p></p>
 *
 * @author jiuhua.xu
 * @version 1.0
 * @since JDK 1.8
 */
public class OptMain {
    public static void main(String[] args) {
        Car car = null;
//        Optional<Car> optCar = Optional.of(car);

//        Optional<Car> optCar = Optional.empty();

//        Optional<Car> optCar = Optional.ofNullable(car);

//        System.out.println(optCar.get());


//        Insurance insurance = null;
//        Optional<Insurance> optInsurance = Optional.ofNullable(null);
//        Optional<String> name = optInsurance.map(Insurance::getName);
//        System.out.println(name);
//
//
//        Person person = null;
//        Optional<Person> optPerson = Optional.ofNullable(person);
//        Optional<Car> car1 = optPerson.map(Person::getCar);
//        Optional<Insurance> insurance1 = car1.map(Car::getInsurance);
//        Optional<String> s = insurance1.map(Insurance::getName);
//
//        String s1 = optPerson.map(Person::getCar)
//                .map(Car::getInsurance)
//                .map(Insurance::getName)
//                .orElse("Unknown");
//
//        System.out.println(s1);
//
//        Insurance insurance2 = new Insurance();
//        insurance2.setName("hello optional");
//
//        Car car2 = new Car();
//        car2.setInsurance(insurance2);
//
//        Person person2 = new Person();
//        person2.setCar(car2);
//
        Optional<Person> optPerson2 = Optional.ofNullable(null);

        Person person = optPerson2.orElseGet(Person::new);


        LocalDateTime l1 = null;
        Optional<LocalDateTime> l11 = Optional.ofNullable(l1);
        LocalDateTime l111 = l11.orElse(LocalDateTime.now());
        System.out.println(l111);

//        String s2 = optPerson2.map(Person::getCar)
//                .map(Car::getInsurance)
//                .map(Insurance::getName)
//                .orElse("Unknown");
//        System.out.println(s2);

//        Optional<Person> optPerson3 = Optional.ofNullable(null);
//        String s3 = optPerson3.flatMap(Person::getCar)
//                .flatMap(Car::getInsurance)
//                .map(Insurance::getName)
//                .orElse("Unknown");
//        System.out.println(s3);

//        int i = Integer.parseInt("12");
    }
}
