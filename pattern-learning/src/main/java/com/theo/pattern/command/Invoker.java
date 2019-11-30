package com.theo.pattern.command;

/**
 * 调用者/发起者
 * @author huangsuixin
 * @date 2019/11/30 15:23
 */
public class Invoker {
    /**
     * 也可以通过容器 {@link java.util.List}<{@link Command}> 容纳很多命令对象，进行批处理，数据库底层的事务管理就是类似的结构。
     */
    private Command command;

    public Invoker(Command command) {
        this.command = command;
    }

    /**
     * 业务方法，用于调用命令方法
     */
    public void call() {
        command.execute();
    }
}
