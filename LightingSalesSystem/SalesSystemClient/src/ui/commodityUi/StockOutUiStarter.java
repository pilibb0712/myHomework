package ui.commodityUi;

import java.io.IOException;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import ui.uiAssistants.StageSize;
import vo.GoodsVO;

public class StockOutUiStarter {
	public void initStockOutUi(Stage stage,String operatorID,String operatorName,GoodsVO vo){
		try{
	FXMLLoader loader=new FXMLLoader(getClass().getResource("StockOutUi.fxml"));
	Parent root=loader.load();
	Scene scene=new Scene(root,StageSize.STAGE_WIDTH,StageSize.STAGE_HEIGHT);

	StockOutUiController controller=loader.getController();
	controller.setStage(stage);
	controller.setOperator(operatorID, operatorName);
    controller.initDeleteUiInfor(vo);

	stage.setTitle("ÉÌÆ·³ö¿â");
	stage.setScene(scene);
	stage.show();
	}catch(IOException e){
		e.printStackTrace();
	}
}
}
