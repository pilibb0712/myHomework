package blImpl.logBl;

import java.rmi.RemoteException;

import assistant.exception.Myexception;
import dataImpl.userData.UserData;
import dataService.userDataService.UserDataService;

/**
 * 工作人员登录或注册的业务逻辑
 * @author 王宁一
 * @version 2017.11.7
 *
 */

public class LogBl {

	private UserDataService data=new UserData();
	
	/**
	 * 判断用户是否为合法用户
	 * @param userId(String)
	 * @param password(String)
	 * @return boolean, true表示用户名和密码匹配, 是合法用户
	 */
	protected boolean isValidUser(String userId, String password) {
		// TODO Auto-generated method stub
		boolean success;
		try {
			success = data.isValidUser(userId, password);
			return success;
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (Myexception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return false;
	}
	
	/**
	 * 注册用户（创建账户）, 注意:UI层得到确认信息后应该跳转至更改密码界面。
	 * @param userId(String)
	 * @param password(String)
	 * @return boolean, true表示此员工已被分配账号, 注册成功
	 */
	public boolean registerUser(String userId, String password) {
		// TODO Auto-generated method stub
		boolean success;
		try {
			success = data.registerUser(userId, password);
			return success;
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (Myexception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return false;
	}
	
	/**
	 * 修改密码
	 * @param userId(String)
	 * @param oldPassword(String)
	 * @param password(String)
	 * @return boolean, true表示更改密码成功
	 */
	public boolean changePassword(String userId, String oldPassword, String password) {
		// TODO Auto-generated method stub
		String newPassword=password;
		boolean success;
		try {
			success = data.changePassword(userId, oldPassword, newPassword);
			return success;
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (Myexception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;
	}
}
