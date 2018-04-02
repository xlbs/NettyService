package com.xlbs.nettyservice.service;

import com.xlbs.nettyservice.constant.Global;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;

public class DataChannelHandler extends ChannelInboundHandlerAdapter{

	Integer userId = null;
	
	@Override
	public void channelActive(ChannelHandlerContext ctx) throws Exception {
		System.out.println(ctx.channel().localAddress().toString()+"连接成功");
		super.channelActive(ctx);
	}

	@Override
	public void handlerRemoved(ChannelHandlerContext ctx) throws Exception {
		Global.ctxs.remove(ctx.hashCode());
		System.out.println(ctx.channel().localAddress().toString()+"移除连接");
		super.handlerRemoved(ctx);
	}


	@Override
	public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause)
			throws Exception {
		System.out.println("-------111");
		super.exceptionCaught(ctx, cause);
	}
	
	@Override
    public void channelInactive(ChannelHandlerContext ctx) throws Exception {
	    //channel失效处理,客户端下线或者强制退出等任何情况都触发这个方法
		Global.ctxs.remove(ctx.hashCode());
	    System.out.println(ctx.channel().localAddress().toString()+"捕获异常");
	    super.channelInactive(ctx);
    }


	@Override
	public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
		//接收到客户端的消息
		ByteBuf buf = (ByteBuf)msg;
		System.out.println(msg.toString());
		byte[] req = new byte[buf.readableBytes()];
		buf.readBytes(req);
		String str = new String(req,"UTF-8");
		System.out.println("服务端接收到来自客户端的消息"+str);
		Global.ctxs.put(ctx.hashCode(),ctx);
//		userId = CommUtil.toInteger(userIdStr);

//		System.out.println(userId+"连接成功");
//		Global.ctxMap.put(userId, ctx);
		
	}

	
}
