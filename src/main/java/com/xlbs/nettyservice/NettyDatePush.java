package com.xlbs.nettyservice;

import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerContext;

import java.util.Map;

import com.clou.common.utils.ObjectUtils;
import com.clou.inteface.domain.util.Global;
import com.clou.inteface.domain.util.Message;

@SuppressWarnings("unchecked")
public class NettyDatePush {
	
	 private static NettyDatePush nettyDatePush = null;
	 
	 private NettyDatePush() {
		 
	 }
	 
	 public static NettyDatePush getInstance() {
		 if (nettyDatePush == null) {
			 nettyDatePush = new NettyDatePush();
		 }
		 return nettyDatePush;
	 }
	 
	 /**
	  * 所有用户
	  * 定时数据推送处理
	  */
	public void DatePush(){
		 for (Integer userId : Global.ctxMap.keySet()) {
			try {
				ChannelHandlerContext ctx = Global.ctxMap.get(userId);
				Map<String, String[]> dateMap = Global.dateMap.get(userId);
				if(!ObjectUtils.isNull(dateMap)){
					if(!ObjectUtils.isEmpty(dateMap.get("ZNDB_LIST"))){//智能电表--列表实时数据处理
						String[] mpIds = dateMap.get("ZNDB_LIST");
						Message message = DateDealWith.queryZNDBList(userId,mpIds);
						if(message.isResult()){
							Map<Object, Object> msgMap = (Map<Object, Object>) message.getMsg();
							String msg = msgMap.values().toString();
							ctx.writeAndFlush(Unpooled.copiedBuffer(msg.getBytes()));
						}
					}else if(!ObjectUtils.isEmpty(dateMap.get("ZNDB_REAL"))){//智能电表--实时数据处理
						String[] mpIds = dateMap.get("ZNDB_REAL");
						Message message = DateDealWith.queryZNDBReal(userId,mpIds);
						if(message.isResult()){
							Map<Object, Object> msgMap = (Map<Object, Object>) message.getMsg();
							String msg = msgMap.values().toString();
							ctx.writeAndFlush(Unpooled.copiedBuffer(msg.getBytes()));
						}
					}else if(!ObjectUtils.isEmpty(dateMap.get("CGSB_LIST"))){//传感设备--列表实时数据处理
						String[] mpIds = dateMap.get("CGSB_LIST");
						Message message = DateDealWith.queryCGSBList(userId,mpIds);
						if(message.isResult()){
							Map<Object, Object> msgMap = (Map<Object, Object>) message.getMsg();
							String msg = msgMap.values().toString();
							ctx.writeAndFlush(Unpooled.copiedBuffer(msg.getBytes()));
						}
					}else if(!ObjectUtils.isEmpty(dateMap.get("CGSB_REAL"))){//传感设备--实时数据处理
						String[] mpIds = dateMap.get("CGSB_REAL");
						Message message = DateDealWith.queryCGSBReal(userId,mpIds);
						if(message.isResult()){
							Map<Object, Object> msgMap = (Map<Object, Object>) message.getMsg();
							String msg = msgMap.values().toString();
							ctx.writeAndFlush(Unpooled.copiedBuffer(msg.getBytes()));
						}
					}
				}
				
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	 }
	
	

}
