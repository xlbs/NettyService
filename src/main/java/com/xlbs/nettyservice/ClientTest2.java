package com.xlbs.nettyservice;

import com.xlbs.nettyservice.openinterface.IBusniss;

public class ClientTest2 {
	
	public static void main(String[] args) {
		IBusniss b = new SubBusniss2Imp();
		try {
			b.sendData("德玛西亚");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
