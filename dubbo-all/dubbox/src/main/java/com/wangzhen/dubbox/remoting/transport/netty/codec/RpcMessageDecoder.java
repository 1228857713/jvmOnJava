package com.wangzhen.dubbox.remoting.transport.netty.codec;

import com.wangzhen.dubbox.common.enums.CompressTypeEnum;
import com.wangzhen.dubbox.common.enums.SerializationTypeEnum;
import com.wangzhen.dubbox.common.extension.ExtensionLoader;
import com.wangzhen.dubbox.compress.Compress;
import com.wangzhen.dubbox.remoting.constants.RpcConstants;
import com.wangzhen.dubbox.remoting.dto.RpcMessage;
import com.wangzhen.dubbox.remoting.dto.RpcRequest;
import com.wangzhen.dubbox.remoting.dto.RpcResponse;
import com.wangzhen.dubbox.serialize.Serializer;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.LengthFieldBasedFrameDecoder;
import lombok.extern.slf4j.Slf4j;

import java.util.Arrays;

/**
 * Description:
 * Datetime:    2020/12/1   14:53
 * Author:   王震
 */
@Slf4j
public class RpcMessageDecoder extends LengthFieldBasedFrameDecoder {
    public RpcMessageDecoder() {
        this(RpcConstants.MAX_FRAME_LENGTH, 5, 4, -9, 0);
    }

    public RpcMessageDecoder(int maxFrameLength, int lengthFieldOffset, int lengthFieldLength,
                             int lengthAdjustment, int initialBytesToStrip) {
        super(maxFrameLength, lengthFieldOffset, lengthFieldLength, lengthAdjustment, initialBytesToStrip);
    }

    @Override
    protected Object decode(ChannelHandlerContext ctx, ByteBuf in) throws Exception {
        Object decode = super.decode(ctx, in);
        if(decode instanceof ByteBuf){
            ByteBuf frame = (ByteBuf) decode;
            if(frame.readableBytes() >= RpcConstants.TOTAL_LENGTH){
                try{
                    return decodeFrame(frame);
                }catch (Exception e){
                    log.error(e.toString());
                }finally {
                    frame.release();
                }
            }
        }
        return decode;
    }

    private Object decodeFrame(ByteBuf in) {
        // note: must read ByteBuf in order
        // read the first 4 bit, which is the magic number, and compare
        int len = RpcConstants.MAGIC_NUMBER.length;
        byte[] tmp = new byte[len];
        in.readBytes(tmp);
        for (int i = 0; i < len; i++) {
            if (tmp[i] != RpcConstants.MAGIC_NUMBER[i]) {
                throw new IllegalArgumentException("Unknown magic code: " + Arrays.toString(tmp));
            }
        }
        // read the version and compare
        byte version = in.readByte();
        if (version != RpcConstants.VERSION) {
            throw new RuntimeException("version isn't compatible" + version);
        }
        int fullLength = in.readInt();
        // build RpcMessage object
        byte messageType = in.readByte();
        byte codecType = in.readByte();
        byte compressType = in.readByte();
        int requestId = in.readInt();
        RpcMessage rpcMessage = RpcMessage.builder()
                .codec(codecType)
                .requestId(requestId)
                .messageType(messageType).build();
        if (messageType == RpcConstants.HEARTBEAT_REQUEST_TYPE) {
            rpcMessage.setData(RpcConstants.PING);
        } else if (messageType == RpcConstants.HEARTBEAT_RESPONSE_TYPE) {
            rpcMessage.setData(RpcConstants.PONG);
        } else {
            int bodyLength = fullLength - RpcConstants.HEAD_LENGTH;
            if (bodyLength > 0) {
                byte[] bs = new byte[bodyLength];
                in.readBytes(bs);
                // decompress the bytes
                String compressName = CompressTypeEnum.getName(compressType);
                Compress compress = ExtensionLoader.getExtensionLoader(Compress.class)
                        .getExtension(compressName);
                bs = compress.decompress(bs);
                // deserialize the object
                String codecName = SerializationTypeEnum.getName(rpcMessage.getCodec());
                log.info("codec name: [{}] ", codecName);
                Serializer serializer = ExtensionLoader.getExtensionLoader(Serializer.class)
                        .getExtension(codecName);
                if (messageType == RpcConstants.REQUEST_TYPE) {
                    RpcRequest tmpValue = serializer.deserialize(bs, RpcRequest.class);
                    rpcMessage.setData(tmpValue);
                } else {
                    RpcResponse tmpValue = serializer.deserialize(bs, RpcResponse.class);
                    rpcMessage.setData(tmpValue);
                }
            }
        }
        return rpcMessage;

    }
}