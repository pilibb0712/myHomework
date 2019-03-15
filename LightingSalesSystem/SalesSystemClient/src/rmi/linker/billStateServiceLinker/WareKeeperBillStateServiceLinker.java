package rmi.linker.billStateServiceLinker;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.Remote;
import java.rmi.RemoteException;

import blService.billService.billStateBlService.WareKeeperBillStateBlService;
import rmi.config.RmiPort;

public class WareKeeperBillStateServiceLinker {
	private static WareKeeperBillStateServiceLinker linker;
	private Remote remote;
	
	private WareKeeperBillStateServiceLinker(){
		setRemote();
	}
	
	private void setRemote(){
		try {
			this.remote = Naming.lookup(RmiPort.PATH+"8006/WareKeeperBillStateRemoteObject");
		}catch (RemoteException e) {
			e.printStackTrace();
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (NotBoundException e) {
			e.printStackTrace();
		}
	}
	
	public static WareKeeperBillStateServiceLinker getInstance(){
		if(linker==null){
			linker=new WareKeeperBillStateServiceLinker();
		}
		return linker;
	}
	
	public WareKeeperBillStateBlService getWareKeeperBillStateBlService(){
		return (WareKeeperBillStateBlService)remote;
	}
}
