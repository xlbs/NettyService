package com.xlbs.nettyservice.service;

import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.ipfilter.AbstractRemoteAddressFilter;

import java.net.SocketAddress;

@SuppressWarnings("rawtypes")
public class IpFilterHandler extends AbstractRemoteAddressFilter{

	@Override
	protected boolean accept(ChannelHandlerContext arg0, SocketAddress arg1)
			throws Exception {
		if(arg1.toString().contains("10.13.175.83")){
			return false;
		}
		return true;
	}

}
