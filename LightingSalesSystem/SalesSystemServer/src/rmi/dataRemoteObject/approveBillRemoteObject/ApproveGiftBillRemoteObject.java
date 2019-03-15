package rmi.dataRemoteObject.approveBillRemoteObject;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;

import blImpl.bill.approveBillBl.ApproveGiftBillBlController;
import blService.billService.approveBillBlService.ApproveGiftBillBlService;
import vo.GiftBillVO;
import vo.UserInfoVO;

/**
 * 该服务的remoteObject
 * @author zhangao
 * @version 2017.12.28
 * */
public class ApproveGiftBillRemoteObject extends UnicastRemoteObject implements ApproveGiftBillBlService{
	/**
	 * 
	 */
	private static final long serialVersionUID = -5878818323989239836L;

	public ApproveGiftBillRemoteObject() throws RemoteException {
		super();
	}

	private ApproveGiftBillBlService service = new ApproveGiftBillBlController();

	@Override
	public ArrayList<GiftBillVO> getBillsList() throws RemoteException {
		return service.getBillsList();
	}

	@Override
	public boolean passBill(GiftBillVO billVO) throws RemoteException {
		return service.passBill(billVO);
	}

	@Override
	public boolean denyBill(GiftBillVO billVO) throws RemoteException {
		return denyBill(billVO);
	}
	
}
