package ui.promotionUi.viewPromotionUi;

import java.net.URL;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import ui.uiAssistants.StageSize;
import vo.UserStrategyVO;


public class ViewUserPromotionUiStarter {
	private UserStrategyVO userStrategyVO;//此界面要显示的userstrategy信息

	private Stage stage ;//可能要在当时的stage进行切换

   /**
   * 无参数构造方法
   */
	public ViewUserPromotionUiStarter(UserStrategyVO vo){
		this(new Stage(),vo);
	}
	
	/**
	* stage内进行scene切换
	* @param stage 要切换的stage
	*/
	public ViewUserPromotionUiStarter (Stage stage,UserStrategyVO vo){
		this.stage = stage;
		this.userStrategyVO = vo;
	}
	
	/**
	* 新建一个总经理主界面
	*/
	public void start(){
		try {
			URL location = getClass().getResource("ViewUserPromotionUi.fxml");
			FXMLLoader flLoader = new FXMLLoader(location);
			Parent root = flLoader.load();
			

			//设置controller并且初始化
			ViewUserPromotionUiContoller contoller = flLoader.getController();
			contoller.setUserStrategy(userStrategyVO);
			contoller.init();
			
			Scene scene = new Scene(root,StageSize.STAGE_WIDTH, StageSize.STAGE_HEIGHT);
			stage.setScene(scene);
			stage.show();
			
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
}
