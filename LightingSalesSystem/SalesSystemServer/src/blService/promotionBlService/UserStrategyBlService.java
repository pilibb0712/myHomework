package blService.promotionBlService;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.ArrayList;

import assistant.exception.Myexception;
import vo.GoodsVO;
import vo.UserStrategyVO;

public interface UserStrategyBlService extends Remote{
	/**
	 * 得到所有现有的用户促销策略清单
	 * @return ArrayList<UserStrategyVO> 用户促销策略清单
	 */
	public ArrayList<UserStrategyVO> getStrategyList() throws RemoteException,Myexception; 
		
	/**
	 * 删除促销策略
	 * @return boolean是否删除成功
	 */
	public boolean deleteStrategy(String strategyId) throws RemoteException,Myexception; 
		

	/**
	 * 新增用户促销策略
	 * @param UserStrategyVO strategy 新增的用户促销策略
	 * @return boolean是否新增成功
	 */
	public boolean addStrategy(UserStrategyVO strategy) throws RemoteException,Myexception; 
		

	/**
	 * 更新用户促销策略
	 * @param UserStrategyVO strategy 新增的用户促销策略
	 * @return boolean是否新增成功
	 */
	public boolean updateStrategy(UserStrategyVO newPromotionStrategyVO) throws RemoteException,Myexception; 
		
	/**
	 * 得到商品的信息
	 * @param String goodsTag 商品的标签
	 * @return GoodsVO 商品的信息
	 */
	public ArrayList<GoodsVO> getGoodsList() throws RemoteException,Myexception; 
		

}
