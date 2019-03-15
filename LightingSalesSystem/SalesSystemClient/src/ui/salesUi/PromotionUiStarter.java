/**
 * 
 */
package ui.salesUi;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;

import assistant.type.CustomerLevelEnum;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import vo.SalesGoodsVO;

/**
 * @author ÍõÄþÒ»
 *
 */
public class PromotionUiStarter {

	private Stage stage=new Stage();
	
	private double consumption;
	
	private CustomerLevelEnum level;
	
	private ArrayList<SalesGoodsVO> goodsVOs;
	
	public PromotionUiStarter(double consumption, CustomerLevelEnum level, ArrayList<SalesGoodsVO> goodsVOs) {
		this.consumption=consumption;
		this.level=level;
		this.goodsVOs=goodsVOs;
	}
	
	public void setStage(Stage stage) {
		this.stage=stage;
	}
	
	public void selectStragety(NewSalesBillUiController salesBillUiController) {
		URL location = getClass().getResource("PromotionChooseUi.fxml");
		FXMLLoader loader=new FXMLLoader();
		loader.setLocation(location);
		try {
			Parent root=loader.load();
			
			Scene scene=new Scene(root,600,400);
			stage.setScene(scene);
			
			PromotionUiController controller=loader.getController();
			
			controller.setStage(stage);
			controller.init(salesBillUiController, consumption, level, goodsVOs);
			
			stage.show();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
}
