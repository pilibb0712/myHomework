package blService.promotionBlService;

import java.util.ArrayList;

import assistant.exception.Myexception;
import assistant.type.StrategyCategoryEnum;
import vo.*;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface PromotionBlService extends Remote{
	
	 public ArrayList<UserStrategyVO> getUserStrategyList()throws RemoteException,Myexception;
	 public ArrayList<TotalStrategyVO> getTotalStrategyList()throws RemoteException,Myexception;
	 public ArrayList<ComboStrategyVO> getComboStrategyList()throws RemoteException,Myexception;
	 public boolean deleteStrategy(StrategyCategoryEnum strategyType ,String strategyId)
			 throws RemoteException,Myexception;
	 public boolean addUserStrategy(UserStrategyVO strategy)throws RemoteException,Myexception;
	 public boolean addTotalStrategy(TotalStrategyVO strategy)throws RemoteException,Myexception;
	 public boolean addComoboStrategy(ComboStrategyVO strategy)throws RemoteException,Myexception;
	 public boolean updateStrategy(StrategyCategoryEnum strategyType ,String strategyId,PromotionStrategyVO newPromotionStrategyVO)
			 throws RemoteException,Myexception ;
	 public GoodsVO goodsINfor(String goodsTag) throws RemoteException,Myexception;
}
