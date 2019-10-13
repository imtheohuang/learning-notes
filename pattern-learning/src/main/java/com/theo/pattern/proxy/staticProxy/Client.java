package com.theo.pattern.proxy.staticProxy;

/**
 * @author huangsuixin
 * @date 2019/10/10 15:45
 * @description //TODO
 */
public class Client {

    public static void main(String[] args) {
        Star star = new RealStar();
        Star proxy = new ProxyStar(star);

        proxy.confer();
        proxy.bookTicket();
        proxy.collectMoney();
        proxy.sing();
    }
}
