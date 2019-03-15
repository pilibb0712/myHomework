package vo;

public class InventoryWarningBillVO extends BillVO{

	/**
	 * 
	 */
	private static final long serialVersionUID = 8179222048022882588L;
	private String goodsName;
	private String goodsID;
	private String goodsType;
	private String goodsPrice;
	private String systemAmount;
	private String warningAmount;
	private String lossAmount;
	//active, not negative

	public void setGoodsName(String str){
		goodsName=str;
	}
	public void setGoodsID(String str){
		goodsID=str;
	}
	public void setGoodsType(String str){
		goodsType=str;
	}
	public void setGoodsPrice(String str){
		goodsPrice=str;
	}
	public void setSystemAmount(String str){
		systemAmount=str;
	}
	public void setWarningAmount(String str){
		warningAmount=str;
	}
	public void setLossAmount(String str){
		lossAmount=str;
	}


	public String getGoodsName(){
		return goodsName;
	}
	public String getGoodsID(){
		return goodsID;
	}
	public String getGoodsType(){
		return goodsType;
	}
	public String getGoodsPrice(){
		return goodsPrice;
	}
	public String getSystemAmount(){
		return systemAmount;
	}
	public String getWarningAmount(){
		return warningAmount;
	}
	public String getLossAmount(){
		return lossAmount;
	}
}
