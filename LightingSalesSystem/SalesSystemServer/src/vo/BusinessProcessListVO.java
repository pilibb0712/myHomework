package vo;

import java.io.Serializable;
import java.util.ArrayList;


/**
 * 经营历程表
 * @author guxinyu
 * @version 2017.12.24
 */
public class BusinessProcessListVO implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 4844247264640972036L;
	private ArrayList<BusinessProcessListBillVO> bills;  
	private ArrayList<InventoryWarningBillVO> inventoryWarningBills;  
	private ArrayList<InventoryOverBillVO> inventoryOverBills;
	private ArrayList<InventoryLossBillVO> inventoryLossBills;
	private ArrayList<PurchaseBillVO> purchaseBills;
	private ArrayList<PurchaseReturnBillVO> purchaseReturnBills;
	private ArrayList<SalesBillVO> salesBills;
	private ArrayList<SalesReturnBillVO> salesReturnBills;
	private ArrayList<ReceiptBillVO> receiptBills;
	private ArrayList<PaymentBillVO> paymentBills;
	private ArrayList<CashExpenseBillVO> cashExpenseBills;
	private ArrayList<GiftBillVO> giftBills;
	
	public BusinessProcessListVO (){
		
	}
	
	public BusinessProcessListVO(ArrayList<BusinessProcessListBillVO> b,
			ArrayList<InventoryWarningBillVO> inventoryWarning,
			ArrayList<InventoryOverBillVO> inventoryOver,
			ArrayList<InventoryLossBillVO> inventoryLoss,
			ArrayList<PurchaseBillVO> purchase,
			ArrayList<PurchaseReturnBillVO> purchaseReturn,
			ArrayList<SalesBillVO> sales,
			ArrayList<SalesReturnBillVO> salesReturn,
			ArrayList<ReceiptBillVO> receipt,
			ArrayList<PaymentBillVO> payment,
			ArrayList<CashExpenseBillVO> cashExpense,
			ArrayList<GiftBillVO> gift){
		setBills(b);
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

	public ArrayList<BusinessProcessListBillVO> getBills() {
		return bills;
	}

	public void setBills(ArrayList<BusinessProcessListBillVO> bills) {
		this.bills = bills;
	}
	
	public ArrayList<InventoryWarningBillVO> getInventoryWarningBills() {
		return inventoryWarningBills;
	}

	public void setInventoryWarningBills(ArrayList<InventoryWarningBillVO> inventoryWarningBills) {
		this.inventoryWarningBills = inventoryWarningBills;
	}

	public ArrayList<InventoryOverBillVO> getInventoryOverBills() {
		return inventoryOverBills;
	}

	public void setInventoryOverBills(ArrayList<InventoryOverBillVO> inventoryOverBills) {
		this.inventoryOverBills = inventoryOverBills;
	}

	public ArrayList<InventoryLossBillVO> getInventoryLossBills() {
		return inventoryLossBills;
	}

	public void setInventoryLossBills(ArrayList<InventoryLossBillVO> inventoryLossBills) {
		this.inventoryLossBills = inventoryLossBills;
	}

	public ArrayList<PurchaseBillVO> getPurchaseBills() {
		return purchaseBills;
	}

	public void setPurchaseBills(ArrayList<PurchaseBillVO> purchaseBills) {
		this.purchaseBills = purchaseBills;
	}

	public ArrayList<PurchaseReturnBillVO> getPurchaseReturnBills() {
		return purchaseReturnBills;
	}

	public void setPurchaseReturnBills(ArrayList<PurchaseReturnBillVO> purchaseReturnBills) {
		this.purchaseReturnBills = purchaseReturnBills;
	}

	public ArrayList<SalesBillVO> getSalesBills() {
		return salesBills;
	}

	public void setSalesBills(ArrayList<SalesBillVO> salesBills) {
		this.salesBills = salesBills;
	}

	public ArrayList<SalesReturnBillVO> getSalesReturnBills() {
		return salesReturnBills;
	}

	public void setSalesReturnBills(ArrayList<SalesReturnBillVO> salesReturnBills) {
		this.salesReturnBills = salesReturnBills;
	}

	public ArrayList<ReceiptBillVO> getReceiptBills() {
		return receiptBills;
	}

	public void setReceiptBills(ArrayList<ReceiptBillVO> receiptBills) {
		this.receiptBills = receiptBills;
	}

	public ArrayList<PaymentBillVO> getPaymentBills() {
		return paymentBills;
	}

	public void setPaymentBills(ArrayList<PaymentBillVO> paymentBills) {
		this.paymentBills = paymentBills;
	}

	public ArrayList<CashExpenseBillVO> getCashExpenseBills() {
		return cashExpenseBills;
	}

	public void setCashExpenseBills(ArrayList<CashExpenseBillVO> cashExpenseBills) {
		this.cashExpenseBills = cashExpenseBills;
	}

	public ArrayList<GiftBillVO> getGiftBills() {
		return giftBills;
	}

	public void setGiftBills(ArrayList<GiftBillVO> giftBills) {
		this.giftBills = giftBills;
	}
	
}
