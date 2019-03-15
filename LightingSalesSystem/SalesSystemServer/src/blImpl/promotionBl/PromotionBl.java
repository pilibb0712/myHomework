package blImpl.promotionBl;

import java.rmi.RemoteException;
import java.util.ArrayList;

import assistant.convertors.ComboStrategyPOVOConvertor;
import assistant.convertors.TotalStrategyPOVOConvertor;
import assistant.convertors.UserStrategyPOVOConvertor;
import assistant.type.StrategyCategoryEnum;
import dataImpl.promotionData.PromotionData;
import dataService.promotionDataService.PromotionDataService;
import po.ComboStrategyPO;
import po.PromotionStrategyPO;
import po.TotalStrategyPO;
import po.UserStrategyPO;
import vo.ComboStrategyVO;
import vo.TotalStrategyVO;
import vo.UserStrategyVO;
/**
 * @author zhangao 161250193
 * promotionBl类
 * @version 2017.12.3
 */

public class PromotionBl {
	
	private PromotionDataService service = new PromotionData();//数据层操作
	
	/**
	 * 得到所有现有的用户促销策略清单
	 * @return ArrayList<UserStrategyVO> 用户促销策略清单
	 */
	public ArrayList<UserStrategyVO> getUserStrategyList() throws RemoteException{
		ArrayList<UserStrategyPO> list = service.getUserStrategyList();
		ArrayList<UserStrategyVO> targetList = new ArrayList<UserStrategyVO>();
		for(UserStrategyPO po:list){
			UserStrategyVO vo = UserStrategyPOVOConvertor.poToVO(po);
			targetList.add(vo);
		}
		return targetList;
	}

	/**
	 * 得到所有现有的用户促销策略清单
	 * @return ArrayList<TotalStrategyVO> 用户促销策略清单
	 */
	public ArrayList<TotalStrategyVO> getTotalStrategyList()throws RemoteException {
		ArrayList<TotalStrategyPO> list = service.getTotalStrategyList();
		ArrayList<TotalStrategyVO> targetList = new ArrayList<TotalStrategyVO>();
		for(TotalStrategyPO po:list){
			TotalStrategyVO vo = TotalStrategyPOVOConvertor.poToVO(po);
			targetList.add(vo);
		}
		return targetList;
	}

	/**
	 * 得到所有现有的用户促销策略清单
	 * @return ArrayList<ComboStrategyVO> 用户促销策略清单
	 */
	public ArrayList<ComboStrategyVO> getComoboStrategyList()throws RemoteException {
		ArrayList<ComboStrategyPO> list = service.getComboStrategyList();
		ArrayList<ComboStrategyVO> targetList = new ArrayList<ComboStrategyVO>();
		for(ComboStrategyPO po:list){
			ComboStrategyVO vo = ComboStrategyPOVOConvertor.poToVO(po);
			targetList.add(vo);
		}
		return targetList;
	}

	/**
	 * 删除促销策略
	 * @return boolean是否删除成功
	 */
	public boolean deleteStrategy(StrategyCategoryEnum strategyType, String strategyId)throws RemoteException {
		return service.deleteStrategy(strategyType.toString(), strategyId);
	}
	
	
	/**
	 * 更新促销策略
	 * @param strategyType 促销策略的种类
	 * @param strategyId 促销策略Id
	 * @param PromotionStrategyPO newPromotionStrategyPO新的促销策略
	 * @return boolean 是否新增成功
	 */
	 public boolean updateStrategy(StrategyCategoryEnum strategyType
		 , String strategyId
		 ,PromotionStrategyPO newPromotionStrategyPO) throws RemoteException{
		 
		return service.updateStrategy(strategyType.toString(), strategyId, newPromotionStrategyPO);
	 }
	
	/**
	 * 新增用户促销策略
	 * @param UserStrategyVO strategy 新增的用户促销策略
	 * @return boolean 是否新增成功
	 */
	public boolean addUserStrategy(UserStrategyVO strategy) throws RemoteException{
		UserStrategyPO po = UserStrategyPOVOConvertor.voToPO(strategy);
		po.setStrategyCategoryEnum(StrategyCategoryEnum.USRESTRATEGY);
		SetPromotionIdBl.getInstance().setId(po);
		return service.addUserStrategy(po);
	}

	/**
	 * 新增总价促销策略
	 * @param TotalStrategyVO strategy 新增的总价促销策略
	 * @return boolean 是否新增成功
	 */
	public boolean addTotalStrategy(TotalStrategyVO strategy) throws RemoteException{
		TotalStrategyPO po = TotalStrategyPOVOConvertor.voToPO(strategy);
		po.setStrategyCategoryEnum(StrategyCategoryEnum.TOTALSTRATEGY);
		SetPromotionIdBl.getInstance().setId(po);
		return service.addTotalStrategy(po);
	}

	/**
	 * 新增组合促销策略
	 * @param ComboStrategyVO strategy 新增的组合促销策略
	 * @return boolean 是否新增成功
	 */
	public boolean addComoboStrategy(ComboStrategyVO strategy) throws RemoteException {
		ComboStrategyPO po = ComboStrategyPOVOConvertor.voToPO(strategy);
		po.setStrategyCategoryEnum(StrategyCategoryEnum.COMBOSTRATEGY);
		SetPromotionIdBl.getInstance().setId(po);
		return service.addComboStrategy(po);
	}
	
	
}
