package ui.promotionUi.updatePromotionUi;

import java.net.URL;
import java.util.ResourceBundle.Control;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import ui.promotionUi.PromotionUiController;
import ui.uiAssistants.StageSize;
import vo.TotalStrategyVO;

public class UpdateTotalPromotionUiStarter {
	private PromotionUiController promotionUiController;//持有他的controller的引用
	private TotalStrategyVO totalStrategyVO;//此界面要显示的totalStrategy信息

	private Stage stage ;//可能要在当时的stage进行切换

   /**
   * 无参数构造方法
   */
	public UpdateTotalPromotionUiStarter(PromotionUiController promotionUiController,TotalStrategyVO vo){
		this(new Stage(),promotionUiController,vo);
	}
	
	/**
	* stage内进行scene切换
	* @param stage 要切换的stage
	*/
	public UpdateTotalPromotionUiStarter (Stage stage,PromotionUiController promotionUiController,TotalStrategyVO vo){
		this.promotionUiController = promotionUiController;
		this.stage = stage;
		this.totalStrategyVO =vo;
	}
	
	/**
	* 新建一个总经理主界面
	*/
	public void start(){
		try {
			URL location = getClass().getResource("UpdateTotalPromotionUi.fxml");
			FXMLLoader flLoader = new FXMLLoader(location);
			Parent root = flLoader.load();
			
			//设置controller并且初始化
			UpdateTotalPromotionUiController contoller = flLoader.getController();
			contoller.setPromotionUiController(promotionUiController);
			contoller.setTotalStrategyVO(totalStrategyVO);
			contoller.init();
			
			Scene scene = new Scene(root,StageSize.STAGE_WIDTH, StageSize.STAGE_HEIGHT);
			stage.setScene(scene);
			stage.show();
			
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
}
