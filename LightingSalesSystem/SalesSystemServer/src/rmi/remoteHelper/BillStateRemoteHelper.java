package rmi.remoteHelper;

import rmi.register.billStateRegister.FinancialStaffBillStateRegister;
import rmi.register.billStateRegister.SalesmanBillStateRegister;
import rmi.register.billStateRegister.WareKeeperBillStateRegister;

public class BillStateRemoteHelper {
	private FinancialStaffBillStateRegister financialStaffBillStateRegister=new FinancialStaffBillStateRegister();
	private SalesmanBillStateRegister salesmanBillStateRegister=new SalesmanBillStateRegister();
	private WareKeeperBillStateRegister wareKeeperBillStateRegister=new WareKeeperBillStateRegister();
	
	public BillStateRemoteHelper(){
		financialStaffBillStateRegister.register();
		salesmanBillStateRegister.register();
		wareKeeperBillStateRegister.register();
	}
}
