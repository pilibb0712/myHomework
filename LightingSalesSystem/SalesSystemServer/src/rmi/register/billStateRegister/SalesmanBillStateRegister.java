package rmi.register.billStateRegister;

import java.net.MalformedURLException;
import java.rmi.AlreadyBoundException;
import java.rmi.Naming;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;

import rmi.config.RmiPort;
import rmi.dataRemoteObject.billStateRemoteObject.SalesmanBillStateRemoteObject;

public class SalesmanBillStateRegister {
	public SalesmanBillStateRegister(){
		
	}
	
	public void register(){
		SalesmanBillStateRemoteObject remoteObject=null;
		try {
			remoteObject = new SalesmanBillStateRemoteObject();
			LocateRegistry.createRegistry(8005);
			Naming.bind(RmiPort.PATH+"8005/SalesmanBillStateRemoteObject",
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
