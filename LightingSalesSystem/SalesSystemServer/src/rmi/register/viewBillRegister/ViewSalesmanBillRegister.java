package rmi.register.viewBillRegister;

import java.net.MalformedURLException;
import java.rmi.AlreadyBoundException;
import java.rmi.Naming;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;

import rmi.config.RmiPort;
import rmi.dataRemoteObject.viewBillRemoteObject.ViewSalesmanBillRemoteObject;

public class ViewSalesmanBillRegister {
	public ViewSalesmanBillRegister(){
		
	}
	
	public void register(){
		ViewSalesmanBillRemoteObject remoteObject;
		try {
			remoteObject = new ViewSalesmanBillRemoteObject();
			LocateRegistry.createRegistry(8008);
			Naming.bind(RmiPort.PATH+"8008/ViewSalesmanBillRemoteObject",
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
