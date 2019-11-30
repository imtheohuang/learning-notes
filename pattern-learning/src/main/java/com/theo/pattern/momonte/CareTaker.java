package com.theo.pattern.momonte;

/**
 * 负责人类
 * 负责管理备忘录
 *
 * @author huangsuixin
 * @date 2019/11/30 17:54
 */
public class CareTaker {
    private EmpMemento memento;

    public EmpMemento getMemento() {
        return memento;
    }

    public void setMemento(EmpMemento memento) {
        this.memento = memento;
    }
}
