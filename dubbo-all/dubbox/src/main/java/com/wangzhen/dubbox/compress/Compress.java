package com.wangzhen.dubbox.compress;


import com.wangzhen.dubbox.common.extension.SPI;

/**
 * @author wangtao .
 * @createTime on 2020/10/3
 */

@SPI
public interface Compress {

    // 压缩
    byte[] compress(byte[] bytes);

    // 解压
    byte[] decompress(byte[] bytes);
}
