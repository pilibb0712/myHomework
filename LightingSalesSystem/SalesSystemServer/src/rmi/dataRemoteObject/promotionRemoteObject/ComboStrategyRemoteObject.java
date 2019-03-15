package rmi.dataRemoteObject.promotionRemoteObject;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;

import javax.print.ServiceUI;

import assistant.exception.Myexception;
import blImpl.promotionBl.ComboStrategyBlController;
import blService.promotionBlService.ComboStrategyBlService;
import vo.ComboStrategyVO;
import vo.GoodsVO;

public class ComboStrategyRemoteObject extends UnicastRemoteObject implements ComboStrategyBlService{

	/**
	 * 
	 */
	private static final long serialVersionUID = 661416866985103335L;

	public ComboStrategyRemoteObject() throws RemoteException {
		super();
	}
	
	private ComboStrategyBlService service = new ComboStrategyBlController();
	
	@Override
	public ArrayList<ComboStrategyVO> getStrategyList() throws RemoteException, Myexception {
		return service.getStrategyList();
	}

	@Override
	public boolean deleteStrategy(String strategyId) throws RemoteException, Myexception {
		return service.deleteStrategy(strategyId);
	}

	@Override
	public boolean addStrategy(ComboStrategyVO strategy) throws RemoteException, Myexception {
		return service.addStrategy(strategy);
	}

	@Override
	public boolean updateStrategy(ComboStrategyVO newPromotionStrategyVO) throws RemoteException, Myexception {
		return service.updateStrategy(newPromotionStrategyVO);
	}

	@Override
	public ArrayList<GoodsVO> getGoodsList() throws RemoteException, Myexception {
		return service.getGoodsList();
	}

}
