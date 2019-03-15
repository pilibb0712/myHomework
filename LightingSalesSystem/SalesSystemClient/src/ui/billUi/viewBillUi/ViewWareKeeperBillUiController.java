/**
 * 查看仓库管理员制定的单据界面，包括库存报警单、库存报损单、库存报警单的界面
 * @author BeibeiZhang
 * @version 2017.11.19
 *
 */
package ui.billUi.viewBillUi;

import java.rmi.RemoteException;

import blService.billService.viewBillBlService.ViewWareKeeperBillBlService;
import blStubs.billStubs.viewBillBlStubs.ViewWareKeeperBillBlService_Stub;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import rmi.remoteHelper.CommodityRemoteHelper;
import rmi.remoteHelper.ViewBillRemoteHelper;
import vo.InventoryLossBillVO;
import vo.InventoryOverBillVO;
import vo.InventoryWarningBillVO;

public class ViewWareKeeperBillUiController {
	@FXML private Text billNameText;
	@FXML private Text BillID;
	@FXML private Text createDateText;
	@FXML private Text Operator;
	@FXML private Text GoodsName;
	@FXML private Text GoodsID;
	@FXML private Text GoodsType;
	@FXML private Text GoodsPrice;
	@FXML private Text SysInvenAmount;
	@FXML private Text RealOrWarningLabel;
	@FXML private Text RealOrWarningAmount;
	@FXML private Text dAmountLabel;
	@FXML private Text dAmount;

	@FXML private Label billStateLabel;
	@FXML private Text approverText;
	@FXML private Text executorText;
	@FXML private Text approveDateText;
	@FXML private Text finishDateText;
	@FXML private TextArea approverCommentArea;

	private Stage billStage;
	private ViewWareKeeperBillBlService viewInvenBill=ViewBillRemoteHelper.getInstance().getViewWareKeeperBillBlService();

	@FXML protected void confirmTheBill(){
       billStage.close();
	}

	public void setInventoryWarningBill(Stage stage,String billID){
	//可以在上一个界面调这个方法，将数据取来之后设置好bill内容，然后显示
	//库存报警单界面
		billStage=stage;
		InventoryWarningBillVO bill=new InventoryWarningBillVO();
		try{
		bill=viewInvenBill.getInventoryWarningBill(billID);
		}catch(RemoteException e){
			e.printStackTrace();
		}
		if(bill.getCategoryEnum().toChineseString()!=null){
			billNameText.setText(bill.getCategoryEnum().toChineseString());
			}
		if(bill.getId()!=null){
			BillID.setText(bill.getId());
		}
		if(bill.getCreateDate().getDate()!=null){
			createDateText.setText(bill.getCreateDate().getDate());
		}
		if((bill.getCreater().getName()!=null)&&(bill.getCreater().getId()!=null)){
			Operator.setText(bill.getCreater().getName()+" "+bill.getCreater().getId());
		}
		if(bill.getGoodsName()!=null){
			GoodsName.setText(bill.getGoodsName());
		}
		if(bill.getGoodsID()!=null){
			GoodsID.setText(bill.getGoodsID());
		}
		if(bill.getGoodsType()!=null){
			GoodsType.setText(bill.getGoodsType());
		}
		if(bill.getGoodsPrice()!=null){
			GoodsPrice.setText(bill.getGoodsPrice());
		}
		if(bill.getSystemAmount()!=null){
		    SysInvenAmount.setText(bill.getSystemAmount());
		}
		RealOrWarningLabel.setText("警戒值：");
		if(bill.getWarningAmount()!=null){
			RealOrWarningAmount.setText(bill.getWarningAmount());
		}
		dAmountLabel.setText("缺损值：");
		if(bill.getLossAmount()!=null){
			dAmount.setText(bill.getLossAmount());
		}
		if(bill.getBillStateEnum()!=null){
		billStateLabel.setText(bill.getBillStateEnum().toString());
		}
		if(bill.getApprover()!=null){
			if(bill.getApprover().getName()!=null){
		      approverText.setText(bill.getApprover().getName());
			}
		}
		if(bill.getExecutor()!=null){
			if(bill.getExecutor().getName()!=null){
				executorText.setText(bill.getExecutor().getName());
				}
		}
		if(bill.getApproveDate()!=null){
		if(bill.getApproveDate().getDate()!=null){
		approveDateText.setText(bill.getApproveDate().getDate());
		}
		}
		if(bill.getFinishDate()!=null){
		if(bill.getFinishDate().getDate()!=null){
			finishDateText.setText(bill.getFinishDate().getDate());
		}
		}
		if(bill.getApproverComment()!=null){
		approverCommentArea.setText(bill.getApproverComment());
		}

	}

	public void setInventoryLossBill(Stage stage,String billID){
	//库存报损单界面
		billStage=stage;
		InventoryLossBillVO bill=new InventoryLossBillVO();
		try{
		bill=viewInvenBill.getInventoryLossBill(billID);
		}catch(RemoteException e){
			e.printStackTrace();
		}
		if(bill.getCategoryEnum().toChineseString()!=null){
			billNameText.setText(bill.getCategoryEnum().toChineseString());
			}
		if(bill.getId()!=null){
			BillID.setText(bill.getId());
		}
		if(bill.getCreateDate().getDate()!=null){
			createDateText.setText(bill.getCreateDate().getDate());
		}
		if((bill.getCreater().getName()!=null)&&(bill.getCreater().getId()!=null)){
			Operator.setText(bill.getCreater().getName()+" "+bill.getCreater().getId());
		}
		if(bill.getGoodsName()!=null){
			GoodsName.setText(bill.getGoodsName());
		}
		if(bill.getGoodsID()!=null){
			GoodsID.setText(bill.getGoodsID());
		}
		if(bill.getGoodsType()!=null){
			GoodsType.setText(bill.getGoodsType());
		}
		if(bill.getGoodsPrice()!=null){
			GoodsPrice.setText(bill.getGoodsPrice());
		}
		if(bill.getSystemAmount()!=null){
		    SysInvenAmount.setText(bill.getSystemAmount());
		}
		RealOrWarningLabel.setText("警戒值：");
		if(bill.getRealAmount()!=null){
			RealOrWarningAmount.setText(bill.getRealAmount());
		}
		dAmountLabel.setText("缺损值：");
		if(bill.getLossAmount()!=null){
			dAmount.setText(bill.getLossAmount());
		}
		if(bill.getBillStateEnum()!=null){
		billStateLabel.setText(bill.getBillStateEnum().toString());
		}
		if(bill.getApprover()!=null){
			if(bill.getApprover().getName()!=null){
		      approverText.setText(bill.getApprover().getName());
			}
		}
		if(bill.getExecutor()!=null){
			if(bill.getExecutor().getName()!=null){
				executorText.setText(bill.getExecutor().getName());
				}
		}
		if(bill.getApproveDate()!=null){
		if(bill.getApproveDate().getDate()!=null){
		approveDateText.setText(bill.getApproveDate().getDate());
		}
		}
		if(bill.getFinishDate()!=null){
		if(bill.getFinishDate().getDate()!=null){
			finishDateText.setText(bill.getFinishDate().getDate());
		}
		}
		if(bill.getApproverComment()!=null){
		approverCommentArea.setText(bill.getApproverComment());
		}

	}

	public void setInventoryOverBill(Stage stage,String billID){
	//库存报溢单
		billStage=stage;
		InventoryOverBillVO bill=new InventoryOverBillVO();
		try{
		bill=viewInvenBill.getInventoryOverBill(billID);
		}catch(RemoteException e){
			e.printStackTrace();
		}
		if(bill.getCategoryEnum().toChineseString()!=null){
			billNameText.setText(bill.getCategoryEnum().toChineseString());
			}
		if(bill.getId()!=null){
			BillID.setText(bill.getId());
		}
		if(bill.getCreateDate().getDate()!=null){
			createDateText.setText(bill.getCreateDate().getDate());
		}
		if((bill.getCreater().getName()!=null)&&(bill.getCreater().getId()!=null)){
			Operator.setText(bill.getCreater().getName()+" "+bill.getCreater().getId());
		}
		if(bill.getGoodsName()!=null){
			GoodsName.setText(bill.getGoodsName());
		}
		if(bill.getGoodsID()!=null){
			GoodsID.setText(bill.getGoodsID());
		}
		if(bill.getGoodsType()!=null){
			GoodsType.setText(bill.getGoodsType());
		}
		if(bill.getGoodsPrice()!=null){
			GoodsPrice.setText(bill.getGoodsPrice());
		}
		if(bill.getSystemAmount()!=null){
		    SysInvenAmount.setText(bill.getSystemAmount());
		}
		RealOrWarningLabel.setText("警戒值：");
		if(bill.getRealAmount()!=null){
			RealOrWarningAmount.setText(bill.getRealAmount());
		}
		dAmountLabel.setText("缺损值：");
		if(bill.getOverAmount()!=null){
			dAmount.setText(bill.getOverAmount());
		}
		if(bill.getBillStateEnum()!=null){
		billStateLabel.setText(bill.getBillStateEnum().toString());
		}
		if(bill.getApprover()!=null){
			if(bill.getApprover().getName()!=null){
		      approverText.setText(bill.getApprover().getName());
			}
		}
		if(bill.getExecutor()!=null){
			if(bill.getExecutor().getName()!=null){
				executorText.setText(bill.getExecutor().getName());
				}
		}
		if(bill.getApproveDate()!=null){
		if(bill.getApproveDate().getDate()!=null){
		approveDateText.setText(bill.getApproveDate().getDate());
		}
		}
		if(bill.getFinishDate()!=null){
		if(bill.getFinishDate().getDate()!=null){
			finishDateText.setText(bill.getFinishDate().getDate());
		}
		}
		if(bill.getApproverComment()!=null){
		approverCommentArea.setText(bill.getApproverComment());
		}

	}


}
