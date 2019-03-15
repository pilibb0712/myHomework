package blDrivers.approveBillBlDrivers;

import static org.junit.Assert.assertEquals;

import java.rmi.RemoteException;

import org.junit.Before;
import org.junit.Test;

import assistant.type.BillCategoryEnum;
import assistant.type.BillStateEnum;
import blImpl.bill.approveBillBl.ApproveGiftBillBlController;
import blInteract.billBlInteract.BillBlInteractServiceFactory;
import blInteract.billBlInteract.GiftBillBlService;
import blService.billService.approveBillBlService.ApproveGiftBillBlService;
import po.GiftBillPO;
import vo.GiftBillVO;

public class ApproveGiftBillBlService_Driver {
	private ApproveGiftBillBlService service =  new ApproveGiftBillBlController();
	private GiftBillBlService billBlService = new BillBlInteractServiceFactory().getGiftBillBlService();
	private String testPassBillId ;
	private String testDenyBillId ;
	@Before
	public void setUp() throws Exception {
		GiftBillPO bill1 = new GiftBillPO();
		bill1.setApproverComment("testPass");
		billBlService.createBill(bill1);
		
		GiftBillPO bill2 = new GiftBillPO();
		bill2.setApproverComment("testDeny");
		billBlService.createBill(bill2);
		
		for(GiftBillPO giftBill: billBlService.getBillListByState(BillStateEnum.TBD)){
			if(giftBill.getApproverComment()==null){continue;}
			if(giftBill.getApproverComment().equals("testPass")){
				testPassBillId = giftBill.getId();
			}
			if(giftBill.getApproverComment().equals("testDeny")){
				testDenyBillId= giftBill.getId();
			}
		}
	}

	@Test
	public void drivePassBill() throws RemoteException {
		GiftBillVO bill = new GiftBillVO();
		bill.setId(testPassBillId);
		bill.setBillStateEnum(BillStateEnum.TBD);
		bill.setCategoryEnum(BillCategoryEnum.GIFT_BILL);
		assertEquals(true, service.passBill(bill));
		assertEquals(BillStateEnum.TODO,billBlService.getBillById(testPassBillId).getBillStateEnum());
	}
	
	@Test
	public void driveDenyBill() throws RemoteException{
		GiftBillVO bill = new GiftBillVO();
		bill.setId(testDenyBillId);
		bill.setBillStateEnum(BillStateEnum.TBD);
		bill.setCategoryEnum(BillCategoryEnum.GIFT_BILL);
		assertEquals(true, service.denyBill(bill));
		assertEquals(BillStateEnum.DENIED,billBlService.getBillById(testDenyBillId).getBillStateEnum());
	}
		
}
