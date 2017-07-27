package org.yinet.s1.net.tcp;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.logging.LogLevel;
import io.netty.handler.logging.LoggingHandler;
import org.yinet.s1.serializer.excel.ExcelUtils;

/**
 * 网络链接
 */
public class TcpUtils {
    public static void start(int port){
        //配置服务器端的nio
        EventLoopGroup bossGroup = new NioEventLoopGroup();
        EventLoopGroup workerGroup = new NioEventLoopGroup();
        try {
            ServerBootstrap b = new ServerBootstrap();
            b.group(bossGroup,workerGroup)
                    .channel(NioServerSocketChannel.class)
                    .option(ChannelOption.SO_BACKLOG,2048)
                    .handler(new LoggingHandler(LogLevel.INFO))
                    .childHandler(new ChannelInitializer<SocketChannel>() {
                        protected void initChannel(SocketChannel ch) throws Exception {
                            ch.pipeline().addLast(new RequestDecoder());
                            ch.pipeline().addLast(new ResponseEncoder());
                            ch.pipeline().addLast(new ServerHandler());
                        }
                    });
            //绑定端口
            ChannelFuture f = b.bind(port).sync();
            System.err.println("*****服务器启动*****");
            f.channel().closeFuture().sync();//等待服务端监听关闭
        }catch (Exception e){
            System.err.println("xxxx服务器启动失败xxxx");
            e.printStackTrace();
        }finally {
            //优雅退出线程
            bossGroup.shutdownGracefully();
            workerGroup.shutdownGracefully();
        }
    }
}
