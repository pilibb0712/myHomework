package ui.mainUi.adminMainUi;

import java.net.URL;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import ui.adminUi.AdminUiStarter;
import ui.uiAssistants.StageSize;
import vo.UserInfoVO;

/**
 * 总经理的主界面启动器
 * @author 张傲  161250193
 * @version 2017.12.10
 */
public class AdminMainUiStarter {
	private Stage stage ;//可能要在当时的stage进行切换

   /**
   * 无参数构造方法
   */
	public AdminMainUiStarter(){
		this(new Stage());
	}
	
	/**
	* stage内进行scene切换
	* @param stage 要切换的stage
	*/
	public AdminMainUiStarter(Stage stage){
		this.stage = stage;
	}
	
	/**
	* 新建一个总经理主界面
	*/
	public void start(UserInfoVO user){
		try {
			AdminUiStarter starter = new AdminUiStarter();
			starter.start();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
}
