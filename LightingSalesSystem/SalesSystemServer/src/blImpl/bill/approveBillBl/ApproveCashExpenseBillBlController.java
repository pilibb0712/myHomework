package blImpl.bill.approveBillBl;

import java.rmi.RemoteException;
import java.util.ArrayList;

import assistant.convertors.FinanceBillsPOVOConvertor;
import assistant.type.BillStateEnum;
import blInteract.billBlInteract.BillBlInteractServiceFactory;
import blInteract.billBlInteract.CashExpenseBillBlService;
import blService.billService.approveBillBlService.ApproveCashExpenseBillBlService;
import po.CashExpenseBillPO;
import vo.CashExpenseBillVO;

/**
 * 单据的审批，包括得到需要审批单据列表，和pass deny单据
 * @author 张傲  161250193
 * @version 2017.12.3
 */

public class ApproveCashExpenseBillBlController implements ApproveCashExpenseBillBlService{

    private CashExpenseBillBlService service 
    	= new BillBlInteractServiceFactory().getCashExpenseBillBlService();//现金费用单相关操作
	
    /**
	 * 得到需要审批的单据列表
	 * @return ArrayList<CashExpenseBillVO> 需要审批的单据列表
	 * @throws RemoteException
	 */
	@Override
	public ArrayList<CashExpenseBillVO> getBillsList() throws RemoteException {
		ArrayList<CashExpenseBillPO> list = service.getBillListByState(BillStateEnum.TBD);
		ArrayList<CashExpenseBillVO> targetList =new ArrayList<CashExpenseBillVO>();
		for(CashExpenseBillPO po: list){
			CashExpenseBillVO vo = FinanceBillsPOVOConvertor.cashExpenseBillPOtoVO(po) ;
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
	public boolean passBill(CashExpenseBillVO billVO) throws RemoteException {
		CashExpenseBillPO billPO = FinanceBillsPOVOConvertor.cashExpenseBillVOtoPO(billVO);
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
	public boolean denyBill(CashExpenseBillVO billVO) throws RemoteException {
		CashExpenseBillPO billPO = FinanceBillsPOVOConvertor.cashExpenseBillVOtoPO(billVO);
		service.denyBill(billPO);
		return true;
	}


}
