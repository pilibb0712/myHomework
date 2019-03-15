package ui.billUi.viewBillUi;

import java.util.ArrayList;

import assistant.utility.Date;
import javafx.application.Application;
import javafx.stage.Stage;
import vo.GiftBillVO;
import vo.GiftVO;
import vo.UserInfoVO;

public class Main extends Application {

	@Override
	public void start(Stage primaryStage) {
		/*GiftBillVO giftBill = new GiftBillVO();
		giftBill.setApproveDate(new Date());
		UserInfoVO user = new UserInfoVO();
		user.setName("小明");
		user.setId("12345");
		giftBill.setApprover(user);
		giftBill.setApproverComment("666");
		giftBill.setCreateDate(new Date());
		user.setName("小红");
		giftBill.setCreater(user);
		user.setName("小刚");
		giftBill.setExecutor(user);
		giftBill.setId("KCZSD-20171224-00002");
		ArrayList<GiftVO> giftVOs = new ArrayList<>();
		GiftVO gift = new GiftVO();
		gift.setGiftAmount(100);
		gift.setId("xxxx");
		gift.setName("a灯");
		gift.setPrice(1000);
		giftVOs.add(gift);
		giftBill.setGifts(giftVOs);
		ViewGiftBillUiStarter starter = new ViewGiftBillUiStarter(giftBill.getId());
		starter.start();*/
		ViewSalesmanBillsUiStarter starter=new ViewSalesmanBillsUiStarter();
		starter.viewSalesBill("XSD-20180112-00003");
	}

	public static void main(String[] args) {
		launch(args);
	}
}
