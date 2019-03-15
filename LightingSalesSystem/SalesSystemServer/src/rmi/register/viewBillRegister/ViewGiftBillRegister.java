package rmi.register.viewBillRegister;

import java.net.MalformedURLException;
import java.rmi.AlreadyBoundException;
import java.rmi.Naming;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;

import rmi.config.RmiPort;
import rmi.dataRemoteObject.viewBillRemoteObject.ViewGiftBillRemoteObject;

public class ViewGiftBillRegister {
	public ViewGiftBillRegister(){
		
	}
	
	public void register(){
		ViewGiftBillRemoteObject remoteObject;
		try {
			remoteObject = new ViewGiftBillRemoteObject();
			LocateRegistry.createRegistry(7007);
			Naming.bind(RmiPort.PATH+"7007/ViewGiftBillRemoteObject",
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
