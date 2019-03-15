package rmi.remoteHelper;

import rmi.register.viewBillRegister.ViewFinanceStaffBillRegister;
import rmi.register.viewBillRegister.ViewGiftBillRegister;
import rmi.register.viewBillRegister.ViewSalesmanBillRegister;
import rmi.register.viewBillRegister.ViewWareKeeperBillRegister;

public class ViewBillRemoteHelper {
	private ViewFinanceStaffBillRegister viewFinanceStaffBillRegister=new ViewFinanceStaffBillRegister();
	private ViewSalesmanBillRegister viewSalesmanBillRegister=new ViewSalesmanBillRegister();
	private ViewWareKeeperBillRegister viewWareKeeperBillRegister=new ViewWareKeeperBillRegister();
	private ViewGiftBillRegister viewGiftBillRegister=new ViewGiftBillRegister();
	
	public ViewBillRemoteHelper(){
		viewFinanceStaffBillRegister.register();
		viewSalesmanBillRegister.register();
		viewWareKeeperBillRegister.register();
		viewGiftBillRegister.register();
	}
}
