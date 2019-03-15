package rmi.linker.viewBillServiceLinker;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.Remote;
import java.rmi.RemoteException;
import blService.billService.viewBillBlService.ViewGiftBillBlService;
import rmi.config.RmiPort;

public class ViewGiftBillServiceLinker {
	private static ViewGiftBillServiceLinker linker;
	private Remote remote;
	
	private ViewGiftBillServiceLinker(){
		setRemote();
	}
	
	private void setRemote(){
		try {
			this.remote = Naming.lookup(RmiPort.PATH+"7007/ViewGiftBillRemoteObject");
		}catch (RemoteException e) {
			e.printStackTrace();
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (NotBoundException e) {
			e.printStackTrace();
		}
	}
	
	public static ViewGiftBillServiceLinker getInstance(){
		if(linker==null){
			linker=new ViewGiftBillServiceLinker();
		}
		return linker;
	}
	
	public ViewGiftBillBlService getViewGiftBillBlService(){
		return (ViewGiftBillBlService)remote;
	}
}
