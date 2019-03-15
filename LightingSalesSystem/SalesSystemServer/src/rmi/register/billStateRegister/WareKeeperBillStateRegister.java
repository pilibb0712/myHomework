package rmi.register.billStateRegister;

import java.net.MalformedURLException;
import java.rmi.AlreadyBoundException;
import java.rmi.Naming;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;

import rmi.config.RmiPort;
import rmi.dataRemoteObject.billStateRemoteObject.WareKeeperBillStateRemoteObject;

public class WareKeeperBillStateRegister {
	public WareKeeperBillStateRegister(){
		
	}
	
	public void register(){
		WareKeeperBillStateRemoteObject remoteObject=null;
		try {
			remoteObject = new WareKeeperBillStateRemoteObject();
			LocateRegistry.createRegistry(8006);
			Naming.bind(RmiPort.PATH+""
					+ "8006/WareKeeperBillStateRemoteObject",
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
