/**
 * 仓库管理员进行商品增加和入库的界面
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

public class AddGoodsUiStarter {
	private Stage currentStage;
	public void initAddGoodsUi(Stage stage,String operatorName){
		//仓库管理员增加商品的初始界面，从调用方传入当前操作员姓名
		if(stage==null){
			currentStage=new Stage();
		}else{
			currentStage=stage;
		}
			try{
			FXMLLoader loader=new FXMLLoader(getClass().getResource("AddGoodsUi0.fxml"));
			Parent root=loader.load();

			Scene scene=new Scene(root,StageSize.STAGE_WIDTH,StageSize.STAGE_HEIGHT);

			AddGoodsUiController controller=loader.getController();
			controller.setStage(currentStage);
			controller.setOperator(operatorName);

			currentStage.setTitle("增加商品");
			currentStage.setScene(scene);
			currentStage.show();
			}catch(IOException e){
				e.printStackTrace();
			}
		}

}
