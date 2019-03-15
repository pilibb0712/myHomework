package test.billBlTest;

import assistant.type.BillCategoryEnum;
import assistant.type.UserPositionEnum;
import blImpl.bill.billBl.GiftBillBl;
import po.GiftBillPO;
import po.UserInfoPO;

public class GiftBillBlSetUp {
	static final BillCategoryEnum CATEGORY_ENUM = BillCategoryEnum.GIFT_BILL;
	GiftBillBl testBl = new GiftBillBl();//需要测试的逻辑对象
	public void setUp(){
		GiftBillPO bill1 = new GiftBillPO();
		GiftBillPO bill2 = new GiftBillPO();
		GiftBillPO bill3 = new GiftBillPO();
		UserInfoPO userInfoPO = new UserInfoPO();
		userInfoPO.setId("12345");
		userInfoPO.setName("张傲");
		userInfoPO.setUserPositionEnum(UserPositionEnum.MANAGER);
		bill1.setCreater(userInfoPO);
		bill1.setExecutor(userInfoPO);
		bill2.setCreater(userInfoPO);
		bill2.setExecutor(userInfoPO);
		bill3.setCreater(userInfoPO);
		bill3.setExecutor(userInfoPO);

		testBl.createBill(bill1);
		testBl.createBill(bill2);
		testBl.createBill(bill3);
	}
}
