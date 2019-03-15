package blImpl.listBl;

import java.rmi.RemoteException;
import java.util.ArrayList;
import assistant.convertors.ListPOVOConvertor;
import blService.listBlService.SalesDetailsListBlService;
import po.SalesDetailsListPO;
import vo.ClassificationVO;
import vo.CustomerInfoVO;
import vo.GoodsVO;
import vo.SalesDetailsListVO;
import vo.UserVO;

/**
 * 销售明细表
 * @author guxinyu
 * @version 12.18
 *
 */
public class SalesDetailsListBlController implements SalesDetailsListBlService{
	SalesDetailsListBl listBl=SalesDetailsListBl.getInstance();

	@Override
	public SalesDetailsListVO getSalesDetailsList() throws RemoteException {
		SalesDetailsListPO po=listBl.getSalesDetailsList();
		
		SalesDetailsListVO vo=ListPOVOConvertor.salesDetailsListPOtoVO(po);
		
		return vo;
	}

	@Override
	public ArrayList<UserVO> getWareKeeperList() throws RemoteException {
		ArrayList<UserVO> vo=listBl.getWareKeeperList();
		
		return vo;
	}
	
	@Override
	public ArrayList<UserVO> getSalesmanList() throws RemoteException{
		ArrayList<UserVO> vo=listBl.getSalesmanList();
		
		return vo;
	}
	
	@Override
	public ArrayList<UserVO> getFinancialStaffList() throws RemoteException{
		ArrayList<UserVO> vo=listBl.getFinancialStaffList();
		
		return vo;
	}
	
	@Override
	public ArrayList<UserVO> getManagerList() throws Exception {
		ArrayList<UserVO> vo=listBl.getManagerList();
		
		return vo;
	}
	
	@Override
	public ArrayList<CustomerInfoVO> getCustomerList() throws RemoteException {
		ArrayList<CustomerInfoVO> vo=listBl.getCustomers();
		return vo;
	}

	@Override
	public ArrayList<ClassificationVO> getCommodityClassification() throws RemoteException {
		ArrayList<ClassificationVO> vo=listBl.getCommodityClassificationList();
		return vo;
	}

	@Override
	public ArrayList<GoodsVO> getCommodities() throws RemoteException {
		ArrayList<GoodsVO> vo=listBl.getCommodityList();
		return vo;
	}

}
