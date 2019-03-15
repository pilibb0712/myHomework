package ui.promotionUi.viewPromotionUi;

import java.net.URL;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import ui.uiAssistants.StageSize;
import vo.TotalStrategyVO;

public class ViewTotalPromotionUiStarter {
	private TotalStrategyVO totalStrategyVO;//此界面要显示的TotalStrategy信息

	private Stage stage ;//可能要在当时的stage进行切换

   /**
   * 无参数构造方法
   */
	public ViewTotalPromotionUiStarter(TotalStrategyVO vo){
		this(new Stage(),vo);
	}
	
	/**
	* stage内进行scene切换
	* @param stage 要切换的stage
	*/
	public ViewTotalPromotionUiStarter (Stage stage,TotalStrategyVO vo){
		this.stage = stage;
		this.totalStrategyVO = vo;
	}
	
	/**
	* 新建一个总经理主界面
	*/
	public void start(){
		try {
			URL location = getClass().getResource("ViewTotalPromotionUi.fxml");
			FXMLLoader flLoader = new FXMLLoader(location);
			Parent root = flLoader.load();
			
			Scene scene = new Scene(root,StageSize.STAGE_WIDTH, StageSize.STAGE_HEIGHT);
			stage.setScene(scene);
			stage.show();
			
			//设置controller并且初始化
			ViewTotalPromotionUiController contoller = flLoader.getController();
			contoller.setTotalStrategy(totalStrategyVO);
			contoller.init();
			
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
}
