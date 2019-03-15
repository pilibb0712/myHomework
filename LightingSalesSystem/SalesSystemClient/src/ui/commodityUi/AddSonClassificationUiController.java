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


public class AddSonClassificationUiController {
	@FXML private TextField sonClassificationNameInput;

	private Stage currentStage;
	private String fatherClassificationID;
	private String fatherClassificationName;

	public void setStage(Stage stage){
		currentStage=stage;
	}
	public void setFather(String fatherID,String fatherName){
		fatherClassificationID=fatherID;
		fatherClassificationName=fatherName;
	}
	@FXML protected void confirmSonClassificationName(){
		 String nameInput=sonClassificationNameInput.getText();
	     try{
	   	  GoodsClassificationBlService classificationSer=CommodityRemoteHelper.getInstance().getGoodsClassificationBlService();
	   	  boolean ifNameExist=classificationSer.ifClassificationExist(nameInput);

	     if(ifNameExist){
	   	  //该类别名称已存在
	   	  Alert nameExistErr=new Alert(Alert.AlertType.ERROR,"该类别名称已存在！");
	   	  nameExistErr.showAndWait();
	     }else{
	   	  //该类别名称不存在
	   	  ClassificationVO newSonClassification=new ClassificationVO();
	   	  newSonClassification.setName(nameInput);
	         newSonClassification.setFatherClassification(fatherClassificationID+"-"+fatherClassificationName);
	         newSonClassification.setSonClassification(null);
	         newSonClassification.setGoodsList(null);
	         classificationSer.addClassification(newSonClassification);
	         Alert addNewSonSuccess=new Alert(Alert.AlertType.INFORMATION,"成功增加新的子商品类别！");
	         addNewSonSuccess.showAndWait();

	        backToClassificationInforUi();
	         //成功增加新的子类别后，回到classificationScene1并更新Ui
	     }
	     }catch(RemoteException e){
	   	  e.printStackTrace();
	     }
	}
	@FXML protected void cancelAddSonClassification(){
		 backToClassificationInforUi();
	    //不做任何修改回到classificationScene1即可
	}
	private void backToClassificationInforUi(){
		ClassificationInforUiStarter starter=new ClassificationInforUiStarter();
		starter.initGoodsClassificationInfotUi(currentStage, fatherClassificationID);
	}


}
