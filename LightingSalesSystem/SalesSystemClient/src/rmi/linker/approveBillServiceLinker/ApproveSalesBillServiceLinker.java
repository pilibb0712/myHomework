 package rmi.linker.approveBillServiceLinker;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.Remote;
import java.rmi.RemoteException;

import blService.billService.approveBillBlService.ApproveSalesBillBlService;
import rmi.config.RmiPort;

public class ApproveSalesBillServiceLinker {
	private static ApproveSalesBillServiceLinker linker ;
	private Remote remote ;
	
	private ApproveSalesBillServiceLinker(){
		setRemote();
	}
	
	/**
	 * °ó¶¨¶Ë¿ÚÄÃµ½remote
	 * */
	private void setRemote(){
		try {
			this.remote = Naming.lookup(RmiPort.PATH+"7710/ApproveSalesBillRemoteObject");
		}catch (RemoteException e) {
			e.printStackTrace();
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (NotBoundException e) {
			e.printStackTrace();
		}
	}
	
	public static ApproveSalesBillServiceLinker getInstance(){
		if(linker==null){
			linker = new ApproveSalesBillServiceLinker();
		}
		return linker;
	}
	
	public ApproveSalesBillBlService  getApproveSalesBillBlService(){
		return (ApproveSalesBillBlService)remote;
	}
}
