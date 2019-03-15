package ui.commodityUi;

import java.io.IOException;
import java.rmi.RemoteException;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import assistant.utility.Date;
import blService.commodityBlService.AddGoodsBlService;
import blStubs.commodityBlStubs.AddGoodsService_Stub;
import blStubs.commodityBlStubs.GoodsInforService_Stub;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import rmi.remoteHelper.CommodityRemoteHelper;
import ui.uiAssistants.StageSize;
import vo.GoodsVO;
import vo.StockInOutVO;

public class StockInUiController {
	@FXML private Text goodsNameOfStock;
	@FXML private Text goodsIDOfStock;
	@FXML private Text classificationOfStock;
	@FXML private Text typeOfStock;
	@FXML private Text amountOfStock;
	@FXML private Text costPriceOfStock;
	@FXML private Text sellingPriceOfStock;
	@FXML private Text currentCostPriceOfStock;
	@FXML private Text currentSellingPriceOfStock;
    @FXML private TextField stockAmount;
	@FXML private TextField inputCurrentCostPriceOfStock;
	@FXML private TextField productionDateOfStock;
	//properties of stock in

	private Stage currentStage;
	private String operator;
	private GoodsVO theGoods;
public void setStage(Stage stage){
	currentStage=stage;
}
public void setOperator(String operatorName){
	operator=operatorName;
}
public void initAddUiInfor(GoodsVO vo){
//仓库管理员进行商品入库的界面
	theGoods=vo;
	goodsNameOfStock.setText("名称："+theGoods.getName());
	goodsIDOfStock.setText("编号："+theGoods.getNumber());
	classificationOfStock.setText("所属类别："+theGoods.getClassification());
	typeOfStock.setText("型号："+theGoods.getType());
	amountOfStock.setText(String.valueOf("库存数量："+theGoods.getAmount()));
	costPriceOfStock.setText("进价："+theGoods.getCostPrice());
	sellingPriceOfStock.setText("零售价："+theGoods.getSellingPrice());
	currentCostPriceOfStock.setText("最近进价："+theGoods.getCurrentCostPrice());
	currentSellingPriceOfStock.setText("最近零售价："+theGoods.getCurrentSellingPrice());
}

@FXML protected void confirmStocKIn(){
 String StockAmount=stockAmount.getText();
 String productionDate=productionDateOfStock.getText();
 String currentCostPrice=inputCurrentCostPriceOfStock.getText();

 boolean amountFormat=true;
 boolean productionDateFormat=true;
 boolean currentCostPriceFormat=true;

 double amountDouble=Double.parseDouble(StockAmount);
 if((double)(amountDouble-(int)amountDouble)!=0.0){
	  //代表输入的amount是小数
	  amountFormat=false;
	  Alert amountFormatErr=new Alert(Alert.AlertType.ERROR,"商品数量不能是小数");
	  amountFormatErr.showAndWait();
 }else{
	  int amountInteger=Integer.parseInt(StockAmount);
	  if(amountInteger<0){
		  //代表输入的amount是负数
		  amountFormat=false;
		  Alert amountFormatErr=new Alert(Alert.AlertType.ERROR,"商品数量不能是负数");
   	  amountFormatErr.showAndWait();
	  }else{

	  }
 }

 if(productionDate.equals("")){

 }else{
	 try{
   		SimpleDateFormat format=new SimpleDateFormat("yyyy-MM-dd");
   		format.parse(productionDate);
   		}catch(ParseException e){
   			//商品的出厂日期不是yyyy-MM-dd格式
   			productionDateFormat=false;
   			Alert dateFormatErr=new Alert(Alert.AlertType.ERROR,"商品出厂日期格式错误");
       	    dateFormatErr.showAndWait();
   		}
 }
if(currentCostPrice.equals("")){

}else{
      if(Double.parseDouble(currentCostPrice)<0.0){
	  //默认输入的是数字。。。。
	  //如果是新商品，操作人员最近进价填进价
  //输入的价格为负
	      currentCostPriceFormat=false;
	      Alert priceFormatErr=new Alert(Alert.AlertType.ERROR,"商品最近进价不能是负数");
	      priceFormatErr.showAndWait();
      }else{

      }
}

if(amountFormat&&productionDateFormat&currentCostPriceFormat){
	//所有格式都正确：入库数量不是小数且非负，生产日期符合yyyy-MM-dd格式，最近进价非负
    //之后将它写入数据
	 stockIn();
	 Alert addGoodsSuccess=new Alert(Alert.AlertType.INFORMATION,"商品成功入库！");
	     addGoodsSuccess.showAndWait();
	     //成功入库之后返回addGoodsUi0
	     backToAddGoodsUi();
 }

}

@FXML protected void cancelStockIn(){
	backToAddGoodsUi();
}

private void stockIn(){
   String StockAmount=stockAmount.getText();
   String productionDate=productionDateOfStock.getText();
   String currentCostPrice=inputCurrentCostPriceOfStock.getText();

   StockInOutVO stock=new StockInOutVO();
   stock.setAmount(Integer.parseInt(StockAmount));
   if(currentCostPrice.equals("")){
	 //如果进来了库存报损、报溢单这样的，减少商品数量时，是不填价格和出厂日期的
	 //到时候显示这条记录的时候，价格设置为商品信息中的最近进价，但日期会显示为null，就知道是库存报溢单了
	 //但是事实上是根据单据进出货的，单据上是有价格的，所以通常currentSellingPrice肯定不会是“”
	     stock.setCurrentCostPrice(theGoods.getCurrentCostPrice());
   }else{
	   stock.setCurrentCostPrice(currentCostPrice);
   }
   stock.setCurrentSellingPrice(null);
   stock.setGoodsID(theGoods.getNumber());
   stock.setGoodsName(theGoods.getName());
   stock.setOperatorName(operator);
   if(productionDate.equals("")){
	   stock.setProductionDate(null);
   }else{
	   stock.setProductionDate(productionDate);
   }
   Date currentTime=new Date();
   stock.setTime(currentTime);

   try{
   AddGoodsBlService addGoodsSer=CommodityRemoteHelper.getInstance().getAddGoodsBlService();
   addGoodsSer.add(stock);
   }catch(RemoteException e){
	   e.printStackTrace();
   }

}
private void backToAddGoodsUi(){
	AddGoodsUiStarter starter=new AddGoodsUiStarter();
	starter.initAddGoodsUi(currentStage, operator);
}

}
