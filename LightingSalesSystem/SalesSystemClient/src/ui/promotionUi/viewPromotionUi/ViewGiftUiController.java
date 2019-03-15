package ui.promotionUi.viewPromotionUi;
import java.util.HashMap;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.input.MouseButton;
import javafx.scene.text.Text;
import ui.promotionUi.bLobj.Goods;
import vo.GiftVO;

public class ViewGiftUiController {
	HashMap<GiftVO, Goods> giftGoodsMap= new HashMap<>();
	private PromotionUiForGiftsUiController controller;//父界面的controller
	private ObservableList<Goods> goodsList ;//商品清单
	private ObservableList<GiftVO> giftsList = FXCollections.observableArrayList();//新增的赠品或者优惠商品
	private Goods currentShowGoods;//当下正在展示的商品
	
	
	/***************************************************************************
     *                                                                         *
     * 界面的商品信息显示                                                           *
     *                                                                         *
     **************************************************************************/
	@FXML
	private Text nameText;
	@FXML
	private Text idText;
	@FXML
	private Text classificationText;
	@FXML
	private Text amountText;
	@FXML
	private Text costPriceText;
	@FXML
	private Text sellPriceText;
	@FXML
	Button addButton;
	
	//设置商品信息显示

	private void showGoods(Goods goods){
		nameText.setText(goods.getName());
		idText.setText(goods.getId());
		classificationText.setText(goods.getClassification());
		amountText.setText(goods.getAmount()+"");
		costPriceText.setText(goods.getCostPrice()+"");
		sellPriceText.setText(goods.getSellingPrice()+"");
	}
	
	/****************************************************************************************/	
	
	
	/***************************************************************************
     *                                                                         *
     * 商品清单的ListView设置显示                                                           *
     *                                                                         *
     **************************************************************************/
	@FXML
	TableView<Goods> goodsTableView;
	@FXML
	TableColumn<Goods, String> goodsTableColumn;
	private void setGoodsTableView(){
		
		goodsTableColumn.setCellFactory(col->{
			TableCell<Goods,String> tableCell= new TableCell<Goods,String>(){
				@Override 
				protected void updateItem(String s,boolean empty){
					int i= this.getIndex();
					if(!empty){
						Goods goods =goodsList.get(i); 
						this.setText(goods.getName());
					}
				}
				
			};
			
			tableCell.setOnMouseClicked(e->{
				int i=0;
				if(e.getButton().equals(MouseButton.PRIMARY)
						&&e.getClickCount()==2
						&&(i=tableCell.getIndex())<goodsList.size()){
					currentShowGoods= goodsList.get(i);
					showGoods(currentShowGoods);
				}
			});
			return tableCell;
		});
		goodsTableView.setItems(goodsList);
	}
	/****************************************************************************************/
	
	
	/***************************************************************************
     *                                                                         *
     * 赠品清单的ListView设置显示                                                           *
     *                                                                         *
     **************************************************************************/
	@FXML
	TableView<GiftVO> giftsTableView;
	@FXML
	TableColumn<GiftVO, String> giftsTableColumn;
	
	private void setGiftsTableView(){	
		giftsTableColumn.setCellFactory(col->{
			TableCell<GiftVO,String> tableCell= new TableCell<GiftVO,String>(){
				@Override 
				protected void updateItem(String s,boolean empty){
					int i= this.getIndex();
					if(!empty&&i<giftsList.size()){
						GiftVO gift =giftsList.get(i); 
						this.setText(gift.getName());
					}
				}
				
			};
			//双击显示商品信息
			tableCell.setOnMouseClicked(e->{
				int i=0;
				if(e.getButton().equals(MouseButton.PRIMARY)
						&&e.getClickCount()==2
						&&(i=tableCell.getIndex())<giftsList.size()){
					GiftVO gift = giftsList.get(i);
					showGoods(giftGoodsMap.get(gift));
				}
			});
			
			
			ContextMenu contextMenu = new ContextMenu();
			MenuItem showMenuItem = new MenuItem("查看");
			showMenuItem.setOnAction(e->{
				int i= tableCell.getIndex();
				GiftVO gift =i<0?null:giftsList.get(i);
				if(gift!=null){
					Goods goods = giftGoodsMap.get(gift);
					showGoods(goods);
				}
			});
			
			contextMenu.getItems().add(showMenuItem);
			
			tableCell.setContextMenu(contextMenu);
			return tableCell;
		});
		
		giftsTableView.setItems(giftsList);
	}
	/****************************************************************************************/
	
	/***************************************************************************
     *                                                                         *
     * 退出按钮监听器                                                        *
     *                                                                         *
     **************************************************************************/
	@FXML
	protected void quitButtonListener(Event e){
		((Node)e.getSource()).getScene().getWindow().hide();
	}
	/****************************************************************************************/
	
	
	//controller的初始化方法
	protected void init(){
		setGoodsList();
		setGiftList();
		setGiftGoodsMap();
		
		setGoodsTableView();
		setGiftsTableView();
		
		if(currentShowGoods!=null){
			showGoods(currentShowGoods);
		}
	}
	
	
	//设置promotion controller
	protected void setViewPromotionUiController(PromotionUiForGiftsUiController controller){
		this.controller = controller;
	}
	protected void setCurrentShowGoods(GiftVO gift){
		for(int i=0;i<goodsList.size();i++){
			Goods goods = goodsList.get(i);
			if(gift.getId().equals(goods.getId())){
				showGoods(goods);
				return;
			}
		}
	}
	protected void setGiftList(){
		giftsList = controller.getGiftList();
	}
	
	private void setGoodsList(){
		goodsList = controller.getGoodsList();
	}
	
	private void setGiftGoodsMap(){
		for(GiftVO gift: giftsList){
			for(Goods goods: goodsList){
				if(goods.getId().equals(gift.getId())){
					giftGoodsMap.put(gift, goods);
				}
			}
		}
	}
}
