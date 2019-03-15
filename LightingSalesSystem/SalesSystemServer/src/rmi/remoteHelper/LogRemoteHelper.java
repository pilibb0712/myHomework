/**
 * 
 */
package rmi.remoteHelper;

import rmi.register.logRegister.LogRegister;

/**
 * @author cosx
 *
 */
public class LogRemoteHelper {

	private LogRegister logRegister=new LogRegister();
	
	public LogRemoteHelper() {
		logRegister.register();
	}
}
