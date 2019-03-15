package rmi.linker.approveBillServiceLinker;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.Remote;
import java.rmi.RemoteException;

import blService.billService.approveBillBlService.ApproveInventoryOverBillBlService;
import rmi.config.RmiPort;

public class ApproveInventoryOverBillServiceLinker {
	private static ApproveInventoryOverBillServiceLinker linker ;
	private Remote remote ;
	
	private ApproveInventoryOverBillServiceLinker(){
		setRemote();
	}
	
	/**
	 * °ó¶¨¶Ë¿ÚÄÃµ½remote
	 * */
	private void setRemote(){
		try {
			this.remote = Naming.lookup(RmiPort.PATH+"7704/ApproveInventoryOverBillRemoteObject");
		}catch (RemoteException e) {
			e.printStackTrace();
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (NotBoundException e) {
			e.printStackTrace();
		}
	}
	
	public static ApproveInventoryOverBillServiceLinker getInstance(){
		if(linker==null){
			linker = new ApproveInventoryOverBillServiceLinker();
		}
		return linker;
	}
	
	public ApproveInventoryOverBillBlService  getApproveInventoryOverBillBlService(){
		return (ApproveInventoryOverBillBlService)remote;
	}
	
}
