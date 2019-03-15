/**
 * 
 */
package blStubs.logBlStubs;

import java.rmi.RemoteException;

import blService.logBlService.LogBlService;

/**
 * @author cosx
 *
 */
public class LogBlService_Stub implements LogBlService {

	/**
	 * @see blService.logBlService.LogBlService#isValidUser(java.lang.String, java.lang.String)
	 * @param userId
	 * @param password
	 * @return
	 * @throws RemoteException
	 */
	@Override
	public boolean isValidUser(String userId, String password) throws RemoteException {
		// TODO Auto-generated method stub
		return true;
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
		// TODO Auto-generated method stub
		return true;
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
		// TODO Auto-generated method stub
		return true;
	}

}
