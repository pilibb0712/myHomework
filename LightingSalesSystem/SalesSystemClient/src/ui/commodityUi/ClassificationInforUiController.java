package ui.commodityUi;

import java.io.IOException;
import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.Optional;

import blService.commodityBlService.GoodsClassificationBlService;
import blStubs.commodityBlStubs.GoodsClassificationService_Stub;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import rmi.remoteHelper.CommodityRemoteHelper;
import ui.uiAssistants.StageSize;
import vo.ClassificationVO;

public class ClassificationInforUiController {
	@FXML private Text classificationName;
	@FXML private Text classificationID;
	@FXML private Text fatherClassification;
	@FXML private Text sonClassificationListLabel;
	@FXML private Text goodsListLabel;
	@FXML private VBox sonClassificationID;
	@FXML private VBox sonClassificationName;
	@FXML private VBox sonGoodsID;
	@FXML private VBox sonGoodsName;
	@FXML private VBox sonGoodsType;
	@FXML private ScrollPane scrollpane;

	private Stage currentStage;
	private ClassificationVO classification;
public void setStage(Stage stage){
	currentStage=stage;
}
public void setClassification(String id){
	try{
	GoodsClassificationBlService classificationSer=CommodityRemoteHelper.getInstance().getGoodsClassificationBlService();
    classification=classificationSer.classificationInfor(id);
	}catch(RemoteException e){
		e.printStackTrace();
	}
}
public void initAndUpdateClassificationInfor(){
	//初始化类别信息
    //也可作为类别增删改之后更新类别信息界面用
         String name=classification.getName();
         String id=classification.getNumber();
         String father=classification.getFatherClassification();
         classificationID.setText("类别编号："+id);
         classificationName.setText("类别名称："+name);
         if(father==null){
        	 fatherClassification.setText("父类别：无");
         }else{
         fatherClassification.setText("父类别："+father);
         }

         ArrayList<String> sonClassifications=classification.getSonClassifacation();
         ArrayList<String> sonGoods=classification.getGoodsList();

         if(sonClassifications==null||((sonClassifications!=null)&&(sonClassifications.size()==0))){
             sonClassificationListLabel.setText("子类别列表：无");
         }else{
        	 Label sonIDLabel=new Label();
             sonIDLabel.setId("sonIDLabel");
             sonIDLabel.setText("子类别编号");
             sonIDLabel.setFont(Font.font(18));
             sonClassificationID.getChildren().removeAll();
             sonClassificationID.getChildren().add(sonIDLabel);
             Label sonNameLabel=new Label();
             sonNameLabel.setId("sonNameLabel");
             sonNameLabel.setText("子类别名称");
             sonNameLabel.setFont(Font.font(18));
             sonClassificationName.getChildren().removeAll();
             sonClassificationName.getChildren().add(sonNameLabel);

        	 for(int i=0;i<=sonClassifications.size()-1;i++){
        		 String son=sonClassifications.get(i);
        		 String[] sonInfors=son.split("-");
        		 String sonId=sonInfors[0];
        		 String sonName=sonInfors[1];
        		 Text sonID=new Text();
        		 sonID.setText(sonId);
        		 sonID.setFont(Font.font(18));
        		 sonClassificationID.getChildren().add(sonID);
        		 Text sonNAME=new Text();
        		 sonNAME.setText(sonName);
        		 sonNAME.setFont(Font.font(18));
        		 sonClassificationName.getChildren().add(sonNAME);
        	 }
         }
         if(sonGoods==null||((sonGoods!=null)&&(sonGoods.size()==0))){
        	 goodsListLabel.setText("子商品列表：无");
         }else{
        	 Label sonGoodsIDLabel=new Label();
             sonGoodsIDLabel.setId("ssonGoodsIDLabel");
             sonGoodsIDLabel.setText("子商品编号");
             sonGoodsIDLabel.setFont(Font.font(18));
             sonGoodsID.getChildren().removeAll();
             sonGoodsID.getChildren().add(sonGoodsIDLabel);
             Label sonGoodsNameLabel=new Label();
             sonGoodsNameLabel.setId("sonGoodsNameLabel");
             sonGoodsNameLabel.setText("子商品名称");
             sonGoodsNameLabel.setFont(Font.font(18));
             sonGoodsName.getChildren().removeAll();
             sonGoodsName.getChildren().add(sonGoodsNameLabel);
             Label sonGoodsTypeLabel=new Label();
             sonGoodsTypeLabel.setId("sonGoodsTypeLabel");
             sonGoodsTypeLabel.setText("子商品型号");
             sonGoodsTypeLabel.setFont(Font.font(18));
             sonGoodsType.getChildren().removeAll();
             sonGoodsType.getChildren().add(sonGoodsTypeLabel);
        	 for(int i=0;i<=sonGoods.size()-1;i++){
        		 String good=sonGoods.get(i);
        		 String[] goodInfors=good.split("-");
        		 String goodId=goodInfors[0];
        		 String goodName=goodInfors[1];
        		 String goodType=goodInfors[2];
        		 Text goodID=new Text();
        		 goodID.setText(goodId);
        		 goodID.setFont(Font.font(18));
        		 sonGoodsID.getChildren().add(goodID);
        		 Text goodNAME=new Text();
        		 goodNAME.setText(goodName);
        		 goodNAME.setFont(Font.font(18));
        		 sonGoodsName.getChildren().add(goodNAME);
        		 Text goodTYPE=new Text();
        		 goodTYPE.setText(goodType);
        		 goodTYPE.setFont(Font.font(18));
        		 sonGoodsType.getChildren().add(goodTYPE);
        	 }
         }
}

@FXML protected void initAddClassificationUi(){
	//增加新的子类别界面
AddSonClassificationUiStarter starter=new AddSonClassificationUiStarter();
starter.initAddClassifictaionUi(currentStage, classification.getNumber(),classification.getName());

	}

@FXML protected void deleteClassification(){
	//删除旧的类别界面
	//只能删除没有商品的叶节点，如果不符合这个标准，将不能删除
ArrayList<String> sonClassifications=classification.getSonClassifacation();
ArrayList<String> goodsList=classification.getGoodsList();
if(sonClassifications==null||((sonClassifications!=null)&&(sonClassifications.size()==0))){
	if(goodsList==null||((goodsList!=null)&&(goodsList.size()==0))){
		Alert obsoleteClassificationConfirm=new Alert(Alert.AlertType.CONFIRMATION,"你确定要彻底删除这个类别？");
  	    Optional<ButtonType> choice=obsoleteClassificationConfirm.showAndWait();
  	    if(choice.isPresent()&&(choice.get()==ButtonType.OK)){
  	    	//用户选择确定删除该类别
  	    	try{
  	    		GoodsClassificationBlService classificationSer=CommodityRemoteHelper.getInstance().getGoodsClassificationBlService();
  	    		classificationSer.deleteClassification(classification);
  	    	}catch(RemoteException e){
  	    		e.printStackTrace();
  	    	}

  	    	Alert obsoleteClassificationSuccess=new Alert(Alert.AlertType.INFORMATION,"成功删除该类别！");
  	  	    obsoleteClassificationSuccess.showAndWait();

  	  	    backToGoodsClassificationUi();
         //成功删除该类别后，回到classificationScene0并更新Ui
  	  	 }else{

  	    }
	}else{
		Alert goodsExistErr=new Alert(Alert.AlertType.ERROR,"该类别下存在商品，请删除全部商品或修改商品类别之后再删除该类别！");
		goodsExistErr.showAndWait();
	}
}else{
	Alert sonClassExistErr=new Alert(Alert.AlertType.ERROR,"该类别下存在子类别，请删除全部子类别或修改子类别所属父类别之后再删除该类别！");
	sonClassExistErr.showAndWait();
}
	}

@FXML protected void initModifyClassificationUi(){
	//修改类别界面，只能修改类别的名称与父类别
   ModifyClassificationUiStarter starter=new ModifyClassificationUiStarter();
   starter.initModifyClassifictaionUi(currentStage, classification);
	}


@FXML protected void confirmClassification(){
	//确认该类别信息
	backToGoodsClassificationUi();
	//没做任何操作，直接回到classificationScene0
}
private void backToGoodsClassificationUi(){
	GoodsClassificationUiStarter starter=new GoodsClassificationUiStarter();
	starter.initGoodsClassificationUi(currentStage);
}
}
