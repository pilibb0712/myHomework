package vo;

import java.io.Serializable;
import java.util.ArrayList;

public class TotalStrategyVO extends PromotionStrategyVO implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 7893407532826968666L;
	private double totalConsumption;
	private double discount;
	private int coupon;
	private ArrayList<GiftVO> gifts;//ÔùËÍÇåµ¥
	
	public TotalStrategyVO(){
		
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
	public ArrayList<GiftVO> getGifts() {
		return gifts;
	}
	public void setGifts(ArrayList<GiftVO> gifts) {
		this.gifts = gifts;
	}
}
