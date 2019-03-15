package assistant.type;

import java.io.Serializable;

/**
 *@author zhangao 161250193
 *@version 2017.11.7
 *促销策略的类型枚举
 */

public enum StrategyCategoryEnum implements Serializable{
	/**
     * USRESTRATEGY 用户促销策略
     * TOTALSTRATEGY 总价促销策略
     * COMBOSTRATEGY 组合促销策略
     */
	USRESTRATEGY,TOTALSTRATEGY,COMBOSTRATEGY;
	public String toString(){
		switch (this) {
		case USRESTRATEGY:
			return "user_strategy";
		case TOTALSTRATEGY:
			return "total_strategy";
		case COMBOSTRATEGY:
			return "combo_strategy";
		default:
			return null;
		}
	}
	
	public String toChineseString(){
		switch (this) {
		case USRESTRATEGY:
			return "用户促销策略";
		case TOTALSTRATEGY:
			return "总价促销策略";
		case COMBOSTRATEGY:
			return "组合促销策略";
		default:
			return null;
		}
	}
}
