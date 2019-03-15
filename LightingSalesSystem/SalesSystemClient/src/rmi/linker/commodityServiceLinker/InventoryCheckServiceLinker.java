package rmi.linker.commodityServiceLinker;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.Remote;
import java.rmi.RemoteException;

import blService.commodityBlService.GoodsClassificationBlService;
import blService.commodityBlService.InventoryCheckBlService;

public class InventoryCheckServiceLinker {
	private static InventoryCheckServiceLinker linker;
	private Remote remote;

	private InventoryCheckServiceLinker(){
		setRemote();
	}

	private void setRemote(){
		try {
			this.remote = Naming.lookup("rmi://127.0.0.1:6006/InventoryCheckRemoteObject");
		}catch (RemoteException e) {
			e.printStackTrace();
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (NotBoundException e) {
			e.printStackTrace();
		}
	}

	public static InventoryCheckServiceLinker getInstance(){
		if(linker==null){
			linker=new InventoryCheckServiceLinker();
		}
		return linker;
	}

	public InventoryCheckBlService getInventoryCheckBlService(){
		return (InventoryCheckBlService)remote;
	}
}
