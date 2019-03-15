package blStubs.listBlStubs;

import blService.listBlService.NewModifyBillBlService;
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

/**
 * 新建红冲单据逻辑接口的桩
 * @author guxinyu
 * @version 2017.11.7
 *
 */
public class NewModifyBillBl_Stub implements NewModifyBillBlService{
	public boolean submitBill(InventoryWarningBillVO toSubmit){
		System.out.println("Is submitted.");
		return true;
	}
	
	public boolean submitBill(InventoryOverBillVO toSubmit){
		System.out.println("Is submitted.");
		return true;
	}
	
	public boolean submitBill(InventoryLossBillVO toSubmit){
		System.out.println("Is submitted.");
		return true;
	}
	
	public boolean submitBill(PurchaseBillVO toSubmit){
		System.out.println("Is submitted.");
		return true;
	}
	
	public boolean submitBill(PurchaseReturnBillVO toSubmit){
		System.out.println("Is submitted.");
		return true;
	}
	
	public boolean submitBill(SalesBillVO toSubmit){
		System.out.println("Is submitted.");
		return true;
	}
	
	public boolean submitBill(SalesReturnBillVO toSubmit){
		System.out.println("Is submitted.");
		return true;
	}
	
	public boolean submitBill(ReceiptBillVO toSubmit){
		System.out.println("Is submitted.");
		return true;
	}
	
	public boolean submitBill(PaymentBillVO toSubmit){
		System.out.println("Is submitted.");
		return true;
	}
	
	public boolean submitBill(CashExpenseBillVO toSubmit){
		System.out.println("Is submitted.");
		return true;
	}
	
	public boolean submitBill(GiftBillVO toSubmit){
		System.out.println("Is submitted.");
		return true;
	}
}
