package blService.billService.viewBillBlService;

import java.rmi.Remote;
import java.rmi.RemoteException;
import vo.*;

/**
 * 工作人员查看销售人员制定的单据信息的业务逻辑接口
 * @author 王宁一
 * @version 2017.11.7
 *
 */

public interface ViewSalsemanBillBlService extends Remote{

	/**
	 * 获取供应商进货单的信息
	 * @param 单据编号（字符串）
	 * @return PurchaseBillVO
	 * @throws RemoteException
	 */
	public PurchaseBillVO getPurchaseBill(String billId) throws RemoteException;
	
	/**
	 * 获取供应商进货退货单的信息
	 * @param 单据编号（字符串）
	 * @return PurchaseReturnBillVO
	 * @throws RemoteException
	 */
	public PurchaseReturnBillVO getPurchaseReturnBill(String billId) throws RemoteException;
	
	/**
	 * 获取销售商出货单的信息
	 * @param 单据编号（字符串）
	 * @return SalesBillVO
	 * @throws RemoteException
	 */
	public SalesBillVO getSalesBill(String billId) throws RemoteException;
	
	/**
	 * 获取销售商出货退货单的信息
	 * @param 单据编号（字符串）
	 * @return SalesReturnBillVO
	 * @throws RemoteException
	 */
	public SalesReturnBillVO getSalesReturnBill(String billId) throws RemoteException;
}
