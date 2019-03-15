package blMocks.listBlMocks;

import vo.CashExpenseBillVO;
import vo.GiftBillVO;
import vo.InventoryLossBillVO;
import vo.InventoryOverBillVO;
import vo.InventoryWarningBillVO;
import vo.PaymentBillVO;
import vo.PurchaseBillVO;
import vo.PurchaseReturnBillVO;
import vo.ReceiptBillVO;
import vo.SalesBillVO;
import vo.SalesReturnBillVO;

public class MockNewModifyCopyBill {
	public boolean saveBill(InventoryWarningBillVO toSubmit){
		System.out.println("Is submitted.");
		return true;
	}

	public boolean saveBill(InventoryOverBillVO toSubmit){
		System.out.println("Is submitted.");
		return true;
	}

	public boolean saveBill(InventoryLossBillVO toSubmit){
		System.out.println("Is submitted.");
		return true;
	}

	public boolean saveBill(PurchaseBillVO toSubmit){
		System.out.println("Is submitted.");
		return true;
	}

	public boolean saveBill(PurchaseReturnBillVO toSubmit){
		System.out.println("Is submitted.");
		return true;
	}

	public boolean saveBill(SalesBillVO toSubmit){
		System.out.println("Is submitted.");
		return true;
	}

	public boolean saveBill(SalesReturnBillVO toSubmit){
		System.out.println("Is submitted.");
		return true;
	}

	public boolean saveBill(ReceiptBillVO toSubmit){
		System.out.println("Is submitted.");
		return true;
	}

	public boolean saveBill(PaymentBillVO toSubmit){
		System.out.println("Is submitted.");
		return true;
	}
	
	public boolean saveBill(CashExpenseBillVO toSubmit){
		System.out.println("Is submitted.");
		return true;
	}

	public boolean saveBill(GiftBillVO toSubmit){
		System.out.println("Is submitted.");
		return true;
	}
}
