package com.theo.pattern.observer;

/**
 * @author huangsuixin
 * @date 2019/11/30 16:53
 */
public class ObserverA implements Observer {
    /**
     * 需要跟目标对象中的值保持一致
     */
    private int state;

    @Override
    public void update(Subject subject) {
        state = ((ConcreteSubject) subject).getState();
    }

    public int getState() {
        return state;
    }

    public void setState(int state) {
        this.state = state;
    }
}
