/**
 * 
 */
package ui.salesUi;


import java.rmi.RemoteException;
import java.util.ArrayList;

import assistant.type.CustomerLevelEnum;
import blService.salesBlService.SalesBlService;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import rmi.remoteHelper.RemoteHelperFactory;
import rmi.remoteHelper.SalesRemoteHelper;
import ui.salesUi.salesBl.ComboStrategy;
import ui.salesUi.salesBl.TotalStrategy;
import ui.salesUi.salesBl.UserStrategy;
import vo.ComboStrategyVO;
import vo.SalesGoodsVO;
import vo.TotalStrategyVO;
import vo.UserStrategyVO;

/**
 * @author cosx
 *
 */
public class PromotionUiController {

	private Stage stage;
	
	NewSalesBillUiController controller;
	
	@FXML
	private GridPane pane;
	
	@FXML
	private Label totalStrategyDiscountLabel;
	
	@FXML
	private Label totalStrategyCouponLabel;
	
	@FXML
	private Label totalStrategyGiftLabel;
	
	@FXML
	private Label totalStrategyAllLabel;
	
	@FXML
	private Button totalStrategyButton;
	
	@FXML
	private Label userStrategyDiscountLabel;
	
	@FXML
	private Label userStrategyCouponLabel;
	
	@FXML
	private Label userStrategyGiftLabel;
	
	@FXML
	private Label userStrategyAllLabel;
	
	@FXML
	private Button userStrategyButton;
	
	@FXML
	private Label comboStrategyDiscountLabel;
	
	@FXML
	private Label comboStrategyAllLabel;
	
	@FXML
	private Button comboStrategyButton;	
	
	private SalesRemoteHelper remoteHelper=RemoteHelperFactory.getSalesRemoteHelper();
	
	private SalesBlService service=remoteHelper.getSalesBlService();
	
	private TotalStrategyVO tSVo;
	
	private UserStrategyVO usVo;
	
	/**
	 * @param stage
	 */
	public void setStage(Stage stage) {
		this.stage=stage;
	}

	/**
	 * @param salesBillUiController
	 * @param goodsVOs 
	 * @param level 
	 * @param consumption 
	 */
	public void init(NewSalesBillUiController salesBillUiController, double consumption, CustomerLevelEnum level, ArrayList<SalesGoodsVO> goodsVOs) {
		
		controller=salesBillUiController;
		
		TotalStrategyVO totalStrategyVO=new TotalStrategyVO();
		UserStrategyVO userStrategyVO=new UserStrategyVO();
		ComboStrategyVO comboStrategyVO=new ComboStrategyVO();
		try {
			if(service.getTotalStrategy(consumption)!=null) {
				totalStrategyVO=service.getTotalStrategy(consumption);
				showTotalStrategyContent(totalStrategyVO, consumption);
				tSVo=totalStrategyVO;
			}else {
				totalStrategyDiscountLabel.setText(0+"");
				totalStrategyCouponLabel.setText(0+"");
				totalStrategyGiftLabel.setText(0+"");
				totalStrategyAllLabel.setText(0+"");
			}
			
			if(service.getUserStrategy(level, consumption)!=null) {
				userStrategyVO=service.getUserStrategy(level, consumption);
				showUserStrategyContent(userStrategyVO, consumption);
				usVo=userStrategyVO;
			}else {
				userStrategyDiscountLabel.setText(0+"");
				userStrategyCouponLabel.setText(0+"");
				userStrategyGiftLabel.setText(0+"");
				userStrategyAllLabel.setText(0+"");
			}

			if(service.getComboStrategy(goodsVOs, consumption)!=null) {
				comboStrategyVO=service.getComboStrategy(goodsVOs, consumption);
				showComboStrategyContent(comboStrategyVO, consumption);
			}else {
				comboStrategyDiscountLabel.setText(0+"");
				comboStrategyAllLabel.setText(0+"");
			}
			
		} catch (RemoteException e) {
			e.printStackTrace();
		}
		
		
	}

	public void showTotalStrategyContent(TotalStrategyVO totalStrategyVO, double consumption) {
		TotalStrategy totalStrategy=new TotalStrategy(totalStrategyVO);
		totalStrategyDiscountLabel.setText(totalStrategy.calcDisCount(consumption)+"");
		totalStrategyCouponLabel.setText(totalStrategy.calcCoupon()+"");
		totalStrategyGiftLabel.setText(totalStrategy.calcGiftValue()+"");
		totalStrategyAllLabel.setText(totalStrategy.calcTotalValue(consumption)+"");
	}
	
	public void showUserStrategyContent(UserStrategyVO userStrategyVO, double consumption) {
		UserStrategy userStrategy=new UserStrategy(userStrategyVO);
		userStrategyDiscountLabel.setText(userStrategy.calcDisCount(consumption)+"");
		userStrategyCouponLabel.setText(userStrategy.calcCoupon()+"");
		userStrategyGiftLabel.setText(userStrategy.calcGiftValue()+"");
		userStrategyAllLabel.setText(userStrategy.calcTotalValue(consumption)+"");
	}
	
	public void showComboStrategyContent(ComboStrategyVO comboStrategyVO, double consumption) {
		ComboStrategy comboStrategy=new ComboStrategy(comboStrategyVO);
		comboStrategyDiscountLabel.setText(comboStrategy.calcDisCount(consumption)+"");
		comboStrategyAllLabel.setText(comboStrategy.calcTotalValue(consumption)+"");
	}
	
	@FXML
	public void totalButtonListener() {
		int discountRow=1;
		int discountColumn=1;
		double discount=getData(discountRow, discountColumn);
		
		int couponRow=1;
		int couponColumn=2;
		double coupon=getData(couponRow, couponColumn);
		
		try {
			service.generateGiftBillByTotalStrategy(tSVo);
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		changeData(discount, coupon);
	}
	
	@FXML
	public void userButtonListener() {
		int discountRow=2;
		int discountColumn=1;
		double discount=getData(discountRow, discountColumn);
		
		int couponRow=2;
		int couponColumn=2;
		double coupon=getData(couponRow, couponColumn);
		
		try {
			service.generateGiftBillByUserStrategy(usVo);
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		changeData(discount, coupon);
	}
	
	@FXML
	public void comboButtonListener() {
		int discountRow=3;
		int discountColumn=1;
		double discount=getData(discountRow, discountColumn);
		
		changeData(discount, 0);
	}
	
	public double getData(int row, int column) {
		Node result=null;
		ObservableList<Node> childrens=pane.getChildren();
		
		for(Node node:childrens) {
			if(GridPane.getRowIndex(node)==row&&GridPane.getColumnIndex(node)==column) {
				result=node;
				break;
			}
		}
		
		Label label=(Label)result;
		if(!label.getText().equals("")) {
			double data=Double.parseDouble(label.getText());
			return data;
		}else {
			return 0;
		}
	}
	
	public void changeData(double discount,double coupon) {
		controller.replaceDiscount(discount, coupon);
		stage.close();
	}
	
}






























