package rmi.linker.promotionServiceLinker;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.Remote;
import java.rmi.RemoteException;

import blService.promotionBlService.UserStrategyBlService;
import rmi.config.RmiPort;

public class UserStrategyServiceLinker {

	private static UserStrategyServiceLinker linker ;
	private Remote remote ;
	
	private UserStrategyServiceLinker(){
		setRemote();
	}
	
	/**
	 * °ó¶¨¶Ë¿ÚÄÃµ½remote
	 * */
	private void setRemote(){
		try {
			this.remote = Naming.lookup(RmiPort.PATH+"7101/UserStrategyRemoteObject");
			System.out.println("linked");
		}catch (RemoteException e) {
			e.printStackTrace();
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (NotBoundException e) {
			e.printStackTrace();
		}
	}
	
	public static UserStrategyServiceLinker getInstance(){
		if(linker==null){
			linker = new UserStrategyServiceLinker();
		}
		return linker;
	}
	
	public UserStrategyBlService  getUserStrategyBlService(){
		return (UserStrategyBlService )remote;
	}
	
}
