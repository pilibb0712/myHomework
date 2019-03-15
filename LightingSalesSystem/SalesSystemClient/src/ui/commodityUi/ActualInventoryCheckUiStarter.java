/**
 * 仓库管理员进行实际库存的审查录入的界面
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

public class ActualInventoryCheckUiStarter {
	private Stage currentStage;
	public void initActualInventoryCheckUi(Stage stage,String operatorName,String operatorID){
	//仓库管理员实际库存确认的初始界面
		try{
			if(stage==null){
				currentStage=new Stage();
			}else{
				currentStage=stage;
			}
			FXMLLoader loader=new FXMLLoader(getClass().getResource("ActualInventoryCheckUi0.fxml"));
			Parent root=loader.load();

			Scene scene=new Scene(root,StageSize.STAGE_WIDTH,StageSize.STAGE_HEIGHT);

			ActualInventoryCheckUiController controller=loader.getController();
			controller.setStage(currentStage);
			controller.setOperator(operatorID, operatorName);

			currentStage.setTitle("实际库存记录");
			currentStage.setScene(scene);
			currentStage.show();
			}catch(IOException e){
				e.printStackTrace();
			}
			//在这个stage中放入fxml并显示（因为每一个功能是一个新的stage）
		    //仓库管理员实际库存确认的初始界面
	}
}
