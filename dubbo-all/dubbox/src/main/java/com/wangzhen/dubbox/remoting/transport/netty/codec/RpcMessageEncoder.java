package com.wangzhen.dubbox.remoting.transport.netty.codec;

import com.wangzhen.dubbox.common.enums.CompressTypeEnum;
import com.wangzhen.dubbox.common.enums.SerializationTypeEnum;
import com.wangzhen.dubbox.common.extension.ExtensionLoader;
import com.wangzhen.dubbox.compress.Compress;
import com.wangzhen.dubbox.remoting.constants.RpcConstants;
import com.wangzhen.dubbox.remoting.dto.RpcMessage;
import com.wangzhen.dubbox.serialize.Serializer;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToByteEncoder;
import io.protostuff.Rpc;
import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * Description: 自定义加密器
 * Datetime:    2020/12/1   14:12
 <p>
 * custom protocol decoder
 * <p>
 * <pre>
 *   0     1     2     3     4        5     6     7     8         9          10      11     12  13  14   15 16
 *   +-----+-----+-----+-----+--------+----+----+----+------+-----------+-------+----- --+-----+-----+-------+
 *   |   magic   code        |version | full length         | messageType| codec|compress|    RequestId       |
 *   +-----------------------+--------+---------------------+-----------+-----------+-----------+------------+
 *   |                                                                                                       |
 *   |                                         body                                                          |
 *   |                                                                                                       |
 *   |                                        ... ...                                                        |
 *   +-------------------------------------------------------------------------------------------------------+
 * 4B  magic code（魔法数）   1B version（版本）   4B full length（消息长度）    1B messageType（消息类型）
 * 1B compress（压缩类型） 1B codec（序列化类型）    4B  requestId（请求的Id）
 * body（object类型数据）
 * </pre>
 * Author:   王震
 */
@Slf4j
public class RpcMessageEncoder  extends MessageToByteEncoder<RpcMessage> {

    // 原子类型的 整数
    public static final AtomicInteger atomicInteger = new AtomicInteger(0);


    @Override
    protected void encode(ChannelHandlerContext channelHandlerContext, RpcMessage rpcMessage, ByteBuf byteBuf) throws Exception {
        try{
            //增加魔数
            byteBuf.writeBytes(RpcConstants.MAGIC_NUMBER);
            // 增肌版本号
            byteBuf.writeByte(RpcConstants.VERSION);
            byteBuf.writerIndex(byteBuf.writerIndex()+4);
            byte messageType = rpcMessage.getMessageType();
            byteBuf.writeByte(messageType);
            byteBuf.writeByte(rpcMessage.getCodec());
            byteBuf.writeInt(atomicInteger.getAndIncrement());
            byte [] bodyBytes =null;
            int fullLength = RpcConstants.HEAD_LENGTH;
            if(messageType!=RpcConstants.HEARTBEAT_REQUEST_TYPE && messageType!=RpcConstants.HEARTBEAT_RESPONSE_TYPE){
                String codecName = SerializationTypeEnum.getName(rpcMessage.getCodec());
                log.info("codec name：[{}]",codecName);
                Serializer serializer = ExtensionLoader.getExtensionLoader(Serializer.class).getExtension(codecName);
                bodyBytes = serializer.serialize(rpcMessage.getData());
                String compressName = CompressTypeEnum.getName(rpcMessage.getCompress());
                Compress compress = ExtensionLoader.getExtensionLoader(Compress.class).getExtension(compressName);
                bodyBytes = compress.compress(bodyBytes);
                fullLength += bodyBytes.length;


            }
            if(bodyBytes!=null){
                byteBuf.writeBytes(bodyBytes);
            }
            int writerIndex = byteBuf.writerIndex();
            byteBuf.writerIndex(writerIndex-fullLength+RpcConstants.MAGIC_NUMBER.length+1);
            byteBuf.writeInt(fullLength);
            byteBuf.writerIndex(writerIndex);


        }catch (Exception e){
            log.error("Encode request error!",e);
        }
    }
}