package assistant.type;

import java.io.Serializable;

public enum CustomerLevelEnum implements Serializable{
	VIP5(5),VIP4(4),VIP3(3),VIP2(2),VIP1(1);
	
	private final int value;
	
	private CustomerLevelEnum(int value){
		this.value = value;
	}
	
	public String toString(){
		return this.name();
	}
	
	public boolean isBiggerThanLevel(CustomerLevelEnum customerLevel){
		return this.value>customerLevel.value;
	}
}
