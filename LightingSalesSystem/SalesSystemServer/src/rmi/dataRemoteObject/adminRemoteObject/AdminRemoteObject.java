package rmi.dataRemoteObject.adminRemoteObject;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;

import assistant.exception.Myexception;
import blImpl.adminBl.AdminBlController;
import blService.adminBlService.AdminBlService;
import vo.UserVO;

public class AdminRemoteObject extends UnicastRemoteObject implements AdminBlService{

	/**
	 * 
	 */
	private static final long serialVersionUID = -386087692403266818L;

	public AdminRemoteObject() throws RemoteException {
		super();
		// TODO Auto-generated constructor stub
	}

	private AdminBlService service = new AdminBlController();
	
	@Override
	public ArrayList<UserVO> getUserList() throws RemoteException, Myexception {
		return service.getUserList();
	}

	@Override
	public UserVO getUser(String userId) throws RemoteException, Myexception {
		return service.getUser(userId);
	}

	@Override
	public String addUser(UserVO user) throws RemoteException, Myexception {
		return service.addUser(user);
	}

	@Override
	public boolean deleteUser(String userId) throws RemoteException, Myexception {
		return service.deleteUser(userId);
	}

	@Override
	public boolean updateUser(UserVO user) throws RemoteException, Myexception {
		return service.updateUser(user);
	}

}
