package blImpl.adminBl;

import java.rmi.RemoteException;
import java.util.ArrayList;

import assistant.convertors.UserPOVOConvertor;
import assistant.exception.Myexception;
import dataImpl.userData.UserData;
import dataService.userDataService.UserDataService;
import po.UserPO;
import vo.UserVO;

/**
 * 用户的bl对象
 * @author zhangao 
 * @version 2017.12.26
 * */
public class AdminBl {
	
	UserDataService service =  new UserData();//数据层对象
	
	/**
	 * 通过id得到用户信息
	 * @param userId 用户的id
	 * @return UserVO 用户信息
	 * @throws RemoteException
	 * @throws Myexception 自定义异常，待写
	 * */
	public UserVO getUser( String userId) throws RemoteException, Myexception{
		UserPO po = service.getUser(userId);
		return UserPOVOConvertor.poToVO(po);
	}

	/**
	 * 得到当前用户清单
	 * @return ArrayList<UserVO> 当前用户清单
	 * @throws RemoteException
	 * @throws Myexception 自定义异常，待写
	 * */
	public ArrayList<UserVO> getUserList() throws RemoteException, Myexception{
		ArrayList<UserVO> vos = new ArrayList<>();
		ArrayList<UserPO> pos = service.getUserList();
		for(UserPO po:pos){
			UserVO vo = UserPOVOConvertor.poToVO(po);
			vos.add(vo);
		}
		return vos;
	}
	
	/**
	 * 新增用户
	 * @param user 新增的用户信息
	 * @return String 生成的用户的Id
	 * @throws RemoteException
	 * @throws Myexception 自定义异常，待写
	 * */
	public String addUser(UserVO user) throws RemoteException, Myexception{
		String id = SetUserIdBl.getInstance().generateId();
		UserPO po = UserPOVOConvertor.voToPO(user);
		po.setId(id);
		service.addUser(po);
		return id;
	}

	/**
	 * 删除用户
	 * @param userId 要删除的用户ID
	 * @return 是否删除成功
	 * @throws RemoteException
	 * @throws Myexception 自定义异常，待写
	 * */
	public boolean deleteUser( String userId) throws RemoteException, Myexception{
		return service.deleteUser(userId);
	}

	/**
	 * 更新用户信息
	 * @param user 要更新的用户信息
	 * @return 是否更新成功
	 * @throws RemoteException
	 * @throws Myexception 自定义异常，待写
	 * */
	public boolean updateUser(UserVO user) throws RemoteException, Myexception{
		UserPO po = UserPOVOConvertor.voToPO(user);
		return service.updateUser(po);
	}
}
