/**
 * 
 */
package rmi.register.customerRegister;

import java.net.MalformedURLException;
import java.rmi.AlreadyBoundException;
import java.rmi.Naming;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;

import rmi.config.RmiPort;
import rmi.dataRemoteObject.customerRemoteObject.CustomerRemoteObject;

/**
 * @author ÍõÄþÒ»
 *
 */
public class CustomerRegister {

	public CustomerRegister() {
		
	}
	
	public void register() {
		CustomerRemoteObject remoteObject=null;
		try {
			remoteObject=new CustomerRemoteObject();
			LocateRegistry.createRegistry(9000);
			Naming.bind(RmiPort.PATH+"9000/CustomerRemoteObject", remoteObject);
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (RemoteException e) {
			e.printStackTrace();
		} catch (AlreadyBoundException e) {
			e.printStackTrace();
		}
	}
}
