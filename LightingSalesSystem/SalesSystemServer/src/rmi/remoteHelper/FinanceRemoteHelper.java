package rmi.remoteHelper;

import rmi.register.financeRegister.BankAccountRegister;
import rmi.register.financeRegister.InOutRegister;
import rmi.register.financeRegister.NewFinanceBillsRegister;

public class FinanceRemoteHelper {
	private BankAccountRegister bankAccountRegister=new BankAccountRegister();
	private InOutRegister inOutRegister=new InOutRegister();
	private NewFinanceBillsRegister newFinanceBillsRegister=new NewFinanceBillsRegister();
	
	public FinanceRemoteHelper(){
		bankAccountRegister.register();
		inOutRegister.register();
		newFinanceBillsRegister.register();
	}
	
}
