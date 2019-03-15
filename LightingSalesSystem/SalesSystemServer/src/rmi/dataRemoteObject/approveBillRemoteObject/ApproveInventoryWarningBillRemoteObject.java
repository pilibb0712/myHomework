package rmi.dataRemoteObject.approveBillRemoteObject;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.security.Provider.Service;
import java.util.ArrayList;

import blImpl.bill.approveBillBl.ApproveInventoryWarningBillBlController;
import blService.billService.approveBillBlService.ApproveInventoryWarningBillBlService;
import vo.InventoryWarningBillVO;
import vo.UserInfoVO;

/**
 * 单据的审批，包括得到需要审批单据列表，和pass deny单据
 * @author 张傲  161250193
 * @version 2017.12.3
 */
public class ApproveInventoryWarningBillRemoteObject extends UnicastRemoteObject implements ApproveInventoryWarningBillBlService{

	/**
	 * 
	 */
	private static final long serialVersionUID = -896255891503028508L;

	public ApproveInventoryWarningBillRemoteObject() throws RemoteException {
		super();
	}

	private ApproveInventoryWarningBillBlService service = new ApproveInventoryWarningBillBlController();
	
	@Override
	public ArrayList<InventoryWarningBillVO> getBillsList() throws RemoteException {
		return service.getBillsList();
	}

	@Override
	public boolean passBill(InventoryWarningBillVO billVO) throws RemoteException {
		return service.passBill(billVO);
	}

	@Override
	public boolean denyBill(InventoryWarningBillVO billVO) throws RemoteException {
		return service.denyBill(billVO);
	}
	
}
