package rmi.register.commodityRegister;

import java.net.MalformedURLException;
import java.rmi.AlreadyBoundException;
import java.rmi.Naming;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;

import rmi.config.RmiPort;
import rmi.dataRemoteObject.commodityRemoteObject.GoodsInforRemoteObject;

public class GoodsInforRegister {
	public GoodsInforRegister(){

	}

	public void register(){
		GoodsInforRemoteObject remoteObject=null;
		try {
			remoteObject = new GoodsInforRemoteObject();
			LocateRegistry.createRegistry(6005);
			Naming.bind(RmiPort.PATH+""
					+ "6005/GoodsInforRemoteObject",
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
