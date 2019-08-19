package com.github.theo.websocket;

import io.netty.channel.Channel;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.channel.group.ChannelGroup;
import io.netty.channel.group.DefaultChannelGroup;
import io.netty.handler.codec.http.websocketx.TextWebSocketFrame;
import io.netty.util.concurrent.GlobalEventExecutor;

import java.time.LocalDateTime;

/**
 * @author huangsuixin
 * @date 2019/08/19 10:12
 * @description 自定义的handler：处理消息的 handler
 * TextWebSocketFrame: 在netty中，用于为 websocket 专门处理文本的对象， frame 是消息的载体
 */
public class ChatHandler extends SimpleChannelInboundHandler<TextWebSocketFrame> {

    /**
     * 用于记录和管理所有客户端 channel
     */
    private static ChannelGroup clients = new DefaultChannelGroup(GlobalEventExecutor.INSTANCE);

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, TextWebSocketFrame msg) throws Exception {

        // 获取客户端传输过来的消息
        String content = msg.text();

        System.out.println("接收到的数据：" + content);

//        for (Channel channel : clients) {
//            channel.writeAndFlush(
//                    new TextWebSocketFrame("[服务器接收到消息：" + LocalDateTime.now() + "], 消息为：" + content));
//        }

        // 上面的for循环和下面的api是一致的效果
         clients.writeAndFlush(new TextWebSocketFrame("[服务器接收到消息：" + LocalDateTime.now() + "], 消息为：" + content));
    }

    /**
     * 当客户端连接服务端后（打开连接）
     * 获取客户端的 channel ，并且放到 ChannelGroup 中去进行管理
     * @param ctx
     * @throws Exception
     */
    @Override
    public void handlerAdded(ChannelHandlerContext ctx) throws Exception {
        clients.add(ctx.channel());

        super.handlerAdded(ctx);
    }

    @Override
    public void handlerRemoved(ChannelHandlerContext ctx) throws Exception {
        // 当触发 handlerRemoved， ChannelGroup 会自动移除对应客户端的 channel
        System.out.println("客户端断开，channel 对应的长id为：" + ctx.channel().id().asLongText());
        System.out.println("客户端断开，channel 对应的短id为：" + ctx.channel().id().asShortText());
    }
}
