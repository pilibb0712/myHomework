package blStubs.commodityBlStubs;

import java.rmi.RemoteException;

import blService.commodityBlService.AddGoodsBlService;
import vo.GoodsVO;
import vo.StockInOutVO;

public class AddGoodsService_Stub implements AddGoodsBlService{

public boolean addNewGoods(GoodsVO goodsVO,StockInOutVO stockVO ) throws RemoteException{
	return true;
}
public boolean add(StockInOutVO vo) throws RemoteException{
	return true;
}

}
