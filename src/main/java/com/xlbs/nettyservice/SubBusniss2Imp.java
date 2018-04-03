package com.xlbs.nettyservice;

import com.xlbs.nettyservice.openinterface.BusnissImp;

public  class SubBusniss2Imp extends BusnissImp {

	@Override
	public void getData(String str) {
		System.out.println("客户端2收到服务端的消息:"+str);
	}



}
