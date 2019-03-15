package blImpl.commodityBl;

import java.rmi.RemoteException;
import java.util.ArrayList;

import blService.commodityBlService.GoodsInforBlService;
import dataImpl.commodityData.GoodsData;
import po.GoodsPO;
import assistant.convertors.CommodityPOVOConvertor;
import vo.GoodsVO;

public class GoodsInforController implements GoodsInforBlService{

	@Override
	public boolean ifGoodsExist(String goodsName,String goodsType) throws RemoteException{
		GoodsData goodsData=new GoodsData();
		GoodsPO goodsPO=goodsData.find(goodsName,goodsType);
		if(goodsPO==null){
			return false;
		}else{
			return true;
		}
	}
	public boolean ifGoodsExist(String goodsID) throws RemoteException {
		// TODO Auto-generated method stub
		GoodsData goodsData=new GoodsData();
		GoodsPO goodsPO=goodsData.find(goodsID);
		if(goodsPO==null){
			return false;
		}else{
			return true;
		}
	}

	public ArrayList<GoodsVO> fuzzyEnquiry(String keyWord) throws RemoteException{
		//keyword：id或者名称中的关键字
		ArrayList<GoodsVO> relatedGoodsVOs=new ArrayList<GoodsVO>();
		GoodsData goodsData=new GoodsData();
		ArrayList<GoodsPO> relatedGoodsPOs=goodsData.fuzzyFinds(keyWord);
		for(int i=0;i<=relatedGoodsPOs.size()-1;i++){
			GoodsPO theGoodsPO=relatedGoodsPOs.get(i);
			CommodityPOVOConvertor poTOvo=new CommodityPOVOConvertor();
			GoodsVO theGoodsVO=poTOvo.GoodsPOtoVO(theGoodsPO);
			relatedGoodsVOs.add(theGoodsVO);
		}
		return relatedGoodsVOs;
		//如果没有相关的商品，那么relatedGoodsVOs的size会等于0，而不是null
	}

	@Override
	public GoodsVO goodsINfor(String goodsID) throws RemoteException {
		// TODO Auto-generated method stub
		GoodsData goodsData=new GoodsData();
		GoodsPO theGoodsPO=goodsData.find(goodsID);
		CommodityPOVOConvertor poTOvo=new CommodityPOVOConvertor();
		GoodsVO theGoodsVO=poTOvo.GoodsPOtoVO(theGoodsPO);
		return theGoodsVO;
	}



	@Override
	public boolean modifyGoodsInfor(GoodsVO vo) throws RemoteException {
		// TODO Auto-generated method stub
			UpdateClassificationOfGoods updateClassificationOfGoods=new UpdateClassificationOfGoods();
			updateClassificationOfGoods.updateClassificationOfGoods(vo);
			//如果更改了商品所属类别，更新原父类别与新父类别的商品清单，并更新商品
			//如果没有更新商品所属类别，也更新一遍父类别，因为商品的名称与型号可能发生变化，不如删掉重新添加一遍

		return true;
	}


}
