package assistant.type;

import java.io.Serializable;
/**
 * @author zhangao 161250193
 * @version 2017.11.8
 * 单据状态的枚举类型
 */
public enum BillStateEnum implements Serializable {
	DONE,//审批通过并完成
	DENIED,//审批不通过
	TODO,//审批通过但未完成
	TBD,//待审批
	HIDDEN,//审批通过或不通过时，进行假删，即不再显示
	DRAFT;//草稿状态
	
	/**
	 * 返回当前单据状态(对应数据层文件夹名)。<br/>
	 * @return String 当前的单据的状态(小写字母，对应存的文件夹名称)
	 */
	public String toString(){
		return this.name().toLowerCase();
	}
}
