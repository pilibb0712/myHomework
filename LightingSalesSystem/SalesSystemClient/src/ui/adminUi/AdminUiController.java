package ui.adminUi;

import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.Optional;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.input.MouseButton;
import javafx.scene.text.Text;
import vo.UserVO;

public class AdminUiController implements AdminForAddUserController,AdminForUpdateUserController{
	private UserList userList;//所有用户的集合
	private ObservableList<UserVO> showUserList = FXCollections.observableArrayList();//展示的用户的list
	private UserVO currentShowUser ;//当下正在展示的用户
	
	
	/**
	 * top部分界面
	 * 关键字查询部分
	 * */
	@FXML	private TextField keyWordTextField;
	@FXML
	protected void searchByKeyWordButtonListener(){
		String key = keyWordTextField.getText();
		showUserList.clear();
		ArrayList<UserVO> newShowUsers = userList.searchByKeyWords(key);
		showUserList.addAll(newShowUsers);
		userTableView.refresh();
	}
	
	/**
	 * left部分界面
	 * 显示用户清单的tableView
	 * */
	@FXML 	TableView<UserVO> userTableView;
	@FXML	TableColumn<UserVO, String> userTableColumn;
	
	private void initUserTableView(){
		initUserTableColumn();
		userTableView.setItems(showUserList);
	}
	
	private void initUserTableColumn(){
		userTableColumn.setCellFactory(col->{
			TextFieldTableCell<UserVO, String> tableCell = new TextFieldTableCell<>();
			tableCell.setOnMouseClicked(e->{
				int i=0;
				if(e.getClickCount()==2
						&&e.getButton().equals(MouseButton.PRIMARY)
						&&(i=tableCell.getIndex())<showUserList.size()){
					currentShowUser = showUserList.get(i);
					showUserInformation(currentShowUser);
					deleteButton.setDisable(false);
					updateButton.setDisable(false);
					//showUser TODO
				}
			});
			return tableCell;
		});
		userTableColumn.setCellValueFactory(cellDataFeature->{
			UserVO user = cellDataFeature.getValue();
			String userName = user.getName();
			ObservableValue<String> name = new SimpleStringProperty(userName);
			return name;
		});
	}

	/**
	 * 界面center部分
	 * 用户具体信息的显示
	 * textField textArea 
	 * 更新用户信息和删除用户信息按钮
	 * */
	@FXML 	Text nameText;
	@FXML   Text idText;
	@FXML	Text ageText;
	@FXML	Text positionText;
	@FXML	TextArea descriptionTextArea;
	
	private void showUserInformation(UserVO user){
		if(user==null){
			nameText.setText(null);
			idText.setText(null);
			ageText.setText(null);
			positionText.setText(null);
			descriptionTextArea.setText(null);
			return;
		}
		nameText.setText(user.getName());
		idText.setText(user.getId());
		ageText.setText(user.getAge()+"");
		positionText.setText(user.getPosition().toChineseString());
		descriptionTextArea.setText(user.getDescription());
	}
	
	@FXML Button  updateButton;
	@FXML
	protected void updateUserButtonListener(){
		UpdateUserUiStarter starter = new UpdateUserUiStarter(this, currentShowUser);
		starter.start();
	}
	
	@FXML private Button deleteButton;
	@FXML
	protected void deleteUserButtonListener(){
		Alert alert = new Alert(AlertType.CONFIRMATION,"是否决定删除此用户");
		Optional<ButtonType> result = alert.showAndWait();
		if(result.isPresent()&&result.get()==ButtonType.OK){
			userList.deleteUser(currentShowUser);//在系统中删除该用户
			showUserList.remove(currentShowUser);//在当前展示用户列表中删除该用户
			currentShowUser =null;//重新设定当前展示用户
			showUserInformation(null);//清空当前显示
		}
	}
	
	/**
	 * 界面的bottom部分
	 * 新增用户按钮
	 * 
	 * */
	@FXML
	protected void addUserButtonListener(){
		AddUserUiStarter starter = new AddUserUiStarter(this);
		starter.start();
	}


	/**
	 * 新增和更新用户接口
	 * */
	@Override
	public boolean addUser(UserVO user){
		userList.addUser(user);
		showUserList.add(user);
		return true;
	}
	@Override
	public void updateUser(UserVO user,UserVO oldUser){
		userList.updateUser(user, oldUser);
		refreshShowUserList();
	}
	
	
	private void refreshShowUserList(){
		showUserList.clear();
		ArrayList<UserVO> list =userList.getUserList(); 
		showUserList.addAll(list);
		userTableView.refresh();
		showUserInformation(currentShowUser);
	}

	/**
	 * 界面初始化
	 * */
	public void init(){
		initButtonDisable();
		initTextEditable();
		initUserTableView();
		initShowUserList();
	}
	
	private void initButtonDisable(){
		deleteButton.setDisable(true);
		updateButton.setDisable(true);
	}
	
	private void initTextEditable(){
		descriptionTextArea.setEditable(false);
	}
	
	private void initShowUserList(){
		try {
			userList = new UserList();
			showUserList.addAll(userList.getUserList());
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
