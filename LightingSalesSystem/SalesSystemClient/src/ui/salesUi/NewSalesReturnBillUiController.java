/**
 * 
 */
package ui.salesUi;

import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.Optional;
import assistant.type.BillCategoryEnum;
import assistant.type.BillStateEnum;
import assistant.utility.Date;
import blService.customerBlService.CustomerBlService;
import blService.salesBlService.SalesBlService;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.TableColumn.CellDataFeatures;
import javafx.scene.control.TableColumn.CellEditEvent;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.text.Text;
import javafx.util.Callback;
import javafx.util.converter.DoubleStringConverter;
import javafx.util.converter.IntegerStringConverter;
import rmi.remoteHelper.CustomerRemoteHelper;
import rmi.remoteHelper.RemoteHelperFactory;
import rmi.remoteHelper.SalesRemoteHelper;
import ui.billUi.billStateUi.SalesmanBillStateUiStarter;
import ui.mainUi.salesmanMainUi.SalesmanMainUiStarter;
import vo.BillVO;
import vo.CustomerVO;
import vo.GoodsVO;
import vo.SalesGoodsVO;
import vo.SalesReturnBillVO;
import vo.UserInfoVO;

/**
 * @author 王宁一
 * @version 2012.12.27
 *
 */
public class NewSalesReturnBillUiController {

	@FXML
	private Text billIdText; 
	
	@FXML
	private TableView<BillVO> draftListTable;
	
	@FXML
	private TableColumn<BillVO, Button> draftSubmitColumn;
	
	@FXML
	private TableColumn<BillVO, Button> draftDeleteColumn;
	
	@FXML
	private TableColumn<BillVO, Button> draftViewColumn;
	
	@FXML
	private TableColumn<BillVO, String> draftColumn;
	
	@FXML
	private TableView<SalesGoodsVO> goodsInfoTable;
	
	@FXML
	private TableColumn<SalesGoodsVO, Button> chooseButtonColumn;
	
	@FXML
	private TableColumn<SalesGoodsVO, String> goodsIdColumn;
	
	@FXML
	private TableColumn<SalesGoodsVO, String> goodsNameColumn;
	
	@FXML
	private TableColumn<SalesGoodsVO, String> goodsTypeColumn;
	
	@FXML
	private TableColumn<SalesGoodsVO, Integer> goodsNumColumn;
	
	@FXML
	private TableColumn<SalesGoodsVO, Double> goodsUnitPriceColumn;
	
	@FXML
	private TableColumn<SalesGoodsVO, Double> goodsSumPriceColumn;
	
	@FXML
	private TableColumn<SalesGoodsVO, String> goodsCommentColumn;
	
	@FXML
	private TextField sellerText;
	
	@FXML
	private TextField businessManText;//默认业务员
	
	@FXML
	private TextField wareText;

	@FXML
	private TextArea commentText;
	
	@FXML
	private TextField moneyText;//折让后总额
	
	@FXML 
	private Button submitButton;
	
	@FXML
	private Button saveAsDraftButton;
	
	@FXML
	private Button newGoodsListButton;
	
	private boolean comeFrom;
	
	private UserInfoVO userInfoVO;
	
	private ObservableList<BillVO> drafts=FXCollections.observableArrayList();
	
	private ObservableList<SalesGoodsVO> goods=FXCollections.observableArrayList();
	
	//private SalesBlService service=new SalesBlService_Stub();
	
	private SalesRemoteHelper salesRemoteHelper=RemoteHelperFactory.getSalesRemoteHelper();
		
	private SalesBlService service=salesRemoteHelper.getSalesBlService();
		
	//private CustomerBlService customerService=new CustomerBlService_Stub();
		
	private CustomerRemoteHelper customerRemoteHelper=RemoteHelperFactory.getCustomerRemoteHelper();
		
	private CustomerBlService customerService=customerRemoteHelper.getCustomerBlService();
	
	private double amount=0;
	
	private CustomerVO customer;
	
	private boolean isSaved=true;
	
	Date date=new Date();
	
	public void init(UserInfoVO user, boolean come) {
		comeFrom=come;
		userInfoVO=user;
		
		ArrayList<SalesGoodsVO> goodsInfo=new ArrayList<>();
		
		goodsInfoTable.setItems(goods);
		goods.addAll(goodsInfo);
		
		//初始化SalesGoods, 商品信息列表
		
			chooseButtonColumn.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<SalesGoodsVO,Button>, ObservableValue<Button>>() {

				@Override
				public ObservableValue<Button> call(CellDataFeatures<SalesGoodsVO, Button> param) {
					SalesGoodsVO goodsVO=param.getValue();
					Button chooseButton=new Button("选择");
					
					chooseButton.setOnAction(paramx->{
						int position=goods.indexOf(goodsVO);
						
						choose(position);
					});
						
					return new SimpleObjectProperty<Button>(chooseButton);
				}
			});		
			
		goodsIdColumn.setCellValueFactory(new PropertyValueFactory<SalesGoodsVO,String>("goodsId"));
			
		goodsNameColumn.setCellValueFactory(new PropertyValueFactory<SalesGoodsVO,String>("goodsName"));
			
		goodsTypeColumn.setCellValueFactory(new PropertyValueFactory<SalesGoodsVO,String>("goodsType"));
			
		goodsNumColumn.setCellValueFactory(new PropertyValueFactory<SalesGoodsVO,Integer>("number"));
		goodsNumColumn.setCellFactory(col->{
			TextFieldTableCell<SalesGoodsVO, Integer> tableCell=new TextFieldTableCell<SalesGoodsVO, Integer>(new IntegerStringConverter());
			tableCell.setEditable(true);
			return tableCell;
			}		
		);
			
		goodsUnitPriceColumn.setCellValueFactory(new PropertyValueFactory<SalesGoodsVO,Double>("unitPrice"));
		goodsUnitPriceColumn.setCellFactory(col->{
			TextFieldTableCell<SalesGoodsVO, Double> tableCell=new TextFieldTableCell<SalesGoodsVO, Double>(new DoubleStringConverter());
			tableCell.setEditable(true);
			return tableCell;
		});
			
		goodsSumPriceColumn.setCellValueFactory(new PropertyValueFactory<SalesGoodsVO,Double>("sum"));
			
		goodsCommentColumn.setCellValueFactory(new PropertyValueFactory<SalesGoodsVO,String>("comment"));
			
		goodsCommentColumn.setCellFactory(TextFieldTableCell.forTableColumn());						
		
		try {
			billIdText.textProperty().addListener(new ChangeListener<String>() {

				@Override
				public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
					// TODO Auto-generated method stub
					isSaved=false;
				}
			});
			
			sellerText.textProperty().addListener(new ChangeListener<String>() {

				@Override
				public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
					// TODO Auto-generated method stub
					isSaved=false;
				}
			});
			
			businessManText.textProperty().addListener(new ChangeListener<String>() {

				@Override
				public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
					// TODO Auto-generated method stub
					isSaved=false;
				}
			});
			
			wareText.textProperty().addListener(new ChangeListener<String>() {

				@Override
				public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
					// TODO Auto-generated method stub
					isSaved=false;
				}
			});
			
			commentText.textProperty().addListener(new ChangeListener<String>() {

				@Override
				public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
					// TODO Auto-generated method stub
					isSaved=false;
				}
			});
			
			moneyText.textProperty().addListener(new ChangeListener<String>() {

				@Override
				public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
					// TODO Auto-generated method stub
					isSaved=false;
				}
			});
			
			//草稿箱
			draftListTable.setItems(drafts);
			ArrayList<SalesReturnBillVO> bills;
			if(service.getSalesReturnDraftBillsList(user)!=null) {
				bills=service.getSalesReturnDraftBillsList(user);
			}else {
				bills=null;
			}
			
			
			drafts.addAll(bills);
			
			draftColumn.setCellValueFactory(new PropertyValueFactory<BillVO,String>("Id"));
			draftDeleteColumn.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<BillVO,Button>, ObservableValue<Button>>() {
				
				@Override
				public ObservableValue<Button> call(CellDataFeatures<BillVO, Button> param) {
					// TODO Auto-generated method stub
					BillVO bill=param.getValue();
					
					Button delete=new Button("删除");
					
					delete.setOnAction(oa ->{
						try {
							if(service.DeletePurchaseDraft(bill.getId())){
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
			
			draftViewColumn.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<BillVO,Button>, ObservableValue<Button>>() {

				@Override
				public ObservableValue<Button> call(CellDataFeatures<BillVO, Button> param) {
					// TODO Auto-generated method stub
					BillVO bill=param.getValue();
					Button view=new Button("查看");
					
					view.setOnAction(paramx->{
						if(isSaved) {
							showContent((SalesReturnBillVO)bill);
						}else {
							warn();
						}
				
						isSaved=true;
					});
					
					return new SimpleObjectProperty<Button>(view);
				}
			});
			
			draftSubmitColumn.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<BillVO,Button>, ObservableValue<Button>>() {

				@Override
				public ObservableValue<Button> call(CellDataFeatures<BillVO, Button> param) {
					
					BillVO bill=param.getValue();
					Button submit=new Button("提交");
					
					submit.setOnAction(paramx->{
						try {
							service.SubmitSalReturn(bill.getId());
							drafts.clear();
							ArrayList<SalesReturnBillVO> bills;
							if(service.getSalesReturnDraftBillsList(userInfoVO)!=null) {
								bills=service.getSalesReturnDraftBillsList(userInfoVO);
							}else {
								bills=null;
							}
							drafts.addAll(bills);
						} catch (RemoteException e) {
							e.printStackTrace();
							Alert alert=new Alert(AlertType.ERROR);
							alert.setTitle("错误");
							alert.setHeaderText(null);
							alert.setContentText("网络故障，请稍后再试");
							alert.showAndWait();
						}
					});
					return new SimpleObjectProperty<Button>(submit);
				}
				
			});
			
		} catch (Exception e) {
			e.printStackTrace();
		}
				
	}
	
	public void showContent(SalesReturnBillVO bill) {
		if(bill.getId()!=null) {
			billIdText.setText(bill.getId());
		}
		if(bill.getCustomer()!=null) {
			sellerText.setText(bill.getCustomer().getName());
		}
		if(bill.getBusinessman()!=null) {
			businessManText.setText(bill.getBusinessman().getName());
		}
		if(bill.getWare()!=0) {
			wareText.setText(""+bill.getWare());
		}
		if(bill.getCreaterComment()!=null) {
			commentText.setText(bill.getCreaterComment());
		}
		if(bill.getMoney()!=0) {
			moneyText.setText(""+bill.getMoney());
		}
		
		goods.clear();
		ArrayList<SalesGoodsVO> toShows=bill.getGoodsList();
		for(int i=0;i<toShows.size();i++) {
			goods.add(toShows.get(i));
		}
	}
	
	@FXML
	public void saveAsDraft() {
		String billId=billIdText.getText();
		if(billId.equals("请重新输入！")) {
			billId="";
		}
		ArrayList<SalesGoodsVO> goodsVOs=new ArrayList<>();
		goodsVOs.addAll(goods);
		//amount已计算好
		
		SalesReturnBillVO draft=new SalesReturnBillVO();
		draft.setCreater(userInfoVO);
		draft.setCreateDate(date);
		draft.setCategoryEnum(BillCategoryEnum.SALES_RETURN_BILL);
		draft.setBillStateEnum(BillStateEnum.DRAFT);
		draft.setGoodsList(goodsVOs);
		if(wareText.getText()!=null) {
			draft.setWare(Integer.valueOf(wareText.getText()));
		}
		if(commentText.getText()!=null) {
			draft.setCreaterComment(commentText.getText());
		}
		draft.setMoney(amount);
		draft.setCustomer(customer);
		String name=businessManText.getText();
		try {
			if(customerService.getUserByName(name)==null) {
				draft.setBusinessman(null);
				Alert alert=new Alert(AlertType.ERROR);
				alert.setTitle("错误");
				alert.setHeaderText(null);
				alert.setContentText("不存在该业务员!");
				alert.showAndWait();
				submitButton.setDisable(true);
			}else {
				draft.setBusinessman(customerService.getUserByName(name));
			}
		} catch (RemoteException e1) {
			e1.printStackTrace();
		}
		
		try {
			if(service.NewSalReturnDraft(draft)) {
				isSaved=true;
				drafts.clear();
				ArrayList<SalesReturnBillVO> bills;
				if(service.getSalesReturnDraftBillsList(userInfoVO)!=null) {
					bills=service.getSalesReturnDraftBillsList(userInfoVO);
				}else {
					bills=null;
				}
				
				drafts.addAll(bills);
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
	}
	
	@FXML
	public void submit() {
		String billId=billIdText.getText();
		if(billId.equals("请重新输入！")) {
			billId="";
		}
		ArrayList<SalesGoodsVO> goodsVOs=new ArrayList<>();
		goodsVOs.addAll(goods);
		//amount已计算好
		
		SalesReturnBillVO bill=new SalesReturnBillVO();
		bill.setCreater(userInfoVO);
		bill.setCreateDate(date);
		bill.setCategoryEnum(BillCategoryEnum.SALES_RETURN_BILL);
		bill.setBillStateEnum(BillStateEnum.TBD);
		bill.setId(billId);
		bill.setGoodsList(goodsVOs);
		if(wareText.getText()!=null) {
			bill.setWare(Integer.valueOf(wareText.getText()));
		}
		if(commentText.getText()!=null) {
			bill.setCreaterComment(commentText.getText());
		}
		bill.setMoney(amount);
		bill.setCustomer(customer);
		String name=businessManText.getText();
		try {
			if(customerService.getUserByName(name)==null) {
				bill.setBusinessman(null);
			}else {
				bill.setBusinessman(customerService.getUserByName(name));
			}
		} catch (RemoteException e1) {
			e1.printStackTrace();
		}
		
		try {
			if(service.NewSalReturn(bill)) {
				isSaved=true;
				SalesmanMainUiStarter starter=new SalesmanMainUiStarter();
				starter.startSalesman(userInfoVO);
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
	}
	
	public void choose(int position) {
		//Start
		ChooseUiStarter starter=new ChooseUiStarter();
		starter.salesReturnStart(this, position);				
	}
	
	public void replaceGoodsData(int position, GoodsVO goodsChoosed) {
		SalesGoodsVO toReplace=goods.get(position);
		//test
				
		toReplace.setGoodsName(goodsChoosed.getName());
		toReplace.setGoodsId(goodsChoosed.getNumber());
		toReplace.setGoodsType(goodsChoosed.getType());
		toReplace.setUnitPrice(Double.parseDouble(goodsChoosed.getSellingPrice()));
		goods.set(position, toReplace);
	}
	
	public void warn() {
		Alert confirmation=new Alert(AlertType.CONFIRMATION);
		confirmation.setTitle("系统消息");
		confirmation.setHeaderText(null);
		confirmation.setContentText("是否保存当前单据到草稿箱？");
		Optional<ButtonType> result=confirmation.showAndWait();
		if(result.get()==ButtonType.OK) {
			saveAsDraft();
			isSaved=true;
		}else {
			confirmation.close();
		}		
	}
	
	@FXML
	protected void getCustomerInfo() {
		String name=sellerText.getText();
		try {
			if(customerService.QueryCustomer(name)!=null) {
				customer=customerService.QueryCustomer(name).get(0);
				System.out.println(customer.getId());
				System.out.println(customer.getName());
			}else {
				Alert alert=new Alert(AlertType.ERROR);
				alert.setTitle("错误");
				alert.setHeaderText(null);
				alert.setContentText("不存在该客户!");
				alert.showAndWait();
				submitButton.setDisable(true);
			}
			
		} catch (RemoteException e) {
			e.printStackTrace();
			sellerText.setText("未找到客户!");
			submitButton.setDisable(true);
		}
	}
	
	@FXML
	protected void newGoodsList() {
		SalesGoodsVO vo=new SalesGoodsVO();
		goods.add(vo);
	}
	
	@FXML
	protected void numColumnEdited(CellEditEvent<TextFieldTableCell<Integer, Integer>, Integer> event)throws RemoteException {

		int oldValue=event.getOldValue();
		int newValue=event.getNewValue();
		
		if(!(oldValue==newValue)) {
			int rowIndex=event.getTablePosition().getRow();
			SalesGoodsVO good=goods.get(rowIndex);
			if(newValue<0) {
				newValue=0;
			}
			good.setNumber(newValue);
			double money=newValue*good.getUnitPrice();
			good.setSum(money);
			goods.set(rowIndex, good);
			
			amount=0;
			for(int i=0;i<goods.size();i++) {
				amount+=goods.get(i).getSum();
			}
			moneyText.setText(""+amount);
		}
	}
	
	
	@FXML
	protected void unitPriceColumnEdited(CellEditEvent<TextFieldTableCell<Double, Double>, Double> event)throws RemoteException {
		double oldValue=event.getOldValue();
		double newValue=event.getNewValue();
		
		if(!(oldValue==newValue)) {
			int rowIndex=event.getTablePosition().getRow();
			SalesGoodsVO good=goods.get(rowIndex);
			if(newValue<0) {
				newValue=0;
			}
			good.setUnitPrice(newValue);
			good.setSum(good.getNumber()*newValue);
			goods.set(rowIndex, good);
			
			amount=0;
			for(int i=0;i<goods.size();i++) {
				amount+=goods.get(i).getSum();
			}
			moneyText.setText(""+amount);
		}
	}
	
	@FXML
	protected void commentColumnEdited(CellEditEvent<TextFieldTableCell<String, String>, String> event)throws RemoteException {
		String oldValue=event.getOldValue();
		String newValue=event.getNewValue();
		
		if(oldValue==null) {
			oldValue="";
		}
		if(!oldValue.equals(newValue)) {
			int rowIndex=event.getTablePosition().getRow();
			SalesGoodsVO good=goods.get(rowIndex);
			good.setComment(newValue);
			goods.set(rowIndex, good);
		}
	}
	
	@FXML
	protected void backButtoneListener(){
		if(comeFrom) {
			SalesmanMainUiStarter starter=new SalesmanMainUiStarter();
			starter.startSalesman(userInfoVO);
		}else {
			SalesmanBillStateUiStarter stateUiStarter=new SalesmanBillStateUiStarter(userInfoVO);
			stateUiStarter.salesmanBillStateUi();
		}		
	}
	
}






























