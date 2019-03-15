package rmi.remoteHelper;

import blService.billService.viewBillBlService.ViewFinanceStaffBillBlService;
import blService.billService.viewBillBlService.ViewGiftBillBlService;
import blService.billService.viewBillBlService.ViewSalsemanBillBlService;
import blService.billService.viewBillBlService.ViewWareKeeperBillBlService;
import rmi.linker.viewBillServiceLinker.ViewFinanceStaffBillServiceLinker;
import rmi.linker.viewBillServiceLinker.ViewGiftBillServiceLinker;
import rmi.linker.viewBillServiceLinker.ViewSalesmanBillServiceLinker;
import rmi.linker.viewBillServiceLinker.ViewWareKeeperBillServiceLinker;

public class ViewBillRemoteHelper {
	private static ViewBillRemoteHelper remoteHelper=new ViewBillRemoteHelper();
	
	private ViewBillRemoteHelper(){
		
	}
	
	public static ViewBillRemoteHelper getInstance(){
		return remoteHelper;
	}
	
	public ViewFinanceStaffBillBlService getViewFinanceStaffBillBlService(){
		return ViewFinanceStaffBillServiceLinker.getInstance().getViewFinanceStaffBillBlService();
	}
	
	public ViewSalsemanBillBlService getViewSalsemanBillBlService(){
		return ViewSalesmanBillServiceLinker.getInstance().getViewSalsemanBillBlService();
	}
	
	public ViewWareKeeperBillBlService getViewWareKeeperBillBlService(){
		return ViewWareKeeperBillServiceLinker.getInstance().getViewWareKeeperBillBlService();
	}
	
	public ViewGiftBillBlService getViewGiftBillBlService(){
		return ViewGiftBillServiceLinker.getInstance().getViewGiftBillBlService();
	}
}
