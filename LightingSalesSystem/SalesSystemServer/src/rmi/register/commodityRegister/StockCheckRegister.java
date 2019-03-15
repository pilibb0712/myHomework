package rmi.register.commodityRegister;

import java.net.MalformedURLException;
import java.rmi.AlreadyBoundException;
import java.rmi.Naming;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;

import rmi.config.RmiPort;
import rmi.dataRemoteObject.commodityRemoteObject.StockCheckRemoteObject;

public class StockCheckRegister {
	public StockCheckRegister(){

	}

	public void register(){
		StockCheckRemoteObject remoteObject=null;
		try {
			remoteObject = new StockCheckRemoteObject();
			LocateRegistry.createRegistry(6008);
			Naming.bind(RmiPort.PATH+""
					+ "6008/StockCheckRemoteObject",
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
