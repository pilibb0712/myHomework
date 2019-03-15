package po;

import java.util.ArrayList;

import assistant.utility.Date;

public class ComboStrategyPO extends PromotionStrategyPO {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 2614612009686224557L;
	private String comboName;
	private double discount;
	private ArrayList<GiftPO> comboCommodityList;
	
	
	public String getComboName() {
		return comboName;
	}
	public void setComboName(String comboName) {
		this.comboName = comboName;
	}
	public double getDiscount() {
		return discount;
	}
	public void setDiscount(double discount) {
		this.discount = discount;
	}
	public ArrayList<GiftPO> getComboCommodityList() {
		return comboCommodityList;
	}
	public void setComboCommodityList(ArrayList<GiftPO> comboCommodityList) {
		this.comboCommodityList = comboCommodityList;
	}
}

