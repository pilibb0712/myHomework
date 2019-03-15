  package rmi.linker.commodityServiceLinker;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.Remote;
import java.rmi.RemoteException;

import blService.commodityBlService.AddGoodsBlService;
import rmi.config.RmiPort;

public class AddGoodsServiceLinker {
	private static AddGoodsServiceLinker linker;
	private Remote remote;

	private AddGoodsServiceLinker(){
		setRemote();
	}

	private void setRemote(){
		try {
			this.remote = Naming.lookup(RmiPort.PATH+"6002/AddGoodsRemoteObject");
		}catch (RemoteException e) {
			e.printStackTrace();
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (NotBoundException e) {
			e.printStackTrace();
		}
	}

	public static AddGoodsServiceLinker getInstance(){
		if(linker==null){
			linker=new AddGoodsServiceLinker();
		}
		return linker;
	}

	public AddGoodsBlService getAddGoodsBlService(){
		return (AddGoodsBlService)remote;
	}
}
