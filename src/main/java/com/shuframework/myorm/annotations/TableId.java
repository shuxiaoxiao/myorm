package com.shuframework.myorm.annotations;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import com.shuframework.myorm.enums.IdTypeEnum;

/**
 * 主键标识
 * @author shu
 *
 */
@Retention(RetentionPolicy.RUNTIME)
//只能用在字段上面
@Target(ElementType.FIELD)
public @interface TableId {

	/**
	 * 主键名, 默认是id
	 * @return
	 */
	String value() default "id";
	
	/**
	 * 主键生成策略类型, 默认是自增
	 * @return
	 */
	IdTypeEnum type() default IdTypeEnum.AUTO;
	
}
