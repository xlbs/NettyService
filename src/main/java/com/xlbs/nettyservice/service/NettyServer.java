package com.xlbs.nettyservice.service;

import com.xlbs.nettyservice.constant.Global;
import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;

public class NettyServer {
	
	public static void main(String[] args) {
		NettyServer server = new NettyServer();
		server.startServer();
	}

	public void startServer(){
		//创建处理连接的线程池
		EventLoopGroup bg = new NioEventLoopGroup();
		//创建处理所有事件的线程池
		EventLoopGroup wg = new NioEventLoopGroup();
		try{
			//设定辅助启动类
			ServerBootstrap b = new ServerBootstrap();
			b.group(bg, wg);
			//设置channel类型
			b.channel(NioServerSocketChannel.class);
			//用于设置bg相关参数
			b.option(ChannelOption.SO_BACKLOG, 1024);
			//用于设置wg相关参数
			b.childOption(ChannelOption.RCVBUF_ALLOCATOR, new AdaptiveRecvByteBufAllocator(64, 2048, 65536));
			//选择执行handler
			b.childHandler(new childChannelHandler());
			//此处可多次执行bind语句绑定多个端口
			ChannelFuture f = b.bind(Global.port).sync();//绑定端口
//			f = b.bind(8010).sync();
			System.out.println("NettyServer Start Run");
			//同步等待服务器关闭信息
			f.channel().closeFuture().sync();
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			bg.shutdownGracefully();
			wg.shutdownGracefully();
		}
	}

	public class childChannelHandler extends ChannelInitializer<SocketChannel>{

		@Override
		protected void initChannel(SocketChannel socketChannel) throws Exception {
			ChannelPipeline pipeline = socketChannel.pipeline();
			//一种继承ChannelInboundHandler，用于处理来自客户端的消息，比如对客户端的消息进行解码，读取等等。该类型在pipeline中的执行顺序与添加顺序一致。
			//一种继承ChannelOutboundHandler，用于处理即将发往客户端的消息，比如对该消息进行编辑，编码等等。该类型在pipeline中的执行顺序与添加顺序相反。
			//而且ChannelOutboundHandler的所有handler，放在ChannelInboundHandler下面是执行不到的。
			//例如：
			// 		pipeline.pipeline().addLast(new OutboundHandler1());　　//handler1
			//		pipeline.pipeline().addLast(new OutboundHandler2());　　//handler2
			//		pipeline.pipeline().addLast(new InboundHandler1());　　 //handler3
			//		pipeline.pipeline().addLast(new InboundHandler2());　　 //handler4
			//以上4个handler的实际执行顺序分别为handler3 -> handler4 -> handler2 ->handler1，如果在handler4下方加上OutboundHandler3，那么这个handler是不会被执行到的。
			pipeline.addLast(new IpFilterHandler());
			pipeline.addLast(new DataChannelHandler());

		}



	}




}
