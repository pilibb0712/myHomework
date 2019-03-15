package blInteract.userInteract;

import java.rmi.RemoteException;

import assistant.exception.Myexception;
import vo.UserInfoVO;

public interface GetUserInfoByUserNameService{
	public UserInfoVO getUserInfoByUserName(String name)throws RemoteException, Myexception;
}
