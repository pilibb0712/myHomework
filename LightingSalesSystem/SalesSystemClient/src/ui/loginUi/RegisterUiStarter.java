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
 * @author cosx
 *
 */
public class RegisterUiStarter {
	
	private Stage stage=StageFactory.getLogStage();
	
	public void startRegister(boolean registered) {
			try {
				URL location = getClass().getResource("RegisterUi.fxml");
				FXMLLoader loader=new FXMLLoader();
				loader.setLocation(location);
				Parent root=loader.load();
				
				Scene scene=new Scene(root);
				stage.setScene(scene);
				
				RegisterUiController controller=loader.getController();
				controller.init(registered);
	
				stage.show();
			} catch (Exception e) {
				// TODO: handle exception
			}
	}
}
