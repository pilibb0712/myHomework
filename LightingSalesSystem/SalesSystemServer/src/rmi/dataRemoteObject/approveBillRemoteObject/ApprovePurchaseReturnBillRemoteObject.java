package rmi.dataRemoteObject.approveBillRemoteObject;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.security.Provider.Service;
import java.util.ArrayList;

import blImpl.bill.approveBillBl.ApprovePurchaseReturnBillBlController;
import blService.billService.approveBillBlService.ApprovePurchaseReturnBillBlService;
import vo.PurchaseReturnBillVO;
import vo.UserInfoVO;

/**
 * 单据的审批，包括得到需要审批单据列表，和pass deny单据
 * @author 张傲  161250193
 * @version 2017.12.3
 */
public class ApprovePurchaseReturnBillRemoteObject extends UnicastRemoteObject implements ApprovePurchaseReturnBillBlService{

	/**
	 * 
	 */
	private static final long serialVersionUID = -4578082032361837858L;

	public ApprovePurchaseReturnBillRemoteObject() throws RemoteException {
		super();
		// TODO Auto-generated constructor stub
	}

	private ApprovePurchaseReturnBillBlService service = new ApprovePurchaseReturnBillBlController();
	
	@Override
	public ArrayList<PurchaseReturnBillVO> getBillsList() throws RemoteException {
		return service.getBillsList();
	}

	@Override
	public boolean passBill(PurchaseReturnBillVO billVO) throws RemoteException {
		return service.passBill(billVO);
	}

	@Override
	public boolean denyBill(PurchaseReturnBillVO billVO) throws RemoteException {
		return service.denyBill(billVO);
	}

}
