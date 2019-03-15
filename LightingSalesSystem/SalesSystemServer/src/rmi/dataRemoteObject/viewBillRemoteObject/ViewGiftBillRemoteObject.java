package rmi.dataRemoteObject.viewBillRemoteObject;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

import assistant.exception.Myexception;
import blImpl.bill.viewBillBl.ViewGiftBillBlController;
import blService.billService.viewBillBlService.ViewGiftBillBlService;
import vo.GiftBillVO;

public class ViewGiftBillRemoteObject extends UnicastRemoteObject implements ViewGiftBillBlService{

	/**
	 * 
	 */
	private static final long serialVersionUID = -4924454326048929159L;

	public ViewGiftBillRemoteObject() throws RemoteException {
		super();
	}

	ViewGiftBillBlService service=new ViewGiftBillBlController();
	
	@Override
	public GiftBillVO getGiftBill(String billId) throws RemoteException, Myexception {
		return service.getGiftBill(billId);
	}

}
