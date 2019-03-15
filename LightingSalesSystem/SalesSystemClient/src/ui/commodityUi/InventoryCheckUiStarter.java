/**
 * 仓库管理员进行库存盘点的界面
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

public class InventoryCheckUiStarter {
	private Stage stage;
	public void initInventoryCheckUi(Stage preStage){
	//初始化当天库存快照
		try{
			if(preStage==null){
				stage=new Stage();
			}else{
				stage=preStage;
			}
			FXMLLoader loader=new FXMLLoader(getClass().getResource("InventoryCheckUi.fxml"));
			Parent root=loader.load();

			InventoryCheckUiController controller=loader.getController();
			controller.setInventoryCheckSheetContents();
			stage.setTitle("库存盘点");
			controller.setInitInfor(stage);


			Scene scene=new Scene(root,StageSize.STAGE_WIDTH,StageSize.STAGE_HEIGHT);
			stage.setScene(scene);
			stage.show();
		}catch(IOException e){
			e.printStackTrace();
		}
	}

}
