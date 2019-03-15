package ui.promotionUi.addPromotionUi;

import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.HashMap;

import assistant.exception.Myexception;
import assistant.utility.Date;
import blService.promotionBlService.ComboStrategyBlService;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.text.Text;
import javafx.util.converter.NumberStringConverter;
import rmi.remoteHelper.RemoteHelperFactory;
import ui.promotionUi.PromotionUiController;
import ui.promotionUi.bLobj.Goods;
import ui.uiAssistants.InputErrorMessageEnum;
import vo.ComboStrategyVO;
import vo.GiftVO;
import vo.GoodsVO;
import vo.TotalStrategyVO;

public class AddComboPromotionUiController implements PromotionUiForGiftsUiController{
	private PromotionUiController promotionUiController;//持有他的controller的引用
	private ComboStrategyVO comboStrategyVO = new ComboStrategyVO();//此界面要显示的userstrategy信息
	
	private HashMap<GiftVO, Goods> giftsGoodsMap = new HashMap<>();
	private ObservableList<GiftVO> gifts = FXCollections.observableArrayList();

	/***************************************************************************
     *                                                                         *
     * 界面显示元素                                                     *
     *                                                                         *
     **************************************************************************/
	//界面显示的textfield
	@FXML
	private TextField nameTextField ;
	@FXML
	private TextField startYearTextField;
	@FXML
	private TextField startMonthTextField;
	@FXML
	private TextField startDayTextField;
	@FXML
	private TextField endYearTextField;
	@FXML
	private TextField endMonthTextField;
	@FXML
	private TextField endDayTextField;
	@FXML
	private TextField discountTextField;	
	@FXML
	private Text totalPriceText;
	@FXML
	private TextArea remarkTextArea;
	
	//设置今天的预览促销时间段
	private void setTodayPromptText(){
		Date today = new Date();
		String todayString = today.getYMDDate();
		String[] ymd = todayString.split("\\.");
		startYearTextField.setPromptText(ymd[0]);
		startMonthTextField.setPromptText(ymd[1]);
		startDayTextField.setPromptText(ymd[2]);
		endYearTextField.setPromptText(ymd[0]);
		endMonthTextField.setPromptText(ymd[1]);
		endDayTextField.setPromptText(ymd[2]);
	}
	
	/****************************************************************************************/	
	
	/***************************************************************************
     *                                                                         *
     * 错误检查和提示                                                    *
     *                                                                         *
     **************************************************************************/
	//错误提示
	@FXML
	private Text startDateErrorText;
	@FXML
	private Text endDateErrorText;
	@FXML
	private Text nameErrorText;
	@FXML
	private Text discountErrorText;
	

	private boolean checkInput(){
		if(!checkNameTextField()){return false;}
		
		if(!checkStartDateTextField()){return false;}
		
		if(!checkEndDateTextField()){return false;}
		
		if(!checkDiscountTextField()){return false;}
		
		return true;
	}
	
	private boolean checkNameTextField(){
		String name = nameTextField.getText();
		if(name == null||name.trim().equals("")){
			nameTextField.setText("请输入");
			return false;
		}
		return true;
	}
	
	private boolean checkStartDateTextField(){
		try{
			if(startYearTextField.getText()==null||startMonthTextField.getText()==null
					||startDayTextField.getText()==null){
				//TODO
				startDateErrorText.setText(InputErrorMessageEnum.Uncompleted_Error.getErrorMessage());
				return false;
			}
			int year = Integer.parseInt(startYearTextField.getText());
			int month = Integer.parseInt(startMonthTextField.getText());
			int day = Integer.parseInt(startDayTextField.getText());
			Date startDate= new Date(year,month,day,00,00);
			comboStrategyVO.setStartDate(startDate);
		}catch (NumberFormatException e) {
			// TODO: handle exception
			startDateErrorText.setText(InputErrorMessageEnum.Time_Format_Error.getErrorMessage());
			return false;
		}
		startDateErrorText.setText("");
		return true;
	}
	
	private boolean checkEndDateTextField(){
		try{
			if(endYearTextField.getText()==null||endMonthTextField.getText()==null
					||endDayTextField.getText()==null){
				//TODO
				endDateErrorText.setText(InputErrorMessageEnum.Uncompleted_Error.getErrorMessage());
				return false;
			}
			int year = Integer.parseInt(endYearTextField.getText());
			int month = Integer.parseInt(endMonthTextField.getText());
			int  day = Integer.parseInt(endDayTextField.getText());
			Date endDate = new Date(year,month,day,00,00);
			if(endDate.compareTo(comboStrategyVO.getStartDate())<=0){
				//TODO 时间重叠
				endDateErrorText.setText(InputErrorMessageEnum.Time_End_Less_Than_Start.getErrorMessage());
				return false;
			}
		}catch(NumberFormatException e){
			//TODO 
			endDateErrorText.setText(InputErrorMessageEnum.Time_Format_Error.getErrorMessage());
			return false;
		}
		endDateErrorText.setText("");
		return true;
	}
	
	private boolean checkDiscountTextField(){
		try{
			String discountString = discountTextField.getText();
			
			if(discountString==null||discountString.trim().equals("")){
				discountString = "100";
			}
			double discount = Double.parseDouble(discountString)/100.0;
			if(discount>1||discount<0){
				//TODO
				discountErrorText.setText(InputErrorMessageEnum.Number_Out_Of_Bounds.getErrorMessage());
				return false;
			}
		}catch (NumberFormatException e) {
			// TODO: handle exception
			discountErrorText.setText(InputErrorMessageEnum.Number_Format_Error.getErrorMessage());
			return false;
		}
		return true;
	}
	
	
	
	/***************************************************************************
     *                                                                         *
     * 对商品新增界面接口                                                   *
     *                                                                         *
     **************************************************************************/
	//商品信息清单
	ComboStrategyBlService comboStrategyBlService
		= RemoteHelperFactory.getPromotionRemoteHelper().getComboStrategyBlService();// 用户促销策略的bl
	ObservableList<Goods> goodsList = FXCollections.observableArrayList();//维护所有的商品信息
	//初始化
	private void initGoodsList(){
		try {
			ArrayList<GoodsVO> vos = comboStrategyBlService.getGoodsList();
			for(GoodsVO vo:vos){
				goodsList.add(new Goods(vo));
			}
		} catch (Myexception e) {
			// TODO: handle exception
		}catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
	}
	//给新增赠品用的两个接口
	@Override
	public ObservableList<Goods> getGoodsList(){
		return goodsList;
	}
	@Override
	public void addGifts(ObservableList<GiftVO> vos , HashMap<GiftVO, Goods> map){
		giftsGoodsMap.putAll(map);
		gifts.addAll(vos);
	}
	

	
	/***************************************************************************
     *                                                                         *
     * 页面内确定取消按钮监听器                                                   *
     *                                                                         *
     **************************************************************************/
	@FXML
	protected void addGiftButtonListener(Event event){
		AddGiftUiStarter starter = new AddGiftUiStarter(this);
		starter.start();
	}
	
	//确定取消按钮
	@FXML
	protected void ensureButtonListener(Event event){
		boolean isSetComplemented= checkInput();
		if(!isSetComplemented){
			return;
		}
		generateComboStrategyVo();
		promotionUiController.addComboStrategy(comboStrategyVO);
		((Node)event.getSource()).getScene().getWindow().hide();
	}
	@FXML
	protected void cancelButtonListener(Event event){
		((Node)event.getSource()).getScene().getWindow().hide();
	}
	
	
	/**
	 * 根据填入信息生成用户促销策略
	 * */
	private void generateComboStrategyVo(){
		String name = nameTextField.getText();
		comboStrategyVO.setName(name);
		
		int year = Integer.parseInt(startYearTextField.getText());
		int month = Integer.parseInt(startMonthTextField.getText());
		int day = Integer.parseInt(startDayTextField.getText());
		Date startDate= new Date(year,month,day,00,00);
		comboStrategyVO.setStartDate(startDate);
	
		year = Integer.parseInt(endYearTextField.getText());
		month = Integer.parseInt(endMonthTextField.getText());
		day = Integer.parseInt(endDayTextField.getText());
		Date endDate = new Date(year,month,day,00,00);
		comboStrategyVO.setEndDate(endDate);
		
		double discount = Double.parseDouble(discountTextField.getText())/100.0;
		comboStrategyVO.setDiscount(discount);
		
		
		String remark = remarkTextArea.getText();
		comboStrategyVO.setRemark(remark);
		
		ArrayList<GiftVO> giftVOs = new ArrayList<>();
		giftVOs.addAll(gifts);
		comboStrategyVO.setComboCommodityList(giftVOs);
	}
	

	
	/***************************************************************************
     *                                                                         *
     * controller的初始化和预先设置                                                *
     *                                                                         *
     **************************************************************************/
	protected void init(){
		initGoodsList();
		setTodayPromptText();
		setGiftsTableView();
	}
	
	protected void setPromotionUiController(PromotionUiController promotionUiController){
		this.promotionUiController = promotionUiController;
	}

	
	
	/***************************************************************************
     *                                                                         *
     * 赠品tablecolumn的显示                                                      *
     *                                                                         *
     **************************************************************************/
	//赠品的信息tableView
	@FXML
	private TableView<GiftVO> giftTableView;
	@FXML
	private TableColumn<GiftVO, String> giftIdTableColumn;
	@FXML
	private TableColumn<GiftVO, String> giftNameTableColumn;
	@FXML
	private TableColumn<GiftVO,Number> giftPriceTableColumn;
	@FXML
	private TableColumn<GiftVO,Number> giftAmountTableColumn;
	@FXML
	private TableColumn<GiftVO,Number> giftTotalPriceTableColumn;
	@FXML
	private TableColumn<GiftVO, Button> giftShowTableColumn;
	@FXML
	private TableColumn<GiftVO, Button> giftDeleteTableColumn;

	private void setGiftsTableView(){
		setGiftNameTableColumn();
		
		setGiftIdTableColumn();
	
		setGiftAmountTableColumn();
		
		setGiftPriceTableColumn();
		
		setGiftTotalPriceTableColumn();
		
		setGiftShowTableColumn();
		
		setGiftDeleteTableColumn();
		
		giftTableView.setItems(gifts);
	}
	
	private void setGiftNameTableColumn(){
		giftNameTableColumn.setCellValueFactory(cellDataFeatures->{
			GiftVO vo = cellDataFeatures.getValue();
			String name = vo.getName();
			ObservableValue<String> giftName = new SimpleStringProperty(name);
			return giftName;
		});
	}
	
	private void setGiftIdTableColumn(){
		giftIdTableColumn.setCellValueFactory(cellDataFeatures->{
			GiftVO vo = cellDataFeatures.getValue();
			String id = vo.getId();
			ObservableValue<String> giftId = new SimpleStringProperty(id);
			return giftId;
		});
	}
	
	private void setGiftAmountTableColumn(){
		giftTableView.setEditable(true);
		
		giftAmountTableColumn.setCellValueFactory(cellDataFeatures->{
			int amount= cellDataFeatures.getValue().getGiftAmount();
			ObservableValue<Number> giftAmount = new SimpleIntegerProperty(amount);
			return giftAmount;
		});
		
		giftAmountTableColumn.setCellFactory(col->{
			TextFieldTableCell<GiftVO, Number> cell = new TextFieldTableCell<GiftVO, Number>(new NumberStringConverter()){
				@Override
				public void commitEdit(Number n){
					super.commitEdit(n);
					int i= this.getIndex();
					long giftAmount = (long)n;
					if(i<gifts.size()){
						gifts.get(i).setGiftAmount((int)giftAmount);
						//更新商品总价
						double totalPrice = 0;
						for(int j=0;j<gifts.size();j++){
							totalPrice += gifts.get(j).getTotalPrice();
						}
						totalPriceText.setText(totalPrice+"");
						giftTableView.refresh();
						return;
					}
				}
			};

			cell.setEditable(true);
			return cell;
		});
		giftAmountTableColumn.setEditable(true);
	}
	
	private void setGiftPriceTableColumn(){
		giftPriceTableColumn.setCellValueFactory(cellDataFeatures->{
			GiftVO vo = cellDataFeatures.getValue();
			double price = vo.getPrice();
			ObservableValue<Number> giftPrice = new SimpleDoubleProperty(price);
			return giftPrice;
		});
	}

	private void setGiftTotalPriceTableColumn(){
		giftTotalPriceTableColumn.setCellValueFactory(cellDataFeatures->{
			GiftVO vo = cellDataFeatures.getValue();
			double price = vo.getTotalPrice();
			ObservableValue<Number> giftPrice = new SimpleDoubleProperty(price);
			return giftPrice;
		});
	}
	
	private void setGiftShowTableColumn(){
		giftShowTableColumn.setCellValueFactory(cellDataFeatures->{
			Button button = new Button("查看");
			button.setOnMouseClicked(e->{
				AddGiftUiStarter starter = new AddGiftUiStarter(this);
				starter.startAndShowGoods(cellDataFeatures.getValue());
			});
			ObservableValue<Button> btn = new SimpleObjectProperty(button);
			return btn;
		});
	}
	
	private void setGiftDeleteTableColumn(){
		giftDeleteTableColumn.setCellValueFactory(cellDataFeatures->{
			Button button = new Button("删除");
			button.setOnMouseClicked(e->{
				GiftVO giftVO = cellDataFeatures.getValue();
				Goods goods=giftsGoodsMap.get(giftVO);
				goods.setAddedToGift(false);
				gifts.remove(giftVO);
			});
			ObservableValue<Button> btn = new SimpleObjectProperty(button);
			return btn;
		});
	}


}
