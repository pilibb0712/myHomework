package ui.promotionUi;

import javafx.application.Application;
import javafx.stage.Stage;
import vo.UserInfoVO;

public class Main extends Application {

	@Override
	public void start(Stage primaryStage) {
		PromotionUiStarter starter = new PromotionUiStarter();
		UserInfoVO user = new UserInfoVO();
		user.setId("13245");
		user.setName("zhangao");
		starter.start(user);
	}

	public static void main(String[] args) {
		launch(args);
	}
}
