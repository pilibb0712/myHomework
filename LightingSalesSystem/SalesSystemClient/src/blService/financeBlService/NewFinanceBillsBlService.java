package blService.financeBlService;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.ArrayList;

import vo.CashExpenseBillVO;
import vo.PaymentBillVO;
import vo.ReceiptBillVO;
import vo.UserInfoVO;

/**
 * 新建财务类单据的逻辑接口
 * @author guxinyu
 * @version 2017.12.2
 *
 */
public interface NewFinanceBillsBlService extends Remote{
	/**
	 * 新建现金费用单，此接口可将现金费用单保存并提交至总经理处审批
	 * @param toSave需要被保存的现金费用单的VO
	 * @return 返回的布尔值表明是否保存成功
	 * @throws RemoteException
	 */
	public boolean newCashExpenseBill(CashExpenseBillVO toSave) throws RemoteException;
	
	/**
	 * 新建现金费用单，此接口可将现金费用单保存草稿
	 * @param toSave
	 * @return 返回的布尔值表明是否保存成功
	 * @throws RemoteException
	 */
	public boolean saveCashExpenseBillDraft(CashExpenseBillVO toSave) throws RemoteException;
	
	/**
	 * 获得现金费用单的草稿
	 * @return 返回草稿状态的现金费用单集合
	 * @throws RemoteException
	 */
	public ArrayList<CashExpenseBillVO> getCashExpenseBillDraftList(UserInfoVO creator) throws RemoteException;
	
	/**
	 * 删除现金费用单草稿
	 * @return
	 * @throws RemoteException
	 */
	public boolean deleteCashExpenseBillDraft(CashExpenseBillVO toDelete) throws RemoteException;
	
	/**
	 * 新建付款单，此接口可将付款单保存并提交至总经理处审批
	 * @param toSave需要被保存的付款单单的VO
	 * @return 返回的布尔值表明是否保存成功
	 * @throws RemoteException
	 */
	public boolean newPaymentBill(PaymentBillVO toSave) throws RemoteException;
	
	/**
	 *  新建付款单，此接口可将付款单保存草稿
	 * @param toSave
	 * @return 返回的布尔值表明是否保存成功
	 * @throws RemoteException
	 */
	public boolean savePaymentBillDraft(PaymentBillVO toSave) throws RemoteException;
	
	/**
	 * 获得付款单的草稿
	 * @return 返回草稿状态的付款单集合
	 * @throws RemoteException
	 */
	public ArrayList<PaymentBillVO> getPaymentBillDraftList(UserInfoVO creator) throws RemoteException;
	
	/**
	 * 删除付款单草稿
	 * @return
	 * @throws RemoteException
	 */
	public boolean deletePaymentBillDraft(PaymentBillVO toDelete) throws RemoteException;
	
	/**
	 * 新建付款单，此接口可将收款单保存并提交至总经理处审批
	 * @param toSave需要被保存的收款单的VO
	 * @return 返回的布尔值表明是否保存成功
	 * @throws RemoteException
	 */
	public boolean newReceiptBill(ReceiptBillVO toSave) throws RemoteException;
	
	/**
	 * 新建付款单，此接口可将收款单保存草稿
	 * @param toSave
	 * @return 返回的布尔值表明是否保存成功
	 * @throws RemoteException
	 */
	public boolean saveReceiptBillDraft(ReceiptBillVO toSave) throws RemoteException;
	
	/**
	 * 获得收款单的草稿
	 * @return 返回草稿状态的收款单集合
	 * @throws RemoteException
	 */
	public ArrayList<ReceiptBillVO> getReceiptBillDraftList(UserInfoVO creator) throws RemoteException;
	
	/**
	 * 删除收款单草稿
	 * @return
	 * @throws RemoteException
	 */
	public boolean deleteReceiptBillDraft(ReceiptBillVO toDelete) throws RemoteException;
}
