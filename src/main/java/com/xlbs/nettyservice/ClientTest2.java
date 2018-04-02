package com.xlbs.nettyservice;

public class ClientTest2 {
	
	public static void main(String[] args) {
		IBusniss b = new SubBusnissImp();
		try {
			b.sendData("212");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
