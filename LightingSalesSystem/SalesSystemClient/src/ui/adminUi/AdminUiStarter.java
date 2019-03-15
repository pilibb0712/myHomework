package ui.adminUi;

import java.io.IOException;
import java.net.URL;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import ui.uiAssistants.StageSize;

public class AdminUiStarter {
	private Stage stage;
	
	public AdminUiStarter(){
		this(new Stage());
	}
	
	public  AdminUiStarter(Stage stage){
		this.stage = stage;
	}
	
	public void start(){
		try {
			URL location = getClass().getResource("AdminUi.fxml");
			FXMLLoader flLoader = new FXMLLoader(location);
			Parent root;
			root = flLoader.load();
			
			AdminUiController controller = flLoader.getController();
			controller.init();
			
			Scene scene = new Scene(root,StageSize.STAGE_WIDTH, StageSize.STAGE_HEIGHT);
			stage.setScene(scene);
			stage.show();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
