# websocket demo


## Websocket api
``` javascript 1.5
var socket = new WebSocket("ws://[ip]:[port]");
```
- 生命周期：onopen(), onmessage(), onerror(), onclose()

- 主动方法：Socket.send(), Socket.close()