package rmi.linker.viewBillServiceLinker;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.Remote;
import java.rmi.RemoteException;
import blService.billService.viewBillBlService.ViewWareKeeperBillBlService;
import rmi.config.RmiPort;

public class ViewWareKeeperBillServiceLinker {
	private static ViewWareKeeperBillServiceLinker linker;
	private Remote remote;
	
	private ViewWareKeeperBillServiceLinker(){
		setRemote();
	}
	
	private void setRemote(){
		try {
			this.remote = Naming.lookup(RmiPort.PATH+"8009/ViewWareKeeperBillRemoteObject");
		}catch (RemoteException e) {
			e.printStackTrace();
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (NotBoundException e) {
			e.printStackTrace();
		}
	}
	
	public static ViewWareKeeperBillServiceLinker getInstance(){
		if(linker==null){
			linker=new ViewWareKeeperBillServiceLinker();
		}
		return linker;
	}
	
	public ViewWareKeeperBillBlService getViewWareKeeperBillBlService(){
		return (ViewWareKeeperBillBlService)remote;
	}
}
