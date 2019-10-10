package com.theo.pattern.adapter;

/**
 * @author huangsuixin
 * @date 2019/10/10 10:29
 * @description //TODO
 */
public class Adapter implements Target {
    private Adaptee adaptee;

    public Adapter() {
        adaptee = new Adaptee();
    }

    @Override
    public void doRequest() {
        adaptee.doRequest();
    }
}
