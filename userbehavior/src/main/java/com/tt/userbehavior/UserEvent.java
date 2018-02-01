package com.tt.userbehavior;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 标题: 用户事件注解
 * 描述：
 * 作者:滕涛
 * 创建时间：2018/1/31 10:30
 */

@Target({ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
public @interface UserEvent {

    String tag();
}
