/**
 * 
 */
package rmi.remoteHelper;

import blService.customerBlService.CustomerBlService;
import rmi.linker.customerServiceLinker.CustomerServiceLinker;

/**
 * @author ÍõÄþÒ»
 *
 */
public class CustomerRemoteHelper {

	private static CustomerRemoteHelper remoteHelper=new CustomerRemoteHelper();
	
	private CustomerRemoteHelper() {
		
	}
	
	public static CustomerRemoteHelper getInstance() {
		return remoteHelper;
	}
	
	public CustomerBlService getCustomerBlService() {
		return CustomerServiceLinker.getInstance().getCustomerBlService();
	}
	
}
