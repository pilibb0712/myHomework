package ui.listUi;

import java.io.IOException;
import java.net.URL;
import java.rmi.RemoteException;
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

public class EditFinanceBillsUiStarter {
	private Stage stage=StageFactory.getFinanceStage();
	
	public void editCashExpenseBillUi(CashExpenseBillVO bill, String operation){
		try{
			URL location = getClass().getResource("EditFinanceBillsUi.fxml");
			FXMLLoader loader=new FXMLLoader();
			loader.setLocation(location);
			loader.setBuilderFactory(new JavaFXBuilderFactory());
			Parent root=loader.load();
	
			Scene scene=new Scene(root, StageSize.STAGE_WIDTH, StageSize.STAGE_HEIGHT);
			stage.setScene(scene);
	
			EditFinanceBillsUiController controller=loader.getController();
			controller.init(bill,operation);
			
			stage.show();
		}catch(RemoteException e){
			e.printStackTrace();
		}catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void editPaymentBillUi(PaymentBillVO bill,String operation){
		try{
			URL location = getClass().getResource("EditFinanceBillsUi.fxml");
			FXMLLoader loader=new FXMLLoader();
			loader.setLocation(location);
			loader.setBuilderFactory(new JavaFXBuilderFactory());
			Parent root=loader.load();
	
			Scene scene=new Scene(root, StageSize.STAGE_WIDTH, StageSize.STAGE_HEIGHT);
			stage.setScene(scene);
	
			EditFinanceBillsUiController controller=loader.getController();
			controller.init(bill,operation);
			
			stage.show();
		}catch(RemoteException e){
			e.printStackTrace();
		}catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void editReceiptBillUi(ReceiptBillVO bill,String operation){
		try{
			URL location = getClass().getResource("EditFinanceBillsUi.fxml");
			FXMLLoader loader=new FXMLLoader();
			loader.setLocation(location);
			loader.setBuilderFactory(new JavaFXBuilderFactory());
			Parent root=loader.load();
	
			Scene scene=new Scene(root, StageSize.STAGE_WIDTH, StageSize.STAGE_HEIGHT);
			stage.setScene(scene);
	
			EditFinanceBillsUiController controller=loader.getController();
			controller.init(bill,operation);
			
			stage.show();
		}catch(RemoteException e){
			e.printStackTrace();
		}catch (IOException e) {
			e.printStackTrace();
		}
	}
}
