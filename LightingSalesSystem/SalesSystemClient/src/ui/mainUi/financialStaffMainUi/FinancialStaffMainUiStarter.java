package ui.mainUi.financialStaffMainUi;

import java.io.IOException;
import java.net.URL;

import javafx.fxml.FXMLLoader;
import javafx.fxml.JavaFXBuilderFactory;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import ui.uiAssistants.StageFactory;
import ui.uiAssistants.StageSize;
import vo.UserInfoVO;

/**
 * 财务人员主界面
 * @author guxinyu
 * @version 2017.12.28
 *
 */
public class FinancialStaffMainUiStarter {
	private static UserInfoVO user;
	
	private Stage stage=StageFactory.getFinanceStage();
	
	public FinancialStaffMainUiStarter(){
		
	}
	
	public FinancialStaffMainUiStarter(UserInfoVO u){
		user=u;
	}
	
	public void mainUi(){
		try {
			URL location = getClass().getResource("FinanceStaffMainUi.fxml");
			FXMLLoader loader=new FXMLLoader();
			loader.setLocation(location);
			loader.setBuilderFactory(new JavaFXBuilderFactory());
			Parent root=loader.load();
			
			
			Scene scene=new Scene(root, StageSize.STAGE_WIDTH, StageSize.STAGE_WIDTH);
			stage.setScene(scene);
			
			FinancialStaffMainUiController controller=loader.getController();
			controller.init(user);
			
			stage.show();
		} catch (IOException e) {
			e.printStackTrace();
		} 	
	}
}
