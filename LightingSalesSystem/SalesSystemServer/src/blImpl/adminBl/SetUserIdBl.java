package blImpl.adminBl;

import dataImpl.userData.GenerateIdData;

/**
 * 设置用户Id的一个单件模式的一个逻辑类
 * @author zhangao 161250193
 * @version 2017.12.27
 * 得到用户Id
 */
public class SetUserIdBl {
	private static SetUserIdBl setUserIdBl;
	
	private SetUserIdBl(){
		
	}
	
	public static SetUserIdBl getInstance(){
		if(setUserIdBl==null){
			setUserIdBl = new SetUserIdBl();
		}
		return setUserIdBl;
	}
	
	/**
	 * 生成用户ID
	 * */
	public String generateId(){
		GenerateIdData generateIdData = new GenerateIdData();
		String id = generateIdData.generateId();
		return id;
	}
}
