/**
 * 仓库管理员进行库存审查的界面
 * @author BeibeiZhang
 * @version 2017.11.19
 *
 */
package ui.commodityUi;

import java.io.IOException;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import ui.uiAssistants.StageSize;

public class StockCheckUiStarter {
	private Stage currentStage;
	public void initStockCheckUi(Stage stage){
		//库存审查的初始界面的初始化
			try{
				if(stage==null){
					currentStage=new Stage();
				}else{
					currentStage=stage;
				}
				FXMLLoader loader=new FXMLLoader(getClass().getResource("StockCheckUi0.fxml"));
				Parent root=loader.load();
				Scene scene=new Scene(root,StageSize.STAGE_WIDTH,StageSize.STAGE_HEIGHT);

				StockCheckUiController controller=loader.getController();
				controller.setStage(currentStage);

				currentStage.setTitle("库存审查");
				currentStage.setScene(scene);
				currentStage.show();
			}catch(IOException e){
				e.printStackTrace();
			}
		}
}
