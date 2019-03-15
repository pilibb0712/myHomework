package blStubs.financeBlStubs;

import java.rmi.RemoteException;

import java.util.ArrayList;
import blService.financeBlService.BankAccountBlService;
import vo.BankAccountVO;

/**
 * 银行账户管理逻辑接口的桩
 * @author guxinyu
 * @version 2017.11.27
 *
 */
public class BankAccountBlService_Stub implements BankAccountBlService{

	@Override
	public boolean newBankAccount(BankAccountVO toSave) throws RemoteException {
		System.out.println(toSave.getAccountName());
		System.out.println(toSave.getRemainingSum());
		
		return true;
	}

	@Override
	public ArrayList<BankAccountVO> viewBankAccounts() throws RemoteException {
		BankAccountVO acc1=new BankAccountVO("account1",51651.0);
		BankAccountVO acc2=new BankAccountVO("account2",4541251.0);
		BankAccountVO acc3=new BankAccountVO("account3",45541.8);
		ArrayList<BankAccountVO> accounts=new ArrayList<BankAccountVO>();
		accounts.add(acc1);
		accounts.add(acc2);
		accounts.add(acc3);
		
		return accounts;
	}

	@Override
	public boolean updateAccount(String oldAccountName, String newAccountName) throws RemoteException {
		System.out.println(oldAccountName+" is replaced with "
				+newAccountName);
		return true;
	}

	@Override
	public boolean deleteAccount(String toDelete) throws RemoteException {
		System.out.println(toDelete+" is deleted.");
		return true;
	}

	public boolean updateRemainingSum(String accountName,double change) throws RemoteException {
		System.out.println("Remaining sum is updated.");	
		return true;
	}

}
