package com.theo.pattern.momonte;

/**
 * 备忘录类
 *
 * @author huangsuixin
 * @date 2019/11/30 17:50
 */
public class EmpMemento {
    private String name;
    private int age;
    private double salary;

    public EmpMemento(Emp emp) {
        this.age = emp.getAge();
        this.salary = emp.getSalary();
        this.name = emp.getName();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public double getSalary() {
        return salary;
    }

    public void setSalary(double salary) {
        this.salary = salary;
    }
}
