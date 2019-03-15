package blImpl.promotionBl;

import java.rmi.RemoteException;
import java.util.ArrayList;

import assistant.type.CustomerLevelEnum;
import assistant.utility.Date;
import vo.UserStrategyVO;

public class UserStrategyList {
	ArrayList<UserStrategy> userStrategys;
	
	/**
	 * 构造方法,维护所用用户促销策略的集合
	 * */
	public UserStrategyList() {
		userStrategys = new ArrayList<UserStrategy>();
		
		PromotionBl bl = new PromotionBl();
		ArrayList<UserStrategyVO> userStrategyVOs = null; 
		try {
			userStrategyVOs = bl.getUserStrategyList();
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		for(UserStrategyVO vo:userStrategyVOs){
			userStrategys.add(new UserStrategy(vo));
		}
	}
	
	/**
	 * 测试用构造方法,维护所用用户促销策略的集合
	 * */
	public UserStrategyList(ArrayList<UserStrategy> userStrategys){
		this.userStrategys  =  userStrategys;
	}
	
	/**
	 * 计算出最好的用户促销策略
	 * @param customerLevel 购买用户的等级
	 * @param consumption 未折让前的商品总价
	 * */
	public UserStrategyVO calcBestUserStrategy(CustomerLevelEnum customerLevel,double consumption){
		double price = 0;
		UserStrategy bestUserStrategy = null;//最优的用户促销侧路
		Date nowDate = new Date();//当下日期
		for(UserStrategy strategy : userStrategys){
			//符合促销策略条件
			if(strategy.isInDateArea(nowDate)&&strategy.isValidCustomerLevel(customerLevel)){
				double value = strategy.calcTotalValue(consumption); 
				if(price<value){
					bestUserStrategy = strategy;
					price = value;
				}
			}
		}
		if(bestUserStrategy==null){return null;}
		return bestUserStrategy.getUserStrategyVO();
	}
	
}
