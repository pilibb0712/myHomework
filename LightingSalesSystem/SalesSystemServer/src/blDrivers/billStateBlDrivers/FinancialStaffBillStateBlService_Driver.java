package blDrivers.billStateBlDrivers;

import static org.junit.Assert.assertEquals;

import java.rmi.RemoteException;

import org.junit.Test;

import assistant.type.BillCategoryEnum;
import assistant.type.BillStateEnum;
import assistant.type.UserPositionEnum;
import blImpl.bill.billStateBl.FinancialStaffBillStateBlController;
import blService.billService.billStateBlService.FinancialStaffBillStateBlService;
import vo.UserInfoVO;

public class FinancialStaffBillStateBlService_Driver {
	FinancialStaffBillStateBlService service=new FinancialStaffBillStateBlController();
	
	@Test
	public void test_getPurchaseBillList() throws RemoteException{
		assertEquals(BillCategoryEnum.PURCHASE_BILL, service.getPurchaseBillList(BillStateEnum.DONE));
	}
	
	@Test
	public void test_getPurchaseReturnBillList() throws RemoteException{
		assertEquals(BillCategoryEnum.PURCHASE_RETURN_BILL, service.getPurchaseReturnBillList(BillStateEnum.DONE));
	}
	
	@Test
	public void test_getSalesBillList() throws RemoteException{
		assertEquals(BillCategoryEnum.SALES_BILL, service.getSalesBillList(BillStateEnum.DONE));
	}
	
	@Test
	public void test_getSalesReturnBillList() throws RemoteException{
		assertEquals(BillCategoryEnum.SALES_RETURN_BILL, service.getSalesReturnBillList(BillStateEnum.DONE));
	}
	
	@Test
	public void test_getCashExpenseBillList() throws RemoteException{
		assertEquals(BillCategoryEnum.CASH_EXPENSE_BILL, service.getCashExpenseBillList(BillStateEnum.DONE, new UserInfoVO("00003","",UserPositionEnum.FINANCE)));
	}
	
	@Test
	public void test_getPaymentBillList() throws RemoteException{
		assertEquals(BillCategoryEnum.PAYMENT_BILL, service.getPaymentBillList(BillStateEnum.DONE, new UserInfoVO("00003","",UserPositionEnum.FINANCE)));
	}
	
	@Test
	public void test_getReceiptBillList() throws RemoteException{
		assertEquals(BillCategoryEnum.RECEIPT_BILL, service.getReceiptBillList(BillStateEnum.DONE, new UserInfoVO("00003","",UserPositionEnum.FINANCE)));
	}
}
