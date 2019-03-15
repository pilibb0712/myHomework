package rmi.register.billStateRegister;

import java.net.MalformedURLException;
import java.rmi.AlreadyBoundException;
import java.rmi.Naming;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;

import rmi.config.RmiPort;
import rmi.dataRemoteObject.billStateRemoteObject.FinancialStaffBillStateRemoteObject;

public class FinancialStaffBillStateRegister {
	public FinancialStaffBillStateRegister(){
		
	}
	
	public void register(){
		FinancialStaffBillStateRemoteObject remoteObject=null;
		try {
			remoteObject = new FinancialStaffBillStateRemoteObject();
			LocateRegistry.createRegistry(8004);
			Naming.bind(RmiPort.PATH+"8004/FinancialStaffBillStateRemoteObject",
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
