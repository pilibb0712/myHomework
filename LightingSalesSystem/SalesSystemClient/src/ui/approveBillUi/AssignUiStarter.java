package ui.approveBillUi;

import java.net.URL;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import ui.uiAssistants.StageSize;
import vo.BillVO;
import vo.UserInfoVO;

public class AssignUiStarter {
	private Stage stage ;//可能要在当时的stage进行切换
	   /**
	   * 无参数构造方法
	   */
		public AssignUiStarter(){
			this(new Stage());
		}
		
		/**
		* stage内进行scene切换
		* @param stage 要切换的stage
		*/
		public AssignUiStarter(Stage stage){
			this.stage = stage;
		}
		
		/**
		* 新建一个总经理主界面
		*/
		public void startAndWait(BillVO bill){
			try {
				URL location = getClass().getResource("AssignUi.fxml");
				FXMLLoader flLoader = new FXMLLoader(location);
				Parent root = flLoader.load();
				
				Scene scene = new Scene(root,StageSize.STAGE_WIDTH, StageSize.STAGE_HEIGHT);
				stage.setScene(scene);
				//stage.setTitle(user.getName()+"     "+user.getId());
				AssignController controller = flLoader.getController();
				controller.init(bill);
				stage.showAndWait();
			} catch(Exception e) {
				e.printStackTrace();
			}
		}
}
