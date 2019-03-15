package rmi.linker.promotionServiceLinker;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.Remote;
import java.rmi.RemoteException;

import blService.promotionBlService.ComboStrategyBlService;
import rmi.config.RmiPort;

public class ComboStrategyServiceLinker {
	private static ComboStrategyServiceLinker linker ;
	private Remote remote ;
	
	private ComboStrategyServiceLinker(){
		setRemote();
	}
	
	/**
	 * °ó¶¨¶Ë¿ÚÄÃµ½remote
	 * */
	private void setRemote(){
		try {
			this.remote = Naming.lookup(RmiPort.PATH+"7100/ComboStrategyRemoteObject");
			System.out.println("linked");
		}catch (RemoteException e) {
			e.printStackTrace();
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (NotBoundException e) {
			e.printStackTrace();
		}
	}
	
	public static ComboStrategyServiceLinker getInstance(){
		if(linker==null){
			linker = new ComboStrategyServiceLinker();
		}
		return linker;
	}
	
	public ComboStrategyBlService  getComboStrategyBlService(){
		return (ComboStrategyBlService)remote;
	}
	
}
