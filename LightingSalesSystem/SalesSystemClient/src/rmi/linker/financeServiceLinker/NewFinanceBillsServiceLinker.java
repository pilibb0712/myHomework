package rmi.linker.financeServiceLinker;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.Remote;
import java.rmi.RemoteException;

import blService.financeBlService.NewFinanceBillsBlService;
import rmi.config.RmiPort;

public class NewFinanceBillsServiceLinker {
	private static NewFinanceBillsServiceLinker linker;
	private Remote remote;
	
	private NewFinanceBillsServiceLinker(){
		setRemote();
	}
	
	private void setRemote(){
		try {
			this.remote = Naming.lookup(RmiPort.PATH+"8003/NewFinanceBillsRemoteObject");
		}catch (RemoteException e) {
			e.printStackTrace();
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (NotBoundException e) {
			e.printStackTrace();
		}
	}
	
	public static NewFinanceBillsServiceLinker getInstance(){
		if(linker==null){
			linker=new NewFinanceBillsServiceLinker();
		}
		return linker;
	}
	
	public NewFinanceBillsBlService getNewFinanceBillsBlService(){
		return (NewFinanceBillsBlService)remote;
	}
}
