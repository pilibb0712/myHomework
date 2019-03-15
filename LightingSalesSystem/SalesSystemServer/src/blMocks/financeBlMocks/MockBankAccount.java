package blMocks.financeBlMocks;

import java.util.ArrayList;

import blImpl.financeBl.BankAccountBl;
import vo.BankAccountVO;

/**
 * BankAccountBlÀàµÄMock Object
 * @author guxinyu
 * @version 11.14
 *
 */
public class MockBankAccount extends BankAccountBl{
	public boolean saveAccount(BankAccountVO toSave){
		System.out.println(toSave.getAccountName());
		System.out.println(toSave.getRemainingSum());
		
		return true;
	}
	
	public ArrayList<BankAccountVO> getAccounts(){
		BankAccountVO acc1=new BankAccountVO("account1",51651.0);
		BankAccountVO acc2=new BankAccountVO("account2",4541251.0);
		BankAccountVO acc3=new BankAccountVO("account3",45541.8);
		ArrayList<BankAccountVO> accounts=new ArrayList<BankAccountVO>();
		accounts.add(acc1);
		accounts.add(acc2);
		accounts.add(acc3);
		
		return accounts;
	}
	
	public boolean updateAccount(String oldAccountName, String newAccountName){
		System.out.println(oldAccountName+" is replaced with "
				+newAccountName);
		return true;
	}
	
	public boolean deleteAccount(String toDelete){
		System.out.println(toDelete+" is deleted.");
		return true;
	}
	
	public boolean updateRemainingSum(String accountName, double change){
		System.out.println("Remaining sum is updated.");	
		return true;
	}
}
