package rmi.register.commodityRegister;

import java.net.MalformedURLException;
import java.rmi.AlreadyBoundException;
import java.rmi.Naming;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;

import rmi.config.RmiPort;
import rmi.dataRemoteObject.commodityRemoteObject.InventoryCheckRemoteObject;

public class InventoryCheckRegister {
	public InventoryCheckRegister(){

	}

	public void register(){
		InventoryCheckRemoteObject remoteObject=null;
		try {
			remoteObject = new InventoryCheckRemoteObject();
			LocateRegistry.createRegistry(6006);
			Naming.bind(RmiPort.PATH+"6006/InventoryCheckRemoteObject",
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
