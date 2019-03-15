package blImpl.bill.billBl;

import assistant.type.BillCategoryEnum;
import assistant.utility.Date;
import dataImpl.billData.SetBillIdData;
import dataService.billDataService.SetBillIdDataService;
import po.BillPO;

/**
 * 设置单据Id的一个单件模式的一个逻辑类
 * @author zhangao 161250193
 * @version 2017.11.29
 * 得到单据
 */
public class SetBillIdBl {
	
	
	SetBillIdDataService setBillIdDataService = new SetBillIdData();
	private static SetBillIdBl setBillIdBl;//该类的单件对象
	
	//构造函数
	private SetBillIdBl(){
		
	}
	
	/**
	 * 取得单件对象引用的接口
	 */
	public static SetBillIdBl getInstance(){
		if(setBillIdBl==null){
			setBillIdBl = new SetBillIdBl();
		}
		return setBillIdBl;
	}
	
	/**
	 * @param BillPO bill需要设置Id的单据
	 * @return 是否设置成功
	 */
	public boolean setBillIdAndUpdateRecord(BillPO bill){
		boolean isSuccessedSet = false; 
		setBillIdDataService.setBillId(bill);//是否设置成功
		isSuccessedSet = setBillIdDataService.updateRecord(bill);//是否设置成功
		return isSuccessedSet;
	}
	
	/**
	 * 生成draft模式下的bill的Id。<br/>
	 * Id的格式为 单据名称+用户账号+“-”+创建年月日+小时+分钟+秒 例如：KCZSD_12345-20170206-02-44-15
	 * @param GiftBillPO bill 需要设定Id的单据 
	 * @return void
	 */
	public void setDraftBillId(BillPO bill){

		if(!(bill.getId()==null||bill.getId().trim().equals(""))){return;}//已经设置过Id,为已存在草稿
		String Id="";
		BillCategoryEnum category = bill.getCategoryEnum();
		Date createDate = bill.getCreateDate();
		Id += category.toBillIdPart()+"_";
		Id += createDate.getBillDraftIdPart();
		bill.setId(Id);
		
	}
	
}
