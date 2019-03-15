 package rmi.linker.approveBillServiceLinker;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.Remote;
import java.rmi.RemoteException;

import blService.billService.approveBillBlService.ApprovePurchaseReturnBillBlService;
import rmi.config.RmiPort;

public class ApprovePurchaseReturnBillServiceLinker {
	private static ApprovePurchaseReturnBillServiceLinker linker ;
	private Remote remote ;
	
	private ApprovePurchaseReturnBillServiceLinker(){
		setRemote();
	}
	
	/**
	 * °ó¶¨¶Ë¿ÚÄÃµ½remote
	 * */
	private void setRemote(){
		try {
			this.remote = Naming.lookup(RmiPort.PATH+"7708/ApprovePurchaseReturnBillRemoteObject");
		}catch (RemoteException e) {
			e.printStackTrace();
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (NotBoundException e) {
			e.printStackTrace();
		}
	}
	
	public static ApprovePurchaseReturnBillServiceLinker getInstance(){
		if(linker==null){
			linker = new ApprovePurchaseReturnBillServiceLinker();
		}
		return linker;
	}
	
	public ApprovePurchaseReturnBillBlService  getApprovePurchaseReturnBillBlService(){
		return (ApprovePurchaseReturnBillBlService)remote;
	}
}
