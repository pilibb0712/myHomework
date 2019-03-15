package blMocks.financeBlMocks;

import blImpl.financeBl.NewFinanceBillsBl;
import vo.CashExpenseBillVO;
import vo.PaymentBillVO;
import vo.ReceiptBillVO;

public class MockNewFinanceBills extends NewFinanceBillsBl{
	public boolean saveCashExpenseBill(CashExpenseBillVO toSave){
		System.out.println(toSave.getBillId()+"is successfully saved.");
		return true;
	}
	
	public boolean savePaymentBill(PaymentBillVO toSave){
		System.out.println(toSave.getBillId()+"is successfully saved.");
		return true;
	}
	
	public boolean saveReceiptBill(ReceiptBillVO toSave){
		System.out.println(toSave.getBillId()+"is successfully saved.");
		return true;
	}
}
