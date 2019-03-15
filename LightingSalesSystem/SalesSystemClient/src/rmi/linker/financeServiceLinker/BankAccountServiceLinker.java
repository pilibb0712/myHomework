package rmi.linker.financeServiceLinker;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.Remote;
import java.rmi.RemoteException;

import blService.financeBlService.BankAccountBlService;
import rmi.config.RmiPort;

public class BankAccountServiceLinker {
	private static BankAccountServiceLinker linker;
	private Remote remote;
	
	private BankAccountServiceLinker(){
		setRemote();
	}
	
	private void setRemote(){
		try {
			this.remote = Naming.lookup(RmiPort.PATH+"8001/BankAccountRemoteObject");
		}catch (RemoteException e) {
			e.printStackTrace();
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (NotBoundException e) {
			e.printStackTrace();
		}
	}
	
	public static BankAccountServiceLinker getInstance(){
		if(linker==null){
			linker=new BankAccountServiceLinker();
		}
		return linker;
	}
	
	public BankAccountBlService getBankAccountBlService(){
		return (BankAccountBlService)remote;
	}
}

