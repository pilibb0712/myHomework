/**
 * 仓库管理员进行实际库存的审查录入的界面
 * @author BeibeiZhang
 * @version 2017.11.19
 *
 */
package ui.commodityUi;

import java.io.IOException;
import java.rmi.RemoteException;

import assistant.type.BillCategoryEnum;
import blService.commodityBlService.GoodsInforBlService;
import blStubs.commodityBlStubs.ActualInventoryCheckService_Stub;
import blStubs.commodityBlStubs.GoodsInforService_Stub;
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
import ui.mainUi.wareKeeperMainUi.WareKeeperMainUiStarter;
import ui.uiAssistants.StageSize;
import vo.GoodsVO;
import vo.InventoryLossBillVO;
import vo.InventoryOverBillVO;
import vo.InventoryWarningBillVO;
import vo.UserInfoVO;

public class ActualInventoryCheckUiController {
	//interfaces for wareKeeperMainUi
	@FXML private TextField goodsIDInput;

	private String operatorName;
	private String operatorID;
	private Stage currentStage;

	public void setStage(Stage stage){
		currentStage=stage;
	}
	public void setOperator(String id,String name){
		operatorName=name;
		operatorID=id;
	}

	@FXML protected void confirmGoodsID(){
       String goodsTag=goodsIDInput.getText();
       try{
       GoodsInforBlService goodsInfor=CommodityRemoteHelper.getInstance().getGoodsInforBlService();
       //先用stub代替远程的GoodsService
       boolean existTheGoods=goodsInfor.ifGoodsExist(goodsTag);
       if(existTheGoods){
    	   GoodsVO goods=goodsInfor.goodsINfor(goodsTag);
    	   initRealAmountCheckUi(goods);
       }else{
    	   Alert goodsNotExistError=new Alert(Alert.AlertType.ERROR,"错误：不存在该商品");
    	   goodsNotExistError.showAndWait();
       }
       }catch(RemoteException e){
    	   e.printStackTrace();
       }

	}
	private void initRealAmountCheckUi(GoodsVO goods){
        RealAmountUiStarter starter=new RealAmountUiStarter();
        starter.initRealAmountUi(currentStage, goods, operatorID, operatorName);
	}
	@FXML protected void returnBack(){
		//back to wareKeeperMainUi
	       WareKeeperMainUiStarter starter=new WareKeeperMainUiStarter();
	       starter.wareKeeperMainUi(currentStage,null,null);
		}
}
