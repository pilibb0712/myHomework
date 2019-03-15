package rmi.linker.listServiceLinker;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.Remote;
import java.rmi.RemoteException;
import blService.listBlService.SalesDetailsListBlService;
import rmi.config.RmiPort;

public class SalesDetailsListServiceLinker {
	private static SalesDetailsListServiceLinker linker;
	private Remote remote;
	
	private SalesDetailsListServiceLinker(){
		setRemote();
	}
	
	private void setRemote(){
		try {
			this.remote = Naming.lookup(RmiPort.PATH+"8012/SalesDetailsListRemoteObject");
		}catch (RemoteException e) {
			e.printStackTrace();
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (NotBoundException e) {
			e.printStackTrace();
		}
	}
	
	public static SalesDetailsListServiceLinker getInstance(){
		if(linker==null){
			linker=new SalesDetailsListServiceLinker();
		}
		return linker;
	}
	
	public SalesDetailsListBlService getSalesDetailsListBlService(){
		return (SalesDetailsListBlService)remote;
	}
}
