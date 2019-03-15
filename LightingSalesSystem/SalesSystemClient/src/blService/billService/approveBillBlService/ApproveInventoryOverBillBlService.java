package blService.billService.approveBillBlService;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.ArrayList;

import vo.InventoryOverBillVO;
import vo.UserInfoVO;
/**
 * 单据的审批，包括得到需要审批单据列表，和pass deny单据
 * @author 张傲  161250193
 * @version 2017.12.3
 */
public interface ApproveInventoryOverBillBlService extends Remote{

	/**
	 * 得到该类型未审批单据清单
	 * @throws RemoteException
	 * */
	public ArrayList<InventoryOverBillVO> getBillsList() throws RemoteException;
	
	/**
	 * 根据单据编号通过单据审批
	 * @throws RemoteException
	 * */
	public boolean passBill(InventoryOverBillVO billVO) 
			throws RemoteException;
	 
	/**
	 * 通过单据编号拒绝单据审批
	 * @throws RemoteException
	 * */
	public boolean denyBill(InventoryOverBillVO billVO) 
			throws RemoteException;
}
