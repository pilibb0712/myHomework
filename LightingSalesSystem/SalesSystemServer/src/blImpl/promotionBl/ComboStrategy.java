package blImpl.promotionBl;

import java.util.ArrayList;

import assistant.type.CustomerLevelEnum;
import assistant.utility.Date;
import vo.ComboStrategyVO;
import vo.GiftBillVO;
import vo.GiftVO;
import vo.SalesGoodsVO;
import vo.SalesManCommodityVO;

public class ComboStrategy {
	private ComboStrategyVO comboStrategyVO;
	
	public ComboStrategy(ComboStrategyVO vo){
		this.comboStrategyVO = vo;
	}
	
	public ComboStrategyVO getComboStrategyVO(){
		return this.comboStrategyVO;
	}
	

	/**
	 * 计算总的折让价值
	 * */
	public double calcTotalValue(double consumption){
		return calcDisCount(consumption);
	}
	
	
	/**
	 * 计算总的折扣价值
	 * */
	public double calcDisCount(double consumption){
		double discount = comboStrategyVO.getDiscount();
		return consumption*discount;
	}
	
	/**
	 * 该促销策略是否在时间范围内
	 * @param Date 当前时间
	 * */
	public boolean isInDateArea(Date date){
		//Date date = new Date();//当前时间
		Date startDate = comboStrategyVO.getStartDate();
		Date endDate = comboStrategyVO.getEndDate();
		return date.isInDateArea(startDate, endDate);
	}
	
	/**
	 * 是否符合该促销策略条件
	 * */
	public boolean isValidCommoditiesCombo(ArrayList<SalesGoodsVO> salesManCommodityVOs){
		ArrayList<GiftVO> gifts = new ArrayList<>();
		for(GiftVO gift: gifts){
			for(SalesGoodsVO commodity: salesManCommodityVOs){
				if(commodity.getGoodsId().equals(gift.getId())
						&&commodity.getNumber()<gift.getGiftAmount()){
					return false;
				}
			}
		}
		return true;
	}

}
