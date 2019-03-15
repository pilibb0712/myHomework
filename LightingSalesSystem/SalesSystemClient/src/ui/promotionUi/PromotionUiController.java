package ui.promotionUi;

import java.net.URL;
import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.Optional;
import java.util.ResourceBundle;

import assistant.exception.Myexception;
import assistant.type.StrategyCategoryEnum;
import assistant.utility.Date;
import blService.promotionBlService.ComboStrategyBlService;
import blService.promotionBlService.TotalStrategyBlService;
import blService.promotionBlService.UserStrategyBlService;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Text;
import rmi.remoteHelper.RemoteHelperFactory;
import ui.mainUi.managerMainUi.ManagerMainUiStarter;
import ui.promotionUi.addPromotionUi.AddComboPromotionUiStarter;
import ui.promotionUi.addPromotionUi.AddTotalPromotionUiStarter;
import ui.promotionUi.addPromotionUi.AddUserPromotionUiStarter;
import ui.promotionUi.updatePromotionUi.UpdateComboPromotionUiStarter;
import ui.promotionUi.updatePromotionUi.UpdateTotalPromotionUiStarter;
import ui.promotionUi.updatePromotionUi.UpdateUserPromotionUiStarter;
import ui.promotionUi.viewPromotionUi.ViewComboPromotionUiStarter;
import ui.promotionUi.viewPromotionUi.ViewTotalPromotionUiStarter;
import ui.promotionUi.viewPromotionUi.ViewUserPromotionUiStarter;
import vo.ComboStrategyVO;
import vo.PromotionStrategyVO;
import vo.TotalStrategyVO;
import vo.UserInfoVO;
import vo.UserStrategyVO;

public class PromotionUiController implements Initializable{
	
	private PromotionUiController promotionUiController = this;
	 
	
	//显示当下处理promotion类型的text
	@FXML
	private Text promotionCategoryText;
	
	//新增按钮以及相关的新增handler
	@FXML
	private Button addPromotionButton;
	
	private EventHandler<MouseEvent> addUserPromotionHandler= new EventHandler<MouseEvent>() {
		private AddUserPromotionUiStarter starter;
		@Override
		public void handle(MouseEvent event) {
			starter = new AddUserPromotionUiStarter(promotionUiController);
			starter.start();
		}
	
	};
	private EventHandler<MouseEvent> addTotalPromotionHandler= new EventHandler<MouseEvent>() {
		private AddTotalPromotionUiStarter starter;
		@Override
		public void handle(MouseEvent event) {
			// TODO Auto-generated method stub
			starter= new AddTotalPromotionUiStarter(promotionUiController);
			starter.start();
		}
	};
	private EventHandler<MouseEvent> addComboPromotionHandler= new EventHandler<MouseEvent>() {
		private AddComboPromotionUiStarter starter;
		@Override
		public void handle(MouseEvent event) {
			// TODO Auto-generated method stub
			starter= new AddComboPromotionUiStarter(promotionUiController);
			starter.start();
		}
		
	};
	
	
	//tableview的各个column
	@FXML
	private TableView<PromotionStrategyVO> promotionTableView;
	
	@FXML 
	private TableColumn<PromotionStrategyVO, String> idTableColumn;
	@FXML 
	private TableColumn<PromotionStrategyVO, String> startDateTableColumn;
	@FXML
	private TableColumn<PromotionStrategyVO, String> endDateTableColumn;
	@FXML 
	private TableColumn<PromotionStrategyVO, Button> viewTableColumn;
	@FXML
	private TableColumn<PromotionStrategyVO, Button> updateTableColumn;
	@FXML
	private TableColumn<PromotionStrategyVO, Button> deleteTableColumn;
	
	
	
	//不同促销策略选择menuItem
	@FXML
	private MenuItem userPromotionMenuItem;
	
	@FXML
	private MenuItem totalPromotionMenuItem;
	
	@FXML
	private MenuItem comboPromotionMenuItem;
	
	//不同促销策略的list
	private ObservableList<PromotionStrategyVO> promotionStrategyVOs = FXCollections.observableArrayList();
	
	private ObservableList<UserStrategyVO> userStrategyVOs= FXCollections.observableArrayList();
	private ObservableList<TotalStrategyVO> totalStrategyVOs= FXCollections.observableArrayList();
	private ObservableList<ComboStrategyVO> comboStrategyVOs= FXCollections.observableArrayList();

	
	//不同促销策略的service
	private UserStrategyBlService userStrategyBlService =RemoteHelperFactory.getPromotionRemoteHelper().getUserStrategyBlService(); //TODO
	private TotalStrategyBlService totalStrategyBlService = RemoteHelperFactory.getPromotionRemoteHelper().getTotalStrategyBlService();//TODO
	private ComboStrategyBlService comboStrategyBlService = RemoteHelperFactory.getPromotionRemoteHelper().getComboStrategyBlService();//TODO
	
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		setTableColumn();
	}
	
	/**
	 * 用户促销策略menuItem的响应
	 * 用来设置menuItem相关配置以及显示
	 * */
	@FXML
	protected void userPromotionMenuItemListener(){
		//设置大标题
		promotionCategoryText.setText(StrategyCategoryEnum.USRESTRATEGY.toChineseString());
		//设置查改删列的按钮响应
		viewTableColumn.setCellValueFactory(cellDataFeature->{
			Button button = new Button("查看");
			button.setOnMouseClicked(e->{
				UserStrategyVO strategy =(UserStrategyVO) cellDataFeature.getValue();
				ViewUserPromotionUiStarter starter = new ViewUserPromotionUiStarter(strategy);
				starter.start();
			});
			button.setAlignment(Pos.CENTER);
			ObservableValue<Button> btn = new SimpleObjectProperty<Button>(button);
			return btn;
		});
		
		updateTableColumn.setCellValueFactory(cellDataFeature->{
			Button button = new Button("修改");
			button.setOnMouseClicked(e->{
				UserStrategyVO strategy =(UserStrategyVO) cellDataFeature.getValue();
				UpdateUserPromotionUiStarter starter = new UpdateUserPromotionUiStarter(this, strategy);
				starter.start();
				//TODO 更新页面Starter
			});
			button.setAlignment(Pos.CENTER);
			ObservableValue<Button> btn = new SimpleObjectProperty<Button>(button);
			return btn;
		});
		
		deleteTableColumn.setCellValueFactory(cellDataFeature->{
			UserStrategyVO strategy =(UserStrategyVO) cellDataFeature.getValue();
			Button button = new Button("删除");
			button.setOnMouseClicked(e->{
				Alert alert = new Alert(AlertType.CONFIRMATION,"您是否确定删除促销策略:"+strategy.getName());
				Optional<ButtonType> result =  alert.showAndWait();
				//如果确定则删除
				if(result.isPresent()&&result.get()==ButtonType.OK){
					try {
						userStrategyBlService.deleteStrategy(strategy.getId());
					}catch(Myexception e1){
						//TODO 自定义异常处理
						e1.printStackTrace();
					} catch (RemoteException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}
				userPromotionMenuItemListener();
			});
			button.setAlignment(Pos.CENTER);
			ObservableValue<Button> btn = new SimpleObjectProperty<Button>(button);
			return btn;
		});
		
		//更新促销策略list
		ArrayList<UserStrategyVO> list = new ArrayList<>();
		promotionStrategyVOs.clear();
		userStrategyVOs.clear();
		try {
			list = userStrategyBlService.getStrategyList();
		}catch(Myexception e1){
			//TODO 自定义异常处理
			e1.printStackTrace();
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			Alert alert = new Alert(AlertType.ERROR,e.getMessage());
			e.printStackTrace();
		}
		if(list!=null){userStrategyVOs.addAll(list);}
		promotionStrategyVOs.addAll(userStrategyVOs);
		promotionTableView.setItems(promotionStrategyVOs);
		promotionTableView.refresh();
		
		//设置新增按钮Handler
		addPromotionButton.setOnMouseClicked(addUserPromotionHandler);
	}
	
	@FXML
	protected void totalPromotionMenuItemListener(){
		//设置大标题
		promotionCategoryText.setText(StrategyCategoryEnum.TOTALSTRATEGY.toChineseString());
		//设置查改删列的按钮响应
		viewTableColumn.setCellValueFactory(cellDataFeature->{
			Button button = new Button("查看");
			button.setOnMouseClicked(e->{
				TotalStrategyVO strategy =(TotalStrategyVO) cellDataFeature.getValue();
				ViewTotalPromotionUiStarter starter =new ViewTotalPromotionUiStarter(strategy);
				starter.start();
				//TODO userPromotionUiStarter
			});
			button.setAlignment(Pos.CENTER);
			ObservableValue<Button> btn = new SimpleObjectProperty<Button>(button);
			return btn;
		});
		
		updateTableColumn.setCellValueFactory(cellDataFeature->{
			Button button = new Button("修改");
			button.setOnMouseClicked(e->{
				TotalStrategyVO strategy =(TotalStrategyVO) cellDataFeature.getValue();
				UpdateTotalPromotionUiStarter starter = new UpdateTotalPromotionUiStarter(this, strategy);
				starter.start();
				//TODO 更新页面Starter
			});
			button.setAlignment(Pos.CENTER);
			ObservableValue<Button> btn = new SimpleObjectProperty<Button>(button);
			return btn;
		});
		
		deleteTableColumn.setCellValueFactory(cellDataFeature->{
			TotalStrategyVO strategy =(TotalStrategyVO) cellDataFeature.getValue();
			Button button = new Button("删除");
			button.setOnMouseClicked(e->{
				Alert alert = new Alert(AlertType.CONFIRMATION,"您是否确定删除促销策略:"+strategy.getName());
				Optional<ButtonType> result =  alert.showAndWait();
				//如果确定则删除
				if(result.isPresent()&&result.get()==ButtonType.OK){
					try {
						totalStrategyBlService.deleteStrategy(strategy.getId());
					}catch(Myexception e1){
						//TODO 自定义异常处理
						e1.printStackTrace();
					} catch (RemoteException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}
				totalPromotionMenuItemListener();
			});
			button.setAlignment(Pos.CENTER);
			ObservableValue<Button> btn = new SimpleObjectProperty<Button>(button);
			return btn;
		});
		
		//更新促销策略list
		ArrayList<TotalStrategyVO> list = new ArrayList<>();
		promotionStrategyVOs.clear();
		totalStrategyVOs.clear();
		try {
			list = totalStrategyBlService.getStrategyList();
		}catch(Myexception e1){
			//TODO 自定义异常处理
			e1.printStackTrace();
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			Alert alert = new Alert(AlertType.ERROR,e.getMessage());
			e.printStackTrace();
		}
		if(list!=null){totalStrategyVOs.addAll(list);}
		promotionStrategyVOs.addAll(totalStrategyVOs);
		promotionTableView.refresh();
		promotionTableView.setItems(promotionStrategyVOs);
//		promotionTableView.refresh();
		//设置新增按钮Handler
		addPromotionButton.setOnMouseClicked(addTotalPromotionHandler);
	}
	

	@FXML
	protected void comboPromotionMenuItemListener(){
		//设置大标题
		promotionCategoryText.setText(StrategyCategoryEnum.COMBOSTRATEGY.toChineseString());
		//设置查改删列的按钮响应
		viewTableColumn.setCellValueFactory(cellDataFeature->{
			Button button = new Button("查看");
			button.setOnMouseClicked(e->{
				ComboStrategyVO strategy =(ComboStrategyVO) cellDataFeature.getValue();
				ViewComboPromotionUiStarter starter = new ViewComboPromotionUiStarter(strategy);
				starter.start();
				//TODO userPromotionUiStarter
			});
			button.setAlignment(Pos.CENTER);
			ObservableValue<Button> btn = new SimpleObjectProperty<Button>(button);
			return btn;
		});
		
		updateTableColumn.setCellValueFactory(cellDataFeature->{
			Button button = new Button("修改");
			button.setOnMouseClicked(e->{
				ComboStrategyVO strategy =(ComboStrategyVO) cellDataFeature.getValue();
				UpdateComboPromotionUiStarter starter = new UpdateComboPromotionUiStarter(this, strategy);
				starter.start();
				//TODO 更新页面Starter
			});
			button.setAlignment(Pos.CENTER);
			ObservableValue<Button> btn = new SimpleObjectProperty<Button>(button);
			return btn;
		});
		
		deleteTableColumn.setCellValueFactory(cellDataFeature->{
			ComboStrategyVO strategy =(ComboStrategyVO) cellDataFeature.getValue();
			Button button = new Button("删除");
			button.setOnMouseClicked(e->{
				Alert alert = new Alert(AlertType.CONFIRMATION,"您是否确定删除促销策略:"+strategy.getName());
				Optional<ButtonType> result =  alert.showAndWait();
				//如果确定则删除
				if(result.isPresent()&&result.get()==ButtonType.OK){
					try {
						comboStrategyBlService.deleteStrategy(strategy.getId());
					}catch(Myexception e1){
						//TODO 自定义异常处理
						e1.printStackTrace();
					} catch (RemoteException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}
				comboPromotionMenuItemListener();
			});
			button.setAlignment(Pos.CENTER);
			ObservableValue<Button> btn = new SimpleObjectProperty<Button>(button);
			return btn;
		});
		
		//更新促销策略list
		ArrayList<ComboStrategyVO> list = new ArrayList<>();
		promotionStrategyVOs.clear();
		comboStrategyVOs.clear();
		try {
			list = comboStrategyBlService.getStrategyList();
			System.out.println("listcombo");
		} catch(Myexception e1){
			//TODO 自定义异常处理
			e1.printStackTrace();
		}catch (RemoteException e) {
			// TODO Auto-generated catch block
			Alert alert = new Alert(AlertType.ERROR,e.getMessage());
			e.printStackTrace();
		}
		if(list!=null){comboStrategyVOs.addAll(list);}
		promotionStrategyVOs.addAll(comboStrategyVOs);
		promotionTableView.refresh();
		promotionTableView.setItems(promotionStrategyVOs);
//		promotionTableView.refresh();
		//设置新增按钮Handler
		addPromotionButton.setOnMouseClicked(addComboPromotionHandler);
	}
	
	//新增促销策略
	public void addUserStrategy(UserStrategyVO vo){
		try {
			userStrategyBlService.addStrategy(vo);
		}catch(Myexception e1){
			//TODO 自定义异常处理
			e1.printStackTrace();
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		userPromotionMenuItemListener();
	}
	
	public void addTotalStrategy(TotalStrategyVO vo){
		try {
			totalStrategyBlService.addStrategy(vo);
		} catch(Myexception e1){
			//TODO 自定义异常处理
			e1.printStackTrace();
		}catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		totalPromotionMenuItemListener();
	}
	
	public void addComboStrategy(ComboStrategyVO vo){
		try {
			comboStrategyBlService.addStrategy(vo);
		}catch(Myexception e1){
			//TODO 自定义异常处理
			e1.printStackTrace();
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		comboPromotionMenuItemListener();
	}
	
	//更新促销策略 
	public void updateUserStrategy(UserStrategyVO vo){
		try {
			userStrategyBlService.updateStrategy(vo);
		} catch(Myexception e1){
			//TODO 自定义异常处理
			e1.printStackTrace();
		}catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		userPromotionMenuItemListener();
	}	
	public void updateTotalStrategy(TotalStrategyVO vo){
		try {
			totalStrategyBlService.updateStrategy(vo);
		} catch(Myexception e1){
			//TODO 自定义异常处理
			e1.printStackTrace();
		}catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		totalPromotionMenuItemListener();
	}
	public void updateComboStrategy(ComboStrategyVO vo){
		try {
			comboStrategyBlService.updateStrategy(vo);
		}catch(Myexception e1){
			//TODO 自定义异常处理
			e1.printStackTrace();
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		comboPromotionMenuItemListener();
	}
	
	/**
	 * 设置tableView的id栏，时间栏，创建者栏
	 * */
	private void setTableColumn(){
		setIdTableColumn();
		setStartDateTableColumn();
		setEndDateTableColumn();
	}
	
	private void setIdTableColumn(){
		idTableColumn.setCellValueFactory(cellDataFeatures->{
			PromotionStrategyVO promotionStrategy = cellDataFeatures.getValue();
			String promotionId = promotionStrategy.getName(); 
			ObservableValue<String> id = new SimpleStringProperty(promotionId);
			return id;
		});
	}	
	private void setStartDateTableColumn(){
		startDateTableColumn.setCellValueFactory(cellDataFeatures->{
			PromotionStrategyVO promotionStrategy = cellDataFeatures.getValue();
			Date promotionDate = promotionStrategy.getStartDate(); 
			ObservableValue<String> date = new SimpleStringProperty(promotionDate.getYMDDate());
			return date;
		});
	}
	private void setEndDateTableColumn(){
		endDateTableColumn.setCellValueFactory(cellDataFeatures->{
			PromotionStrategyVO promotionStrategy = cellDataFeatures.getValue();
			Date promotionDate = promotionStrategy.getEndDate(); 
			ObservableValue<String> date = new SimpleStringProperty(promotionDate.getYMDDate());
			return date;
		});
	}
	
	@FXML
	protected void returnButtonListener(){
		ManagerMainUiStarter starter = new ManagerMainUiStarter();
		starter.start(new UserInfoVO());
	}
}
