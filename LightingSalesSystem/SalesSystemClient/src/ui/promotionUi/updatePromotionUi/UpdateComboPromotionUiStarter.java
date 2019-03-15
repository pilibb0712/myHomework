package ui.promotionUi.updatePromotionUi;

import java.net.URL;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import ui.promotionUi.PromotionUiController;
import ui.uiAssistants.StageSize;
import vo.ComboStrategyVO;

public class UpdateComboPromotionUiStarter {
	private PromotionUiController promotionUiController;//持有他的controller的引用
	private ComboStrategyVO comboStrategyVO;//此界面要显示的userstrategy信息

	private Stage stage ;//可能要在当时的stage进行切换

   /**
   * 无参数构造方法
   */
	public UpdateComboPromotionUiStarter(PromotionUiController promotionUiController,ComboStrategyVO vo){
		this(new Stage(),promotionUiController,vo);
	}
	
	/**
	* stage内进行scene切换
	* @param stage 要切换的stage
	*/
	public UpdateComboPromotionUiStarter (Stage stage,PromotionUiController promotionUiController,ComboStrategyVO vo){
		this.promotionUiController = promotionUiController;
		this.stage = stage;
		this.comboStrategyVO = vo;
	}
	
	/**
	* 新建一个总经理主界面
	*/
	public void start(){
		try {
			URL location = getClass().getResource("UpdateComboPromotionUi.fxml");
			FXMLLoader flLoader = new FXMLLoader(location);
			Parent root = flLoader.load();
			
			Scene scene = new Scene(root,StageSize.STAGE_WIDTH, StageSize.STAGE_HEIGHT);
			stage.setScene(scene);
			stage.show();
			
			//设置controller并且初始化
			UpdateComboPromotionUiController contoller = flLoader.getController();
			contoller.setPromotionUiController(promotionUiController);
			contoller.setComboStrategyVO(comboStrategyVO);
			contoller.init();
			
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
}
