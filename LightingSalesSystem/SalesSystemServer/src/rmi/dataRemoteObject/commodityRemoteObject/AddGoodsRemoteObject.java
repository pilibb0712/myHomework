package rmi.dataRemoteObject.commodityRemoteObject;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;


import blImpl.commodityBl.AddGoodsController;
import blService.commodityBlService.AddGoodsBlService;
import vo.GoodsVO;
import vo.StockInOutVO;

public class AddGoodsRemoteObject extends UnicastRemoteObject implements AddGoodsBlService{


		/**
	 *
	 */
	private static final long serialVersionUID = -5128341735611895750L;

		public AddGoodsRemoteObject() throws RemoteException {
			super();
		}

		AddGoodsBlService service=new AddGoodsController();

		@Override
		public boolean addNewGoods(GoodsVO goodsVO, StockInOutVO stockVO) throws RemoteException {
			// TODO Auto-generated method stub
			return service.addNewGoods(goodsVO, stockVO);
		}

		@Override
		public boolean add(StockInOutVO vo) throws RemoteException {
			// TODO Auto-generated method stub
			return service.add(vo);
		}




		}