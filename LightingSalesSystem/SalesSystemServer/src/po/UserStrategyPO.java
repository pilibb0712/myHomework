package po;

import java.util.ArrayList;

import assistant.type.CustomerLevelEnum;

/**
 * @author zhangao 161250193
 * @version 2017.12.3
 * UserStrategyPO 用户促销策略的PO类
 */

public class UserStrategyPO extends  PromotionStrategyPO {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -7786109013185396959L;
	
	private CustomerLevelEnum customerLevel;//客户Vip等级
	private double discount;//折扣
	private int coupon;//代金券
	private ArrayList<GiftPO> gifts;//赠送清单
	
	public UserStrategyPO(){
		
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
	public ArrayList<GiftPO> getGifts() {
		return  gifts;
	}
	public void setGifts(ArrayList<GiftPO> gifts) {
		this.gifts = gifts;
	} 

}

