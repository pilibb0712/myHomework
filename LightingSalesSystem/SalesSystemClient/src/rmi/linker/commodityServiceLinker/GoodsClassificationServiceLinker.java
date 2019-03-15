package rmi.linker.commodityServiceLinker;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.Remote;
import java.rmi.RemoteException;


import blService.commodityBlService.GoodsClassificationBlService;
import rmi.config.RmiPort;

public class GoodsClassificationServiceLinker {
	private static GoodsClassificationServiceLinker linker;
	private Remote remote;

	private GoodsClassificationServiceLinker(){
		setRemote();
	}

	private void setRemote(){
		try {
			this.remote = Naming.lookup(RmiPort.PATH+"6004/GoodsClassificationRemoteObject");
		}catch (RemoteException e) {
			e.printStackTrace();
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (NotBoundException e) {
			e.printStackTrace();
		}
	}

	public static GoodsClassificationServiceLinker getInstance(){
		if(linker==null){
			linker=new GoodsClassificationServiceLinker();
		}
		return linker;
	}

	public GoodsClassificationBlService getGoodsClassificationBlService(){
		return (GoodsClassificationBlService)remote;
	}
}
