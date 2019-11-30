package com.theo.pattern.command;

/**
 * @author huangsuixin
 * @date 2019/11/30 15:21
 */
public interface Command {
    /**
     * 执行
     */
    void execute();

}

class ConcreteCommand implements Command {
    private Receiver receiver;

    public ConcreteCommand(Receiver receiver) {
        this.receiver = receiver;
    }

    @Override
    public void execute() {
        receiver.action();
    }
}
