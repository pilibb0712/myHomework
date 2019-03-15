package vo;

import java.util.ArrayList;

public class ComboStrategyVO extends PromotionStrategyVO {
	/**
	 * 
	 */
	private static final long serialVersionUID = -7280549173752058239L;
	private double discount;
	private ArrayList<GiftVO> comboCommodityList;
	
	public ComboStrategyVO(){
		
	}
	

	public double getDiscount() {
		return discount;
	}
	public void setDiscount(double discount) {
		this.discount = discount;
	}
	public ArrayList<GiftVO> getComboCommodityList() {
		return comboCommodityList;
	}
	public void setComboCommodityList(ArrayList<GiftVO> comboCommodityList) {
		this.comboCommodityList = comboCommodityList;
	}
}
