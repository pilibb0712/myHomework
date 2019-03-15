package ui.commodityUi;

import java.io.IOException;

import assistant.utility.Date;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import ui.uiAssistants.StageSize;

public class StockCheckSheetUiStarter {
public void initStockCheckSheetUi(Stage stage,Date begin,Date end){
	try{
		FXMLLoader loader=new FXMLLoader(getClass().getResource("StockCheckUi1.fxml"));
		Parent root=loader.load();

		Scene scene=new Scene(root,StageSize.STAGE_WIDTH,StageSize.STAGE_HEIGHT);

		StockCheckSheetUiController controller=loader.getController();
		controller.setStage(stage);
		controller.initStockCheckBill(begin, end);

		stage.setTitle("出入库情况表");
		stage.setScene(scene);
		stage.show();
		}catch(IOException e){
			e.printStackTrace();
		}
}
}
