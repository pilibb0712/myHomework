package ui.commodityUi;

import java.io.IOException;
import java.rmi.RemoteException;

import assistant.type.BillCategoryEnum;
import blService.commodityBlService.ActualInventoryCheckBlService;
import blService.commodityBlService.SaveBillBlService;
import blStubs.commodityBlStubs.ActualInventoryCheckService_Stub;
import blStubs.commodityBlStubs.SaveBillService_Stub;
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
import vo.InventoryLossBillVO;
import vo.InventoryOverBillVO;
import vo.UserInfoVO;

public class RealAmountUiController {

	@FXML private Text goodsName;
	@FXML private Text goodsID;
	@FXML private Text classification;
	@FXML private Text type;
	@FXML private Text amount;
	@FXML private Text costPrice;
	@FXML private Text sellingPrice;
	@FXML private Text currentCostPrice;
	@FXML private Text currentSellingPrice;
	@FXML private TextField realAmount;

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

public void initRealAmountCheckUi(GoodsVO vo){
	theGoods=vo;

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

@FXML protected void confirmRealAmount(){
    String realAmountStr=realAmount.getText();
    double realAmount=Double.parseDouble(realAmountStr);
    if((double)(realAmount-(int)realAmount)!=0.0){
  	  Alert inputDoubleError=new Alert(Alert.AlertType.ERROR,"错误：商品数量不能是小数");
  	  inputDoubleError.showAndWait();
    }else{
  	  if(realAmount<0.0){
  	   Alert negativeInputError=new Alert(Alert.AlertType.ERROR,"错误：商品数量不能是负数");
  	   negativeInputError.showAndWait();
  	  }else{
  		  int dValue=0;
  			try{
  			  	ActualInventoryCheckBlService actulInvenService=CommodityRemoteHelper.getInstance().getActualInventoryCheckBlService();
  			    dValue=actulInvenService.actualInventoryCheck(theGoods.getNumber(), (int)realAmount);
  			    //dValue=actualAmount-systemAmount
 			  	}catch(RemoteException e){
			  		e.printStackTrace();
			  	}
  	    	   if(dValue>0){
  	    		   //get the inventoryOverBillVO
  	    		   //只将单据设置好保存在服务器，并不即时显示在界面，而是可以通过查看单据去看
  	    		   //但如果真的建立了单据，记得即时显示一个对话框告诉操作员一下就行了
  	    		   newInventoryOverBill(dValue);
  	    		   Alert inventoryOver=new Alert(Alert.AlertType.INFORMATION,"该商品实际库存超过系统库存，已自动生成库存报溢单！");
  	    	   	   inventoryOver.showAndWait();
  	    	   	   backToActualInveCheckUi();

  	    	   }else if(dValue<0){
  	    		 //get the inventoryLossBillVO
  	    		   newInventoryLossBill(dValue);
  	    		   Alert inventoryLoss=new Alert(Alert.AlertType.INFORMATION,"该商品实际库存少于系统库存，已自动生成库存报损单！");
  	    	   	   inventoryLoss.showAndWait();
  	    	   	   backToActualInveCheckUi();
  	    	   }else{
  	    		   Alert inventoryEqual=new Alert(Alert.AlertType.INFORMATION,"该商品实际库存等于系统库存，未发生任何库存报损或报溢情况！");
  	    	   	   inventoryEqual.showAndWait();
  	    	   	   backToActualInveCheckUi();
  	    	   }
  	  }
    }
	}

	private void newInventoryOverBill(int dValue){
		InventoryOverBillVO overBill=new InventoryOverBillVO();

		overBill.setId(null);
		overBill.setBillStateEnum(null);
		overBill.setApproveDate(null);
		overBill.setApprover(null);
		overBill.setApproverComment(null);
		overBill.setCreateDate(null);
		overBill.setCreaterComment(null);
		overBill.setExecutor(null);
		overBill.setFinishDate(null);
		overBill.setSelected(false);

  	UserInfoVO operator=new UserInfoVO();
  	operator.setId(operatorID);
  	operator.setName(operatorName);

  	overBill.setCreater(operator);
  	overBill.setCategoryEnum(BillCategoryEnum.INVENTORY_OVER_BILL);
  	overBill.setGoodsID(theGoods.getNumber());
  	overBill.setGoodsName(theGoods.getName());
  	overBill.setGoodsType(theGoods.getType());
  	if(theGoods.getCurrentCostPrice()==null){
  		overBill.setGoodsPrice(theGoods.getCurrentSellingPrice());
  	}else{
  		overBill.setGoodsPrice(theGoods.getCurrentCostPrice());
  	}
  	overBill.setSystemAmount(String.valueOf(theGoods.getAmount()));
  	overBill.setRealAmount(String.valueOf(realAmount.getText()));
  	overBill.setOverAmount(String.valueOf(dValue));
  	try{
  	SaveBillBlService saveBillSer=CommodityRemoteHelper.getInstance().getSaveBillBlService();
  	saveBillSer.saveInventoryOverBill(overBill);
  	}catch(RemoteException e){
  		e.printStackTrace();
  	}

	}
  private void newInventoryLossBill(int dValue){
  	InventoryLossBillVO lossBill=new InventoryLossBillVO();

  	lossBill.setId(null);
  	lossBill.setBillStateEnum(null);
  	lossBill.setApproveDate(null);
  	lossBill.setApprover(null);
  	lossBill.setApproverComment(null);
  	lossBill.setCreateDate(null);
  	lossBill.setCreaterComment(null);
  	lossBill.setExecutor(null);
  	lossBill.setFinishDate(null);
  	lossBill.setSelected(false);

  	UserInfoVO operator=new UserInfoVO();
  	operator.setId(operatorID);
  	operator.setName(operatorName);

  	lossBill.setCreater(operator);
  	lossBill.setCategoryEnum(BillCategoryEnum.INVENTORY_LOSS_BILL);
  	lossBill.setGoodsID(theGoods.getNumber());
  	lossBill.setGoodsName(theGoods.getName());
  	lossBill.setGoodsType(theGoods.getType());
  	if(theGoods.getCurrentSellingPrice()==null){
  		lossBill.setGoodsPrice(theGoods.getCurrentCostPrice());
  	}else{
  		lossBill.setGoodsPrice(theGoods.getCurrentSellingPrice());
  	}
  	lossBill.setSystemAmount(String.valueOf(theGoods.getAmount()));
  	lossBill.setRealAmount(String.valueOf(realAmount.getText()));
  	lossBill.setLossAmount(String.valueOf(-dValue));
  	try{
  	SaveBillBlService saveBillSer=CommodityRemoteHelper.getInstance().getSaveBillBlService();
  	saveBillSer.saveInventoryLossBill(lossBill);
  	}catch(RemoteException e){
  		e.printStackTrace();
  	}
}

  @FXML protected void cancelRealAmountUi(){
		//back to actualInvenCheck0UI
		backToActualInveCheckUi();
	}
	private void backToActualInveCheckUi(){
		ActualInventoryCheckUiStarter starter=new ActualInventoryCheckUiStarter();
		starter.initActualInventoryCheckUi(currentStage, operatorName, operatorID);
	}

}
