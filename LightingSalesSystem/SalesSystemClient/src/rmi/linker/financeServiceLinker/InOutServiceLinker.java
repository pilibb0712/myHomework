package rmi.linker.financeServiceLinker;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.Remote;
import java.rmi.RemoteException;

import blService.financeBlService.InOutBlService;
import rmi.config.RmiPort;

public class InOutServiceLinker {
	private static InOutServiceLinker linker;
	private Remote remote;
	
	private InOutServiceLinker(){
		setRemote();
	}
	
	private void setRemote(){
		try {
			this.remote = Naming.lookup(RmiPort.PATH+"8002/InOutRemoteObject");
		}catch (RemoteException e) {
			e.printStackTrace();
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (NotBoundException e) {
			e.printStackTrace();
		}
	}
	
	public static InOutServiceLinker getInstance(){
		if(linker==null){
			linker=new InOutServiceLinker();
		}
		return linker;
	}
	
	public InOutBlService getInOutBlService(){
		return (InOutBlService)remote;
	}
}
