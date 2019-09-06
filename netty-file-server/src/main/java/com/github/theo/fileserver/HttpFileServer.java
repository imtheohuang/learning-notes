package com.github.theo.fileserver;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.codec.http.HttpObjectAggregator;
import io.netty.handler.codec.http.HttpRequestDecoder;
import io.netty.handler.codec.http.HttpRequestEncoder;
import io.netty.handler.codec.http.HttpResponseEncoder;
import io.netty.handler.stream.ChunkedWriteHandler;

/**
 * @author huangsuixin
 * @date 2019/09/06 14:38
 * @description //TODO
 */
public class HttpFileServer {
    private static final String DEFAULT_URL = "/netty-file-server/";
//    private static final String DEFAULT_URL = "/src/main/java/";

    public void run(final int port, final String url) throws InterruptedException {
        EventLoopGroup bossGroup = new NioEventLoopGroup();
        EventLoopGroup workGroup = new NioEventLoopGroup();

        try {
            ServerBootstrap bootstrap = new ServerBootstrap();

            bootstrap.group(bossGroup, workGroup)
                    .channel(NioServerSocketChannel.class)
                    .childHandler(new ChannelInitializer<SocketChannel>() {
                        @Override
                        protected void initChannel(SocketChannel channel) throws Exception {
                            // http 请求消息解码器
                            channel.pipeline().addLast("http-decoder", new HttpRequestDecoder());
                            // 将多个消息转换成单一的 FullHttpRequest 或 FullHttpResponse，因为HTTP解码器再每个HTTP消息中会产生多个消息对象
                            channel.pipeline().addLast("http-aggregator", new HttpObjectAggregator(65536));
                            // http 响应编码
                            channel.pipeline().addLast("http-encoder", new HttpResponseEncoder());
                            // 支持异步发送大码流，但不占用过多的内存，防止发送Java内存溢出
                            channel.pipeline().addLast("http-chunked", new ChunkedWriteHandler());
                            channel.pipeline().addLast("fileServerHandler", new HttpFileServerHandler(url));
                        }
                    });
            ChannelFuture sync = bootstrap.bind("127.0.0.1", port).sync();

            System.out.println("HTTP 文件目录服务器启动，网址是：127.0.0.1:" + port + url);

            sync.channel().closeFuture().sync();

        } finally {
            bossGroup.shutdownGracefully();
            workGroup.shutdownGracefully();
        }
    }

    public static void main(String[] args) throws InterruptedException {
        int port = 8080;
        new HttpFileServer().run(port, DEFAULT_URL);

    }
}
