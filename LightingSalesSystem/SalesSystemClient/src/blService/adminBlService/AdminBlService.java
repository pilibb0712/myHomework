package blService.adminBlService;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.ArrayList;

import assistant.exception.Myexception;
import vo.UserVO;;

public interface AdminBlService extends Remote {

public ArrayList<UserVO> getUserList() throws RemoteException,Myexception;
	
	public UserVO getUser( String userId) throws RemoteException,Myexception;
	
	
	public String addUser(UserVO user) throws RemoteException,Myexception;

	
	public boolean deleteUser( String userId) throws RemoteException,Myexception;

	
	public boolean updateUser(UserVO user) throws RemoteException,Myexception;
	
}
