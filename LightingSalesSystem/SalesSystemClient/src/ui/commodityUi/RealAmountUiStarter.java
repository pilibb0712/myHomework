package ui.commodityUi;

import java.io.IOException;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import ui.uiAssistants.StageSize;
import vo.GoodsVO;

public class RealAmountUiStarter {
public void initRealAmountUi(Stage stage,GoodsVO goods,String operatorId,String operatorName){
	try{
	FXMLLoader loader=new FXMLLoader(getClass().getResource("ActualInventoryCheckUi1.fxml"));
	Parent root=loader.load();

	Scene scene=new Scene(root,StageSize.STAGE_WIDTH,StageSize.STAGE_HEIGHT);

	RealAmountUiController controller=loader.getController();
	controller.setStage(stage);
	controller.setOperator(operatorId, operatorName);
	controller.initRealAmountCheckUi(goods);

	stage.setTitle("Êµ¼Ê¿â´æ¼ÇÂ¼");
	stage.setScene(scene);
	stage.show();
	}catch(IOException e){
		e.printStackTrace();
	}

}
}
