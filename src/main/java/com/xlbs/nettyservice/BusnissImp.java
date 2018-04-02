package com.xlbs.nettyservice;

import com.xlbs.nettyservice.client.NettyClient;

public abstract class BusnissImp implements IBusniss{

	ClientWirteImp write;
	
	NettyClient client;
	
	public abstract String getData(String str);
	
	@Override
	public void createChannel() {
		write = new ClientWirteImp(this);
		client = new NettyClient();
		client.setIclient(write);
		client.createClient();
	}
	
	@Override
	public void sendData(String msg) throws Exception {
		if(write == null){
			createChannel();
		}
//		Calendar cal = Calendar.getInstance();
//		while(Calendar.getInstance().getTimeInMillisrecvMsg() - cal.getTimeInMillis() < 5000){
//			if(write.getCtx() != null){
//				break;
//			}
//			try {
//				Thread.sleep(100);
//			} catch (InterruptedException e) {
//				e.printStackTrace();
//			}
//		}
		if(write.getCtx() != null){
			write.sendData(msg);
		}else{
			throw new Exception("连接失败");
		}
		
	}

	@Override
	public void closeChannel() {
		if(write != null){
			write.close();
			client.getGroup().shutdownGracefully();
			write = null;
			client = null;
		}
	}

	@Override
	public void recvMsg(String msg) {
		getData(msg);
	}
	
}
