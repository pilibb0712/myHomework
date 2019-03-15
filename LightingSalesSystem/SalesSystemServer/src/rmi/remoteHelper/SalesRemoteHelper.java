/**
 * 
 */
package rmi.remoteHelper;

import rmi.register.salesRegister.SalesRegister;

/**
 * @author cosx
 *
 */
public class SalesRemoteHelper {

	private SalesRegister salesRegister=new SalesRegister();
	
	public SalesRemoteHelper() {
		salesRegister.register();
	}
}
