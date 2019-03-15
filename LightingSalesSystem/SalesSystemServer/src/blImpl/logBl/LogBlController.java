package blImpl.logBl;

import blService.logBlService.LogBlService;

/**
 * 工作人员创建账户或登录的业务逻辑控制器
 * @author 王宁一
 * @version 2017.11.7
 *
 */

public class LogBlController implements LogBlService{

	private LogBl Impl=new LogBl();
	/**
	 * 判断用户是否为合法用户
	 * @param 用户输入的ID（字符串）
	 * @param 用户输入的密码（字符串）
	 * @return 布尔值，如果用户名与密码匹配返回True
	 */
	@Override
	public boolean isValidUser(String userId, String password) {
		// TODO Auto-generated method stub
		boolean success=Impl.isValidUser(userId, password);
		return success;
	}

	/**
	 * 注册用户（创建账户）
	 * @param 用户输入已经被分配好的ID（字符串）
	 * @param 用户输入的密码（字符串）
	 * @return 布尔值，如果创建过程成功则返回True
	 */
	@Override
	public boolean registerUser(String userId, String password) {
		// TODO Auto-generated method stub
		boolean success=Impl.registerUser(userId, password);
		return success;
	}

	/**
	 * 修改密码, 不能出现某些特殊字符, 如windows命名规则
	 * @param 用户输入的ID（字符串）
	 * @param 用户输入的旧密码（字符串）
	 * @param 用户输入的新密码（字符串）
	 * @return 布尔值，如果修改密码成功则返回True
	 */
	@Override
	public boolean changePassword(String userId, String oldPassword, String password) {
		// TODO Auto-generated method stub
		boolean success=Impl.changePassword(userId, oldPassword, password);
		return success;
	}

}
