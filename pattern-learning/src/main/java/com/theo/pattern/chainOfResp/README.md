## 责任链模式
模拟请假流程
- 3天以内 主任可批准
- 100 天以内 经理可以批准

`Leader` : 领导抽象类
- 属性 
  - `next` - `Leader`, 下一个处理者
  - 其他属性
- 方法
  - `void handleRequest(LeaveRequest request)` 抽象方法，处理请求
- 子类
  - `Director` 主任
  - `Manager` 经理
  
`LeaveRequest`: 封装请假的请求信息
