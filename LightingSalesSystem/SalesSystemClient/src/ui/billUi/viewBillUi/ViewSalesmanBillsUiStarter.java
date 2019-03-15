/**
 * 
 */
package ui.billUi.viewBillUi;

import java.net.URL;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import ui.uiAssistants.StageSize;

/**
 * @author 王宁一
 *
 */
public class ViewSalesmanBillsUiStarter {

	private Stage stage=new Stage();
	
	public void viewPurchaseBill(String billId) {
		
		try {
			URL location = getClass().getResource("ViewSalesmanPurchasesBillUi.fxml");
			FXMLLoader loader=new FXMLLoader();
			loader.setLocation(location);
			Parent root=loader.load();
			
			Scene scene=new Scene(root, StageSize.STAGE_WIDTH, StageSize.STAGE_HEIGHT);
			stage.setScene(scene);
			
			ViewSalesmanPurchaseBillUiController controller=loader.getController();
			controller.viewPurchaseBill("供应商进货单", billId);
			
			stage.show();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void viewPurchaseReturnBill(String billId) {
		try {
			URL location = getClass().getResource("ViewSalesmanPurchasesBillUi.fxml");
			FXMLLoader loader=new FXMLLoader();
			loader.setLocation(location);
			Parent root=loader.load();
			
			Scene scene=new Scene(root, StageSize.STAGE_WIDTH, StageSize.STAGE_HEIGHT);
			stage.setScene(scene);
			
			ViewSalesmanPurchaseBillUiController controller=loader.getController();
			controller.viewPurchaseBill("供应商进货退货单", billId);
			
			stage.show();
		} catch (Exception e) {
			
		}
	}	
	
	public void viewSalesReturnBill(String billId) {
		try {
			URL location = getClass().getResource("ViewSalesmanPurchasesBillUi.fxml");
			FXMLLoader loader=new FXMLLoader();
			loader.setLocation(location);
			Parent root=loader.load();
			
			Scene scene=new Scene(root, StageSize.STAGE_WIDTH, StageSize.STAGE_HEIGHT);
			stage.setScene(scene);
			
			ViewSalesmanPurchaseBillUiController controller=loader.getController();
			controller.viewPurchaseBill("销售商出货退货单", billId);
			
			stage.show();
		} catch (Exception e) {
			
		}
	}
	
	public void viewSalesBill(String billId) {
		try {
			URL location = getClass().getResource("ViewSalesmanSalesBill.fxml");
			FXMLLoader loader=new FXMLLoader();
			loader.setLocation(location);
			Parent root=loader.load();
			
			Scene scene=new Scene(root, StageSize.STAGE_WIDTH, StageSize.STAGE_HEIGHT);
			stage.setScene(scene);
			
			ViewSalesmanSalesBillUiController controller=loader.getController();
			controller.viewSalesBill("销售商出货单", billId);
			
			stage.show();
		} catch (Exception e) {
			
		}
	}
	
}































