package blImpl.promotionBl;

import java.util.ArrayList;

import assistant.convertors.UserStrategyPOVOConvertor;
import assistant.type.BillCategoryEnum;
import assistant.type.CustomerLevelEnum;
import assistant.utility.Date;
import po.UserStrategyPO;
import vo.GiftBillVO;
import vo.GiftVO;
import vo.UserStrategyVO;


/**
 * 用户促销策略的逻辑对象
 * */
public class UserStrategy {
	private UserStrategyVO userStrategyVO;
	
	/**
	 * 以vo为参数的构造方法
	 * @param vo构造方法参数
	 * */
	public UserStrategy(UserStrategyVO vo){
		userStrategyVO = vo;
	}
	
	/**
	 * 以po为参数的构造方法
	 * @param po构造方法参数
	 * */
	public UserStrategy(UserStrategyPO po){
		userStrategyVO = UserStrategyPOVOConvertor.poToVO(po);
	}
	
	/**
	 * 用户促销策略的构造方法
	 * */
	public UserStrategyVO getUserStrategyVO() {
		return userStrategyVO;
	}
	
	/**
	 * 计算总的折让价值
	 * */
	public double calcTotalValue(double consumption){
		return calcDisCount(consumption)+ calcGiftValue()+calcCoupon();
	}
	
	/**
	 * 计算代金券价值
	 * */
	public double calcCoupon(){
		return getUserStrategyVO().getCoupon();
	}
	
	/**
	 * 计算总的赠品的价值
	 * */
	public double calcGiftValue(){
		double price = 0;
		ArrayList<GiftVO> gifts = getUserStrategyVO().getGifts();
		for(GiftVO gift :gifts){
			price += gift.getPrice()*gift.getGiftAmount();
		}
		return price;
	}
	
	/**
	 * 计算总的折扣价值
	 * */
	public double calcDisCount(double consumption){
		double discount = getUserStrategyVO().getDiscount();
		return consumption*discount;
	}
	
	/**
	 * 该促销策略是否在时间范围内
	 * @param Date 当前时间
	 * */
	public boolean isInDateArea(Date date){
		//Date date = new Date();//当前时间
		Date startDate = getUserStrategyVO().getStartDate();
		Date endDate = getUserStrategyVO().getEndDate();
		return date.isInDateArea(startDate, endDate);
	}
	
	/**
	 * 是否符合该促销策略条件
	 * */
	public boolean isValidCustomerLevel(CustomerLevelEnum customerLevel){
		CustomerLevelEnum targetCustomerLevel = getUserStrategyVO().getCustomerLevel();
		return customerLevel.isBiggerThanLevel(targetCustomerLevel);
	}

	/**
	 * 根据赠品生成库存赠送单
	 * */
	public GiftBillVO generateGiftBill(){
		GiftBillVO giftBillVO = new GiftBillVO();
		giftBillVO.setCategoryEnum(BillCategoryEnum.GIFT_BILL);
		giftBillVO.setCreater(null);
		giftBillVO.setCreateDate(new Date());
		
		ArrayList<GiftVO> gifts = userStrategyVO.getGifts();
		giftBillVO.setGifts(gifts);
		return giftBillVO;
	}
}
