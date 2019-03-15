package dataService.userDataService;

import java.rmi.RemoteException;
import java.util.ArrayList;

import assistant.exception.Myexception;
import po.UserPO;
import vo.UserVO;

public interface UserDataService{
	 public UserPO getUser(String userId) throws RemoteException,Myexception;
	 public ArrayList<UserPO> getUserList() throws RemoteException,Myexception;
	 public boolean addUser(UserPO user) throws RemoteException,Myexception;
	 public boolean deleteUser(String Id) throws RemoteException,Myexception;
	 public boolean updateUser(UserPO user) throws RemoteException,Myexception;
	 public boolean changePassword(String userId, String oldPassword, String newPassword)throws RemoteException,Myexception ;
	 public boolean registerUser(String userId, String password)throws RemoteException,Myexception ;
	 public boolean isValidUser(String userId, String password)throws RemoteException,Myexception ; 
	 public boolean adminRegister(String userId)throws RemoteException,Myexception;
}
