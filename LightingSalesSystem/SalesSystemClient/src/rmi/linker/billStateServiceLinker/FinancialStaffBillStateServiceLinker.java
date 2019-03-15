package rmi.linker.billStateServiceLinker;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.Remote;
import java.rmi.RemoteException;

import blService.billService.billStateBlService.FinancialStaffBillStateBlService;
import rmi.config.RmiPort;

public class FinancialStaffBillStateServiceLinker {
	private static FinancialStaffBillStateServiceLinker linker;
	private Remote remote;
	
	private FinancialStaffBillStateServiceLinker(){
		setRemote();
	}
	
	private void setRemote(){
		try {
			this.remote = Naming.lookup(RmiPort.PATH+"8004/FinancialStaffBillStateRemoteObject");
		}catch (RemoteException e) {
			e.printStackTrace();
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (NotBoundException e) {
			e.printStackTrace();
		}
	}
	
	public static FinancialStaffBillStateServiceLinker getInstance(){
		if(linker==null){
			linker=new FinancialStaffBillStateServiceLinker();
		}
		return linker;
	}
	
	public FinancialStaffBillStateBlService getFinancialStaffBillStateBlService(){
		return (FinancialStaffBillStateBlService)remote;
	}
}
