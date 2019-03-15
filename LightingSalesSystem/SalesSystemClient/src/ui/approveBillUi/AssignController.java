package ui.approveBillUi;

import java.rmi.RemoteException;
import java.util.ArrayList;

import assistant.type.UserPositionEnum;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.input.MouseButton;
import javafx.scene.text.Text;
import ui.adminUi.UserList;
import vo.BillVO;
import vo.UserInfoVO;
import vo.UserVO;

public class AssignController {
	private BillVO bill;
	private UserList userList;//所有用户的集合
	private ObservableList<UserVO> showUserList = FXCollections.observableArrayList();//展示的用户的list
	private UserVO currentShowUser ;//当下正在展示的用户
	
	protected void init(BillVO bill){
		this.bill = bill;
		initTextEditable();
		initUserTableView();
		initShowUserList();
		
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
	
	@FXML
	protected void setExecutor(Event e){
		String name = currentShowUser.getName();
		String id = currentShowUser.getId();
		UserPositionEnum position = currentShowUser.getPosition();
		UserInfoVO userInfo = new UserInfoVO(id,name,position);
		bill.setExecutor(userInfo);
		((Node)(e.getSource())).getScene().getWindow().hide();
	}
}
