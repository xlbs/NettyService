package com.xlbs.nettyservice.client;

import com.xlbs.nettyservice.openinterface.IBusniss;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerContext;

public class ClientOperaImp implements IClientOpera {

	private ChannelHandlerContext ctx;
	
	private IBusniss ibus ;
	
	public ClientOperaImp(IBusniss ibus){
		this.ibus = ibus;
	}
	
	@Override
	public void sendData(String msg) {
		try{
			ctx.writeAndFlush(Unpooled.copiedBuffer(msg.getBytes()));//向服务端发送数据
		}catch(Exception  e){
			e.printStackTrace();
		}
	}

	@Override
	public void setCtx(ChannelHandlerContext ctx) {
		this.ctx = ctx;
	}

	@Override
	public void recvMsg(Object msg) {
		ibus.recvMsg((String) msg);
	}

	@Override
	public void close() {
		ctx.close();
	}

	public ChannelHandlerContext getCtx() {
		return ctx;
	}

}
