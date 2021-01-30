package com.wangzhen.dubbox.annotation;



import com.wangzhen.dubbox.spring.CustomScannerRegistrar;
import org.springframework.context.annotation.Import;

import java.lang.annotation.*;

/**
 * scan custom annotations
 *
 * @author shuang.kou
 * @createTime 2020年08月10日 21:42:00
 */
@Target({ElementType.TYPE, ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
// 这个注解可以导入我们需要的类 到 spring 容器中
@Import(CustomScannerRegistrar.class)
@Documented
public @interface RpcScan {

    String[] basePackage();

}
