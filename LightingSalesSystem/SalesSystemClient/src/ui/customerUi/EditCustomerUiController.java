/**
 * 
 */
package ui.customerUi;

import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.Optional;

import assistant.type.CustomerLevelEnum;
import blService.customerBlService.CustomerBlService;
import blService.logBlService.LogBlService;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonBar.ButtonData;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Dialog;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.util.Pair;
import rmi.remoteHelper.CustomerRemoteHelper;
import rmi.remoteHelper.LogRemoteHelper;
import rmi.remoteHelper.RemoteHelperFactory;
import vo.CustomerVO;
import vo.UserInfoVO;

/**
 * @author 王宁一
 *
 */
public class EditCustomerUiController {
	
	private Stage stage;
	
	private SelectCustomerUiController selectCustomerUiController;
	
	private CustomerVO oriCustomer;
	
	private CustomerVO changedCustomer;

	@FXML
	private TextField idTextField;
	
	@FXML
	private TextField typeTextField;
	
	@FXML
	private ComboBox<String> levelComboBox;
	
	@FXML
	private TextField nameTextField;
	
	@FXML
	private TextField telTextField;
	
	@FXML
	private TextField addressTextField;
	
	@FXML
	private TextField postTextField;
	
	@FXML
	private TextField emailTextField;
	
	@FXML
	private TextField businessmanTextField;
	
	@FXML
	private Text shouldReceiveText;
	
	@FXML
	private Text shouldPayText;
	
	@FXML
	private TextField shouldReceiveCreditTextField;
	
	@FXML
	private Button confirmButton;
	
	@FXML
	private Button cancleButton;
	
	ObservableList<String> options=FXCollections.observableArrayList(
			CustomerLevelEnum.VIP1.toString(),
			CustomerLevelEnum.VIP2.toString(),
			CustomerLevelEnum.VIP3.toString(),
			CustomerLevelEnum.VIP4.toString(),
			CustomerLevelEnum.VIP5.toString()
		);
	
	private CustomerRemoteHelper customerRemoteHelper=RemoteHelperFactory.getCustomerRemoteHelper();
	
	private LogRemoteHelper logRemoteHelper=RemoteHelperFactory.getLogRemoteHelper();
	
	private CustomerBlService service=customerRemoteHelper.getCustomerBlService();
	//private CustomerBlService service=new CustomerBlService_Stub();
	
	private LogBlService verifyService=logRemoteHelper.getLogBlService();
	//private LogBlService verifyService=new LogBlService_Stub();
	
	public void init(SelectCustomerUiController selectCustomerUiController, CustomerVO customerVO, Stage stage) {
		
		changedCustomer=new CustomerVO();
		
		this.selectCustomerUiController=selectCustomerUiController;
		this.oriCustomer=customerVO;
		this.stage=stage;
		
		
		idTextField.setText(oriCustomer.getId());
		typeTextField.setText(oriCustomer.getType());
		
		levelComboBox.setItems(options);
		levelComboBox.setEditable(false);
		
		if(oriCustomer.getLevel()!=null) {
			levelComboBox.setValue(oriCustomer.getLevel().toString());
		}
		
		levelComboBox.setOnAction(eve->{
			changedCustomer.setLevel(CustomerLevelEnum.valueOf(levelComboBox.getSelectionModel().getSelectedItem()));
		});
		
		if(oriCustomer.getName()!=null) {
			nameTextField.setText(oriCustomer.getName());
		}
		if(oriCustomer.getAddress()!=null) {
			addressTextField.setText(oriCustomer.getAddress());
		}
		if(oriCustomer.getEmail()!=null) {
			emailTextField.setText(oriCustomer.getEmail());
		}
		if(oriCustomer.getTel()!=null) {
			telTextField.setText(oriCustomer.getTel());
		}
		if(oriCustomer.getPost()!=null) {
			postTextField.setText(oriCustomer.getPost());
		}		
		if(oriCustomer.getDefaultSalesman()!=null) {
			businessmanTextField.setText(oriCustomer.getDefaultSalesman().getName());
		}
		
		shouldReceiveText.setText(oriCustomer.getShouldReceive()+"");
		shouldPayText.setText(oriCustomer.getShouldPay()+"");
		shouldReceiveCreditTextField.setText(oriCustomer.getShouldReceiveCredit()+"");
		shouldReceiveCreditTextField.setEditable(false);
	}
	
	@FXML
	protected void confirmButtonListener() {
		changedCustomer.setId(idTextField.getText());
		changedCustomer.setType(typeTextField.getText());
		changedCustomer.setLevel(CustomerLevelEnum.valueOf(levelComboBox.getValue()));
		changedCustomer.setName(nameTextField.getText());
		changedCustomer.setAddress(addressTextField.getText());
		changedCustomer.setEmail(emailTextField.getText());
		changedCustomer.setTel(telTextField.getText());
		changedCustomer.setPost(postTextField.getText());
		String businessmanStr=businessmanTextField.getText();
		
		try {
			if(null==service.getUserByName(businessmanStr)) {
				changedCustomer.setDefaultSalesman(null);
				Alert alert=new Alert(AlertType.ERROR);
				alert.setTitle("错误");
				alert.setHeaderText(null);
				alert.setContentText("不存在该业务员!");
				alert.showAndWait();
				changedCustomer.setDefaultSalesman(oriCustomer.getDefaultSalesman());
			}else {
				UserInfoVO businessman=service.getUserByName(businessmanStr);
				changedCustomer.setDefaultSalesman(businessman);
			}
			
		} catch (RemoteException e) {
			e.printStackTrace();
		}
		changedCustomer.setShouldReceive(oriCustomer.getShouldReceive());
		changedCustomer.setShouldPay(oriCustomer.getShouldPay());
		changedCustomer.setShouldReceiveCredit(Double.parseDouble(shouldReceiveCreditTextField.getText()));
		//弹窗提示确认
		Alert alert=new Alert(AlertType.INFORMATION);
		alert.setTitle("系统消息");
		alert.setHeaderText(null);
		alert.setContentText("修改客户信息成功!");
		alert.showAndWait();
		selectCustomerUiController.changeCustomer(oriCustomer, changedCustomer);
		stage.close();
	}
	
	@FXML
	protected void cancleButtonListener() {
		//弹窗提示 确认取消？
		Alert confirmation=new Alert(AlertType.CONFIRMATION);
		confirmation.setTitle("系统消息");
		confirmation.setHeaderText(null);
		confirmation.setContentText("您确定要取消编辑吗？");
		Optional<ButtonType> result=confirmation.showAndWait();
		if(result.get()==ButtonType.OK) {
			stage.close();
		}else {
			confirmation.close();
		}
	}
	
	@FXML
	protected void shouldReceiveCreditChanged() {
		Dialog<Pair<String, String>> dialog=new Dialog<>();
		dialog.setTitle("身份验证");
		dialog.setHeaderText("请确认您的权限");
		
		ButtonType loginButtonType=new ButtonType("确认", ButtonData.OK_DONE);
		dialog.getDialogPane().getButtonTypes().addAll(loginButtonType, ButtonType.CANCEL);
		
		GridPane grid = new GridPane();
		grid.setHgap(10);
		grid.setVgap(10);
		grid.setPadding(new Insets(20, 150, 10, 10));

		TextField userId = new TextField();
		userId.setPromptText("UserID");
		PasswordField password = new PasswordField();
		password.setPromptText("Password");

		grid.add(new Label("UserID:"), 0, 0);
		grid.add(userId, 1, 0);
		grid.add(new Label("Password:"), 0, 1);
		grid.add(password, 1, 1);

		Node loginButton = dialog.getDialogPane().lookupButton(loginButtonType);
		loginButton.setDisable(true);
		
		userId.textProperty().addListener((observable, oldValue, newValue) -> {
		    loginButton.setDisable(newValue.trim().isEmpty());
		});

		dialog.getDialogPane().setContent(grid);

		Platform.runLater(()->userId.requestFocus());
		
		dialog.setResultConverter(dialogButton->{
			if(dialogButton==loginButtonType) {
				return new Pair<>(userId.getText(),password.getText());
			}
			return null;
		});
		
		Optional<Pair<String, String>> result=dialog.showAndWait();
		
		result.ifPresent(idPw->{
			//验证账号密码
			String id=idPw.getKey();
			String pw=idPw.getValue();
			//最高权限
			try {
				if(verifyService.isValidUser(id, pw)) {
					ArrayList<UserInfoVO> userInfoVOs=new ArrayList<>();
					userInfoVOs=service.GetUsers();
					for(int i=0;i<userInfoVOs.size();i++) {
						UserInfoVO user=userInfoVOs.get(i);
						if(user.getId().equals(id)) {
							if(user.getUserPositionEnum().toString().equals("manager")) {
								shouldReceiveCreditTextField.setEditable(true);
								dialog.close();
							}else {
								dialog.setContentText("您没有权限");
								shouldReceiveCreditTextField.setEditable(false);
								shouldReceiveCreditTextField.setText("您没有权限");
							}
						}
					}
					
				}else {
					dialog.setContentText("账号或密码错误");
				}
			} catch (RemoteException e) {
				e.printStackTrace();
			}
			
		});

	}
	
			
	
}
































