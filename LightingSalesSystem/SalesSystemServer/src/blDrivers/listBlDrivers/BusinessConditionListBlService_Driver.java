package blDrivers.listBlDrivers;

import java.rmi.RemoteException;

import org.junit.Test;

import blImpl.listBl.BusinessConditionListBlController;
import blService.listBlService.BusinessConditionListBlService;

public class BusinessConditionListBlService_Driver {
	BusinessConditionListBlService service=new BusinessConditionListBlController();
	
	@Test
	public void test_getBusinessConditionList() throws RemoteException{
		service.getBusinessConditionList();
	}
}
