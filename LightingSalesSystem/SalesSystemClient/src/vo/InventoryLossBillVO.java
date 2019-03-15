package vo;

public class InventoryLossBillVO extends BillVO{

	/**
	 * 
	 */
	private static final long serialVersionUID = -7990964708173651461L;
	private String goodsName;
	private String goodsID;
	private String goodsType;
	private String goodsPrice;
	private String systemAmount;
	private String realAmount;
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
	public void setRealAmount(String str){
		realAmount=str;
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
	public String getRealAmount(){
		return realAmount;
	}
	public String getLossAmount(){
		return lossAmount;
	}
}
