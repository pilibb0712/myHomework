package dataDrivers.customerDataDrivers;

import dataService.customerDataService.CustomerDataService;
import po.CustomerPO;

public class CustomerDataService_Driver {

	public void Drive(CustomerDataService customerDataService){
		
		CustomerPO po=new CustomerPO("i00001", "in", 'A', "customer", "10010", "No.1, Road A", "000000", "customer@customer.com", 1000, 500, 0, "people");
		customerDataService.init();
		System.out.println("init succeed");
		customerDataService.insert(po);
		System.out.println("insert succeed");
		po.setLevel('B');
		customerDataService.update(po);
		System.out.println("update succeed");
		CustomerPO result=customerDataService.find("i00001");
		if(result.getLevel()=='B') {
			System.out.println("find succeed");
		}
		customerDataService.delete("i00001", "customer");
		System.out.println("delete succeed");
		customerDataService.finish();
		System.out.println("succeed");
	}
}
