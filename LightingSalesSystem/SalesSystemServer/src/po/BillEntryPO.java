package po;

import java.io.Serializable;

/**
 * 财务类单据里的条目清单中的一条
 * @author guxinyu
 * @version 2017.12.4
 *
 */
public class BillEntryPO implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 3841367687333366267L;
	private String entryName;
	private String entryMoney;
	
	public BillEntryPO(String name, String money){
		setEntryName(name);
		setEntryMoney(money);
	}
	
	public void setEntryName(String name){
		entryName=name;
	}
	
	public String getEntryName(){
		return entryName;
	}
	
	public void setEntryMoney(String money){
		entryMoney=money;;
	}
	
	public String getEntryMoney(){
		return entryMoney;
	}
}
