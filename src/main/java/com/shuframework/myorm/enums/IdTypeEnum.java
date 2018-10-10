package com.shuframework.myorm.enums;

/**
 * 生成ID类型 枚举
 * 
 * @author shu
 *
 */
public enum IdTypeEnum {

	AUTO(0, "数据库ID自增"), INPUT(1, "用户输入ID"),

	/* 以下2种类型、只有当插入对象ID 为空，才自动填充。 */
	ID_WORKER(2, "全局唯一IDWORKER"), UUID(3, "全局唯一UUID");

	/**
	 * 主键
	 */
	private final int key;

	/**
	 * 描述
	 */
	private final String desc;

	IdTypeEnum(final int key, final String desc) {
		this.key = key;
		this.desc = desc;
	}

	// /**
	// * 主键策略 （默认 ID_WORKER）
	// *
	// * @param idType ID 策略类型
	// * @return
	// */
	// public static IdTypeEnum getIdType(int idType) {
	// IdTypeEnum[] its = IdTypeEnum.values();
	// for (IdTypeEnum it : its) {
	// if (it.getKey() == idType) {
	// return it;
	// }
	// }
	// return ID_WORKER;
	// }

	public int getKey() {
		return this.key;
	}

	public String getDesc() {
		return this.desc;
	}
}
