package rmi.dataRemoteObject.viewBillRemoteObject;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import blImpl.bill.viewBillBl.ViewFinanceStaffBillBlController;
import blService.billService.viewBillBlService.ViewFinanceStaffBillBlService;
import vo.CashExpenseBillVO;
import vo.PaymentBillVO;
import vo.ReceiptBillVO;

public class ViewFinanceStaffBillRemoteObject extends UnicastRemoteObject implements ViewFinanceStaffBillBlService{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1668094014954090643L;

	public ViewFinanceStaffBillRemoteObject() throws RemoteException {
		super();
	}

	ViewFinanceStaffBillBlService service=new ViewFinanceStaffBillBlController();
	
	@Override
	public PaymentBillVO getPaymentBill(String billId) throws RemoteException {
		return service.getPaymentBill(billId);
	}

	@Override
	public ReceiptBillVO getReceiptBill(String billId) throws RemoteException {
		return service.getReceiptBill(billId);
	}

	@Override
	public CashExpenseBillVO getCashExpenseBill(String billId) throws RemoteException {
		return service.getCashExpenseBill(billId);
	}

}
