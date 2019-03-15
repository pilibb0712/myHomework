package ui.promotionUi.addPromotionUi;

import java.net.URL;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import ui.promotionUi.PromotionUiController;
import ui.uiAssistants.StageSize;

public class AddComboPromotionUiStarter {
	private PromotionUiController promotionUiController;//持有他的controller的引用
	//private UserStrategyVO userStrategyVO;//此界面要显示的userstrategy信息

	private Stage stage ;//可能要在当时的stage进行切换

   /**
   * 无参数构造方法
   */
	public AddComboPromotionUiStarter(PromotionUiController promotionUiController){
		this(new Stage(),promotionUiController);
	}
	
	/**
	* stage内进行scene切换
	* @param stage 要切换的stage
	*/
	public AddComboPromotionUiStarter (Stage stage,PromotionUiController promotionUiController){
		this.promotionUiController = promotionUiController;
		this.stage = stage;
	}
	
	/**
	* 新建一个总经理主界面
	*/
	public void start(){
		try {
			URL location = getClass().getResource("AddComboPromotionUi.fxml");
			FXMLLoader flLoader = new FXMLLoader(location);
			Parent root = flLoader.load();
			
			Scene scene = new Scene(root,StageSize.STAGE_WIDTH, StageSize.STAGE_HEIGHT);
			stage.setScene(scene);
			stage.show();
			
			//设置controller并且初始化
			AddComboPromotionUiController contoller = flLoader.getController();
			contoller.setPromotionUiController(promotionUiController);
			contoller.init();
			
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
}
