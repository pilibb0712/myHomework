package blImpl.promotionBl;

import java.rmi.RemoteException;
import java.util.ArrayList;

import assistant.convertors.UserStrategyPOVOConvertor;
import assistant.exception.Myexception;
import assistant.type.StrategyCategoryEnum;
import blInteract.commodityInteract.CommodityBlInteractServiceFactory;
import blInteract.commodityInteract.GoodsListService;
import blService.promotionBlService.UserStrategyBlService;
import po.UserStrategyPO;
import vo.GoodsVO;
import vo.UserStrategyVO;

public class UserStrategyBlController implements UserStrategyBlService{

	private PromotionBl bl = new PromotionBl();//处理促销策略的业务逻辑对象
	private static final StrategyCategoryEnum STRATEGY_TYPE = StrategyCategoryEnum.USRESTRATEGY;//单据的种类
	
	
	/**
	 * 得到所有现有的用户促销策略清单
	 * @return ArrayList<UserStrategyVO> 用户促销策略清单
	 */
	@Override
	public ArrayList<UserStrategyVO> getStrategyList() throws RemoteException,Myexception {
		return bl.getUserStrategyList();
	}
	

	/**
	 * 删除促销策略
	 * @return boolean是否删除成功
	 */
	@Override
	public boolean deleteStrategy(String strategyId) throws RemoteException,Myexception {
		return bl.deleteStrategy(STRATEGY_TYPE, strategyId);
	}

	/**
	 * 新增用户促销策略
	 * @param UserStrategyVO strategy 新增的用户促销策略
	 * @return boolean是否新增成功
	 */
	@Override
	public boolean addStrategy(UserStrategyVO strategy) throws RemoteException,Myexception {
		return bl.addUserStrategy(strategy);
	}

	/**
	 * 更新用户促销策略
	 * @param UserStrategyVO strategy 新增的用户促销策略
	 * @return boolean是否新增成功
	 */
	@Override
	public boolean updateStrategy(UserStrategyVO newPromotionStrategyVO) throws RemoteException,Myexception {
		UserStrategyPO po = UserStrategyPOVOConvertor.voToPO(newPromotionStrategyVO);
		return bl.updateStrategy(STRATEGY_TYPE, po.getId(), po);
	}

	/**
	 * 得到商品的信息
	 * @param String goodsTag 商品的标签
	 * @return GoodsVO 商品的信息
	 */
	@Override
	public ArrayList<GoodsVO> getGoodsList() throws RemoteException,Myexception {
		CommodityBlInteractServiceFactory factory = new CommodityBlInteractServiceFactory();
		GoodsListService service = factory.getGoodsListBlService();
		return service.getGoodsList();
	}	

}
