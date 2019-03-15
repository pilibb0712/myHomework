package blDrivers.billBlDrivers;

import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.Before;
import org.junit.Test;

import assistant.type.BillStateEnum;
import blInteract.billBlInteract.BillBlInteractServiceFactory;
import blInteract.billBlInteract.InventoryLossBillBlService;
import po.InventoryLossBillPO;

public class InventoryLossBillBl_Driver {
	
	private InventoryLossBillBlService service = new BillBlInteractServiceFactory().getInventoryLossBillBlService();
	
	@Before
	public void setUp() throws Exception {
	}

	@Test
	public void driveGetBillListByState(){
		ArrayList<InventoryLossBillPO> list =service.getBillListByState(BillStateEnum.TBD);
		assertEquals("KCBSD-20180104-00001",list.get(0).getId());
		System.out.println(list.get(0).getCreateDate().getYMDDate());
	}

}
