package dataDrivers.promotionDataDrivers;

import java.util.ArrayList;

import assistant.type.StrategyCategoryEnum;
import dataImpl.promotionData.PromotionData;
import dataService.promotionDataService.PromotionDataService;
import po.ComboStrategyPO;
import po.TotalStrategyPO;
import po.UserStrategyPO;

/**
 * @author zhangao 161250193
 * @version 2017.11.25
 * 促销策略数据PromotionDataService的驱动测试
 */


public class PromotionDataService_Driver{

	PromotionDataService promotionDataService =  new PromotionData();
	
	/**
	 * 测试驱动新增用户促销策略方法
	 */
	private void driveAddUserStrategy(){
		UserStrategyPO po = new UserStrategyPO();
		po.setId("2017-1125-10-38");
		po.setName("测试用用户促销策略1");
		promotionDataService.addUserStrategy(po);
		
		UserStrategyPO po1 = new UserStrategyPO();
		po1.setId("2017-1125-10-39");
		po1.setName("测试用用户促销策略2");
		promotionDataService.addUserStrategy(po1);
		
		UserStrategyPO po2 = new UserStrategyPO();
		po2.setId("2017-1125-10-40");
		po2.setName("测试用用户促销策略3");
		promotionDataService.addUserStrategy(po2);
		System.out.println("driveAddUserStrategy测试结束");
	}
	
	/**
	 * 测试驱动新增总价促销策略方法
	 */
	private void driveAddTotalStrategy(){
		TotalStrategyPO po = new TotalStrategyPO();
		po.setId("2017-1125-10-38");
		po.setName("测试用总价促销策略1");
		promotionDataService.addTotalStrategy(po);
		System.out.println("driveAddTotalStrategy测试结束");
	}
	
	/**
	 * 测试驱动新增组合促销策略方法
	 */
	private void driveAddComboStrategy(){
		ComboStrategyPO po = new ComboStrategyPO();
		po.setId("2017-1125-10-38");
		po.setName("测试用总价促销策略1");
		promotionDataService.addComboStrategy(po);
		System.out.println("driveAddComboStrategy测试结束");
	}
	
	/**
	 * 测试驱动删除促销策略方法
	 */
	private void driveDeleteStrategy(){
		promotionDataService.deleteStrategy(
				StrategyCategoryEnum.USRESTRATEGY.toString(),"2017-1125-10-38");
		
	}
	
	/**
	 * 测试驱动更新促销策略方法
	 */
	private void driveUpdateStrategy(){
		UserStrategyPO po = new UserStrategyPO();
		po.setId("2017-1125-10-38");
		po.setName("测试用用户促销策略update1");
		promotionDataService.updateStrategy(StrategyCategoryEnum.USRESTRATEGY.toString(),po.getId(),po);
	}
	
	
	/**
	 * 测试驱动得到现有所有用户策略方法
	 */
	private void driveGetUserStrategyList(){
		ArrayList<UserStrategyPO> list = promotionDataService.getUserStrategyList();
		for(UserStrategyPO po:list){
			System.out.println(po.getName());
		}
	}
	
	public static void main(String [] args){
		PromotionDataService_Driver promotionDataService_Driver 
			= new PromotionDataService_Driver();
		//promotionDataService_Driver.driveAddUserStrategy();
		//promotionDataService_Driver.driveAddComboStrategy();
		//promotionDataService_Driver.driveAddTotalStrategy();
		//promotionDataService_Driver.driveDeleteStrategy();
		//promotionDataService_Driver.driveUpdateStrategy();
		//promotionDataService_Driver.driveGetUserStrategyList();
	}
}
