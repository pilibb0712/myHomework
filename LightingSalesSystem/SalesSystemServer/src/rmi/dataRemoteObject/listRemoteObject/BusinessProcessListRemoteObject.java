package rmi.dataRemoteObject.listRemoteObject;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;

import blImpl.listBl.BusinessProcessListBlController;
import blService.listBlService.BusinessProcessListBlService;
import vo.BusinessProcessListVO;
import vo.CustomerInfoVO;
import vo.UserVO;

public class BusinessProcessListRemoteObject extends UnicastRemoteObject implements BusinessProcessListBlService{

	/**
	 * 
	 */
	private static final long serialVersionUID = 4344877474544751321L;

	public BusinessProcessListRemoteObject() throws RemoteException {
		super();
	}

	private BusinessProcessListBlService service=new BusinessProcessListBlController();
	
	@Override
	public BusinessProcessListVO getBusinessProcessList() throws RemoteException {
		return service.getBusinessProcessList();
	}

	@Override
	public ArrayList<UserVO> getWareKeeperList() throws RemoteException {
		return service.getWareKeeperList();
	}

	@Override
	public ArrayList<UserVO> getSalesmanList() throws RemoteException {
		return service.getSalesmanList();
	}

	@Override
	public ArrayList<UserVO> getFinancialStaffList() throws RemoteException {
		return service.getFinancialStaffList();
	}

	@Override
	public ArrayList<UserVO> getManagerList() throws RemoteException {
		return service.getManagerList();
	}

	@Override
	public ArrayList<CustomerInfoVO> getCustomerList() throws RemoteException {
		return service.getCustomerList();
	}

}
