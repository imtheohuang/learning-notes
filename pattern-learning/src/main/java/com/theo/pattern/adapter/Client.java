package com.theo.pattern.adapter;

/**
 * @author huangsuixin
 * @date 2019/10/10 10:28
 * @description //TODO
 */
public class Client {

    public void test(Target t) {
        t.doRequest();
    }

    public static void main(String[] args) {
        Client client = new Client();
        Target target = new Adapter();

        client.test(target);
    }
}
