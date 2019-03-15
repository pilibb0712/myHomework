package ui.financeUi;

import java.rmi.RemoteException;
import java.util.ArrayList;
import assistant.type.BillCategoryEnum;
import assistant.utility.Date;
import blService.financeBlService.NewFinanceBillsBlService;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.TableColumn.CellDataFeatures;
import javafx.scene.control.TableColumn.CellEditEvent;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.util.Callback;
import rmi.remoteHelper.FinanceRemoteHelper;
import rmi.remoteHelper.RemoteHelperFactory;
import ui.billUi.billStateUi.FinancialStaffBillStateUiStarter;
import ui.mainUi.financialStaffMainUi.FinancialStaffMainUiStarter;
import vo.BillEntryVO;
import vo.BillVO;
import vo.CashExpenseBillVO;
import vo.PaymentBillVO;
import vo.ReceiptBillVO;
import vo.UserInfoVO;

public class NewFinanceBillsUiController {
	@FXML
	private Text billTypePrompt;
	
	@FXML
	private TableView<BillEntryVO> entryTable;
	
	@FXML
	private TableColumn<BillEntryVO, String> entryNameColumn;
	
	@FXML
	private TableColumn<BillEntryVO, String> entryMoneyColumn;
	
	@FXML
	private Button submitButton;
	
	@FXML
	private Button saveDraftButton;
	
	@FXML
	private TableView<BillVO> draftListTable;
	
	@FXML
	private TableColumn<BillVO, Button> draftDeleteColumn;
	
	@FXML
	private TableColumn<BillVO, Button> draftViewColumn;
	
	@FXML
	private TableColumn<BillVO, String> draftColumn;
	
	@FXML
	private Text createTimeText;
	
	@FXML
	private TextField bankAccountTextField;
	
	@FXML
	private TextField sumTextField;
	
	@FXML
	private TextArea commentTextArea;
 	
	private String billType;
	
	private UserInfoVO creator;
	
	private ObservableList<BillEntryVO> entries=FXCollections.observableArrayList();
	
	private ObservableList<BillVO> drafts=FXCollections.observableArrayList();

	private FinanceRemoteHelper remoteHelper=RemoteHelperFactory.getFinanceRemoteHelper();
	
//	private NewFinanceBillsBlService newFinanceBillsBl=new NewFinanceBillsBlService_Stub();
	
	private NewFinanceBillsBlService newFinanceBillsBl=remoteHelper.getNewFinanceBillsBlService();
	
	private boolean isSaved=true;
	
	private String parentUi;
	
	public void init(String type, UserInfoVO ctor, String parent){
		parentUi=parent;
		
		//初始化时间Text
		Date time=new Date();
		createTimeText.setText(time.getDate().toString());
		
		//监听各种信息变化
		bankAccountTextField.textProperty().addListener(new ChangeListener<String>(){

			@Override
			public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
				isSaved=false;
			}
			
		});
		
		sumTextField.textProperty().addListener(new ChangeListener<String>(){

			@Override
			public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
				isSaved=false;
			}
			
		});
		
		commentTextArea.textProperty().addListener(new ChangeListener<String>(){

			@Override
			public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
				isSaved=false;
			}
			
		});
		
		//初始化条目清单
		billTypePrompt.setText(type);
		billType=type;
		creator=ctor;
		
		entryNameColumn.setCellValueFactory(new PropertyValueFactory<>("entryName"));
		entryMoneyColumn.setCellValueFactory(new PropertyValueFactory<>("entryMoney"));
		
		entryNameColumn.setCellFactory(TextFieldTableCell.forTableColumn());
		entryMoneyColumn.setCellFactory(TextFieldTableCell.forTableColumn());
	
		for(int i=0;i<100;i++)
			entries.add(new BillEntryVO("",""));
		
		entryTable.setItems(entries);
		
		//初始化草稿箱
		draftListTable.setItems(drafts);
		
		switch (billType){
			case "现金费用单":
				try {
					ArrayList<CashExpenseBillVO> bills=newFinanceBillsBl.getCashExpenseBillDraftList(creator);
					
					drafts.addAll(bills);
			
					draftColumn.setCellValueFactory(
								new PropertyValueFactory<BillVO,String>("Id")
						);
						
					draftDeleteColumn.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<BillVO, Button>, ObservableValue<Button>>(){
							
						@Override
						public ObservableValue<Button> call(CellDataFeatures<BillVO, Button> param) {
							BillVO bill=param.getValue();
							
							Button delete=new Button("删除");
							
							delete.setOnAction(oa ->{
								try {
									if(newFinanceBillsBl.deleteCashExpenseBillDraft((CashExpenseBillVO)bill)){
										drafts.remove(bill);
									}else{
										/**
										 * 错误提示
										 */
									}
								} catch (RemoteException e) {
									e.printStackTrace();
								}
							});

								
							return new SimpleObjectProperty<Button>(delete);
						}
					});
						
					draftViewColumn.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<BillVO, Button>, ObservableValue<Button>>(){

						@Override
						public ObservableValue<Button> call(CellDataFeatures<BillVO, Button> param) {
							BillVO bill=param.getValue();
							
							Button view=new Button("查看");
								
							view.setOnAction(oa ->{
								if(isSaved){
									showCashExpenseBillDraft((CashExpenseBillVO)bill);
								}else{
									savePrompt(bill);
								}
								
								isSaved=true;
							});
								
							return new SimpleObjectProperty<Button>(view);
						}
						
					});				
					
				} catch (RemoteException e) {
					e.printStackTrace();
				}
				break;
			case "收款单":
				try {
					ArrayList<ReceiptBillVO> bills=newFinanceBillsBl.getReceiptBillDraftList(creator);
					
					drafts.addAll(bills);
				
					draftColumn.setCellValueFactory(
							new PropertyValueFactory<BillVO,String>("Id")
					);
						
					draftDeleteColumn.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<BillVO, Button>, ObservableValue<Button>>(){
							

						@Override
						public ObservableValue<Button> call(CellDataFeatures<BillVO, Button> param) {
							BillVO bill=param.getValue();
							
							Button delete=new Button("删除");
							
							delete.setOnAction(oa ->{
								try {
									if(newFinanceBillsBl.deleteReceiptBillDraft((ReceiptBillVO)bill)){
										drafts.remove(bill);
									}else{
										
									}
								} catch (RemoteException e) {
									e.printStackTrace();
								}
							});

								
							return new SimpleObjectProperty<Button>(delete);
						}
					});
						
						draftViewColumn.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<BillVO, Button>, ObservableValue<Button>>(){

							@Override
							public ObservableValue<Button> call(CellDataFeatures<BillVO, Button> param) {
								BillVO bill=param.getValue();
								
								Button view=new Button("查看");
								
								view.setOnAction(oa ->{
									if(isSaved){
										showReceiptBillDraft((ReceiptBillVO)bill);
									}else{
										savePrompt(bill);
									}
									
									isSaved=true;
								});
								
								return new SimpleObjectProperty<Button>(view);
							}
						
						});								
				} catch (RemoteException e) {
					e.printStackTrace();
				}
				break;
			case "付款单":
				try {
					ArrayList<PaymentBillVO> bills=newFinanceBillsBl.getPaymentBillDraftList(creator);
					
					drafts.addAll(bills);
					
					draftColumn.setCellValueFactory(
							new PropertyValueFactory<BillVO,String>("Id")
					);
						
					draftDeleteColumn.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<BillVO, Button>, ObservableValue<Button>>(){
							
						@Override
						public ObservableValue<Button> call(CellDataFeatures<BillVO, Button> param) {
							BillVO bill=param.getValue();
							
							Button delete=new Button("删除");
							
							delete.setOnAction(oa ->{
								try {
									if(newFinanceBillsBl.deletePaymentBillDraft((PaymentBillVO)bill)){
										drafts.remove(bill);
									}else{
										/**
										 * 错误提示
										 */
									}
								} catch (RemoteException e) {
									e.printStackTrace();
								}
							});

								
								return new SimpleObjectProperty<Button>(delete);
							}
						});
						
						draftViewColumn.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<BillVO, Button>, ObservableValue<Button>>(){

							@Override
							public ObservableValue<Button> call(CellDataFeatures<BillVO, Button> param) {
								BillVO bill=param.getValue();
								
								Button view=new Button("查看");
								
								view.setOnAction(oa ->{
									if(isSaved){
										showPaymentBillDraft((PaymentBillVO)bill);
									}else{
										savePrompt(bill);
									}
									
									isSaved=true;
								});
								
								return new SimpleObjectProperty<Button>(view);
							}
						
						});						
				} catch (RemoteException e) {
					e.printStackTrace();
				}
				break;
		}
	}
	
	//当前草稿未保存但要切换单据时跳出提示
	private void savePrompt(BillVO toShow){
		Stage promptStage=new Stage();
		
		VBox rootBox=new VBox();
		rootBox.setSpacing(30);
		rootBox.setAlignment(Pos.CENTER);
		
		Scene promptScene=new Scene(rootBox, 600,400);
		promptStage.setScene(promptScene);
		
		Text prompt=new Text("是否保存当前单据至草稿箱？");
		prompt.setFont(Font.font(20));
		
		HBox buttons=new HBox();
		buttons.setSpacing(50);
		buttons.setAlignment(Pos.CENTER);
		
		Button yesButton=new Button("是");
		yesButton.setFont(Font.font(20));
		yesButton.setPrefSize(80, 50);
		yesButton.setMaxSize(80, 50);
		yesButton.setMinSize(80, 50);
		Button noButton=new Button("否");
		noButton.setFont(Font.font(20));
		noButton.setPrefSize(80, 50);
		noButton.setMaxSize(80, 50);
		noButton.setMinSize(80, 50);
		Button cancelButton=new Button("取消");
		cancelButton.setFont(Font.font(20));
		cancelButton.setPrefSize(80, 50);
		cancelButton.setMaxSize(80, 50);
		cancelButton.setMinSize(80, 50);
		buttons.getChildren().addAll(yesButton, noButton, cancelButton);
		
		rootBox.getChildren().addAll(prompt, buttons);
		
		promptStage.show();
		
		yesButton.setOnAction(oa ->{
			//保存当前草稿
			saveDraft();
			
			switch (billType){
			case "现金费用单":
				showCashExpenseBillDraft((CashExpenseBillVO)toShow);
				break;
			case "付款单":
				showPaymentBillDraft((PaymentBillVO)toShow);
				break;
			case "收款单":
				showReceiptBillDraft((ReceiptBillVO)toShow);
				break;
			}
			
			promptStage.close();
			
			isSaved=true;
		});
		
		noButton.setOnAction(oa ->{
			switch (billType){
			case "现金费用单":
				showCashExpenseBillDraft((CashExpenseBillVO)toShow);
				break;
			case "付款单":
				showPaymentBillDraft((PaymentBillVO)toShow);
				break;
			case "收款单":
				showReceiptBillDraft((ReceiptBillVO)toShow);
				break;
			}
			
			promptStage.close();
			
			isSaved=true;
		});
		
		cancelButton.setOnAction(oa ->{
			promptStage.close();
		});
	}
	
	//分别显示三张单据的草稿内容
	public void showCashExpenseBillDraft(CashExpenseBillVO toShow){
		//显示日期、银行账户、总额、备注
		createTimeText.setText(toShow.getCreateDate().getDate());
		bankAccountTextField.setText(toShow.getBankAccount());
		sumTextField.setText(String.valueOf(toShow.getSum()));
		commentTextArea.setText(toShow.getCreaterComment());
			
		//更换Table里的内容
		entries.clear();
		ArrayList<BillEntryVO> billEntries=toShow.getEntries();
		if(billEntries!=null){
			for(int j=0;j<billEntries.size();j++){
				entries.add(billEntries.get(j));
			}
		}
	}
	
	public void showPaymentBillDraft(PaymentBillVO toShow){
		//显示日期、银行账户、总额、备注
		createTimeText.setText(toShow.getCreateDate().getDate());
		bankAccountTextField.setText(toShow.getBankAccount());
		sumTextField.setText(String.valueOf(toShow.getSum()));
		commentTextArea.setText(toShow.getCreaterComment());
			
		//更换Table里的内容
		entries.clear();
		ArrayList<BillEntryVO> billEntries=toShow.getEntries();
		if(billEntries!=null){
			for(int j=0;j<billEntries.size();j++){
				entries.add(billEntries.get(j));
			}
		}
	}
	
	public void showReceiptBillDraft(ReceiptBillVO toShow){
		//显示日期、银行账户、总额、备注
		createTimeText.setText(toShow.getCreateDate().getDate());
		bankAccountTextField.setText(toShow.getBankAccount());
		sumTextField.setText(String.valueOf(toShow.getSum()));
		commentTextArea.setText(toShow.getCreaterComment());
					
		//更换Table里的内容
		entries.clear();
		ArrayList<BillEntryVO> billEntries=toShow.getEntries();
		if(billEntries!=null){
			for(int j=0;j<billEntries.size();j++){
				entries.add(billEntries.get(j));
			}
		}
	}
	
	//保存当前内容为草稿
	private void saveDraft(){
		String bankAccount=bankAccountTextField.getText();
		ArrayList<BillEntryVO> en=new ArrayList<BillEntryVO>();
		en.addAll(entries);
		double sum=0.0;
		if(!sumTextField.getText().equals(""))
			sum=Double.parseDouble(sumTextField.getText());
		String comment=commentTextArea.getText();
		
		switch (billType){
		case "现金费用单":
			CashExpenseBillVO ceBill=new CashExpenseBillVO();
			ceBill.setCategoryEnum(BillCategoryEnum.CASH_EXPENSE_BILL);
			ceBill.setSum(sum);
			ceBill.setBankAccount(bankAccount);
			ceBill.setEntries(en);
			ceBill.setCreaterComment(comment);
			ceBill.setCreater(creator);
			try {
				if(newFinanceBillsBl.saveCashExpenseBillDraft(ceBill))
					isSaved=true;
					ArrayList<CashExpenseBillVO> bills=newFinanceBillsBl.getCashExpenseBillDraftList(creator);
					drafts.clear();
					drafts.addAll(bills);
					
					Alert alert=new Alert(Alert.AlertType.INFORMATION, "保存成功！");
					alert.showAndWait();
			} catch (RemoteException e) {
				e.printStackTrace();
			}
			break;
		case "付款单":
			PaymentBillVO pBill=new PaymentBillVO();
			pBill.setCategoryEnum(BillCategoryEnum.CASH_EXPENSE_BILL);
			pBill.setSum(sum);
			pBill.setBankAccount(bankAccount);
			pBill.setEntries(en);
			pBill.setCreaterComment(comment);
			pBill.setCreater(creator);
			try {
				if(newFinanceBillsBl.savePaymentBillDraft(pBill))
					isSaved=true;
					ArrayList<PaymentBillVO> bills=newFinanceBillsBl.getPaymentBillDraftList(creator);
					drafts.clear();
					drafts.addAll(bills);
					
					Alert alert=new Alert(Alert.AlertType.INFORMATION, "保存成功！");
					alert.showAndWait();
			} catch (RemoteException e) {
				e.printStackTrace();
			}
			break;
		case "收款单":
			ReceiptBillVO rBill=new ReceiptBillVO();
			rBill.setCategoryEnum(BillCategoryEnum.CASH_EXPENSE_BILL);
			rBill.setSum(sum);
			rBill.setBankAccount(bankAccount);
			rBill.setEntries(en);
			rBill.setCreaterComment(comment);
			rBill.setCreater(creator);
			try {
				if(newFinanceBillsBl.saveReceiptBillDraft(rBill))
					isSaved=true;
					ArrayList<ReceiptBillVO> bills=newFinanceBillsBl.getReceiptBillDraftList(creator);
					drafts.clear();
					drafts.addAll(bills);
					
					Alert alert=new Alert(Alert.AlertType.INFORMATION, "保存成功！");
					alert.showAndWait();
			} catch (RemoteException e) {
				e.printStackTrace();
			}
			break;
		}
	}
	
	@FXML
	protected void columnOnEditCommit(CellEditEvent<TextFieldTableCell<String, String>, String> event) throws Exception{
		String oldValue=event.getOldValue();
		String newValue=event.getNewValue();
		
		if(!oldValue.equals(newValue)){
			int rowIndex=event.getTablePosition().getRow();
			int columnIndex=event.getTablePosition().getColumn();
			BillEntryVO entry=entries.get(rowIndex);
			if(columnIndex==0)
				entry.setEntryName(newValue);
			else
				entry.setEntryMoney(newValue);
		}
		
		isSaved=false;
	}
	
	@FXML
	protected void submitButtonListener(){
		String bankAccount=bankAccountTextField.getText();
		ArrayList<BillEntryVO> en=new ArrayList<BillEntryVO>();
		en.addAll(entries);
		double sum=0.0;
		if(!sumTextField.getText().equals(""))
			sum=Double.parseDouble(sumTextField.getText());
		String comment=commentTextArea.getText();
		
		switch (billType){
		case "现金费用单":
			CashExpenseBillVO ceBill=new CashExpenseBillVO();
			ceBill.setCategoryEnum(BillCategoryEnum.CASH_EXPENSE_BILL);
			ceBill.setSum(sum);
			ceBill.setBankAccount(bankAccount);
			ceBill.setEntries(en);
			ceBill.setCreaterComment(comment);
			ceBill.setCreater(creator);
			try {
				if(newFinanceBillsBl.newCashExpenseBill(ceBill)){
					isSaved=true;
					
					Alert alert=new Alert(Alert.AlertType.INFORMATION, "提交成功！");
					alert.showAndWait();
				}
			} catch (RemoteException e) {
				e.printStackTrace();
			}
			break;
		case "付款单":
			PaymentBillVO pBill=new PaymentBillVO();
			pBill.setCategoryEnum(BillCategoryEnum.CASH_EXPENSE_BILL);
			pBill.setSum(sum);
			pBill.setBankAccount(bankAccount);
			pBill.setEntries(en);
			pBill.setCreaterComment(comment);
			pBill.setCreater(creator);
			try {
				if(newFinanceBillsBl.newPaymentBill(pBill)){
					isSaved=true;
				
					Alert alert=new Alert(Alert.AlertType.INFORMATION, "提交成功！");
					alert.showAndWait();
				}	
			} catch (RemoteException e) {
				e.printStackTrace();
			}
			break;
		case "收款单":
			ReceiptBillVO rBill=new ReceiptBillVO();
			rBill.setCategoryEnum(BillCategoryEnum.CASH_EXPENSE_BILL);
			rBill.setSum(sum);
			rBill.setBankAccount(bankAccount);
		    rBill.setEntries(en);
			rBill.setCreaterComment(comment);
			rBill.setCreater(creator);
			try {
				if(newFinanceBillsBl.newReceiptBill(rBill)){
					isSaved=true;
					Alert alert=new Alert(Alert.AlertType.INFORMATION, "提交成功！");
					alert.showAndWait();
				}
			} catch (RemoteException e) {
				e.printStackTrace();
			}
			break;
		}
		
		entries.clear();
		for(int i=0;i<100;i++){
			BillEntryVO empty=new BillEntryVO("","");
			entries.add(empty);
		}
		
		bankAccountTextField.setText("");
		sumTextField.setText("");
		commentTextArea.setText("");
		createTimeText.setText(new Date().getDate());
		
		isSaved=true;
	}
	
	@FXML
	protected void saveDraftButtonListener(){
		saveDraft();
	}
	
	@FXML
	protected void backButtonListener(){
		if(!isSaved){
			Stage promptStage=new Stage();
			
			VBox rootBox=new VBox();
			rootBox.setSpacing(30);
			rootBox.setAlignment(Pos.CENTER);
			
			Scene promptScene=new Scene(rootBox, 600,400);
			promptStage.setScene(promptScene);
			
			Text prompt=new Text("是否保存当前单据至草稿箱？");
			prompt.setFont(Font.font(20));
			
			HBox buttons=new HBox();
			buttons.setSpacing(50);
			buttons.setAlignment(Pos.CENTER);
			
			Button yesButton=new Button("是");
			yesButton.setFont(Font.font(20));
			yesButton.setPrefSize(80, 50);
			yesButton.setMaxSize(80, 50);
			yesButton.setMinSize(80, 50);
			Button noButton=new Button("否");
			noButton.setFont(Font.font(20));
			noButton.setPrefSize(80, 50);
			noButton.setMaxSize(80, 50);
			noButton.setMinSize(80, 50);
			Button cancelButton=new Button("取消");
			cancelButton.setFont(Font.font(20));
			cancelButton.setPrefSize(80, 50);
			cancelButton.setMaxSize(80, 50);
			cancelButton.setMinSize(80, 50);
			buttons.getChildren().addAll(yesButton, noButton, cancelButton);
			
			rootBox.getChildren().addAll(prompt, buttons);
			
			promptStage.show();
			
			yesButton.setOnAction(oa ->{
				//保存当前草稿
				saveDraft();
				isSaved=true;
				
				if(parentUi.equals("mainUi")){
					FinancialStaffMainUiStarter starter=new FinancialStaffMainUiStarter();
					starter.mainUi();
				}else if(parentUi.equals("modifyUi")){
					FinancialStaffBillStateUiStarter starter=new FinancialStaffBillStateUiStarter();
					starter.financialStaffBillStateUi();
				}
				
				promptStage.close();
			});
			
			noButton.setOnAction(oa ->{
				if(parentUi.equals("mainUi")){
					FinancialStaffMainUiStarter starter=new FinancialStaffMainUiStarter();
					starter.mainUi();
				}else if(parentUi.equals("modifyUi")){
					FinancialStaffBillStateUiStarter starter=new FinancialStaffBillStateUiStarter();
					starter.financialStaffBillStateUi();
				}
				promptStage.close();
			});
			
			cancelButton.setOnAction(oa ->{
				promptStage.close();
			});
		}else{
			if(parentUi.equals("mainUi")){
				FinancialStaffMainUiStarter starter=new FinancialStaffMainUiStarter();
				starter.mainUi();
			}else if(parentUi.equals("modifyUi")){
				FinancialStaffBillStateUiStarter starter=new FinancialStaffBillStateUiStarter();
				starter.financialStaffBillStateUi();
			}
		}	
	}
}
