package ui.commodityUi;

import java.io.IOException;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import ui.uiAssistants.StageSize;
import vo.GoodsVO;

public class StockInUiStarter {
public void initStockInUi(Stage stage,GoodsVO vo,String operator){
	try{
		FXMLLoader loader=new FXMLLoader(getClass().getResource("StockInUi.fxml"));
		Parent root=loader.load();

		Scene scene=new Scene(root,StageSize.STAGE_WIDTH,StageSize.STAGE_HEIGHT);

		StockInUiController controller=loader.getController();
        controller.setStage(stage);
        controller.setOperator(operator);
        controller.initAddUiInfor(vo);

		stage.setTitle("…Ã∆∑»Îø‚");
		stage.setScene(scene);
		stage.show();
		}catch(IOException e){
			e.printStackTrace();
		}
}
}
