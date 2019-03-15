package rmi.remoteHelper;

public class RemoteHelperFactory {
	
	public static ApproveBillRemoteHelper getApproveBillRemoteHelper(){
		return ApproveBillRemoteHelper.getInstance();
	}
	
	public static PromotionRemoteHelper getPromotionRemoteHelper(){
		return PromotionRemoteHelper.getInstance();
	}
	
	public static AdminRemoteHelper getAdminRemoteHelper(){
		return AdminRemoteHelper.getInstance();
	}
	
	public static BillStateRemoteHelper getBillStateRemoteHelper(){
		return BillStateRemoteHelper.getInstance();
	}
	
	public static FinanceRemoteHelper getFinanceRemoteHelper(){
		return FinanceRemoteHelper.getInstance();
	} 
	
	public static ListRemoteHelper getListRemoteHelper(){
		return ListRemoteHelper.getInstance();
	}
	
	public static ViewBillRemoteHelper getViewBillRemoteHelper(){
		return ViewBillRemoteHelper.getInstance();
	}
	
	public static CustomerRemoteHelper getCustomerRemoteHelper() {
		return CustomerRemoteHelper.getInstance();
	}
	
	public static LogRemoteHelper getLogRemoteHelper() {
		return LogRemoteHelper.getInstance();
	}
	
	public static SalesRemoteHelper getSalesRemoteHelper() {
		return SalesRemoteHelper.getInstance();
	}
}
