package com.github.huangsuixin.springbootnettydemo;

import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.socket.SocketChannel;
import io.netty.handler.codec.http.HttpObjectAggregator;
import io.netty.handler.codec.http.HttpServerCodec;
import io.netty.handler.codec.http.websocketx.WebSocketServerProtocolHandler;
import io.netty.handler.stream.ChunkedWriteHandler;

/**
 * @author huangsuixin
 * @date 2019/08/19 10:00
 * @description websocket 初始化器
 */
public class WSServerInitialzer extends ChannelInitializer<SocketChannel> {
    @Override
    protected void initChannel(SocketChannel ch) throws Exception {
        ChannelPipeline pipeline = ch.pipeline();

        // websocket 基于http协议，所以需要http编解码协议
        pipeline.addLast(new HttpServerCodec());

        // 对写大数据流的支持
        pipeline.addLast(new ChunkedWriteHandler());

        // 对http message 进行聚合，聚合成 FullHttpRequest 或 FullHttpResponse
        // 几乎在netty中的编程，都会使用到此 handler
        pipeline.addLast(new HttpObjectAggregator(1024 * 64));

        // ================== 以上适用于支持http协议 ==================

        /* websocket 服务器处理的协议，用于指定客户端连接访问的路由 '/ws'
           本 handler 会帮你处理一些繁重复杂的事
           会帮你处理握手动作：handshaking (close, ping, pong) ping + pong  = 心跳
           对于 websocket 来讲，都是以 frames 进行传输的，不同的数据类型对应的 frames 也不同
         */
        pipeline.addLast(new WebSocketServerProtocolHandler("/ws"));

        // 自定义的 handler
        pipeline.addLast(new ChatHandler());
    }
}
