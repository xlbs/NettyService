package com.xlbs.nettyservice.client;

import io.netty.channel.ChannelHandlerContext;

public interface IClientOpera {

	/**
	 * 向服务端发送信息
	 * <br>
	 * version：
	 * @param msg
	 */
	public void sendData(String msg);

	/**
	 * 接收服务端信息
	 * <br>
	 * version：
	 * @param str
	 */
	public void recvMsg(Object msg);

	/**
	 * 客户端关闭连接
	 */
	public void close();
	
	/**
	 * 设置连接通道
	 * <br>
	 * version：
	 * @param ctx
	 */
	public void setCtx(ChannelHandlerContext ctx);
	
}
