package rmi.remoteHelper;

import rmi.register.approveBillRegister.ApproveCashExpenseBillRegister;
import rmi.register.approveBillRegister.ApproveGiftBillRegister;
import rmi.register.approveBillRegister.ApproveInventoryLossBillRegister;
import rmi.register.approveBillRegister.ApproveInventoryOverBillRegister;
import rmi.register.approveBillRegister.ApproveInventoryWarningBillRegister;
import rmi.register.approveBillRegister.ApprovePaymentBillRegister;
import rmi.register.approveBillRegister.ApprovePurchaseBillRegister;
import rmi.register.approveBillRegister.ApprovePurchaseReturnBillRegister;
import rmi.register.approveBillRegister.ApproveReceiptBillRegister;
import rmi.register.approveBillRegister.ApproveSalesBillRegister;
import rmi.register.approveBillRegister.ApproveSalesReturnBillRegister;

public class ApproveBillRemoteHelper {
	private ApproveGiftBillRegister approveGiftBillRegister = new ApproveGiftBillRegister();
	private ApproveCashExpenseBillRegister approveCashExpenseBillRegister = new ApproveCashExpenseBillRegister();
	private ApproveInventoryLossBillRegister approveInventoryLossBillRegister = new ApproveInventoryLossBillRegister();
	private ApproveInventoryOverBillRegister approveInventoryOverBillRegister = new ApproveInventoryOverBillRegister();
	private ApproveInventoryWarningBillRegister approveInventoryWarningBillRegister = new ApproveInventoryWarningBillRegister();
	private ApprovePurchaseBillRegister approvePurchaseBillRegister = new ApprovePurchaseBillRegister();
	private ApprovePurchaseReturnBillRegister approvePurchaseReturnBillRegister = new ApprovePurchaseReturnBillRegister();
	private ApprovePaymentBillRegister approvePaymentBillRegister = new ApprovePaymentBillRegister();
	private ApproveReceiptBillRegister approveReceiptBillRegister = new ApproveReceiptBillRegister();
	private ApproveSalesBillRegister approveSalesBillRegister = new ApproveSalesBillRegister();
	private ApproveSalesReturnBillRegister approveSalesReturnBillRegister = new ApproveSalesReturnBillRegister();
	
	public ApproveBillRemoteHelper(){
		approveGiftBillRegister.register();
		approveCashExpenseBillRegister.register();
		approveInventoryLossBillRegister.register();
		approveInventoryOverBillRegister.register();
		approveInventoryWarningBillRegister.register();
		approvePaymentBillRegister.register();
		approveReceiptBillRegister.register();
		approvePurchaseReturnBillRegister.register();
		approvePurchaseBillRegister.register();
		approveSalesBillRegister.register();
		approveSalesReturnBillRegister.register();
	}
}
