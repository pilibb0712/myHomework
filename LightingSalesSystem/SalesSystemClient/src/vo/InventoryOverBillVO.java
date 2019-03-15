package vo;

public class InventoryOverBillVO extends BillVO{

	/**
	 * 
	 */
	private static final long serialVersionUID = -3251538765798630733L;
	private String goodsName;
	private String goodsID;
	private String goodsType;
	private String goodsPrice;
	private String systemAmount;
	private String realAmount;
	private String overAmount;


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
	public void setOverAmount(String str){
		overAmount=str;
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
	public String getOverAmount(){
		return overAmount;
	}
}
