package com.theo.pattern.bridge;

/**
 * @author huangsuixin
 * @date 2019/10/10 16:45
 * @description //TODO
 */
public class Client {

    public static void main(String[] args) {
        // 销售联想的笔记本电脑

        Computer lenovoLaptop = new Laptop(new Lenovo());

        lenovoLaptop.sale();
    }
}
