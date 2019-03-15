package ui.commodityUi;

import java.io.IOException;
import java.rmi.RemoteException;

import blService.commodityBlService.GoodsInforBlService;
import blStubs.commodityBlStubs.GoodsInforService_Stub;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import rmi.remoteHelper.CommodityRemoteHelper;
import ui.uiAssistants.StageSize;
import vo.ClassificationVO;
import vo.GoodsVO;

public class GoodsInforUiController {
	@FXML private Text goodsName;
	@FXML private Text goodsID;
	@FXML private Text classification;
	@FXML private Text type;
	@FXML private Text amount;
	@FXML private Text costPrice;
	@FXML private Text sellingPrice;
	@FXML private Text currentCostPrice;
	@FXML private Text currentSellingPrice;

private Stage currentStage;
private GoodsVO theGoods;

public void setStage(Stage stage){
	currentStage=stage;
}
public void initGoodsInfor(String GoodsID){
	try{
        GoodsInforBlService goodsInfor=CommodityRemoteHelper.getInstance().getGoodsInforBlService();
        GoodsVO Goods=goodsInfor.goodsINfor(GoodsID);
        theGoods=Goods;
	}catch(RemoteException e){
		e.printStackTrace();
	}
        goodsName.setText("名称："+theGoods.getName());
		goodsID.setText("编号："+theGoods.getNumber());
		classification.setText("所属类别："+theGoods.getClassification());
		type.setText("型号："+theGoods.getType());
		amount.setText(String.valueOf("库存数量："+theGoods.getAmount()));
		costPrice.setText("进价："+theGoods.getCostPrice());
		sellingPrice.setText("零售价："+theGoods.getSellingPrice());
		currentCostPrice.setText("最近进价："+theGoods.getCurrentCostPrice());
		currentSellingPrice.setText("最近零售价："+theGoods.getCurrentSellingPrice());
}
@FXML protected void confirmGoodsInfor(){
	backToGoodsInforManaUi();
}

@FXML protected void initModifyGoodsInforUi(){
	//初始化商品信息修改界面
	ModifyGoodsUiStarter starter=new ModifyGoodsUiStarter();
	starter.initModifyGoodsUi(currentStage, theGoods);
}

private void backToGoodsInforManaUi(){
	GoodsInforManagementUiStarter starter=new GoodsInforManagementUiStarter();
	starter.initGoodsInforManagementUi(currentStage);
}
}
