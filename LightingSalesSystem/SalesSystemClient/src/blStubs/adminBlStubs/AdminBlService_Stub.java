package blStubs.adminBlStubs;

import java.rmi.RemoteException;
import java.util.ArrayList;

import assistant.type.UserPositionEnum;
import blService.adminBlService.AdminBlService;
import vo.UserVO;


public class AdminBlService_Stub implements AdminBlService {

	@Override
	public ArrayList<UserVO> getUserList() throws RemoteException {
		ArrayList<UserVO> userVOs = new ArrayList<>();
		UserVO user1 = new UserVO();
		user1.setName("ÕÅ°Á");
		user1.setAge(18);
		user1.setId("12345");
		user1.setPosition(UserPositionEnum.MANAGER);
		user1.setDescription("¹þ¹þ¹þ¹þ¹þ¹þ¹þ¹þ¹þ¹þ¹þ¹þ");
		UserVO user2 = new UserVO();
		user2.setName("ÍõÄþÒ»");
		user2.setAge(19);
		user2.setId("97582");
		user2.setPosition(UserPositionEnum.SALESMAN);
		user2.setDescription("xixixixiixix");
		userVOs.add(user1);
		userVOs.add(user2);
		return userVOs;
	}

	@Override
	public UserVO getUser(String userId) throws RemoteException {
		// TODO Auto-generated method stub
		UserVO vo=new UserVO();
		vo.setName("testName");
		vo.setId(userId);
		vo.setAge(20);
		vo.setDescription("²ÆÎñÈËÔ±");
		vo.setPosition(UserPositionEnum.FINANCE);
		return vo;
	}

	@Override
	public String addUser(UserVO user) throws RemoteException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean deleteUser(String userId) throws RemoteException {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean updateUser(UserVO user) throws RemoteException {
		// TODO Auto-generated method stub
		return false;
	}

	
}
