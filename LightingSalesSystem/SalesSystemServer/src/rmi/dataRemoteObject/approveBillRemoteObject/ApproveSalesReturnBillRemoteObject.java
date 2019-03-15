 package rmi.dataRemoteObject.approveBillRemoteObject;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;

import blImpl.bill.approveBillBl.ApproveSalesReturnBillBlController;
import blService.billService.approveBillBlService.ApproveSalesReturnBillBlService;
import vo.SalesReturnBillVO;
import vo.UserInfoVO;

/**
 * 单据的审批，包括得到需要审批单据列表，和pass deny单据
 * @author 张傲  161250193
 * @version 2017.12.3
 */
public class ApproveSalesReturnBillRemoteObject extends UnicastRemoteObject implements ApproveSalesReturnBillBlService {

	/**
	 * 
	 */
	private static final long serialVersionUID = 7476794552195113588L;

	public ApproveSalesReturnBillRemoteObject() throws RemoteException {
		super();
	}

	private ApproveSalesReturnBillBlService service = new ApproveSalesReturnBillBlController();
	
	@Override
	public ArrayList<SalesReturnBillVO> getBillsList() throws RemoteException {
		return service.getBillsList();
	}

	@Override
	public boolean passBill(SalesReturnBillVO billVO) throws RemoteException {
		return service.passBill(billVO);
	}

	@Override
	public boolean denyBill(SalesReturnBillVO billVO) throws RemoteException {
		return service.denyBill(billVO);
	}

}
