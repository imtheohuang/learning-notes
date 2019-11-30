package com.theo.pattern.momonte;

/**
 * 源发器类
 * @author huangsuixin
 * @date 2019/11/30 17:49
 */
public class Emp {
    private String name;
    private int age;
    private double salary;

    /**
     * 进行备忘操作
     * @return {@link EmpMemento}备忘录对象
     */
    public EmpMemento memento() {
        return new EmpMemento(this);
    }

    /**
     * 进行数据恢复
     * @param empMemento {@link EmpMemento}
     */
    public void recovery(EmpMemento empMemento) {
        this.age = empMemento.getAge();
        this.salary = empMemento.getSalary();
        this.name = empMemento.getName();
    }

    public Emp() {
    }

    public Emp(String name, int age, double salary) {
        this.name = name;
        this.age = age;
        this.salary = salary;
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

    @Override
    public String toString() {
        return "Emp{" +
                "name='" + name + '\'' +
                ", age=" + age +
                ", salary=" + salary +
                '}';
    }
}
