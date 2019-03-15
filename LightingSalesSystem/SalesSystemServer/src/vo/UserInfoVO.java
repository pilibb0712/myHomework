package vo;

import java.io.Serializable;

import assistant.type.UserPositionEnum;

/**
 * 只包含用户名称和Id的抽象的用户信息
 * @author zhangao 161250193
 * @version 2017.11.29
 * */

public class UserInfoVO implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 8244218252612913451L;
	private String Id;//用户的Id
	private String name;//用户名字
	private UserPositionEnum userPositionEnum;//用户职位
	
	public UserInfoVO(){
		
	}
	
	public UserInfoVO(String i, String n){
		setId(i);
		setName(n);
	}
	
	public UserInfoVO(String i, String n, UserPositionEnum pos){
		setId(i);
		setName(n);
		setUserPositionEnum(pos);
	}
	
	public String getId() {
		if(Id==null)
			return "";
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
	public UserPositionEnum getUserPositionEnum() {
		return userPositionEnum;
	}
	public void setUserPositionEnum(UserPositionEnum userPositionEnum) {
		this.userPositionEnum = userPositionEnum;
	}
	
	
}
