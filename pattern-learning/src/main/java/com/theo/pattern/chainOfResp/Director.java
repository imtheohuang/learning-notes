package com.theo.pattern.chainOfResp;

/**
 * 主任
 * @author huangsuixin
 * @date 2019/11/30 11:57
 */
public class Director extends Leader{

    public Director(String name) {
        super(name);
    }

    @Override
    public void handleRequest(LeaveRequest request) {
        if (request.getDay() <= 3) {
            System.out.println("请假");
        } else {
            if (next != null) {
                next.handleRequest(request);
            } else {

            }
        }

    }
}
