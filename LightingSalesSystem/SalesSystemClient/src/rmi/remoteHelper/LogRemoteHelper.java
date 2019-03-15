/**
 * 
 */
package rmi.remoteHelper;

import blService.logBlService.LogBlService;
import rmi.linker.logServiceLinker.LogServiceLinker;

/**
 * @author ÍõÄþÒ»
 *
 */
public class LogRemoteHelper {
	private static LogRemoteHelper remoteHelper=new LogRemoteHelper();
	
	private LogRemoteHelper() {
		
	}
	
	public static LogRemoteHelper getInstance() {
		return remoteHelper;
	}
	
	public LogBlService getLogBlService() {
		return LogServiceLinker.getInstance().getLogBlService();
	}
	
}
