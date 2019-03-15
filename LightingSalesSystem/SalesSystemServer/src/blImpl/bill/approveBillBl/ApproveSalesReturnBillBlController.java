package blImpl.bill.approveBillBl;

import java.rmi.RemoteException;
import java.util.ArrayList;

import assistant.convertors.SalesmanBillsPOVOConvertor;
import assistant.convertors.UserInfoPOVOConvertor;
import assistant.type.BillStateEnum;
import assistant.utility.Date;
import blInteract.billBlInteract.BillBlInteractServiceFactory;
import blInteract.billBlInteract.SalesReturnBillBlService;
import blService.billService.approveBillBlService.ApproveSalesReturnBillBlService;
import po.SalesReturnBillPO;
import po.UserInfoPO;
import vo.SalesReturnBillVO;
import vo.UserInfoVO;

/**
 * 单据的审批，包括得到需要审批单据列表，和pass deny单据
 * @author 张傲  161250193
 * @version 2017.12.3
 */

public class ApproveSalesReturnBillBlController implements ApproveSalesReturnBillBlService{

    private SalesReturnBillBlService service 
    	= new BillBlInteractServiceFactory().getSalesReturnBillBlService();//现金费用单相关操作
	
    /**
	 * 得到需要审批的单据列表
	 * @return ArrayList<SalesReturnBillVO> 需要审批的单据列表
	 * @throws RemoteException
	 */
	@Override
	public ArrayList<SalesReturnBillVO> getBillsList() throws RemoteException {
		ArrayList<SalesReturnBillPO> list = service.getBillListByState(BillStateEnum.TBD);
		ArrayList<SalesReturnBillVO> targetList =new ArrayList<SalesReturnBillVO>();
		for(SalesReturnBillPO po: list){
			SalesReturnBillVO vo = SalesmanBillsPOVOConvertor.salesReturnBillPOtoVO(po) ;
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
	public boolean passBill(SalesReturnBillVO billVO) throws RemoteException {
		SalesReturnBillPO billPO = SalesmanBillsPOVOConvertor.salesReturnBillVOtoPO(billVO);
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
	public boolean denyBill(SalesReturnBillVO billVO) throws RemoteException {
		SalesReturnBillPO billPO = SalesmanBillsPOVOConvertor.salesReturnBillVOtoPO(billVO);
		service.denyBill(billPO);
		return true;
	}

}
