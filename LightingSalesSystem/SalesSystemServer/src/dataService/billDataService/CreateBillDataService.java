package dataService.billDataService;

import po.*;

public interface CreateBillDataService{


	 public boolean createGiftBill(GiftBillPO bill);
	 public boolean createInventoryOverBill(InventoryOverBillPO bill);
	 public boolean createInventoryWarningBill(InventoryWarningBillPO bill);
	 public boolean createInventoryLossBill(InventoryLossBillPO bill);
	 public boolean createPurchaseBill(PurchaseBillPO bill);
	 public boolean createPurchaseReturnBill(PurchaseReturnBillPO bill);
	 public boolean createSalesBill(SalesBillPO bill);
	 public boolean createSalesReturnBill(SalesReturnBillPO bill);
	 public boolean createReceiptBill(ReceiptBillPO bill);
	 public boolean createPaymentBill(PaymentBillPO bill);
	 public boolean createCashExpenseBill(CashExpenseBillPO bill);
	 
}
