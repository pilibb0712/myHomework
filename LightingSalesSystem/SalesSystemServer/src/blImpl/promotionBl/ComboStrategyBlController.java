package blImpl.promotionBl;

import java.rmi.RemoteException;
import java.util.ArrayList;

import assistant.convertors.ComboStrategyPOVOConvertor;
import assistant.exception.Myexception;
import assistant.type.StrategyCategoryEnum;
import blInteract.commodityInteract.CommodityBlInteractServiceFactory;
import blInteract.commodityInteract.GoodsListService;
import blService.promotionBlService.ComboStrategyBlService;
import po.ComboStrategyPO;
import vo.ComboStrategyVO;
import vo.GoodsVO;

public class ComboStrategyBlController implements ComboStrategyBlService{
	
	private PromotionBl bl = new PromotionBl();//处理促销策略的业务逻辑对象
	private static final StrategyCategoryEnum STRATEGY_TYPE = StrategyCategoryEnum.COMBOSTRATEGY;//单据的种类
	
	/**
	 * 得到所有现有的组合促销策略清单
	 * @return ArrayList<ComboStrategyVO> 组合促销策略清单
	 */
	@Override
	public ArrayList<ComboStrategyVO> getStrategyList() throws RemoteException,Myexception{
		return bl.getComoboStrategyList();
	}
		
	/**
	 * 删除促销策略
	 * @return boolean是否删除成功
	 */
	@Override
	public boolean deleteStrategy(String strategyId) throws RemoteException,Myexception{
		return bl.deleteStrategy(STRATEGY_TYPE, strategyId);
	}
		

	/**
	 * 新增组合促销策略
	 * @param ComboStrategyVO strategy 新增的组合促销策略
	 * @return boolean是否新增成功
	 */
	@Override
	public boolean addStrategy(ComboStrategyVO strategy) throws RemoteException,Myexception{
		return bl.addComoboStrategy(strategy);
	} 
		

	/**
	 * 更新组合促销策略
	 * @param ComboStrategyVO strategy 新增的组合促销策略
	 * @return boolean是否新增成功
	 */
	@Override
	public boolean updateStrategy(ComboStrategyVO newPromotionStrategyVO) 
			throws RemoteException,Myexception{
		ComboStrategyPO po = ComboStrategyPOVOConvertor.voToPO(newPromotionStrategyVO);
		return bl.updateStrategy(STRATEGY_TYPE, po.getId(), po);
	} 

	/**
	 * 得到所有商品的信息
	 * @return GoodsVO 商品的信息
	 */
	@Override
	public ArrayList<GoodsVO> getGoodsList() throws RemoteException,Myexception{
		 CommodityBlInteractServiceFactory factory = new CommodityBlInteractServiceFactory();
			GoodsListService service = factory.getGoodsListBlService();
			return service.getGoodsList();
	} 
		
}
