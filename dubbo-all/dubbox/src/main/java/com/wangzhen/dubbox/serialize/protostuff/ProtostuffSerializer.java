package com.wangzhen.dubbox.serialize.protostuff;

import com.wangzhen.dubbox.serialize.Serializer;
import io.protostuff.LinkedBuffer;
import io.protostuff.ProtostuffIOUtil;
import io.protostuff.Schema;
import io.protostuff.runtime.RuntimeSchema;

/**
 * Description:
 * Datetime:    2020/11/26   10:52
 * Author:   王震
 */
public class ProtostuffSerializer implements Serializer {

    private static final LinkedBuffer BUFFER = LinkedBuffer.allocate(LinkedBuffer.DEFAULT_BUFFER_SIZE);

    @Override
    public byte[] serialize(Object obj) {
        Class<?> clazz = obj.getClass();
        Schema schema = RuntimeSchema.getSchema(clazz);
        byte[] bytes;
        try {
            bytes = ProtostuffIOUtil.toByteArray(obj, schema, BUFFER);
        } finally {
            BUFFER.clear();
        }
        return bytes;
    }

    @Override
    public <T> T deserialize(byte[] bytes, Class<T> clazz) {
        Schema<T> schema = RuntimeSchema.getSchema(clazz);
        T obj = schema.newMessage();
        ProtostuffIOUtil.mergeFrom(bytes, obj, schema);
        return obj;
    }
}