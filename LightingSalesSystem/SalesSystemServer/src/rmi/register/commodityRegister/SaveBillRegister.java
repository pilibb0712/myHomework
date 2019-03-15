package rmi.register.commodityRegister;

import java.net.MalformedURLException;
import java.rmi.AlreadyBoundException;
import java.rmi.Naming;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;

import rmi.config.RmiPort;
import rmi.dataRemoteObject.commodityRemoteObject.SaveBillRemoteObject;

public class SaveBillRegister {
	public SaveBillRegister(){

	}

	public void register(){
		SaveBillRemoteObject remoteObject=null;
		try {
			remoteObject = new SaveBillRemoteObject();
			LocateRegistry.createRegistry(6007);
			Naming.bind(RmiPort.PATH+"6007/SaveBillRemoteObject",
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
