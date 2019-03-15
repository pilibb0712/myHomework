package rmi.dataRemoteObject.promotionRemoteObject;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;

import assistant.exception.Myexception;
import blImpl.promotionBl.TotalStrategyBlController;
import blService.promotionBlService.TotalStrategyBlService;
import vo.GoodsVO;
import vo.TotalStrategyVO;

public class TotalStrategyRemoteObject extends UnicastRemoteObject implements TotalStrategyBlService{
	

	/**
	 * 
	 */
	private static final long serialVersionUID = 3448836936546010474L;

	public TotalStrategyRemoteObject() throws RemoteException {
		super();
	}

	private TotalStrategyBlService service = new TotalStrategyBlController();
	
	@Override
	public ArrayList<TotalStrategyVO> getStrategyList() throws RemoteException, Myexception {
		return service.getStrategyList();
	}

	@Override
	public boolean deleteStrategy(String strategyId) throws RemoteException, Myexception {
		return service.deleteStrategy(strategyId);
	}

	@Override
	public boolean addStrategy(TotalStrategyVO strategy) throws RemoteException, Myexception {
		return service.addStrategy(strategy);
	}

	@Override
	public boolean updateStrategy(TotalStrategyVO newPromotionStrategyVO) throws RemoteException, Myexception {
		return service.updateStrategy(newPromotionStrategyVO);
	}

	@Override
	public ArrayList<GoodsVO> getGoodsList() throws RemoteException, Myexception {
		return service.getGoodsList();
	}

}
