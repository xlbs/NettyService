package com.xlbs.nettyservice.service;

import com.xlbs.nettyservice.constant.Global;
import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.Channel;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;

public class NettyServer {
	
	public static void main(String[] args) {
		NettyServer server = new NettyServer();
		server.startServer();
	}

	public void startServer(){
		EventLoopGroup bg = new NioEventLoopGroup();
		EventLoopGroup wg = new NioEventLoopGroup();
		try{
			ServerBootstrap b = new ServerBootstrap();
			b.group(bg, wg);
			b.channel(NioServerSocketChannel.class);
			b.option(ChannelOption.SO_BACKLOG, 1024);
			b.childHandler(new ChannelInitializer<Channel>() {
				@Override
				protected void initChannel(Channel ch) throws Exception {
					ChannelPipeline pip = ch.pipeline();
					pip.addLast(new IpFilterHandler());
					pip.addLast("handler",new DataChannelHandler());
				}
			});
			ChannelFuture f = b.bind(Global.port).sync();//绑定端口
			System.out.println("NettyServer Start Run");
			f.channel().closeFuture().sync();
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			bg.shutdownGracefully();
			wg.shutdownGracefully();
		}
	}

}
