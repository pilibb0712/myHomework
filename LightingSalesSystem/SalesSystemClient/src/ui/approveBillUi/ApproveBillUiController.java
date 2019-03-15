package ui.approveBillUi;

import java.io.IOException;
import java.net.URL;

import assistant.type.BillCategoryEnum;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import ui.mainUi.managerMainUi.ManagerMainUiStarter;
import vo.UserInfoVO;

public class ApproveBillUiController {
	
	@FXML
	private BorderPane borderPane;
	private UserInfoVO user;
	@FXML
	private Text billCategoryText;
	
	private VBox giftBillVBox;
	private VBox cashExpenseBillVBox;
	private VBox inventoryLossBillVBox;
	private VBox inventoryOverBillVBox;
	private VBox inventoryWarningBillVBox;
	private VBox paymentBillVBox;
	private VBox purchaseBillVBox;
	private VBox purchaseReturnBillVBox;
	private VBox receiptBillVBox;
	private VBox salesBillVBox;
	private VBox salesReturnBillVBox;
	
	public void init(UserInfoVO user){
		this.user = user;
		initVBox();
	}
	
	private void initVBox(){
		initGiftBillVBox();
		initCashExpenseBillVBox();
		initInventoryLossBillVBox();
		initInventoryOverBillVBox();
		initInventoryWarningBillVBox();
		initPaymentBillVBox();
		initPurchaseBillVBox();
		initPurchaseReturnBillVBox();
		initReceiptBillVBox();
		initSalesBillVBox();
		initSalesReturnBillVBox();
	}
	
	private void initGiftBillVBox(){
		try {
			URL location = getClass().getResource("ApproveGiftBillUi.fxml");
			FXMLLoader flLoader = new FXMLLoader(location);
			giftBillVBox = (VBox)flLoader.load();
			ApproveGiftBillController controller = flLoader.getController();
			controller.init(user);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	private void initCashExpenseBillVBox(){
		try {
			URL location = getClass().getResource("ApproveCashExpenseBillUi.fxml");
			FXMLLoader flLoader = new FXMLLoader(location);
			cashExpenseBillVBox = (VBox)flLoader.load();
			ApproveCashExpenseBillUiController controller = flLoader.getController();
			controller.init(user);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	private void initInventoryLossBillVBox(){
		try {
			URL location = getClass().getResource("ApproveInventoryLossBillUi.fxml");
			FXMLLoader flLoader = new FXMLLoader(location);
			inventoryLossBillVBox = (VBox)flLoader.load();
			ApproveInventoryLossBillUiController controller = flLoader.getController();
			controller.init(user);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	private void initInventoryOverBillVBox(){
		try {
			URL location = getClass().getResource("ApproveInventoryOverBillUi.fxml");
			FXMLLoader flLoader = new FXMLLoader(location);
			inventoryOverBillVBox = (VBox)flLoader.load();
			ApproveInventoryOverBillUiController controller = flLoader.getController();
			controller.init(user);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	private void initInventoryWarningBillVBox(){
		try {
			URL location = getClass().getResource("ApproveInventoryWarningBillUi.fxml");
			FXMLLoader flLoader = new FXMLLoader(location);
			inventoryWarningBillVBox = (VBox)flLoader.load();
			ApproveInventoryWarningBillUiController controller = flLoader.getController();
			controller.init(user);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	private void initPaymentBillVBox(){
		try {
			URL location = getClass().getResource("ApprovePaymentBillUi.fxml");
			FXMLLoader flLoader = new FXMLLoader(location);
			paymentBillVBox = (VBox)flLoader.load();
			ApprovePaymentBillUiController controller = flLoader.getController();
			controller.init(user);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	private void initPurchaseBillVBox(){
		try {
			URL location = getClass().getResource("ApprovePurchaseBillUi.fxml");
			FXMLLoader flLoader = new FXMLLoader(location);
			purchaseBillVBox = (VBox)flLoader.load();
			ApprovePurchaseBillUiController controller = flLoader.getController();
			controller.init(user);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	private void initPurchaseReturnBillVBox(){
		try {
			URL location = getClass().getResource("ApprovePurchaseReturnBillUi.fxml");
			FXMLLoader flLoader = new FXMLLoader(location);
			purchaseReturnBillVBox = (VBox)flLoader.load();
			ApprovePurchaseReturnBillUiController controller = flLoader.getController();
			controller.init(user);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	private void initReceiptBillVBox(){
		try {
			URL location = getClass().getResource("ApproveReceiptBillUi.fxml");
			FXMLLoader flLoader = new FXMLLoader(location);
			receiptBillVBox = (VBox)flLoader.load();
			ApproveReceiptBillUiController controller = flLoader.getController();
			controller.init(user);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	private void initSalesBillVBox(){
		try {
			URL location = getClass().getResource("ApproveSalesBillUi.fxml");
			FXMLLoader flLoader = new FXMLLoader(location);
			salesBillVBox = (VBox)flLoader.load();
			ApproveSalesBillUiController controller = flLoader.getController();
			controller.init(user);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	private void initSalesReturnBillVBox(){
		try {
			URL location = getClass().getResource("ApproveSalesReturnBillUi.fxml");
			FXMLLoader flLoader = new FXMLLoader(location);
			salesReturnBillVBox = (VBox)flLoader.load();
			ApproveSalesReturnBillUiController controller = flLoader.getController();
			controller.init(user);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	/**
	 * 不同单据的menuItemListener
	 * */
	@FXML
	protected void giftBillMenuItemListener(){
		billCategoryText.setText(BillCategoryEnum.GIFT_BILL.toChineseString());
		borderPane.setCenter(giftBillVBox);
	} 
	
	@FXML
	protected void cashExpenseBillMenuItemListener(){
		billCategoryText.setText(BillCategoryEnum.CASH_EXPENSE_BILL.toChineseString());
		borderPane.setCenter(cashExpenseBillVBox);
	} 

	@FXML
	protected void inventoryLossBillMenuItemListener(){
		billCategoryText.setText(BillCategoryEnum.INVENTORY_LOSS_BILL.toChineseString());
		borderPane.setCenter(inventoryLossBillVBox);
	} 
	
	@FXML
	protected void inventoryOverBillMenuItemListener(){
		billCategoryText.setText(BillCategoryEnum.INVENTORY_OVER_BILL.toChineseString());
		borderPane.setCenter(inventoryOverBillVBox);
	} 
	
	@FXML
	protected void inventoryWarningBillMenuItemListener(){
		billCategoryText.setText(BillCategoryEnum.INVENTORY_WARNING_BILL.toChineseString());
		borderPane.setCenter(inventoryWarningBillVBox);
	} 
	
	@FXML
	protected void paymentBillMenuItemListener(){
		billCategoryText.setText(BillCategoryEnum.PAYMENT_BILL.toChineseString());
		borderPane.setCenter(paymentBillVBox);
	} 
	
	@FXML
	protected void purchaseBillMenuItemListener(){
		billCategoryText.setText(BillCategoryEnum.PURCHASE_BILL.toChineseString());
		borderPane.setCenter(purchaseBillVBox);
	} 
	
	@FXML
	protected void purchaseReturnBillMenuItemListener(){
		billCategoryText.setText(BillCategoryEnum.PURCHASE_RETURN_BILL.toChineseString());
		borderPane.setCenter(purchaseReturnBillVBox);
	} 
	
	@FXML
	protected void receiptBillMenuItemListener(){
		billCategoryText.setText(BillCategoryEnum.RECEIPT_BILL.toChineseString());
		borderPane.setCenter(receiptBillVBox);
	} 
	
	@FXML
	protected void salesBillMenuItemListener(){
		billCategoryText.setText(BillCategoryEnum.SALES_BILL.toChineseString());
		borderPane.setCenter(salesBillVBox);
	} 
	
	@FXML
	protected void salesReturnBillMenuItemListener(){
		billCategoryText.setText(BillCategoryEnum.SALES_RETURN_BILL.toChineseString());
		borderPane.setCenter(salesReturnBillVBox);
	} 
	
	@FXML 
	protected void returnButtonListener(){
		ManagerMainUiStarter starter = new ManagerMainUiStarter();
		starter.start(this.user);
	}
}
