package rmi.dataRemoteObject.viewBillRemoteObject;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import blImpl.bill.viewBillBl.ViewSalsemanBillBlController;
import blService.billService.viewBillBlService.ViewSalsemanBillBlService;
import vo.PurchaseBillVO;
import vo.PurchaseReturnBillVO;
import vo.SalesBillVO;
import vo.SalesReturnBillVO;

public class ViewSalesmanBillRemoteObject extends UnicastRemoteObject implements ViewSalsemanBillBlService{

	/**
	 * 
	 */
	private static final long serialVersionUID = -2133298531065488202L;

	public ViewSalesmanBillRemoteObject() throws RemoteException {
		super();
	}

	ViewSalsemanBillBlService service=new ViewSalsemanBillBlController();
	
	@Override
	public PurchaseBillVO getPurchaseBill(String billId) throws RemoteException {
		return service.getPurchaseBill(billId);
	}

	@Override
	public PurchaseReturnBillVO getPurchaseReturnBill(String billId) throws RemoteException {
		return service.getPurchaseReturnBill(billId);
	}

	@Override
	public SalesBillVO getSalesBill(String billId) throws RemoteException {
		return service.getSalesBill(billId);
	}

	@Override
	public SalesReturnBillVO getSalesReturnBill(String billId) throws RemoteException {
		return service.getSalesReturnBill(billId);
	}

}
