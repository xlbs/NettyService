package com.xlbs.nettyservice;

import com.xlbs.nettyservice.openinterface.IBusniss;

public class ClientTest1 {
	
	public static void main(String[] args) {
		IBusniss b = new SubBusniss1Imp();
		try {
			b.sendData("萨瓦迪卡");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
