package com.shuframework.myorm.util;

import com.shuframework.myorm.enums.SeparatorTypeEnum;

/**
 * String 的工具类
 * 
 * @author shu
 */
public class StringUtil {

	/**
	 * 判断是否为null或空串（去空格了），是返回 true
	 * @param str
	 * @return
	 */
	private static boolean isEmpty(String str){
//		return str == null || str.trim().length() == 0;
		return SystemUtil.isEmpty(str);
	}


	/**
	 * 转换空串，如str是空串或null 则转成num，不为空就是本身
	 * @param str
	 * @param num
	 * @return
	 */
	public static String parseEmpty(String str, String num){
		if(isEmpty(str)){
			return num;
		}
		return str;
	}
	
	/**
	 * 对数字补零，
	 * <p>数字1，补零到3位，则返回001</p>
	 * <p>数字-1，补零到3位，则返回-01</p>
	 * @param num
	 * @param length
	 * @return
	 */
	public static String fillLeftZero(int num, int length) {
		return String.format("%0" + length + "d", num);
	}

	/**
	 * 去掉所有空格
	 * @param str
	 * @return
	 */
	public static String trimAll(String str){
		return str.replace(" ", "");
	}

	/**
	 * 多个字符串拼接, 底层是StringBuilder的append实现
	 * @param str
	 * @return
	 */
	public static String append(Object... str){
		StringBuilder sb = new StringBuilder();
		for (Object s : str) {
			sb.append(s);
		}
		return sb.toString();
	}

	/**
	 * 多个字符串拼接, 底层是StringBuilder的append实现
	 * @param str
	 * @return
	 */
	public static String appendHasTrim(String... str){
		StringBuilder sb = new StringBuilder();
		for (String s : str) {
			sb.append(trimAll(s));
		}
		return sb.toString();
	}


	public static String toPropertyName(String columnName) {
		if (isEmpty(columnName)) {
			return columnName;
		}
		String separator = SeparatorTypeEnum.UNDERLINE.getValue();
		//字段里面包含了分隔符才转换，否则返回原字段
		if (!columnName.contains(separator)){
			return columnName;
		}
		String lowerStr = columnName.toLowerCase();
		StringBuilder sb = new StringBuilder(columnName.length());
		String[] splitStrArr = lowerStr.split(separator);
		//切分的数组是空 返回当前(由于split方法不会返回空，所以这个判断可以省略)
//		if (SystemUtil.isEmpty(splitStrArr)){
////		if (splitStrArr == null || splitStrArr.length == 0){
//			return columnName;
//		}
		sb.append(splitStrArr[0]);
		for (int i = 1,max = splitStrArr.length; i < max; i++) {
			String splitStr = splitStrArr[i];
			sb.append(Character.toUpperCase(splitStr.charAt(0)));
			sb.append(splitStr.substring(1));
		}
		return sb.toString();
	}

	/**
	 * 首字母大写,其它字母小写
	 * <p> capitalizeFully("abc_Abc", "_")	结果是AbcAbc </p>
	 * <p> capitalizeFully("abc_Abc", ",")	结果是Abc_abc </p>
	 * @param str
	 * @return
	 */
	public static String capitalizeFully(String str, final String separator) {
		if (isEmpty(str)) {
			return str;
		}
		//字段里面包含了分隔符才转换，否则返回原字段
		if (!str.contains(separator)){
			return str;
		}
		StringBuilder sb = new StringBuilder(str.length());
		String lowerStr = str.toLowerCase();
		String[] splitStrArr = lowerStr.split(separator);
		//切分的数组是空 返回当前(由于split方法不会返回空，所以这个判断可以省略)
//		if (SystemUtil.isEmpty(splitStrArr)){
////		if (splitStrArr == null || splitStrArr.length == 0){
//			return str;
//		}
		for (String splitStr : splitStrArr) {
			sb.append(Character.toUpperCase(splitStr.charAt(0)));
			sb.append(splitStr.substring(1));
		}
		return sb.toString();
	}

	/**
	 * 首字母大写,其它字母小写
	 * <p> capitalizeFully("abc")	结果是Abc </p>
	 * <p> capitalizeFully("abc_Abc")	结果是AbcAbc </p>
	 * <p> capitalizeFully("abc,Abc")	结果是Abc,abc </p>
	 * @param str
	 * @return
	 */
	public static String capitalizeFully(String str) {
		return capitalizeFully(str, SeparatorTypeEnum.UNDERLINE.getValue());
	}

	/**
	 * 首字母大写
	 * @param str
	 * @return
	 */
	public static String capitalize(String str) {
		return changeFirstCharacterCase(str, true);
	}

	/**
	 * 首字母小写
	 * @param str
	 * @return
	 */
	public static String uncapitalize(String str) {
		return changeFirstCharacterCase(str, false);
	}

	/**
	 * 首字母小写
	 * @param clazz
	 * @return
	 */
	public static String uncapitalize(Class<?> clazz) {
		//clazz.getName();// 全路径
		String className = clazz.getSimpleName();//只是类名
		return changeFirstCharacterCase(className, false);
	}

	/**
	 * 转换首字母，其他字母不变
	 * @param str
	 * @param capitalize
	 * @return
	 */
	private static String changeFirstCharacterCase(String str, boolean capitalize) {
		if (isEmpty(str)) {
			return str;
		}
		StringBuilder sb = new StringBuilder(str.length());
		if (capitalize) {
			sb.append(Character.toUpperCase(str.charAt(0)));
		}else {
			sb.append(Character.toLowerCase(str.charAt(0)));
		}
		sb.append(str.substring(1));
		return sb.toString();
	}
    
}
