package rmi.linker.commodityServiceLinker;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.Remote;
import java.rmi.RemoteException;


import blService.commodityBlService.SaveBillBlService;
import rmi.config.RmiPort;

public class SaveBillServiceLinker {
	private static SaveBillServiceLinker linker;
	private Remote remote;

	private SaveBillServiceLinker(){
		setRemote();
	}

	private void setRemote(){
		try {
			this.remote = Naming.lookup(RmiPort.PATH+"6007/SaveBillRemoteObject");
		}catch (RemoteException e) {
			e.printStackTrace();
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (NotBoundException e) {
			e.printStackTrace();
		}
	}

	public static SaveBillServiceLinker getInstance(){
		if(linker==null){
			linker=new SaveBillServiceLinker();
		}
		return linker;
	}

	public SaveBillBlService getSaveBillBlService(){
		return (SaveBillBlService)remote;
	}
}
