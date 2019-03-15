package rmi.dataRemoteObject.approveBillRemoteObject;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;

import blImpl.bill.approveBillBl.ApproveInventoryOverBillBlController;
import blService.billService.approveBillBlService.ApproveInventoryOverBillBlService;
import vo.InventoryOverBillVO;
import vo.UserInfoVO;

/**
 * 该服务的remoteObject
 * @author zhangao
 * @version 2017.12.28
 * */
public class ApproveInventoryOverBillRemoteObject extends UnicastRemoteObject implements ApproveInventoryOverBillBlService{

	/**
	 * 
	 */
	private static final long serialVersionUID = -7785296308384209918L;

	public ApproveInventoryOverBillRemoteObject() throws RemoteException {
		super();
	}

	private ApproveInventoryOverBillBlService service = new ApproveInventoryOverBillBlController();
	
	@Override
	public ArrayList<InventoryOverBillVO> getBillsList() throws RemoteException {
		return service.getBillsList();
	}

	@Override
	public boolean passBill(InventoryOverBillVO  billVO) throws RemoteException {
		return service.passBill(billVO);
	}

	@Override
	public boolean denyBill(InventoryOverBillVO billVO) throws RemoteException {
		return service.denyBill(billVO);
	}

}
