package blImpl.listBl;

import java.rmi.RemoteException;
import java.util.ArrayList;
import assistant.convertors.ListPOVOConvertor;
import blService.listBlService.BusinessProcessListBlService;
import po.BusinessProcessListPO;
import vo.BusinessProcessListVO;
import vo.CustomerInfoVO;
import vo.UserVO;

/**
 * 经营历程表
 * @author guxinyu
 * @version 12.23
 *
 */
public class BusinessProcessListBlController implements BusinessProcessListBlService{
	private BusinessProcessListBl listBl=BusinessProcessListBl.getInstance();

	
	@Override
	public BusinessProcessListVO getBusinessProcessList() throws RemoteException {
		BusinessProcessListPO po=listBl.getBills();
		
		BusinessProcessListVO vo=ListPOVOConvertor.businessProcessListPOtoVO(po);
		
		return vo;
	}

	@Override
	public ArrayList<UserVO> getWareKeeperList() throws RemoteException {
		ArrayList<UserVO> vo=listBl.getWareKeeperList();
		
		return vo;
	}

	@Override
	public ArrayList<UserVO> getSalesmanList() throws RemoteException {
		ArrayList<UserVO> vo=listBl.getSalesmanList();
		
		return vo;
	}

	@Override
	public ArrayList<UserVO> getFinancialStaffList() throws RemoteException {
		ArrayList<UserVO> vo=listBl.getFinancialStaffList();
		
		return vo;
	}
	
	@Override
	public ArrayList<UserVO> getManagerList() throws RemoteException {
		ArrayList<UserVO> vo=listBl.getManagerList();
		
		return vo;
	}

	@Override
	public ArrayList<CustomerInfoVO> getCustomerList() throws RemoteException {
		ArrayList<CustomerInfoVO> vo=listBl.getCustomerList();
		
		return vo;
	}
}
