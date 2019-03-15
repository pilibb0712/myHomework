package rmi.linker.commodityServiceLinker;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.Remote;
import java.rmi.RemoteException;

import blService.commodityBlService.ActualInventoryCheckBlService;
import rmi.config.RmiPort;

public class ActualInventoryCheckServiceLinker {
	private static ActualInventoryCheckServiceLinker linker;
	private Remote remote;

	private ActualInventoryCheckServiceLinker(){
		setRemote();
	}

	private void setRemote(){
		try {
			this.remote = Naming.lookup(RmiPort.PATH+"6001/ActualInventoryCheckRemoteObject");
		}catch (RemoteException e) {
			e.printStackTrace();
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (NotBoundException e) {
			e.printStackTrace();
		}
	}

	public static ActualInventoryCheckServiceLinker getInstance(){
		if(linker==null){
			linker=new ActualInventoryCheckServiceLinker();
		}
		return linker;
	}

	public ActualInventoryCheckBlService getActualInventoryCheckBlService(){
		return (ActualInventoryCheckBlService)remote;
	}
}
