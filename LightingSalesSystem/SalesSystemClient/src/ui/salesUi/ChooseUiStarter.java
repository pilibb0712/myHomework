/**
 * 
 */
package ui.salesUi;

import java.net.URL;
import java.util.ArrayList;

import blService.salesBlService.SalesBlService;
import blStubs.salesBlStubs.SalesBlService_Stub;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import vo.GoodsVO;
import vo.SalesGoodsVO;

/**
 * @author 王宁一
 * @version 2017.12.26
 * 商品选择界面
 *
 */
public class ChooseUiStarter {
	
	private Stage stage=new Stage();
	
	public void setStage(Stage stage) {
		this.stage=stage;
	}
	
	public void ChooseUiStater() {
		
	}
	
	public void purchaseStart(NewPurchaseBillUiController purchaseController, int position) {
		try {
			URL location = getClass().getResource("Choose.fxml");
			FXMLLoader loader=new FXMLLoader();
			loader.setLocation(location);
			Parent root=loader.load();
			
			Scene scene=new Scene(root,600,400);
			stage.setScene(scene);
			
			ChooseUiController controller=loader.getController();
			
			controller.setStage(stage);
			controller.SelectPurchaseCommodity(purchaseController, position);
			
			stage.show();
		}catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void purchaseReturnStart(NewPurchaseReturnBillUiController purchaseReturnBillUiController, int position) {
		try {
			URL location = getClass().getResource("Choose.fxml");
			FXMLLoader loader=new FXMLLoader();
			loader.setLocation(location);
			Parent root=loader.load();
			
			Scene scene=new Scene(root,600,400);
			stage.setScene(scene);
			
			ChooseUiController controller=loader.getController();
			
			controller.setStage(stage);
			controller.SelectPurchaseReturnCommodity(purchaseReturnBillUiController, position);
			
			stage.show();
		}catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void salesStart(NewSalesBillUiController salesController, int position) {
		try {
			URL location = getClass().getResource("Choose.fxml");
			FXMLLoader loader=new FXMLLoader();
			loader.setLocation(location);
			Parent root=loader.load();
			
			Scene scene=new Scene(root,600,400);
			stage.setScene(scene);
			
			ChooseUiController controller=loader.getController();
			
			controller.setStage(stage);
			controller.SelectSalesCommodity(salesController, position);
			
			stage.show();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void salesReturnStart(NewSalesReturnBillUiController salesReturnController, int position) {
		try {
			URL location = getClass().getResource("Choose.fxml");
			FXMLLoader loader=new FXMLLoader();
			loader.setLocation(location);
			Parent root=loader.load();
			
			Scene scene=new Scene(root,600,400);
			stage.setScene(scene);
			
			ChooseUiController controller=loader.getController();
			
			controller.setStage(stage);
			controller.SelectSalesReturnCommodity(salesReturnController, position);
			
			stage.show();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
}
