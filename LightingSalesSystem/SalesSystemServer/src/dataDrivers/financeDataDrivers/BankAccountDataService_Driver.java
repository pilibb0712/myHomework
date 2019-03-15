package dataDrivers.financeDataDrivers;

import java.util.ArrayList;

import dataImpl.financeData.BankAccountData;
import dataService.financeDataService.BankAccountDataService;
import po.BankAccountPO;

public class BankAccountDataService_Driver {
	public static BankAccountDataService service=new BankAccountData();
	
	public static void main(String[] args){
		//test_insert();
		//test_readBankAccounts();
		//test_updateName();
		test_delete();
		test_readBankAccounts();
		//test_updateMoney();
	}
	
	public static void test_insert(){
		BankAccountPO toInsert1=new BankAccountPO("account3",456.2);
	
		if(service.insert(toInsert1))
			System.out.println("Inserted successfully.");
		else
			System.out.println("Insert failed.");
	}
	
	public static void test_readBankAccounts(){
		ArrayList<BankAccountPO> accounts=new ArrayList<BankAccountPO>();
		
		accounts=service.readBankAccounts();
		
		for(int i=0;i<accounts.size();i++){
			System.out.println(accounts.get(i).getAccountName()+"  "
					+accounts.get(i).getRemainingSum());
		}
	}
	
	public static void test_updateName(){
		if(service.updateName("account1", "account1.1"))
			System.out.println("Name updated successfully.");
		else
			System.out.println("Name update failed.");
	}
	
	public static void test_updateMoney(){
		if(service.updateMoney("account2", -4561)){
			System.out.println("Money updated successfully.");
		}else
			System.out.println("Money update failed.");
		
	}
	
	public static void test_delete(){
		if(service.delete("account3"))
			System.out.println("Deleted successfully.");
		else
			System.out.println("Delete failed");
	}
}
