package rmi.remoteHelper;

import blService.financeBlService.BankAccountBlService;
import blService.financeBlService.InOutBlService;
import blService.financeBlService.NewFinanceBillsBlService;
import rmi.linker.financeServiceLinker.BankAccountServiceLinker;
import rmi.linker.financeServiceLinker.InOutServiceLinker;
import rmi.linker.financeServiceLinker.NewFinanceBillsServiceLinker;

public class FinanceRemoteHelper {
	private static FinanceRemoteHelper remoteHelper=new FinanceRemoteHelper();
	
	private FinanceRemoteHelper(){
		
	}
	
	public static FinanceRemoteHelper getInstance(){
		return remoteHelper;
	}
	
	public BankAccountBlService getBankAccountBlService(){
		return BankAccountServiceLinker.getInstance().getBankAccountBlService();
	}
	
	public InOutBlService getInOutBlService(){
		return InOutServiceLinker.getInstance().getInOutBlService();
	}
	
	public NewFinanceBillsBlService getNewFinanceBillsBlService(){
		return NewFinanceBillsServiceLinker.getInstance().getNewFinanceBillsBlService();
	}
}
