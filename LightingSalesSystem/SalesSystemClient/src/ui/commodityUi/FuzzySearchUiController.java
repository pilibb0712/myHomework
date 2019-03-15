package ui.commodityUi;

import java.util.ArrayList;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import ui.uiAssistants.Fonts;
import vo.GoodsVO;

public class FuzzySearchUiController {
@FXML private VBox relatedGoodsPane;

private Stage currentStage;

public void setStage(Stage stage){
	currentStage=stage;
}
public void initFuzzySearchUi(ArrayList<GoodsVO> relatedGoods){
	for(int i=0;i<=relatedGoods.size()-1;i++){
		GoodsVO Goods=relatedGoods.get(i);
		String goodsID=Goods.getNumber();
		String goodsName=Goods.getName();
		String goodsType=Goods.getType();
		String goodsInforLabel=goodsID+"    "+goodsName+"    "+goodsType;
		Button theGoodsBu=new Button();
		theGoodsBu.setText(goodsInforLabel);
		theGoodsBu.setFont(Fonts.BUTTON_FONT);
		theGoodsBu.getStyleClass().add("usercase-buttons");
		theGoodsBu.setOnAction((ActionEvent e)->{
			initGoodsInforUi(goodsID);
		});
		relatedGoodsPane.getChildren().add(theGoodsBu);
       }
	}
private void initGoodsInforUi(String ID){
       GoodsInforUiStarter starter=new GoodsInforUiStarter();
       starter.initGoodsInforUi(currentStage, ID);
}
@FXML protected void returnFromFuzzySearch(){
	backToGoodsInforManaUi();
}
private void backToGoodsInforManaUi(){
	GoodsInforManagementUiStarter starter=new GoodsInforManagementUiStarter();
	starter.initGoodsInforManagementUi(currentStage);
}
}
