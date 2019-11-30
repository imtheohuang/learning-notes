package com.theo.pattern.momonte;

/**
 * @author huangsuixin
 * @date 2019/11/30 17:55
 */
public class Client {
    public static void main(String[] args) {
        CareTaker careTaker = new CareTaker();

        Emp emp = new Emp("silence", 20, 5000.00);
        System.out.println(emp.toString());

        careTaker.setMemento(emp.memento());

        emp.setName("hahahah");
        emp.setSalary(65200.00);

        System.out.println(emp.toString());

        // 恢复到备忘录保存的状态
        emp.recovery(careTaker.getMemento());
        System.out.println(emp.toString());

    }
}
