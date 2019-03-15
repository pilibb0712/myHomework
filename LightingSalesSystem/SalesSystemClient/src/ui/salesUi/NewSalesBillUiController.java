/**
 * 
 */
package ui.salesUi;

import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.Optional;

import assistant.type.BillCategoryEnum;
import assistant.type.BillStateEnum;
import assistant.type.CustomerLevelEnum;
import assistant.utility.Date;
import blService.customerBlService.CustomerBlService;
import blService.salesBlService.SalesBlService;
import javafx.application.Platform;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Dialog;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonBar.ButtonData;
import javafx.scene.control.TableColumn.CellDataFeatures;
import javafx.scene.control.TableColumn.CellEditEvent;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.util.Callback;
import javafx.util.Pair;
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
import vo.SalesBillVO;
import vo.SalesGoodsVO;
import vo.UserInfoVO;

/**
 * @author 王宁一
 * @version 2017.12.27
 * 销售单
 *
 */
public class NewSalesBillUiController {

	@FXML
	private Text billIdText; 
	
	@FXML
	private TableView<BillVO> draftListTable;
	
	@FXML
	private TableColumn<BillVO, Button> draftDeleteColumn;
	
	@FXML
	private TableColumn<BillVO, Button> draftSubmitColumn;
	
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
	private TextField oriMoneyText;
	
	@FXML
	private TextField discountText;
	
	@FXML
	private TextField couponText;
	
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
	
	private double oriAmount=0;
	
	private double discount=0;
	
	private double limit=0;
	
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
				
				discountText.textProperty().addListener(new ChangeListener<String>() {

					@Override
					public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
						// TODO Auto-generated method stub
						isSaved=false;
					}
				});
				
				couponText.textProperty().addListener(new ChangeListener<String>() {

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
				
			//草稿箱
			draftListTable.setItems(drafts);
			ArrayList<SalesBillVO> bills;
			if(service.getSalesDraftBillsList(userInfoVO)!=null) {
				bills=service.getSalesDraftBillsList(user);
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
							if(service.DeleteSalesDraft(bill.getId())){
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
							showContent((SalesBillVO)bill);
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
							service.SubmitSales(bill.getId());
							drafts.clear();
							ArrayList<SalesBillVO> bills;
							if(service.getSalesDraftBillsList(userInfoVO)!=null) {
								bills=service.getSalesDraftBillsList(userInfoVO);
							}else {
								bills=null;
							}
							
							drafts.addAll(bills);
						} catch (RemoteException e) {
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
	
	public void showContent(SalesBillVO bill) {
		if(bill.getId()!=null) {
			billIdText.setText(bill.getId());
		}
		if(bill.getCustomer()!=null) {
			customer=bill.getCustomer();
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
		discountText.setText(""+bill.getDiscount());
		discount=bill.getDiscount();
		couponText.setText(""+bill.getCoupon());
		oriMoneyText.setText(""+bill.getOriMoney());
		oriAmount=bill.getOriMoney();
		moneyText.setText(""+bill.getMoney());
		amount=bill.getMoney();
		
		goods.clear();
		ArrayList<SalesGoodsVO> toShows=bill.getGoodsList();
		for(int i=0;i<toShows.size();i++) {
			goods.add(toShows.get(i));
		}	
	}
	
	@FXML
	public void saveAsDraft() {
		ArrayList<SalesGoodsVO> goodsVOs=new ArrayList<>();
		goodsVOs.addAll(goods);
		//amount已计算好
		
		SalesBillVO draft=new SalesBillVO();
		draft.setCreater(userInfoVO);
		draft.setCreateDate(date);
		draft.setCategoryEnum(BillCategoryEnum.SALES_BILL);
		draft.setBillStateEnum(BillStateEnum.DRAFT);
		draft.setGoodsList(goodsVOs);
		if(wareText.getText()!=null) {
			draft.setWare(Integer.valueOf(wareText.getText()));
		}
		if(commentText.getText()!=null) {
			draft.setCreaterComment(commentText.getText());
		}
		if(couponText.getText()!=null) {
			draft.setCoupon(Double.parseDouble(couponText.getText()));
		}
		
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
		
		oriAmount=0;
		for(int i=0;i<goods.size();i++) {
			oriAmount+=goods.get(i).getSum();
		}
		oriMoneyText.setText(""+oriAmount);
		amount=oriAmount-discount;
		moneyText.setText(""+amount);
		draft.setOriMoney(oriAmount);
		draft.setMoney(amount);
		
		try {
			if(service.NewSalesDraft(draft)) {
				isSaved=true;
				drafts.clear();
				ArrayList<SalesBillVO> bills;
				if(service.getSalesDraftBillsList(userInfoVO)!=null) {
					bills=service.getSalesDraftBillsList(userInfoVO);
				}else {
					bills=null;
				}
				
				drafts.addAll(bills);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@FXML
	public void submit() {
		ArrayList<SalesGoodsVO> goodsVOs=new ArrayList<>();
		goodsVOs.addAll(goods);
		//amount已计算好
		
		SalesBillVO bill=new SalesBillVO();
		bill.setCreater(userInfoVO);
		bill.setCreateDate(date);
		bill.setCategoryEnum(BillCategoryEnum.SALES_BILL);
		bill.setBillStateEnum(BillStateEnum.TBD);
		bill.setGoodsList(goodsVOs);
		if(wareText.getText()!=null) {
			bill.setWare(Integer.valueOf(wareText.getText()));
		}
		if(commentText.getText()!=null) {
			bill.setCreaterComment(commentText.getText());
		}
		if(couponText.getText()!=null) {
			bill.setCoupon(Double.parseDouble(couponText.getText()));
		}
		bill.setCustomer(customer);
		String name=businessManText.getText();
		try {
			if(customerService.getUserByName(name)==null) {
				bill.setBusinessman(null);
				Alert alert=new Alert(AlertType.ERROR);
				alert.setTitle("错误");
				alert.setHeaderText(null);
				alert.setContentText("不存在该业务员!");
				alert.showAndWait();
				submitButton.setDisable(true);
			}else {
				bill.setBusinessman(customerService.getUserByName(name));
			}
		} catch (RemoteException e1) {
			e1.printStackTrace();
		}
		
		oriAmount=0;
		for(int i=0;i<goods.size();i++) {
			oriAmount+=goods.get(i).getSum();
		}
		oriMoneyText.setText(""+oriAmount);
		amount=oriAmount-discount;
		moneyText.setText(""+amount);
		bill.setOriMoney(oriAmount);
		bill.setMoney(amount);
		
		try {
			if(service.NewSales(bill)) {
				isSaved=true;
				SalesmanMainUiStarter starter=new SalesmanMainUiStarter();
				starter.startSalesman(userInfoVO);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void choose(int position) {
		//Start
		ChooseUiStarter starter=new ChooseUiStarter();
		starter.salesStart(this, position);
				
	}
	
	@FXML
	public void chooseStrategy() {
		ArrayList<SalesGoodsVO> goodsVOs=new ArrayList<>();
		goodsVOs.addAll(goods);
		double consumption=oriAmount;
		CustomerLevelEnum level;
		if(customer!=null) {
			level=customer.getLevel();
		}else {
			level=CustomerLevelEnum.VIP1;//普通客户
		}
		PromotionUiStarter promotionUiStarter=new PromotionUiStarter(consumption, level, goodsVOs);
		promotionUiStarter.selectStragety(this);
	}
	
	public void replaceGoodsData(int position, GoodsVO goodsChoosed) {
		SalesGoodsVO toReplace=goods.get(position);
				
		toReplace.setGoodsName(goodsChoosed.getName());
		toReplace.setGoodsId(goodsChoosed.getNumber());
		toReplace.setGoodsType(goodsChoosed.getType());
		toReplace.setUnitPrice(Double.parseDouble(goodsChoosed.getSellingPrice()));//售价，复制时需注意
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
	protected void getDiscountNum() {
		double tempResult=0;
		if(discountText.getText()!=null) {
			tempResult=Double.parseDouble(discountText.getText());
		}
		switch (userInfoVO.getUserPositionEnum()) {
		case SALESMAN:
			limit=1000;
			verify();
			break;
		default:
			break;
		}
		
		switch (userInfoVO.getUserPositionEnum()) {
		case MANAGER://任意金额折让
			discount=tempResult;
			break;
		default:
			if(limit<tempResult) {
				discount=limit;
				discountText.setText(""+limit);
			}else {
				discount=tempResult;
			}
			break;	
		}
//		discount=Double.parseDouble(discountText.getText());
		amount=oriAmount-discount;
		if(amount<0) {
			amount=0;
		}
		moneyText.setText(""+amount);
	}
	
	private void verify() {
		
		Dialog<Pair<String, String>> dialog=new Dialog<>();
		dialog.setTitle("身份验证");
		dialog.setHeaderText("请确认您的权限");
		
		ButtonType loginButtonType=new ButtonType("确认", ButtonData.OK_DONE);
		dialog.getDialogPane().getButtonTypes().addAll(loginButtonType, ButtonType.CANCEL);
		
		GridPane grid = new GridPane();
		grid.setHgap(10);
		grid.setVgap(10);
		grid.setPadding(new Insets(20, 150, 10, 10));

		TextField userId = new TextField();
		userId.setPromptText("UserID");
		PasswordField password = new PasswordField();
		password.setPromptText("Password");

		grid.add(new Label("UserID:"), 0, 0);
		grid.add(userId, 1, 0);
		grid.add(new Label("Password:"), 0, 1);
		grid.add(password, 1, 1);

		Node loginButton = dialog.getDialogPane().lookupButton(loginButtonType);
		loginButton.setDisable(true);
		
		userId.textProperty().addListener((observable, oldValue, newValue) -> {
		    loginButton.setDisable(newValue.trim().isEmpty());
		});

		dialog.getDialogPane().setContent(grid);

		Platform.runLater(()->userId.requestFocus());
		
		dialog.setResultConverter(dialogButton->{
			if(dialogButton==loginButtonType) {
				return new Pair<>(userId.getText(),password.getText());
			}
			return null;
		});
		
		Optional<Pair<String, String>> result=dialog.showAndWait();
		
		result.ifPresent(idPw->{
			//验证账号密码
			String id=idPw.getKey();
			String pw=idPw.getValue();			
			
			//TODO
			//权限
			if(id.equals(userInfoVO.getId())&&pw.equals("salesmanager")) {
				limit=5000;
				dialog.close();
			}else {
				dialog.setContentText("您没有权限");
			}
		});
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
				sellerText.setPromptText("未找到客户");
				Alert alert=new Alert(AlertType.ERROR);
				alert.setTitle("错误");
				alert.setHeaderText(null);
				alert.setContentText("不存在该客户!");
				alert.showAndWait();
			}			
		} catch (RemoteException e) {
			e.printStackTrace();
			sellerText.setText("未找到客户!");
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
			
			oriAmount=0;
			for(int i=0;i<goods.size();i++) {
				oriAmount+=goods.get(i).getSum();
			}
			
			amount=oriAmount-discount;
			if(amount<0) {
				amount=0;
			}
			oriMoneyText.setText(""+oriAmount);
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
			
			oriAmount=0;
			for(int i=0;i<goods.size();i++) {
				oriAmount+=goods.get(i).getSum();
			}

			amount=oriAmount-discount;
			if(amount<0) {
				amount=0;
			}
			oriMoneyText.setText(""+oriAmount);
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

	/**
	 * @param discount2
	 * @param coupon
	 */
	public void replaceDiscount(double m_discount, double m_coupon) {
		// TODO Auto-generated method stub
		discount=m_discount;
		discountText.setText(discount+"");
		couponText.setText(m_coupon+"");
		amount=oriAmount-discount;
		moneyText.setText(amount+"");
		
	}
	
}






























