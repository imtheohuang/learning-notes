package com.theo.pattern.command;

/**
 * @author huangsuixin
 * @date 2019/11/30 15:26
 */
public class Client {
    public static void main(String[] args) {
        Command c = new ConcreteCommand(new Receiver());

        Invoker invoker = new Invoker(c);

        invoker.call();
    }
}
