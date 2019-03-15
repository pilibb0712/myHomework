package blDrivers.financeBlDrivers;

import java.rmi.RemoteException;
import java.util.ArrayList;

import assistant.exception.AccountNameExistException;
import blImpl.financeBl.BankAccountBlController;
import blInteract.financeInteract.BankAccountSumUpdateBlService;
import blService.financeBlService.BankAccountBlService;
import vo.BankAccountVO;

/**
 * 
 * @author guxinyu
 * @version 2017.12.2
 *
 */
public class BankAccountBlService_Driver {
	public static BankAccountBlService service=new BankAccountBlController();
	public static BankAccountSumUpdateBlService sumService=new BankAccountBlController();
	
	public static void main(String[] args){
		try {
			test_newBankAccount();
			//test_viewBankAccounts();
			//test_updateAccount();
			//test_viewBankAccounts();
			//test_deleteAccount();
			//test_updateRemainingSum();
			test_viewBankAccounts();
		} catch (RemoteException e) {
			e.printStackTrace();
		} catch (AccountNameExistException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * T220500
	 */
	public static void test_newBankAccount() throws RemoteException, AccountNameExistException{
		BankAccountVO vo=new BankAccountVO("account4",5641.2);
		if(service.newBankAccount(vo))
			System.out.println("Successfully created.");
		else
			System.out.println("Fail to create.");
	}
	
	/**
	 * T220600
	 */
	public static void test_viewBankAccounts() throws RemoteException{
		ArrayList<BankAccountVO> accounts=new ArrayList<BankAccountVO>();
		
		accounts=service.viewBankAccounts();
		
		for(int i=0;i<accounts.size();i++){
			System.out.println(accounts.get(i).getAccountName()+"  "
					+accounts.get(i).getRemainingSum());
		}
	}
	
	/**
	 * T220700
	 */
	public static void test_updateAccount() throws RemoteException, AccountNameExistException{
		if(service.updateAccount("account3", "account3.1"))
			System.out.println("Name successfully updated.");
		else
			System.out.println("Fail to update name.");
	}
	
	/**
	 * T220800
	 */
	public static void test_deleteAccount() throws RemoteException{
		if(service.deleteAccount("account2"))
			System.out.println("Successfully deleted.");
		else
			System.out.println("Fail to delete.");
	}
	
	/**
	 * T220900
	 */
	public static void test_updateRemainingSum() throws RemoteException{
		if(sumService.updateRemainingSum("account1.1", 565))
			System.out.println("Money successfully updated.");
		else
			System.out.println("Fail to update Money.");
	}
}
