package ui.adminUi;

import java.io.IOException;
import java.net.URL;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import ui.uiAssistants.StageSize;
import vo.UserVO;

public class UpdateUserUiStarter {
	private AdminForUpdateUserController updateUserController;
	private UserVO oldUser;
	
	private Stage stage;
	
	public UpdateUserUiStarter(AdminForUpdateUserController addUserController,UserVO oldUser){
		this(new Stage(),addUserController,oldUser);
	}
	
	public  UpdateUserUiStarter(Stage stage,AdminForUpdateUserController addUserController,UserVO oldUser){
		this.stage = stage;
		this.updateUserController = addUserController;
		this.oldUser =oldUser;
	}
	
	public void start(){
		try {
			URL location = getClass().getResource("AddUserUi.fxml");
			FXMLLoader flLoader = new FXMLLoader(location);
			Parent root;
			root = flLoader.load();
			
			AddUserUiController controller = flLoader.getController();
			controller.init(updateUserController,oldUser);
			
			Scene scene = new Scene(root,StageSize.STAGE_WIDTH, StageSize.STAGE_HEIGHT);
			stage.setScene(scene);
			stage.show();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
