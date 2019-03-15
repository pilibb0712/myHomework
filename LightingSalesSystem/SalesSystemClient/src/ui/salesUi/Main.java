package ui.salesUi;

import assistant.type.UserPositionEnum;
import javafx.application.Application;
import javafx.stage.Stage;
import vo.UserInfoVO;

public class Main extends Application {

	@Override
	public void start(Stage primaryStage) {
		UserInfoVO user=new UserInfoVO();
		user.setId("00008");
		user.setName("ÍõÄþÒ»");
		user.setUserPositionEnum(UserPositionEnum.SALESMAN);
		NewSalesmanBillsUiStarter starter=new NewSalesmanBillsUiStarter(user);
		starter.newPurchaseBill();
//		starter.newPurchaseReturnBill();
//		starter.newSalesBill();
//		starter.newSalesReturnBill();
		
	}

	public static void main(String[] args) {
		launch(args);
	}
}
