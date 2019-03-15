package ui.commodityUi;

import java.io.IOException;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import ui.uiAssistants.StageSize;
import vo.ClassificationVO;

public class ModifyClassificationUiStarter {
	public void initModifyClassifictaionUi(Stage stage,ClassificationVO vo){
		//库存管理界面初始化
			try{
			FXMLLoader loader=new FXMLLoader(getClass().getResource("ModifyClassificationUi.fxml"));
			Parent root=loader.load();

			ModifyClassificationUiController controller=loader.getController();
			controller.setStage(stage);
			controller.setClassification(vo);
			controller.iniModifyClassificationUiInfor();

			Scene scene=new Scene(root,StageSize.STAGE_WIDTH,StageSize.STAGE_HEIGHT);
			stage.setScene(scene);
			stage.setTitle("修改类别信息");
			stage.show();
			}catch(IOException e){
				e.printStackTrace();
			}

		}
}
