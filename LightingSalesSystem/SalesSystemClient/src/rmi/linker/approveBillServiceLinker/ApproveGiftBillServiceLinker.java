package rmi.linker.approveBillServiceLinker;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.Remote;
import java.rmi.RemoteException;

import blService.billService.approveBillBlService.ApproveGiftBillBlService;
import rmi.config.RmiPort;

public class ApproveGiftBillServiceLinker {
	
	private static ApproveGiftBillServiceLinker linker ;
	private Remote remote ;
	
	private ApproveGiftBillServiceLinker(){
		setRemote();
	}
	
	/**
	 * °ó¶¨¶Ë¿ÚÄÃµ½remote
	 * */
	private void setRemote(){
		try {
			this.remote = Naming.lookup(RmiPort.PATH+"7702/ApproveGiftBillRemoteObject");
			System.out.println("linked");
		}catch (RemoteException e) {
			e.printStackTrace();
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (NotBoundException e) {
			e.printStackTrace();
		}
	}
	
	public static ApproveGiftBillServiceLinker getInstance(){
		if(linker==null){
			linker = new ApproveGiftBillServiceLinker();
		}
		return linker;
	}
	
	public ApproveGiftBillBlService  getApproveGiftBillBlService(){
		return (ApproveGiftBillBlService)remote;
	}
	
	
}
