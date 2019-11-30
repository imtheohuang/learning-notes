package com.theo.pattern.chainOfResp;

/**
 * @author huangsuixin
 * @date 2019/11/30 14:07
 */
public class Client {
    public static void main(String[] args) {
        Leader d1 = new Director("主任");
        Leader d2 = new Manager("经理");

        d1.setNext(d2);

        LeaveRequest request = new LeaveRequest();
        request.setDay(5);
        d1.handleRequest(request);
    }
}
