package blInteract.financeInteract;

import blImpl.financeBl.BankAccountBlController;
import blImpl.financeBl.InOutBlController;

/**
 * 财务类逻辑层内交互接口工厂
 * @author guxinyu
 * @version 2017.12.23
 *
 */
public class FinanceBlFactory {
	public BankAccountSumUpdateBlService getBankAccountSumUpdateBlService(){
		BankAccountSumUpdateBlService service=new BankAccountBlController();
		
		return service;
	}
	
	public AutoAddEntryService getAutoAddEntryService(){
		AutoAddEntryService service=new InOutBlController();
		
		return service;
	}
}
