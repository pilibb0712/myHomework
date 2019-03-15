package rmi.dataRemoteObject.listRemoteObject;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;

import blImpl.listBl.SalesDetailsListBlController;
import blService.listBlService.SalesDetailsListBlService;
import vo.ClassificationVO;
import vo.CustomerInfoVO;
import vo.GoodsVO;
import vo.SalesDetailsListVO;
import vo.UserVO;

public class SalesDetailsListRemoteObject extends UnicastRemoteObject implements SalesDetailsListBlService{

	/**
	 * 
	 */
	private static final long serialVersionUID = 3423600187310394233L;

	public SalesDetailsListRemoteObject() throws RemoteException {
		super();
	}
	
	private SalesDetailsListBlService service=new SalesDetailsListBlController();

	@Override
	public SalesDetailsListVO getSalesDetailsList() throws RemoteException {
		return service.getSalesDetailsList();
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
	public ArrayList<UserVO> getManagerList() throws Exception {
		return service.getManagerList();
	}

	@Override
	public ArrayList<CustomerInfoVO> getCustomerList() throws RemoteException {
		return service.getCustomerList();
	}

	@Override
	public ArrayList<ClassificationVO> getCommodityClassification() throws RemoteException {
		return service.getCommodityClassification();
	}

	@Override
	public ArrayList<GoodsVO> getCommodities() throws RemoteException {
		return service.getCommodities();
	}

}
