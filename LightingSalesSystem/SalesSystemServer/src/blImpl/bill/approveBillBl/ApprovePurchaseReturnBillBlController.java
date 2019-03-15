package blImpl.bill.approveBillBl;

import java.rmi.RemoteException;
import java.util.ArrayList;

import assistant.convertors.SalesmanBillsPOVOConvertor;
import assistant.convertors.UserInfoPOVOConvertor;
import assistant.type.BillStateEnum;
import assistant.utility.Date;
import blInteract.billBlInteract.BillBlInteractServiceFactory;
import blInteract.billBlInteract.PurchaseReturnBillBlService;
import blService.billService.approveBillBlService.ApprovePurchaseReturnBillBlService;
import po.PurchaseReturnBillPO;
import po.UserInfoPO;
import vo.PurchaseReturnBillVO;
import vo.UserInfoVO;

/**
 * 单据的审批，包括得到需要审批单据列表，和pass deny单据
 * @author 张傲  161250193
 * @version 2017.12.3
 */

public class ApprovePurchaseReturnBillBlController implements ApprovePurchaseReturnBillBlService{

    private PurchaseReturnBillBlService service 
    	= new BillBlInteractServiceFactory().getPurchaseReturnBillBlService();//现金费用单相关操作
	
    /**
	 * 得到需要审批的单据列表
	 * @return ArrayList<PurchaseReturnBillVO> 需要审批的单据列表
	 * @throws RemoteException
	 */
	@Override
	public ArrayList<PurchaseReturnBillVO> getBillsList() throws RemoteException {
		ArrayList<PurchaseReturnBillPO> list = service.getBillListByState(BillStateEnum.TBD);
		ArrayList<PurchaseReturnBillVO> targetList =new ArrayList<PurchaseReturnBillVO>();
		for(PurchaseReturnBillPO po: list){
			PurchaseReturnBillVO vo = SalesmanBillsPOVOConvertor.purchaseReturnBillPOtoVO(po) ;
			targetList.add(vo);
		}
		return targetList;
	}

	  /**
	  * 根据Id通过单据的审批
	  * @param String billId单据的Id
	  * @param UserInfoVO approver 审批人信息
	  * @param String approverComment 审批人的批注 
	  * @throws RemoteException
	  */
	@Override
	public boolean passBill(PurchaseReturnBillVO billVO) throws RemoteException {
		PurchaseReturnBillPO billPO = SalesmanBillsPOVOConvertor.purchaseReturnBillVOtoPO(billVO);
		service.passBill(billPO);
		return true;
	}

	 /**
	  * 根据Id拒绝通过单据的审批
	  * @param String billId单据的Id
	  * @param UserInfoVO approver 审批人信息
	  * @param String approverComment 审批人的批注 
	  * @throws RemoteException
	  */
	@Override
	public boolean denyBill(PurchaseReturnBillVO billVO) throws RemoteException {
		PurchaseReturnBillPO billPO = SalesmanBillsPOVOConvertor.purchaseReturnBillVOtoPO(billVO);
		service.denyBill(billPO);
		return true;
	}

}
