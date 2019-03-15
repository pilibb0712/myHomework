package blImpl.commodityBl;

import java.util.ArrayList;

import dataImpl.commodityData.GoodsClassificationData;
import po.ClassificationPO;
import po.GoodsPO;

public class ID {
	public String rootClassificationID(){
		//根类别ID：C+两位十进制数（根类别数递增决定）
		int numOfRoots=0;
		GoodsClassificationData classificationData=new GoodsClassificationData();
		ArrayList<ClassificationPO> allClassifications=classificationData.finds();
		for(int i=0;i<=allClassifications.size()-1;i++){
			ClassificationPO classification=allClassifications.get(i);
			String father=classification.getFatherClassification();
			if(father==null){
				numOfRoots++;
			}
		}
		numOfRoots++;
		//加上这个新的类别之后根类别的数量
		String newRootClassificationID="C"+String.format("%02d",numOfRoots);
		return newRootClassificationID;
	}
	public String sonClassificationID(String fatherClassification){
		//子类别ID：C+父类别ID+两位十进制数（由所属父类别下子类别数递增决定）
		String[] inforOfFather=fatherClassification.split("-");
		String fatherID=inforOfFather[0];
		GoodsClassificationData classificationData=new GoodsClassificationData();
		ClassificationPO fatherObj=classificationData.find(fatherID);
		ArrayList<String> sonClassificationList=fatherObj.getSonClassifacation();
		int numOfClassifications=0;
		if(sonClassificationList==null){

		}else{
			numOfClassifications=sonClassificationList.size();
		}
		numOfClassifications++;
		//加上这个新的类别之后子类别的数量
		String newSonClassificationID="C"+fatherID.substring(1, fatherID.length())+String.format("%02d",numOfClassifications);
		//fatherID.substring(1, fatherID.length()):去掉fatherID中的C，只留下数字
		return newSonClassificationID;
	}
	public String newGoodsID(String classification){
		//新商品ID：G+所属类别ID+两位十进制数（由所属类别下商品数递增决定）
		//名称相同，但是型号不同也是不同的商品
		GoodsClassificationData classificationData=new GoodsClassificationData();
		ClassificationPO classificationObj=classificationData.find(classification);
		String classificationID=classificationObj.getNumber();
		ArrayList<String> goodsList=classificationObj.getGoodsList();
		int numOfGoods=0;
		if(goodsList==null){

		}else{
			numOfGoods=goodsList.size();
		}
		numOfGoods++;
		//加上这个新的商品之后子商品的数量
		String newGoodsID="G"+classificationID.substring(1, classificationID.length())+String.format("%02d", numOfGoods);
		//classificationID.substring(1, classificationID.length()):去掉classificationID中的C,只留下数字
		return newGoodsID;
	}
}
