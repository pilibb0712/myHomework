package rmi.dataRemoteObject.approveBillRemoteObject;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;

import blImpl.bill.approveBillBl.ApproveInventoryLossBillBlController;
import blService.billService.approveBillBlService.ApproveInventoryLossBillBlService;
import vo.BillVO;
import vo.InventoryLossBillVO;
import vo.UserInfoVO;

/**
 * 该服务的remoteObject
 * @author zhangao
 * @version 2017.12.28
 * */
public class ApproveInventoryLossBillRemoteObject  extends UnicastRemoteObject implements ApproveInventoryLossBillBlService{

	/**
	 * 
	 */
	private static final long serialVersionUID = -1237877548674340817L;

	public ApproveInventoryLossBillRemoteObject() throws RemoteException {
		super();
	}

	private ApproveInventoryLossBillBlService service = new ApproveInventoryLossBillBlController();
	
	@Override
	public ArrayList<InventoryLossBillVO> getBillsList() throws RemoteException {
		return service.getBillsList();
	}

	@Override
	public boolean passBill(InventoryLossBillVO billVO) throws RemoteException {
		return service.passBill(billVO);
	}

	@Override
	public boolean denyBill(InventoryLossBillVO billVO) throws RemoteException {
		return service.denyBill(billVO);
	}

}
