/**
 * 
 */
package rmi.linker.logServiceLinker;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.Remote;
import java.rmi.RemoteException;

import blService.logBlService.LogBlService;
import rmi.config.RmiPort;

/**
 * @author ÍõÄþÒ»
 *
 */
public class LogServiceLinker {
	private static LogServiceLinker linker;
	private Remote remote;
	
	private LogServiceLinker() {
		setRemote();
	}

	/**
	 * 
	 */
	private void setRemote() {
		try {
			this.remote=Naming.lookup(RmiPort.PATH+"9002/LogRemoteObject");
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (RemoteException e) {
			e.printStackTrace();
		} catch (NotBoundException e) {
			e.printStackTrace();
		}		
	}
	
	public static LogServiceLinker getInstance() {
		if(linker==null) {
			linker=new LogServiceLinker();
		}
		return linker;
	}
	
	public LogBlService getLogBlService() {
		return (LogBlService)remote;
	}
	
}
