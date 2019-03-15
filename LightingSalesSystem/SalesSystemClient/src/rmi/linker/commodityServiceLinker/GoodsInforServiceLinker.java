package rmi.linker.commodityServiceLinker;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.Remote;
import java.rmi.RemoteException;

import blService.commodityBlService.GoodsClassificationBlService;
import blService.commodityBlService.GoodsInforBlService;
import rmi.config.RmiPort;

public class GoodsInforServiceLinker {
	private static GoodsInforServiceLinker linker;
	private Remote remote;

	private GoodsInforServiceLinker(){
		setRemote();
	}

	private void setRemote(){
		try {
			this.remote = Naming.lookup(RmiPort.PATH+"6005/GoodsInforRemoteObject");
		}catch (RemoteException e) {
			e.printStackTrace();
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (NotBoundException e) {
			e.printStackTrace();
		}
	}

	public static GoodsInforServiceLinker getInstance(){
		if(linker==null){
			linker=new GoodsInforServiceLinker();
		}
		return linker;
	}

	public GoodsInforBlService getGoodsInforBlService(){
		return (GoodsInforBlService)remote;
	}
}
