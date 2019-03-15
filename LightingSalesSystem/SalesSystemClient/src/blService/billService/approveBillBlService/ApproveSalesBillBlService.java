package blService.billService.approveBillBlService;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.ArrayList;

import vo.SalesBillVO;
import vo.UserInfoVO;

public interface ApproveSalesBillBlService extends Remote{

	/**
	 * 得到该类型未审批单据清单
	 * @throws RemoteException
	 * */
	public ArrayList<SalesBillVO> getBillsList() throws RemoteException;
	
	/**
	 * 根据单据编号通过单据审批
	 * @throws RemoteException
	 * */
	public boolean passBill(SalesBillVO billVO) 
			throws RemoteException;
	 
	/**
	 * 通过单据编号拒绝单据审批
	 * @throws RemoteException
	 * */
	public boolean denyBill(SalesBillVO billVO) 
			throws RemoteException;
}
