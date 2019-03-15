package ui.mainUi.salesmanMainUi;

import assistant.type.UserPositionEnum;
import javafx.application.Application;
import javafx.stage.Stage;
import ui.loginUi.LoginUiStarter;
import vo.UserInfoVO;


public class Main extends Application {
	@Override
	public void start(Stage primaryStage) {
		try {
			UserInfoVO testUser=new UserInfoVO();
			testUser.setId("00001");
			testUser.setName("Name");
			testUser.setUserPositionEnum(UserPositionEnum.SALESMAN);
			SalesmanMainUiStarter starter=new SalesmanMainUiStarter();
			starter.startSalesman(testUser);
			
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		launch(args);
	}
}
