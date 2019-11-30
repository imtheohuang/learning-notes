package com.theo.pattern.chainOfResp;

/**
 * @author huangsuixin
 * @date 2019/11/30 11:55
 */
public abstract class Leader {
    private String name;

    protected Leader next;

    public Leader(String name) {
        this.name = name;
    }

    public void setNext(Leader next) {
        this.next = next;
    }

    /**
     * 处理请假
     * @param request {@link LeaveRequest}
     */
    public abstract void handleRequest(LeaveRequest request);
}
