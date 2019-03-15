package po;

import java.util.ArrayList;

import assistant.utility.Date;

public class TotalStrategyPO extends  PromotionStrategyPO{
	/**
	 * 
	 */
	private static final long serialVersionUID = 5130275451390514323L;
	private double totalConsumption;
	private double discount;
	private int coupon;
	private ArrayList<GiftPO> gifts;
	
	public TotalStrategyPO(){
		
	}
	
	public double getTotalConsumption() {
		return totalConsumption;
	}
	public void setTotalConsumption(double totalConsumption) {
		this.totalConsumption = totalConsumption;
	}
	public double getDiscount() {
		return discount;
	}
	public void setDiscount(double discount) {
		this.discount = discount;
	}
	public int getCoupon() {
		return coupon;
	}
	public void setCoupon(int coupon) {
		this.coupon = coupon;
	}
	public ArrayList<GiftPO> getGifts() {
		return gifts;
	}
	public void setGifts(ArrayList<GiftPO> gifts) {
		this.gifts = gifts;
	}
	
	
}

