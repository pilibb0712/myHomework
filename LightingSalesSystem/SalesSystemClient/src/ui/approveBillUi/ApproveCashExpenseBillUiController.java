package ui.approveBillUi;

import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.Optional;

import assistant.type.BillStateEnum;
import assistant.utility.Date;
import blService.billService.approveBillBlService.ApproveCashExpenseBillBlService;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.geometry.Pos;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.CheckBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import rmi.remoteHelper.RemoteHelperFactory;
import ui.billUi.viewBillUi.ViewFinancialStaffBillUiStarter;
import vo.BillVO;
import vo.CashExpenseBillVO;
import vo.UserInfoVO;

public class ApproveCashExpenseBillUiController {

	
	private static final String DEFAULT_APPROVER_COMENT = "总经理不想理你，并甩给你一张单据";
	private ObservableList<CashExpenseBillVO> bills = FXCollections.observableArrayList();
	private UserInfoVO user;
	private ApproveCashExpenseBillBlService service 
		= RemoteHelperFactory.getApproveBillRemoteHelper().getApproveCashExpenseBillBlService(); 
	
	/**
	 * 界面bottom部分
	 * 通过审批按钮
	 * 拒绝审批按钮
	 * */
	@FXML
	private Button passButton;
	@FXML
	private Button denyButton;
	
	/**
	 * 界面center部分
	 * 单据信息tableview
	 * */
	@FXML
	private TableView<CashExpenseBillVO> billTableView;
	@FXML
	private TableColumn<CashExpenseBillVO, String> billIdColumn;
	@FXML
	private TableColumn<CashExpenseBillVO, String> billCreateDateColumn;
	@FXML
	private TableColumn<CashExpenseBillVO, String> billCreaterColumn;
	@FXML
	private TableColumn<CashExpenseBillVO, Button> viewBillColumn;	
	@FXML
	private TableColumn<CashExpenseBillVO, CheckBox> chooseBillColumn;
	@FXML
	private TableColumn<CashExpenseBillVO,Button> remarkTableColumn;
	@FXML
	private TableColumn<CashExpenseBillVO, Button> assignTableColumn;
	
	private void initTableView(){
		setBillIdColumn();
		setBillCreateDateColumn();
		setBillCreaterColumn();
		setViewBillColumn();
		setChooseBillColumn();
		setRemarkTableColumn();
		setAssignTableColumn();
		billTableView.setItems(bills);
	}

	private void setBillIdColumn(){
		billIdColumn.setCellValueFactory(cellDataFeatures->{
			BillVO bill = cellDataFeatures.getValue();
			ObservableValue<String> billId = new SimpleStringProperty(bill.getId());
			return billId;
		});
	}
	
	private void setBillCreateDateColumn(){
		billCreateDateColumn.setCellValueFactory(cellDataFeatures->{
			BillVO bill = cellDataFeatures.getValue();
			Date date = bill.getCreateDate();
			ObservableValue<String> dateString = new SimpleStringProperty(date.getYMDDate());
			return dateString;
		});
	}
	
	private void setBillCreaterColumn(){
		billCreaterColumn.setCellValueFactory(cellDataFeatures->{
			BillVO bill = cellDataFeatures.getValue();
			UserInfoVO user = bill.getCreater();
			ObservableValue<String> userName = new SimpleStringProperty(user.getName());
			return userName;
		});
	}
	
	private void setChooseBillColumn(){
		chooseBillColumn.setCellValueFactory(cellDataFeatures->{
			CheckBox checkBox = new CheckBox();
			checkBox.setAlignment(Pos.CENTER);
			checkBox.selectedProperty().addListener((x,oldValue,newValue)->{
				BillVO bill = cellDataFeatures.getValue();
				bill.setSelected(newValue);
			});
			ObservableValue<CheckBox> box = new SimpleObjectProperty<CheckBox>(checkBox);
			return box;
		});
	}
	
	private void setViewBillColumn(){
		viewBillColumn.setCellValueFactory(cellDataFeatures->{
			Button button = new Button();
			button.setText("查看");
			button.setAlignment(Pos.CENTER);
			button.setOnMouseClicked(e->{
				CashExpenseBillVO bill = (CashExpenseBillVO)cellDataFeatures.getValue();
				ViewFinancialStaffBillUiStarter starter = new ViewFinancialStaffBillUiStarter();
				String billId= bill.getId();
				starter.viewCashExpenseBill(billId);
				//TODO 库存赠送单view starter
			});
			ObservableValue<Button> btn = new SimpleObjectProperty<Button>(button);
			return btn;
 		});
	}
	
	private void setRemarkTableColumn(){
		remarkTableColumn.setCellValueFactory(cellDataFeature->{
			Button button = new Button("批注");
			button.setOnMouseClicked(e->{
				CashExpenseBillVO bill = (CashExpenseBillVO)cellDataFeature.getValue();
				CommentDialog dialog = new CommentDialog(bill.getApproverComment());
				String comment = dialog.showAndWait();
				bill.setApproverComment(comment);
			});
			ObservableValue<Button> btn = new SimpleObjectProperty<Button>(button);
			return btn;
		});
	}
	
	private void setAssignTableColumn(){
		assignTableColumn.setCellValueFactory(cellDataFeature->{
			Button button = new Button("分配");
			button.setOnMouseClicked(e->{
				BillVO bill = cellDataFeature.getValue();
				AssignUiStarter starter = new AssignUiStarter();
				starter.startAndWait(bill);
			});
			ObservableValue<Button> btn = new SimpleObjectProperty<Button>(button);
			return btn;
		});
	}
	
	/**
	 * 初始化controller
	 * */
	protected void init(UserInfoVO user){
		this.user = user;
		initBillList();
		initTableView();
		initPassButton();
		initDenyButton();
	}
	
	private void initBillList(){
		ArrayList<CashExpenseBillVO> cashExpenseBills = new ArrayList<>();
		try {
			cashExpenseBills = service.getBillsList();
		}catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		bills.addAll(cashExpenseBills);
	}
	
	private void initPassButton(){
		passButton.setOnMouseClicked(e->{
			Alert alert = new Alert(AlertType.CONFIRMATION,"是否决定通过选中单据的审批");
			//alert.getDialogPane().getScene().getStylesheets().add(" TODO ");
			Optional<ButtonType> result = alert.showAndWait();
			if(result.isPresent()&&result.get()==ButtonType.OK){
				try{
					for(int i=0;i<bills.size();i++){
						CashExpenseBillVO bill = bills.get(i);
						if(bill.isSelected()){
							bill.setBillStateEnum(BillStateEnum.TODO);
							setApproveInfo(bill);
							service.passBill(bill);
							bills.remove(bill);
							i--;
						}
					}
				}catch (RemoteException e1) {
					// TODO: handle exception
				}
				billTableView.refresh();
			}
		});
	}
	
	private void initDenyButton(){
		denyButton.setOnMouseClicked(e->{
			Alert alert = new Alert(AlertType.CONFIRMATION,"是否决定不通过选中单据的审批");
			//alert.getDialogPane().getScene().getStylesheets().add(" TODO ");
			Optional<ButtonType> result = alert.showAndWait();
			if(result.isPresent()&&result.get()==ButtonType.OK){
				try{
					for(int i=0;i<bills.size();i++){
						CashExpenseBillVO bill = bills.get(i);
						if(bill.isSelected()){
							bill.setBillStateEnum(BillStateEnum.DENIED);
							setApproveInfo(bill);
							service.denyBill(bill);
							bills.remove(bill);
							i--;
						}
					}
				}catch (RemoteException e1) {
					// TODO: handle exception
				}
				billTableView.refresh();
			}
		});
	}
	
	/**
	 * 单据提交前的准备
	 * 设置单据执行者信息，包括审批日期，审批人，审批人备注
	 * @param bill要设置的bill
	 * */
	private void setApproveInfo(BillVO bill){
		bill.setApproveDate(new Date());
		bill.setApprover(user);
		if(bill.getApproverComment()==null){bill.setApproverComment(DEFAULT_APPROVER_COMENT);}
		
		//如果审批未通过，不设置执行者
		if(bill.getBillStateEnum()==BillStateEnum.DENIED){return;}
		
		//如果已分配执行者，则直接返回
		if(bill.getExecutor()!=null){
			return;
		}
		//没有分配执行者
		//如果有创建者，默认创建者执行
		if((bill.getCreater())!=null){
			bill.setExecutor(bill.getCreater());
		}
		if(bill.getCreater()==null){
			//bill.setExecutor(); TODO 随机人员roll点
		}
	}
}
