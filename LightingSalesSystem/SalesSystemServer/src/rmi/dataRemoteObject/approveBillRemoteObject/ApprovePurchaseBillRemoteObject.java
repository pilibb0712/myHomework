package rmi.dataRemoteObject.approveBillRemoteObject;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;

import blImpl.bill.approveBillBl.ApprovePurchaseBillBlController;
import blService.billService.approveBillBlService.ApprovePurchaseBillBlService;
import vo.PurchaseBillVO;
import vo.UserInfoVO;
/**
 * 单据的审批，包括得到需要审批单据列表，和pass deny单据
 * @author 张傲  161250193
 * @version 2017.12.3
 */
public class ApprovePurchaseBillRemoteObject extends UnicastRemoteObject implements ApprovePurchaseBillBlService{

	/**
	 * 
	 */
	private static final long serialVersionUID = -5655073318199568411L;

	public ApprovePurchaseBillRemoteObject() throws RemoteException {
		super();
	}
	
	private ApprovePurchaseBillBlService service = new ApprovePurchaseBillBlController();
	
	@Override
	public ArrayList<PurchaseBillVO> getBillsList() throws RemoteException {
		return service.getBillsList();
	}

	@Override
	public boolean passBill(PurchaseBillVO billVO) throws RemoteException {
		return service.passBill(billVO);
	}

	@Override
	public boolean denyBill(PurchaseBillVO billVO) throws RemoteException {
		return service.denyBill(billVO);
	}

}
