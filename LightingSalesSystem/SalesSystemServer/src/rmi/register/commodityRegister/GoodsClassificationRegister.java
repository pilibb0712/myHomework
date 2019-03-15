package rmi.register.commodityRegister;

import java.net.MalformedURLException;
import java.rmi.AlreadyBoundException;
import java.rmi.Naming;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;

import rmi.config.RmiPort;
import rmi.dataRemoteObject.commodityRemoteObject.GoodsClassificationRemoteObject;

public class GoodsClassificationRegister {
	public GoodsClassificationRegister(){

	}

	public void register(){
		GoodsClassificationRemoteObject remoteObject=null;
		try {
			remoteObject = new GoodsClassificationRemoteObject();
			LocateRegistry.createRegistry(6004);
			Naming.bind(RmiPort.PATH+""
					+ ""
					+ "6004/GoodsClassificationRemoteObject",
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
