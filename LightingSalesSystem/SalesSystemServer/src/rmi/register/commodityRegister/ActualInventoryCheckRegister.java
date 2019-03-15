package rmi.register.commodityRegister;

import java.net.MalformedURLException;
import java.rmi.AlreadyBoundException;
import java.rmi.Naming;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;

import rmi.config.RmiPort;
import rmi.dataRemoteObject.commodityRemoteObject.ActualInventoryCheckRemoteObject;

public class ActualInventoryCheckRegister {
public ActualInventoryCheckRegister(){

	}

	public void register(){
		ActualInventoryCheckRemoteObject remoteObject=null;
		try {
			remoteObject = new ActualInventoryCheckRemoteObject();
			LocateRegistry.createRegistry(6001);
			Naming.bind(RmiPort.PATH+"6001/ActualInventoryCheckRemoteObject",
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
