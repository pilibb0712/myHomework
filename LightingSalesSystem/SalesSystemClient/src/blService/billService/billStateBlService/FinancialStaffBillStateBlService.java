package blService.billService.billStateBlService;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.ArrayList;

import assistant.type.BillStateEnum;
import vo.CashExpenseBillVO;
import vo.PaymentBillVO;
import vo.PurchaseBillVO;
import vo.PurchaseReturnBillVO;
import vo.ReceiptBillVO;
import vo.SalesBillVO;
import vo.SalesReturnBillVO;
import vo.UserInfoVO;

/**
 * 财务管理人员单据状态逻辑接口
 * @author guxinyu
 * @version 2017.12.9
 *
 */
public interface FinancialStaffBillStateBlService extends Remote{

	/**
	 *获取财务人员待制定的单据列表<br>
	 *收款单、付款单、现金费用单的信息来自进货单、进货退货单、销售单和销售进货单<br>
	 *本方法读取已完成的进货单<br>
	 * @return 返回所有已完成的进货单<br>
	 * @throws RemoteException
	 * 
	 */
	public ArrayList<PurchaseBillVO> getPurchaseBillList(BillStateEnum state) throws RemoteException;
	
	/**
	 * 本方法读取已完成的进货退货单<br>
	 * @return 返回所有已完成的进货退货单<br>
	 * @throws RemoteException
	 */
	public ArrayList<PurchaseReturnBillVO> getPurchaseReturnBillList(BillStateEnum state) throws RemoteException;
	
	/**
	 * 本方法读取已完成的销售单<br>
	 * @return 返回所有已完成的销售单<br>
	 * @throws RemoteException
	 */
	public ArrayList<SalesBillVO> getSalesBillList(BillStateEnum state) throws RemoteException;
	
	/**
	 * 本方法读取已完成的销售退货单<br>
	 * @return 返回所有已完成的销售退货单<br>
	 * @throws RemoteException
	 */
	public ArrayList<SalesReturnBillVO> getSalesReturnBillList(BillStateEnum state) throws RemoteException;
	
	/**
	 *获取财务人员未通过审核的现金费用单据列表<br>
	 * @return 所有未通过审核的现金费用单
	 * @throws RemoteException
	 */
	public ArrayList<CashExpenseBillVO> getCashExpenseBillList(BillStateEnum state, UserInfoVO user) throws RemoteException;
	
	/**
	 * 获取财务人员未通过审核的付款单单据列表<br>
	 * @return 所有未通过审核的付款单
	 * @throws RemoteException
	 */
	public ArrayList<PaymentBillVO> getPaymentBillList(BillStateEnum state, UserInfoVO user) throws RemoteException;
	
	/**
	 * 获取财务人员未通过审核的收款单单据列表<br>
	 * @return 所有未通过审核的收款单
	 * @throws RemoteException
	 */
	public ArrayList<ReceiptBillVO> getReceiptBillList(BillStateEnum state, UserInfoVO user) throws RemoteException;
	
	/**
	 * 删除未通过的现金费用单
	 * @param toDelete 要被删除的单子
	 * @return 是否删除成功
	 * @throws RemoteException
	 */
	public boolean deleteUnpassCashExpenseBill(CashExpenseBillVO toDelete) throws RemoteException;
	
	/**
	 * 删除未通过的付款单
	 * @param toDelete 要被删除的单子
	 * @return 是否删除成功
	 * @throws RemoteException
	 */
	public boolean deleteUnpassPaymentBill(PaymentBillVO toDelete) throws RemoteException;
	
	/**
	 * 删除未通过的收款单
	 * @param toDelete 要被删除的单子
	 * @return 是否删除成功
	 * @throws RemoteException
	 */
	public boolean deleteUnpassReceiptBill(ReceiptBillVO toDelete) throws RemoteException;
	
	public boolean doneCashExpenseBill(CashExpenseBillVO done) throws RemoteException;
	
	public boolean donePaymentBill(PaymentBillVO done) throws RemoteException;
	
	public boolean doneReceiptBill(ReceiptBillVO done) throws RemoteException;
}
