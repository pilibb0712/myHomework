package blService.billService.viewBillBlService;

import java.rmi.Remote;
import java.rmi.RemoteException;
import vo.CashExpenseBillVO;
import vo.PaymentBillVO;
import vo.ReceiptBillVO;


/**
 * 查看财务人员单据逻辑接口
 * @author guxinyu
 * @version 2017.12.6
 *
 */
public interface ViewFinanceStaffBillBlService extends Remote{
	/**
	 * 查看付款单
	 * @param billId为付款单单据编号，格式为"FKD-yyyymmdd-xxxxx"
	 * @return 方法返回该付款单的VO
	 * @throws RemoteException
	 */
	public PaymentBillVO getPaymentBill(String billId)throws RemoteException;
	
	/**
	 * 查看收款单
	 * @param billId为收款单单据编号，格式为"SKD-yyyymmdd-xxxxx"
	 * @return 方法返回该收款单的VO
	 * @throws RemoteException
	 */
	public ReceiptBillVO getReceiptBill(String billId) throws RemoteException;
	
	/**
	 * 查看现金费用单
	 * @param billId为现金费用单单据编号，格式为"XJFYD-yyyymmdd-xxxxx"
	 * @return 方法返回该现金费用单的VO
	 * @throws RemoteException
	 */
	public CashExpenseBillVO getCashExpenseBill(String billId)throws RemoteException;
}
