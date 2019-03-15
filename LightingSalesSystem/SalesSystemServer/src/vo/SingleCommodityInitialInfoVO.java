package vo;

import java.io.Serializable;

/**
 * 账单期初信息中商品信息的一条
 * @author guxinyu
 * @version 2017.12.4
 *
 */
public class SingleCommodityInitialInfoVO implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = -5703227051157658043L;
	private String commodityClass="";//分类
	private String commodityName="";
	private String commodityType="";//型号
	private String purchasePrice="";//进价
	private String sellingPrice="";//售价
	private String recentPurchasePrice="";
	private String recentSellingPrice="";
	
	public SingleCommodityInitialInfoVO(){
		
	}
	
	public SingleCommodityInitialInfoVO(String comClass,String comName,String comType,
			String purPrice,String sellPrice,String rePurPrice, String reSellPrice){
		setCommodityClass(comClass);
		setCommodityName(comName);
		setCommodityType(comType);
		setPurchasePrice(purPrice);
		setSellingPrice(sellPrice);
		setRecentPurchasePrice("0.0");
		setRecentSellingPrice("0.0");
	}

	public String getCommodityClass() {
		return commodityClass;
	}

	public void setCommodityClass(String commodityClass) {
		this.commodityClass = commodityClass;
	}

	public String getCommodityName() {
		return commodityName;
	}

	public void setCommodityName(String commodityName) {
		this.commodityName = commodityName;
	}

	public String getCommodityType() {
		return commodityType;
	}

	public void setCommodityType(String commodityType) {
		this.commodityType = commodityType;
	}

	public String getPurchasePrice() {
		return purchasePrice;
	}

	public void setPurchasePrice(String purchasePrice) {
		this.purchasePrice = purchasePrice;
	}

	public String getSellingPrice() {
		return sellingPrice;
	}

	public void setSellingPrice(String sellingPrice) {
		this.sellingPrice = sellingPrice;
	}

	public String getRecentPurchasePrice() {
		return recentPurchasePrice;
	}

	public void setRecentPurchasePrice(String recentPurchasePrice) {
		this.recentPurchasePrice = recentPurchasePrice;
	}

	public String getRecentSellingPrice() {
		return recentSellingPrice;
	}

	public void setRecentSellingPrice(String recentSellingPrice) {
		this.recentSellingPrice = recentSellingPrice;
	}
}
