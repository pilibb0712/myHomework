package ui.promotionUi.viewPromotionUi;

import java.net.URL;

import javax.swing.text.View;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import ui.promotionUi.PromotionUiController;
import ui.uiAssistants.StageSize;
import vo.ComboStrategyVO;
import vo.UserStrategyVO;

public class ViewComboPromotionUiStarter {
	private ComboStrategyVO comboStrategyVO;//此界面要显示的userstrategy信息

	private Stage stage ;//可能要在当时的stage进行切换

   /**
   * 无参数构造方法
   */
	public ViewComboPromotionUiStarter(ComboStrategyVO vo){
		this(new Stage(),vo);
	}
	
	/**
	* stage内进行scene切换
	* @param stage 要切换的stage
	*/
	public ViewComboPromotionUiStarter (Stage stage,ComboStrategyVO vo){
		this.stage = stage;
		this.comboStrategyVO=vo;
	}
	
	/**
	* 新建一个总经理主界面
	*/
	public void start(){
		try {
			URL location = getClass().getResource("ViewComboPromotionUi.fxml");
			FXMLLoader flLoader = new FXMLLoader(location);
			Parent root = flLoader.load();
			
			Scene scene = new Scene(root,StageSize.STAGE_WIDTH, StageSize.STAGE_HEIGHT);
			stage.setScene(scene);
			stage.show();
			
			//设置controller并且初始化
			ViewComboPromotionUiController contoller = flLoader.getController();
			contoller.setComboStrategy(comboStrategyVO);
			contoller.init();
			
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
}
