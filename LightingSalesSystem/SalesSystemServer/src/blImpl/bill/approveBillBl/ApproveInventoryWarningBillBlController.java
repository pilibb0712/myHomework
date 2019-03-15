package blImpl.bill.approveBillBl;

import java.rmi.RemoteException;
import java.util.ArrayList;

import assistant.convertors.CommodityPOVOConvertor;
import assistant.convertors.UserInfoPOVOConvertor;
import assistant.type.BillStateEnum;
import assistant.utility.Date;
import blInteract.billBlInteract.BillBlInteractServiceFactory;
import blInteract.billBlInteract.InventoryWarningBillBlService;
import blService.billService.approveBillBlService.ApproveInventoryWarningBillBlService;
import po.InventoryWarningBillPO;
import po.UserInfoPO;
import vo.InventoryWarningBillVO;
import vo.UserInfoVO;

/**
 * 单据的审批，包括得到需要审批单据列表，和pass deny单据
 * @author 张傲  161250193
 * @version 2017.12.3
 */

public class ApproveInventoryWarningBillBlController implements ApproveInventoryWarningBillBlService{

    private InventoryWarningBillBlService service 
    	= new BillBlInteractServiceFactory().getInventoryWarningBillBlService();//现金费用单相关操作
	
    /**
	 * 得到需要审批的单据列表
	 * @return ArrayList<InventoryWarningBillVO> 需要审批的单据列表
	 * @throws RemoteException
	 */
	@Override
	public ArrayList<InventoryWarningBillVO> getBillsList() throws RemoteException {
		ArrayList<InventoryWarningBillPO> list = service.getBillListByState(BillStateEnum.TBD);
		ArrayList<InventoryWarningBillVO> targetList =new ArrayList<InventoryWarningBillVO>();
		for(InventoryWarningBillPO po: list){
			InventoryWarningBillVO vo = CommodityPOVOConvertor.inventoryWarningBillPOtoVO(po) ;
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
	public boolean passBill(InventoryWarningBillVO billVO) throws RemoteException {
		InventoryWarningBillPO billPO = CommodityPOVOConvertor.inventoryWarningBillVOtoPO(billVO);
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
	public boolean denyBill(InventoryWarningBillVO billVO) throws RemoteException {
		InventoryWarningBillPO billPO = CommodityPOVOConvertor.inventoryWarningBillVOtoPO(billVO);
		service.denyBill(billPO);
		return true;
	}

	
}
