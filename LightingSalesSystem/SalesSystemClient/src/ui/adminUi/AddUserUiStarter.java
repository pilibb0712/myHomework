package ui.adminUi;

import java.io.IOException;
import java.net.URL;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import ui.uiAssistants.StageSize;

public class AddUserUiStarter {
	private AdminForAddUserController addUserController;
	
	private Stage stage;
	
	public AddUserUiStarter(AdminForAddUserController addUserController){
		this(new Stage(),addUserController);
	}
	
	public  AddUserUiStarter(Stage stage,AdminForAddUserController addUserController){
		this.stage = stage;
		this.addUserController = addUserController;
	}
	
	public void start(){
		try {
			URL location = getClass().getResource("AddUserUi.fxml");
			FXMLLoader flLoader = new FXMLLoader(location);
			Parent root;
			root = flLoader.load();
			
			AddUserUiController controller = flLoader.getController();
			controller.init(addUserController);
			
			Scene scene = new Scene(root,StageSize.STAGE_WIDTH, StageSize.STAGE_HEIGHT);
			stage.setScene(scene);
			stage.show();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
