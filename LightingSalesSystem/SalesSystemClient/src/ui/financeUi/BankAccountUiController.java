package ui.financeUi;

import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.Iterator;
import assistant.exception.AccountNameExistException;
import blService.financeBlService.BankAccountBlService;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.geometry.Pos;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableColumn.CellEditEvent;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Text;
import javafx.util.Callback;
import rmi.remoteHelper.FinanceRemoteHelper;
import rmi.remoteHelper.RemoteHelperFactory;
import ui.mainUi.financialStaffMainUi.FinancialStaffMainUiStarter;
import ui.uiAssistants.StageSize;
import vo.BankAccountVO;

/**
 * 银行账户界面controller
 * @author guxinyu
 * @version 2017.12.30
 *
 */
public class BankAccountUiController {
	@FXML
	Button backButton;
	
	@FXML
	private BorderPane rootPane;
	
	@FXML
	private GridPane bankAccountGrid;
	
	@FXML
	private Button newAccountButton;
	
	@FXML
	private TextField accountNameTextField;
	
	@FXML
	private TextField remainingSumTextField;
	
	@FXML
	private TableView<BankAccountVO> bankAccountList;
	
	@FXML
	private TableColumn<BankAccountVO, String> accountNameColumn;
	
	@FXML 
	private TableColumn<BankAccountVO, String> remainingSumColumn;
	
	@FXML
	private TableColumn<BankAccountVO, CheckBox> checkBoxColumn;
	
	@FXML
	private Text errorPrompt;
	
	private FinanceRemoteHelper remoteHelper=RemoteHelperFactory.getFinanceRemoteHelper();
		
//	private BankAccountBlService bankAccountBl=new BankAccountBlService_Stub();
	
	private BankAccountBlService bankAccountBl=remoteHelper.getBankAccountBlService();
	
	private ObservableList<BankAccountVO> items=FXCollections.observableArrayList();

	//初始化银行账户列表
	public void init(){
		rootPane.setPrefSize(StageSize.STAGE_WIDTH, StageSize.STAGE_HEIGHT);
		
		ArrayList<BankAccountVO> accounts;
			try {
				accounts = bankAccountBl.viewBankAccounts();	
						
				for(int i=0;i<accounts.size();i++)
					items.add(accounts.get(i));
				
				checkBoxColumn.setCellValueFactory(
						new Callback<TableColumn.CellDataFeatures<BankAccountVO, CheckBox>, ObservableValue<CheckBox>>(){
							@Override
							public ObservableValue<CheckBox> call(TableColumn.CellDataFeatures<BankAccountVO, CheckBox> arg0){
								BankAccountVO account = arg0.getValue();

				                CheckBox checkBox = new CheckBox();
				                
				                checkBox.setAlignment(Pos.CENTER);

				                checkBox.selectedProperty().setValue(account.isSelected());

				                checkBox.selectedProperty().addListener(new ChangeListener<Boolean>() {
				                    
				                	@Override
				                	public void changed(ObservableValue<? extends Boolean> ov,
				                            Boolean old_val, Boolean new_val) {

				                    	account.setSelected(new_val);

				                    }
				                	
				                });

				                return new SimpleObjectProperty<CheckBox>(checkBox);
							}
						});
				accountNameColumn.setCellValueFactory(
						new PropertyValueFactory<>("accountName"));
				accountNameColumn.setCellFactory(TextFieldTableCell.forTableColumn());
				
				remainingSumColumn.setCellValueFactory(
						new PropertyValueFactory<>("remainingSum"));
				
				bankAccountList.setItems(items);
			} catch (RemoteException e) {
				e.printStackTrace();
			}	
	}
	
	private void doubleNameAlert(String message){
		Alert alert=new Alert(Alert.AlertType.ERROR, message);
		alert.showAndWait();
	}
	
	@FXML //编辑账户
	protected void modifyAccountNameListener(CellEditEvent<BankAccountVO, String> event) throws Exception{
		String oldName=event.getOldValue();
		String newName=event.getNewValue();
		
		if(!oldName.equals(newName)){
			try {
				if(bankAccountBl.updateAccount(oldName, newName)){
					int lineIndex=event.getTablePosition().getRow();
					BankAccountVO beModified=items.get(lineIndex);
					beModified.setAccountName(newName);
				}else{
				
				}
			} catch (AccountNameExistException e) {
				doubleNameAlert(e.getMessage());
			}
		}
	}
	
	@FXML //新建银行账户按钮监听
	protected void newAccountButtonListener(){
		String newName=accountNameTextField.getText();
		String newSum=remainingSumTextField.getText();
		
		if(!newName.equals("")&&!newSum.equals("")){
			try{
				BankAccountVO newAccount=new BankAccountVO(newName, Double.parseDouble(newSum));
			
				bankAccountBl.newBankAccount(newAccount);
		
				items.add(newAccount);
		
				accountNameTextField.clear();
				remainingSumTextField.clear();
				
			}catch(NumberFormatException nf){
				errorPrompt.setText("账户余额必须是数字");
			}catch(AccountNameExistException e){
				doubleNameAlert(e.getMessage());
			} catch (RemoteException e) {
				e.printStackTrace();
			}
		}else{
			errorPrompt.setText("请填写完整账户名和账户余额");
		}
	}
	
	@FXML //删除银行账户按钮监听
	protected void deleteAccountsButtonListener() throws Exception{
		Iterator<BankAccountVO> it=items.iterator();
		
		while(it.hasNext()){
			BankAccountVO account=it.next();
			
			if(account.isSelected()){
				if(bankAccountBl.deleteAccount(account.getAccountName()))
					it.remove();
				else{
					errorPrompt.setText("银行账户删除失败");
				}
			}
		}
	}
	
	@FXML
	protected void backButtonListener(){
		FinancialStaffMainUiStarter starter=new FinancialStaffMainUiStarter();
		starter.mainUi();
	}
}
