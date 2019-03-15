package blImpl.bill.approveBillBl;

import java.rmi.RemoteException;
import java.util.ArrayList;

import assistant.convertors.SalesmanBillsPOVOConvertor;
import assistant.convertors.UserInfoPOVOConvertor;
import assistant.type.BillStateEnum;
import assistant.utility.Date;
import blInteract.billBlInteract.BillBlInteractServiceFactory;
import blInteract.billBlInteract.PurchaseBillBlService;
import blInteract.financeInteract.BankAccountSumUpdateBlService;
import blInteract.financeInteract.FinanceBlFactory;
import blService.billService.approveBillBlService.ApprovePurchaseBillBlService;
import po.PurchaseBillPO;
import po.UserInfoPO;
import vo.PurchaseBillVO;
import vo.UserInfoVO;

/**
 * 单据的审批，包括得到需要审批单据列表，和pass deny单据
 * @author 张傲  161250193
 * @version 2017.12.3
 */

public class ApprovePurchaseBillBlController implements ApprovePurchaseBillBlService{

    private PurchaseBillBlService service 
    	= new BillBlInteractServiceFactory().getPurchaseBillBlService();//现金费用单相关操作
	
    /**
	 * 得到需要审批的单据列表
	 * @return ArrayList<PurchaseBillVO> 需要审批的单据列表
	 * @throws RemoteException
	 */
	@Override
	public ArrayList<PurchaseBillVO> getBillsList() throws RemoteException {
		ArrayList<PurchaseBillPO> list = service.getBillListByState(BillStateEnum.TBD);
		ArrayList<PurchaseBillVO> targetList =new ArrayList<PurchaseBillVO>();
		for(PurchaseBillPO po: list){
			PurchaseBillVO vo = SalesmanBillsPOVOConvertor.purchaseBillPOtoVO(po) ;
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
	public boolean passBill(PurchaseBillVO billVO) throws RemoteException {
		PurchaseBillPO billPO = SalesmanBillsPOVOConvertor.purchaseBillVOtoPO(billVO);
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
	public boolean denyBill(PurchaseBillVO billVO) throws RemoteException {
		PurchaseBillPO billPO = SalesmanBillsPOVOConvertor.purchaseBillVOtoPO(billVO);
		service.denyBill(billPO);
		return true;
	}

}
