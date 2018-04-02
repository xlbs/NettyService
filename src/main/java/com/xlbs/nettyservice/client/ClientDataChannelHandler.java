package com.xlbs.nettyservice.client;

import com.xlbs.nettyservice.IClientWrite;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;

public class ClientDataChannelHandler extends ChannelInboundHandlerAdapter {

	private IClientWrite iclient;
	
	public ClientDataChannelHandler(IClientWrite iclient){
		this.iclient = iclient;
	}

	@Override
	public void channelActive(ChannelHandlerContext ctx) throws Exception {
		System.out.println("客户端连接成功");
		iclient.setCtx(ctx);
		super.channelActive(ctx);
	}

	@Override
	public void handlerRemoved(ChannelHandlerContext ctx) throws Exception {
		System.out.println("客户端关闭连接");
		ctx.close();
		super.handlerRemoved(ctx);
	}


	@Override
	public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause)
			throws Exception {
		super.exceptionCaught(ctx, cause);
	}
	
	@Override
    public void channelInactive(ChannelHandlerContext ctx) throws Exception {
	    //channel失效处理,客户端下线或者强制退出等任何情况都触发这个方法
	    super.channelInactive(ctx);
    }


	@Override
	public void channelRead(ChannelHandlerContext ctx, Object msg)
			throws Exception {
		ByteBuf buf = (ByteBuf)msg;
		byte[] req = new byte[buf.readableBytes()];
		buf.readBytes(req);
		String body = new String(req,"UTF-8");
		iclient.recvMsg(body);
	}

	public IClientWrite getIclient() {
		return iclient;
	}

	public void setIclient(IClientWrite iclient) {
		this.iclient = iclient;
	}

}