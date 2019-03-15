package rmi.register.financeRegister;

import java.net.MalformedURLException;
import java.rmi.AlreadyBoundException;
import java.rmi.Naming;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;

import rmi.config.RmiPort;
import rmi.dataRemoteObject.financeRemoteObject.InOutRemoteObject;

public class InOutRegister {
	public InOutRegister(){
		
	}
	
	public void register(){
		InOutRemoteObject remoteObject=null;
		
		try {
			remoteObject = new InOutRemoteObject();
			LocateRegistry.createRegistry(8002);
			Naming.bind(RmiPort.PATH+"8002/InOutRemoteObject",
				remoteObject);
		} catch (RemoteException e) {
			e.printStackTrace();
		}catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (AlreadyBoundException e) {
			e.printStackTrace();
		}
		
	}
}
