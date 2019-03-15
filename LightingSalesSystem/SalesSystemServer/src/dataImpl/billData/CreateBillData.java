package dataImpl.billData;

import java.io.File;

import assistant.type.BillCategoryEnum;
import assistant.utility.ReadAndWriteBillPOSerUtility;
import dataService.billDataService.CreateBillDataService;
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
 * @version 2017.11.9
 * 创建单据
 */

public class CreateBillData implements CreateBillDataService{
	//private static final int BILL_ID_LENGTH=5;//单据编号的长度，现在为5.即KCZSD-20171108-00001中00001部分
	
	private static final String ROOT_PATH = "data"+File.separator+"bill"+File.separator;//根目录地址
	private static final String STATE_PATH = "tbd"+File.separator;//对应的状态文件夹地址
	public static final String CREATE_RECORD = "create_record.txt" ;
	private static final String GIFT_BILL_PATH = ROOT_PATH +BillCategoryEnum.GIFT_BILL.toString()+File.separator+STATE_PATH ;//库存赠送单的地址
	private static final String CASH_EXPENSE_BILL_PATH = ROOT_PATH +BillCategoryEnum.CASH_EXPENSE_BILL.toString()+File.separator+STATE_PATH ;//现金费用单的地址
	private static final String INVENTORY_LOSS_BILL_PATH = ROOT_PATH +BillCategoryEnum.INVENTORY_LOSS_BILL.toString()+File.separator+STATE_PATH ;//库存报损单的地址
	private static final String INVENTORY_OVER_BILL_PATH = ROOT_PATH +BillCategoryEnum.INVENTORY_OVER_BILL.toString()+File.separator+STATE_PATH ;//库存报溢单的地址
	private static final String INVENTORY_WARNING_BILL_PATH = ROOT_PATH +BillCategoryEnum.INVENTORY_WARNING_BILL.toString()+File.separator+STATE_PATH ;//库存报警单的地址
	private static final String PAYMENT_BILL_PATH = ROOT_PATH +BillCategoryEnum.PAYMENT_BILL.toString()+File.separator+STATE_PATH ;//付款单的地址
	private static final String PURCHASE_BILL_PATH = ROOT_PATH +BillCategoryEnum.PURCHASE_BILL.toString()+File.separator+STATE_PATH ;//进货单的地址
	private static final String PURCHASE_RETURN_BILL_PATH = ROOT_PATH +BillCategoryEnum.PURCHASE_RETURN_BILL.toString()+File.separator+STATE_PATH ;//进货退货单的地址
	private static final String RECEIPT_BILL_PATH = ROOT_PATH +BillCategoryEnum.RECEIPT_BILL.toString()+File.separator+STATE_PATH ;//收款单的地址
	private static final String SALES_BILL_PATH = ROOT_PATH +BillCategoryEnum.SALES_BILL.toString()+File.separator+STATE_PATH ;//销售单的地址
	private static final String SALES_RETURN_BILL = ROOT_PATH +BillCategoryEnum.SALES_RETURN_BILL.toString()+File.separator+STATE_PATH ;//销售退货单的地址
	
	/**
	 * 创建一张新的库存赠送单。<br/>
	 * @param GiftBillPO bill 需要新建的单据 
	 * @return boolean 是否增加成功
	 */
	@Override
	public boolean createGiftBill(GiftBillPO bill) {
		String path = GIFT_BILL_PATH+bill.getId()+".ser";//当前文件要存的位置
		return writeObj(bill, path);
	}

	/**
	 * 创建一张新的库存报溢单。<br/>
	 * @param InventoryOverBillPO bill 需要新建的单据 
	 * @return boolean 是否增加成功
	 */
	@Override
	public boolean createInventoryOverBill(InventoryOverBillPO bill) {
		String path = INVENTORY_OVER_BILL_PATH+bill.getId()+".ser";//当前文件要存的位置
		return writeObj(bill, path);
	}

	/**
	 * 创建一张新的库存报警单。<br/>
	 * @param InventoryWarningBillPO bill 需要新建的单据 
	 * @return boolean 是否增加成功
	 */
	@Override
	public boolean createInventoryWarningBill(InventoryWarningBillPO bill) {
		String path = INVENTORY_WARNING_BILL_PATH+bill.getId()+".ser";//当前文件要存的位置
		return writeObj(bill, path);
	}

	/**
	 * 创建一张新的库存报损单。<br/>
	 * @param InventoryLossBillPO bill 需要新建的单据 
	 * @return boolean 是否增加成功
	 */
	@Override
	public boolean createInventoryLossBill(InventoryLossBillPO bill) {
		String path = INVENTORY_LOSS_BILL_PATH+bill.getId()+".ser";//当前文件要存的位置
		return writeObj(bill, path);
	}

	/**
	 * 创建一张新的进货单。<br/>
	 * @param PurchaseBillPO bill 需要新建的单据 
	 * @return boolean 是否增加成功
	 */
	@Override
	public boolean createPurchaseBill(PurchaseBillPO bill) {
		String path = PURCHASE_BILL_PATH+bill.getId()+".ser";//当前文件要存的位置
		return writeObj(bill, path);
	}

	/**
	 * 创建一张新的进货退货单。<br/>
	 * @param PurchaseReturnBillPO bill 需要新建的单据 
	 * @return boolean 是否增加成功
	 */
	@Override
	public boolean createPurchaseReturnBill(PurchaseReturnBillPO bill) {
		String path =  PURCHASE_RETURN_BILL_PATH+bill.getId()+".ser";//当前文件要存的位置
		return writeObj(bill, path);
	}

	/**
	 * 创建一张新的销售单。<br/>
	 * @param SalesBillPO bill 需要新建的单据 
	 * @return boolean 是否增加成功
	 */
	@Override
	public boolean createSalesBill(SalesBillPO bill) {
		String path = SALES_BILL_PATH+bill.getId()+".ser";//当前文件要存的位置
		return writeObj(bill, path);
	}

	/**
	 * 创建一张新的销售退货单。<br/>
	 * @param SalesReturnBillPO bill 需要新建的单据 
	 * @return boolean 是否增加成功
	 */
	@Override
	public boolean createSalesReturnBill(SalesReturnBillPO bill) {
		String path = SALES_RETURN_BILL+bill.getId()+".ser";//当前文件要存的位置
		return writeObj(bill, path);
	}

	/**
	 * 创建一张新的收款单。<br/>
	 * @param ReceiptBillPO bill 需要新建的单据 
	 * @return boolean 是否增加成功
	 */
	@Override
	public boolean createReceiptBill(ReceiptBillPO bill) {
		String path = RECEIPT_BILL_PATH+bill.getId()+".ser";//当前文件要存的位置
		return writeObj(bill, path);
	}

	/**
	 * 创建一张新的付款单。<br/>
	 * @param PaymentBillPO bill 需要新建的单据 
	 * @return boolean 是否增加成功
	 */
	@Override
	public boolean createPaymentBill(PaymentBillPO bill) {
		String path = PAYMENT_BILL_PATH+bill.getId()+".ser";//当前文件要存的位置
		return writeObj(bill, path);
	}

	/**
	 * 创建一张新的现金费用单。<br/>
	 * @param CashExpenseBillPO bill 需要新建的单据 
	 * @return boolean 是否增加成功
	 */
	@Override
	public boolean createCashExpenseBill(CashExpenseBillPO bill) {
		String path = CASH_EXPENSE_BILL_PATH+bill.getId()+".ser";//当前文件要存的位置
		return writeObj(bill, path);
	}
	
	/**
	 * 写bill的ser文件。<br/>
	 * @param GiftBillPO bill 需要新建的单据 
	 * @return boolean 是写文件成功
	 */
	private boolean writeObj(BillPO bill,String path){
		return ReadAndWriteBillPOSerUtility.writeObj(bill, path);
	}
}
