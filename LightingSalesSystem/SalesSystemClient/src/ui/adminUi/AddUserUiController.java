package ui.adminUi;

import assistant.type.UserPositionEnum;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn.CellEditEvent;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Text;
import vo.UserVO;

public class AddUserUiController {
	private UserVO oldUser;
	private UserPositionEnum userPosition;
	private AdminForAddUserController addUserController;
	private AdminForUpdateUserController updateUserController;
	
	/**
	 * 界面center部分
	 * 用户信息填写框
	 * */
	@FXML TextField nameTextField;
	@FXML TextField positionTextField;
	@FXML TextField ageTextField;
	@FXML TextArea descriptionTextArea;
	
	private void initUserText(){
		nameTextField.setText(oldUser.getName());
		positionTextField.setText(oldUser.getPosition().toChineseString());
		ageTextField.setText(oldUser.getAge()+"");
		descriptionTextArea.setText(oldUser.getDescription());
	}
	
	/**
	 * 用户职位选择menuButton的监听器
	 * */
	@FXML
	protected void managerMenuItemListener(){
		userPosition = UserPositionEnum.MANAGER;
		positionTextField.setText(UserPositionEnum.MANAGER.toChineseString());
	}
	@FXML
	protected void financeMenuItemListener(){
		userPosition = UserPositionEnum.FINANCE;
		positionTextField.setText(UserPositionEnum.FINANCE.toChineseString());
	}
	@FXML
	protected void wareKeeperMenuItemListener(){
		userPosition = UserPositionEnum.WAREKEPPER;
		positionTextField.setText(UserPositionEnum.WAREKEPPER.toChineseString());
	}
	@FXML
	protected void adminMenuItemListener(){
		userPosition = UserPositionEnum.ADMIN;
		positionTextField.setText(UserPositionEnum.ADMIN.toChineseString());
	}
	@FXML
	protected void salesmanMenuItemListener(){
		userPosition = UserPositionEnum.SALESMAN;
		positionTextField.setText(UserPositionEnum.SALESMAN.toChineseString());
	}
	
	/**
	 * 新增和更新用户的handler
	 * */
	EventHandler<MouseEvent> addUserEventHandler = new EventHandler<MouseEvent>() {
		@Override
		public void handle(MouseEvent event){
			if(!isValidInput()){return;}
			UserVO user =  generateUser();
			addUserController.addUser(user);
			((Node)(event.getSource())).getScene().getWindow().hide();
		}
	};
	EventHandler<MouseEvent> updateUserEventHandler = new EventHandler<MouseEvent>() {
		@Override
		public void handle(MouseEvent event){
			if(!isValidInput()){return;}
			UserVO user = generateUser();
			updateUserController.updateUser(user, oldUser);
			((Node)(event.getSource())).getScene().getWindow().hide();
		}
	};
	
	
	
	/**
	 * 界面去取消按钮监听器
	 * */
	@FXML 
	protected void cancelButtonListener(Event e){
		((Node)e.getSource()).getScene().getWindow().hide();
	}

	/**
	 * 输入检查以及UserVO的生成
	 * */
	@FXML Text nameErrorText;
	@FXML Text positionErrorText;
	@FXML Text ageTextErrorText;
	
	private boolean isValidInput(){
		if(!isValidName()){return false;}
		if(!isValidAge()){return false;}
		if(!isValidPosition()){return false;}
		return true;
	}
	
	private boolean isValidName(){
		String name = nameTextField.getText();
		if(name==null||name.trim().equals("")){
			nameErrorText.setText("必填");//TODO
			return false;
		}
		nameErrorText.setText(null);
		return true;
	}
	
	private boolean isValidPosition(){
		String position = positionTextField.getText();
		if(position==null||position.trim().equals("")){
			positionErrorText.setText("必填");//TODO
			return false;
		}
		positionErrorText.setText(null);
		return true;
	}
	
	private boolean isValidAge(){
		String ageString = ageTextField.getText();
		if(ageString==null||ageString.trim().equals("")){
			ageTextErrorText.setText("必填");
			return false;
		}
		try{
			int age = Integer.parseInt(ageString);
			if(age<16){
				ageTextErrorText.setText("雇佣童工");
				return false;
			}
		}catch (NumberFormatException e) {
			ageTextErrorText.setText("数字格式错误");
			return false;
		}
		ageTextErrorText.setText(null);
		return true;
	}
	
	private UserVO generateUser(){
		UserVO user = oldUser;
		if(user==null){user = new UserVO();}
		String name = nameTextField.getText();
		user.setName(name);
		
		String age = ageTextField.getText();
		int userAge = Integer.parseInt(age);
		user.setAge(userAge);
		
		user.setPosition(userPosition);
		
		String description= descriptionTextArea.getText();
		user.setDescription(description);
		return user;
	}
	
	/**
	 * 界面确定按钮
	 * */
	@FXML private Button ensureButton;
	private void initEnsureAddEventHandler(){
		ensureButton.setOnMouseClicked(addUserEventHandler);
	}
	private void initEnsureUpdateEventHandler(){
		ensureButton.setOnMouseClicked(updateUserEventHandler);
	}
	
	
	/**
	 * 界面controller初始化方法
	 * 新增模式
	 * 修改模式
	 * */
	protected void init(AdminForAddUserController controller){
		this.addUserController = controller;
		initEnsureAddEventHandler();
	}
	
	protected void init(AdminForUpdateUserController controller,UserVO oldUser){
		this.updateUserController = controller;
		this.oldUser = oldUser;
		this.userPosition  = oldUser.getPosition();
		initUserText();
		initEnsureUpdateEventHandler();
	}
	
	
}
