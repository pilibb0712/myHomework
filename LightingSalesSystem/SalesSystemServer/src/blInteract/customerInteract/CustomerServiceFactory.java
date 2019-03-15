package blInteract.customerInteract;

import blImpl.customerBl.CustomerBlController;

public class CustomerServiceFactory {
	public GetCustomerService getGetCustomerService(){
		GetCustomerService service=new CustomerBlController();
		return service;
	}
	
	public ModifyCustomerMoneyService getModifyCustomerMoneyService(){
		ModifyCustomerMoneyService service=new CustomerBlController();
		return service;
	}
}
