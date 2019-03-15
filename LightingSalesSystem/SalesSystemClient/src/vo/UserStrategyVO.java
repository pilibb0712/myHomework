package vo;

import java.io.Serializable;
import java.util.ArrayList;

import assistant.type.CustomerLevelEnum;
import assistant.utility.Date;

public class UserStrategyVO extends PromotionStrategyVO implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 2579716006225125916L;
	private CustomerLevelEnum customerLevel;//客户vip等级
	private double discount;//折扣
	private int coupon;//代金券
	private ArrayList<GiftVO> gifts;//赠送清单
	
	public UserStrategyVO(){
		
	}
	
	public UserStrategyVO (String Id,Date startDate,Date endDate,String remark,CustomerLevelEnum customerLevel,double discount,int coupon,ArrayList<GiftVO> gifts){
		super(Id,startDate,endDate,remark);
		this.coupon=coupon;
		this.customerLevel=customerLevel;
		this.discount=discount;
		this.gifts=gifts;
	}
	
	public CustomerLevelEnum getCustomerLevel() {
		return customerLevel;
	}
	public void setCustomerLevel(CustomerLevelEnum customerLevel) {
		this.customerLevel = customerLevel;
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
