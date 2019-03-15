package dataDrivers.billDataDrivers;

import java.util.ArrayList;

import assistant.type.BillCategoryEnum;
import assistant.type.BillStateEnum;
import assistant.utility.Date;
import dataImpl.billData.GetBillData;
import dataImpl.billData.SaveAsDraftBillData;
import dataService.billDataService.GetBillDataService;
import dataService.billDataService.SaveAsDraftBillDataService;
import po.BillPO;
import po.GiftBillPO;

/**
 * @author zhangao 161250193
 * @version 2017.11.21
 * 将单据储存为草稿状态的驱动
 */

public class SaveAsDraftBillDataService_Driver {
	
	private SaveAsDraftBillDataService saveAsDraftBillDataService
		=new SaveAsDraftBillData();
	
	/**
	 * 驱动测试储存一张库存赠送单草稿。<br/>
	 */
	public void driveSaveDraftGiftBill(){
		GiftBillPO giftBillPO = new GiftBillPO();
		giftBillPO.setCreateDate(new Date());
		giftBillPO.setCategoryEnum(BillCategoryEnum.GIFT_BILL);
		giftBillPO.setCreaterComment("这是一张新的草稿");
		setBillId(giftBillPO);
		saveAsDraftBillDataService.saveAsDraftGiftBill(giftBillPO);
		
//		GetBillDataService getBillDataService = new GetBillData();
//		ArrayList<BillPO> bills;
//		bills=getBillDataService.getBillListByStateAndName(BillStateEnum.DRAFT.toString(), "gift_bill");
//		GiftBillPO giftBillPO2 = (GiftBillPO)bills.get(0);
//		System.out.println(giftBillPO2.getCreaterComment());
//		giftBillPO2.setCreaterComment("覆盖后的草稿");
//		saveAsDraftBillDataService.saveAsDraftGiftBill(giftBillPO2);
	}

	
	public static void main(String []args){
		SaveAsDraftBillDataService_Driver saveAsDraftBillDataService_Driver
			= new SaveAsDraftBillDataService_Driver();
		saveAsDraftBillDataService_Driver.driveSaveDraftGiftBill();;
	}
	
	/**
	 * 生成draft模式下的bill的Id。<br/>
	 * Id的格式为 单据名称+用户账号+“-”+创建年月日+小时+分钟+秒 例如：KCZSD_12345-20170206-02-44-15
	 * @param GiftBillPO bill 需要设定Id的单据 
	 * @return void
	 */
	private void setBillId(BillPO bill){
		
		if(bill.getId()!=null){return;}//已经设置过Id,为已存在草稿
		String Id="";
		BillCategoryEnum category = bill.getCategoryEnum();
		Date createDate = bill.getCreateDate();
		Id += category.toBillIdPart()+"_";
		Id += createDate.getBillDraftIdPart();
		bill.setId(Id);
	}
}
