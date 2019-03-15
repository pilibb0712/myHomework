/**
 * 
 */
package ui.billUi.viewBillUi;

import java.rmi.RemoteException;
import java.util.ArrayList;
import blService.billService.viewBillBlService.ViewSalsemanBillBlService;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.cell.PropertyValueFactory;
import rmi.remoteHelper.RemoteHelperFactory;
import rmi.remoteHelper.ViewBillRemoteHelper;
import vo.SalesBillVO;
import vo.SalesGoodsVO;

/**
 * @author 王宁一
 *
 */
public class ViewSalesmanSalesBillUiController {

	@FXML
	private Label billStateLabel;
	
	@FXML
	private Label categoryLabel;
	
	@FXML
	private Label billIdLabel;
	
	@FXML
	private Label creatDateLabel;
	
	@FXML
	private Label creatorLabel;
	
	@FXML
	private Label customerLabel;
	
	@FXML
	private Label wareLabel;
	
	@FXML
	private Label oriMoneyLabel;
	
	@FXML
	private Label discountLabel;
	
	@FXML
	private Label couponLabel;
	
	@FXML
	private Label moneyLabel;
	
	@FXML
	private TableView<SalesGoodsVO> goodsInfoTable;
	
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
	private TextArea creatorCommentTextArea;
	
	@FXML
	private Label approverLabel;
	
	@FXML
	private Label executorLabel;
	
	@FXML
	private Label approveDateLabel;
	
	@FXML
	private Label finishDateLabel;
	
	@FXML
	private Label businessManLabel;
	
	@FXML
	private TextArea approverCommentText;
	
	private ViewBillRemoteHelper remoteHelper=RemoteHelperFactory.getViewBillRemoteHelper();
	
	private ViewSalsemanBillBlService service=remoteHelper.getViewSalsemanBillBlService();
	
	private ObservableList<SalesGoodsVO> goods=FXCollections.observableArrayList();
	
	public void viewSalesBill(String category, String idString) {
		//state待用
		categoryLabel.setText(category);
		billIdLabel.setText(idString);
		SalesBillVO salesBill=new SalesBillVO();
		try {
			salesBill=service.getSalesBill(idString);
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if(salesBill.getBusinessman()!=null) {
			businessManLabel.setText(salesBill.getBusinessman().getName());
		}
		creatDateLabel.setText(salesBill.getCreateDate().getDate());
		creatorLabel.setText(salesBill.getCreater().getName());
		if(salesBill.getCustomer()!=null) {
			customerLabel.setText(salesBill.getCustomer().getName());
		}		
		wareLabel.setText(salesBill.getWare()+"");
		oriMoneyLabel.setText(salesBill.getOriMoney()+"");
		discountLabel.setText(salesBill.getDiscount()+"");
		couponLabel.setText(salesBill.getCoupon()+"");
		moneyLabel.setText(salesBill.getMoney()+"");
		
		goodsInfoTable.setItems(goods);
		ArrayList<SalesGoodsVO> salesGoodsInfo=salesBill.getGoodsList();
		goods.addAll(salesGoodsInfo);
		
		goodsIdColumn.setCellValueFactory(new PropertyValueFactory<SalesGoodsVO,String>("goodsId"));
		
		goodsNameColumn.setCellValueFactory(new PropertyValueFactory<SalesGoodsVO,String>("goodsName"));
		
		goodsTypeColumn.setCellValueFactory(new PropertyValueFactory<SalesGoodsVO,String>("goodsType"));
		
		goodsNumColumn.setCellValueFactory(new PropertyValueFactory<SalesGoodsVO,Integer>("number"));
		
		goodsUnitPriceColumn.setCellValueFactory(new PropertyValueFactory<SalesGoodsVO,Double>("unitPrice"));
		
		goodsSumPriceColumn.setCellValueFactory(new PropertyValueFactory<SalesGoodsVO,Double>("sum"));
		
		goodsCommentColumn.setCellValueFactory(new PropertyValueFactory<SalesGoodsVO,String>("comment"));
		
		
		if(salesBill.getBillStateEnum()!=null) {
			String state;
			switch (salesBill.getBillStateEnum().toString()) {
			case "done":
				state="已完成";
				break;
			case "denied":
				state="不通过";
				break;
			case "todo":
				state="未完成";
				break;
			case "tbd":
				state="待审批";
				break;
			case "draft":
				state="草稿";
				break;
			default:
				state="不显示";
				break;
			}
			billStateLabel.setText(state);
		}
		if(salesBill.getCreaterComment()!=null) {
			creatorCommentTextArea.setText(salesBill.getCreaterComment());
		}
		if(salesBill.getApprover()!=null) {
			approverLabel.setText(salesBill.getApprover().getName());
		}
		if(salesBill.getApproveDate()!=null) {
			approveDateLabel.setText(salesBill.getApproveDate().getDate());
		}
		if(salesBill.getApproverComment()!=null) {
			approverCommentText.setText(salesBill.getApproverComment());
		}
		if(salesBill.getExecutor()!=null) {
			executorLabel.setText(salesBill.getExecutor().getName());
		}
		if(salesBill.getFinishDate()!=null) {
			finishDateLabel.setText(salesBill.getFinishDate().getDate());
		}
		
	}
	
	
	
}


































