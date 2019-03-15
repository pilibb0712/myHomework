package rmi.linker.commodityServiceLinker;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.Remote;
import java.rmi.RemoteException;

import blService.commodityBlService.StockCheckBlService;
import rmi.config.RmiPort;

public class StockCheckServiceLinker {
	private static StockCheckServiceLinker linker;
	private Remote remote;

	private StockCheckServiceLinker(){
		setRemote();
	}

	private void setRemote(){
		try {
			this.remote = Naming.lookup(RmiPort.PATH+"6008/StockCheckRemoteObject");
		}catch (RemoteException e) {
			e.printStackTrace();
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (NotBoundException e) {
			e.printStackTrace();
		}
	}

	public static StockCheckServiceLinker getInstance(){
		if(linker==null){
			linker=new StockCheckServiceLinker();
		}
		return linker;
	}

	public StockCheckBlService getStockCheckBlService(){
		return (StockCheckBlService)remote;
	}
}
