package rmi.dataRemoteObject.promotionRemoteObject;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;

import assistant.exception.Myexception;
import blImpl.promotionBl.UserStrategyBlController;
import blService.promotionBlService.UserStrategyBlService;
import vo.GoodsVO;
import vo.UserStrategyVO;

public class UserStrategyRemoteObject extends UnicastRemoteObject implements UserStrategyBlService{

	/**
	 * 
	 */
	private static final long serialVersionUID = -519875058315149055L;

	public UserStrategyRemoteObject() throws RemoteException {
		super();
	}

	private UserStrategyBlService service = new UserStrategyBlController();
	
	@Override
	public ArrayList<UserStrategyVO> getStrategyList() throws RemoteException, Myexception {
		return service.getStrategyList();
	}

	@Override
	public boolean deleteStrategy(String strategyId) throws RemoteException, Myexception {
		return service.deleteStrategy(strategyId);
	}

	@Override
	public boolean addStrategy(UserStrategyVO strategy) throws RemoteException, Myexception {
		return service.addStrategy(strategy);
	}

	@Override
	public boolean updateStrategy(UserStrategyVO newPromotionStrategyVO) throws RemoteException, Myexception {
		return service.updateStrategy(newPromotionStrategyVO);
	}

	@Override
	public ArrayList<GoodsVO> getGoodsList() throws RemoteException, Myexception {
		return service.getGoodsList();
	}

}
