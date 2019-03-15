package blStubs.commodityBlStubs;

import java.rmi.RemoteException;

import blService.commodityBlService.DeleteGoodsBlService;
import vo.GoodsVO;
import vo.StockInOutVO;
public class DeleteGoodsService_Stub implements DeleteGoodsBlService{
	public boolean delete(StockInOutVO vo) throws RemoteException{
		return true;
	}
	public boolean obsoleteGoods(GoodsVO vo) throws RemoteException{
		return true;
	}
	public int compareTowarning(String goodsTag,int currentAmout) throws RemoteException{
		return 0;
		//we need three types of return value, they are >0 ,=0 ,<0???
	}

}
