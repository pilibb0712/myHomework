package ui.salesUi.salesBl;

import vo.ComboStrategyVO;

public class ComboStrategy {
	private ComboStrategyVO comboStrategyVO;
	
	public ComboStrategy(ComboStrategyVO vo){
		this.comboStrategyVO = vo;
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
}
