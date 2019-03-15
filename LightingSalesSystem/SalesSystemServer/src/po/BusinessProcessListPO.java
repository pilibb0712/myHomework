package po;

import java.util.ArrayList;

/**
 * 经营历程表
 * @author guxinyu
 * @version 2017.11.7
 *
 */
public class BusinessProcessListPO {
	private ArrayList<InventoryWarningBillPO> inventoryWarningBills;  
	private ArrayList<InventoryOverBillPO> inventoryOverBills;
	private ArrayList<InventoryLossBillPO> inventoryLossBills;
	private ArrayList<PurchaseBillPO> purchaseBills;
	private ArrayList<PurchaseReturnBillPO> purchaseReturnBills;
	private ArrayList<SalesBillPO> salesBills;
	private ArrayList<SalesReturnBillPO> salesReturnBills;
	private ArrayList<ReceiptBillPO> receiptBills;
	private ArrayList<PaymentBillPO> paymentBills;
	private ArrayList<CashExpenseBillPO> cashExpenseBills;
	private ArrayList<GiftBillPO> giftBills;
	
	public BusinessProcessListPO (ArrayList<InventoryWarningBillPO> inventoryWarning,
			ArrayList<InventoryOverBillPO> inventoryOver,
			ArrayList<InventoryLossBillPO> inventoryLoss,
			ArrayList<PurchaseBillPO> purchase,
			ArrayList<PurchaseReturnBillPO> purchaseReturn,
			ArrayList<SalesBillPO> sales,
			ArrayList<SalesReturnBillPO> salesReturn,
			ArrayList<ReceiptBillPO> receipt,
			ArrayList<PaymentBillPO> payment,
			ArrayList<CashExpenseBillPO> cashExpense,
			ArrayList<GiftBillPO> gift){
		
		setInventoryWarningBills(inventoryWarning);
		setInventoryOverBills(inventoryOver);
		setInventoryLossBills(inventoryLoss);
		setPurchaseBills(purchase);
		setPurchaseReturnBills(purchaseReturn);
		setSalesBills(sales);
		setSalesReturnBills(salesReturn);
		setReceiptBills(receipt);
		setPaymentBills(payment);
		setCashExpenseBills(cashExpense);
		setGiftBills(gift);
		
	}

	public ArrayList<InventoryWarningBillPO> getInventoryWarningBills() {
		return inventoryWarningBills;
	}

	public void setInventoryWarningBills(ArrayList<InventoryWarningBillPO> inventoryWarningBills) {
		this.inventoryWarningBills = inventoryWarningBills;
	}

	public ArrayList<InventoryOverBillPO> getInventoryOverBills() {
		return inventoryOverBills;
	}

	public void setInventoryOverBills(ArrayList<InventoryOverBillPO> inventoryOverBills) {
		this.inventoryOverBills = inventoryOverBills;
	}

	public ArrayList<InventoryLossBillPO> getInventoryLossBills() {
		return inventoryLossBills;
	}

	public void setInventoryLossBills(ArrayList<InventoryLossBillPO> inventoryLossBills) {
		this.inventoryLossBills = inventoryLossBills;
	}

	public ArrayList<PurchaseBillPO> getPurchaseBills() {
		return purchaseBills;
	}

	public void setPurchaseBills(ArrayList<PurchaseBillPO> purchaseBills) {
		this.purchaseBills = purchaseBills;
	}

	public ArrayList<PurchaseReturnBillPO> getPurchaseReturnBills() {
		return purchaseReturnBills;
	}

	public void setPurchaseReturnBills(ArrayList<PurchaseReturnBillPO> purchaseReturnBills) {
		this.purchaseReturnBills = purchaseReturnBills;
	}

	public ArrayList<SalesBillPO> getSalesBills() {
		return salesBills;
	}

	public void setSalesBills(ArrayList<SalesBillPO> salesBills) {
		this.salesBills = salesBills;
	}

	public ArrayList<SalesReturnBillPO> getSalesReturnBills() {
		return salesReturnBills;
	}

	public void setSalesReturnBills(ArrayList<SalesReturnBillPO> salesReturnBills) {
		this.salesReturnBills = salesReturnBills;
	}

	public ArrayList<ReceiptBillPO> getReceiptBills() {
		return receiptBills;
	}

	public void setReceiptBills(ArrayList<ReceiptBillPO> receiptBills) {
		this.receiptBills = receiptBills;
	}

	public ArrayList<PaymentBillPO> getPaymentBills() {
		return paymentBills;
	}

	public void setPaymentBills(ArrayList<PaymentBillPO> paymentBills) {
		this.paymentBills = paymentBills;
	}

	public ArrayList<CashExpenseBillPO> getCashExpenseBills() {
		return cashExpenseBills;
	}

	public void setCashExpenseBills(ArrayList<CashExpenseBillPO> cashExpenseBills) {
		this.cashExpenseBills = cashExpenseBills;
	}

	public ArrayList<GiftBillPO> getGiftBills() {
		return giftBills;
	}

	public void setGiftBills(ArrayList<GiftBillPO> giftBills) {
		this.giftBills = giftBills;
	}
	

}
