package ui.mainUi.managerMainUi;

import java.net.URL;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import ui.uiAssistants.StageFactory;
import ui.uiAssistants.StageSize;
import vo.UserInfoVO;

/**
 * 总经理的主界面启动器
 * @author 张傲  161250193
 * @version 2017.12.10
 */
public class ManagerMainUiStarter {
	static private Stage stage ;//可能要在当时的stage进行切换
	static private UserInfoVO userInfo;
	
   /**
   * 无参数构造方法
   */
	public ManagerMainUiStarter(){
		this(new Stage());
	}
	
	/**
	* stage内进行scene切换
	* @param stage 要切换的stage
	*/
	public ManagerMainUiStarter(Stage stage){
		this.stage = StageFactory.getManagerStage();
	}
	
	/**
	* 新建一个总经理主界面
	*/
	public void start(UserInfoVO user){
		try {
			userInfo = user;
			URL location = getClass().getResource("ManagerMainUi.fxml");
			FXMLLoader flLoader = new FXMLLoader(location);
			Parent root = flLoader.load();
			
			ManagerMainUiController controller = flLoader.getController();
			controller.setUser(user);
			
			Scene scene = new Scene(root,StageSize.STAGE_WIDTH, StageSize.STAGE_HEIGHT);
			stage.setScene(scene);
			stage.show();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	/**
	* 新建一个总经理主界面
	*/
	public void start(){
		start(userInfo);
	}
}
