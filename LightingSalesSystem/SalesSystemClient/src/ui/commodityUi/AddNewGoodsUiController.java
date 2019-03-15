package ui.commodityUi;

import java.io.IOException;
import java.rmi.RemoteException;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import assistant.utility.Date;
import blService.commodityBlService.AddGoodsBlService;
import blService.commodityBlService.GoodsClassificationBlService;
import blService.commodityBlService.GoodsInforBlService;
import blStubs.commodityBlStubs.AddGoodsService_Stub;
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
import vo.StockInOutVO;

public class AddNewGoodsUiController {
	@FXML private TextField goodsNameOfNewGoods;
	@FXML private TextField classificationOfNewGoods;
	@FXML private TextField goodsTypeOfNewGoods;
	@FXML private TextField amountOfNewGoods;
	@FXML private TextField costPriceOfNewGoods;
	@FXML private TextField sellingPriceOfNewGoods;
	@FXML private TextField currentCostPriceOfNewGoods;
	@FXML private TextField currentSellingPriceOfNewGoods;
	@FXML private TextField productionDateOfNewGoods;
	//properties of add new goods


private Stage currentStage;
private String operator;
public void setStage(Stage stage){
	currentStage=stage;
}
public void setOperator(String operatorName){
	operator=operatorName;
}

@FXML protected void confirmAddNewGoods(){
    String GoodsName=goodsNameOfNewGoods.getText();
    String Classification=classificationOfNewGoods.getText();
    String GoodsType=goodsTypeOfNewGoods.getText();
    String Amount=amountOfNewGoods.getText();
    String CostPrice=costPriceOfNewGoods.getText();
    String SellingPrice=sellingPriceOfNewGoods.getText();
    String CurrentCostPrice=currentCostPriceOfNewGoods.getText();
    String CurrentSellingPrice=currentSellingPriceOfNewGoods.getText();
    String ProductionDate=productionDateOfNewGoods.getText();

    boolean goodsExist=true;
    boolean classificationExist=true;
    boolean amountFormat=true;
    boolean costPriceFormat=true;
    boolean sellingPriceFormat=true;
    boolean cuCostPriFormat=true;
    boolean cuSellPriFormat=true;
    boolean productionDateFormat=true;

    GoodsInforBlService goodsInforSer=CommodityRemoteHelper.getInstance().getGoodsInforBlService();
    try{
    goodsExist=goodsInforSer.ifGoodsExist(GoodsName, GoodsType);
    }catch(RemoteException e){
  	  e.printStackTrace();
    }

    if(goodsExist){
  	  Alert goodsExistErr=new Alert(Alert.AlertType.ERROR,"该商品已存在");
  	  goodsExistErr.showAndWait();
    }

    GoodsClassificationBlService classificationSer=CommodityRemoteHelper.getInstance().getGoodsClassificationBlService();
    try{
    classificationExist=classificationSer.ifClassificationExist(Classification);
    }catch(RemoteException e){
       e.printStackTrace();
  	  }

    if(classificationExist==false){
  	  Alert classNotExistErr=new Alert(Alert.AlertType.ERROR,"所属类别不存在");
  	  classNotExistErr.showAndWait();
    }

    double amountDouble=Double.parseDouble(Amount);
    if((double)(amountDouble-(int)amountDouble)!=0.0){
  	  //代表输入的amount是小数
  	  amountFormat=false;
  	  Alert amountFormatErr=new Alert(Alert.AlertType.ERROR,"商品数量不能是小数");
  	  amountFormatErr.showAndWait();
    }else{
  	  int amountInteger=Integer.parseInt(Amount);
  	  if(amountInteger<0){
  		  //代表输入的amount是负数
  		  amountFormat=false;
  		  Alert amountFormatErr=new Alert(Alert.AlertType.ERROR,"商品数量不能是负数");
      	  amountFormatErr.showAndWait();
  	  }else{

  	  }
    }

    if(Double.parseDouble(CostPrice)<0.0){
  	  //默认输入的是数字。。。。
  	  //输入的价格为负
  	  costPriceFormat=false;
  	  Alert priceFormatErr=new Alert(Alert.AlertType.ERROR,"商品进价不能是负数");
  	  priceFormatErr.showAndWait();
    }else{

    }

    if(Double.parseDouble(SellingPrice)<0.0){
  	  //默认输入的是数字。。。。
  	  //如果是新商品，操作人员零售价填进价
  	  //输入的价格为负
  	  sellingPriceFormat=false;
  	  Alert priceFormatErr=new Alert(Alert.AlertType.ERROR,"商品零售价不能是负数");
  	  priceFormatErr.showAndWait();
    }else{

    }

    if(Double.parseDouble(CurrentCostPrice)<0.0){
  	  //默认输入的是数字。。。。
  	  //如果是新商品，操作人员最近进价填进价
  	  //输入的价格为负
  	  cuCostPriFormat=false;
  	  Alert priceFormatErr=new Alert(Alert.AlertType.ERROR,"商品最近进价不能是负数");
  	  priceFormatErr.showAndWait();
    }else{

    }

    if(Double.parseDouble(CurrentSellingPrice)<0.0){
  	  //默认输入的是数字。。。。
  	  //如果是新商品，操作人员最近零售价填零售价
  	  //输入的价格为负
  	  cuSellPriFormat=false;
  	  Alert priceFormatErr=new Alert(Alert.AlertType.ERROR,"商品最近零售价不能是负数");
  	  priceFormatErr.showAndWait();
    }else{

    }

    try{
		SimpleDateFormat format=new SimpleDateFormat("yyyy-MM-dd");
		format.parse(ProductionDate);
		}catch(ParseException e){
			//商品的出厂日期不是yyyy-MM-dd格式
			productionDateFormat=false;
			Alert dateFormatErr=new Alert(Alert.AlertType.ERROR,"商品出厂日期格式错误");
    	    dateFormatErr.showAndWait();
		}

    if((goodsExist==false)&&classificationExist&&amountFormat&&costPriceFormat&&sellingPriceFormat&&cuCostPriFormat&&cuSellPriFormat&&productionDateFormat){
     //所有格式都正确：该商品不存在，其所属类别存在，数量是int且非负，进价、零售价、最近进价、最近零售价都是非负、生产日期也是yyyy-MM-dd格式
     //之后将它写入数据
     addNewGoods();
     Alert addNewGoodsSuccess=new Alert(Alert.AlertType.INFORMATION,"成功增加新商品并入库！");
	   addNewGoodsSuccess.showAndWait();
	   //成功增加新商品之后返回addGoodsUi0
	   backToAddGoodsUi();
     }


}

private void addNewGoods(){
	String GoodsName=goodsNameOfNewGoods.getText();
    String Classification=classificationOfNewGoods.getText();
    String GoodsType=goodsTypeOfNewGoods.getText();
    String Amount=amountOfNewGoods.getText();
    String CostPrice=costPriceOfNewGoods.getText();
    String SellingPrice=sellingPriceOfNewGoods.getText();
    String CurrentCostPrice=currentCostPriceOfNewGoods.getText();
    String CurrentSellingPrice=currentSellingPriceOfNewGoods.getText();
    String ProductionDate=productionDateOfNewGoods.getText();
    String classificationName=" ";
    try{
    GoodsClassificationBlService classificationSer=CommodityRemoteHelper.getInstance().getGoodsClassificationBlService();
    ClassificationVO classificationVO=classificationSer.classificationInfor(Classification);
    classificationName=classificationVO.getName();
    }catch(RemoteException e){
  	   e.printStackTrace();
     }

    GoodsVO newGoods=new GoodsVO();
    newGoods.setAmount(Integer.parseInt(Amount));
    newGoods.setClassification(classificationName);
    newGoods.setCostPrice(CostPrice);
    newGoods.setCurrentCostPrice(CurrentCostPrice);
    newGoods.setCurrentSellingPrice(CurrentSellingPrice);
    newGoods.setName(GoodsName);
    newGoods.setSellingPrice(SellingPrice);
    newGoods.setType(GoodsType);

    StockInOutVO stock=new StockInOutVO();
    stock.setAmount(Integer.parseInt(Amount));
    stock.setCurrentCostPrice(CurrentCostPrice);
    stock.setCurrentSellingPrice(null);
    stock.setGoodsName(GoodsName);
    stock.setOperatorName(operator);
    stock.setProductionDate(ProductionDate);
    Date currentTime=new Date();
    stock.setTime(currentTime);

    try{
    	AddGoodsBlService addGoodsSer=CommodityRemoteHelper.getInstance().getAddGoodsBlService();
    	 addGoodsSer.addNewGoods(newGoods, stock);
      }catch(RemoteException e){
 	   e.printStackTrace();
    }


}

@FXML protected void cancelAddNewGoods(){
	backToAddGoodsUi();
}

private void backToAddGoodsUi(){
	AddGoodsUiStarter starter=new AddGoodsUiStarter();
	starter.initAddGoodsUi(currentStage, operator);
}

}
