package com.github.theo.hello;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;

/**
 * @author huangsuixin
 * @date 2019/08/18 16:00
 * @description 实现客户端发送一个请求，服务端返回 “hello netty”
 */
public class HelloServer {
    public static void main(String[] args) throws InterruptedException {

        // 定义一对线程组
        // 主线程组，用于接收客户端的连接，但不做任何处理
        EventLoopGroup bossGroup = new NioEventLoopGroup();

        // 从线程组，主线程组会把任务丢给从线程组
        EventLoopGroup workerGroup = new NioEventLoopGroup();

        try {
            // netty 服务器的创建，ServerBootstrap 是一个启动类
            ServerBootstrap serverBootstrap = new ServerBootstrap();
            // 设置主从线程组
            serverBootstrap.group(bossGroup, workerGroup)
                    // 设置nio的双向通道
                    .channel(NioServerSocketChannel.class)
                    // 子处理器，用于处理 worker group
                    .childHandler(new HelloServerInitializer());

            // 启动 server，并设置监听端口为8088，启动方式为同步
            ChannelFuture channelFuture = serverBootstrap.bind(8088).sync();

            // 监听关闭的channel，并设置为同步
            channelFuture.channel().closeFuture().sync();
        } finally {
            bossGroup.shutdownGracefully();
            workerGroup.shutdownGracefully();
        }
    }
}
