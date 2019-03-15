package ui.listUi;

import java.io.File;
import java.io.FileOutputStream;
import java.rmi.RemoteException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Iterator;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.HorizontalAlignment;
import org.apache.poi.ss.util.CellRangeAddress;
import assistant.type.CustomerLevelEnum;
import assistant.type.UserPositionEnum;
import assistant.utility.Date;
import blService.listBlService.SalesDetailsListBlService;
import javafx.beans.property.ReadOnlyStringWrapper;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.DateCell;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeTableColumn;
import javafx.scene.control.TreeTableColumn.CellDataFeatures;
import javafx.scene.control.TreeTableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.util.Callback;
import javafx.util.StringConverter;
import rmi.remoteHelper.ListRemoteHelper;
import rmi.remoteHelper.RemoteHelperFactory;
import ui.mainUi.financialStaffMainUi.FinancialStaffMainUiStarter;
import ui.mainUi.managerMainUi.ManagerMainUiStarter;
import ui.uiAssistants.StageFactory;
import vo.ClassificationVO;
import vo.CustomerInfoVO;
import vo.SalesDetailsListSalesBillVO;
import vo.SalesDetailsListVO;
import vo.UserVO;

/**
 * 销售明细表界面Controller
 * @author guxinyu
 * @version 2017.12.18
 *
 */
public class SalesDetailsListUiController {
	private Stage stage;
	
	//左侧条件筛选区
	@FXML
	private DatePicker startDatePicker;
	
	@FXML
	private DatePicker endDatePicker;
	
		//商品
	@FXML
	private TreeTableColumn<String, CheckBox> commodityChooseColumn;
	
	@FXML
	private TreeTableColumn<String, String> commodityColumn;
	
	@FXML
	private TreeTableView<String> commodityClassTable;
	
		//客户
	@FXML
	private TreeTableColumn<CustomerInfoVO, CheckBox> customerChooseColumn;
	
	@FXML
	private TreeTableColumn<CustomerInfoVO, String> customerNameColumn;
	
	@FXML
	private TreeTableColumn<CustomerInfoVO, String> customerIdColumn;
	
	@FXML
	private TreeTableView<CustomerInfoVO> customerTable;
	
		//业务员
	@FXML
	private TreeTableColumn<UserVO, CheckBox> userChooseColumn;
	
	@FXML
	private TreeTableColumn<UserVO, String> userNameColumn;
	
	@FXML
	private TreeTableColumn<UserVO, String> userIdColumn;
	
	@FXML
	private TreeTableView<UserVO> userTable;
	
		//仓库
	@FXML
	private TextField wareHouseTextField;
	
	@FXML
	private Button wareHouseOkButton;
	
	//销售明细表区
	@FXML
	private TableView<SalesDetailsListSalesBillVO> listTable;
	
	@FXML
	private TableColumn<SalesDetailsListSalesBillVO, String> dateColumn;
	
	@FXML
	private TableColumn<SalesDetailsListSalesBillVO, String> commodityNameColumn;
	
	@FXML
	private TableColumn<SalesDetailsListSalesBillVO, String> typeColumn;
	
	@FXML
	private TableColumn<SalesDetailsListSalesBillVO, String> numberColumn;
	
	@FXML
	private TableColumn<SalesDetailsListSalesBillVO, String> unitPriceColumn;
	
	@FXML
	private TableColumn<SalesDetailsListSalesBillVO, String> sumColumn;
	
	private ListRemoteHelper remoteHelper=RemoteHelperFactory.getListRemoteHelper(); 
	
	//private SalesDetailsListBlService salesDetailsListBl=new SalesDetailsListBlService_Stub();
	
	private SalesDetailsListBlService salesDetailsListBl=remoteHelper.getSalesDetailsListBlService();
	
	private ObservableList<SalesDetailsListSalesBillVO> listItems=FXCollections.observableArrayList();
	
	private ArrayList<SalesDetailsListSalesBillVO> billInfos=new ArrayList<SalesDetailsListSalesBillVO>();
	
	private boolean isChoosed=false;
	
	private final String dateFormat="yyyy.MM.dd";
	
	private UserPositionEnum pos;

	public void init(UserPositionEnum ps){
		pos=ps;
		
		if(ps==UserPositionEnum.FINANCE)
			stage=StageFactory.getFinanceStage();
		else if(ps==UserPositionEnum.MANAGER)
			stage=StageFactory.getManagerStage();
		
		//条件筛选区初始化
			//时间
		StringConverter<LocalDate> converter = new StringConverter<LocalDate>() {
	          DateTimeFormatter dateFormatter =
	              DateTimeFormatter.ofPattern(dateFormat);
	          @Override
	          public String toString(LocalDate date) {
	              if (date != null) {
	                  return dateFormatter.format(date);
	              } else {
	                  return "";
	              }
	          }
	 
	          @Override
	          public LocalDate fromString(String string) {
	              if (string != null && !string.isEmpty()) {
	                  return LocalDate.parse(string, dateFormatter);
	              } else {
	                  return null;
	              }
	          }
	      };
	      
	    startDatePicker.setConverter(converter);//更换日期选择器的格式
	    endDatePicker.setConverter(converter);
	    
	    final Callback<DatePicker, DateCell> dayCellFactory = 
	            new Callback<DatePicker, DateCell>() {
	                @Override
	                public DateCell call(final DatePicker datePicker) {
	                    return new DateCell() {
	                        @Override
	                        public void updateItem(LocalDate item, boolean empty) {
	                            super.updateItem(item, empty);
	                           
	                            if (item.isBefore(startDatePicker.getValue().plusDays(1))) {
	                            	setDisable(true);
	                            	setStyle("-fx-background-color: #c1c1c1;");
	                            }   
	                    }
	                };
	            }
	        };
	     endDatePicker.setDayCellFactory(dayCellFactory);//将startDatePicker所选择的日期及其之前的日期单元格都禁用
		
			//商品
		ArrayList<ClassificationVO> comClassification=null;
		try {
			comClassification=salesDetailsListBl.getCommodityClassification();
		} catch (RemoteException e1) {
			e1.printStackTrace();
		}
		
		TreeItem<String> commodityRootItem=new TreeItem<String>("所有商品");
		
		
		for(int i=0;i<comClassification.size();i++){
			ClassificationVO eachClass=comClassification.get(i);
			
			ArrayList<String> goods=eachClass.getGoodsList();
			
			if(goods!=null){
				TreeItem<String> classItem=new TreeItem<String>(eachClass.getName());
	
				for(int j=0;j<goods.size();j++){
					TreeItem<String> goodsItem=new TreeItem<String>(goods.get(j));
					classItem.getChildren().add(goodsItem);
				}
			
				commodityRootItem.getChildren().add(classItem);
			}
			
		}
	
		commodityClassTable.setRoot(commodityRootItem);
		
		commodityColumn.setCellValueFactory((CellDataFeatures<String, String> p) -> 
         new ReadOnlyStringWrapper(p.getValue().getValue()));  
		
		
		commodityChooseColumn.setCellValueFactory(
				new Callback<TreeTableColumn.CellDataFeatures<String, CheckBox>, ObservableValue<CheckBox>>(){

					@Override
					public ObservableValue<CheckBox> call(CellDataFeatures<String, CheckBox> param) {
						CheckBox checkBox=new CheckBox();
						
						TreeItem<String> thisGoods=param.getValue();
						
						if(!thisGoods.isLeaf()){
							return null;
						}
						
						checkBox.selectedProperty().addListener(new ChangeListener<Boolean>(){

							@Override
							public void changed(ObservableValue<? extends Boolean> observable, Boolean oldValue,
									Boolean newValue) {
								if(!isChoosed){
									listItems.clear();
									isChoosed=true;
								}
								
								Iterator<SalesDetailsListSalesBillVO> it=listItems.iterator();
								
								if(oldValue==false&&newValue==true){
									for(int i=0;i<billInfos.size();i++){
										SalesDetailsListSalesBillVO bill=billInfos.get(i);
										String commodity=bill.getCommodityId()+"-"+bill.getCommodityName()+"-"+bill.getCommodityType();
										
										if(thisGoods.getValue().equals(commodity)&&!isExist(bill.getBillId(),bill.getCommodityName())){
											listItems.add(bill);
										}
									}
								}else if(oldValue==true&&newValue==false){
									
									while(it.hasNext()){
										SalesDetailsListSalesBillVO bill=it.next();
										String commodity=bill.getCommodityId()+"-"+bill.getCommodityName()+"-"+bill.getCommodityType();
									
										if(thisGoods.getValue().equals(commodity)){
											it.remove();
										}
									}
									
								}
								
							}
							
						});
						
						return new SimpleObjectProperty<CheckBox>(checkBox);
					}
					
				}
			);
		
			//客户
		ArrayList<CustomerInfoVO> customers=null;
		try {
			customers=salesDetailsListBl.getCustomerList();
		} catch (RemoteException e2) {
			e2.printStackTrace();
		}
		
		CustomerInfoVO CusRootVO=new CustomerInfoVO();
		CusRootVO.setName("所有顾客");
		TreeItem<CustomerInfoVO> rootItem=new TreeItem<CustomerInfoVO>(CusRootVO);
		
		CustomerInfoVO supplier=new CustomerInfoVO();
		supplier.setName("进货商");
		TreeItem<CustomerInfoVO> supplierRootItem=new TreeItem<CustomerInfoVO>(supplier);
		
		CustomerInfoVO consumer=new CustomerInfoVO();
		consumer.setName("销售商");
		TreeItem<CustomerInfoVO> consumerRootItem=new TreeItem<CustomerInfoVO>(consumer);
		
		rootItem.getChildren().add(supplierRootItem);
		rootItem.getChildren().add(consumerRootItem);
		
		CustomerInfoVO supplierVIP1=new CustomerInfoVO();
		supplierVIP1.setName(CustomerLevelEnum.VIP1.toString());
		TreeItem<CustomerInfoVO> supplierVIP1RootItem=new TreeItem<CustomerInfoVO>(supplierVIP1);
		
		CustomerInfoVO supplierVIP2=new CustomerInfoVO();
		supplierVIP2.setName(CustomerLevelEnum.VIP2.toString());
		TreeItem<CustomerInfoVO> supplierVIP2RootItem=new TreeItem<CustomerInfoVO>(supplierVIP2);
		
		CustomerInfoVO supplierVIP3=new CustomerInfoVO();
		supplierVIP3.setName(CustomerLevelEnum.VIP3.toString());
		TreeItem<CustomerInfoVO> supplierVIP3RootItem=new TreeItem<CustomerInfoVO>(supplierVIP3);
		
		CustomerInfoVO supplierVIP4=new CustomerInfoVO();
		supplierVIP4.setName(CustomerLevelEnum.VIP4.toString());
		TreeItem<CustomerInfoVO> supplierVIP4RootItem=new TreeItem<CustomerInfoVO>(supplierVIP4);
		
		CustomerInfoVO supplierVIP5=new CustomerInfoVO();
		supplierVIP5.setName(CustomerLevelEnum.VIP5.toString());
		TreeItem<CustomerInfoVO> supplierVIP5RootItem=new TreeItem<CustomerInfoVO>(supplierVIP5);
		
		supplierRootItem.getChildren().add(supplierVIP1RootItem);
		supplierRootItem.getChildren().add(supplierVIP2RootItem);
		supplierRootItem.getChildren().add(supplierVIP3RootItem);
		supplierRootItem.getChildren().add(supplierVIP4RootItem);
		supplierRootItem.getChildren().add(supplierVIP5RootItem);
		
		CustomerInfoVO consumerVIP1=new CustomerInfoVO();
		consumerVIP1.setName(CustomerLevelEnum.VIP1.toString());
		TreeItem<CustomerInfoVO> consumerVIP1RootItem=new TreeItem<CustomerInfoVO>(consumerVIP1);
		
		CustomerInfoVO consumerVIP2=new CustomerInfoVO();
		consumerVIP2.setName(CustomerLevelEnum.VIP2.toString());
		TreeItem<CustomerInfoVO> consumerVIP2RootItem=new TreeItem<CustomerInfoVO>(consumerVIP2);
		
		CustomerInfoVO consumerVIP3=new CustomerInfoVO();
		consumerVIP3.setName(CustomerLevelEnum.VIP3.toString());
		TreeItem<CustomerInfoVO> consumerVIP3RootItem=new TreeItem<CustomerInfoVO>(consumerVIP3);
		
		CustomerInfoVO consumerVIP4=new CustomerInfoVO();
		consumerVIP4.setName(CustomerLevelEnum.VIP4.toString());
		TreeItem<CustomerInfoVO> consumerVIP4RootItem=new TreeItem<CustomerInfoVO>(consumerVIP4);
		
		CustomerInfoVO consumerVIP5=new CustomerInfoVO();
		consumerVIP5.setName(CustomerLevelEnum.VIP5.toString());
		TreeItem<CustomerInfoVO> consumerVIP5RootItem=new TreeItem<CustomerInfoVO>(consumerVIP5);
		
		consumerRootItem.getChildren().add(consumerVIP1RootItem);
		consumerRootItem.getChildren().add(consumerVIP2RootItem);
		consumerRootItem.getChildren().add(consumerVIP3RootItem);
		consumerRootItem.getChildren().add(consumerVIP4RootItem);
		consumerRootItem.getChildren().add(consumerVIP5RootItem);
		
		for(int i=0;i<customers.size();i++){
			CustomerInfoVO cus=customers.get(i);
			String type=cus.getType();
			CustomerLevelEnum level=cus.getLevel();
			
			TreeItem<CustomerInfoVO> item=new TreeItem<CustomerInfoVO>(cus);
			
			if(type.equals("进货商")){
				switch (level){
				case VIP1:
					supplierVIP1RootItem.getChildren().add(item);
					break;
				case VIP2:
					supplierVIP2RootItem.getChildren().add(item);
					break;
				case VIP3:
					supplierVIP3RootItem.getChildren().add(item);
					break;
				case VIP4:
					supplierVIP4RootItem.getChildren().add(item);
					break;
				case VIP5:
					supplierVIP5RootItem.getChildren().add(item);
					break;
				}
			}else if(type.equals("销售商")){
				switch (level){
				case VIP1:
					consumerVIP1RootItem.getChildren().add(item);
					break;
				case VIP2:
					consumerVIP2RootItem.getChildren().add(item);
					break;
				case VIP3:
					consumerVIP3RootItem.getChildren().add(item);
					break;
				case VIP4:
					consumerVIP4RootItem.getChildren().add(item);
					break;
				case VIP5:
					consumerVIP5RootItem.getChildren().add(item);
					break;
				}
			}
		}
		
		customerTable.setRoot(rootItem);
		
		customerNameColumn.setCellValueFactory(
		          (TreeTableColumn.CellDataFeatures<CustomerInfoVO, String> param) -> 
		          new ReadOnlyStringWrapper(param.getValue().getValue().getName())
		);
		
		customerIdColumn.setCellValueFactory(
		          (TreeTableColumn.CellDataFeatures<CustomerInfoVO, String> param) -> 
		          new ReadOnlyStringWrapper(param.getValue().getValue().getId())
		);
		
		customerChooseColumn.setCellValueFactory(new Callback<TreeTableColumn.CellDataFeatures<CustomerInfoVO, CheckBox>, ObservableValue<CheckBox>>(){

			@Override
			public ObservableValue<CheckBox> call(CellDataFeatures<CustomerInfoVO, CheckBox> param) {
				CheckBox checkBox=new CheckBox();
				
				TreeItem<CustomerInfoVO> thisCus=param.getValue();
				
				if(!thisCus.isLeaf()){
					return null;
				}
				
				checkBox.selectedProperty().addListener(new ChangeListener<Boolean>(){

					@Override
					public void changed(ObservableValue<? extends Boolean> observable, Boolean oldValue,
							Boolean newValue) {
						if(!isChoosed){
							listItems.clear();
							isChoosed=true;
						}
						
						Iterator<SalesDetailsListSalesBillVO> it=listItems.iterator();
						
						if(oldValue==false&&newValue==true){
							
							for(int i=0;i<billInfos.size();i++){
								SalesDetailsListSalesBillVO bill=billInfos.get(i);
								String cusId=bill.getCustomerId();
								
								if(thisCus.getValue().getId().equals(cusId)&&!isExist(bill.getBillId(), bill.getCommodityName())){
									listItems.add(bill);
								}
							}
						}else if(oldValue==true&&newValue==false){
							
							while(it.hasNext()){
								SalesDetailsListSalesBillVO bill=it.next();
								String cusId=bill.getCustomerId();
							
								if(thisCus.getValue().getId().equals(cusId)){
									it.remove();
								}
							}
							
						}
						
					}
					
				});
				
				return new SimpleObjectProperty<CheckBox>(checkBox);
			}
			
		});
		
			//业务员
		ArrayList<UserVO> wareKeepers=null;
		ArrayList<UserVO> salesmen=null;
		ArrayList<UserVO> financialStaffs=null;
		ArrayList<UserVO> managers=null;
		try {
			wareKeepers=salesDetailsListBl.getWareKeeperList();
			salesmen=salesDetailsListBl.getSalesmanList();
			financialStaffs=salesDetailsListBl.getFinancialStaffList();
			managers=salesDetailsListBl.getManagerList();
		} catch (RemoteException e1) {
			e1.printStackTrace();
		}
		
		UserVO rootVO=new UserVO();
		rootVO.setName("所有员工");
		TreeItem<UserVO> userRootItem=new TreeItem<UserVO>(rootVO);
		UserVO wareRootVO=new UserVO();
		wareRootVO.setName("仓库管理员");
		TreeItem<UserVO> wareKeeperItems=new TreeItem<UserVO>(wareRootVO);
		UserVO salesRootVO=new UserVO();
		salesRootVO.setName("销售进货员");
		TreeItem<UserVO> salesmanItems=new TreeItem<UserVO>(salesRootVO);
		UserVO financialRootVO=new UserVO();
		financialRootVO.setName("财务人员");
		TreeItem<UserVO> financialStaffItems=new TreeItem<UserVO>(financialRootVO);
		UserVO managerRootVO=new UserVO();
		managerRootVO.setName("总经理");
		TreeItem<UserVO> managerItems=new TreeItem<UserVO>(managerRootVO);
		userRootItem.getChildren().add(wareKeeperItems);
		userRootItem.getChildren().add(salesmanItems);
		userRootItem.getChildren().add(financialStaffItems);
		userRootItem.getChildren().add(managerItems);
		
		userTable.setRoot(userRootItem);
		
		for(int i=0;i<wareKeepers.size();i++){
			UserVO each=wareKeepers.get(i);
			TreeItem<UserVO> eachItem=new TreeItem<UserVO>(each);
			wareKeeperItems.getChildren().add(eachItem);
		}
		
		for(int i=0;i<salesmen.size();i++){
			UserVO each=salesmen.get(i);
			TreeItem<UserVO> eachItem=new TreeItem<UserVO>(each);
			salesmanItems.getChildren().add(eachItem);
		}
		
		for(int i=0;i<financialStaffs.size();i++){
			UserVO each=financialStaffs.get(i);
			TreeItem<UserVO> eachItem=new TreeItem<UserVO>(each);
			financialStaffItems.getChildren().add(eachItem);
		}
		
		for(int i=0;i<managers.size();i++){
			UserVO each=managers.get(i);
			TreeItem<UserVO> eachItem=new TreeItem<UserVO>(each);
			managerItems.getChildren().add(eachItem);
		}
		
		userNameColumn.setCellValueFactory(
		          (TreeTableColumn.CellDataFeatures<UserVO, String> param) -> 
		          new ReadOnlyStringWrapper(param.getValue().getValue().getName())
		);
		
		userIdColumn.setCellValueFactory(
		          (TreeTableColumn.CellDataFeatures<UserVO, String> param) -> 
		          new ReadOnlyStringWrapper(param.getValue().getValue().getId())
		);
		
		userChooseColumn.setCellValueFactory(new Callback<TreeTableColumn.CellDataFeatures<UserVO, CheckBox>, ObservableValue<CheckBox>>(){

			@Override
			public ObservableValue<CheckBox> call(CellDataFeatures<UserVO, CheckBox> param) {
				CheckBox checkBox=new CheckBox();
				
				TreeItem<UserVO> thisUser=param.getValue();
				
				if(!thisUser.isLeaf()){
					return null;
				}
				
				checkBox.selectedProperty().addListener(new ChangeListener<Boolean>(){

					@Override
					public void changed(ObservableValue<? extends Boolean> observable, Boolean oldValue,
							Boolean newValue) {
						if(!isChoosed){
							listItems.clear();
							isChoosed=true;
						}
						
						Iterator<SalesDetailsListSalesBillVO> it=listItems.iterator();
						
						if(oldValue==false&&newValue==true){
							
							for(int i=0;i<billInfos.size();i++){
								SalesDetailsListSalesBillVO bill=billInfos.get(i);
								String userId=bill.getOperatorId();
								
								if(thisUser.getValue().getId().equals(userId)&&!isExist(bill.getBillId(), bill.getCommodityName())){
									listItems.add(bill);
								}
							}
						}else if(oldValue==true&&newValue==false){
							
							while(it.hasNext()){
								SalesDetailsListSalesBillVO bill=it.next();
								String userId=bill.getOperatorId();
							
								if(thisUser.getValue().getId().equals(userId)){
									it.remove();
								}
							}
							
						}
						
					}
					
				});
				
				return new SimpleObjectProperty<CheckBox>(checkBox);
			}
			
		});
		
		//表单Table初始化
			//获取单据列表
		SalesDetailsListVO list=null;
		
		try {
			list=salesDetailsListBl.getSalesDetailsList();
		} catch (RemoteException e) {
			e.printStackTrace();
		}
		
		billInfos=list.getSalesBills();
		
		listItems.addAll(billInfos);
		
			//绑定列
		dateColumn.setCellValueFactory(
				new PropertyValueFactory<SalesDetailsListSalesBillVO, String>("date"));
		commodityNameColumn.setCellValueFactory(
				new PropertyValueFactory<SalesDetailsListSalesBillVO, String>("commodityName"));
		typeColumn.setCellValueFactory(
				new PropertyValueFactory<SalesDetailsListSalesBillVO, String>("commodityType"));
		numberColumn.setCellValueFactory(
				new PropertyValueFactory<SalesDetailsListSalesBillVO, String>("number"));
		unitPriceColumn.setCellValueFactory(
				new PropertyValueFactory<SalesDetailsListSalesBillVO, String>("unitPrice"));
		sumColumn.setCellValueFactory(
				new PropertyValueFactory<SalesDetailsListSalesBillVO, String>("sum"));
		
		listTable.setItems(listItems);
	}
	
	@FXML
	protected void backToDefaultButtonListener() throws Exception{
		listItems.clear();
		listItems.addAll(billInfos);
		isChoosed=false;
	}
	
	@FXML
	protected void dateOkButtonListener() throws Exception{

		if(!isChoosed){
			listItems.clear();
			isChoosed=true;
		}
		
		for(int i=0;i<listItems.size();i++){
			Iterator<SalesDetailsListSalesBillVO> it=listItems.iterator();
			
			while(it.hasNext()){
				SalesDetailsListSalesBillVO bill=it.next();
				if(!isInDateRange(bill)){
					it.remove();
				}
			}
			
		} 
		
		for(int i=0;i<billInfos.size();i++){
			SalesDetailsListSalesBillVO bill=billInfos.get(i);
			
			if(isInDateRange(bill)&&!isExist(bill.getBillId(), bill.getCommodityName()))
				listItems.add(bill);
		}
		
	}
	
	@FXML
	protected void wareHouseOkButtonListener() throws Exception{
		String wareChosed=wareHouseTextField.getText();
		
		if(!isChoosed){
			listItems.clear();
			isChoosed=true;
		}
		
		Iterator<SalesDetailsListSalesBillVO> it=listItems.iterator();
		
		while(it.hasNext()){
			SalesDetailsListSalesBillVO bill=it.next();
			String ware=bill.getWare();
			
			if(!ware.equals(wareChosed)){
				it.remove();
			}
		}
		
		for(int i=0;i<billInfos.size();i++){
			SalesDetailsListSalesBillVO bill=billInfos.get(i);
			String ware=bill.getWare();
			
				
			if(ware.equals(wareChosed)&&!isExist(bill.getBillId(),bill.getCommodityName())){
				listItems.add(bill);
			}
		}
	}
	
	@FXML
	protected void exportButtonListener() throws Exception{
		
		HSSFWorkbook book=new HSSFWorkbook();
		HSSFSheet sheet=book.createSheet();
		HSSFRow title=sheet.createRow(0);
		
		HSSFCellStyle style=book.createCellStyle();
		style.setAlignment(HorizontalAlignment.CENTER);
		
		//标题
		HSSFCell titleCell=title.createCell(0);
		titleCell.setCellStyle(style);
		Date date=new Date();
		String time=date.getDate();
		titleCell.setCellValue("销售明细表    "+time);
		sheet.addMergedRegion(new CellRangeAddress(0,0,0,5));
		
		//属性
		HSSFRow row1=sheet.createRow(1);
		
		HSSFCell attribute1=row1.createCell(0);
		attribute1.setCellStyle(style);
		attribute1.setCellValue("时间");
		HSSFCell attribute2=row1.createCell(1);
		attribute2.setCellStyle(style);
		attribute2.setCellValue("商品名");
		HSSFCell attribute3=row1.createCell(2);
		attribute3.setCellStyle(style);
		attribute3.setCellValue("型号");
		HSSFCell attribute4=row1.createCell(3);
		attribute4.setCellStyle(style);
		attribute4.setCellValue("数量");
		HSSFCell attribute5=row1.createCell(4);
		attribute5.setCellStyle(style);
		attribute5.setCellValue("单价");
		HSSFCell attribute6=row1.createCell(5);
		attribute6.setCellStyle(style);
		attribute6.setCellValue("总额");
		
		//数据
		int rowIndex=2;
		for(int i=0;i<listItems.size();i++){
			SalesDetailsListSalesBillVO bill=listItems.get(i);
			
			HSSFRow row=sheet.createRow(rowIndex);
			
			HSSFCell cell1=row.createCell(0);
			cell1.setCellValue(bill.getDate());
			cell1.setCellStyle(style);
			HSSFCell cell2=row.createCell(1);
			cell2.setCellValue(bill.getCommodityName());
			cell2.setCellStyle(style);
			HSSFCell cell3=row.createCell(2);
			cell3.setCellValue(bill.getCommodityType());
			cell3.setCellStyle(style);
			HSSFCell cell4=row.createCell(3);
			cell4.setCellValue(bill.getNumber());
			cell4.setCellStyle(style);
			HSSFCell cell5=row.createCell(4);
			cell5.setCellValue(bill.getUnitPrice());
			cell5.setCellStyle(style);
			HSSFCell cell6=row.createCell(5);
			cell6.setCellValue(bill.getSum());
			cell6.setCellStyle(style);
			
			rowIndex++;
		}
		
		FileChooser chooser=new FileChooser();
		chooser.setInitialFileName("销售明细表.xls");
		File file=chooser.showSaveDialog(stage);
		
		if(file!=null){
			FileOutputStream stream=new FileOutputStream(file);
			book.write(stream);
			stream.close();
		}
		
		book.close();
	}
	
	private boolean isInDateRange(SalesDetailsListSalesBillVO bill){
		String startDate=startDatePicker.getValue().toString();
		String[] startDateParts=startDate.split("-");
		
		int startYear=Integer.parseInt(startDateParts[0]);
		int startMonth=Integer.parseInt(startDateParts[1]);
		int startDay=Integer.parseInt(startDateParts[2]);
		
		//开始日期
		Date start=new Date(startYear, startMonth, startDay, 0, 0);
		
		String endDate=endDatePicker.getValue().toString();
		String[] endDateParts=endDate.split("-");
		
		int endYear=Integer.parseInt(endDateParts[0]);
		int endMonth=Integer.parseInt(endDateParts[1]);
		int endDay=Integer.parseInt(endDateParts[2]);
		
		//结束日期
		Date end=new Date(endYear, endMonth, endDay, 23, 59);
		
		String date=bill.getDate();
				
		String[] dateParts=date.split("\\.");
				
		int dateYear=Integer.parseInt(dateParts[0]);
		int dateMonth=Integer.parseInt(dateParts[1]);
		int dateDay=Integer.parseInt(dateParts[2]);
		
		//本单据日期
		Date current=new Date(dateYear, dateMonth, dateDay, 12, 0);
				
		if((current.compareTo(start)>=0)&&(current.compareTo(end)<=0))
			return true;
		else
			return false;
	}
	
	private boolean isExist(String billId, String comName){
		Iterator<SalesDetailsListSalesBillVO> it=listItems.iterator();
		
		while(it.hasNext()){
			SalesDetailsListSalesBillVO bill=it.next();
			String id=bill.getBillId();
			String commodityName=bill.getCommodityName();
			
			if(billId.equals(id)&&comName.equals(commodityName))
				return true;
		}
			
		return false;
	}
	
	@FXML
	protected void backButtonListener(){
		if(pos==UserPositionEnum.FINANCE){
			FinancialStaffMainUiStarter starter=new FinancialStaffMainUiStarter();
			starter.mainUi();
		}else{
			ManagerMainUiStarter starter=new ManagerMainUiStarter();
			starter.start();	
		}
	}
}
