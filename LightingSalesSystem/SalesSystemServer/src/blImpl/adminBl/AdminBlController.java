package blImpl.adminBl;

import java.rmi.RemoteException;
import java.util.ArrayList;

import assistant.exception.Myexception;
import blService.adminBlService.AdminBlService;
import vo.UserVO;

public class AdminBlController implements AdminBlService {
	
	private AdminBl bl = new AdminBl();
	
	/**
	 * 通过id得到用户信息
	 * @param userId 用户的id
	 * @return UserVO 用户信息
	 * @throws RemoteException
	 * @throws Myexception 自定义异常，待写
	 * */
	@Override
	public UserVO getUser(String userId) throws RemoteException,Myexception {
		return bl.getUser(userId);
	}

	/**
	 * 新增用户
	 * @param user 新增的用户信息
	 * @return String 生成的用户的Id
	 * @throws RemoteException
	 * @throws Myexception 自定义异常，待写
	 * */
	@Override
	public String addUser(UserVO user)throws RemoteException,Myexception {
		
		return bl.addUser(user);
	}

	/**
	 * 删除用户
	 * @param userId 要删除的用户ID
	 * @return 是否删除成功
	 * @throws RemoteException
	 * @throws Myexception 自定义异常，待写
	 * */
	@Override
	public boolean deleteUser( String userId)throws RemoteException,Myexception {
		
		return bl.deleteUser(userId);
	}
	
	/**
	 * 更新用户信息
	 * @param user 要更新的用户信息
	 * @return 是否更新成功
	 * @throws RemoteException
	 * @throws Myexception 自定义异常，待写
	 * */
	@Override
	public boolean updateUser(UserVO user)throws RemoteException,Myexception  {
		return bl.updateUser(user);
	}

	
	/**
	 * 得到当前用户清单
	 * @return ArrayList<UserVO> 当前用户清单
	 * @throws RemoteException
	 * @throws Myexception 自定义异常，待写
	 * */
	@Override
	public ArrayList<UserVO> getUserList()throws RemoteException,Myexception  {
		return bl.getUserList();
	}

}
