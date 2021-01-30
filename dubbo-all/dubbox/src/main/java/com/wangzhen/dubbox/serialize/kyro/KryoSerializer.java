package com.wangzhen.dubbox.serialize.kyro;

import com.esotericsoftware.kryo.Kryo;
import com.esotericsoftware.kryo.io.Input;
import com.esotericsoftware.kryo.io.Output;
import com.wangzhen.dubbox.common.exception.SerializeException;
import com.wangzhen.dubbox.remoting.dto.RpcRequest;
import com.wangzhen.dubbox.remoting.dto.RpcResponse;
import com.wangzhen.dubbox.serialize.Serializer;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;

/**
 * Description:
 * Datetime:    2020/11/25   15:56
 * Author:   王震
 */
public class KryoSerializer implements Serializer {
    private  final  ThreadLocal<Kryo> threadLocal = ThreadLocal.withInitial(() -> {
        Kryo kryo = new Kryo();
        kryo.register(RpcRequest.class);
        kryo.register(RpcResponse.class);
        return kryo;
    });


    // 序列化
    @Override
    public byte[] serialize(Object obj) {
        try {
            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            Output output = new Output(byteArrayOutputStream);
            Kryo kryo = threadLocal.get();
            kryo.writeObject(output,obj);
            threadLocal.remove();
            return output.toBytes();
        }catch (Exception e){
            throw new SerializeException("Serialization failed");
        }

    }

    // 反序列化
    @Override
    public <T> T deserialize(byte[] bytes, Class<T> clazz) {
        try{
            ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(bytes);
            Input input = new Input(byteArrayInputStream);
            Kryo kryo = threadLocal.get();
            T t = kryo.readObject(input, clazz);
            threadLocal.remove();
            return t;
        }catch (Exception e){
            throw new SerializeException("deserialize failed");
        }
    }
}