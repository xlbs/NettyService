package com.xlbs.nettyservice;

public class ClientTest1 {
	
	public static void main(String[] args) {
		IBusniss b = new SubBusnissImp();
		try {
			b.sendData("92");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
