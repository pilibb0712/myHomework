package blImpl.bill.approveBillBl;

import java.rmi.RemoteException;
import java.util.ArrayList;

import assistant.convertors.CommodityPOVOConvertor;
import assistant.convertors.UserInfoPOVOConvertor;
import assistant.type.BillStateEnum;
import assistant.utility.Date;
import blInteract.billBlInteract.BillBlInteractServiceFactory;
import blInteract.billBlInteract.InventoryOverBillBlService;
import blService.billService.approveBillBlService.ApproveInventoryOverBillBlService;
import po.InventoryOverBillPO;
import po.UserInfoPO;
import vo.InventoryOverBillVO;
import vo.UserInfoVO;

/**
 * 单据的审批，包括得到需要审批单据列表，和pass deny单据
 * @author 张傲  161250193
 * @version 2017.12.3
 */

public class ApproveInventoryOverBillBlController implements ApproveInventoryOverBillBlService{

    private InventoryOverBillBlService service 
    	= new BillBlInteractServiceFactory().getInventoryOverBillBlService();//现金费用单相关操作
	
    /**
	 * 得到需要审批的单据列表
	 * @return ArrayList<InventoryOverBillVO> 需要审批的单据列表
	 * @throws RemoteException
	 */
	@Override
	public ArrayList<InventoryOverBillVO> getBillsList() throws RemoteException {
		ArrayList<InventoryOverBillPO> list = service.getBillListByState(BillStateEnum.TBD);
		ArrayList<InventoryOverBillVO> targetList =new ArrayList<InventoryOverBillVO>();
		for(InventoryOverBillPO po: list){
			InventoryOverBillVO vo = CommodityPOVOConvertor.inventoryOverBillPOtoVO(po) ;
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
	public boolean passBill(InventoryOverBillVO billVO) throws RemoteException {
		InventoryOverBillPO billPO = CommodityPOVOConvertor.inventoryOverBillVOtoPO(billVO); 
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
	public boolean denyBill(InventoryOverBillVO billVO) throws RemoteException {
		InventoryOverBillPO billPO = CommodityPOVOConvertor.inventoryOverBillVOtoPO(billVO); 
		service.denyBill(billPO);
		return true;
	}

	

}
