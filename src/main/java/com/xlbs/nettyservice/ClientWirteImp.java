package com.xlbs.nettyservice;

import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerContext;

public class ClientWirteImp  implements IClientWrite{

//	private String recvStr = null;
	
	private ChannelHandlerContext ctx;
	
	private IBusniss ibus ;
	
	public ClientWirteImp(IBusniss ibus){
		this.ibus = ibus;
	}
	
	@Override
	public void sendData(String msg) {
		try{
			ctx.writeAndFlush(Unpooled.copiedBuffer(msg.getBytes()));//向服务端发送数据
//			Calendar cal = Calendar.getInstance();
//			while(Calendar.getInstance().getTimeInMillis() - cal.getTimeInMillis() < 5000){
//				if(recvStr != null){
//					return recvStr;
//				}
//				Thread.sleep(100);
//			}
//			return "请求数据超时";
		}catch(Exception  e){
			e.printStackTrace();
		}
//		finally{
//			recvStr = null;
//		}
	}

	@Override
	public void setCtx(ChannelHandlerContext ctx) {
		this.ctx = ctx;
	}

	@Override
	public void recvMsg(Object msg) {
//		recvStr =  (String) msg;
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
