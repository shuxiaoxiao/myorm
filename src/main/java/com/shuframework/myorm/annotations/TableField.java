package com.shuframework.myorm.annotations;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 字段标识
 * 
 * @author shu
 *
 */
@Retention(RetentionPolicy.RUNTIME)
// 只能用在字段上面
@Target(ElementType.FIELD)
public @interface TableField {

	/**
	 * 字段名
	 * 
	 * @return
	 */
	String value() default "";

	/**
	 * 是否为数据库表字段, 默认 true存在, false不存在
	 * 
	 * @return
	 */
	boolean exist() default true;

	String dataType() default "int";
	int length() default 20;

}
