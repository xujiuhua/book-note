package com.cj.fx15.clazz.pair3;

import com.cj.fx15.clazz.Pair;

import java.math.BigDecimal;

/**
 * <p></p>
 *
 * @author jiuhua.xu
 * @version 1.0
 * @since JDK 1.8
 */
public class TestPair3 {
    public static void main(String[] args) {

        final Employee a = new Employee();
        a.setName("A");
        final Employee b = new Employee();
        b.setName("B");
        final Pair<Employee> employeePair = new Pair<>(a, b);
        printBuddies(employeePair);


        final Manager ceo = new Manager();
        ceo.setName("ceo");
        final Manager cfo = new Manager();
        cfo.setName("cfo");

        // 注意不能把Pair<Manager>传递给方法printBuddies(Pair<Employee>)
        final Pair<Manager> managerPair = new Pair<>(ceo, cfo);
        // printBuddies(managerPair);

        // 所以需要通配符
        printBuddies2(managerPair);


        // 不会破坏
        Pair<? extends Employee> em = managerPair;

        // 合法 ? extends Employee getFirst()
        final Employee first = em.getFirst();

        // 编译器只知道需要某个Employee的子类型,但不知道具体是什么类型； 拒绝传递任何特定的类型，毕竟?不能用来匹配
        // void setFirst(? extends Employee)
        // em.setFirst(a);  // 编译错误
        // em.setFirst(ceo);  // 编译错误


        // 超类限定
        printBonus();

    }

    private static void printBonus() {

        final Manager ceo = new Manager();
        ceo.setBonus(BigDecimal.TEN);
        final Manager cfo = new Manager();
        cfo.setBonus(BigDecimal.ONE);

        Manager[] a = {ceo, cfo};


        // Pair<Employee>, Pair<Object> 都是合理的
        // final Pair<Employee> result = new Pair<>();
        final Pair<Object> result = new Pair<>();
        minmaxBonus(a, result);
        System.out.println(result);
        // 不能保证返回的对象的类型
        final Object first = result.getFirst();

    }

    public static void printBuddies(Pair<Employee> p) {
        final Employee first = p.getFirst();
        final Employee second = p.getSecond();
        System.out.println(first.getName());
        System.out.println(second.getName());
    }

    public static void printBuddies2(Pair<? extends Employee> p) {
        final Employee first = p.getFirst();
        final Employee second = p.getSecond();
        System.out.println(first.getName());
        System.out.println(second.getName());
    }


    public static void minmaxBonus(Manager[] a, Pair<? super Manager> result) {

        if (a.length == 0) return;

        Manager min = a[0];
        Manager max = a[0];

        for (int i = 0; i < a.length; i++) {
            if (min.getBonus().compareTo(a[i].getBonus()) > 0) {
                min = a[i];
            }
            if (max.getBonus().compareTo(a[i].getBonus()) < 0) {
                max = a[i];
            }
        }

        // 只能传递Manager对象 或者是某个子类型对象
        result.setFirst(min);
        result.setSecond(max);

    }
}
