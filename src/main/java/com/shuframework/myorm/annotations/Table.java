package com.shuframework.myorm.annotations;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 数据库表的注解
 * @author shu
 *
 */
@Retention(RetentionPolicy.RUNTIME)
//只能用在类上面
@Target(ElementType.TYPE)
public @interface Table {

	/**
	 * 表名
	 * @return
	 */
	String value() default "";
	
}
