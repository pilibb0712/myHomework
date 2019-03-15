/**
 * 
 */
package blStubs.billStubs.viewBillBlStubs;

import java.rmi.RemoteException;

import blService.billService.viewBillBlService.ViewSalsemanBillBlService;
import vo.PurchaseBillVO;
import vo.PurchaseReturnBillVO;
import vo.SalesBillVO;
import vo.SalesReturnBillVO;

/**
 * @author cosx
 *
 */
public class ViewSalsemanBillBlService_Stub implements ViewSalsemanBillBlService {

	/**
	 * @see blService.billService.viewBillBlService.ViewSalsemanBillBlService#getPurchaseBill(java.lang.String)
	 * @param billNum
	 * @return
	 * @throws RemoteException
	 */
	@Override
	public PurchaseBillVO getPurchaseBill(String billNum) throws RemoteException {
		// TODO Auto-generated method stub
		return null;
	}

	/**
	 * @see blService.billService.viewBillBlService.ViewSalsemanBillBlService#getPurchaseReturnBill(java.lang.String)
	 * @param billNum
	 * @return
	 * @throws RemoteException
	 */
	@Override
	public PurchaseReturnBillVO getPurchaseReturnBill(String billNum) throws RemoteException {
		// TODO Auto-generated method stub
		return null;
	}

	/**
	 * @see blService.billService.viewBillBlService.ViewSalsemanBillBlService#getSalesBill(java.lang.String)
	 * @param billNum
	 * @return
	 * @throws RemoteException
	 */
	@Override
	public SalesBillVO getSalesBill(String billNum) throws RemoteException {
		// TODO Auto-generated method stub
		return null;
	}

	/**
	 * @see blService.billService.viewBillBlService.ViewSalsemanBillBlService#getSalesReturnBill(java.lang.String)
	 * @param billNum
	 * @return
	 * @throws RemoteException
	 */
	@Override
	public SalesReturnBillVO getSalesReturnBill(String billNum) throws RemoteException {
		// TODO Auto-generated method stub
		return null;
	}

}
