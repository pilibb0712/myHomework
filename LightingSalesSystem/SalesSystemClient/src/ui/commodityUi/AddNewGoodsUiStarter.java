package ui.commodityUi;

import java.io.IOException;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import ui.uiAssistants.StageSize;

public class AddNewGoodsUiStarter {
	public void initAddNewGoodsUi(Stage stage,String operatorName){
	try{
		FXMLLoader loader=new FXMLLoader(getClass().getResource("AddNewGoodsUi.fxml"));
		Parent root=loader.load();

		Scene scene=new Scene(root,StageSize.STAGE_WIDTH,StageSize.STAGE_HEIGHT);

		AddNewGoodsUiController controller=loader.getController();
		controller.setStage(stage);
		controller.setOperator(operatorName);

		stage.setTitle("增加新商品");
		stage.setScene(scene);
		stage.show();
		}catch(IOException e){
			e.printStackTrace();
		}
	}
}
