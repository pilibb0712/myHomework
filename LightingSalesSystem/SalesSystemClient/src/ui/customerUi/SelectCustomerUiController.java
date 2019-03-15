/**
 * 
 */
package ui.customerUi;


import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.Optional;

import assistant.type.UserPositionEnum;
import blService.customerBlService.CustomerBlService;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableColumn.CellDataFeatures;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import javafx.util.Callback;
import rmi.remoteHelper.CustomerRemoteHelper;
import rmi.remoteHelper.RemoteHelperFactory;
import ui.mainUi.salesmanMainUi.SalesmanMainUiStarter;
import vo.CustomerVO;
import vo.UserInfoVO;

/**
 * @author 王宁一
 *
 */
public class SelectCustomerUiController {
	
	private UserInfoVO user;
	
	private CustomerVO waitToChangedCustomer;
	
	@FXML 
	private TextField queryInfoTextField;
	
	@FXML
	private TableView<CustomerVO> customerList;
	
	@FXML
	private TableColumn<CustomerVO, String> customerNameColumn;
	
	@FXML
	private TableColumn<CustomerVO, String> customerIdColumn;
	
	@FXML
	private TableColumn<CustomerVO, Button> customerSelectColumn;
	
	@FXML
	private Label userIdLabel;
	
	@FXML
	private Label customerIdLabel;
	
	@FXML
	private Label customerTypeLabel;
	
	@FXML
	private Label customerLevelLabel;
	
	@FXML
	private Label customerNameLabel;
	
	@FXML
	private Label customerAddressLabel;
	
	@FXML
	private Label customerEmailLabel;
	
	@FXML
	private Label customerTelLabel;
	
	@FXML
	private Label customerPostLabel;
	
	@FXML
	private Label customerBusinessmanLabel;
	
	@FXML
	private Label customerShouldReceive;
	
	@FXML
	private Label customerShouldPay;
	
	@FXML
	private Label customerShouldReceiveCreditLabel;
	
	@FXML
	private Button addButton;
	
	@FXML
	private Button editButton;
	
	@FXML
	private Button deleteButton;
	
	@FXML
	private Button cancleButton;
	
	private CustomerRemoteHelper remoteHelper=RemoteHelperFactory.getCustomerRemoteHelper();
	
	private CustomerBlService service=remoteHelper.getCustomerBlService();
	
	//private CustomerBlService service=new CustomerBlService_Stub();
	
	private ObservableList<CustomerVO> customers=FXCollections.observableArrayList();
	
	public void init(UserInfoVO user) {
		this.user=user;
		
		String userId=user.getId();
		//Text
		userIdLabel.setText("UserID: "+userId);
		customerList.setItems(customers);
		
		try {
			ArrayList<CustomerVO> customerVOs=service.QueryCustomer("");
			customers.addAll(customerVOs);
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		customerNameColumn.setCellValueFactory(new PropertyValueFactory<CustomerVO, String>("Name"));
		customerIdColumn.setCellValueFactory(new PropertyValueFactory<CustomerVO, String>("Id"));
		customerSelectColumn.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<CustomerVO,Button>, ObservableValue<Button>>() {

			@Override
			public ObservableValue<Button> call(CellDataFeatures<CustomerVO, Button> param) {
				// TODO Auto-generated method stub
				CustomerVO customerVO=param.getValue();
				
				Button select=new Button("选择");
				
				select.setOnAction(paramx->{
					showContent(customerVO);
					waitToChangedCustomer=customerVO;
				});
				
				return new SimpleObjectProperty<Button>(select);
			}
		});			
	}
	
	@FXML
	protected void queryAction()throws Exception{
		String string=queryInfoTextField.getText();
		
		ArrayList<CustomerVO> customerVOs=new ArrayList<CustomerVO>();
		customerVOs=service.QueryCustomer(string);
		
		if(customerVOs==null) {
			customers.clear();
		}else {
			customers.clear();
			customers.addAll(customerVOs);
		}
		
	}
	
	@FXML
	protected void addButtonListener() {
		AddCustomerUiStarter addCustomerUiStarter=new AddCustomerUiStarter(user);
		addCustomerUiStarter.addCustomer();
	}
	
	@FXML
	protected void deleteButtonListener() {
		Alert confirmation=new Alert(AlertType.CONFIRMATION);
		confirmation.setTitle("系统消息");
		confirmation.setHeaderText(null);
		confirmation.setContentText("您确定要删除该客户吗？");
		Optional<ButtonType> result=confirmation.showAndWait();
		if(result.get()==ButtonType.OK) {			
			try {
				if(waitToChangedCustomer.getShouldPay()!=0||waitToChangedCustomer.getShouldReceive()!=0) {
					Alert alert=new Alert(AlertType.ERROR);
					alert.setTitle("错误");
					alert.setHeaderText(null);
					alert.setContentText("该客户存在应收应付信息，不可删除");
					alert.showAndWait();
					confirmation.close();
				}
				if(service.DelCustomer(waitToChangedCustomer.getId(), waitToChangedCustomer.getName())) {
					int position=customers.indexOf(waitToChangedCustomer);
					customers.remove(position);
				}				
			} catch (RemoteException e) {
				e.printStackTrace();
			}			
		}else {
			confirmation.close();
		}				
	}
	
	@FXML
	protected void editButtonListener() {
		if(waitToChangedCustomer==null) {
			
		}else {
			EditCustomerUiStarter editCustomerUiStarter=new EditCustomerUiStarter();
			editCustomerUiStarter.editCustomer(this, waitToChangedCustomer);
		}
	}
	
	@FXML
	protected void cancleButtonListener() {
		SalesmanMainUiStarter starter=new SalesmanMainUiStarter();
		starter.startSalesman(user);
	}
	
	public void showContent(CustomerVO customerVO) {
		if(customerVO.getId()!=null) {
			customerIdLabel.setText(customerVO.getId());
		}
		if(customerVO.getType()!=null) {
			customerTypeLabel.setText(customerVO.getType());
		}
		if(customerVO.getLevel()!=null) {
			customerLevelLabel.setText(customerVO.getLevel().toString());
		}
		if(customerVO.getName()!=null) {
			customerNameLabel.setText(customerVO.getName());
		}
		if(customerVO.getAddress()!=null) {
			customerAddressLabel.setText(customerVO.getAddress());
		}
		if(customerVO.getEmail()!=null) {
			customerEmailLabel.setText(customerVO.getEmail());
		}
		if(customerVO.getTel()!=null) {
			customerTelLabel.setText(customerVO.getTel());
		}
		if(customerVO.getPost()!=null) {
			customerPostLabel.setText(customerVO.getPost());
		}
		if(customerVO.getDefaultSalesman()!=null) {
			customerBusinessmanLabel.setText(customerVO.getDefaultSalesman().getName());
		}
		
		customerShouldReceive.setText(""+customerVO.getShouldReceive());
		customerShouldPay.setText(""+customerVO.getShouldPay());
		customerShouldReceiveCreditLabel.setText(""+customerVO.getShouldReceiveCredit());
		
	}
	
	public void changeCustomer(CustomerVO oriCustomer, CustomerVO toReplace) {		
		try {
			if(service.ModCustomer(toReplace)) {
				int position=customers.indexOf(oriCustomer);		
				customers.set(position, toReplace);
			}else {
				Alert alert=new Alert(AlertType.ERROR);
				alert.setTitle("错误");
				alert.setHeaderText(null);
				alert.setContentText("修改未成功");
				alert.showAndWait();
			}
			
		} catch (RemoteException e) {
			e.printStackTrace();
		}
	}
}






























