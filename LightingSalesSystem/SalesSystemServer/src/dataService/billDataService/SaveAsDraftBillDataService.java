package dataService.billDataService;

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

public interface SaveAsDraftBillDataService {
     public boolean saveAsDraftGiftBill(GiftBillPO bill);
	 public boolean saveAsDraftInventoryOverBill(InventoryOverBillPO bill);
	 public boolean saveAsDraftInventoryWarningBill(InventoryWarningBillPO bill);
	 public boolean saveAsDraftInventoryLossBill(InventoryLossBillPO bill);
	 public boolean saveAsDraftPurchaseBill(PurchaseBillPO bill);
	 public boolean saveAsDraftSalesBill(SalesBillPO bill);
	 public boolean saveAsDraftSalesReturnBill(SalesReturnBillPO bill);
	 public boolean saveAsDraftReceiptBill(ReceiptBillPO bill);
	 public boolean saveAsDraftPaymentBill(PaymentBillPO bill);
	 public boolean saveAsDraftCashExpenseBill(CashExpenseBillPO bill);
	 public boolean saveAsDraftPurchaseReturnBill(PurchaseReturnBillPO bill);
}
