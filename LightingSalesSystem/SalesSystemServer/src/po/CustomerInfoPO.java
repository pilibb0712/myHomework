package po;

import assistant.type.CustomerLevelEnum;

/**
 * gxy¼ÓµÄ
 * @author Lenovo
 *
 */
public class CustomerInfoPO {
	private String Id;
	private String name;
	private String type;
	private CustomerLevelEnum level;
	
	public CustomerInfoPO(){
		
	}
	
	public CustomerInfoPO(String i,String n, String t, CustomerLevelEnum l){
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
