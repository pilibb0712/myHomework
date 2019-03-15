package blImpl.commodityBl;

import java.util.ArrayList;

import assistant.convertors.CommodityPOVOConvertor;
import blInteract.commodityInteract.GoodsListService;
import dataImpl.commodityData.GoodsData;
import po.GoodsPO;
import vo.GoodsVO;

public class GoodsListImpl implements GoodsListService{
	public ArrayList<GoodsVO> getGoodsList(){
		GoodsData goodsData=new GoodsData();
		ArrayList<GoodsPO> allGoodsPOs=goodsData.finds();
		ArrayList<GoodsVO> goodsList=new ArrayList<GoodsVO>();
		for(int i=0;i<=allGoodsPOs.size()-1;i++){
			GoodsPO theGoodsPO=allGoodsPOs.get(i);
			CommodityPOVOConvertor turn=new CommodityPOVOConvertor();
			GoodsVO theGoodsVO=turn.GoodsPOtoVO(theGoodsPO);
			goodsList.add(theGoodsVO);
		}
		return goodsList;
	}

}
