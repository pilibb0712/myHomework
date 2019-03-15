package blDrivers.billBlDrivers;

import blImpl.bill.billBl.CashExpenseBillBl;
import blInteract.billBlInteract.BillBlInteractServiceFactory;
import blInteract.billBlInteract.CashExpenseBillBlService;
import po.CashExpenseBillPO;

public class CashExpenseBl_Driver {
	BillBlInteractServiceFactory factory = new BillBlInteractServiceFactory();
	private CashExpenseBillBlService service= factory.getCashExpenseBillBlService();
	
	private void driveSaveAsDraftBill(){
		CashExpenseBillPO po= new CashExpenseBillPO();
		//po.setId("XJFYD_2017-12-29_21_37_25");
		service.saveAsDraftBill(po);
	}
	
	
	public static void main(String [] args){
		CashExpenseBl_Driver driver = new CashExpenseBl_Driver();
		driver.driveSaveAsDraftBill();
	}
}
