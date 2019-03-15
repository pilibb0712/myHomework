package ui.mainUi.wareKeeperMainUi;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.stage.Stage;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;


public class Main extends Application {
	@Override
	public void start(Stage primaryStage) {
		try {
            WareKeeperMainUiStarter starter=new WareKeeperMainUiStarter();
            starter.wareKeeperMainUi(null,"zbb", "00001");
		} catch(Exception e) {
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {
		launch(args);
	}
}
