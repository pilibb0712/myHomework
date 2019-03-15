package ui.customerUi;

import assistant.type.UserPositionEnum;
import javafx.application.Application;
import javafx.stage.Stage;
import vo.UserInfoVO;

public class Main extends Application {

	@Override
	public void start(Stage primaryStage) {
		UserInfoVO vo=new UserInfoVO();
		vo.setId("00004");
		vo.setName("a_salseman");
		vo.setUserPositionEnum(UserPositionEnum.SALESMAN);
		
		AddCustomerUiStarter starter=new AddCustomerUiStarter(vo);
		starter.addCustomer();
//		SelectCustomerUiStarter starter=new SelectCustomerUiStarter();
//		starter.selectCustomer(vo);
	}

	public static void main(String[] args) {
		launch(args);
	}
}
