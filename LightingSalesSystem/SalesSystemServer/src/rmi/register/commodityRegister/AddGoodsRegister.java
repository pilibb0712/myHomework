package rmi.register.commodityRegister;

import java.net.MalformedURLException;
import java.rmi.AlreadyBoundException;
import java.rmi.Naming;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;

import rmi.config.RmiPort;
import rmi.dataRemoteObject.commodityRemoteObject.AddGoodsRemoteObject;

public class AddGoodsRegister {
	public AddGoodsRegister(){

	}

	public void register(){
		AddGoodsRemoteObject remoteObject=null;
		try {
			remoteObject = new AddGoodsRemoteObject();
			LocateRegistry.createRegistry(6002);
			Naming.bind(RmiPort.PATH+"6002/AddGoodsRemoteObject",
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
