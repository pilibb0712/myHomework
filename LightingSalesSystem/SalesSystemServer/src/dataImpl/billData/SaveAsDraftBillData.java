package dataImpl.billData;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;

import assistant.type.BillCategoryEnum;
import assistant.utility.Date;
import assistant.utility.ReadAndWriteTxtUtility;
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
 * @version 2017.11.21
 * 将单据储存为草稿状态
 * 若已经存在该草稿，则覆盖上次内容
 */
public class SaveAsDraftBillData implements SaveAsDraftBillDataService{

	private static final String FILE_EXTENSION = ".ser";
	private static final String ROOT_PATH = "data"+File.separator+"bill"+File.separator;//根目录地址
	private static final String STATE_PATH = "draft"+File.separator;//对应的状态文件夹地址
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
	 * 创建一张新的库存赠送单草稿。<br/>
	 * 若已经存在该草稿，则覆盖上次内容
	 * @param GiftBillPO bill 需要新建的单据 
	 * @return boolean 是否增加成功
	 */
	@Override
	public boolean saveAsDraftGiftBill(GiftBillPO bill) {
		String path = GIFT_BILL_PATH+bill.getId()+".ser";//当前文件要存的位置
		return writeObj(bill, path);
	}

	/**
	 * 创建一张新的库存报溢单。<br/>
	 * 若已经存在该草稿，则覆盖上次内容
	 * @param InventoryOverBillPO bill 需要新建的单据 
	 * @return boolean 是否增加成功
	 */
	@Override
	public boolean saveAsDraftInventoryOverBill(InventoryOverBillPO bill) {
		String path = INVENTORY_OVER_BILL_PATH+bill.getId()+".ser";//当前文件要存的位置
		return writeObj(bill, path);
	}

	/**
	 * 创建一张新的库存报警单。<br/>
	 * 若已经存在该草稿，则覆盖上次内容
	 * @param InventoryWarningBillPO bill 需要新建的单据 
	 * @return boolean 是否增加成功
	 */
	@Override
	public boolean saveAsDraftInventoryWarningBill(InventoryWarningBillPO bill) {
		String path = INVENTORY_WARNING_BILL_PATH+bill.getId()+".ser";//当前文件要存的位置
		return writeObj(bill, path);
	}

	/**
	 * 创建一张新的库存报损单。<br/>
	 * 若已经存在该草稿，则覆盖上次内容
	 * @param InventoryLossBillPO bill 需要新建的单据 
	 * @return boolean 是否增加成功
	 */
	@Override
	public boolean saveAsDraftInventoryLossBill(InventoryLossBillPO bill) {
		String path = INVENTORY_LOSS_BILL_PATH+bill.getId()+".ser";//当前文件要存的位置
		return writeObj(bill, path);
	}

	/**
	 * 创建一张新的进货单。<br/>
	 * 若已经存在该草稿，则覆盖上次内容
	 * @param PurchaseBillPO bill 需要新建的单据 
	 * @return boolean 是否增加成功
	 */
	@Override
	public boolean saveAsDraftPurchaseBill(PurchaseBillPO bill) {
		String path = PURCHASE_BILL_PATH+bill.getId()+".ser";//当前文件要存的位置
		return writeObj(bill, path);
	}

	/**
	 * 创建一张新的进货退货单。<br/>
	 * 若已经存在该草稿，则覆盖上次内容
	 * @param PurchaseReturnBillPO bill 需要新建的单据 
	 * @return boolean 是否增加成功
	 */
	@Override
	public boolean saveAsDraftPurchaseReturnBill(PurchaseReturnBillPO bill) {
		String path =  PURCHASE_RETURN_BILL_PATH+bill.getId()+".ser";//当前文件要存的位置
		return writeObj(bill, path);
	}

	/**
	 * 创建一张新的销售单。<br/>
	 * 若已经存在该草稿，则覆盖上次内容
	 * @param SalesBillPO bill 需要新建的单据 
	 * @return boolean 是否增加成功
	 */
	@Override
	public boolean saveAsDraftSalesBill(SalesBillPO bill) {
		String path = SALES_BILL_PATH+bill.getId()+".ser";//当前文件要存的位置
		return writeObj(bill, path);
	}

	/**
	 * 创建一张新的销售退货单。<br/>
	 * 若已经存在该草稿，则覆盖上次内容
	 * @param SalesReturnBillPO bill 需要新建的单据 
	 * @return boolean 是否增加成功
	 */
	@Override
	public boolean saveAsDraftSalesReturnBill(SalesReturnBillPO bill) {
		String path = SALES_RETURN_BILL+bill.getId()+".ser";//当前文件要存的位置
		return writeObj(bill, path);
	}

	/**
	 * 创建一张新的收款单。<br/>
	 * 若已经存在该草稿，则覆盖上次内容
	 * @param ReceiptBillPO bill 需要新建的单据 
	 * @return boolean 是否增加成功
	 */
	@Override
	public boolean saveAsDraftReceiptBill(ReceiptBillPO bill) {
		String path = RECEIPT_BILL_PATH+bill.getId()+".ser";//当前文件要存的位置
		return writeObj(bill, path);
	}

	/**
	 * 创建一张新的付款单。<br/>
	 * 若已经存在该草稿，则覆盖上次内容
	 * @param PaymentBillPO bill 需要新建的单据 
	 * @return boolean 是否增加成功
	 */
	@Override
	public boolean saveAsDraftPaymentBill(PaymentBillPO bill) {
		String path = PAYMENT_BILL_PATH+bill.getId()+".ser";//当前文件要存的位置	
		return writeObj(bill, path);
	}

	/**
	 * 创建一张新的现金费用单。<br/>
	 * 若已经存在该草稿，则覆盖上次内容
	 * @param CashExpenseBillPO bill 需要新建的单据 
	 * @return boolean 是否增加成功
	 */
	@Override
	public boolean saveAsDraftCashExpenseBill(CashExpenseBillPO bill) {
		String path = CASH_EXPENSE_BILL_PATH+bill.getId()+".ser";//当前文件要存的位置
		return writeObj(bill, path);
	}
	
	/**
	 * 写bill的ser文件。<br/>
	 * @param GiftBillPO bill 需要新建的单据 
	 * @return boolean 是写文件成功
	 */
	private boolean writeObj(BillPO bill,String path){
		ObjectOutputStream oStream ;
//		System.out.println(path);
		try {
			oStream = new ObjectOutputStream(new FileOutputStream(path));
			oStream.writeObject(bill);
			oStream.close();
			return true;
		} catch (IOException e) {
			e.printStackTrace();
			return false;
		}
	}
}
