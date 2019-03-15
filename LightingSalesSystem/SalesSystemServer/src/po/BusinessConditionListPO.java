package po;

import java.util.ArrayList;


/**
 * 经营情况表
 * @author guxinyu
 * @version 2017.12.23
 *
 */
public class BusinessConditionListPO {
	private ArrayList<PurchaseBillPO> purchaseBills;  
	private ArrayList<PurchaseReturnBillPO> purchaseReturnBills;  
	private ArrayList<SalesBillPO> salesBills;  
	private ArrayList<SalesReturnBillPO> salesReturnBills;  
	private ArrayList<InventoryOverBillPO> inventoryOverBills;  
	private ArrayList<InventoryLossBillPO> inventoryLossBills;  
	private ArrayList<GiftBillPO> giftBills;  
	private ArrayList<GoodsPO> goodsList;
	
	public BusinessConditionListPO(ArrayList<PurchaseBillPO> purchase,
			ArrayList<PurchaseReturnBillPO> purchaseReturn,
			ArrayList<SalesBillPO> sales,
			ArrayList<SalesReturnBillPO> salesReturn,
			ArrayList<InventoryOverBillPO> inventoryOver,
			ArrayList<InventoryLossBillPO> inventoryLoss, 
			ArrayList<GiftBillPO> gift,
			ArrayList<GoodsPO> goods){
		setPurchaseBills(purchase);
		setPurchaseReturnBills(purchaseReturn);
		setSalesBills(sales);
		setSalesReturnBills(salesReturn);
		setInventoryOverBills(inventoryOver);
		setInventoryLossBills(inventoryLoss);
		setGiftBills(gift);
		setGoodsList(goods);
	}
	
	public BusinessConditionListPO(){
		
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

	public ArrayList<GoodsPO> getGoodsList() {
		return goodsList;
	}

	public void setGoodsList(ArrayList<GoodsPO> goodsList) {
		this.goodsList = goodsList;
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

	public ArrayList<GiftBillPO> getGiftBills() {
		return giftBills;
	}

	public void setGiftBills(ArrayList<GiftBillPO> giftBills) {
		this.giftBills = giftBills;
	}
	
}
