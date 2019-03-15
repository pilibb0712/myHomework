package blService.billService.billStateBlService;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.ArrayList;

import assistant.type.BillStateEnum;
import vo.GiftBillVO;
import vo.InventoryLossBillVO;
import vo.InventoryOverBillVO;
import vo.InventoryWarningBillVO;
import vo.PurchaseBillVO;
import vo.PurchaseReturnBillVO;
import vo.SalesBillVO;
import vo.SalesReturnBillVO;
import vo.UserInfoVO;

/**
 * 仓库管理人员单据状态逻辑接口
 * @author guxinyu
 * @version 2017.12.12
 *
 */
public interface WareKeeperBillStateBlService extends Remote{
	/**
	 * 根据状态获取库存报警单列表
	 * @return 库存报警单列表
	 * @throws RemoteException
	 */
	public ArrayList<InventoryWarningBillVO> getInventoryWarningList(BillStateEnum state, UserInfoVO user) throws RemoteException;
	
	/**
	 * 删除未通过审核的库存报警单
	 * @param bill 要删除的
	 * @return 是否删除成功
	 * @throws RemoteException
	 */
	public boolean deleteUnpassInventoryWarningBill(InventoryWarningBillVO bill) throws RemoteException;
	
	/**
	 * 根据状态获取库存报损单列表
	 * @return 库存报损单列表
	 * @throws RemoteException
	 */
	public ArrayList<InventoryLossBillVO> getInventoryLossList(BillStateEnum state, UserInfoVO user) throws RemoteException;
	
	/**
	 * 删除未通过审核的库存报损单
	 * @param bill 要删除的
	 * @return 是否删除成功
	 * @throws RemoteException
	 */
	public boolean deleteUnpassInventoryLossBill(InventoryLossBillVO bill) throws RemoteException;
	
	/**
	 * 做完库存报损单
	 * @param bill 做完的单据
	 * @return 是否更改成功
	 * @throws RemoteException
	 */
	public boolean doneInventoryLossBill(InventoryLossBillVO bill) throws RemoteException;
	
	
	/**
	 * 根据状态获取库存报溢单列表
	 * @return 库存报溢单列表
	 * @throws RemoteException
	 */
	public ArrayList<InventoryOverBillVO> getInventoryOverList(BillStateEnum state, UserInfoVO user) throws RemoteException;
	
	/**
	 * 删除未通过审核的库存报溢单
	 * @param bill 要删除的
	 * @return 是否删除成功
	 * @throws RemoteException
	 */
	public boolean deleteUnpassInventoryOverBill(InventoryOverBillVO bill) throws RemoteException;
	
	/**
	 * 做完库存报溢单
	 * @param bill 做完的单据
	 * @return 是否更改成功
	 * @throws RemoteException
	 */
	public boolean doneInventoryOverBill(InventoryOverBillVO bill) throws RemoteException;
	
	
	/**
	 *本方法读取进货单<br>
	 * @return 返回所有已完成的进货单<br>
	 * @throws RemoteException
	 * 
	 */
	public ArrayList<PurchaseBillVO> getPurchaseBillList(BillStateEnum state, UserInfoVO user) throws RemoteException;
	
	/**
	 * 做完进货单
	 * @param bill 做完的单据
	 * @return 是否更改成功
	 * @throws RemoteException
	 */
	public boolean donePurchaseBill(PurchaseBillVO bill) throws RemoteException;
	
	
	/**
	 * 本方法读取进货退货单<br>
	 * @return 返回所有已完成的进货退货单<br>
	 * @throws RemoteException
	 */
	public ArrayList<PurchaseReturnBillVO> getPurchaseReturnBillList(BillStateEnum state, UserInfoVO user) throws RemoteException;
	
	/**
	 * 做完进货退货单
	 * @param bill 做完的单据
	 * @return 是否更改成功
	 * @throws RemoteException
	 */
	public boolean donePurchaseReturnBill(PurchaseReturnBillVO bill) throws RemoteException;
	
	/**
	 * 本方法读取销售单<br>
	 * @return 返回所有已完成的销售单<br>
	 * @throws RemoteException
	 */
	public ArrayList<SalesBillVO> getSalesBillList(BillStateEnum state, UserInfoVO user) throws RemoteException;
	
	/**
	 * 做完销售单
	 * @param bill 做完的单据
	 * @return 是否更改成功
	 * @throws RemoteException
	 */
	public boolean doneSalesBill(SalesBillVO bill) throws RemoteException;
	
	
	/**
	 * 本方法读取销售退货单<br>
	 * @return 返回所有已完成的销售退货单<br>
	 * @throws RemoteException
	 */
	public ArrayList<SalesReturnBillVO> getSalesReturnBillList(BillStateEnum state, UserInfoVO user) throws RemoteException;
	
	/**
	 * 做完销售退货单
	 * @param bill 做完的单据
	 * @return 是否更改成功
	 * @throws RemoteException
	 */
	public boolean doneSalesReturnBill(SalesReturnBillVO bill) throws RemoteException;
	
	/**
	 * 本方法读取库存赠送单<br>
	 * @return 返回所有已完成的库存赠送单<br>
	 * @throws RemoteException
	 */
	public ArrayList<GiftBillVO> getGiftBillList(BillStateEnum state, UserInfoVO user) throws RemoteException;
	
	/**
	 * 做完库存赠送单
	 * @param bill 做完的单据
	 * @return 是否更改成功
	 * @throws RemoteException
	 */
	public boolean doneGiftBill(GiftBillVO bill) throws RemoteException;
	
}
