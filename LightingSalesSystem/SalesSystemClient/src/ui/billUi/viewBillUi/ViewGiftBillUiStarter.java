package ui.billUi.viewBillUi;

import java.net.URL;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import ui.uiAssistants.StageSize;
import vo.GiftBillVO;

public class ViewGiftBillUiStarter {
	private Stage stage ;//可能要在当时的stage进行切换
	private String  billId;//页面初始化时将要现实的goods
	
   /**
   * 无参数构造方法
   */
	public ViewGiftBillUiStarter(String billId){
		this(new Stage(),billId);
	}
	
	/**
	* stage内进行scene切换
	* @param stage 要切换的stage
	*/
	public ViewGiftBillUiStarter(Stage stage,String billId){
		this.stage = stage;
		this.billId = billId;
	}
	
	/**
	* 新建一个giftBill单据查看界面
	*/
	public void start(){
		try {
			URL location = getClass().getResource("ViewGiftBillUi.fxml");
			FXMLLoader flLoader = new FXMLLoader(location);
			Parent root = flLoader.load();
			
			//设置controller并且初始化
			ViewGiftBillUiController controller = flLoader.getController();
			controller.init(billId);
			
			Scene scene = new Scene(root,StageSize.STAGE_WIDTH, StageSize.STAGE_HEIGHT);
			stage.setScene(scene);
			stage.show();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	
}
