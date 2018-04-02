package com.xlbs.nettyservice.client;

import com.xlbs.nettyservice.IClientWrite;
import com.xlbs.nettyservice.constant.Global;
import io.netty.bootstrap.Bootstrap;
import io.netty.channel.Channel;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioSocketChannel;


public class NettyClient {

    private IClientWrite iclient;
    
    EventLoopGroup group = new NioEventLoopGroup();

	public void createClient(){
    	group = new NioEventLoopGroup();
        try {
            Bootstrap b = new Bootstrap();
            b.group(group).channel(NioSocketChannel.class).handler(new ChannelInitializer<Channel>() {
				@Override
				protected void initChannel(Channel ch) throws Exception {
					ChannelPipeline pip = ch.pipeline();
					pip.addLast("handler",new ClientDataChannelHandler(iclient));
				}
			});
            // 连接服务端
            Channel ch = b.connect(Global.ip, Global.port).sync().channel();
        }catch(Exception e){
        	
        }
    }
    
	public IClientWrite getIclient() {
		return iclient;
	}
	
	public void setIclient(IClientWrite iclient) {
		this.iclient = iclient;
	}
	
	public EventLoopGroup getGroup() {
		return group;
	}
	
	public void setGroup(EventLoopGroup group) {
		this.group = group;
	}

}
