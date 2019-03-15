package blService.billService.approveBillBlService;

import java.rmi.Remote;

import java.rmi.RemoteException;
import java.util.ArrayList;

import assistant.type.BillCategoryEnum;
import vo.BillVO;


public interface  ApproveBillBlService extends Remote{
	 public ArrayList<BillVO> getBillsList(BillCategoryEnum billCategoryEnum) throws RemoteException;
	
	 public boolean passBill(String billName, String billId) throws RemoteException;
	 
	 public boolean denyBill(String billName, String billId) throws RemoteException;
}
