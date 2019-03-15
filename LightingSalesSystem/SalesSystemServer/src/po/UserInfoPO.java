package po;

import java.io.Serializable;

import assistant.type.UserPositionEnum;

/**
 * UserInfoPO数据类
 * 包括用户名称和Id，用于单据中创建人信息等
 * @author 张傲  161250193
 * @version 2017.12.1
 */

public class UserInfoPO implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 2118414849207336385L;
	private String name;//用户名称
	private String Id;//用户的Id
	private UserPositionEnum userPosition;//用户职位
	
	/**
	 * 对用户信息中用户 名称 id 职位的比较，判断是否相等 
	 * @param UserInfoPO userInfoPO要比较的用户信息
	 * @return boolean 两个用户信息是否相等
	 */
	public boolean equals(UserInfoPO userInfoPO){
//		if(userInfoPO.getId().equals(this.Id)
//				&&userInfoPO.getName().equals(this.name)
//				&&userInfoPO.getUserPositionEnum()==this.userPosition){
//			return true;
//		}
		if(userInfoPO.getId().equals(this.Id)){
			return true;
		}
		return false;
	}
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getId() {
		return Id;
	}
	public void setId(String id) {
		Id = id;
	}
	public UserPositionEnum getUserPositionEnum() {
		return userPosition;
	}
	public void setUserPositionEnum(UserPositionEnum userPositionEnum) {
		this.userPosition = userPositionEnum;
	}
	
}
