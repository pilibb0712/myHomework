package blDrivers.commodityBlDrivers;

import blImpl.commodityBl.UpdateClassificationOfGoods;
import vo.GoodsVO;

public class UpdateClassificationOfGoods_Driver {
	public void drive(UpdateClassificationOfGoods update){
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


      if(update.updateOldClassification(goods)){
    	  //test updateOldClassification(String goodsID)
  		System.out.println("Update Old Classification of the goods successfully");
  	}else{
  		System.out.println("Update Old Classification of the goods unsuccessfully");
  	}

      if(update.updateGoodsClassification(goods)){
    	  //test updateGoodsClassification(GoodsVO vo)
  		System.out.println("Update the goods successfully");
  	}else{
  		System.out.println("Update the goods unsuccessfully");
  	}

      if(update.updateNewClassification(goods)){
    	  //test updateNewClassification(GoodsVO vo)
  		System.out.println("Update New Classification of the goods successfully");
  	}else{
  		System.out.println("Update New Classification of the goods unsuccessfully");
  	}


    if(update.updateClassificationOfGoods(goods)){
    	  //test updateClassificationOfGoods(GoodsVO vo)
  		System.out.println("Update the classification of the goods successfully");
  	}else{
  		System.out.println("Update the classification of the goods unsuccessfully");
  	}
	}
}
