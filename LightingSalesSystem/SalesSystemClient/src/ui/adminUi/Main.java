package ui.adminUi;

import javafx.application.Application;
import javafx.stage.Stage;

public class Main extends Application {

	@Override
	public void start(Stage primaryStage) {
		Stage stage = new Stage();
		AdminUiStarter starter = new AdminUiStarter();
		starter.start(); 
	}

	public static void main(String[] args) {
		launch(args);
	}
}
