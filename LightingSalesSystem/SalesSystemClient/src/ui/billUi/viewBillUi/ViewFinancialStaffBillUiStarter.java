package ui.billUi.viewBillUi;

import java.io.IOException;
import java.net.URL;
import assistant.type.BillStateEnum;
import javafx.fxml.FXMLLoader;
import javafx.fxml.JavaFXBuilderFactory;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import ui.uiAssistants.StageSize;

/**
 * 查看财务类单据界面starter
 * @author guxinyu
 * @version 2017.12.12
 *
 */
public class ViewFinancialStaffBillUiStarter{
	private Stage stage=new Stage();
	
	public void viewCashExpenseBill(String billId){
		URL location = getClass().getResource("ViewFinancialStaffBillUi.fxml");
		FXMLLoader loader=new FXMLLoader();
		loader.setLocation(location);
	    loader.setBuilderFactory(new JavaFXBuilderFactory());
		Parent root=null;
		try {
			root = loader.load();
		} catch (IOException e) {
			e.printStackTrace();
		}
	
		Scene scene=new Scene(root, StageSize.STAGE_WIDTH, StageSize.STAGE_HEIGHT);
		stage.setScene(scene);
		
		ViewFinancialStaffBillUiController controller=loader.getController();
		controller.init("现金费用单", billId);
		
		stage.show();
	}
	
	public void viewReceiptBill(String billId){
		URL location = getClass().getResource("ViewFinancialStaffBillUi.fxml");
		FXMLLoader loader=new FXMLLoader();
		loader.setLocation(location);
	    loader.setBuilderFactory(new JavaFXBuilderFactory());
		Parent root=null;
		try {
			root = loader.load();
		} catch (IOException e) {
			e.printStackTrace();
		}
	
		Scene scene=new Scene(root, StageSize.STAGE_WIDTH, StageSize.STAGE_HEIGHT);
		stage.setScene(scene);
		
		ViewFinancialStaffBillUiController controller=loader.getController();
		controller.init("收款单", billId);
		
		stage.show();
	}
	
	public void viewPaymentBill(String billId){
		URL location = getClass().getResource("ViewFinancialStaffBillUi.fxml");
		FXMLLoader loader=new FXMLLoader();
		loader.setLocation(location);
	    loader.setBuilderFactory(new JavaFXBuilderFactory());
		Parent root=null;
		try {
			root = loader.load();
		} catch (IOException e) {
			e.printStackTrace();
		}
	
		Scene scene=new Scene(root, StageSize.STAGE_WIDTH, StageSize.STAGE_HEIGHT);
		stage.setScene(scene);
		
		ViewFinancialStaffBillUiController controller=loader.getController();
		controller.init("付款单", billId);
		
		stage.show();
	}
}
