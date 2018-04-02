package com.xlbs.nettyservice;

public interface IBusniss {

	/**
	 * 创建连接
	 * <br>
	 * version：
	 */
	public void createChannel();
	
	/**
	 * 客户端向服务端发送数据
	 * <br>
	 * version：
	 * @param msg
	 * @throws Exception
	 */
	public void sendData(String msg) throws Exception;
	
	/**
	 * 接收服务端信息
	 * <br>
	 * version：
	 * @param str
	 */
	public void recvMsg(String str);
	
	/**
	 * 关闭连接
	 * <br>
	 * version：
	 */
	public void closeChannel();
	
	
}
