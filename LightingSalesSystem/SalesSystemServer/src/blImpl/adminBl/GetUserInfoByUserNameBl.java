package blImpl.adminBl;

import java.rmi.RemoteException;
import java.util.ArrayList;

import assistant.exception.Myexception;
import blInteract.userInteract.GetUserInfoByUserNameService;
import vo.UserInfoVO;
import vo.UserVO;

public class GetUserInfoByUserNameBl implements GetUserInfoByUserNameService{

	@Override
	public UserInfoVO getUserInfoByUserName(String name) throws RemoteException, Myexception {
		AdminBl adminBl = new AdminBl();
		ArrayList<UserVO> userVOs = adminBl.getUserList();
		for(UserVO userVO: userVOs){
			if(userVO.getName().equals(name)){
				UserInfoVO userInfoVO 
					= new UserInfoVO(userVO.getId(),userVO.getName(),userVO.getPosition());
				return userInfoVO;
			}
		}
		return null;
	}
	
}
