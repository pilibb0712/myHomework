package blInteract.billBlInteract;

import blImpl.bill.billBl.CashExpenseBillBl;
import blImpl.bill.billBl.GiftBillBl;
import blImpl.bill.billBl.InventoryLossBillBl;
import blImpl.bill.billBl.InventoryOverBillBl;
import blImpl.bill.billBl.InventoryWarningBillBl;
import blImpl.bill.billBl.PaymentBillBl;
import blImpl.bill.billBl.PurchaseBillBl;
import blImpl.bill.billBl.PurchaseReturnBillBl;
import blImpl.bill.billBl.ReceiptBillBl;
import blImpl.bill.billBl.SalesBillBl;
import blImpl.bill.billBl.SalesReturnBillBl;

public class BillBlInteractServiceFactory {
	
	public CashExpenseBillBlService getCashExpenseBillBlService(){
		CashExpenseBillBlService service = new CashExpenseBillBl();
		return service;
	}
	
	public GiftBillBlService getGiftBillBlService(){
		GiftBillBlService service = new GiftBillBl();
		return service ;
	}
	
	public InventoryLossBillBlService getInventoryLossBillBlService(){
		InventoryLossBillBlService service = new InventoryLossBillBl();
		return service;
	}
	
	public InventoryOverBillBlService getInventoryOverBillBlService(){
		InventoryOverBillBlService service = new InventoryOverBillBl();
		return service;
	}
	
	public InventoryWarningBillBlService getInventoryWarningBillBlService(){
		InventoryWarningBillBlService service = new InventoryWarningBillBl();
		return service;
	}
	
	public PaymentBillBlService getPaymentBillBlService(){
		PaymentBillBlService service = new PaymentBillBl();
		return service;
	}
	
	public PurchaseBillBlService getPurchaseBillBlService(){
		PurchaseBillBlService service = new PurchaseBillBl();
		return service;
	}
	
	public PurchaseReturnBillBlService getPurchaseReturnBillBlService(){
		PurchaseReturnBillBlService service = new PurchaseReturnBillBl();
		return service;
	}
	
	public ReceiptBillBlService getReceiptBillBlService(){
		ReceiptBillBlService service = new ReceiptBillBl();
		return service;
	}
	
	public SalesBillBlService getSalesBillBlService(){
		SalesBillBlService service = new SalesBillBl();
		return service;
	}
	
	public SalesReturnBillBlService getSalesReturnBillBlService(){
		SalesReturnBillBlService service = new SalesReturnBillBl();
		return service;
	}
}
