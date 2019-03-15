package rmi.dataRemoteObject.commodityRemoteObject;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;


import blImpl.commodityBl.DeleteGoodsController;
import blService.commodityBlService.DeleteGoodsBlService;
import vo.GoodsVO;
import vo.StockInOutVO;

public class DeleteGoodsRemoteObject extends UnicastRemoteObject implements DeleteGoodsBlService{


	/**
	 *
	 */
	private static final long serialVersionUID = 3846030289024026130L;

	public DeleteGoodsRemoteObject() throws RemoteException {
		super();
	}

	DeleteGoodsBlService service=new DeleteGoodsController();

	@Override
	public boolean delete(StockInOutVO vo) throws RemoteException {
		// TODO Auto-generated method stub
		return service.delete(vo);
	}

	@Override
	public boolean obsoleteGoods(GoodsVO vo) throws RemoteException {
		// TODO Auto-generated method stub
		return service.obsoleteGoods(vo);
	}

	@Override
	public int compareTowarning(String goodsID, int currentAmount) throws RemoteException {
		// TODO Auto-generated method stub
		return service.compareTowarning(goodsID, currentAmount);
	}


	}
