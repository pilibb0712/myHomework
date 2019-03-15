package rmi.linker.listServiceLinker;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.Remote;
import java.rmi.RemoteException;
import blService.listBlService.BusinessProcessListBlService;
import rmi.config.RmiPort;

public class BusinessProcessListServiceLinker {
	private static BusinessProcessListServiceLinker linker;
	private Remote remote;
	
	private BusinessProcessListServiceLinker(){
		setRemote();
	}
	
	private void setRemote(){
		try {
			this.remote = Naming.lookup(RmiPort.PATH+"8011/BusinessProcessListRemoteObject");
		}catch (RemoteException e) {
			e.printStackTrace();
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (NotBoundException e) {
			e.printStackTrace();
		}
	}
	
	public static BusinessProcessListServiceLinker getInstance(){
		if(linker==null){
			linker=new BusinessProcessListServiceLinker();
		}
		return linker;
	}
	
	public BusinessProcessListBlService getBusinessProcessListBlService(){
		return (BusinessProcessListBlService)remote;
	}
}
