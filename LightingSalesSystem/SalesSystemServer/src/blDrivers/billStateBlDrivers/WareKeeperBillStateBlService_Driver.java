package blDrivers.billStateBlDrivers;

import static org.junit.Assert.assertEquals;

import java.rmi.RemoteException;

import org.junit.Test;

import assistant.type.BillCategoryEnum;
import assistant.type.BillStateEnum;
import assistant.type.UserPositionEnum;
import blImpl.bill.billStateBl.WareKeeperBillStateBlController;
import blService.billService.billStateBlService.WareKeeperBillStateBlService;
import vo.UserInfoVO;

public class WareKeeperBillStateBlService_Driver {
	WareKeeperBillStateBlService service=new WareKeeperBillStateBlController();
	
	@Test
	public void test_getPurchaseBillList() throws RemoteException{
		assertEquals(BillCategoryEnum.PURCHASE_BILL, service.getPurchaseBillList(BillStateEnum.DONE, new UserInfoVO("00003","",UserPositionEnum.SALESMAN)));
	}
	
	@Test
	public void test_getPurchaseReturnBillList() throws RemoteException{
		assertEquals(BillCategoryEnum.PURCHASE_RETURN_BILL, service.getPurchaseReturnBillList(BillStateEnum.DONE, new UserInfoVO("00003","",UserPositionEnum.SALESMAN)));
	}
	
	@Test
	public void test_getSalesBillList() throws RemoteException{
		assertEquals(BillCategoryEnum.SALES_BILL, service.getSalesBillList(BillStateEnum.DONE, new UserInfoVO("00003","",UserPositionEnum.SALESMAN)));
	}
	
	@Test
	public void test_getSalesReturnBillList() throws RemoteException{
		assertEquals(BillCategoryEnum.SALES_RETURN_BILL, service.getSalesReturnBillList(BillStateEnum.DONE, new UserInfoVO("00003","",UserPositionEnum.SALESMAN)));
	}
}
