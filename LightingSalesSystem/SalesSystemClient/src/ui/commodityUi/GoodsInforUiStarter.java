package ui.commodityUi;

import java.io.IOException;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import ui.uiAssistants.StageSize;

public class GoodsInforUiStarter {
public void initGoodsInforUi(Stage stage,String goodsID){
	try{
		FXMLLoader loader=new FXMLLoader(getClass().getResource("GoodsInforUi.fxml"));
		Parent root=loader.load();

		Scene scene=new Scene(root,StageSize.STAGE_WIDTH,StageSize.STAGE_HEIGHT);

		GoodsInforUiController controller=loader.getController();
		controller.setStage(stage);
		controller.initGoodsInfor(goodsID);

		stage.setTitle("Ä£ºýËÑË÷½á¹û");
		stage.setScene(scene);
		stage.show();

		}catch(IOException e){
			e.printStackTrace();
		}
}
}
