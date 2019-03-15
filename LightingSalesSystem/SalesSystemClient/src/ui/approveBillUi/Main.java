package ui.approveBillUi;

import javafx.application.Application;
import javafx.stage.Stage;
import vo.UserInfoVO;

public class Main extends Application {

	@Override
	public void start(Stage primaryStage) {
		ApproveBillUiStarter starter = new ApproveBillUiStarter();
		UserInfoVO user = new UserInfoVO();
		user.setId("12345");
		user.setName("уехЩ");
		starter.start(user);
	}

	public static void main(String[] args) {
		launch(args);
	}
}
