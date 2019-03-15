package blImpl.bill.billBl;

import assistant.type.BillCategoryEnum;
import assistant.utility.Date;
import dataImpl.billData.SaveAsDraftBillData;
import dataService.billDataService.SaveAsDraftBillDataService;
import po.BillPO;
import po.CashExpenseBillPO;
import po.GiftBillPO;
import po.InventoryLossBillPO;
import po.InventoryOverBillPO;
import po.InventoryWarningBillPO;
import po.PaymentBillPO;
import po.PurchaseBillPO;
import po.PurchaseReturnBillPO;
import po.ReceiptBillPO;
import po.SalesBillPO;
import po.SalesReturnBillPO;

/**
 * @author zhangao 161250193
 * @version 2017.11.28
 * 将单据储存为草稿状态
 * 若已经存在该草稿，则覆盖上次内容
 */

public class SaveAsDraftBillBl {
	
	 private SaveAsDraftBillDataService saveAsDraftBillDataService = new SaveAsDraftBillData();//数据层对象
	
	 
	/**
	 * 创建一张新的库存赠送单草稿。<br/>
	 * 若已经存在该草稿，则覆盖上次内容
	 * @param GiftBillPO bill 需要新建的单据 
	 * @return boolean 是否增加成功
	 */
	public boolean saveAsDraftGiftBill(GiftBillPO bill) {
		setBillId(bill);
		return saveAsDraftBillDataService.saveAsDraftGiftBill(bill);
	}

	/**
	 * 创建一张新的库存报溢单。<br/>
	 * 若已经存在该草稿，则覆盖上次内容
	 * @param InventoryOverBillPO bill 需要新建的单据 
	 * @return boolean 是否增加成功
	 */

	public boolean saveAsDraftInventoryOverBill(InventoryOverBillPO bill) {
		setBillId(bill);
		return saveAsDraftBillDataService.saveAsDraftInventoryOverBill(bill);
	}

	/**
	 * 创建一张新的库存报警单。<br/>
	 * 若已经存在该草稿，则覆盖上次内容
	 * @param InventoryWarningBillPO bill 需要新建的单据 
	 * @return boolean 是否增加成功
	 */

	public boolean saveAsDraftInventoryWarningBill(InventoryWarningBillPO bill) {
		setBillId(bill);
		return saveAsDraftBillDataService.saveAsDraftInventoryWarningBill(bill);
	}

	/**
	 * 创建一张新的库存报损单。<br/>
	 * 若已经存在该草稿，则覆盖上次内容
	 * @param InventoryLossBillPO bill 需要新建的单据 
	 * @return boolean 是否增加成功
	 */

	public boolean saveAsDraftInventoryLossBill(InventoryLossBillPO bill) {
		setBillId(bill);
		return saveAsDraftBillDataService.saveAsDraftInventoryLossBill(bill);
	}

	/**
	 * 创建一张新的进货单。<br/>
	 * 若已经存在该草稿，则覆盖上次内容
	 * @param PurchaseBillPO bill 需要新建的单据 
	 * @return boolean 是否增加成功
	 */

	public boolean saveAsDraftPurchaseBill(PurchaseBillPO bill) {
		setBillId(bill);
		return saveAsDraftBillDataService.saveAsDraftPurchaseBill(bill);
	}

	/**
	 * 创建一张新的进货退货单。<br/>
	 * 若已经存在该草稿，则覆盖上次内容
	 * @param PurchaseReturnBillPO bill 需要新建的单据 
	 * @return boolean 是否增加成功
	 */

	public boolean saveAsDraftPurchaseReturnBill(PurchaseReturnBillPO bill) {
		setBillId(bill);
		return saveAsDraftBillDataService.saveAsDraftPurchaseReturnBill(bill);
	}

	/**
	 * 创建一张新的销售单。<br/>
	 * 若已经存在该草稿，则覆盖上次内容
	 * @param SalesBillPO bill 需要新建的单据 
	 * @return boolean 是否增加成功
	 */

	public boolean saveAsDraftSalesBill(SalesBillPO bill) {
		setBillId(bill);
		return saveAsDraftBillDataService.saveAsDraftSalesBill(bill);
	}

	/**
	 * 创建一张新的销售退货单。<br/>
	 * 若已经存在该草稿，则覆盖上次内容
	 * @param SalesReturnBillPO bill 需要新建的单据 
	 * @return boolean 是否增加成功
	 */

	public boolean saveAsDraftSalesReturnBill(SalesReturnBillPO bill) {
		setBillId(bill);
		return saveAsDraftBillDataService.saveAsDraftSalesReturnBill(bill);
	}

	/**
	 * 创建一张新的收款单。<br/>
	 * 若已经存在该草稿，则覆盖上次内容
	 * @param ReceiptBillPO bill 需要新建的单据 
	 * @return boolean 是否增加成功
	 */

	public boolean saveAsDraftReceiptBill(ReceiptBillPO bill) {
		setBillId(bill);
		return saveAsDraftBillDataService.saveAsDraftReceiptBill(bill);
	}

	/**
	 * 创建一张新的付款单。<br/>
	 * 若已经存在该草稿，则覆盖上次内容
	 * @param PaymentBillPO bill 需要新建的单据 
	 * @return boolean 是否增加成功
	 */

	public boolean saveAsDraftPaymentBill(PaymentBillPO bill) {
		setBillId(bill);
		return saveAsDraftBillDataService.saveAsDraftPaymentBill(bill);
	}

	/**
	 * 创建一张新的现金费用单。<br/>
	 * 若已经存在该草稿，则覆盖上次内容
	 * @param CashExpenseBillPO bill 需要新建的单据 
	 * @return boolean 是否增加成功
	 */

	public boolean saveAsDraftCashExpenseBill(CashExpenseBillPO bill) {
		setBillId(bill);
		return saveAsDraftBillDataService.saveAsDraftCashExpenseBill(bill);
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
