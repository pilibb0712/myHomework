package rmi.linker.viewBillServiceLinker;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.Remote;
import java.rmi.RemoteException;

import blService.billService.viewBillBlService.ViewFinanceStaffBillBlService;
import rmi.config.RmiPort;

public class ViewFinanceStaffBillServiceLinker {
	private static ViewFinanceStaffBillServiceLinker linker;
	private Remote remote;
	
	private ViewFinanceStaffBillServiceLinker(){
		setRemote();
	}
	
	public void setRemote(){
		try {
			this.remote = Naming.lookup(RmiPort.PATH+"8007/ViewFinanceStaffBillRemoteObject");
		}catch (RemoteException e) {
			e.printStackTrace();
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (NotBoundException e) {
			e.printStackTrace();
		}
	}
	
	public static ViewFinanceStaffBillServiceLinker getInstance(){
		if(linker==null){
			linker=new ViewFinanceStaffBillServiceLinker();
		}
		return linker;
	}
	
	public ViewFinanceStaffBillBlService getViewFinanceStaffBillBlService(){
		return (ViewFinanceStaffBillBlService)remote;
	}
}
