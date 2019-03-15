package blImpl.financeBl;

import java.rmi.RemoteException;
import java.util.ArrayList;
import assistant.convertors.BankAccountPOVOConvertor;
import assistant.exception.AccountNameExistException;
import blInteract.financeInteract.BankAccountSumUpdateBlService;
import blService.financeBlService.BankAccountBlService;
import po.BankAccountPO;
import vo.BankAccountVO;

/**
 * 银行账户相关操作的控制模块
 * @author guxinyu
 * @version 2017.12.30
 *
 */
public class BankAccountBlController implements BankAccountBlService,BankAccountSumUpdateBlService{
	private BankAccountBl bl=BankAccountBl.getInstance();
	
	@Override
	public boolean newBankAccount(BankAccountVO toSave) throws RemoteException, AccountNameExistException{
		BankAccountPO toSavePO=BankAccountPOVOConvertor.bankAccountVOtoPO(toSave);
		
		if(bl.saveAccount(toSavePO))
			return true;
		else 
			return false;
	}

	@Override
	public ArrayList<BankAccountVO> viewBankAccounts() throws RemoteException {
		ArrayList<BankAccountPO> accountsPO=bl.getAccounts();
		
		ArrayList<BankAccountVO> accountsVO=new ArrayList<BankAccountVO>();
		for(int i=0;i<accountsPO.size();i++){
			BankAccountVO each=BankAccountPOVOConvertor.bankAccountPOtoVO(accountsPO.get(i));
			accountsVO.add(each);
		}
		return accountsVO;
	}

	@Override
	public boolean updateAccount(String oldAccountName, String newAccountName) throws RemoteException, AccountNameExistException{
		if(bl.updateAccount(oldAccountName, newAccountName))
			return true;
		else
			return false;
	}

	@Override
	public boolean deleteAccount(String toDelete) throws RemoteException {
		if(bl.deleteAccount(toDelete))
			return true;
		else
			return false;
	}

	@Override
	public boolean updateRemainingSum(String accountName, double change){
		if(bl.updateRemainingSum(accountName, change))
			return true;
		else
			return false;
	}
}
