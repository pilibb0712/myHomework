package rmi.register.commodityRegister;

import java.net.MalformedURLException;
import java.rmi.AlreadyBoundException;
import java.rmi.Naming;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;

import rmi.config.RmiPort;
import rmi.dataRemoteObject.commodityRemoteObject.DeleteGoodsRemoteObject;

public class DeleteGoodsRegister {
	public DeleteGoodsRegister(){

	}

	public void register(){
		DeleteGoodsRemoteObject remoteObject=null;
		try {
			remoteObject = new DeleteGoodsRemoteObject();
			LocateRegistry.createRegistry(6003);
			Naming.bind(RmiPort.PATH+"6003/DeleteGoodsRemoteObject",
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
