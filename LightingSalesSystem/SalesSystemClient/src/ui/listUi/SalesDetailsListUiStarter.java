package ui.listUi;

import java.io.IOException;
import java.net.URL;

import assistant.type.UserPositionEnum;
import javafx.fxml.FXMLLoader;
import javafx.fxml.JavaFXBuilderFactory;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import ui.uiAssistants.StageFactory;
import ui.uiAssistants.StageSize;

/**
 * 销售明细表界面
 * @author guxinyu
 * @version 2017.12.10
 *
 */
public class SalesDetailsListUiStarter {
	private Stage stage;

	public void salesDetailsListUi(UserPositionEnum p){
		URL location = getClass().getResource("SalesDetailsListUi.fxml");
		FXMLLoader loader=new FXMLLoader();
		loader.setLocation(location);
	    loader.setBuilderFactory(new JavaFXBuilderFactory());
		Parent root=null;
		try {
			root = loader.load();
		} catch (IOException e) {
			e.printStackTrace();
		}
	
		if(p==UserPositionEnum.FINANCE)
			stage=StageFactory.getFinanceStage();
		else if(p==UserPositionEnum.MANAGER)
			stage=StageFactory.getManagerStage();
		
		Scene scene=new Scene(root, StageSize.STAGE_WIDTH, StageSize.STAGE_HEIGHT);
		stage.setScene(scene);
		
		SalesDetailsListUiController controller=loader.getController();
		controller.init(p);
	
		stage.show();
	}
}
