/**
 * 仓库管理员进行商品信息管理界面
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

public class GoodsInforManagementUiStarter {
	private Stage currentStage;
	public void initGoodsInforManagementUi(Stage stage){
		//初始化商品信息管理界面
			try{
			if(stage==null){
				currentStage=new Stage();
			}else{
				currentStage=stage;
			}
			FXMLLoader loader=new FXMLLoader(getClass().getResource("GoodsInforManaUi0.fxml"));
			Parent root=loader.load();

			Scene scene=new Scene(root,StageSize.STAGE_WIDTH,StageSize.STAGE_HEIGHT);

			GoodsInforManagementUiController controller=loader.getController();
			controller.setStage(currentStage);

			currentStage.setTitle("商品信息管理");
			currentStage.setScene(scene);
			currentStage.show();
			}catch(IOException e){
				e.printStackTrace();
			}
		}
}
