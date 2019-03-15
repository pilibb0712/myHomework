package ui.approveBillUi;

import java.net.URL;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import ui.uiAssistants.StageFactory;
import ui.uiAssistants.StageSize;
import vo.UserInfoVO;

/**
 * 总经理的审批单据界面启动器
 * @author 张傲  161250193
 * @version 2017.12.10
 */
public class ApproveBillUiStarter {
	private Stage stage ;//可能要在当时的stage进行切换

   /**
   * 无参数构造方法
   */
	public ApproveBillUiStarter(){
		this(new Stage());
	}
	
	/**
	* stage内进行scene切换
	* @param stage 要切换的stage
	*/
	public ApproveBillUiStarter(Stage stage){
		this.stage = StageFactory.getManagerStage();
	}
	
	/**
	* 新建一个总经理主界面
	*/
	public void start(UserInfoVO user){
		try {
			URL location = getClass().getResource("ApproveBillUi.fxml");
			FXMLLoader flLoader = new FXMLLoader(location);
			Parent root = flLoader.load();
			
			Scene scene = new Scene(root,StageSize.STAGE_WIDTH, StageSize.STAGE_HEIGHT);
			stage.setScene(scene);
			//stage.setTitle(user.getName()+"     "+user.getId());
			ApproveBillUiController controller = flLoader.getController();
			controller.init(user);
			stage.show();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
}
