package ui.promotionUi.updatePromotionUi;

import java.net.URL;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import ui.promotionUi.PromotionUiController;
import ui.uiAssistants.StageSize;
import vo.UserStrategyVO;


public class UpdateUserPromotionUiStarter {
	
		private PromotionUiController promotionUiController;//持有他的controller的引用
		private UserStrategyVO userStrategyVO;//此界面要显示的userStrategy信息
	
		private Stage stage ;//可能要在当时的stage进行切换

	   /**
	   * 无参数构造方法
	   */
		public UpdateUserPromotionUiStarter(PromotionUiController promotionUiController,UserStrategyVO vo){
			this(new Stage(),promotionUiController,vo);
		}
		
		/**
		* stage内进行scene切换
		* @param stage 要切换的stage
		*/
		public UpdateUserPromotionUiStarter(Stage stage,PromotionUiController promotionUiController,UserStrategyVO vo){
			this.promotionUiController = promotionUiController;
			this.stage = stage;
			this.userStrategyVO= vo;
		}
		
		/**
		* 新建一个总经理主界面
		*/
		public void start(){
			try {
				URL location = getClass().getResource("UpdateUserPromotionUi.fxml");
				FXMLLoader flLoader = new FXMLLoader(location);
				Parent root = flLoader.load();
				
				Scene scene = new Scene(root,StageSize.STAGE_WIDTH, StageSize.STAGE_HEIGHT);
				stage.setScene(scene);
				stage.show();
				
				//设置controller并且初始化
				UpdateUserPromotionUiContoller contoller = flLoader.getController();
				contoller.setPromotionUiController(promotionUiController);
				contoller.setUserStrategyVO(userStrategyVO);
				contoller.init();
				
			} catch(Exception e) {
				e.printStackTrace();
			}
		}
}
