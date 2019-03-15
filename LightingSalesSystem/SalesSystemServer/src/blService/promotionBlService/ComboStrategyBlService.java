package blService.promotionBlService;

import java.rmi.Remote;
import java.rmi.RemoteException;

import java.util.ArrayList;

import assistant.exception.Myexception;
import vo.ComboStrategyVO;
import vo.GoodsVO;

public interface ComboStrategyBlService extends Remote{
	/**
	 * 得到所有现有的组合促销策略清单
	 * @return ArrayList<ComboStrategyVO> 组合促销策略清单
	 */
	public ArrayList<ComboStrategyVO> getStrategyList() throws RemoteException,Myexception; 
		
	/**
	 * 删除促销策略
	 * @return boolean是否删除成功
	 */
	public boolean deleteStrategy(String strategyId) throws RemoteException,Myexception; 
		

	/**
	 * 新增组合促销策略
	 * @param ComboStrategyVO strategy 新增的组合促销策略
	 * @return boolean是否新增成功
	 */
	public boolean addStrategy(ComboStrategyVO strategy) throws RemoteException,Myexception; 
		

	/**
	 * 更新组合促销策略
	 * @param ComboStrategyVO strategy 新增的组合促销策略
	 * @return boolean是否新增成功
	 */
	public boolean updateStrategy(ComboStrategyVO newPromotionStrategyVO) throws RemoteException,Myexception; 
		
	/**
	 * 得到商品的信息
	 * @param String goodsTag 商品的标签
	 * @return GoodsVO 商品的信息
	 */
	/**
	 * 得到商品的信息
	 * @param String goodsTag 商品的标签
	 * @return GoodsVO 商品的信息
	 */
	public ArrayList<GoodsVO> getGoodsList() throws RemoteException,Myexception; 
		

}
