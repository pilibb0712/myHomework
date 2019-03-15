package ui.financeUi;

import java.io.IOException;
import java.net.URL;
import java.rmi.RemoteException;
import javafx.fxml.FXMLLoader;
import javafx.fxml.JavaFXBuilderFactory;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import ui.uiAssistants.StageFactory;
import ui.uiAssistants.StageSize;

/**
 * 账户列表界面启动类
 * @author guxinyu
 * @version 11.27
 *
 */
public class BankAccountUiStarter {
	private Stage stage=StageFactory.getFinanceStage();
	
	public void viewBankAccounts(){
		
		try {
			
			URL location = getClass().getResource("BankAccountUi.fxml");
			FXMLLoader loader=new FXMLLoader();
			loader.setLocation(location);
		    loader.setBuilderFactory(new JavaFXBuilderFactory());
			Parent root = loader.load();
		
			Scene scene=new Scene(root, StageSize.STAGE_WIDTH, StageSize.STAGE_HEIGHT);
			stage.setScene(scene);
			
			BankAccountUiController controller=loader.getController();
			controller.init();
			
			stage.show();
		}catch(RemoteException e){
			e.printStackTrace();
		}catch (IOException e) {
			e.printStackTrace();
		}
		
	}
	
}
