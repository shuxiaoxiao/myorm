package com.shuframework.myorm.core;

import com.shuframework.myorm.bean.ColumnInfo;
import com.shuframework.myorm.bean.TableInfo;
import com.shuframework.myorm.util.StringUtil;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;


/**
 * 负责获取管理数据库所有表结构和类结构的关系，并可以根据表结构生成类结构。
 * @author gaoqi www.sxt.cn
 *
 */
public class TableContext {

	/**
	 * 表名为key，表信息对象为value
	 */
	public static  Map<String,TableInfo>  tables = new HashMap<String,TableInfo>();
	
	/**
	 * 将po的class对象和表信息对象关联起来，便于重用！
	 */
	public static  Map<Class,TableInfo>  poClassTableMap = new HashMap<Class,TableInfo>();
	
	private TableContext(){}
	
	static {
		try {
			//初始化获得表的信息
			Connection con = DBManager.getConn();
			DatabaseMetaData dbmd = con.getMetaData(); 
			
			ResultSet tableRet = dbmd.getTables(null, "%","%",new String[]{"TABLE"}); 
			
			while(tableRet.next()){
				String tableName = (String) tableRet.getObject("TABLE_NAME");
				
				TableInfo ti = new TableInfo();
				ti.setTableName(tableName);
//				TableInfo ti = new TableInfo(tableName, new ArrayList<ColumnInfo>(),new HashMap<String, ColumnInfo>());
				tables.put(tableName, ti);
				//查询表中的所有字段
				ResultSet columns = dbmd.getColumns(null, "%", tableName, "%");
				while(columns.next()){
					String columnName = columns.getString("COLUMN_NAME");
					String dataType = columns.getString("TYPE_NAME");
					String columnComment = columns.getString("REMARKS");
					String propertyName = StringUtil.toPropertyName(columnName);
					ColumnInfo ci = new ColumnInfo(columnName, columnComment, dataType, 0, dataType, propertyName);
					ti.getColumns().put(columnName, ci);
				}
				//查询表中的主键
				ResultSet pkColumn = dbmd.getPrimaryKeys(null, "%", tableName);
				while(pkColumn.next()){
					ColumnInfo ci2 = ti.getColumns().get(pkColumn.getObject("COLUMN_NAME"));
					ci2.setKeyType(1);  //设置为主键类型
					ti.getPriKeys().add(ci2);
				}
				
				if(ti.getPriKeys().size()>0){  //取唯一主键。。方便使用。如果是联合主键。则为空！
					ti.setOnlyPriKey(ti.getPriKeys().get(0));
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		//更新类结构(使用模板去生成)
//		updateJavaPOFile();

	}
	
//	/**
//	 * 根据表结构，更新配置的po包下面的java类
//	 * 实现了从表结构转化到类结构
//	 */
//	public static void updateJavaPOFile(){
//		Map<String,TableInfo> map = TableContext.tables;
//		for(TableInfo t:map.values()){
//			JavaFileUtils.createJavaPOFile(t,new MySqlTypeConvertor());
//		}
//	}
	
//	/**
//	 * 加载po包下面的类
//	 */
//	public static void loadPOTables(){
//
//		for(TableInfo tableInfo:tables.values()){
//			try {
//				Class c = Class.forName(DBManager.getConf().getPoPackage()
//						+"."+StringUtils.firstChar2UpperCase(tableInfo.getTname()));
//				poClassTableMap.put(c, tableInfo);
//			} catch (ClassNotFoundException e) {
//				e.printStackTrace();
//			}
//		}
//
//	}
	
	
	public static void main(String[] args) {
		 Map<String,TableInfo>  tables = TableContext.tables;
		 System.out.println(tables);
	}

}