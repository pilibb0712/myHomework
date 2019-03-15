package rmi.remoteHelper;

import blService.listBlService.BusinessConditionListBlService;
import blService.listBlService.BusinessProcessListBlService;
import blService.listBlService.SalesDetailsListBlService;
import rmi.linker.listServiceLinker.BusinessConditionListServiceLinker;
import rmi.linker.listServiceLinker.BusinessProcessListServiceLinker;
import rmi.linker.listServiceLinker.SalesDetailsListServiceLinker;

public class ListRemoteHelper {
	private static ListRemoteHelper remoteHelper=new ListRemoteHelper();
	
	private ListRemoteHelper(){
		
	}
	
	public static ListRemoteHelper getInstance(){
		return remoteHelper;
	}
	
	public BusinessConditionListBlService getBusinessConditionListBlService(){
		return BusinessConditionListServiceLinker.getInstance().getBusinessConditionListBlService();
	}
	
	public BusinessProcessListBlService getBusinessProcessListBlService(){
		return BusinessProcessListServiceLinker.getInstance().getBusinessProcessListBlService();
	}
	
	public SalesDetailsListBlService getSalesDetailsListBlService(){
		return SalesDetailsListServiceLinker.getInstance().getSalesDetailsListBlService();
	}
}
