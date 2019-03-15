/**
 *
 */
package ui.loginUi;

import java.rmi.RemoteException;

import assistant.exception.Myexception;
import blService.adminBlService.AdminBlService;
import blService.logBlService.LogBlService;
import blStubs.adminBlStubs.AdminBlService_Stub;
import blStubs.logBlStubs.LogBlService_Stub;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import rmi.remoteHelper.AdminRemoteHelper;
import rmi.remoteHelper.LogRemoteHelper;
import rmi.remoteHelper.RemoteHelperFactory;
import ui.adminUi.AdminUiStarter;
import ui.mainUi.financialStaffMainUi.FinancialStaffMainUiStarter;
import ui.mainUi.managerMainUi.ManagerMainUiStarter;
import ui.mainUi.salesmanMainUi.SalesmanMainUiStarter;
import ui.mainUi.wareKeeperMainUi.WareKeeperMainUiStarter;
import ui.uiAssistants.StageFactory;
import vo.UserInfoVO;
import vo.UserVO;

/**
 * @author 王宁一
 * @version 2017.12.27
 * 登录界面Controller
 *
 */
public class LoginUiController {

	Stage stage=StageFactory.getLogStage();

	@FXML
	private TextField userIdTextField;

	@FXML
	private PasswordField userPasswordTextField;

	@FXML
	private Text promptTextField;

	@FXML
	private Button registerButton;

	@FXML
	private Button LoginButton;

	/**
	 * 设置界面
	 */
	//AdminBlService getUserService=new AdminBlService_Stub();
	//LogBlService loginService=new LogBlService_Stub();
	private AdminRemoteHelper adminRemoteHelper=RemoteHelperFactory.getAdminRemoteHelper();
	private AdminBlService getUserService=adminRemoteHelper.getAdminBlService();

	private LogRemoteHelper logRemoteHelper=RemoteHelperFactory.getLogRemoteHelper();
	private LogBlService loginService=logRemoteHelper.getLogBlService();

	public void setStage(Stage s){
		stage=s;
	}

	public void init(Stage stage) {
		this.stage=stage;
	}

	@FXML
	private void login() {
		String idString=userIdTextField.getText();
		String pwString=userPasswordTextField.getText();

		try {
			if(loginService.isValidUser(idString, pwString)) {
				UserVO userVO=new UserVO();
				try {
					userVO = getUserService.getUser(idString);
				} catch (Myexception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				UserInfoVO userInfoVO=new UserInfoVO();
				userInfoVO.setId(idString);
				userInfoVO.setName(userVO.getName());
				userInfoVO.setUserPositionEnum(userVO.getPosition());

				switch (userVO.getPosition()) {
				case MANAGER:
					ManagerMainUiStarter managerMainUiStarter=new ManagerMainUiStarter();
					managerMainUiStarter.start(userInfoVO);
					stage.close();
					break;
				case WAREKEPPER:
					WareKeeperMainUiStarter wareKeeperMainUiStarter=new WareKeeperMainUiStarter();
					wareKeeperMainUiStarter.wareKeeperMainUi(null,userInfoVO.getName(), idString);
					stage.close();
					break;
				case FINANCE:
					FinancialStaffMainUiStarter financialStaffMainUiStarter=new FinancialStaffMainUiStarter(userInfoVO);
					financialStaffMainUiStarter.mainUi();
					stage.close();
					break;
				case ADMIN:
					AdminUiStarter adminUiStarter=new AdminUiStarter();
					adminUiStarter.start();
					stage.close();
					break;
				case SALESMAN:
					//待完成
					SalesmanMainUiStarter salesmanMainUiStarter=new SalesmanMainUiStarter();
					salesmanMainUiStarter.startSalesman(userInfoVO);
					stage.close();
					break;

				default:
					break;
				}
			}else {
				promptTextField.setText("密码错误，请重新输入或注册");
			}
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@FXML
	private void register() {
		RegisterUiStarter registerUiStarter=new RegisterUiStarter();
		registerUiStarter.startRegister(false);
	}

	@FXML
	protected void exitButtonListener(){
		System.exit(0);
	}
}

























