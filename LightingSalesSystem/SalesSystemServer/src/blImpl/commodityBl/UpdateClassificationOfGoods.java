package blImpl.commodityBl;

import java.util.ArrayList;

import dataImpl.commodityData.GoodsClassificationData;
import dataImpl.commodityData.GoodsData;
import po.ClassificationPO;
import po.GoodsPO;
import assistant.convertors.CommodityPOVOConvertor;
import vo.GoodsVO;

public class UpdateClassificationOfGoods {

	public boolean updateOldClassification(GoodsVO goods){
		//将这个商品从这个类别的商品列表删掉

		//注：如果是修改商品类别
		//注：这个操作一定要在更改该商品的父类别（updateGoodsClassification(GoodsVO goods)）之前操作
		//注：因为这时候这个goodsvo已经是的父类别已经是新类别，但数据文件中还没更新
		//注：故根据它的id找到它在数据文件中保存po，找到它的原来的父类
		//注：否则它的父类别已经更改了

		//注：但如果仅仅是删除商品
		//注：就是直接使用这个方法就行了

		String goodsID=goods.getNumber();
		GoodsData goodsData=new GoodsData();
		GoodsPO preGoods=goodsData.find(goodsID);
		String preClassification=preGoods.getClassification();
		GoodsClassificationData classificationData=new GoodsClassificationData();
		ClassificationPO preClassificationObj=classificationData.find(preClassification);
		ArrayList<String> goodsList=preClassificationObj.getGoodsList();
		if(goodsList==null){

		}else{
			for(int i=0;i<=goodsList.size()-1;i++){
				String currentGoods=goodsList.get(i);
				String[] currentGoodsInfor=currentGoods.split("-");
				String currentGoodsID=currentGoodsInfor[0];
				if(currentGoodsID.equals(goodsID)){
					goodsList.remove(i);
					break;
				}
			}
		}
		preClassificationObj.setGoodsList(goodsList);
		classificationData.update(preClassificationObj);
		return true;
	}

	public boolean updateNewClassification(GoodsVO goods){
		//将这个商品加入该类别的商品列表中

		//注：如果是修改商品类别
		//注：这个操作一定要在更改该商品的父类别（updateGoodsClassification(GoodsVO goods)）之后操作
		//注：因为是对这个商品的新的父类别进行操作的

		//注：但如果仅仅是增加新商品
		//注：就是直接使用这个方法就行了
		String newClassification=goods.getClassification();
		GoodsClassificationData classificationData=new GoodsClassificationData();
		ClassificationPO newClassificationObj=classificationData.find(newClassification);
		ArrayList<String> goodsList=newClassificationObj.getGoodsList();

		String goodsID=goods.getNumber();
		String goodsName=goods.getName();
		String goodsType=goods.getType();
		String goodsInfor=goodsID+"-"+goodsName+"-"+goodsType;

		if(goodsList==null){
			ArrayList<String> iniGoodsList=new ArrayList<String>();
			iniGoodsList.add(goodsInfor);
			newClassificationObj.setGoodsList(iniGoodsList);
		}else{
			goodsList.add(goodsInfor);
			newClassificationObj.setGoodsList(goodsList);
		}
		classificationData.update(newClassificationObj);
		return true;
	}

	public boolean updateGoodsClassification(GoodsVO goods){
		//更新该商品
		CommodityPOVOConvertor turn=new CommodityPOVOConvertor();
		GoodsPO goodsPO=turn.GoodsVOtoPO(goods);
		GoodsData goodsData=new GoodsData();
		goodsData.update(goodsPO);
		return true;
	}

	public boolean updateClassificationOfGoods(GoodsVO goods){
		//to use the other three functions
		//用于修改商品类别
		//先updateOldClassification(GoodsVO goods)在旧的父类别中删除该商品
		//然后updateGoodsClassification(GoodsVO goods)更新该商品的父类别
		//最后updateNewClassification(GoodsVO goods)在新的父类别中加入该商品
		updateOldClassification(goods);
		updateGoodsClassification(goods);
		updateNewClassification(goods);

		return true;
	}
}
