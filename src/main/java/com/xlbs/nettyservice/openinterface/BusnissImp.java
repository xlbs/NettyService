package com.xlbs.nettyservice.openinterface;

import com.xlbs.nettyservice.client.ClientImp;
import com.xlbs.nettyservice.client.NettyClient;

import java.util.Calendar;

public abstract class BusnissImp implements IBusniss {

	ClientImp write;
	
	NettyClient client;
	
	public abstract void getData(String str);
	
	@Override
	public void createChannel() {
		write = new ClientImp(this);
		client = new NettyClient();
		client.setIclient(write);
		client.createClient();
	}
	
	@Override
	public void sendData(String msg) throws Exception {
		if(write == null){
			createChannel();
		}
		Calendar cal = Calendar.getInstance();
		//5s内未建立连接视为无法建立连接
		while(Calendar.getInstance().getTimeInMillis()- cal.getTimeInMillis() < 5000){
			if(write.getCtx() != null){
				break;
			}
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
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
