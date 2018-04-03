package com.xlbs.nettyservice;

import com.xlbs.nettyservice.openinterface.BusnissImp;

public  class SubBusniss1Imp extends BusnissImp {

	@Override
	public void getData(String str) {
		System.out.println("客户端1收到服务端的消息:"+str);
	}



}
