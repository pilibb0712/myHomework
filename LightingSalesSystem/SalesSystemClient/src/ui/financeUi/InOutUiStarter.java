package ui.financeUi;

import java.io.IOException;
import java.net.URL;

import javafx.fxml.FXMLLoader;
import javafx.fxml.JavaFXBuilderFactory;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import ui.uiAssistants.StageFactory;
import ui.uiAssistants.StageSize;

public class InOutUiStarter {
	private Stage stage=StageFactory.getFinanceStage();
	
	public void viewInOuts(){
		
		try {
			URL location = getClass().getResource("InOutUi.fxml");
			FXMLLoader loader=new FXMLLoader();
			loader.setLocation(location);
			loader.setBuilderFactory(new JavaFXBuilderFactory());
			Parent root=loader.load();
			
			Scene scene=new Scene(root, StageSize.STAGE_WIDTH, StageSize.STAGE_HEIGHT);
			stage.setScene(scene);
		
			InOutUiController controller=loader.getController();
			controller.init();
			
			stage.show();
		} catch (IOException e) {
			e.printStackTrace();
		}
	
	}
}
