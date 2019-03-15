/**
 * 
 */
package ui.loginUi;

import java.rmi.RemoteException;
import blService.logBlService.LogBlService;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import rmi.remoteHelper.LogRemoteHelper;
import rmi.remoteHelper.RemoteHelperFactory;
import ui.uiAssistants.StageFactory;

/**
 * @author cosx
 *
 */
public class RegisterUiController {

	Stage stage=StageFactory.getLogStage();
	
	@FXML
	private Text idPromptText;
	
	@FXML
	private TextField userIdTextField;
	
	@FXML
	private Text oldPwPromptText;
	
	@FXML
	private PasswordField oldPwTextField;
	
	@FXML
	private Text newPwPromptText;
	
	@FXML
	private PasswordField newPwTextField;
	
	@FXML
	private Text promptText;
	
	@FXML
	private Button confirmButton;
	
	@FXML
	private Button cancleButton;
	
	
	//LogBlService loginService=new LogBlService_Stub();
	private LogRemoteHelper logRemoteHelper=RemoteHelperFactory.getLogRemoteHelper();
	private LogBlService loginService=logRemoteHelper.getLogBlService();
	
	boolean registered=false;
	
	public void init(boolean m_registered) {
		registered=m_registered;
		if(!registered) {
			stage.setTitle("注册");
			oldPwPromptText.setText("验证密码：");
			oldPwTextField.setPromptText("请输入初始密码");
			
			newPwPromptText.setVisible(false);
			newPwTextField.setVisible(false);
		}else {
			stage.setTitle("修改密码");
			idPromptText.setText("账号");
			oldPwPromptText.setText("旧密码");
			oldPwTextField.setPromptText("请输入旧密码");
		}
		
	}
	
	@FXML
	private void confirm() {
		String userId=userIdTextField.getText();
		String oldPassword=oldPwTextField.getText();
		if(!registered) {
			try {
				if(loginService.registerUser(userId, oldPassword)) {
					registered=true;
					RegisterUiStarter starter=new RegisterUiStarter();
					starter.startRegister(true);
				}else {
					promptText.setText("管理员处未注册！");
				}
			} catch (RemoteException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				promptText.setText("注册失败，请重试！");
			}
		}else {
			String newPassword=newPwTextField.getText();
			try {
				if(loginService.changePassword(userId, oldPassword, newPassword)) {
					promptText.setText("更改密码成功，请重新登录");
					Alert alert=new Alert(AlertType.INFORMATION);
					alert.setTitle("系统消息");
					alert.setHeaderText(null);
					alert.setContentText("更改密码成功，请重新登录");
					alert.showAndWait();
					LoginUiStarter starter=new LoginUiStarter();
					starter.startLogin();
				}else {
					promptText.setText("更改密码失败");
					oldPwPromptText.setText("");
					newPwPromptText.setText("");
				}
			} catch (RemoteException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		
	}
	
	@FXML
	private void cancle() {
		LoginUiStarter starter=new LoginUiStarter();
		starter.startLogin();
	}
	
}



















