package ui.mainUi.financialStaffMainUi;

import javafx.application.Application;
import javafx.stage.Stage;
import vo.UserInfoVO;


public class Main extends Application {
	@Override
	public void start(Stage primaryStage) {
		try {
			
			UserInfoVO user=new UserInfoVO();
			user.setName("财务人员");
			user.setId("123456");
	
			FinancialStaffMainUiStarter starter=new FinancialStaffMainUiStarter(user);
			
			starter.mainUi();
			
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		launch(args);
	}
}
