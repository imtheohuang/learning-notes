package com.theo.pattern.observer;

import java.util.ArrayList;
import java.util.List;

/**
 * @author huangsuixin
 * @date 2019/11/30 16:49
 */
public class Subject {
    protected List<Observer> list = new ArrayList<>();

    public void register(Observer observer) {
        list.add(observer);

    }

    public void remove(Observer observer) {
        list.remove(observer);
    }

    /**
     * 通知所有的观察者重载状态
     */
    public void notifyAllObservers() {
        list.forEach(observer -> observer.update(this));

    }
}
