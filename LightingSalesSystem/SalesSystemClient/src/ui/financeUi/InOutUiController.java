package ui.financeUi;

import java.net.URL;
import java.rmi.RemoteException;
import java.util.ArrayList;
import blService.financeBlService.InOutBlService;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.JavaFXBuilderFactory;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ListView;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import rmi.remoteHelper.FinanceRemoteHelper;
import rmi.remoteHelper.RemoteHelperFactory;
import ui.mainUi.financialStaffMainUi.FinancialStaffMainUiStarter;
import ui.uiAssistants.StageFactory;
import ui.uiAssistants.StageSize;
import vo.InOutDetailsVO;
import vo.InitialInfoVO;
import vo.SingleBankAccountInitialInfoVO;
import vo.SingleCommodityInitialInfoVO;
import vo.SingleCustomerInitialInfoVO;
import vo.SingleEntryVO;

/**
 * 账单管理界面controller
 * @author guxinyu
 * @version 2017.12.4
 *
 */
public class InOutUiController {
	private Stage stage=StageFactory.getFinanceStage();
	
	//账单列表部分
	@FXML
	private ListView<String> inOutList;
	
	
	//账单明细部分
	@FXML
	private TableView<SingleEntryVO> detailsTable;
	
	@FXML
	private TableColumn<SingleEntryVO, String> dateColumn;
	
	@FXML
	private TableColumn<SingleEntryVO, String> eventColumn;
	
	@FXML
	private TableColumn<SingleEntryVO, String> balanceColumn;
	
	//期初信息部分
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
	
	private FinanceRemoteHelper remoteHelper=RemoteHelperFactory.getFinanceRemoteHelper();
	
//	private InOutBlService inOutBl=new InOutBlService_Stub();
	
	private InOutBlService inOutBl=remoteHelper.getInOutBlService();
	
	private ObservableList<String> inOutListItems=FXCollections.observableArrayList();
	
	private ObservableList<SingleEntryVO> inOutDetailsItems=FXCollections.observableArrayList();
	
	private ObservableList<SingleCommodityInitialInfoVO> commodityInitialItems=FXCollections.observableArrayList();
	
	private ObservableList<SingleCustomerInitialInfoVO> customerInitialItems=FXCollections.observableArrayList();
	
	private ObservableList<SingleBankAccountInitialInfoVO> bankAccountInitialItems=FXCollections.observableArrayList();
	
	public void init(){
		//显示账单列表
		String[] inOuts=null;
		try {
			inOuts=inOutBl.viewInOutList();
		} catch (RemoteException e) {
			e.printStackTrace();
		}
		
		for(int i=0;i<inOuts.length;i++){
			inOutListItems.add(inOuts[i]);
		}
		
		inOutList.setItems(inOutListItems);
		
		//初始化期初信息面板
		
		
		//绑定账单明细Table属性
		dateColumn.setCellValueFactory(
				new PropertyValueFactory<SingleEntryVO, String>("date"));
		eventColumn.setCellValueFactory(
				new PropertyValueFactory<SingleEntryVO, String>("event"));
		balanceColumn.setCellValueFactory(
				new PropertyValueFactory<SingleEntryVO, String>("amount"));
		
		detailsTable.setItems(inOutDetailsItems);
		
		//绑定期初信息三个Table的属性
		commodityClassColumn.setCellValueFactory(
				new PropertyValueFactory<SingleCommodityInitialInfoVO, String>("commodityClass"));
		commodityNameColumn.setCellValueFactory(
				new PropertyValueFactory<SingleCommodityInitialInfoVO, String>("commodityName"));
		commodityTypeColumn.setCellValueFactory(
				new PropertyValueFactory<SingleCommodityInitialInfoVO, String>("commodityType"));
		purchasePriceColumn.setCellValueFactory(
				new PropertyValueFactory<SingleCommodityInitialInfoVO, String>("purchasePrice"));
		sellingPriceColumn.setCellValueFactory(
				new PropertyValueFactory<SingleCommodityInitialInfoVO, String>("sellingPrice"));
		recentPurchsePriceColumn.setCellValueFactory(
				new PropertyValueFactory<SingleCommodityInitialInfoVO, String>("recentPurchasePrice"));
		recentSellingPriceColumn.setCellValueFactory(
				new PropertyValueFactory<SingleCommodityInitialInfoVO, String>("recentSellingPrice"));
		customerClassColumn.setCellValueFactory(
				new PropertyValueFactory<SingleCustomerInitialInfoVO, String>("customerClass"));
		customerNameColumn.setCellValueFactory(
				new PropertyValueFactory<SingleCustomerInitialInfoVO, String>("customerName"));
		customerContactColumn.setCellValueFactory(
				new PropertyValueFactory<SingleCustomerInitialInfoVO, String>("customerContact"));
		customerReceiveColumn.setCellValueFactory(
				new PropertyValueFactory<SingleCustomerInitialInfoVO, String>("customerReceive"));
		customerPaymentColumn.setCellValueFactory(
				new PropertyValueFactory<SingleCustomerInitialInfoVO, String>("customerPay"));
		bankAccountNameColumn.setCellValueFactory(
				new PropertyValueFactory<SingleBankAccountInitialInfoVO, String>("bankAccountName"));
		bankAccountSumColumn.setCellValueFactory(
				new PropertyValueFactory<SingleBankAccountInitialInfoVO, String>("bankAccountSum"));
		
		commodityInfoTable.setItems(commodityInitialItems);
		
		customerInfoTable.setItems(customerInitialItems);
		
		bankAccountInfoTable.setItems(bankAccountInitialItems);
		
		
		//为每一个账单设置监听
		inOutList.getSelectionModel().selectedItemProperty().addListener(
			(ObservableValue<? extends String> ov, String old_val,
			String new_val) -> {
				
				inOutDetailsItems.clear();
				commodityInitialItems.clear();
				customerInitialItems.clear();
				bankAccountInitialItems.clear();
				
				try {
					
					//显示账单明细
					InOutDetailsVO details=inOutBl.viewInOutDetails(inOutList.getSelectionModel().getSelectedItem());
					
					ArrayList<SingleEntryVO> entries=details.getEntries();
					
					inOutDetailsItems.addAll(entries);
					
					//显示期初信息
					InitialInfoVO initial=inOutBl.viewInitialInformation(inOutList.getSelectionModel().getSelectedItem());
					
					ArrayList<SingleCommodityInitialInfoVO> comSingles=initial.getCommodityInfos();
					ArrayList<SingleCustomerInitialInfoVO> cusSingles=initial.getCustomerInfos();
					ArrayList<SingleBankAccountInitialInfoVO> bankSingles=initial.getBankAccountInfos();
					
					for(int i=0;i<comSingles.size();i++)
						commodityInitialItems.add(comSingles.get(i));
						
					for(int i=0;i<cusSingles.size();i++)
						customerInitialItems.add(cusSingles.get(i));
						
					for(int i=0;i<bankSingles.size();i++)
						bankAccountInitialItems.add(bankSingles.get(i));
					
				} catch (RemoteException e) {
					e.printStackTrace();
				}
		
			});
	}
	
	@FXML
	protected void newInOutButtonListener()throws Exception{
		URL location = getClass().getResource("NewInOutUi.fxml");
		FXMLLoader loader=new FXMLLoader();
		loader.setLocation(location);
		loader.setBuilderFactory(new JavaFXBuilderFactory());
		Parent root=loader.load();
		
		Scene scene=new Scene(root, StageSize.STAGE_WIDTH, StageSize.STAGE_HEIGHT);
		stage.setScene(scene);
	
		NewInOutUiController controller=loader.getController();
		controller.setStage(stage);
		controller.init();
		
		stage.show();
	}
	
	@FXML
	protected void backButtonListener(){
		FinancialStaffMainUiStarter starter=new FinancialStaffMainUiStarter();
		starter.mainUi();
	}
}
