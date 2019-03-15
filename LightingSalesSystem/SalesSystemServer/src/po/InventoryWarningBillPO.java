package po;

import assistant.utility.Date;

public class InventoryWarningBillPO extends BillPO{
	private String goodsName;
	private String goodsID;
	private String goodsType;
	private String goodsPrice;
	private String systemAmount;
	private String warningAmount;
	private String lossAmount;

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
