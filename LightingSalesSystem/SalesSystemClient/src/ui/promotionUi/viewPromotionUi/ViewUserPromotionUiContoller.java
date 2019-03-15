package ui.promotionUi.viewPromotionUi;

import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.HashMap;

import javax.swing.text.View;

import assistant.exception.Myexception;
import assistant.type.CustomerLevelEnum;
import assistant.utility.Date;
import blService.promotionBlService.ComboStrategyBlService;
import blService.promotionBlService.UserStrategyBlService;
import blStubs.promotionBlStubs.ComboStrategyBlService_Stub;
import blStubs.promotionBlStubs.UserStrategyBlService_Stub;
import ui.uiAssistants.InputErrorMessageEnum;
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
import vo.ComboStrategyVO;
import vo.GiftVO;
import vo.GoodsVO;
import vo.UserStrategyVO;

public class ViewUserPromotionUiContoller implements PromotionUiForGiftsUiController{
	private UserStrategyVO userStrategyVO = new UserStrategyVO();//此界面要显示的userstrategy信息
	
	private HashMap<GiftVO, Goods> giftsGoodsMap = new HashMap<>();
	private ObservableList<GiftVO> gifts = FXCollections.observableArrayList();

	/***************************************************************************
     *                                                                         *
     * 界面显示元素                                                     *
     *                                                                         *
     **************************************************************************/
	//界面显示的textfield
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
	private Text customerLevelText;
	@FXML
	private Text couponText ;
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
	UserStrategyBlService userStrategyBlService
		= RemoteHelperFactory.getPromotionRemoteHelper().getUserStrategyBlService();// 用户促销策略的bl
	ObservableList<Goods> goodsList = FXCollections.observableArrayList();//维护所有的商品信息
	//初始化
	private void initGoodsList(){
		try {
			ArrayList<GoodsVO> vos = userStrategyBlService.getGoodsList();
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
		if(userStrategyVO.getGifts()==null){return;}
		gifts.addAll(userStrategyVO.getGifts());
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
	private void setUserStrategyVoText(){
		Date startDate = userStrategyVO.getStartDate();
		startDateText.setText(startDate.getYMDDate());
		
		Date enDate =userStrategyVO.getEndDate();
		endDateText.setText(enDate.getYMDDate());
		
		customerLevelText.setText(userStrategyVO.getCustomerLevel().toString());
		
		nameText.setText(userStrategyVO.getName());
		
		discountText.setText(userStrategyVO.getDiscount()*100+"");
		
		couponText.setText(userStrategyVO.getCoupon()+"");
		
		double totalPrice=0;
		for(GiftVO gift:gifts){
			totalPrice+= gift.getTotalPrice();
		}
		totalPriceText.setText(totalPrice+"");
		
		remarkTextArea.setEditable(false);
		remarkTextArea.setText(userStrategyVO.getRemark());
	}
	
	/****************************************************************************************/

	
	/***************************************************************************
     *                                                                         *
     * controller的初始化和预先设置                                                *
     *                                                                         *
     **************************************************************************/
	protected void init(){
		initGiftsList();
		initGoodsList();
		setGiftsTableView();
		setUserStrategyVoText();
	}
	/**
	 * starter预先设置
	 * */
	protected void setUserStrategy(UserStrategyVO vo){
		this.userStrategyVO=vo;
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
	@FXML
	private TableColumn<GiftVO, Button> giftDeleteTableColumn;

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
		giftTableView.setEditable(false);
		
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
				ViewGiftUiStarter starter = new ViewGiftUiStarter(this);
				starter.startAndShowGoods(cellDataFeatures.getValue());
			});
			ObservableValue<Button> btn = new SimpleObjectProperty(button);
			return btn;
		});
	}
	

}
