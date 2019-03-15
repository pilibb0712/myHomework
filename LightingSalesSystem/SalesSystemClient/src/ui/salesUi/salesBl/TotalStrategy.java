package ui.salesUi.salesBl;

import java.util.ArrayList;

import vo.GiftVO;
import vo.TotalStrategyVO;

public class TotalStrategy {
	private TotalStrategyVO totalStrategyVO;
	
	/**
	 * 以vo为参数的构造方法
	 * @param vo构造方法参数
	 * */
	public TotalStrategy(TotalStrategyVO vo){
		totalStrategyVO = vo;
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
}
