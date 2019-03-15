package rmi.remoteHelper;

import blService.adminBlService.AdminBlService;
import blService.billService.approveBillBlService.ApproveGiftBillBlService;
import rmi.linker.adminServiceLinker.AdminServiceLinker;
import rmi.linker.approveBillServiceLinker.ApproveGiftBillServiceLinker;

public class AdminRemoteHelper {
	private static AdminRemoteHelper remoteHelper =new AdminRemoteHelper();
	
	private AdminRemoteHelper(){
		
	}
	
	public static AdminRemoteHelper getInstance(){
		return remoteHelper;
	}
	
	public AdminBlService getAdminBlService(){
		return AdminServiceLinker.getInstance().getAdminBlService();
	}
}
