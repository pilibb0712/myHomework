/**
 * 
 */
package ui.salesUi;

import java.rmi.RemoteException;
import java.util.ArrayList;

import blService.salesBlService.SalesBlService;
import blStubs.salesBlStubs.SalesBlService_Stub;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TableColumn.CellDataFeatures;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import javafx.util.Callback;
import rmi.remoteHelper.RemoteHelperFactory;
import rmi.remoteHelper.SalesRemoteHelper;
import vo.GoodsVO;

/**
 * @author cosx
 *
 */
public class ChooseUiController {
	private Stage stage;
	
	@FXML
	private TableView<GoodsVO> goodsChooseList;
	
	@FXML
	private TableColumn<GoodsVO, String> goodInfoColumn;
	
	@FXML
	private TableColumn<GoodsVO, Button> goodChooseColumn;

	private ObservableList<GoodsVO> goodsWaitedToChoose=FXCollections.observableArrayList();
	
	private SalesRemoteHelper remoteHelper=RemoteHelperFactory.getSalesRemoteHelper();
	
	private SalesBlService service=remoteHelper.getSalesBlService();
	
	//private SalesBlService service=new SalesBlService_Stub();
	
	public void setStage(Stage stage) {
		this.stage=stage;
	}
	
	public void SelectPurchaseCommodity(NewPurchaseBillUiController controller, int p) {
		
		ArrayList<GoodsVO> commodities=new ArrayList<>();
		
		try {
			commodities=service.CommoditySelect();
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		goodsChooseList.setItems(goodsWaitedToChoose);
		goodsWaitedToChoose.addAll(commodities);
		goodInfoColumn.setCellValueFactory(new PropertyValueFactory<GoodsVO,String>("name"));
		
		goodChooseColumn.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<GoodsVO,Button>, ObservableValue<Button>>() {

			@Override
			public ObservableValue<Button> call(CellDataFeatures<GoodsVO, Button> param) {
				// TODO Auto-generated method stub
				GoodsVO goodsChoosed=param.getValue();
				Button ChooseButton=new Button("选择");
				ChooseButton.setOnAction(paramx->{
					controller.replaceGoodsData(p, goodsChoosed);
				stage.close();
				}
				);
				
				return new SimpleObjectProperty<Button>(ChooseButton);
			}
		});
	}
	
	public void SelectPurchaseReturnCommodity(NewPurchaseReturnBillUiController controller, int p) {
		ArrayList<GoodsVO> commodities=new ArrayList<>();
		
		try {
			commodities=service.CommoditySelect();
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		goodsChooseList.setItems(goodsWaitedToChoose);
		goodsWaitedToChoose.addAll(commodities);
		goodInfoColumn.setCellValueFactory(new PropertyValueFactory<GoodsVO,String>("name"));
		
		goodChooseColumn.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<GoodsVO,Button>, ObservableValue<Button>>() {

			@Override
			public ObservableValue<Button> call(CellDataFeatures<GoodsVO, Button> param) {
				// TODO Auto-generated method stub
				GoodsVO goodsChoosed=param.getValue();
				Button ChooseButton=new Button("选择");
				ChooseButton.setOnAction(paramx->{
					controller.replaceGoodsData(p, goodsChoosed);				
				stage.close();
				}
				);
				
				return new SimpleObjectProperty<Button>(ChooseButton);
			}
		});
	}
	
	public void SelectSalesCommodity(NewSalesBillUiController controller, int p) {
		ArrayList<GoodsVO> commodities=new ArrayList<>();
		
		try {
			commodities=service.CommoditySelect();
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		goodsChooseList.setItems(goodsWaitedToChoose);
		goodsWaitedToChoose.addAll(commodities);
		goodInfoColumn.setCellValueFactory(new PropertyValueFactory<GoodsVO,String>("name"));
		
		goodChooseColumn.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<GoodsVO,Button>, ObservableValue<Button>>() {

			@Override
			public ObservableValue<Button> call(CellDataFeatures<GoodsVO, Button> param) {
				// TODO Auto-generated method stub
				GoodsVO goodsChoosed=param.getValue();
				Button ChooseButton=new Button("选择");
				ChooseButton.setOnAction(paramx->{
				controller.replaceGoodsData(p, goodsChoosed);				
				stage.close();
				}
				);
				
				return new SimpleObjectProperty<Button>(ChooseButton);
			}
		});
	}
	
	public void SelectSalesReturnCommodity(NewSalesReturnBillUiController controller, int p) {
		ArrayList<GoodsVO> commodities=new ArrayList<>();
		
		try {
			commodities=service.CommoditySelect();
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		goodsChooseList.setItems(goodsWaitedToChoose);
		goodsWaitedToChoose.addAll(commodities);
		goodInfoColumn.setCellValueFactory(new PropertyValueFactory<GoodsVO,String>("name"));
		
		goodChooseColumn.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<GoodsVO,Button>, ObservableValue<Button>>() {

			@Override
			public ObservableValue<Button> call(CellDataFeatures<GoodsVO, Button> param) {
				// TODO Auto-generated method stub
				GoodsVO goodsChoosed=param.getValue();
				Button ChooseButton=new Button("选择");
				ChooseButton.setOnAction(paramx->{
				controller.replaceGoodsData(p, goodsChoosed);				
				stage.close();
				}
				);
				
				return new SimpleObjectProperty<Button>(ChooseButton);
			}
		});
	}
	
}
