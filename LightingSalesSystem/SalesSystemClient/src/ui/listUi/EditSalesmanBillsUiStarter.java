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
import vo.PurchaseBillVO;
import vo.PurchaseReturnBillVO;
import vo.SalesBillVO;
import vo.SalesReturnBillVO;

public class EditSalesmanBillsUiStarter {
	private Stage stage=StageFactory.getFinanceStage();
	
	public void editSalesBillUi(SalesBillVO bill, String operation){
		try{
			URL location = getClass().getResource("EditSalesBillUi.fxml");
			FXMLLoader loader=new FXMLLoader();
			loader.setLocation(location);
			loader.setBuilderFactory(new JavaFXBuilderFactory());
			Parent root=loader.load();
	
			Scene scene=new Scene(root, StageSize.STAGE_WIDTH, StageSize.STAGE_HEIGHT);
			stage.setScene(scene);
	
			EditSalesBillUiController controller=loader.getController();
			controller.init(bill, operation);
			
			stage.show();
		}catch(RemoteException e){
			e.printStackTrace();
		}catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void editSalesReturnBillUi(SalesReturnBillVO bill,String operation){
		try{
			URL location = getClass().getResource("EditSalesReturnBillUi.fxml");
			FXMLLoader loader=new FXMLLoader();
			loader.setLocation(location);
			loader.setBuilderFactory(new JavaFXBuilderFactory());
			Parent root=loader.load();
	
			Scene scene=new Scene(root, StageSize.STAGE_WIDTH, StageSize.STAGE_HEIGHT);
			stage.setScene(scene);
	
			EditSalesReturnBillUiController controller=loader.getController();
			controller.init(bill, operation);
			
			stage.show();
		}catch(RemoteException e){
			e.printStackTrace();
		}catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void editPurchaseBillUi(PurchaseBillVO bill, String operation){
		try{
			URL location = getClass().getResource("EditPurchaseBillUi.fxml");
			FXMLLoader loader=new FXMLLoader();
			loader.setLocation(location);
			loader.setBuilderFactory(new JavaFXBuilderFactory());
			Parent root=loader.load();
	
			Scene scene=new Scene(root, StageSize.STAGE_WIDTH, StageSize.STAGE_HEIGHT);
			stage.setScene(scene);
	
			EditPurchaseBillUiController controller=loader.getController();
			controller.init(bill, operation);
			
			stage.show();
		}catch(RemoteException e){
			e.printStackTrace();
		}catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void editPurchaseReturnBillUi(PurchaseReturnBillVO bill,String operation){
		try{
			URL location = getClass().getResource("EditPurchaseReturnBillUi.fxml");
			FXMLLoader loader=new FXMLLoader();
			loader.setLocation(location);
			loader.setBuilderFactory(new JavaFXBuilderFactory());
			Parent root=loader.load();
	
			Scene scene=new Scene(root, StageSize.STAGE_WIDTH, StageSize.STAGE_HEIGHT);
			stage.setScene(scene);
	
			EditPurchaseReturnBillUiController controller=loader.getController();
			controller.init(bill, operation);
			
			stage.show();
		}catch(RemoteException e){
			e.printStackTrace();
		}catch (IOException e) {
			e.printStackTrace();
		}
	}
}
