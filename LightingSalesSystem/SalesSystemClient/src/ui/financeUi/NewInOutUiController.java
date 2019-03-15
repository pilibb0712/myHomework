package ui.financeUi;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import blService.financeBlService.InOutBlService;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.JavaFXBuilderFactory;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.TableColumn.CellEditEvent;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.stage.Stage;
import rmi.remoteHelper.FinanceRemoteHelper;
import rmi.remoteHelper.RemoteHelperFactory;
import ui.uiAssistants.StageFactory;
import ui.uiAssistants.StageSize;
import vo.BankAccountVO;
import vo.InitialInfoVO;
import vo.SingleBankAccountInitialInfoVO;
import vo.SingleCommodityInitialInfoVO;
import vo.SingleCustomerInitialInfoVO;

/**
 * ÐÂ½¨ÕËµ¥Controller
 * @author guxinyu
 * @version 2017.12.4
 *
 */
public class NewInOutUiController {
	private Stage stage=StageFactory.getFinanceStage();
	
	@FXML
	private TableView<SingleCommodityInitialInfoVO> commodityInfoTable;
	
	@FXML
	private TableView<SingleCustomerInitialInfoVO> customerInfoTable;
	
	@FXML
	private TableView<SingleBankAccountInitialInfoVO> bankAccountInfoTable;
	
	@FXML
	private TableColumn<SingleCommodityInitialInfoVO, String> commodityClassColumn;
	
	@FXML
	private TableColumn<SingleCommodityInitialInfoVO, String> commodityNameColumn;
	
	@FXML
	private TableColumn<SingleCommodityInitialInfoVO, String> commodityTypeColumn;
	
	@FXML
	private TableColumn<SingleCommodityInitialInfoVO, String> purchasePriceColumn; 
	
	@FXML
	private TableColumn<SingleCommodityInitialInfoVO, String> sellingPriceColumn;
	
	@FXML
	private TableColumn<SingleCommodityInitialInfoVO, String> recentPurchsePriceColumn;
	
	@FXML
	private TableColumn<SingleCommodityInitialInfoVO, String> recentSellingPriceColumn;
	
	@FXML
	private TableColumn<SingleCustomerInitialInfoVO, String> customerClassColumn;
	
	@FXML
	private TableColumn<SingleCustomerInitialInfoVO, String> customerNameColumn;
	
	@FXML
	private TableColumn<SingleCustomerInitialInfoVO, String> customerContactColumn;
	
	@FXML
	private TableColumn<SingleCustomerInitialInfoVO, String> customerReceiveColumn;
	
	@FXML
	private TableColumn<SingleCustomerInitialInfoVO, String> customerPaymentColumn;
	
	@FXML
	private TableColumn<SingleBankAccountInitialInfoVO, String> bankAccountNameColumn;
	
	@FXML
	private TableColumn<SingleBankAccountInitialInfoVO, String> bankAccountSumColumn;
	
	@FXML
	private TextField yearTextField;

	private FinanceRemoteHelper remoteHelper=RemoteHelperFactory.getFinanceRemoteHelper();
	
//	private InOutBlService inOutBl=new InOutBlService_Stub();
	
	private InOutBlService inOutBl=remoteHelper.getInOutBlService();
	
	private ObservableList<SingleCommodityInitialInfoVO> commodityInitialItems=FXCollections.observableArrayList();
	
	private ObservableList<SingleCustomerInitialInfoVO> customerInitialItems=FXCollections.observableArrayList();
	
	private ObservableList<SingleBankAccountInitialInfoVO> bankAccountInitialItems=FXCollections.observableArrayList();
	
	private static final int MAX_ENTRY=25;
	
	public void setStage(Stage s){
		stage=s;
	}
	
	public void init(){
		commodityClassColumn.setCellValueFactory(
				new PropertyValueFactory<SingleCommodityInitialInfoVO, String>("commodityClass"));
		commodityClassColumn.setCellFactory(TextFieldTableCell.forTableColumn());
		
		commodityNameColumn.setCellValueFactory(
				new PropertyValueFactory<SingleCommodityInitialInfoVO, String>("commodityName"));
		commodityNameColumn.setCellFactory(TextFieldTableCell.forTableColumn());
		
		commodityTypeColumn.setCellValueFactory(
				new PropertyValueFactory<SingleCommodityInitialInfoVO, String>("commodityType"));
		commodityTypeColumn.setCellFactory(TextFieldTableCell.forTableColumn());
		
		purchasePriceColumn.setCellValueFactory(
				new PropertyValueFactory<SingleCommodityInitialInfoVO, String>("purchasePrice"));
		purchasePriceColumn.setCellFactory(TextFieldTableCell.forTableColumn());
		
		sellingPriceColumn.setCellValueFactory(
				new PropertyValueFactory<SingleCommodityInitialInfoVO, String>("sellingPrice"));
		sellingPriceColumn.setCellFactory(TextFieldTableCell.forTableColumn());
		
		recentPurchsePriceColumn.setCellValueFactory(
				new PropertyValueFactory<SingleCommodityInitialInfoVO, String>("recentPurchasePrice"));
		recentPurchsePriceColumn.setCellFactory(TextFieldTableCell.forTableColumn());
		
		recentSellingPriceColumn.setCellValueFactory(
				new PropertyValueFactory<SingleCommodityInitialInfoVO, String>("recentSellingPrice"));
		recentSellingPriceColumn.setCellFactory(TextFieldTableCell.forTableColumn());
		
		customerClassColumn.setCellValueFactory(
				new PropertyValueFactory<SingleCustomerInitialInfoVO, String>("customerClass"));
		customerClassColumn.setCellFactory(TextFieldTableCell.forTableColumn());
		
		customerNameColumn.setCellValueFactory(
				new PropertyValueFactory<SingleCustomerInitialInfoVO, String>("customerName"));
		customerNameColumn.setCellFactory(TextFieldTableCell.forTableColumn());
		
		customerContactColumn.setCellValueFactory(
				new PropertyValueFactory<SingleCustomerInitialInfoVO, String>("customerContact"));
		customerContactColumn.setCellFactory(TextFieldTableCell.forTableColumn());
		
		customerReceiveColumn.setCellValueFactory(
				new PropertyValueFactory<SingleCustomerInitialInfoVO, String>("customerReceive"));
		customerReceiveColumn.setCellFactory(TextFieldTableCell.forTableColumn());
		
		customerPaymentColumn.setCellValueFactory(
				new PropertyValueFactory<SingleCustomerInitialInfoVO, String>("customerPay"));
		customerPaymentColumn.setCellFactory(TextFieldTableCell.forTableColumn());
		
		bankAccountNameColumn.setCellValueFactory(
				new PropertyValueFactory<SingleBankAccountInitialInfoVO, String>("bankAccountName"));
		bankAccountNameColumn.setCellFactory(TextFieldTableCell.forTableColumn());
		
		bankAccountSumColumn.setCellValueFactory(
				new PropertyValueFactory<SingleBankAccountInitialInfoVO, String>("bankAccountSum"));
		bankAccountSumColumn.setCellFactory(TextFieldTableCell.forTableColumn());
		
		commodityInfoTable.setItems(commodityInitialItems);
		
		customerInfoTable.setItems(customerInitialItems);
		
		bankAccountInfoTable.setItems(bankAccountInitialItems);
		
		for(int i=0;i<MAX_ENTRY;i++){
			SingleCommodityInitialInfoVO each=new SingleCommodityInitialInfoVO();
			commodityInitialItems.add(each);
		}
		
		for(int i=0;i<MAX_ENTRY;i++){
			SingleCustomerInitialInfoVO each=new SingleCustomerInitialInfoVO();
			customerInitialItems.add(each);
		}
		
		for(int i=0;i<MAX_ENTRY;i++){
			SingleBankAccountInitialInfoVO each=new SingleBankAccountInitialInfoVO();
			bankAccountInitialItems.add(each);
		}
	
	}
	
	@FXML
	protected void commodityClassColumnOnEditCommit(CellEditEvent<BankAccountVO, String> event)throws Exception{
		String oldValue=event.getOldValue();
		String newValue=event.getNewValue();
		
		if(!oldValue.equals(newValue)){
			int index=event.getTablePosition().getRow();
			SingleCommodityInitialInfoVO changed=commodityInitialItems.get(index);
			changed.setCommodityClass(newValue);
		}
	}
	
	@FXML
	protected void commodityNameColumnOnEditCommit(CellEditEvent<BankAccountVO, String> event)throws Exception{
		String oldValue=event.getOldValue();
		String newValue=event.getNewValue();
		
		if(!oldValue.equals(newValue)){
			int index=event.getTablePosition().getRow();
			SingleCommodityInitialInfoVO changed=commodityInitialItems.get(index);
			changed.setCommodityName(newValue);
		}
	}
	
	@FXML
	protected void commodityTypeColumnOnEditCommit(CellEditEvent<BankAccountVO, String> event)throws Exception{
		String oldValue=event.getOldValue();
		String newValue=event.getNewValue();
		
		if(!oldValue.equals(newValue)){
			int index=event.getTablePosition().getRow();
			SingleCommodityInitialInfoVO changed=commodityInitialItems.get(index);
			changed.setCommodityType(newValue);
		}
	}
	
	@FXML
	protected void purchasePriceColumnOnEditCommit(CellEditEvent<BankAccountVO, String> event)throws Exception{
		String oldValue=event.getOldValue();
		String newValue=event.getNewValue();
		
		if(!oldValue.equals(newValue)){
			int index=event.getTablePosition().getRow();
			SingleCommodityInitialInfoVO changed=commodityInitialItems.get(index);
			changed.setPurchasePrice(newValue);
		}
	}
	
	@FXML
	protected void sellingPriceColumnOnEditCommit(CellEditEvent<BankAccountVO, String> event)throws Exception{
		String oldValue=event.getOldValue();
		String newValue=event.getNewValue();
		
		if(!oldValue.equals(newValue)){
			int index=event.getTablePosition().getRow();
			SingleCommodityInitialInfoVO changed=commodityInitialItems.get(index);
			changed.setSellingPrice(newValue);
		}
	}
	
	@FXML
	protected void recentPurchasePriceColumnOnEditCommit(CellEditEvent<BankAccountVO, String> event)throws Exception{
		String oldValue=event.getOldValue();
		String newValue=event.getNewValue();
		
		if(!oldValue.equals(newValue)){
			int index=event.getTablePosition().getRow();
			SingleCommodityInitialInfoVO changed=commodityInitialItems.get(index);
			changed.setRecentPurchasePrice(newValue);
		}
	}
	
	@FXML
	protected void recentSellingPriceColumnOnEditCommit(CellEditEvent<BankAccountVO, String> event)throws Exception{
		String oldValue=event.getOldValue();
		String newValue=event.getNewValue();
		
		if(!oldValue.equals(newValue)){
			int index=event.getTablePosition().getRow();
			SingleCommodityInitialInfoVO changed=commodityInitialItems.get(index);
			changed.setRecentSellingPrice(newValue);
		}
	}
	
	@FXML
	protected void customerClassColumnOnEditCommit(CellEditEvent<BankAccountVO, String> event)throws Exception{
		String oldValue=event.getOldValue();
		String newValue=event.getNewValue();
		
		if(!oldValue.equals(newValue)){
			int index=event.getTablePosition().getRow();
			SingleCustomerInitialInfoVO changed=customerInitialItems.get(index);
			changed.setCustomerClass(newValue);
		}
	}
	
	@FXML
	protected void customerNameColumnOnEditCommit(CellEditEvent<BankAccountVO, String> event)throws Exception{
		String oldValue=event.getOldValue();
		String newValue=event.getNewValue();
		
		if(!oldValue.equals(newValue)){
			int index=event.getTablePosition().getRow();
			SingleCustomerInitialInfoVO changed=customerInitialItems.get(index);
			changed.setCustomerName(newValue);
		}
	}
	
	@FXML
	protected void customerContactColumnOnEditCommit(CellEditEvent<BankAccountVO, String> event)throws Exception{
		String oldValue=event.getOldValue();
		String newValue=event.getNewValue();
		
		if(!oldValue.equals(newValue)){
			int index=event.getTablePosition().getRow();
			SingleCustomerInitialInfoVO changed=customerInitialItems.get(index);
			changed.setCustomerContact(newValue);
		}
	}
	
	@FXML
	protected void customerReceiveColumnOnEditCommit(CellEditEvent<BankAccountVO, String> event)throws Exception{
		String oldValue=event.getOldValue();
		String newValue=event.getNewValue();
		
		if(!oldValue.equals(newValue)){
			int index=event.getTablePosition().getRow();
			SingleCustomerInitialInfoVO changed=customerInitialItems.get(index);
			changed.setCustomerReceive(newValue);
		}
	}
	
	@FXML
	protected void customerPaymentColumnOnEditCommit(CellEditEvent<BankAccountVO, String> event)throws Exception{
		String oldValue=event.getOldValue();
		String newValue=event.getNewValue();
		
		if(!oldValue.equals(newValue)){
			int index=event.getTablePosition().getRow();
			SingleCustomerInitialInfoVO changed=customerInitialItems.get(index);
			changed.setCustomerPay(newValue);
		}
	}
	
	@FXML
	protected void bankAccountNameColumnOnEditCommit(CellEditEvent<BankAccountVO, String> event)throws Exception{
		String oldValue=event.getOldValue();
		String newValue=event.getNewValue();
		
		if(!oldValue.equals(newValue)){
			int index=event.getTablePosition().getRow();
			SingleBankAccountInitialInfoVO changed=bankAccountInitialItems.get(index);
			changed.setBankAccountName(newValue);
		}
	}
	
	@FXML
	protected void bankAccountSumColumnOnEditCommit(CellEditEvent<BankAccountVO, String> event)throws Exception{
		String oldValue=event.getOldValue();
		String newValue=event.getNewValue();
		
		if(!oldValue.equals(newValue)){
			int index=event.getTablePosition().getRow();
			SingleBankAccountInitialInfoVO changed=bankAccountInitialItems.get(index);
			changed.setBankAccountSum(newValue);
		}
	}
	
	@FXML
	protected void okButtonListener() throws Exception{
		ArrayList<SingleCommodityInitialInfoVO> com=new ArrayList<SingleCommodityInitialInfoVO>();
		com.addAll(commodityInitialItems);
		ArrayList<SingleCustomerInitialInfoVO> cus=new ArrayList<SingleCustomerInitialInfoVO>();
		cus.addAll(customerInitialItems);
		ArrayList<SingleBankAccountInitialInfoVO> bank=new ArrayList<SingleBankAccountInitialInfoVO>();
		bank.addAll(bankAccountInitialItems);
		
		InitialInfoVO toSave=new InitialInfoVO(com, cus, bank);
		toSave.setYear(yearTextField.getText());
		
		if(inOutBl.newInOut(toSave)){
			returnToPrevious();
		}else{
			
		}
	}
	
	@FXML
	protected void returnButtonListener() throws Exception{
		returnToPrevious();
	}
	
	private void returnToPrevious() throws IOException{
		URL location = getClass().getResource("InOutUi.fxml");
		FXMLLoader loader=new FXMLLoader();
		loader.setLocation(location);
		loader.setBuilderFactory(new JavaFXBuilderFactory());
		Parent root=loader.load();
		
		Scene scene=new Scene(root, StageSize.STAGE_WIDTH, StageSize.STAGE_HEIGHT);
		stage.setScene(scene);
	
		InOutUiController controller=loader.getController();
		controller.init();
		
		stage.show();
	}
}
