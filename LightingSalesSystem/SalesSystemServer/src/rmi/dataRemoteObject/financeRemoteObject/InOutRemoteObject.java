package rmi.dataRemoteObject.financeRemoteObject;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

import blImpl.financeBl.InOutBlController;
import blService.financeBlService.InOutBlService;
import vo.InOutDetailsVO;
import vo.InitialInfoVO;

public class InOutRemoteObject extends UnicastRemoteObject implements InOutBlService{

	public InOutRemoteObject() throws RemoteException {
		super();
	}

	/**
	 * 
	 */
	private static final long serialVersionUID = -2893246718947698924L;
	
	InOutBlService service=new InOutBlController();

	@Override
	public boolean newInOut(InitialInfoVO initialInfo) throws RemoteException {
		return service.newInOut(initialInfo);
	}

	@Override
	public InitialInfoVO viewInitialInformation(String inOutYear) throws RemoteException {
		return service.viewInitialInformation(inOutYear);
	}

	@Override
	public InOutDetailsVO viewInOutDetails(String inOutYear) throws RemoteException {
		return service.viewInOutDetails(inOutYear);
	}

	@Override
	public String[] viewInOutList() throws RemoteException {
		return service.viewInOutList();
	}

	
}
