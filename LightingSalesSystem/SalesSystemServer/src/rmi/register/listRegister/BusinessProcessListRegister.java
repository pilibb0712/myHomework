package rmi.register.listRegister;

import java.net.MalformedURLException;
import java.rmi.AlreadyBoundException;
import java.rmi.Naming;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;

import rmi.config.RmiPort;
import rmi.dataRemoteObject.listRemoteObject.BusinessProcessListRemoteObject;

public class BusinessProcessListRegister {
	public BusinessProcessListRegister(){
		
	}
	
	public void register(){
		BusinessProcessListRemoteObject remoteObject=null;
		try {
			remoteObject = new BusinessProcessListRemoteObject();
			LocateRegistry.createRegistry(8011);
			Naming.bind(RmiPort.PATH+"8011/BusinessProcessListRemoteObject",
					remoteObject);
		} catch (RemoteException e) {
			e.printStackTrace();
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (AlreadyBoundException e) {
			e.printStackTrace();
		}
	}
}
