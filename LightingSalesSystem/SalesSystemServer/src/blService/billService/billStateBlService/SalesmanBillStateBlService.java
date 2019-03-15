package blService.billService.billStateBlService;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.ArrayList;

import assistant.type.BillStateEnum;
import vo.PurchaseBillVO;
import vo.PurchaseReturnBillVO;
import vo.SalesBillVO;
import vo.SalesReturnBillVO;
import vo.UserInfoVO;

/**
 * 进货销售人员单据状态逻辑接口
 * @author guxinyu
 * @version 2017.12.20
 *
 */
public interface SalesmanBillStateBlService extends Remote{

	/**
	 *本方法读取已完成的进货单<br>
	 * @return 返回所有已完成的进货单<br>
	 * @throws RemoteException
	 * 
	 */
	public ArrayList<PurchaseBillVO> getPurchaseBillList(BillStateEnum state, UserInfoVO user) throws RemoteException;
	
	/**
	 * 删除未通过审核的进货单
	 * @param bill 要删除的单据
	 * @return 是否删除成功
	 * @throws RemoteException
	 */
	public boolean deletePurchaseBill(PurchaseBillVO bill) throws RemoteException;
	
	/**
	 * 本方法读取已完成的进货退货单<br>
	 * @return 返回所有已完成的进货退货单<br>
	 * @throws RemoteException
	 */
	public ArrayList<PurchaseReturnBillVO> getPurchaseReturnBillList(BillStateEnum state, UserInfoVO user) throws RemoteException;
	
	/**
	 * 删除未通过审核的进货退货单
	 * @param bill 要删除的单据
	 * @return 是否删除成功
	 * @throws RemoteException
	 */
	public boolean deletePurchaseReturnBill(PurchaseReturnBillVO bill) throws RemoteException;
	
	
	/**
	 * 本方法读取已完成的销售单<br>
	 * @return 返回所有已完成的销售单<br>
	 * @throws RemoteException
	 */
	public ArrayList<SalesBillVO> getSalesBillList(BillStateEnum state, UserInfoVO user) throws RemoteException;
	
	/**
	 * 删除未通过审核的销售单
	 * @param bill 要删除的单据
	 * @return 是否删除成功
	 * @throws RemoteException
	 */
	public boolean deleteSalesBill(SalesBillVO bill) throws RemoteException;
	
	/**
	 * 本方法读取已完成的销售退货单<br>
	 * @return 返回所有已完成的销售退货单<br>
	 * @throws RemoteException
	 */
	public ArrayList<SalesReturnBillVO> getSalesReturnBillList(BillStateEnum state, UserInfoVO user) throws RemoteException;
	
	/**
	 * 删除未通过审核的销售退货单
	 * @param bill 要删除的单据
	 * @return 是否删除成功
	 * @throws RemoteException
	 */
	public boolean deleteSalesReturnBill(SalesReturnBillVO bill) throws RemoteException;
	
}
