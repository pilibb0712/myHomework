package po;

import java.io.Serializable;

import assistant.type.UserPositionEnum;

public class UserPO implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = -1400610599607490228L;
	
	private String Id;
	private String name;
	private String description;
	private int age;
	private UserPositionEnum position;	
	
	public UserPO(){
		
	}
	
	public UserPO(String Id,String name,String description,int age,UserPositionEnum position){
		this.age=age;
		this.Id=Id;
		this.name=name;
		this.description=description;
		this.position=position;
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
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public int getAge() {
		return age;
	}
	public void setAge(int age) {
		this.age = age;
	}
	public UserPositionEnum getPosition() {
		return position;
	}
	public void setPosition(UserPositionEnum position) {
		this.position = position;
	}
}
