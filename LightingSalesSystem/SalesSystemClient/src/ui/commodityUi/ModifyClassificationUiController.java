package ui.commodityUi;

import java.io.IOException;
import java.rmi.RemoteException;
import java.util.ArrayList;

import blService.commodityBlService.GoodsClassificationBlService;
import blStubs.commodityBlStubs.GoodsClassificationService_Stub;
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

public class ModifyClassificationUiController {
	@FXML private TextField modifyClassificationName;
	@FXML private TextField modifyFatherClassification;
	private Stage currentStage;
	private ClassificationVO classification;
	public void setStage(Stage stage){
		currentStage=stage;

	}
	public void setClassification(ClassificationVO vo){
		classification=vo;
	}
	public void iniModifyClassificationUiInfor(){
		      modifyClassificationName.setPromptText(classification.getName());
		  if(classification.getFatherClassification()==null){
			  modifyFatherClassification.setPromptText("无");
		    }else{
		      modifyFatherClassification.setPromptText(classification.getFatherClassification());
		    }
	//设置classificationName和fatherClassification的默认值
		}

	@FXML protected void confirmModifyClassification(){
		String preName=modifyClassificationName.getPromptText();
		String preFather=modifyFatherClassification.getPromptText();
		String newName=modifyClassificationName.getText();
		String newFather=modifyFatherClassification.getText();
		boolean classExist=false;
		boolean fatherExist=true;
		try{
		GoodsClassificationBlService classificationSer=CommodityRemoteHelper.getInstance().getGoodsClassificationBlService();
		String preFatherID=" ";
		String preFatherName=" ";
		if(preFather.equals("无")){
			preFatherName="无";
		}else{
			preFatherID=preFather.split("-")[0];
			preFatherName=preFather.split("-")[1];
		}
		if(newFather.equals("")||newFather.equals(preFatherID)||newFather.equals(preFatherName)){
			//父类别没有改变
			if(newName.equals("")||newName.equals(preName)){
				//类别名称没有改变
				Alert notModifyInfor=new Alert(Alert.AlertType.INFORMATION,"没有进行任何修改。");
				notModifyInfor.showAndWait();
				backToClassificationInforUi();
				//没做任何操作，直接回到classificationScene1
			}else{
				//类别名称改变
			classExist=classificationSer.ifClassificationExist(newName);
			if(classExist==false){
				classification.setName(newName);
				classificationSer.modifyClassificationInfor(classification);
				Alert modifySuccess=new Alert(Alert.AlertType.INFORMATION,"成功修改类别名称！");
				modifySuccess.showAndWait();

				backToClassificationInforUi();
		         //成功修改类别后，回到classificationScene1并更新信息
			}else{
				Alert nameExistErr=new Alert(Alert.AlertType.ERROR,"该类别名称已存在！");
			   	nameExistErr.showAndWait();
			}
			}
		}else{
			//父类别改变
			fatherExist=classificationSer.ifClassificationExist(newFather);
			if(fatherExist){
				ClassificationVO newFatherVO=classificationSer.classificationInfor(newFather);
				ArrayList<String> sonGoodsList=newFatherVO.getGoodsList();
				if(sonGoodsList==null){
					if(newName.equals("")||newName.equals(preName)){
						//类别名称没有改变
						classification.setFatherClassification(newFatherVO.getNumber()+"-"+newFatherVO.getName());
						classificationSer.modifyClassificationInfor(classification);
						Alert modifySuccess=new Alert(Alert.AlertType.INFORMATION,"成功修改父类别！");
						modifySuccess.showAndWait();

						backToClassificationInforUi();
				         //成功修改类别后，回到classificationScene1并更新信息
					}else{
						//类别名称改变
					classExist=classificationSer.ifClassificationExist(newName);
					if(classExist==false){
						classification.setName(newName);
						classification.setFatherClassification(newFatherVO.getNumber()+"-"+newFatherVO.getName());
						classificationSer.modifyClassificationInfor(classification);
						Alert modifySuccess=new Alert(Alert.AlertType.INFORMATION,"成功修改类别名称和父类别！");
						modifySuccess.showAndWait();

						backToClassificationInforUi();
				         //成功修改类别后，回到classificationScene1并更新信息
					}else{
						Alert nameExistErr=new Alert(Alert.AlertType.ERROR,"该类别名称已存在！");
					   	nameExistErr.showAndWait();
					}
					}
				}else{
					//该类别下有商品，就算是曾经有过也算，就不能添加子类别
					Alert goodsExistError=new Alert(Alert.AlertType.ERROR,"该父类别下存在商品，不能添加子类别！");
					goodsExistError.showAndWait();
				}
			}else{
				Alert fatherNotExistErr=new Alert(Alert.AlertType.ERROR,"该父类别不存在！");
			   	fatherNotExistErr.showAndWait();
			}
		}
				}catch(RemoteException exception){
					exception.printStackTrace();
				}
	}

	@FXML protected void cancelModifyClassification(){
		backToClassificationInforUi();
		//没做任何操作，直接回到classificationScene1
	}

	private void backToClassificationInforUi(){
		ClassificationInforUiStarter starter=new ClassificationInforUiStarter();
		starter.initGoodsClassificationInfotUi(currentStage, classification.getNumber());
	}



}
