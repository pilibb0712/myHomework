package rmi.dataRemoteObject.listRemoteObject;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

import blImpl.listBl.BusinessConditionListBlController;
import blService.listBlService.BusinessConditionListBlService;
import vo.BusinessConditionListVO;

public class BusinessConditionListRemoteObject extends UnicastRemoteObject implements BusinessConditionListBlService{

	/**
	 * 
	 */
	private static final long serialVersionUID = -2556700056280702103L;

	public BusinessConditionListRemoteObject() throws RemoteException {
		super();
	}
	
	private BusinessConditionListBlService service=new BusinessConditionListBlController();

	@Override
	public BusinessConditionListVO getBusinessConditionList() throws RemoteException {
		return service.getBusinessConditionList();
	}

}
