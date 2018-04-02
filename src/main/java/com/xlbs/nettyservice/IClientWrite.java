package com.xlbs.nettyservice;

import io.netty.channel.ChannelHandlerContext;

public interface IClientWrite {

	/**
	 * 向服务端发送信息
	 * <br>
	 * version：
	 * @param msg
	 */
	public void sendData(String msg);
	
	/**
	 * 设置连接通道
	 * <br>
	 * version：
	 * @param ctx
	 */
	public void setCtx(ChannelHandlerContext ctx);
	
	/**
	 * 接收服务端信息
	 * <br>
	 * version：
	 * @param str
	 */
	public void recvMsg(Object msg);
	
	
	
	public void close();
	
}
