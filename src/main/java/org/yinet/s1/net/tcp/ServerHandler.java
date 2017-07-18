package org.yinet.s1.net.tcp;
import io.netty.channel.Channel;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import org.yinet.s1.net.tcp.model.Request;
import org.yinet.s1.net.tcp.process.MassegeHandOut;

public class ServerHandler extends SimpleChannelInboundHandler<Request>{
	/**消息分发器*/
	private MassegeHandOut massegeHandOut = new MassegeHandOut();
	private void process(Channel channel,Request request) throws CloneNotSupportedException {
		massegeHandOut.process(channel,request);
	}

	@Override
	public void channelActive(ChannelHandlerContext ctx) throws Exception {
		//客户端在
		System.out.println("用户进来"+ctx.channel().remoteAddress());
	}

	@Override
	public void channelInactive(ChannelHandlerContext ctx) throws Exception {
		ctx.channel().close();
	}

	@Override
	public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
	}

	@Override
	protected void channelRead0(ChannelHandlerContext ctx, Request request) throws Exception {
		//将消息发送到消息分发
		process(ctx.channel(), request);
	}

}
