package com.theo.pattern.chainOfResp;

/**
 * 经理
 *
 * @author huangsuixin
 * @date 2019/11/30 14:05
 */
public class Manager extends Leader {
    public Manager(String name) {
        super(name);
    }

    @Override
    public void handleRequest(LeaveRequest request) {

        if (request.getDay() < 100) {
            // 处理请假
            System.out.println("经理：批准");
        } else {
            if (next != null) {
                next.handleRequest(request);
            }
        }
    }
}
