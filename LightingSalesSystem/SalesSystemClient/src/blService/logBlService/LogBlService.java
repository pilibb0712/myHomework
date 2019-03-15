package blService.logBlService;

import java.rmi.Remote;
import java.rmi.RemoteException;

/**
 * 工作人员创建账户或登录的业务逻辑接口
 * @author 王宁一
 * @version 2017.11.7
 *
 */

public interface LogBlService extends Remote {

	/**
	 * 判断用户是否为合法用户
	 * @param 用户输入的ID（字符串）
	 * @param 用户输入的密码（字符串）
	 * @return 布尔值，如果用户名与密码匹配返回True
	 * @throws RemoteException
	 */
	public boolean isValidUser(String userId, String password) throws RemoteException;
	
	/**
	 * 注册用户（创建账户）
	 * @param 用户输入已经被分配好的ID（字符串）
	 * @param 用户输入的密码（字符串）
	 * @return 布尔值，如果创建过程成功则返回True
	 * @throws RemoteException
	 */
	public boolean registerUser(String userId, String password) throws RemoteException;
	
	/**
	 * 修改密码
	 * @param 用户输入的ID（字符串）
	 * @param 用户输入的旧密码（字符串）
	 * @param 用户输入的新密码（字符串）
	 * @return 布尔值，如果修改密码成功则返回True
	 * @throws RemoteException
	 */
	public boolean changePassword(String userId, String oldPassword, String password) throws RemoteException;
}
