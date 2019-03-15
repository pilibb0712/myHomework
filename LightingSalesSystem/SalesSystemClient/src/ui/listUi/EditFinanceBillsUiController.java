package ui.listUi;

import java.rmi.RemoteException;
import java.util.ArrayList;

import assistant.type.UserPositionEnum;
import assistant.utility.Date;
import blService.financeBlService.NewFinanceBillsBlService;
import blStubs.financeBlStubs.NewFinanceBillsBlService_Stub;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.TableColumn.CellEditEvent;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.text.Text;
import vo.BillEntryVO;
import vo.BillVO;
import vo.CashExpenseBillVO;
import vo.PaymentBillVO;
import vo.ReceiptBillVO;

public class EditFinanceBillsUiController {
	@FXML
	private Text billTypePrompt;
	
	@FXML
	private TableView<BillEntryVO> entryTable;
	
	@FXML
	private TableColumn<BillEntryVO, String> entryNameColumn;
	
	@FXML
	private TableColumn<BillEntryVO, String> entryMoneyColumn;
	
	@FXML
	private Text createTimeText;
	
	@FXML
	private Text bankAccountText;
	
	@FXML
	private TextField sumText;
	
	@FXML
	private TextArea commentText;
	
	//右侧
	@FXML
	private Text approverText;
	
	@FXML
	private Text executorText;
	
	@FXML
	private Text approveDateText;
	
	@FXML
	private Text finishDateText;
	
	@FXML
	private TextArea approverCommentArea;
	
	private String billType;
	
	private ObservableList<BillEntryVO> entries=FXCollections.observableArrayList();
	
	private NewFinanceBillsBlService newFinanceBillsBl=new NewFinanceBillsBlService_Stub();
	
	private BillVO currentBill=null;
	
	public void init(BillVO bill, String operation){
		billTypePrompt.setText(bill.getCategoryEnum().toChineseString());
		
		currentBill=bill;
		billType=bill.getCategoryEnum().toChineseString();
		
		//初始化右侧
		if(bill.getApprover()!=null)
			approverText.setText(bill.getApprover().getName()+" "+bill.getApprover().getId());
		if(bill.getExecutor()!=null)
			executorText.setText(bill.getExecutor().getName()+" "+bill.getExecutor().getId());
		if(bill.getApproveDate()!=null)
			approveDateText.setText(bill.getApproveDate().getDate());
		if(bill.getFinishDate()!=null)
			finishDateText.setText(bill.getFinishDate().getDate());
		if(bill.getApproverComment()!=null)
			approverCommentArea.setText(bill.getApproverComment());
		
		//初始化Text
		Date time=new Date();
		createTimeText.setText(time.getDate().toString());
		if(bill.getCreaterComment()!=null)
			commentText.setText(bill.getCreaterComment());
		
		ArrayList<BillEntryVO> ens=null;
		
		switch (billType){
		case "现金费用单":
			CashExpenseBillVO ceBill=(CashExpenseBillVO)bill;
			if(ceBill.getBankAccount()!=null)
				bankAccountText.setText(ceBill.getBankAccount());
			
			if(operation.equals("红冲并复制"))
				sumText.setText(String.valueOf(ceBill.getSum()));
			else if(operation.equals("红冲"))
				sumText.setText(String.valueOf(-ceBill.getSum()));
			
			ens=ceBill.getEntries();
			break;
		case "付款单":
			PaymentBillVO pBill=(PaymentBillVO)bill;
			if(pBill.getBankAccount()!=null)
				bankAccountText.setText(pBill.getBankAccount());
			
			if(operation.equals("红冲并复制"))
				sumText.setText(String.valueOf(pBill.getSum()));
			else if(operation.equals("红冲"))
				sumText.setText(String.valueOf(-pBill.getSum()));
			
			ens=pBill.getEntries();
			break;
		case "收款单":
			ReceiptBillVO rBill=(ReceiptBillVO)bill;
			if(rBill.getBankAccount()!=null)
				bankAccountText.setText(rBill.getBankAccount());
			
			if(operation.equals("红冲并复制"))
				sumText.setText(String.valueOf(rBill.getSum()));
			else if(operation.equals("红冲"))
				sumText.setText(String.valueOf(-rBill.getSum()));
			
			ens=rBill.getEntries();
			break;
		}
		
		entryNameColumn.setCellValueFactory(new PropertyValueFactory<>("entryName"));
		entryMoneyColumn.setCellValueFactory(new PropertyValueFactory<>("entryMoney"));
		
		entryMoneyColumn.setCellFactory(TextFieldTableCell.forTableColumn());
		
		if(operation.equals("红冲并复制"))
			entries.addAll(ens);
		else if(operation.equals("红冲")){
			for(int i=0;i<ens.size();i++){
				BillEntryVO entry=ens.get(i);
				if(!entry.getEntryMoney().equals("")){
					String negMoney="-"+entry.getEntryMoney();
					entry.setEntryMoney(negMoney);
				}
			}
			entries.addAll(ens);
		}
		
		entryTable.setItems(entries);
	}
	
	@FXML
	protected void columnOnEditCommit(CellEditEvent<TextFieldTableCell<String, String>, String> event) throws Exception{
		String oldValue=event.getOldValue();
		String newValue=event.getNewValue();
		
		if(!oldValue.equals(newValue)){
			int rowIndex=event.getTablePosition().getRow();
			BillEntryVO entry=entries.get(rowIndex);
			entry.setEntryMoney(newValue);
		}
	}
	
	@FXML
	protected void submitButtonListener(){
		ArrayList<BillEntryVO> newEntries=new ArrayList<BillEntryVO>();
		newEntries.addAll(entries);
		
		switch (billType){
		case "现金费用单":
			CashExpenseBillVO ceBill=(CashExpenseBillVO)currentBill;
			ceBill.setSum(Double.parseDouble(sumText.getText()));
			ceBill.setEntries(newEntries);
			try {
				if(newFinanceBillsBl.newCashExpenseBill(ceBill)){
					
				}else{
					/**
					 * 错误提示
					 */
				}
			} catch (RemoteException e) {
				e.printStackTrace();
			}
			break;
		case "付款单":
			PaymentBillVO pBill=(PaymentBillVO)currentBill;
			pBill.setSum(Double.parseDouble(sumText.getText()));
			pBill.setEntries(newEntries);
			try {
				if(newFinanceBillsBl.newPaymentBill(pBill)){
					
				}else{
					/**
					 * 错误提示
					 */
				}
			} catch (RemoteException e) {
				e.printStackTrace();
			}
			break;
		case "收款单":
			ReceiptBillVO rBill=(ReceiptBillVO)currentBill;
			rBill.setSum(Double.parseDouble(sumText.getText()));
			rBill.setEntries(newEntries);
			try {
				if(newFinanceBillsBl.newReceiptBill(rBill)){
					
				}else{
					/**
					 * 错误提示
					 */
				}
			} catch (RemoteException e) {
				e.printStackTrace();
			}
			break;
		}
	}
	
	@FXML
	protected void backButtonListener(){
		BusinessProcessListUiStarter starter=new BusinessProcessListUiStarter();
		starter.businessProcessListUi(UserPositionEnum.FINANCE);
	}
}
