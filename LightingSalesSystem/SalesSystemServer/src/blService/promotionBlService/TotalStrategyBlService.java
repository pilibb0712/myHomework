package blService.promotionBlService;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.ArrayList;

import assistant.exception.Myexception;
import vo.GoodsVO;
import vo.TotalStrategyVO;

public interface TotalStrategyBlService extends Remote{
	/**
	 * 得到所有现有的总价促销策略清单
	 * @return ArrayList<TotalStrategyVO> 总价促销策略清单
	 */
	public ArrayList<TotalStrategyVO> getStrategyList() throws RemoteException,Myexception; 
		
	/**
	 * 删除促销策略
	 * @return boolean是否删除成功
	 */
	public boolean deleteStrategy(String strategyId) throws RemoteException,Myexception; 
		

	/**
	 * 新增总价促销策略
	 * @param TotalStrategyVO strategy 新增的总价促销策略
	 * @return boolean是否新增成功
	 */
	public boolean addStrategy(TotalStrategyVO strategy) throws RemoteException,Myexception; 
		

	/**
	 * 更新总价促销策略
	 * @param TotalStrategyVO strategy 新增的总价促销策略
	 * @return boolean是否新增成功
	 */
	public boolean updateStrategy(TotalStrategyVO newPromotionStrategyVO) throws RemoteException,Myexception; 
		
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
