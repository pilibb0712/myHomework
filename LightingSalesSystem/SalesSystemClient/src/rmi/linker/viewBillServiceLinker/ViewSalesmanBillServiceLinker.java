package rmi.linker.viewBillServiceLinker;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.Remote;
import java.rmi.RemoteException;

import blService.billService.viewBillBlService.ViewSalsemanBillBlService;
import rmi.config.RmiPort;

public class ViewSalesmanBillServiceLinker {
	private static ViewSalesmanBillServiceLinker linker;
	private Remote remote;
	
	private ViewSalesmanBillServiceLinker(){
		setRemote();
	}
	
	private void setRemote(){
		try {
			this.remote = Naming.lookup(RmiPort.PATH+"8008/ViewSalesmanBillRemoteObject");
		}catch (RemoteException e) {
			e.printStackTrace();
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (NotBoundException e) {
			e.printStackTrace();
		}
	}
	
	public static ViewSalesmanBillServiceLinker getInstance(){
		if(linker==null){
			linker=new ViewSalesmanBillServiceLinker();
		}
		return linker;
	}
	
	public ViewSalsemanBillBlService getViewSalsemanBillBlService(){
		return (ViewSalsemanBillBlService)remote;
	}
}
