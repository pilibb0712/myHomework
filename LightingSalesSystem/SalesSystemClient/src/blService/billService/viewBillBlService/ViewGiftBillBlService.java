package blService.billService.viewBillBlService;

import java.rmi.Remote;
import java.rmi.RemoteException;

import assistant.exception.Myexception;
import vo.GiftBillVO;
/**
 * @author zhangao 
 * @version 2017.12.28
 * */

public interface ViewGiftBillBlService extends Remote {
	
	/**
	 * 根据单据Id得到一张giftBill
	 * @param billId单据的ID
	 * @return GiftVO 该编号的单据
	 * @throws RemoteException
	 * @throws Myexception 自定义异常
	 * */
	public GiftBillVO getGiftBill(String billId) throws RemoteException,Myexception;
}
