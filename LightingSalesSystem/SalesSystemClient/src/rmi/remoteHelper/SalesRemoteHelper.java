/**
 * 
 */
package rmi.remoteHelper;

import blService.salesBlService.SalesBlService;
import rmi.linker.salesServiceLinker.SalesServiceLinker;

/**
 * @author ÍõÄþÒ»
 *
 */
public class SalesRemoteHelper {
	private static SalesRemoteHelper remoteHelper=new SalesRemoteHelper();
	
	private SalesRemoteHelper() {
		
	}
	
	public static SalesRemoteHelper getInstance() {
		return remoteHelper;
	}
	
	public SalesBlService getSalesBlService() {
		return SalesServiceLinker.getInstance().getSalesBlService();
	}
	
}
