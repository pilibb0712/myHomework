package blImpl.promotionBl;

import java.rmi.RemoteException;
import java.util.ArrayList;

import assistant.convertors.TotalStrategyPOVOConvertor;
import assistant.exception.Myexception;
import assistant.type.StrategyCategoryEnum;
import blInteract.commodityInteract.CommodityBlInteractServiceFactory;
import blInteract.commodityInteract.GoodsListService;
import blService.promotionBlService.TotalStrategyBlService;
import po.TotalStrategyPO;
import vo.GoodsVO;
import vo.TotalStrategyVO;

public class TotalStrategyBlController implements TotalStrategyBlService{
	private PromotionBl bl = new PromotionBl();//处理促销策略的业务逻辑对象
	private static final StrategyCategoryEnum STRATEGY_TYPE = StrategyCategoryEnum.TOTALSTRATEGY;//单据的种类
	
	
	/**
	 * 得到所有现有的总价促销策略清单
	 * @return ArrayList<TotalStrategyVO> 总价促销策略清单
	 */
	@Override
	public ArrayList<TotalStrategyVO> getStrategyList() throws RemoteException,Myexception {
		return bl.getTotalStrategyList();
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
	 * 新增总价促销策略
	 * @param TotalStrategyVO strategy 新增的总价促销策略
	 * @return boolean是否新增成功
	 */
	@Override
	public boolean addStrategy(TotalStrategyVO strategy) throws RemoteException,Myexception {
		return bl.addTotalStrategy(strategy);
	}

	/**
	 * 更新总价促销策略
	 * @param TotalStrategyVO strategy 新增的总价促销策略
	 * @return boolean是否新增成功
	 */
	@Override
	public boolean updateStrategy(TotalStrategyVO newPromotionStrategyVO) throws RemoteException,Myexception {
		TotalStrategyPO po = TotalStrategyPOVOConvertor.voToPO(newPromotionStrategyVO);
		return bl.updateStrategy(STRATEGY_TYPE, po.getId(), po);
	}

	/**
	 * 得到所有商品的信息
	 * @return GoodsVO 商品的信息
	 */
	@Override
	public ArrayList<GoodsVO> getGoodsList() throws RemoteException,Myexception {
		 CommodityBlInteractServiceFactory factory = new CommodityBlInteractServiceFactory();
		GoodsListService service = factory.getGoodsListBlService();
		return service.getGoodsList();
	}

	
}
