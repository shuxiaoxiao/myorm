package com.shuframework.myorm.util;

import com.shuframework.myorm.bean.ColumnInfo;
import com.shuframework.myorm.bean.JavaFieldGetSet;
import com.shuframework.myorm.bean.TableInfo;
import com.shuframework.myorm.core.DBManager;
import com.shuframework.myorm.core.MySqlTypeConvertor;
import com.shuframework.myorm.core.TableContext;
import com.shuframework.myorm.core.TypeConvertor;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;


/**
 * 封装了生成Java文件(源代码)常用的操作
 * @author gaoqi www.sxt.cn
 *
 */
public class JavaFileUtils {

	static String rt = "\r\t";
	static String n = "\n";
	static String t1 = "\t";
	static String t2 = "\t\t";


	/**
	 * 根据字段信息生成java属性信息。如：varchar username-->private String username;以及相应的set和get方法源码
	 * @param column 字段信息
	 * @param convertor 类型转化器
	 * @return java属性和set/get方法源码
	 */
	public static JavaFieldGetSet createFieldGetSetSRC(ColumnInfo column, TypeConvertor convertor){
		JavaFieldGetSet jfgs  = new JavaFieldGetSet();

		String columnName = column.getColumnName();
		String upperColumnName = StringUtil.capitalize(columnName);
		String javaFieldType = convertor.databaseType2JavaType(column.getColumnType());
		//private int userId;
		StringBuilder propSrc = new StringBuilder();
//		propSrc.append(t1).append("private ").append(javaFieldType).append(" ").append(columnName).append(";").append(n);
//		jfgs.setFieldInfo(propSrc.toString());
		propSrc.append(t1).append("private %s %s;").append(n);
		String fieldInfo = String.format(propSrc.toString(), javaFieldType, columnName);
		jfgs.setFieldInfo(fieldInfo);

		//public String getUsername(){return username;}
		StringBuilder getSrc = new StringBuilder();
//		getSrc.append(t1).append("public ").append(javaFieldType).append(" get").append(upperColumnName)
//				.append("(){").append(n);
//		getSrc.append(t2).append("return ").append(columnName).append(";").append(n);
//		getSrc.append(t1).append("}").append(n);
//		jfgs.setGetInfo(getSrc.toString());
		getSrc.append(t1).append("public %s get%s() {return %s;}").append(n);
		String getInfo = String.format(getSrc.toString(), javaFieldType, upperColumnName, columnName);
		jfgs.setGetInfo(getInfo);
		
		//public void setUsername(String username){this.username=username;}
		StringBuilder setSrc = new StringBuilder();
//		setSrc.append(t1).append("public void set").append(upperColumnName)
//				.append("(").append(javaFieldType).append(" ").append(columnName).append("){").append(n);
//		setSrc.append(t2).append("this.").append(columnName).append("=").append(columnName).append(";").append(n);
//		setSrc.append(t1).append("}").append(n);
//		jfgs.setSetInfo(setSrc.toString());
		setSrc.append(t1).append("public void set%s(%s %s) {this.%s = %s;}").append(n);
		String setInfo = String.format(setSrc.toString(), upperColumnName, javaFieldType, columnName, columnName, columnName);
		jfgs.setSetInfo(setInfo);

		return jfgs;
	}
	
//	/**
//	 * 根据表信息生成java类的源代码
//	 * @param tableInfo 表信息
//	 * @param convertor 数据类型转化器
//	 * @return java类的源代码
//	 */
//	public static String createJavaSrc(TableInfo tableInfo, TypeConvertor convertor){
//
//		Map<String,ColumnInfo> columns = tableInfo.getColumns();
//		List<JavaFieldGetSet> javaFields = new ArrayList<JavaFieldGetSet>();
//
//		for(ColumnInfo c:columns.values()){
//			javaFields.add(createFieldGetSetSRC(c,convertor));
//		}
//
//		StringBuilder src = new StringBuilder();
//
//		//生成package语句
//		src.append("package "+ DBManager.getConf().getPoPackage()+";\n\n");
//		//生成import语句
//		src.append("import java.sql.*;\n");
//		src.append("import java.util.*;\n\n");
//		//生成类声明语句
//		src.append("public class "+StringUtils.firstChar2UpperCase(tableInfo.getTname())+" {\n\n");
//
//		//生成属性列表
//		for(JavaFieldGetSet f:javaFields){
//			src.append(f.getFieldInfo());
//		}
//		src.append("\n\n");
//		//生成get方法列表
//		for(JavaFieldGetSet f:javaFields){
//			src.append(f.getGetInfo());
//		}
//		//生成set方法列表
//		for(JavaFieldGetSet f:javaFields){
//			src.append(f.getSetInfo());
//		}
//
//		//生成类结束
//		src.append("}\n");
//		return src.toString();
//	}
//
//
//	public static void createJavaPOFile(TableInfo tableInfo,TypeConvertor convertor){
//		String src = createJavaSrc(tableInfo,convertor);
//
//		String srcPath = DBManager.getConf().getSrcPath()+"\\";
//		String packagePath = DBManager.getConf().getPoPackage().replaceAll("\\.", "/");
//
//		File f = new File(srcPath+packagePath);
//
//		if(!f.exists()){  //如果指定目录不存在，则帮助用户建立
//			f.mkdirs();
//		}
//
//		BufferedWriter bw = null;
//
//		try {
//			bw = new BufferedWriter(new FileWriter(f.getAbsoluteFile()+"/"+StringUtils.firstChar2UpperCase(tableInfo.getTname())+".java"));
//			bw.write(src);
//			System.out.println("建立表"+tableInfo.getTname()+
//					"对应的java类："+StringUtils.firstChar2UpperCase(tableInfo.getTname())+".java");
//		} catch (IOException e) {
//			e.printStackTrace();
//		}finally{
//			try {
//				if(bw!=null){
//					bw.close();
//				}
//			} catch (IOException e) {
//				e.printStackTrace();
//			}
//		}
//
//	}
	
	
	public static void main(String[] args) {
		ColumnInfo ci = new ColumnInfo("id", "int", 0, "int");
		JavaFieldGetSet f = createFieldGetSetSRC(ci, new MySqlTypeConvertor());
		System.out.println(f);
		
//		Map<String,TableInfo> map = TableContext.tables;
//		for(TableInfo t:map.values()){
//			createJavaPOFile(t,new MySqlTypeConvertor());
//		}
	}
	
}
