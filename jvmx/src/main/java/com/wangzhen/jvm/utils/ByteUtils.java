package com.wangzhen.jvm.utils;


import java.io.IOException;
import java.io.UTFDataFormatException;

public class ByteUtils {


    public static String bytesToHexString(byte[] src) {
        return bytesToHexString(src, src.length);
    }



    /**
     * @param src 待转换的字节数组
     * @param len 只转换字节数组中的前len个字节
     * @return 转换成的字符串, 考虑到这是对底层数据的操作,
     */
    public static String bytesToHexString(byte[] src, int len) {
        StringBuilder stringBuilder = new StringBuilder("");
        if (src == null || len <= 0) {
            return null;
        }
        for (int i = 0; i < len; i++) {
            int v = src[i] & 0xFF;
            String hv = Integer.toHexString(v).toUpperCase();
            if (hv.length() < 2) {
                stringBuilder.append(0);
            }
            stringBuilder.append(hv);
        }
        return stringBuilder.toString();
    }

    /**
     * 将字节数组转换为 int 类型
     * @param bytes
     * @return
     */
    public static int bytesToInt(byte[] bytes)  {
        if(bytes.length == 1) {
            return Byte.toUnsignedInt(bytes[0]);
        }
        if(bytes.length ==2) {
            return bytesToU16(bytes);
        }
        if (bytes.length ==4 ) {
            return byteToInt32(bytes);
        }
        return -1;
    }

    //Java中并没有u16,所以这里使用int来表示;
    public static int bytesToU16(byte[] data) {
        assert data.length == 2;
        return (data[0] + 256) % 256 * 256 + (data[1] + 256) % 256;
    }

    //这个方法和下个方法区别在哪里,返回值不同啊...
    /*public static long bytesToU32(byte[] data) {
        assert data.length == 4;
        long res = 0;
        for (int i = 0; i < 4; i++) {
            res += res * 256 + (data[i] + 256) % 256;
        }
        return res;
    }*/

    public static int byteToInt32(byte[] data) {
        assert data.length == 4;
        int res = 0;
        for (int i = 0; i < data.length; i++) {
            res = res << 8 | (data[i] + 256) % 256;
        }
        return res;
    }

    public static long byteToLong64(byte[] data) {
        assert data.length == 8;
        long res = 0;
        for (int i = 0; i < data.length; i++) {
            res = res << 8 | (data[i] + 256) % 256;
        }
        return res;
    }


    public static float byteToFloat32(byte[] b) {
        int i = byteToInt32(b);
        return Float.intBitsToFloat(i);
    }


    public static double byteToDouble64(byte[] b) {
        long l = byteToLong64(b);
        return Double.longBitsToDouble(l);
    }

    //将MUTF8转为UTF8编码, 根据java.io.DataInputStream.readUTF（）方法改写。
    public  static  String decodeMUTF8(byte[] bytearr) throws IOException {
        int utflen = bytearr.length;
        char[] chararr = new char[utflen];
        int c, char2, char3;
        int count = 0;
        int chararr_count = 0;

        while (count < utflen) {
            c = (int) bytearr[count] & 0xff;
            if (c > 127) {
                break;
            }
            count++;
            chararr[chararr_count++] = (char) c;
        }

        while (count < utflen) {
            c = (int) bytearr[count] & 0xff;
            switch (c >> 4) {
                case 0:
                case 1:
                case 2:
                case 3:
                case 4:
                case 5:
                case 6:
                case 7:
                    /* 0xxxxxxx*/
                    count++;
                    chararr[chararr_count++] = (char) c;
                    break;
                case 12:
                case 13:
                    /* 110x xxxx   10xx xxxx*/
                    count += 2;
                    if (count > utflen) {
                        throw new UTFDataFormatException("malformed input: partial character at end");
                    }
                    char2 = (int) bytearr[count - 1];
                    if ((char2 & 0xC0) != 0x80) {
                        throw new UTFDataFormatException("malformed input around byte " + count);
                    }
                    chararr[chararr_count++] = (char) (((c & 0x1F) << 6) |
                            (char2 & 0x3F));
                    break;
                case 14:
                    /* 1110 xxxx  10xx xxxx  10xx xxxx */
                    count += 3;
                    if (count > utflen) {
                        throw new UTFDataFormatException(
                                "malformed input: partial character at end");
                    }
                    char2 = (int) bytearr[count - 2];
                    char3 = (int) bytearr[count - 1];
                    if (((char2 & 0xC0) != 0x80) || ((char3 & 0xC0) != 0x80)) {
                        throw new UTFDataFormatException(
                                "malformed input around byte " + (count - 1));
                    }
                    chararr[chararr_count++] = (char) (((c & 0x0F) << 12) |
                            ((char2 & 0x3F) << 6) |
                            ((char3 & 0x3F) << 0));
                    break;
                default:
                    /* 10xx xxxx,  1111 xxxx */
                    throw new UTFDataFormatException(
                            "malformed input around byte " + count);
            }
        }
        // The number of chars produced may be less than utflen
        return new String(chararr, 0, chararr_count);
    }
}
