package blDrivers.listBlDrivers;

import static org.junit.Assert.assertEquals;

import java.rmi.RemoteException;

import org.junit.Test;

import assistant.type.UserPositionEnum;
import blImpl.listBl.BusinessProcessListBlController;
import blService.listBlService.BusinessProcessListBlService;

public class BusinessProcessListBlService_Driver {
	BusinessProcessListBlService service=new BusinessProcessListBlController();
	
	
	@Test
	public void test_getBusinessProcessList() throws RemoteException{
		service.getBusinessProcessList();
	}
	
	@Test
	public void test_getWareKeeperList() throws RemoteException{
		assertEquals(UserPositionEnum.WAREKEPPER, service.getWareKeeperList().get(0).getPosition());
	}
	
	@Test
	public void test_getSalesmanList() throws RemoteException{
		assertEquals(UserPositionEnum.SALESMAN, service.getSalesmanList().get(0).getPosition());
	}
	
	@Test
	public void test_getFinancialStaffList() throws RemoteException{
		assertEquals(UserPositionEnum.FINANCE, service.getFinancialStaffList().get(0).getPosition());
	}
	
	@Test
	public void test_getManagerList() throws RemoteException{
		assertEquals(UserPositionEnum.MANAGER, service.getManagerList().get(0).getPosition());
	}
	
	@Test
	public void test_getCustomerList() throws RemoteException{
		service.getCustomerList();
	}
}
