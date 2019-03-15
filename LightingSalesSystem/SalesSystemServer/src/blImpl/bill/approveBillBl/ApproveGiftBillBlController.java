package blImpl.bill.approveBillBl;

import java.rmi.RemoteException;
import java.util.ArrayList;

import assistant.convertors.GiftBillPOVOConvertor;
import assistant.type.BillStateEnum;
import blInteract.billBlInteract.BillBlInteractServiceFactory;
import blInteract.billBlInteract.GiftBillBlService;
import blService.billService.approveBillBlService.ApproveGiftBillBlService;
import po.GiftBillPO;
import vo.GiftBillVO;

/**
 * 单据的审批，包括得到需要审批单据列表，和pass deny单据
 * @author 张傲  161250193
 * @version 2017.12.3
 */

public class ApproveGiftBillBlController implements ApproveGiftBillBlService{

    private GiftBillBlService service 
    	= new BillBlInteractServiceFactory().getGiftBillBlService();//现金费用单相关操作
	
    /**
	 * 得到需要审批的单据列表
	 * @return ArrayList<GiftBillVO> 需要审批的单据列表
	 * @throws RemoteException
	 */
	@Override
	public ArrayList<GiftBillVO> getBillsList() throws RemoteException {
		ArrayList<GiftBillPO> list = service.getBillListByState(BillStateEnum.TBD);
		ArrayList<GiftBillVO> targetList =new ArrayList<GiftBillVO>();
		for(GiftBillPO po: list){
			GiftBillVO vo = GiftBillPOVOConvertor.giftBillPOtoVO(po) ;
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
	public boolean passBill(GiftBillVO billVO) throws RemoteException {
		GiftBillPO billPO = GiftBillPOVOConvertor.giftBillVOtoPO(billVO);
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
	public boolean denyBill(GiftBillVO billVO) throws RemoteException {
		GiftBillPO billPO = GiftBillPOVOConvertor.giftBillVOtoPO(billVO);
		service.denyBill(billPO);
		return true;
	}
}
