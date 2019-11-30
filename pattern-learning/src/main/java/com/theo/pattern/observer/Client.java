package com.theo.pattern.observer;

/**
 * @author huangsuixin
 * @date 2019/11/30 16:55
 */
public class Client {
    public static void main(String[] args) {
        ConcreteSubject subject = new ConcreteSubject();

        ObserverA observer1 = new ObserverA();
        ObserverA observer2 = new ObserverA();
        ObserverA observer3 = new ObserverA();

        subject.register(observer1);
        subject.register(observer2);
        subject.register(observer3);

        // 改变subject 的状态
        subject.setState(3000);

        System.out.println(observer1.getState());
        System.out.println(observer2.getState());
        System.out.println(observer3.getState());

    }
}
