package blImpl.bill.approveBillBl;

import java.rmi.RemoteException;
import java.util.ArrayList;

import assistant.convertors.CommodityPOVOConvertor;
import assistant.convertors.InOutPOVOConvertor;
import assistant.convertors.UserInfoPOVOConvertor;
import assistant.type.BillStateEnum;
import assistant.utility.Date;
import blInteract.billBlInteract.BillBlInteractServiceFactory;
import blInteract.billBlInteract.InventoryLossBillBlService;
import blService.billService.approveBillBlService.ApproveInventoryLossBillBlService;
import po.InventoryLossBillPO;
import po.UserInfoPO;
import vo.InventoryLossBillVO;
import vo.UserInfoVO;

/**
 * 单据的审批，包括得到需要审批单据列表，和pass deny单据
 * @author 张傲  161250193
 * @version 2017.12.3
 */

public class ApproveInventoryLossBillBlController implements ApproveInventoryLossBillBlService{

    private InventoryLossBillBlService service 
    	= new BillBlInteractServiceFactory().getInventoryLossBillBlService();//现金费用单相关操作
	
    /**
	 * 得到需要审批的单据列表
	 * @return ArrayList<InventoryLossBillVO> 需要审批的单据列表
	 * @throws RemoteException
	 */
	@Override
	public ArrayList<InventoryLossBillVO> getBillsList() throws RemoteException {
		ArrayList<InventoryLossBillPO> list = service.getBillListByState(BillStateEnum.TBD);
		ArrayList<InventoryLossBillVO> targetList =new ArrayList<InventoryLossBillVO>();
		for(InventoryLossBillPO po: list){
			InventoryLossBillVO vo = CommodityPOVOConvertor.inventoryLossBillPOtoVO(po) ;
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
	public boolean passBill(InventoryLossBillVO billVO) throws RemoteException {
		InventoryLossBillPO billPO = CommodityPOVOConvertor.inventoryLossBillVOtoPO(billVO);
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
	public boolean denyBill(InventoryLossBillVO billVO) throws RemoteException {
		InventoryLossBillPO billPO = CommodityPOVOConvertor.inventoryLossBillVOtoPO(billVO);
		service.denyBill(billPO);
		return true;
	}

}
