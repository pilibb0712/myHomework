package ui.commodityUi;

import java.io.IOException;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import ui.uiAssistants.StageSize;
import vo.GoodsVO;

public class ModifyGoodsUiStarter {
public void initModifyGoodsUi(Stage stage,GoodsVO goods){
	try{
	FXMLLoader loader=new FXMLLoader(getClass().getResource("ModifyGoodsUi.fxml"));
	Parent root=loader.load();

	Scene scene=new Scene(root,StageSize.STAGE_WIDTH,StageSize.STAGE_HEIGHT);

	ModifyGoodsUiController controller=loader.getController();
	controller.setStage(stage);
	controller.initModifyGoodsInfor(goods);

	stage.setTitle("商品信息管理");
	stage.setScene(scene);
	stage.show();
	}catch(IOException e){
		e.printStackTrace();
	}
}
}
