/**
 * 
 */
package ui.customerUi;

import java.net.URL;

import javafx.fxml.FXMLLoader;
import javafx.fxml.JavaFXBuilderFactory;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import ui.uiAssistants.StageFactory;
import ui.uiAssistants.StageSize;
import vo.CustomerVO;

/**
 * @author ÍõÄþÒ»
 *
 */
public class EditCustomerUiStarter {

	private Stage stage=new Stage();
	
	public void editCustomer(SelectCustomerUiController selectCustomerUiController, CustomerVO vo) {
		try {
			URL location=getClass().getResource("EditCustomerUi.fxml");
			FXMLLoader loader=new FXMLLoader();
			loader.setLocation(location);
		    loader.setBuilderFactory(new JavaFXBuilderFactory());
			Parent root = loader.load();
			
			Scene scene=new Scene(root, StageSize.STAGE_WIDTH, StageSize.STAGE_HEIGHT);
			stage.setScene(scene);
			
			EditCustomerUiController controller=loader.getController();
			
			controller.init(selectCustomerUiController, vo, stage);
			
			stage.show();
			
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}
}
