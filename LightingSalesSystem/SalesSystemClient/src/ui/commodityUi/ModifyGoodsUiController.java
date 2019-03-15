package ui.commodityUi;

import java.io.IOException;
import java.rmi.RemoteException;

import blService.commodityBlService.GoodsClassificationBlService;
import blService.commodityBlService.GoodsInforBlService;
import blStubs.commodityBlStubs.GoodsClassificationService_Stub;
import blStubs.commodityBlStubs.GoodsInforService_Stub;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import rmi.remoteHelper.CommodityRemoteHelper;
import ui.uiAssistants.StageSize;
import vo.ClassificationVO;
import vo.GoodsVO;

public class ModifyGoodsUiController {
	@FXML private TextField newGoodsName;
	@FXML private TextField newGoodsType;
	@FXML private TextField newGoodsClassification;
	@FXML private TextField newCostPrice;
	@FXML private TextField newSellingPrice;
	@FXML private TextField newCurrentCostPrice;
	@FXML private TextField newCurrentSellingPrice;


private Stage currentStage;
private GoodsVO theGoods;
public void setStage(Stage stage){
	currentStage=stage;
}
public void initModifyGoodsInfor(GoodsVO goods){
	theGoods=goods;
	newGoodsName.setPromptText(theGoods.getName());
	newGoodsClassification.setPromptText(theGoods.getClassification());
	newGoodsType.setPromptText(theGoods.getType());
	newCostPrice.setPromptText(theGoods.getCostPrice());
	newSellingPrice.setPromptText(theGoods.getSellingPrice());
	newCurrentCostPrice.setPromptText(theGoods.getCurrentCostPrice());
	newCurrentSellingPrice.setPromptText(theGoods.getCurrentSellingPrice());
}

@FXML protected void confirmModifyGoodsInfor(){
	String newName=newGoodsName.getText();
	String newClassification=newGoodsClassification.getText();
	String newType=newGoodsType.getText();
	String newCost=newCostPrice.getText();
	String newSelling=newSellingPrice.getText();
	String newCurrentCost=newCurrentCostPrice.getText();
	String newCurrentSelling=newCurrentSellingPrice.getText();

	 boolean goodsExist=true;
     boolean classificationExist=true;
     boolean costPriceFormat=true;
     boolean sellingPriceFormat=true;
     boolean cuCostPriFormat=true;
     boolean cuSellPriFormat=true;

     GoodsInforBlService goodsInforSer=CommodityRemoteHelper.getInstance().getGoodsInforBlService();

	try{
		if(newName.equals("")||newName.equals(newGoodsName.getPromptText())){
		  if(newType.equals("")||newType.equals(newGoodsType.getPromptText())){
			  //名字和型号都没改
            goodsExist=false;
		  }else{
			  //没改名字，改了型号
			String name=newGoodsName.getPromptText();
			goodsExist=goodsInforSer.ifGoodsExist(name,newType);
			if(goodsExist){
				 Alert goodsExistErr=new Alert(Alert.AlertType.ERROR,"该商品已存在");
	        	 goodsExistErr.showAndWait();
			}else{
				theGoods.setType(newType);
			}
		  }
	    }else{
		  if(newType.equals("")||newType.equals(newGoodsType.getPromptText())){
			  //改了名字没改型号
			  String goodsType=newGoodsType.getPromptText();
			  goodsExist=goodsInforSer.ifGoodsExist(newName,goodsType);
			  if(goodsExist){
					 Alert goodsExistErr=new Alert(Alert.AlertType.ERROR,"该商品已存在");
		        	 goodsExistErr.showAndWait();
				}else{
					theGoods.setName(newName);
				}
		  }else{
			  //改了名字也改了型号
			  goodsExist=goodsInforSer.ifGoodsExist(newName,newType);
			  if(goodsExist){
					 Alert goodsExistErr=new Alert(Alert.AlertType.ERROR,"该商品已存在");
		        	 goodsExistErr.showAndWait();
				}else{
					theGoods.setName(newName);
					theGoods.setType(newType);
				}
		  }
	    }
	}catch(RemoteException e){
		e.printStackTrace();
	}
		//检查新的商品名称+型号对应的商品是否已存在
		if(newClassification.equals("")||newClassification.equals(newGoodsClassification.getPromptText())){

		}else{
			GoodsClassificationBlService classificationSer=CommodityRemoteHelper.getInstance().getGoodsClassificationBlService();
	          try{
	          classificationExist=classificationSer.ifClassificationExist(newClassification);
	          }catch(RemoteException e){
	             e.printStackTrace();
	        	  }

	          if(classificationExist==false){
	        	  Alert classNotExistErr=new Alert(Alert.AlertType.ERROR,"所属类别不存在");
	        	  classNotExistErr.showAndWait();
	          }else{
	        	  try{
	    	          ClassificationVO classificationVO=classificationSer.classificationInfor(newClassification);
	    	          String newClassificationName=classificationVO.getName();
	    	          theGoods.setClassification(newClassificationName);
	    	          }catch(RemoteException e){
	    	             e.printStackTrace();
	    	        	  }

	          }
		}
        //检查新的父类别是否存在

		 if(newCost.equals("")||newCost.equals(newCostPrice.getPromptText())){

		 }else{
			 if(Double.parseDouble(newCost)<0.0){
	        	  //默认输入的是数字。。。。
	        	  //输入的价格为负
	        	  costPriceFormat=false;
	        	  Alert priceFormatErr=new Alert(Alert.AlertType.ERROR,"商品进价不能是负数");
	        	  priceFormatErr.showAndWait();
	          }else{
                 theGoods.setCostPrice(newCost);
	          }
		 }

		 if(newSelling.equals("")||newSelling.equals(newSellingPrice.getPromptText())){

		 }else{
			 if(Double.parseDouble(newSelling)<0.0){
	        	  //默认输入的是数字。。。。
	        	  //输入的价格为负
				  sellingPriceFormat=false;
	        	  Alert priceFormatErr=new Alert(Alert.AlertType.ERROR,"商品零售价不能是负数");
	        	  priceFormatErr.showAndWait();
	          }else{
                theGoods.setSellingPrice(newSelling);
	          }
		 }

        if(newCurrentCost.equals("")||newCurrentCost.equals(newCurrentCostPrice.getPromptText())){

		 }else{
	          if(Double.parseDouble(newCurrentCost)<0.0){
	        	  //默认输入的是数字。。。。
	        	  //如果是新商品，操作人员最近进价填进价
	        	  //输入的价格为负
	        	  cuCostPriFormat=false;
	        	  Alert priceFormatErr=new Alert(Alert.AlertType.ERROR,"商品最近进价不能是负数");
	        	  priceFormatErr.showAndWait();
	          }else{
                theGoods.setCurrentCostPrice(newCurrentCost);
	          }

		 }

        if(newCurrentSelling.equals("")||newCurrentSelling.equals(newCurrentSellingPrice.getPromptText())){

		 }else{
			 if(Double.parseDouble(newCurrentSelling)<0.0){
	        	  //默认输入的是数字。。。。
	        	  //如果是新商品，操作人员最近零售价填零售价
	        	  //输入的价格为负
	        	  cuSellPriFormat=false;
	        	  Alert priceFormatErr=new Alert(Alert.AlertType.ERROR,"商品最近零售价不能是负数");
	        	  priceFormatErr.showAndWait();
	          }else{
                theGoods.setCurrentSellingPrice(newCurrentSelling);
	          }

		 }
        if((goodsExist==false)&&classificationExist&&costPriceFormat&&sellingPriceFormat&&cuCostPriFormat&&cuSellPriFormat){
            //所有格式都正确：该商品不存在，其所属类别存在，进价、零售价、最近进价、最近零售价都是非负
            //之后将它写入数据
            try{

            	goodsInforSer.modifyGoodsInfor(theGoods);
            }catch(RemoteException e){
            	e.printStackTrace();
            }
            Alert modifyGoodsSuccess=new Alert(Alert.AlertType.INFORMATION,"成功修改商品信息！");
      	    modifyGoodsSuccess.showAndWait();
      	   //成功修改商品之后返回GoodsInforUi并更新ui信息
      	    backToGoodsInforUi();
            }

}

@FXML protected void cancelModifyGoodsInfor(){
	backToGoodsInforUi();
}
private void backToGoodsInforUi(){
	GoodsInforUiStarter starter=new GoodsInforUiStarter();
	starter.initGoodsInforUi(currentStage, theGoods.getNumber());
}
}
