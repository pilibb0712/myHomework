package blImpl.promotionBl;

import java.util.ArrayList;

import assistant.type.BillCategoryEnum;
import assistant.utility.Date;
import vo.GiftBillVO;
import vo.GiftVO;
import vo.TotalStrategyVO;

/**
 * 总价促销策略的逻辑对象
 * */
public class TotalStrategy {
	private TotalStrategyVO totalStrategyVO;
	
	/**
	 * 以vo为参数的构造方法
	 * @param vo构造方法参数
	 * */
	public TotalStrategy(TotalStrategyVO vo){
		totalStrategyVO = vo;
	}
	
	public TotalStrategyVO geTotalStrategyVO(){
		return this.totalStrategyVO;
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
		return totalStrategyVO.getCoupon();
	}
	
	/**
	 * 计算总的赠品的价值
	 * */
	public double calcGiftValue(){
		double price = 0;
		ArrayList<GiftVO> gifts = totalStrategyVO.getGifts();
		for(GiftVO gift :gifts){
			price += gift.getPrice()*gift.getGiftAmount();
		}
		return price;
	}
	
	/**
	 * 计算总的折扣价值
	 * */
	public double calcDisCount(double consumption){
		double discount = totalStrategyVO.getDiscount();
		return consumption*discount;
	}
	
	/**
	 * 该促销策略是否在时间范围内
	 * @param Date 当前时间
	 * */
	public boolean isInDateArea(Date date){
		//Date date = new Date();//当前时间
		Date startDate = totalStrategyVO.getStartDate();
		Date endDate = totalStrategyVO.getEndDate();
		return date.isInDateArea(startDate, endDate);
	}
	
	/**
	 * 是否符合该促销策略条件
	 * */
	public boolean isValidConsumption(double consumption){
		return consumption>totalStrategyVO.getTotalConsumption();
	}

	/**
	 * 根据赠品生成库存赠送单
	 * */
	public GiftBillVO generateGiftBill(){
		GiftBillVO giftBillVO = new GiftBillVO();
		giftBillVO.setCategoryEnum(BillCategoryEnum.GIFT_BILL);
		giftBillVO.setCreater(null);
		giftBillVO.setCreateDate(new Date());
		
		ArrayList<GiftVO> gifts = totalStrategyVO.getGifts();
		giftBillVO.setGifts(gifts);
		return giftBillVO;
	}
	
}
