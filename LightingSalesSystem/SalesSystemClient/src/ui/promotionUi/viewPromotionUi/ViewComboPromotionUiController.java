package ui.promotionUi.viewPromotionUi;

import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.HashMap;

import assistant.exception.Myexception;
import assistant.utility.Date;
import blService.promotionBlService.ComboStrategyBlService;
import blStubs.promotionBlStubs.ComboStrategyBlService_Stub;
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
import javafx.scene.text.Text;
import rmi.remoteHelper.RemoteHelperFactory;
import ui.promotionUi.PromotionUiController;
import ui.promotionUi.bLobj.Goods;
import vo.ComboStrategyVO;
import vo.GiftVO;
import vo.GoodsVO;

public class ViewComboPromotionUiController implements PromotionUiForGiftsUiController{
	private ComboStrategyVO comboStrategyVO ;//此界面要显示的comboStrategy信息
	private ObservableList<GiftVO> gifts = FXCollections.observableArrayList();

	/***************************************************************************
     *                                                                         *
     * 界面显示元素                                                     *
     *                                                                         *
     **************************************************************************/
	//界面显示的textField
	@FXML
	private Text nameText ;
	@FXML
	private Text startDateText;
	@FXML
	private Text endDateText;
	@FXML
	private Text discountText;	
	@FXML
	private Text totalPriceText;
	@FXML
	private TextArea remarkTextArea;
	
	/****************************************************************************************/	
	
	
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
		}catch (Myexception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}  catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
	}
	
	private void initGiftsList(){
		gifts.addAll(comboStrategyVO.getComboCommodityList());
	}
	
	//给新增赠品用的两个接口
	@Override
	public ObservableList<Goods> getGoodsList(){
		return goodsList;
	}
	
	@Override
	public ObservableList<GiftVO> getGiftList(){
		return gifts;
	}
	
	/****************************************************************************************/

	
	/***************************************************************************
     *                                                                         *
     * 页面内退出按钮监听器                                                   *
     *                                                                         *
     **************************************************************************/
	
	//确定取消按钮
	@FXML
	protected void quitButtonListener(Event event){
		((Node)event.getSource()).getScene().getWindow().hide();
	}
	/****************************************************************************************/
	
	
	/**
	 * 根据填入信息生成用户促销策略
	 * */
	private void setComboStrategyVoText(){
		Date startDate = comboStrategyVO.getStartDate();
		startDateText.setText(startDate.getYMDDate());
		
		Date enDate = comboStrategyVO.getEndDate();
		endDateText.setText(enDate.getYMDDate());
		
		nameText.setText(comboStrategyVO.getName());
		
		discountText.setText(comboStrategyVO.getDiscount()*100+"");
		
		double totalPrice=0;
		for(GiftVO gift:gifts){
			totalPrice+= gift.getTotalPrice();
		}
		totalPriceText.setText(totalPrice+"");
		
		remarkTextArea.setEditable(false);
		remarkTextArea.setText(comboStrategyVO.getRemark());
	}
	
	/****************************************************************************************/

	
	/***************************************************************************
     *                                                                         *
     * controller的初始化和预先设置                                                *
     *                                                                         *
     **************************************************************************/
	/**
	 * starter预先设置
	 * */
	protected void init(){
		initGiftsList();
		initGoodsList();
		setGiftsTableView();
		setComboStrategyVoText();
	}
	/**
	 * starter预先设置
	 * */
	protected void setComboStrategy(ComboStrategyVO vo){
		this.comboStrategyVO=vo;
	}
	
	/****************************************************************************************/
	
	
	/***************************************************************************
     *                                                                         *
     * 赠品tableColumn的显示                                                      *
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


	private void setGiftsTableView(){
		setGiftNameTableColumn();
		
		setGiftIdTableColumn();
	
		setGiftAmountTableColumn();
		
		setGiftPriceTableColumn();
		
		setGiftTotalPriceTableColumn();
		
		setGiftShowTableColumn();
		
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
		giftAmountTableColumn.setCellValueFactory(cellDataFeatures->{
			int amount= cellDataFeatures.getValue().getGiftAmount();
			ObservableValue<Number> giftAmount = new SimpleIntegerProperty(amount);
			return giftAmount;
		});
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
				ViewGiftUiStarter starter = new ViewGiftUiStarter(this);
				starter.startAndShowGoods(cellDataFeatures.getValue());
			});
			ObservableValue<Button> btn = new SimpleObjectProperty(button);
			return btn;
		});
	}
	

}
