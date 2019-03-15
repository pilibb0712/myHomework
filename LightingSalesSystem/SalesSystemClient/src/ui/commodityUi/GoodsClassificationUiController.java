/**
 * 仓库管理员进行库存分类管理的界面
 * @author BeibeiZhang
 * @version 2017.11.19
 *
 */
package ui.commodityUi;

import java.io.IOException;
import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.Optional;

import blService.commodityBlService.GoodsClassificationBlService;
import blStubs.commodityBlStubs.GoodsClassificationService_Stub;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import rmi.remoteHelper.CommodityRemoteHelper;
import ui.mainUi.wareKeeperMainUi.WareKeeperMainUiStarter;
import ui.uiAssistants.Fonts;
import vo.ClassificationTreeVO;
import vo.ClassificationVO;

public class GoodsClassificationUiController {
	@FXML private GridPane classificationsPane;
	private Stage currentStage;

	public void setStage(Stage stage){
		currentStage=stage;
	}
	public void initAndUpdateClassificationTree(){
		//初始化商品分类管理界面的所有类别按钮
		//也用作商品分类增删改后的类别界面的类别更新
		ClassificationTreeVO classificationTree=new ClassificationTreeVO();
		try{
	GoodsClassificationBlService classificationSer=CommodityRemoteHelper.getInstance().getGoodsClassificationBlService();
	classificationTree=classificationSer.getClassificationTree();
		}catch(RemoteException exception){
			exception.printStackTrace();
		}
	ArrayList<String[]> treeLayers=classificationTree.getClassificationLayer();
	classificationsPane.getChildren().removeAll();
	//classificationPane中的所有组件先清空，将新的类别结构重新加进去
	for(int i=0;i<=treeLayers.size()-1;i++){
		String[] layer=treeLayers.get(i);
		for(int j=0;j<=layer.length-1;j++){
			String classification=layer[j];
			String[] infors=classification.split("-");
			String classID=infors[0];
			String className=infors[1];
			Button classificationBu=new Button();
			classificationBu.setText(className);
			//类别按钮上只显示类别名称
			classificationBu.setId(classID);
			classificationBu.setPrefWidth(250);
			classificationBu.setPrefHeight(60);
			classificationBu.setFont(Fonts.BUTTON_FONT);
			//classificationBu.getStylesheets().add("commodityUi/GoodsClassificationUi0.css");
			classificationBu.getStyleClass().add("usercase-buttons");
			classificationBu.setOnAction((ActionEvent e)->{
				showClassificationInforUi(classID);
			});
			classificationsPane.add(classificationBu, j, i);
		}
	}
	}

	private void showClassificationInforUi(String classID){
       ClassificationInforUiStarter starter=new ClassificationInforUiStarter();
       starter.initGoodsClassificationInfotUi(currentStage, classID);
	}

@FXML	protected void initAddNewClassificationUI(){
	//增加新的根类别界面
	AddRootClassificationUiStarter starter=new AddRootClassificationUiStarter();
	starter.initAddRootClassifictaionUi(currentStage);

	}


@FXML	protected void returnBack(){
        WareKeeperMainUiStarter starter=new WareKeeperMainUiStarter();
        starter.wareKeeperMainUi(currentStage, null, null);
	}

}
