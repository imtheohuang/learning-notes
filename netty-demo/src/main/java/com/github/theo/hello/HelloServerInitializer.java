package com.github.theo.hello;

import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.socket.SocketChannel;
import io.netty.handler.codec.http.HttpServerCodec;

/**
 * @author huangsuixin
 * @date 2019/08/18 16:18
 * @description 初始化器，channel注册成功后，会执行里面的相应的初始化方法
 */
public class HelloServerInitializer extends ChannelInitializer<SocketChannel> {
    @Override
    protected void initChannel(SocketChannel channel) throws Exception {

        // 通过channel获取对应的管道
        ChannelPipeline pipeline = channel.pipeline();

        // 通过管道添加 handler
        // HttpServerCodec 是 netty 自己提供的助手类
        // 当请求到服务端，我们需要做解码，相应到客户端做编码
        pipeline.addLast("HttpServerCodec", new HttpServerCodec());

        // 添加自定义的助手类
        pipeline.addLast("customHandler", new CustomHandler());

    }
}
