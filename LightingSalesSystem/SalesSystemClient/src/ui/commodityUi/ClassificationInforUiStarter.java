package ui.commodityUi;

import java.io.IOException;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import ui.uiAssistants.StageSize;

public class ClassificationInforUiStarter {
	public void initGoodsClassificationInfotUi(Stage stage,String classificationID){
		//库存管理界面初始化
			try{
			FXMLLoader loader=new FXMLLoader(getClass().getResource("ClassificationInforUi.fxml"));
			Parent root=loader.load();

			ClassificationInforUiController controller=loader.getController();
			controller.setStage(stage);
			controller.setClassification(classificationID);
			controller.initAndUpdateClassificationInfor();

			Scene scene=new Scene(root,StageSize.STAGE_WIDTH,StageSize.STAGE_HEIGHT);
			stage.setScene(scene);
			stage.setTitle("类别信息");
			stage.show();
			}catch(IOException e){
				e.printStackTrace();
			}

		}
}
