package com.theo.pattern.observer;

/**
 * @author huangsuixin
 * @date 2019/11/30 16:51
 */
public class ConcreteSubject extends Subject {
    private int state;

    public int getState() {
        return state;
    }

    public void setState(int state) {
        this.state = state;
        // 目标对象的
        this.notifyAllObservers();
    }
}
