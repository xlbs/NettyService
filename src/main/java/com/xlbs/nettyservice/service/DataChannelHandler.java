package com.xlbs.nettyservice.service;

import com.xlbs.nettyservice.constant.Global;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;

public class DataChannelHandler extends ChannelInboundHandlerAdapter{

	@Override
	public void channelActive(ChannelHandlerContext ctx) throws Exception {
		System.out.println(ctx.channel().remoteAddress().toString()+"---连接成功");
		super.channelActive(ctx);
	}

	@Override
	public void handlerRemoved(ChannelHandlerContext ctx) throws Exception {
		Global.ctxs.remove(ctx.hashCode());
		System.out.println(ctx.channel().remoteAddress().toString()+"---断开连接");
		super.handlerRemoved(ctx);
	}


	@Override
	//捕获异常前执行的方法
	public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause)
			throws Exception {
		super.exceptionCaught(ctx, cause);
	}
	
	@Override
	//channel失效处理,客户端下线或者强制退出等任何情况都触发这个方法
    public void channelInactive(ChannelHandlerContext ctx) throws Exception {
	    System.out.println(ctx.channel().remoteAddress().toString()+"---出现异常");
	    super.channelInactive(ctx);
    }


	@Override
	public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
		//接收到客户端的消息
		ByteBuf buf = (ByteBuf)msg;
		byte[] req = new byte[buf.readableBytes()];
		buf.readBytes(req);
		String str = new String(req,"UTF-8");
		System.out.println("来自客户端"+ctx.channel().remoteAddress().toString()+"的消息:"+str);
		Global.ctxs.put(ctx.hashCode(),ctx);

		String replyMsg = "客户端"+ctx.channel().remoteAddress().toString()+"，你好";
		ctx.writeAndFlush(Unpooled.copiedBuffer(replyMsg.getBytes()));

	}

	
}
