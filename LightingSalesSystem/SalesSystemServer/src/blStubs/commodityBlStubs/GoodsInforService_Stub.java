package blStubs.commodityBlStubs;

import java.rmi.RemoteException;
import java.util.ArrayList;

import blService.commodityBlService.GoodsInforBlService;
import vo.GoodsVO;

public class GoodsInforService_Stub implements GoodsInforBlService{
	public boolean ifGoodsExist(String goodsName,String goodsType) throws RemoteException{
		return true;
		//???
	}

	public boolean ifGoodsExist(String goodsTag) throws RemoteException{
		return true;
		//???
	}

	public ArrayList<GoodsVO> fuzzyEnquiry(String keyWord) throws RemoteException{
		ArrayList<GoodsVO> relatedGoods=new ArrayList<GoodsVO>();
		GoodsVO goods=new GoodsVO();
		goods.setName("AAa");
		goods.setNumber("11001");
		goods.setClassification("AA");
		goods.setType("00001A");
		goods.setAmount(100);
		goods.setCostPrice("100");
		goods.setSellingPrice("110");
		goods.setCurrentCostPrice("100");
		goods.setCurrentSellingPrice("110");
		relatedGoods.add(goods);
		return relatedGoods;
	}

	public GoodsVO goodsINfor(String goodsTag) throws RemoteException{
		GoodsVO goods=new GoodsVO();
		goods.setName("AAa");
		goods.setNumber("11001");
		goods.setClassification("AA");
		goods.setType("00001A");
		goods.setAmount(100);
		goods.setCostPrice("100");
		goods.setSellingPrice("110");
		goods.setCurrentCostPrice("100");
		goods.setCurrentSellingPrice("110");
		return goods;

	}

	public boolean modifyGoodsInfor(GoodsVO vo) throws RemoteException{
		return true;
	}


}
