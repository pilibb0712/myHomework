package ui.commodityUi;

import java.io.IOException;
import java.rmi.RemoteException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Optional;

import assistant.type.BillCategoryEnum;
import assistant.utility.Date;
import blService.commodityBlService.DeleteGoodsBlService;
import blService.commodityBlService.GoodsInforBlService;
import blService.commodityBlService.SaveBillBlService;
import blStubs.commodityBlStubs.DeleteGoodsService_Stub;
import blStubs.commodityBlStubs.GoodsInforService_Stub;
import blStubs.commodityBlStubs.SaveBillService_Stub;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import rmi.remoteHelper.CommodityRemoteHelper;
import ui.uiAssistants.StageSize;
import vo.GoodsVO;
import vo.InventoryWarningBillVO;
import vo.StockInOutVO;
import vo.UserInfoVO;

public class StockOutUiController {
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
	@FXML private TextField inputCurrentSellingPriceOfStock;
	@FXML private TextField productionDateOfStock;
	//properties of stock out

private Stage currentStage;
private String operatorID;
private String operatorName;
private GoodsVO theGoods;
public void setStage(Stage stage){
	currentStage=stage;
}
public void setOperator(String id,String name){
	operatorID=id;
	operatorName=name;
}
public void initDeleteUiInfor(GoodsVO goods){
	//商品出库界面
	    theGoods=goods;

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
@FXML protected void confirmStocKOut(){
	 String StockAmount=stockAmount.getText();
    String productionDate=productionDateOfStock.getText();
    String currentSellingPrice=inputCurrentSellingPriceOfStock.getText();

    boolean amountFormat=true;
    boolean productionDateFormat=true;
    boolean currentSellingPriceFormat=true;

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
   if(currentSellingPrice.equals("")){

   }else{
         if(Double.parseDouble(currentSellingPrice)<0.0){
  	  //默认输入的是数字。。。。
     //输入的价格为负
  	      currentSellingPriceFormat=false;
  	      Alert priceFormatErr=new Alert(Alert.AlertType.ERROR,"商品最近零售价不能是负数");
  	      priceFormatErr.showAndWait();
         }else{

         }
   }

   if(amountFormat&&productionDateFormat&currentSellingPriceFormat){
   	//所有格式都正确：出库数量不是小数且非负，生产日期符合yyyy-MM-dd格式，最近进价非负
       //之后将它写入数据
   	 stockOut();
   	 Alert deleteGoodsSuccess=new Alert(Alert.AlertType.INFORMATION,"商品成功出库！");
  	    deleteGoodsSuccess.showAndWait();
  	     //成功出库之后，检查warning值
        int dValue=compareWarning();
        if(dValue<=0){
       	 //当前库存小于警戒值，生成库存报警单
       	 newWarningBill(dValue);
       	 Alert inventoryWarning=new Alert(Alert.AlertType.INFORMATION,"该商品当前库存低于(等于)警戒值，已自动生成库存报警单！");
	   	 inventoryWarning.showAndWait();
        }else{
       	 //dValue>0 说明现存库存大于警戒值，不会生成库存报警单
        }
        //成功出库，并检查库存数量后，回到deleteGoodsUi0
        backToDeleteGoodsUi();
    }

}
private void stockOut(){
	   String StockAmount=stockAmount.getText();
	   String productionDate=productionDateOfStock.getText();
	   String currentSellingPrice=inputCurrentSellingPriceOfStock.getText();

      StockInOutVO stock=new StockInOutVO();
      stock.setAmount((Integer.parseInt(StockAmount))*(-1));
      //出库数量的int为负数
      if(currentSellingPrice.equals("")){
   	   //如果进来了库存报损、报溢单这样的，减少商品数量时，是不填价格和出厂日期的
   	   //到时候显示这条记录的时候，库存报损价格设置为商品信息的最近零售价，但出厂日期不会显示的，这就知道是库存报损了
   	   //但是事实上是根据单据进出货的，单据上是有价格的，所以通常currentSellingPrice肯定不会是“”
   	   stock.setCurrentSellingPrice(theGoods.getCurrentSellingPrice());
      }else{
   	   stock.setCurrentSellingPrice(currentSellingPrice);
      }
      stock.setCurrentCostPrice(null);
      stock.setGoodsID(theGoods.getNumber());
      stock.setGoodsName(theGoods.getName());
      stock.setOperatorName(operatorName);
      if(productionDate.equals("")){
   	   stock.setProductionDate(null);
      }else{
   	   stock.setProductionDate(productionDate);
      }
      Date currentTime=new Date();
      stock.setTime(currentTime);

      try{
      DeleteGoodsBlService deleteGoodsSer=CommodityRemoteHelper.getInstance().getDeleteGoodsBlService();
      deleteGoodsSer.delete(stock);
      }catch(RemoteException e){
   	   e.printStackTrace();
      }

}

private int compareWarning(){
	//商品出库之后与警戒值比较
	//返回与警戒值的差值
	String GoodsID=theGoods.getNumber();
	int preAmount=theGoods.getAmount();
	String stockAmoun=stockAmount.getText();
	int currentAmount=preAmount-Integer.parseInt(stockAmoun);
	int dValue=0;
	try{
	DeleteGoodsBlService deleteGoodsSer=CommodityRemoteHelper.getInstance().getDeleteGoodsBlService();
    dValue=deleteGoodsSer.compareTowarning(GoodsID, currentAmount);
	}catch(RemoteException e){
		e.printStackTrace();
	}
	return dValue;
	}

private void newWarningBill(int dValue){

	InventoryWarningBillVO warningBill=new InventoryWarningBillVO();

	warningBill.setId(null);
	warningBill.setBillStateEnum(null);
	warningBill.setApproveDate(null);
	warningBill.setApprover(null);
	warningBill.setApproverComment(null);
	warningBill.setCreateDate(null);
	warningBill.setCreaterComment(null);
	warningBill.setExecutor(null);
	warningBill.setFinishDate(null);
	warningBill.setSelected(false);


	UserInfoVO operator=new UserInfoVO();
	operator.setId(operatorID);
	operator.setName(operatorName);

	warningBill.setCreater(operator);
	warningBill.setCategoryEnum(BillCategoryEnum.INVENTORY_WARNING_BILL);
	warningBill.setGoodsID(theGoods.getNumber());
	warningBill.setGoodsType(theGoods.getType());
	warningBill.setGoodsName(theGoods.getName());
	if(inputCurrentSellingPriceOfStock.getText().equals("")){
		//如果是库存报损出库的话，操作员不会填最近零售价，就设置为商品的最近零售价
		warningBill.setGoodsPrice(theGoods.getCurrentSellingPrice());
	}else{
	warningBill.setGoodsPrice(inputCurrentSellingPriceOfStock.getText());
	}

	int preAmount=theGoods.getAmount();
	String stockAmoun=stockAmount.getText();
	int currentAmount=preAmount-Integer.parseInt(stockAmoun);
	int warningAmount=currentAmount+(-dValue);
	warningBill.setSystemAmount(String.valueOf(currentAmount));
	warningBill.setWarningAmount(String.valueOf(warningAmount));
	warningBill.setLossAmount(String.valueOf(-dValue));
	//lossAmount is active
	try{
	SaveBillBlService saveBillSer=CommodityRemoteHelper.getInstance().getSaveBillBlService();
	saveBillSer.saveInventoryWarningBill(warningBill);
	}catch(RemoteException e){
		e.printStackTrace();
	}


}

@FXML protected void cancelStockOut(){
	backToDeleteGoodsUi();
}

@FXML protected void obsoleteTheGoods(){
//删除商品界面
	if(theGoods.getAmount()>0){
		Alert existGoodsError=new Alert(Alert.AlertType.ERROR,"仓库中还存在该商品，请先全部出库后再删除该商品！");
		existGoodsError.showAndWait();
  	    //如果还有商品，就得先全部出库后，才能删除该商品
	}else{
	Alert obsoleteGoodsConfirm=new Alert(Alert.AlertType.CONFIRMATION,"你确定要彻底删除这个商品？");
	    Optional<ButtonType> choice=obsoleteGoodsConfirm.showAndWait();
	    if(choice.isPresent()&&(choice.get()==ButtonType.OK)){
	    	//用户选择确定删除该商品
	    	obsolete();
	    	Alert obsoleteGoodsSuccess=new Alert(Alert.AlertType.INFORMATION,"成功删除该商品！");
	  	    obsoleteGoodsSuccess.showAndWait();
	  	    //删除完商品后回到deleteGoodsUi0
	  	  backToDeleteGoodsUi();
	  	 }else{

	    }
	}
}
private void obsolete(){
	String GoodsID=theGoods.getNumber();
	try{
	GoodsInforBlService goodsInforSer=CommodityRemoteHelper.getInstance().getGoodsInforBlService();
	GoodsVO theGoods=goodsInforSer.goodsINfor(GoodsID);
	DeleteGoodsBlService deleteGoodsSer=CommodityRemoteHelper.getInstance().getDeleteGoodsBlService();
   deleteGoodsSer.obsoleteGoods(theGoods);
}catch(RemoteException e){
	e.printStackTrace();
}
}

private void backToDeleteGoodsUi(){
	DeleteGoodsUiStarter starter=new DeleteGoodsUiStarter();
	starter.initDeleteGoodsUi(currentStage, operatorID, operatorName);
}
}
