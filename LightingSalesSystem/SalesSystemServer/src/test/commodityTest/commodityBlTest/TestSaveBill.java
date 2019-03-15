package test.commodityTest.commodityBlTest;

import java.rmi.RemoteException;

import assistant.type.BillCategoryEnum;
import assistant.type.UserPositionEnum;
import blImpl.commodityBl.SaveBillController;
import vo.InventoryLossBillVO;
import vo.InventoryOverBillVO;
import vo.InventoryWarningBillVO;
import vo.UserInfoVO;

public class TestSaveBill {
public void testSaveInventoryWarningBill(){
	try{
		SaveBillController service=new SaveBillController();
		InventoryWarningBillVO vo=new InventoryWarningBillVO();
		UserInfoVO creator=new UserInfoVO();
		creator.setId("0001");
		creator.setName("zbb");
		creator.setUserPositionEnum(UserPositionEnum.WAREKEPPER);

		vo.setCreater(creator);
		vo.setCategoryEnum(BillCategoryEnum.INVENTORY_WARNING_BILL);
        vo.setGoodsID("G01010101");
        vo.setGoodsName("¾­µä±´±´µÆ");
        vo.setGoodsType("T0001");
        vo.setGoodsPrice("100");
        vo.setSystemAmount("80");
        vo.setWarningAmount("100");
        vo.setLossAmount("20");

        service.saveInventoryWarningBill(vo);

	}catch(RemoteException e){
		e.printStackTrace();
	}
}
public void testSaveInventoryLossBill(){
	try{
		SaveBillController service=new SaveBillController();

		InventoryLossBillVO vo=new InventoryLossBillVO();
		UserInfoVO creator=new UserInfoVO();
		creator.setId("0001");
		creator.setName("zbb");
		creator.setUserPositionEnum(UserPositionEnum.WAREKEPPER);

		vo.setCreater(creator);
		vo.setCategoryEnum(BillCategoryEnum.INVENTORY_LOSS_BILL);
        vo.setGoodsID("G01010101");
        vo.setGoodsName("¾­µä±´±´µÆ");
        vo.setGoodsType("T0001");
        vo.setGoodsPrice("100");
        vo.setSystemAmount("80");
        vo.setRealAmount("60");
        vo.setLossAmount("20");

        service.saveInventoryLossBill(vo);
	}catch(RemoteException e){
		e.printStackTrace();
	}
}
public void testSaveInventoryOverBill(){
	try{
		SaveBillController service=new SaveBillController();

		InventoryOverBillVO vo=new InventoryOverBillVO();
		UserInfoVO creator=new UserInfoVO();
		creator.setId("0001");
		creator.setName("zbb");
		creator.setUserPositionEnum(UserPositionEnum.WAREKEPPER);

		vo.setCreater(creator);
		vo.setCategoryEnum(BillCategoryEnum.INVENTORY_OVER_BILL);
        vo.setGoodsID("G01010101");
        vo.setGoodsName("¾­µä±´±´µÆ");
        vo.setGoodsType("T0001");
        vo.setGoodsPrice("100");
        vo.setSystemAmount("80");
        vo.setRealAmount("100");
        vo.setOverAmount("20");

        service.saveInventoryOverBill(vo);
	}catch(RemoteException e){
		e.printStackTrace();
	}
}
public static void main(String[] args){
	TestSaveBill test=new TestSaveBill();
	//test.testSaveInventoryWarningBill();
	//test.testSaveInventoryLossBill();
	test.testSaveInventoryOverBill();
}
}
