package rmi.remoteHelper;

import blService.billService.billStateBlService.FinancialStaffBillStateBlService;
import blService.billService.billStateBlService.SalesmanBillStateBlService;
import blService.billService.billStateBlService.WareKeeperBillStateBlService;
import rmi.linker.billStateServiceLinker.FinancialStaffBillStateServiceLinker;
import rmi.linker.billStateServiceLinker.SalesmanBillStateServiceLinker;
import rmi.linker.billStateServiceLinker.WareKeeperBillStateServiceLinker;

public class BillStateRemoteHelper {
	private static BillStateRemoteHelper remoteHelper=new BillStateRemoteHelper();
	
	private BillStateRemoteHelper(){
		
	}
	
	public static BillStateRemoteHelper getInstance(){
		return remoteHelper;
	}
	
	public FinancialStaffBillStateBlService getFinancialStaffBillStateBlService(){
		return FinancialStaffBillStateServiceLinker.getInstance().getFinancialStaffBillStateBlService();
	}
	
	public SalesmanBillStateBlService getSalesmanBillStateBlService(){
		return SalesmanBillStateServiceLinker.getInstance().getSalesmanBillStateBlService();
	}
	
	public WareKeeperBillStateBlService getWareKeeperBillStateBlService(){
		return WareKeeperBillStateServiceLinker.getInstance().getWareKeeperBillStateBlService();
	}
}
