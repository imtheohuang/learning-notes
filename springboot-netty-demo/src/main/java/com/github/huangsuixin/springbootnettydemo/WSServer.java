package com.github.huangsuixin.springbootnettydemo;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import org.springframework.stereotype.Component;

/**
 * @author huangsuixin
 * @date 2019/08/19 20:22
 * @description websocket server
 */
@Component
public class WSServer {
    private static final int PORT = 8088;
    private EventLoopGroup mainGroup;
    private EventLoopGroup subGroup;
    private ServerBootstrap server;

    private ChannelFuture future;

    public WSServer() {
        mainGroup = new NioEventLoopGroup();
        subGroup = new NioEventLoopGroup();
        server = new ServerBootstrap();
        server.group(mainGroup, subGroup)
                .channel(NioServerSocketChannel.class)
                .childHandler(new WSServerInitialzer());
    }

    public void start() {
        future = server.bind(PORT);
        System.err.println("Netty websocket server is started！");
    }

    private static class SingletionWSServer {
        static final WSServer INSTANCE = new WSServer();
    }

    public static WSServer getInstance() {
        return SingletionWSServer.INSTANCE;
    }
}
