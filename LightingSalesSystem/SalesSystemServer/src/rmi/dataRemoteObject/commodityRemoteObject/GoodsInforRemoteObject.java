package rmi.dataRemoteObject.commodityRemoteObject;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;

import blImpl.commodityBl.GoodsInforController;
import blService.commodityBlService.GoodsInforBlService;
import vo.GoodsVO;

public class GoodsInforRemoteObject extends UnicastRemoteObject implements GoodsInforBlService{


	/**
	 *
	 */
	private static final long serialVersionUID = 8726186239027025302L;

	public GoodsInforRemoteObject() throws RemoteException {
		super();
	}

	GoodsInforBlService service=new GoodsInforController();

	@Override
	public boolean ifGoodsExist(String goodsName, String goodsType) throws RemoteException {
		// TODO Auto-generated method stub
		return service.ifGoodsExist(goodsName,goodsType);
	}

	@Override
	public boolean ifGoodsExist(String goodsID) throws RemoteException {
		// TODO Auto-generated method stub
		return service.ifGoodsExist(goodsID);
	}

	@Override
	public ArrayList<GoodsVO> fuzzyEnquiry(String keyWord) throws RemoteException {
		// TODO Auto-generated method stub
		return service.fuzzyEnquiry(keyWord);
	}

	@Override
	public GoodsVO goodsINfor(String goodsTag) throws RemoteException {
		// TODO Auto-generated method stub
		return service.goodsINfor(goodsTag);
	}

	@Override
	public boolean modifyGoodsInfor(GoodsVO vo) throws RemoteException {
		// TODO Auto-generated method stub
		return service.modifyGoodsInfor(vo);
	}


	}
