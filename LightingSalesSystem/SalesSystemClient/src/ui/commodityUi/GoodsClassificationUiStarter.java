/**
 * 仓库管理员进行库存分类管理的界面
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

public class GoodsClassificationUiStarter {
	private Stage currentStage;
	public void initGoodsClassificationUi(Stage stage){
		//库存管理界面初始化
		if(stage==null){
			//从仓库管理员初始界面过来时是没有stage的，需要自己new
			currentStage=new Stage();
		}else{
			//但从其他后续的界面退回这个界面时，是会传同一个stage的
			currentStage=stage;
		}
			try{
			FXMLLoader loader=new FXMLLoader(getClass().getResource("GoodsClassificationUi0.fxml"));
			Parent root=loader.load();

			GoodsClassificationUiController controller=loader.getController();
			controller.setStage(stage);
			controller.initAndUpdateClassificationTree();

			Scene scene=new Scene(root,StageSize.STAGE_WIDTH,StageSize.STAGE_HEIGHT);
            controller.setStage(currentStage);

			currentStage.setScene(scene);
			currentStage.setTitle("商品分类管理");
			currentStage.show();
			}catch(IOException e){
				e.printStackTrace();
			}

		}
}
