package blImpl.promotionBl;

import java.rmi.RemoteException;
import java.util.ArrayList;

import assistant.utility.Date;
import vo.ComboStrategyVO;
import vo.SalesGoodsVO;

public class ComboStrategyList {
	private ArrayList<ComboStrategy> comboStrategies;

	/**
	 * 构造方法,维护所用用户促销策略的集合
	 * */
	public ComboStrategyList(){
		comboStrategies = new ArrayList<ComboStrategy>();
		PromotionBl bl = new PromotionBl();
		ArrayList<ComboStrategyVO> comboStrategyVOs = null; 
		try {
			comboStrategyVOs = bl.getComoboStrategyList();
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		for(ComboStrategyVO vo:comboStrategyVOs){
			comboStrategies.add(new ComboStrategy(vo));
		}
	}
	
	/**
	 * 测试用构造方法,维护所用用户促销策略的集合
	 * */
	public ComboStrategyList(ArrayList<ComboStrategy> list){
		this.comboStrategies = list;
	}
	
	/**
	 * 计算出最好的用户促销策略
	 * @param customerLevel 购买用户的等级
	 * @param consumption 未折让前的商品总价
	 * */
	public ComboStrategyVO calcBestComboStrategy(ArrayList<SalesGoodsVO> salesManCommodityVOs,double consumption){
		double price = 0;
		ComboStrategy bestUserStrategy = null;//最优的用户促销侧路
		Date nowDate = new Date();//当下日期
		for(ComboStrategy strategy : comboStrategies){
			//符合促销策略条件
			if(strategy.isInDateArea(nowDate)&&strategy.isValidCommoditiesCombo(salesManCommodityVOs)){
				double value = strategy.calcTotalValue(consumption); 
				if(price<value){
					bestUserStrategy = strategy;
					price = value;
				}
			}
		}
		if(bestUserStrategy==null){return null;}
		return bestUserStrategy.getComboStrategyVO();
	}
}
