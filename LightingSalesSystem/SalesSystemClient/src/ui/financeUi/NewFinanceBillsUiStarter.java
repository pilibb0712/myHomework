package ui.financeUi;

import java.io.IOException;
import java.net.URL;
import java.rmi.RemoteException;
import assistant.type.BillCategoryEnum;
import javafx.fxml.FXMLLoader;
import javafx.fxml.JavaFXBuilderFactory;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import ui.uiAssistants.StageFactory;
import ui.uiAssistants.StageSize;
import vo.CashExpenseBillVO;
import vo.PaymentBillVO;
import vo.ReceiptBillVO;
import vo.UserInfoVO;

public class NewFinanceBillsUiStarter {
	private Stage stage=StageFactory.getFinanceStage();
	
	private UserInfoVO user;
	
	public NewFinanceBillsUiStarter(UserInfoVO u){
		user=u;
	}
	
	public void setStage(Stage s){
		stage=s;
	}
	
	public void newCashExpenseBill(){
		try{
			URL location = getClass().getResource("NewFinanceBillsUi.fxml");
			FXMLLoader loader=new FXMLLoader();
			loader.setLocation(location);
			loader.setBuilderFactory(new JavaFXBuilderFactory());
			Parent root=loader.load();
	
			Scene scene=new Scene(root, StageSize.STAGE_WIDTH, StageSize.STAGE_HEIGHT);
			stage.setScene(scene);
		
			NewFinanceBillsUiController controller=loader.getController();
			controller.init(BillCategoryEnum.CASH_EXPENSE_BILL.toChineseString(),user,"mainUi");
			
			stage.show();
		}catch(RemoteException e){
			e.printStackTrace();
		}catch (IOException e) {
			e.printStackTrace();
		}
		
	}
	
	public void newReceiptBill(){
		try{
			URL location = getClass().getResource("NewFinanceBillsUi.fxml");
			FXMLLoader loader=new FXMLLoader();
			loader.setLocation(location);
			loader.setBuilderFactory(new JavaFXBuilderFactory());
			Parent root=loader.load();
	
			Scene scene=new Scene(root, StageSize.STAGE_WIDTH, StageSize.STAGE_HEIGHT);
			stage.setScene(scene);
		
			NewFinanceBillsUiController controller=loader.getController();
			controller.init(BillCategoryEnum.RECEIPT_BILL.toChineseString(),user,"mainUi");
		
			stage.show();
		}catch(RemoteException e){
			e.printStackTrace();
		}catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void newPaymentBill(){
		try{
			URL location = getClass().getResource("NewFinanceBillsUi.fxml");
			FXMLLoader loader=new FXMLLoader();
			loader.setLocation(location);
			loader.setBuilderFactory(new JavaFXBuilderFactory());
			Parent root=loader.load();
	
			Scene scene=new Scene(root, StageSize.STAGE_WIDTH, StageSize.STAGE_HEIGHT);
			stage.setScene(scene);
		
			NewFinanceBillsUiController controller=loader.getController();
			controller.init(BillCategoryEnum.PAYMENT_BILL.toChineseString(),user,"mainUi");
		
			stage.show();
		}catch(RemoteException e){
			e.printStackTrace();
		}catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void editCashExpenseBill(CashExpenseBillVO bill){
		try{
			URL location = getClass().getResource("NewFinanceBillsUi.fxml");
			FXMLLoader loader=new FXMLLoader();
			loader.setLocation(location);
			loader.setBuilderFactory(new JavaFXBuilderFactory());
			Parent root=loader.load();
	
			Scene scene=new Scene(root, StageSize.STAGE_WIDTH, StageSize.STAGE_HEIGHT);
			stage.setScene(scene);
		
			NewFinanceBillsUiController controller=loader.getController();
			controller.init(BillCategoryEnum.CASH_EXPENSE_BILL.toChineseString(),user,"modifyUi");
			controller.showCashExpenseBillDraft(bill);
			
			stage.show();
		}catch(RemoteException e){
			e.printStackTrace();
		}catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void editPaymentBill(PaymentBillVO bill){
		try{
			URL location = getClass().getResource("NewFinanceBillsUi.fxml");
			FXMLLoader loader=new FXMLLoader();
			loader.setLocation(location);
			loader.setBuilderFactory(new JavaFXBuilderFactory());
			Parent root=loader.load();
	
			Scene scene=new Scene(root, StageSize.STAGE_WIDTH, StageSize.STAGE_HEIGHT);
			stage.setScene(scene);
		
			NewFinanceBillsUiController controller=loader.getController();
			controller.init(BillCategoryEnum.PAYMENT_BILL.toChineseString(),user,"modifyUi");
			controller.showPaymentBillDraft(bill);
			
			stage.show();
		}catch(RemoteException e){
			e.printStackTrace();
		}catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void editReceiptBill(ReceiptBillVO bill){
		try{
			URL location = getClass().getResource("NewFinanceBillsUi.fxml");
			FXMLLoader loader=new FXMLLoader();
			loader.setLocation(location);
			loader.setBuilderFactory(new JavaFXBuilderFactory());
			Parent root=loader.load();
	
			Scene scene=new Scene(root, StageSize.STAGE_WIDTH, StageSize.STAGE_HEIGHT);
			stage.setScene(scene);
		
			NewFinanceBillsUiController controller=loader.getController();
			controller.init(BillCategoryEnum.RECEIPT_BILL.toChineseString(),user,"modifyUi");
			controller.showReceiptBillDraft(bill);
			
			stage.show();
		}catch(RemoteException e){
			e.printStackTrace();
		}catch (IOException e) {
			e.printStackTrace();
		}
	}
}
