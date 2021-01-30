package com.wangzhen.dubbox.remoting.transport.netty.client;

import io.netty.channel.Channel;
import lombok.extern.slf4j.Slf4j;

import java.net.InetSocketAddress;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Description:
 * Datetime:    2020/12/1   15:04
 * Author:   王震
 */
@Slf4j
public class ChannelProvider {
    private final Map<String, Channel> channelMap;

    public ChannelProvider() {
        this.channelMap = new ConcurrentHashMap<>();
    }

    public Channel get(InetSocketAddress inetSocketAddress){
        String key = inetSocketAddress.toString();
        if(channelMap.containsKey(key)){
            Channel channel = channelMap.get(key);
            if(channel!=null && channel.isActive()){
                return channel;
            }else {
                channelMap.remove(key);
            }
        }
        return null;
    }

    public void set(InetSocketAddress inetSocketAddress,Channel channel){
        String key = inetSocketAddress.toString();
        channelMap.put(key,channel);
    }

    public void remove(InetSocketAddress inetSocketAddress){
        String key = inetSocketAddress.toString();
        channelMap.remove(key);
        log.info("Channel map size :[{}]", channelMap.size());
    }
}