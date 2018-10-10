package com.shuframework.myorm.util;

import java.util.Collection;
import java.util.Map;


/**
 * 系统通用的判断<br>
 * 1.大部分类型 判断是否为null或为空
 * 2.手动生成的id，主要用于编码或主键
 *
 * @author shuheng
 *
 */
public class SystemUtil {

	/**
	 * 判断str是否为null或空串（去空格了）,是返回 true
	 * 
	 * @param str
	 */
	public static boolean isEmpty(String str){
		//当str = null时为true，后面的不执行了，所以str = null时不会执行trim()，所以就没问题
		return str == null || str.trim().length() == 0;
	}
	/**
	 * 判断str是否不为null或非空串（去空格了），是返回 true
	 * 
	 * @param str
	 */
	public static boolean isNotEmpty(String str){
		return !isEmpty(str);
	}
	
	/**
	 * 判断list是否为null或空,是返回 true
	 * 
	 * @param list
	 */
	public static boolean isEmpty(Collection<?> list){
		return list == null || list.size() == 0;
	}
	/**
	 * 判断list是否不为null或非空串（去空格了），是返回 true
	 * 
	 * @param list
	 */
	public static boolean isNotEmpty(Collection<?> list){
		return !isEmpty(list);
	}
	
	/**
	 * 判断数组对象 是否为null或空,是返回 true
	 * 
	 * @param array
	 */
	public static boolean isEmpty(Object[] array){
		return array == null || array.length == 0;
	}
	/**
	 * 判断数组对象 是否不为null或非空串（去空格了），是返回 true
	 * 
	 * @param array
	 */
	public static boolean isNotEmpty(Object[] array){
		return !isEmpty(array);
	}
	
	/**
	 * 判断map对象 是否为null或空,是返回 true
	 * 
	 * @param map
	 */
    public static boolean isEmpty(Map map) {
        return (map == null || map.isEmpty());
    }
    /**
     * 判断map对象 是否不为null或非空,是返回 true
     * 
     * @param map
     */
    public static boolean isNotEmpty(Map map) {
    	return !isEmpty(map);
    }

	/**
	 * 判断Integer对象 是否为null或0,是返回 true
	 *
	 * @param intNum
	 */
	public static boolean isEmpty(Integer intNum) {
		return (intNum == null || intNum == 0);
	}
	/**
	 * 判断Integer对象 是否不为null或非0,是返回 true
	 *
	 * @param intNum
	 */
	public static boolean isNotEmpty(Integer intNum) {
		return !isEmpty(intNum);
	}

	/**
	 * 判断Long对象 是否为null或0,是返回 true
	 *
	 * @param longNum
	 */
	public static boolean isEmpty(Long longNum) {
		return (longNum == null || longNum == 0);
	}
	/**
	 * 判断Long对象 是否不为null或非0,是返回 true
	 *
	 * @param longNum
	 */
	public static boolean isNotEmpty(Long longNum) {
		return !isEmpty(longNum);
	}

//	/**
//	 * 检查字符串长度，true表示未通过（不满足），false表示通过
//	 * 如果required = true 则会判断不为空， 长度是否满足
//	 *
//	 * @param str
//	 * @param required	是否必填
//	 * @param limitLength 长度
//	 * @return
//	 */
//	public static boolean checkStrLength(String str, boolean required, int limitLength){
//		boolean flag = true;
//		if(SystemUtil.isNotEmpty(str)){
//			if (str.trim().length() <= limitLength){
//				flag = false;
//			}
//		}else{
//			if (!required){//非必填直接过
//				flag = false;
//			}
//		}
//		return flag;
//	}

}
