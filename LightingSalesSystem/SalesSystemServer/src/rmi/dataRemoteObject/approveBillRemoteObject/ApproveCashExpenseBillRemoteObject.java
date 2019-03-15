package rmi.dataRemoteObject.approveBillRemoteObject;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;

import blImpl.bill.approveBillBl.ApproveCashExpenseBillBlController;
import blService.billService.approveBillBlService.ApproveCashExpenseBillBlService;
import vo.CashExpenseBillVO;
import vo.UserInfoVO;

/**
 * 该服务的remoteObject
 * @author zhangao
 * @version 2017.12.28
 * */
public class ApproveCashExpenseBillRemoteObject extends UnicastRemoteObject implements ApproveCashExpenseBillBlService {

	/**
	 * 
	 */
	private static final long serialVersionUID = 7799801042865376250L;

	public ApproveCashExpenseBillRemoteObject() throws RemoteException {
		super();
	}

	private ApproveCashExpenseBillBlService service = new ApproveCashExpenseBillBlController();

	@Override
	public ArrayList<CashExpenseBillVO> getBillsList() throws RemoteException {
		return service.getBillsList();
	}

	@Override
	public boolean passBill(CashExpenseBillVO billVO) throws RemoteException {
		return service.passBill(billVO);
	}

	@Override
	public boolean denyBill(CashExpenseBillVO billVO) throws RemoteException {
		return denyBill(billVO);
	}

}
