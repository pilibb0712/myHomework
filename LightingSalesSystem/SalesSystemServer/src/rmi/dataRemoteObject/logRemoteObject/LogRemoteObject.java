/**
 * 
 */
package rmi.dataRemoteObject.logRemoteObject;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

import blImpl.logBl.LogBlController;
import blService.logBlService.LogBlService;

/**
 * @author ÍõÄþÒ»
 *
 */
public class LogRemoteObject extends UnicastRemoteObject implements LogBlService{

	/**
	 * 
	 */
	private static final long serialVersionUID = 8636095595605858401L;

	public LogRemoteObject() throws RemoteException{
		super();
	}

	LogBlService service=new LogBlController();
	
	/**
	 * @see blService.logBlService.LogBlService#isValidUser(java.lang.String, java.lang.String)
	 * @param userId
	 * @param password
	 * @return
	 * @throws RemoteException
	 */
	@Override
	public boolean isValidUser(String userId, String password) throws RemoteException {
		return service.isValidUser(userId, password);
	}

	/**
	 * @see blService.logBlService.LogBlService#registerUser(java.lang.String, java.lang.String)
	 * @param userId
	 * @param password
	 * @return
	 * @throws RemoteException
	 */
	@Override
	public boolean registerUser(String userId, String password) throws RemoteException {
		return service.registerUser(userId, password);
	}

	/**
	 * @see blService.logBlService.LogBlService#changePassword(java.lang.String, java.lang.String, java.lang.String)
	 * @param userId
	 * @param oldPassword
	 * @param password
	 * @return
	 * @throws RemoteException
	 */
	@Override
	public boolean changePassword(String userId, String oldPassword, String password) throws RemoteException {
		return service.changePassword(userId, oldPassword, password);
	}
	
	
}
