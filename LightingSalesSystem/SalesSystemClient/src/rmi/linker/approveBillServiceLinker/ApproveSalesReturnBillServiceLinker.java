 package rmi.linker.approveBillServiceLinker;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.Remote;
import java.rmi.RemoteException;

import blService.billService.approveBillBlService.ApproveSalesReturnBillBlService;
import rmi.config.RmiPort;

public class ApproveSalesReturnBillServiceLinker {
	private static ApproveSalesReturnBillServiceLinker linker ;
	private Remote remote ;
	
	private ApproveSalesReturnBillServiceLinker(){
		setRemote();
	}
	
	/**
	 * °ó¶¨¶Ë¿ÚÄÃµ½remote
	 * */
	private void setRemote(){
		try {
			this.remote = Naming.lookup(RmiPort.PATH+"7711/ApproveSalesReturnBillRemoteObject");
		}catch (RemoteException e) {
			e.printStackTrace();
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (NotBoundException e) {
			e.printStackTrace();
		}
	}
	
	public static ApproveSalesReturnBillServiceLinker getInstance(){
		if(linker==null){
			linker = new ApproveSalesReturnBillServiceLinker();
		}
		return linker;
	}
	
	public ApproveSalesReturnBillBlService  getApproveSalesReturnBillBlService(){
		return (ApproveSalesReturnBillBlService)remote;
	}
}
