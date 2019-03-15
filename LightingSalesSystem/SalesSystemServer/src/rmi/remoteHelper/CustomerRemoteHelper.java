/**
 * 
 */
package rmi.remoteHelper;

import rmi.register.customerRegister.CustomerRegister;

/**
 * @author cosx
 *
 */
public class CustomerRemoteHelper {

	private CustomerRegister customerRegister=new CustomerRegister();
	
	public CustomerRemoteHelper() {
		customerRegister.register();
	}
	
}
