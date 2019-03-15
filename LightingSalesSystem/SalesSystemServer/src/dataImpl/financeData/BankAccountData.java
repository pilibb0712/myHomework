package dataImpl.financeData;

import java.io.File;
import java.util.ArrayList;
import assistant.exception.AccountNameExistException;
import assistant.utility.ReadAndWriteBankAccountPOSerUtility;
import dataService.financeDataService.BankAccountDataService;
import po.BankAccountPO;

/**
 * 银行账户相关操作数据库实现
 * @author guxinyu
 * @version 12.30
 *
 */
public class BankAccountData implements BankAccountDataService {
	private static final String BANK_ACCOUNT_ROOTPATH="data"+File.separator+"bankAccounts";
	
	@Override
	public boolean insert(BankAccountPO toInsert) throws AccountNameExistException{
		File[] accountList=list();
		
		for(int i=0;i<accountList.length;i++){
			if(toInsert.getAccountName().equals(accountList[i].getName())){
				throw new AccountNameExistException();//账户名已存在
			}
		}
		
		String path=BANK_ACCOUNT_ROOTPATH+File.separator+toInsert.getAccountName();
		if(ReadAndWriteBankAccountPOSerUtility.writeObj(toInsert, path))
			return true;
		else
			return false;//序列化不成功
	}

	@Override
	public ArrayList<BankAccountPO> readBankAccounts(){
		File[] accountList=list();
		
		ArrayList<BankAccountPO> accounts=new ArrayList<BankAccountPO>();
		for(int i=0;i<accountList.length;i++){
			String accountPath=BANK_ACCOUNT_ROOTPATH+File.separator+accountList[i].getName();
			BankAccountPO each=ReadAndWriteBankAccountPOSerUtility.readObj(accountPath);
			accounts.add(each);
		}
		
		return accounts;
	}

	@Override
	public boolean updateName(String oldName, String newName) throws AccountNameExistException{
		File[] accountList=list();
		
		for(int i=0;i<accountList.length;i++){
			if(accountList[i].getName().equals(newName)){
				throw new AccountNameExistException();
			}
			
			if(accountList[i].getName().equals(oldName)){
				String oldPath=BANK_ACCOUNT_ROOTPATH+File.separator+oldName;
				BankAccountPO oldPO=ReadAndWriteBankAccountPOSerUtility.readObj(oldPath);
				double sum=oldPO.getRemainingSum();
				accountList[i].delete();
				
				String newPath=BANK_ACCOUNT_ROOTPATH+File.separator+newName;
				BankAccountPO newPO=new BankAccountPO(newName, sum);
				if(ReadAndWriteBankAccountPOSerUtility.writeObj(newPO, newPath))
					return true;
				else
					return false;//序列化不成功
			}
		}
		return false;//被修改的银行账户不存在（理论上不会出现这种情况）
	}

	@Override
	public boolean updateMoney(String name, double change) {
		File[] accountList=list();
		
		for(int i=0;i<accountList.length;i++){
			if(accountList[i].getName().equals(name)){
				String path=BANK_ACCOUNT_ROOTPATH+File.separator+name;
				BankAccountPO oldPO=ReadAndWriteBankAccountPOSerUtility.readObj(path);
				double sum=oldPO.getRemainingSum()+change;
				accountList[i].delete();
				
				BankAccountPO newPO=new BankAccountPO(name, sum);
				if(ReadAndWriteBankAccountPOSerUtility.writeObj(newPO, path))
					return true;
				else
					return false;//序列化不成功
			}
		}
		return false;//被修改的银行账户不存在（理论上不会出现这种情况）
	}

	@Override
	public boolean delete(String toDelete) {
		File[] accountList=list();
		
		for(int i=0;i<accountList.length;i++){
			if(accountList[i].getName().equals(toDelete)){
				if(accountList[i].delete())
					return true;
				else 
					return false;//删除不成功
			}
		}
		
		return false;//要被删除的银行账户不存在
	}

	private File[] list(){
		File accountRoot=new File(BANK_ACCOUNT_ROOTPATH);
		File[] list=accountRoot.listFiles();
		
		return list;
	}
}
