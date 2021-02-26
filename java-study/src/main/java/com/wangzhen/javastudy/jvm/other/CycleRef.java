package com.wangzhen.javastudy.jvm.other;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

/**
 * @author: wangzhen
 * @Date: 2021/2/22 10:42
 * @Desc: CycleRef
 */

@Slf4j
public class CycleRef {

    @Test
    public void test1(){
        RefA refA = new RefA();
    }
}


class RefA{
    RefB refB2=new RefB();
    static RefB ref=new RefB();
}

class RefB{
    static  RefA  refA=new RefA();
}
