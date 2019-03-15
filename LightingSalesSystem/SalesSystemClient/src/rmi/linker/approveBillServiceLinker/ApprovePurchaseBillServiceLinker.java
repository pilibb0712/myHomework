package rmi.linker.approveBillServiceLinker;
import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.Remote;
import java.rmi.RemoteException;

import blService.billService.approveBillBlService.ApprovePurchaseBillBlService;
import rmi.config.RmiPort;

public class ApprovePurchaseBillServiceLinker {
	private static ApprovePurchaseBillServiceLinker linker ;
	private Remote remote ;
	
	private ApprovePurchaseBillServiceLinker(){
		setRemote();
	}
	
	/**
	 * °ó¶¨¶Ë¿ÚÄÃµ½remote
	 * */
	private void setRemote(){
		try {
			this.remote = Naming.lookup(RmiPort.PATH+"7008/ApprovePurchaseBillRemoteObject");
		}catch (RemoteException e) {
			e.printStackTrace();
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (NotBoundException e) {
			e.printStackTrace();
		}
	}

	public static ApprovePurchaseBillServiceLinker getInstance(){
		if(linker==null){
			linker = new ApprovePurchaseBillServiceLinker();
		}
		return linker;
	}
	
	public ApprovePurchaseBillBlService  getApprovePurchaseBillBlService(){
		return (ApprovePurchaseBillBlService)remote;
	}
}