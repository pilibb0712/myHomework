package blImpl.bill.viewBillBl;

import java.rmi.RemoteException;
import assistant.convertors.FinanceBillsPOVOConvertor;
import blImpl.bill.billBl.CashExpenseBillBl;
import blImpl.bill.billBl.PaymentBillBl;
import blImpl.bill.billBl.ReceiptBillBl;
import blInteract.billBlInteract.CashExpenseBillBlService;
import blInteract.billBlInteract.PaymentBillBlService;
import blInteract.billBlInteract.ReceiptBillBlService;
import blService.billService.viewBillBlService.ViewFinanceStaffBillBlService;
import po.CashExpenseBillPO;
import po.PaymentBillPO;
import po.ReceiptBillPO;
import vo.CashExpenseBillVO;
import vo.PaymentBillVO;
import vo.ReceiptBillVO;

/**
 * 查看财务类单据的控制模块
 * @author guxinyu
 * @version 2017.12.6
 *
 */
public class ViewFinanceStaffBillBlController implements ViewFinanceStaffBillBlService{

	@Override
	public PaymentBillVO getPaymentBill(String billId) throws RemoteException {
		PaymentBillBlService bl=new PaymentBillBl();
		
		PaymentBillPO po=bl.getBillById(billId);
		PaymentBillVO vo=FinanceBillsPOVOConvertor.paymentBillPOtoVO(po);
		
		return vo;
	}

	@Override
	public ReceiptBillVO getReceiptBill(String billId) throws RemoteException {
		ReceiptBillBlService bl=new ReceiptBillBl();
		
		ReceiptBillPO po=bl.getBillById(billId);
		ReceiptBillVO vo=FinanceBillsPOVOConvertor.receiptBillPOtoVO(po);
		
		return vo;
	}

	@Override
	public CashExpenseBillVO getCashExpenseBill(String billId) throws RemoteException {
		CashExpenseBillBlService bl=new CashExpenseBillBl();
		
		CashExpenseBillPO po=bl.getBillById(billId);
		CashExpenseBillVO vo=FinanceBillsPOVOConvertor.cashExpenseBillPOtoVO(po);
		
		return vo;
	}

	
}
