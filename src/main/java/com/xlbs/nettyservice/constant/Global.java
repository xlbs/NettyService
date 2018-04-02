package com.xlbs.nettyservice.constant;


import io.netty.channel.ChannelHandlerContext;

import java.util.concurrent.ConcurrentHashMap;

public class Global {

    public static final int port = 8899;

    public static final  String ip = "127.0.0.1";

    public static ConcurrentHashMap<Integer, ChannelHandlerContext> ctxs ;



}
