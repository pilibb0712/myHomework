package blStubs.adminBlStubs;

import java.rmi.RemoteException;
import java.util.ArrayList;

import assistant.exception.Myexception;
import assistant.type.UserPositionEnum;
import blService.adminBlService.AdminBlService;
import vo.UserVO;


public class AdminBlService_Stub implements AdminBlService {

	@Override
	public ArrayList<UserVO> getUserList() throws RemoteException, Myexception {
		ArrayList<UserVO> userVOs = new ArrayList<>();
		UserVO user1 = new UserVO();
		user1.setAge(18);
		user1.setDescription("stub user1");
		user1.setName("stub user1");
		user1.setPosition(UserPositionEnum.FINANCE);
		
		UserVO user2 = new UserVO();
		user2.setAge(18);
		user2.setDescription("stub user2");
		user2.setName("stub user2");
		user2.setPosition(UserPositionEnum.MANAGER);
		userVOs.add(user1);
		userVOs.add(user2);
		return userVOs;
	}

	@Override
	public UserVO getUser(String userId) throws RemoteException, Myexception {
		UserVO user = new UserVO();
		user.setAge(18);
		user.setDescription("stub user");
		user.setName("stub user");
		user.setPosition(UserPositionEnum.FINANCE);
		return user;
	}

	@Override
	public String addUser(UserVO user) throws RemoteException, Myexception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean deleteUser(String userId) throws RemoteException, Myexception {
		return true;
	}

	@Override
	public boolean updateUser(UserVO user) throws RemoteException, Myexception {
		return true;
	}

	
}
