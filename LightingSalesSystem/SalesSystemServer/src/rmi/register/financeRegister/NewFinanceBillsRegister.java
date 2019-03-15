package rmi.register.financeRegister;

import java.net.MalformedURLException;
import java.rmi.AlreadyBoundException;
import java.rmi.Naming;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import rmi.dataRemoteObject.financeRemoteObject.NewFinanceBillsRemoteObject;

public class NewFinanceBillsRegister {
	public NewFinanceBillsRegister(){
		
	}
	
	public void register(){
		NewFinanceBillsRemoteObject remoteObject=null;
		
		try {
			remoteObject = new NewFinanceBillsRemoteObject();
			LocateRegistry.createRegistry(8003);
			Naming.bind("rmi://127.0.0.1:8003/NewFinanceBillsRemoteObject",
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
