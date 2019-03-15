package blImpl.promotionBl;

import java.rmi.RemoteException;
import java.util.ArrayList;

import assistant.utility.Date;
import vo.TotalStrategyVO;

public class TotalStrategyList {
	private ArrayList<TotalStrategy> totalStrategies;
	
	/**
	 * 构造方法,维护所用用户促销策略的集合
	 * */
	public TotalStrategyList(){
		totalStrategies = new ArrayList<>();
		
		PromotionBl bl = new PromotionBl();
		ArrayList<TotalStrategyVO> totalStrategyVOs = null; 
		try {
			totalStrategyVOs = bl.getTotalStrategyList();
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		for(TotalStrategyVO vo:totalStrategyVOs){
			totalStrategies.add(new TotalStrategy(vo));
		}
	}

	public TotalStrategyList(ArrayList<TotalStrategy> list){
		this.totalStrategies = list;
	}
	
	/**
	 * 计算出最好的用户促销策略
	 * @param consumption 未折让前的商品总价
	 * */
	public TotalStrategyVO calcBestTotalStrategy(double consumption){
		double price = 0;
		TotalStrategy bestTotalStrategy = null;//最优的用户促销侧路
		Date nowDate = new Date();//当下日期
		for(TotalStrategy strategy : totalStrategies){
			//符合促销策略条件
			if(strategy.isInDateArea(nowDate)&&strategy.isValidConsumption(consumption)){
				double value = strategy.calcTotalValue(consumption); 
				if(price<value){
					bestTotalStrategy = strategy;
					price = value;
				}
			}
		}
		if(bestTotalStrategy==null){return null;}
		return bestTotalStrategy.geTotalStrategyVO();
	}
	
}
