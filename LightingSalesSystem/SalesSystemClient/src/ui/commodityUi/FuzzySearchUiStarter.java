package ui.commodityUi;

import java.io.IOException;
import java.util.ArrayList;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import ui.uiAssistants.StageSize;
import vo.GoodsVO;

public class FuzzySearchUiStarter {
public void initFuzzySearchUi(Stage stage,ArrayList<GoodsVO> relatedGoods){
	try{
	FXMLLoader loader=new FXMLLoader(getClass().getResource("FuzzySearchUi.fxml"));
	Parent root=loader.load();

	Scene scene=new Scene(root,StageSize.STAGE_WIDTH,StageSize.STAGE_HEIGHT);

	FuzzySearchUiController controller=loader.getController();
	controller.setStage(stage);
	controller.initFuzzySearchUi(relatedGoods);

	stage.setTitle("Ä£ºýËÑË÷½á¹û");
	stage.setScene(scene);
	stage.show();

	}catch(IOException e){
		e.printStackTrace();
	}
}
}
