package rmi.register.listRegister;

import java.net.MalformedURLException;
import java.rmi.AlreadyBoundException;
import java.rmi.Naming;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;

import rmi.config.RmiPort;
import rmi.dataRemoteObject.listRemoteObject.SalesDetailsListRemoteObject;

public class SalesDetailsListRegister {
	public SalesDetailsListRegister(){
		
	}
	
	public void register(){
		SalesDetailsListRemoteObject remoteObject=null;
		try {
			remoteObject = new SalesDetailsListRemoteObject();
			LocateRegistry.createRegistry(8012);
			Naming.bind(RmiPort.PATH+""
					+ ""
					+ "8012/SalesDetailsListRemoteObject",
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
