/**
 * 
 */
package vo;

import java.io.Serializable;

import assistant.type.CustomerLevelEnum;

/**
 * gxy改了
 * @author 王宁一
 * @version 2017.12.18
 * 给财务人员的客户信息
 */
public class CustomerInfoVO implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 10419021593395583L;
	private String Id;
	private String name;
	private String type;
	private CustomerLevelEnum level;
	
	public CustomerInfoVO(){
		
	}
	
	public CustomerInfoVO(String i,String n, String t, CustomerLevelEnum l){
		setId(i);
		setName(n);
		setType(t);
		setLevel(l);
	}
	
	public String getId() {
		return Id;
	}
	
	public void setId(String id) {
		Id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public CustomerLevelEnum getLevel() {
		return level;
	}

	public void setLevel(CustomerLevelEnum level) {
		this.level = level;
	}
	
	
}
