/**
 * 仓库管理员进行商品增加和入库的界面
 * @author BeibeiZhang
 * @version 2017.11.19
 *
 */
package ui.commodityUi;

import java.io.IOException;
import java.rmi.RemoteException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import assistant.utility.Date;
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
import javafx.scene.text.Text;
import javafx.stage.Stage;
import rmi.remoteHelper.CommodityRemoteHelper;
import ui.mainUi.wareKeeperMainUi.WareKeeperMainUiStarter;
import ui.uiAssistants.StageSize;
import vo.GoodsVO;
import vo.StockInOutVO;

public class AddGoodsUiController {
	@FXML private TextField goodsID;
	//properties of add goods scene0
  private Stage currentStage;
  private String operator;

    public void setStage(Stage stage){
    	currentStage=stage;
    }

    public void setOperator(String operatorName){
    	operator=operatorName;
    }


    @FXML protected void confirmGoodsID(){
   	 boolean goodsExist=false;
   	 String GoodsID=goodsID.getText();
   	 GoodsInforBlService goodsInforSer=CommodityRemoteHelper.getInstance().getGoodsInforBlService();
       try{
      	 goodsExist=goodsInforSer.ifGoodsExist(GoodsID);

         if(goodsExist){
          GoodsVO goods=goodsInforSer.goodsINfor(GoodsID);
      	  initStockInUi(goods);
         }else{
      	   Alert goodsNotExistErr=new Alert(Alert.AlertType.ERROR,"该商品不存在");
     	   goodsNotExistErr.showAndWait();
         }
       }catch(RemoteException e){
       	  e.printStackTrace();
         }

   }


	@FXML protected void returnBack(){
		WareKeeperMainUiStarter starter=new WareKeeperMainUiStarter();
        starter.wareKeeperMainUi(currentStage, null, null);
	}

private void initStockInUi(GoodsVO vo){
	StockInUiStarter starter=new StockInUiStarter();
	starter.initStockInUi(currentStage, vo,operator);
}
@FXML protected void initAddNewGoodsUi(){
	//进入增加新商品界面
	AddNewGoodsUiStarter starter=new AddNewGoodsUiStarter();
	starter.initAddNewGoodsUi(currentStage, operator);
}


}
