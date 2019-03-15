package ui.commodityUi;

import java.rmi.RemoteException;

import blService.commodityBlService.GoodsClassificationBlService;
import blStubs.commodityBlStubs.GoodsClassificationService_Stub;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import rmi.remoteHelper.CommodityRemoteHelper;
import vo.ClassificationVO;

public class AddRootClassificationUiController {
	@FXML private TextField classificationNameInput;

	private Stage currentStage;
	public void setStage(Stage stage){
	currentStage=stage;
}

@FXML protected void confirmNewRootClassificationName(){
    String nameInput=classificationNameInput.getText();
    try{
  	  GoodsClassificationBlService classificationSer=CommodityRemoteHelper.getInstance().getGoodsClassificationBlService();
  	  boolean ifNameExist=classificationSer.ifClassificationExist(nameInput);

    if(ifNameExist){
  	  //该类别名称已存在
  	  Alert nameExistErr=new Alert(Alert.AlertType.ERROR,"该类别名称已存在！");
  	  nameExistErr.showAndWait();
    }else{
  	  //该类别名称不存在
  	  ClassificationVO newRootClassification=new ClassificationVO();
  	  newRootClassification.setName(nameInput);
        newRootClassification.setFatherClassification(null);
        newRootClassification.setSonClassification(null);
        newRootClassification.setGoodsList(null);
        classificationSer.addNewClassification(newRootClassification);
        Alert addNewRootSuccess=new Alert(Alert.AlertType.INFORMATION,"成功增加新的根商品类别！");
        addNewRootSuccess.showAndWait();

        backToGoodsClassificationUi();
	      //成功增加新的根类别后，回到classificationScene0并更新Ui
    }
    }catch(RemoteException e){
  	  e.printStackTrace();
    }
}

@FXML protected void cancelAddNewClassification(){
    backToGoodsClassificationUi();
     //没做任何操作直接回到classificationScene0
}

private void backToGoodsClassificationUi(){
	GoodsClassificationUiStarter starter=new GoodsClassificationUiStarter();
	starter.initGoodsClassificationUi(currentStage);
}

}
