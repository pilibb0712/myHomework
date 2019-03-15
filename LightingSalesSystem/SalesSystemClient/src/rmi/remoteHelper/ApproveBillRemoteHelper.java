package rmi.remoteHelper;

import blService.billService.approveBillBlService.ApproveCashExpenseBillBlService;
import blService.billService.approveBillBlService.ApproveGiftBillBlService;
import blService.billService.approveBillBlService.ApproveInventoryLossBillBlService;
import blService.billService.approveBillBlService.ApproveInventoryOverBillBlService;
import blService.billService.approveBillBlService.ApproveInventoryWarningBillBlService;
import blService.billService.approveBillBlService.ApprovePaymentBillBlService;
import blService.billService.approveBillBlService.ApprovePurchaseBillBlService;
import blService.billService.approveBillBlService.ApprovePurchaseReturnBillBlService;
import blService.billService.approveBillBlService.ApproveReceiptBillBlService;
import blService.billService.approveBillBlService.ApproveSalesBillBlService;
import blService.billService.approveBillBlService.ApproveSalesReturnBillBlService;
import rmi.linker.approveBillServiceLinker.ApproveCashExpenseBillServiceLinker;
import rmi.linker.approveBillServiceLinker.ApproveGiftBillServiceLinker;
import rmi.linker.approveBillServiceLinker.ApproveInventoryLossBillServiceLinker;
import rmi.linker.approveBillServiceLinker.ApproveInventoryOverBillServiceLinker;
import rmi.linker.approveBillServiceLinker.ApproveInventoryWarningBillServiceLinker;
import rmi.linker.approveBillServiceLinker.ApprovePaymentBillServiceLinker;
import rmi.linker.approveBillServiceLinker.ApprovePurchaseBillServiceLinker;
import rmi.linker.approveBillServiceLinker.ApprovePurchaseReturnBillServiceLinker;
import rmi.linker.approveBillServiceLinker.ApproveReceiptBillServiceLinker;
import rmi.linker.approveBillServiceLinker.ApproveSalesBillServiceLinker;
import rmi.linker.approveBillServiceLinker.ApproveSalesReturnBillServiceLinker;

public class ApproveBillRemoteHelper {
	
	private static ApproveBillRemoteHelper remoteHelper =new ApproveBillRemoteHelper();
	
	private ApproveBillRemoteHelper(){
		
	}
	
	public static ApproveBillRemoteHelper getInstance(){
		return remoteHelper;
	}
	
	public ApproveGiftBillBlService getApproveGiftBillBlService(){
		return ApproveGiftBillServiceLinker.getInstance().getApproveGiftBillBlService();
	}
	
	public ApproveCashExpenseBillBlService getApproveCashExpenseBillBlService(){
		return ApproveCashExpenseBillServiceLinker.getInstance().getApproveCashExpenseBillBlService();
	}
	
	public ApproveInventoryLossBillBlService getApproveInventoryLossBillBlService(){
		return ApproveInventoryLossBillServiceLinker.getInstance().getApproveInventoryLossBillBlService();
	}
	
	public ApproveInventoryWarningBillBlService getApproveInventoryWarningBillBlService(){
		return ApproveInventoryWarningBillServiceLinker.getInstance().getApproveInventoryWarningBillBlService();
	}
	public ApproveInventoryOverBillBlService getApproveInventoryOverBillBlService(){
		return ApproveInventoryOverBillServiceLinker.getInstance().getApproveInventoryOverBillBlService();
	}
	
	public ApprovePaymentBillBlService getApprovePaymentBillBlService(){
		return ApprovePaymentBillServiceLinker.getInstance().getApprovePaymentBillBlService();
	}
	public ApproveReceiptBillBlService getApproveReceiptBillBlService(){
		return ApproveReceiptBillServiceLinker.getInstance().getApproveReceiptBillBlService();
	}
	public ApprovePurchaseBillBlService getApprovePurchaseBillBlService(){
		return ApprovePurchaseBillServiceLinker.getInstance().getApprovePurchaseBillBlService();
	}
	public ApprovePurchaseReturnBillBlService getApprovePurchaseReturnBillBlService(){
		return ApprovePurchaseReturnBillServiceLinker.getInstance().getApprovePurchaseReturnBillBlService();
	}
	public ApproveSalesBillBlService getApproveSalesBillBlService(){
		return ApproveSalesBillServiceLinker.getInstance().getApproveSalesBillBlService();
	}
	public ApproveSalesReturnBillBlService getApproveSalesReturnBillBlService(){
		return ApproveSalesReturnBillServiceLinker.getInstance().getApproveSalesReturnBillBlService();
	}
	
}
