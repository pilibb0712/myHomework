package vo;

import java.io.Serializable;

/**
 * 用于经营情况表
 * @author guxinyu
 * @version 2017.12.23
 *
 */
public class IncomeCostVO implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = -6409162391363344982L;
	//收入类
	private String salesEarning;
	private String inventoryOverEarning;
	private String costAjdustEarning;
	private String purchaseReturnGapEarning;
	private String couponGapEarning;
	//支出类
	private String salesCost;
	private String inventoryLossCost;
	private String giftCost;
	
	public IncomeCostVO(){
		
	}
	
	public IncomeCostVO(String salesEarn, String overEarn, String costAdjustEarn, 
			String purchaseReGapEarn, String couponGapEarn, String saCost, String lossCost,
			String giCost){
		setSalesEarning(salesEarn);
		setInventoryOverEarning(overEarn);
		setCostAjdustEarning(costAdjustEarn);
		setPurchaseReturnGapEarning(purchaseReGapEarn);
		setCouponGapEarning(couponGapEarn);
		setSalesCost(saCost);
		setInventoryLossCost(lossCost);
		setGiftCost(giCost);
	}
	
	public String getSalesEarning() {
		return salesEarning;
	}
	public void setSalesEarning(String salesEarning) {
		this.salesEarning = salesEarning;
	}
	public String getInventoryOverEarning() {
		return inventoryOverEarning;
	}
	public void setInventoryOverEarning(String inventoryOverEarning) {
		this.inventoryOverEarning = inventoryOverEarning;
	}
	public String getCostAjdustEarning() {
		return costAjdustEarning;
	}
	public void setCostAjdustEarning(String costAjdustEarning) {
		this.costAjdustEarning = costAjdustEarning;
	}
	public String getPurchaseReturnGapEarning() {
		return purchaseReturnGapEarning;
	}
	public void setPurchaseReturnGapEarning(String purchaseReturnGapEarning) {
		this.purchaseReturnGapEarning = purchaseReturnGapEarning;
	}
	public String getCouponGapEarning() {
		return couponGapEarning;
	}
	public void setCouponGapEarning(String couponGapEarning) {
		this.couponGapEarning = couponGapEarning;
	}
	public String getSalesCost() {
		return salesCost;
	}
	public void setSalesCost(String salesCost) {
		this.salesCost = salesCost;
	}
	public String getInventoryLossCost() {
		return inventoryLossCost;
	}
	public void setInventoryLossCost(String inventoryLossCost) {
		this.inventoryLossCost = inventoryLossCost;
	}
	public String getGiftCost() {
		return giftCost;
	}
	public void setGiftCost(String giftCost) {
		this.giftCost = giftCost;
	}
	
	
}
