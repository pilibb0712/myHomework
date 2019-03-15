package blDrivers.billBlDrivers;


import java.util.ArrayList;

import assistant.type.BillCategoryEnum;
import assistant.type.BillStateEnum;
import assistant.type.UserPositionEnum;
import assistant.utility.Date;
import blImpl.bill.billBl.GiftBillBl;
import blInteract.billBlInteract.GiftBillBlService;
import po.GiftBillPO;
import po.GiftPO;
import po.UserInfoPO;

public class GiftBillBl_Driver {
	
	static final BillCategoryEnum CATEGORY_ENUM = BillCategoryEnum.GIFT_BILL;
	GiftBillBl testBl = new GiftBillBl();//需要测试的逻辑对象
	
	
	/**
	 * 驱动测试创建新单据
	 * */
	public void driveGiftCreateBill(){
		GiftBillPO bill = new GiftBillPO();
		bill.setCreateDate(new Date());
		bill.setCategoryEnum(CATEGORY_ENUM);
		ArrayList<GiftPO> gifts = new ArrayList<>();
		GiftPO po = new GiftPO();
		po.setId("465");
		po.setName("a灯");
		po.setPrice(1000);
		po.setGiftAmount(100);
		gifts.add(po);
		bill.setGifts(gifts);
		UserInfoPO userInfo = new UserInfoPO();
		userInfo.setId("12345");
		userInfo.setName("zaa");
		userInfo.setUserPositionEnum(UserPositionEnum.MANAGER);
		bill.setCreater(userInfo);

		bill.setCategoryEnum(CATEGORY_ENUM);
		testBl.createBill(bill);
	}
	
	/**
	 * 驱动测试查询单据
	 * */
	public void driveGetBill(){
		String billId ;
		GiftBillPO billPO;
		
		billId = "KCZSD-20171228-00006";
		billPO = testBl.getBillById(billId);
		System.out.println(billPO.getId());
		System.out.println(billPO.getBillStateEnum());
		System.out.println(billPO.getGifts().get(0).getName());
//		
//		billId = "KCZSD-20171130-00002";
//		billPO = testBl.getBillById(billId);
//		System.out.println(billPO.getId());
//		
//		billId = "KCZSD-20171130-00003";
//		billPO = testBl.getBillById(billId);
//		System.out.println(billPO.getCreaterComment());
	}

	/**
	 * 驱动测试将单据储存为草稿
	 * */
	public void driveSaveAsDraftBill(){
		GiftBillPO bill = new GiftBillPO();
		
		UserInfoPO userInfo = new UserInfoPO();
		userInfo.setId("12345");
		userInfo.setName("za");
		userInfo.setUserPositionEnum(UserPositionEnum.MANAGER);
		bill.setCreater(userInfo);
		
		bill.setCategoryEnum(CATEGORY_ENUM);
		bill.setCreaterComment("测试用草稿单据7");
		testBl.saveAsDraftBill(bill);
		
		//输出部分
//		String billId ;
//		GiftBillPO billPO;
//		billId = "KCZSD_2017-11-30_11_56_39";
//		billPO = testBl.getBillById(billId);
//		System.out.println(billPO.getCreaterComment());
	}
	
	/**
	 * 驱动测试更新草稿单据
	 * */
	public void driveUpdateDraftBill(){
		String billId = "KCZSD_2017-11-30_11_56_39";
		GiftBillPO billPO = testBl.getBillById(billId);
		billPO.setCreaterComment("update1测试用草稿单据3");
		testBl.updateDraftBill(billPO);
		
		billId = "KCZSD_2017-11-30_11_56_39";
		billPO = testBl.getBillById(billId);
		System.out.println(billPO.getCreaterComment());
	}
	
	/**
	 * 驱动测试删除草稿单据
	 * */
	public void driveDeleteDraftBill(){
		String billId = "KCZSD_2017-11-30_11_56_39";
		testBl.deleteDraftBill(billId);
	}
	
	/**
	 * 驱动测试提交草稿单据
	 * */
	public void driveSubmitBill(){
		String billId = "KCZSD_2017-11-30_11_56_21";
		testBl.submitBill(billId);
	}
	
	/**
	 * 驱动测试pass单据
	 * */
	public void drivePassBill(){
		//String billId= "KCZSD-20171201-00005";
		//testBl.passBill(billId);
	}
	
	/**
	 * 驱动测试deny单据
	 * */
	public void driveDenyBill(){
		//String billId= "KCZSD-20171201-00006";
		//testBl.denyBill(billId);
	}
	
	/**
	 * 驱动测试done单据
	 * */
	public void driveDoneBill(){
		//String billId= "KCZSD-20171201-00007";
		//testBl.doneBill(billId);
	}
	
	/**
	 * 驱动测试hide单据
	 * */
	public void driveHideBill(){
		//String billId= "KCZSD-20171201-00008";
		//testBl.hideBill(billId);
	}
	
	/**
	 * 驱动测试通过状态查询单据
	 * */
	public void driveGetBillListByState(){
		BillStateEnum billState = BillStateEnum.TBD;
		ArrayList<GiftBillPO> billPOs = testBl.getBillListByState(billState);
		for(GiftBillPO billPO: billPOs){
			System.out.println(billPO.getId());
		}
	}
	
	public void driveGetDraftBillsListByCreater(){
		//ArrayList<GiftBillPO> billPOs=testBl.getDraftBillsListByCreater("za","12345",UserPositionEnum.MANAGER);
		//for(GiftBillPO billPO:billPOs){
		//	System.out.println(billPO.getId());
	//	}
	//	System.out.println("----------------------------------------");
	}
	
	public void driveGetDeniedBillsListByCreater(){
//		ArrayList<GiftBillPO> billPOs=testBl.getDeniedBillsListByCreater("za","12345",UserPositionEnum.MANAGER);
//		for(GiftBillPO billPO:billPOs){
//			System.out.println(billPO.getId());
//		}
//		System.out.println("----------------------------------------");
	}
	
	public void driveGetDoneBillsListByExecutor(){
//		ArrayList<GiftBillPO> billPOs=testBl.getDoneBillsListByExecutor("za","12345",UserPositionEnum.MANAGER);
//		for(GiftBillPO billPO:billPOs){
//			System.out.println(billPO.getId());
//		}
//		System.out.println("----------------------------------------");
	}
	
	public void driveGetTBDBillsListByCreater(){
//		ArrayList<GiftBillPO> billPOs=testBl.getTBDBillsListByCreater("za","12345",UserPositionEnum.MANAGER);
//		for(GiftBillPO billPO:billPOs){
//			System.out.println(billPO.getId());
//		}
//		System.out.println("----------------------------------------");
	}
	
	public void driveGetToDoBillsListByExecutor(){
//		ArrayList<GiftBillPO> billPOs=testBl.getToDoBillsListByExecutor("za","12345",UserPositionEnum.MANAGER);
//		for(GiftBillPO billPO:billPOs){
//			System.out.println(billPO.getId());
//		}
//		System.out.println("----------------------------------------");
	}
	
	public static void main(String [] args){
		GiftBillBl_Driver giftBillBl_Driver = new GiftBillBl_Driver();
		giftBillBl_Driver.driveGiftCreateBill();
		//giftBillBl_Driver.driveGetBill();
		//giftBillBl_Driver.driveSaveAsDraftBill();
		//giftBillBl_Driver.driveUpdateDraftBill();
		//giftBillBl_Driver.driveDeleteDraftBill();
		//giftBillBl_Driver.driveSubmitBill();
		//giftBillBl_Driver.drivePassBill();
		//giftBillBl_Driver.driveDenyBill();
		//giftBillBl_Driver.driveHideBill();
		//giftBillBl_Driver.driveDoneBill();
		//giftBillBl_Driver.driveGetBillListByState();
		//giftBillBl_Driver.driveGetDeniedBillsListByCreater();
		//giftBillBl_Driver.driveGetDoneBillsListByExecutor();
		//giftBillBl_Driver.driveGetDraftBillsListByCreater();
		//giftBillBl_Driver.driveGetTBDBillsListByCreater();
		//giftBillBl_Driver.driveGetToDoBillsListByExecutor();
		
	}
}
