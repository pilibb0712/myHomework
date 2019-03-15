package blStubs.billStubs.billStateBlStubs;

import blService.billService.billStateBlService.WareKeeperBillStateBlService;

/**
 * 库存管理人员单据状态逻辑接口的桩
 * @author guxinyu
 * @version 2017.11.7
 *
 */
public class WareKeeperBillStateBlService_Stub implements WareKeeperBillStateBlService{
	@Override
	//获取通过审核待处理的单据的单据编号列表
	public String[] getToDoList(){
		String[] toReturn={"PurchaseBill/JHD-20160530-00001",
				"SalesReturnBill/XSTHD-20160530-00002",
				"InventoryOverBill/KCBYD-20160530-00003"};
		
		return toReturn;
	}
		
	@Override
	//获取未通过审核的单据的单据编号列表
	public String[] getUnpassedList(){
		String[] toReturn={"InventoryOverBill/KCBYD-20160530-00003",
				"InventoryLossBill/KCBSD-20160530-00005",
				"InventoryWarningBill/KCBJD-20160530-00004"};
		
		return toReturn;
	}
	
	@Override
	//获得已完成的单据的单据的单据编号列表
	public String[] getDoneList(){
		String[] toReturn={"PurchaseBill/JHD-20160530-00003",
				"SalesReturnBill/XSTHD-20160530-00004",
				"InventoryOverBill/KCBYD-20160530-00005"};
		
		return toReturn;
	}

}
