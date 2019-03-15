/**
 * 
 */
package ui.loginUi;

import java.net.URL;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import ui.uiAssistants.StageFactory;

/**
 * @author 王宁一
 * @version 2017.12.27
 * 登录界面
 *
 */
public class LoginUiStarter {

	private Stage stage=StageFactory.getLogStage();
	
	public void startLogin() {
		try {
			
			URL location = getClass().getResource("LoginUi.fxml");
			FXMLLoader loader=new FXMLLoader();
			loader.setLocation(location);
			Parent root=loader.load();
			
			Scene scene=new Scene(root);
			stage.setScene(scene);
			LoginUiController controller=loader.getController();
			controller.init(stage);
			
			stage.getScene().setFill(null);
			stage.getScene().getRoot().setStyle(
					"-fx-background-color: transparent");
			
			stage.show();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
}
