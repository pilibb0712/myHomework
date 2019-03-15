package dataStubs.customerDataStubs;

import dataService.customerDataService.CustomerDataService;
import po.CustomerPO;

public class CustomerDataService_Stub implements CustomerDataService {

	@Override
	public void insert(CustomerPO po) {
		// TODO Auto-generated method stub
		System.out.println("insert succeed!");

	}

	@Override
	public void delete(String number, String name) {
		// TODO Auto-generated method stub
		System.out.println("delete succeed!");

	}

	@Override
	public void update(CustomerPO po){
		// TODO Auto-generated method stub
		System.out.println("update succeed!");
	}

	@Override
	public CustomerPO find(String name){
		// TODO Auto-generated method stub
		System.out.println("find succeed!");
		CustomerPO po=new CustomerPO("","",'A',"","","","","",50.0,50.0,50.0,"");
		return po;
	}

	@Override
	public void init() {
		// TODO Auto-generated method stub
		System.out.println("init succeed!");

	}

	@Override
	public void finish(){
		// TODO Auto-generated method stub
		System.out.println("finish succeed!");

	}

}
