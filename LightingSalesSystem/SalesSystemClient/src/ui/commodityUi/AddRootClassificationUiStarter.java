package ui.commodityUi;

import java.io.IOException;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import ui.uiAssistants.StageSize;

public class AddRootClassificationUiStarter {
	public void initAddRootClassifictaionUi(Stage stage){
		//库存管理界面初始化
			try{
			FXMLLoader loader=new FXMLLoader(getClass().getResource("AddRootClassificationUi.fxml"));
			Parent root=loader.load();

			AddRootClassificationUiController controller=loader.getController();
			controller.setStage(stage);

			Scene scene=new Scene(root,StageSize.STAGE_WIDTH,StageSize.STAGE_HEIGHT);
			stage.setScene(scene);
			stage.setTitle("增加新的根类别");
			stage.show();
			}catch(IOException e){
				e.printStackTrace();
			}

		}
}
