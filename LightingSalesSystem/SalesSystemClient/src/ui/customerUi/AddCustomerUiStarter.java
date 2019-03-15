package ui.customerUi;

import java.io.IOException;
import java.net.URL;
import java.rmi.RemoteException;

import assistant.type.UserPositionEnum;
import javafx.fxml.FXMLLoader;
import javafx.fxml.JavaFXBuilderFactory;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import ui.uiAssistants.StageFactory;
import vo.UserInfoVO;
import vo.UserVO;

/**
 * @author ÍõÄþÒ»
 *
 */
public class AddCustomerUiStarter {
	private Stage stage=StageFactory.getSalesmanStage();
	private UserInfoVO user;
	
	public void setStage(Stage stage) {
		this.stage=stage;
	}

	public AddCustomerUiStarter(UserInfoVO user2) {
		this.user=user2;
	}
	
	public void addCustomer() {
		
		try {
			
			URL location = getClass().getResource("AddCustomerUi.fxml");
			FXMLLoader loader=new FXMLLoader();
			loader.setLocation(location);
			Parent root=loader.load();
			
			Scene scene=new Scene(root, 1386, 806);
			stage.setScene(scene);
			
			AddCustomerUiController controller=loader.getController();
			controller.init(user);
			controller.setUser(user);
			stage.show();
			
		} catch (RemoteException e) {
			// TODO: handle exception
			e.printStackTrace();
		}catch (IOException e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}

}
