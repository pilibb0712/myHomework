package rmi.linker.adminServiceLinker;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.Remote;
import java.rmi.RemoteException;

import blService.adminBlService.AdminBlService;
import blService.billService.approveBillBlService.ApproveCashExpenseBillBlService;
import rmi.config.RmiPort;

public class AdminServiceLinker {
	private static AdminServiceLinker linker ;
	private Remote remote ;
	
	private AdminServiceLinker(){
		setRemote();
	}
	
	/**
	 * °ó¶¨¶Ë¿ÚÄÃµ½remote
	 * */
	private void setRemote(){
		try {
			this.remote = Naming.lookup(RmiPort.PATH+"7200/AdminRemoteObject");
		}catch (RemoteException e) {
			e.printStackTrace();
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (NotBoundException e) {
			e.printStackTrace();
		}
	}
	
	public static AdminServiceLinker getInstance(){
		if(linker==null){
			linker = new AdminServiceLinker();
		}
		return linker;
	}
	
	public AdminBlService  getAdminBlService(){
		return (AdminBlService)remote;
	}
	
}
