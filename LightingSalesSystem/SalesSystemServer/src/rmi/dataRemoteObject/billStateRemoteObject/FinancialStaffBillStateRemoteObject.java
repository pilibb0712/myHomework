package rmi.dataRemoteObject.billStateRemoteObject;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;

import assistant.type.BillStateEnum;
import blImpl.bill.billStateBl.FinancialStaffBillStateBlController;
import blService.billService.billStateBlService.FinancialStaffBillStateBlService;
import vo.CashExpenseBillVO;
import vo.PaymentBillVO;
import vo.PurchaseBillVO;
import vo.PurchaseReturnBillVO;
import vo.ReceiptBillVO;
import vo.SalesBillVO;
import vo.SalesReturnBillVO;
import vo.UserInfoVO;

public class FinancialStaffBillStateRemoteObject extends UnicastRemoteObject implements FinancialStaffBillStateBlService{

	/**
	 * 
	 */
	private static final long serialVersionUID = -2122700274509915048L;

	public FinancialStaffBillStateRemoteObject() throws RemoteException {
		super();
	}
	
	FinancialStaffBillStateBlService service=new FinancialStaffBillStateBlController();

	@Override
	public ArrayList<PurchaseBillVO> getPurchaseBillList(BillStateEnum state) throws RemoteException {
		return service.getPurchaseBillList(state);
	}

	@Override
	public ArrayList<PurchaseReturnBillVO> getPurchaseReturnBillList(BillStateEnum state) throws RemoteException {
		return service.getPurchaseReturnBillList(state);
	}

	@Override
	public ArrayList<SalesBillVO> getSalesBillList(BillStateEnum state) throws RemoteException {
		return service.getSalesBillList(state);
	}

	@Override
	public ArrayList<SalesReturnBillVO> getSalesReturnBillList(BillStateEnum state) throws RemoteException {
		return service.getSalesReturnBillList(state);
	}

	@Override
	public ArrayList<CashExpenseBillVO> getCashExpenseBillList(BillStateEnum state, UserInfoVO user) throws RemoteException {
		return service.getCashExpenseBillList(state, user);
	}

	@Override
	public ArrayList<PaymentBillVO> getPaymentBillList(BillStateEnum state, UserInfoVO user) throws RemoteException {
		return service.getPaymentBillList(state, user);
	}

	@Override
	public ArrayList<ReceiptBillVO> getReceiptBillList(BillStateEnum state, UserInfoVO user) throws RemoteException {
		return service.getReceiptBillList(state, user);
	}

	@Override
	public boolean deleteUnpassCashExpenseBill(CashExpenseBillVO toDelete) throws RemoteException {
		return service.deleteUnpassCashExpenseBill(toDelete);
	}

	@Override
	public boolean deleteUnpassPaymentBill(PaymentBillVO toDelete) throws RemoteException {
		return service.deleteUnpassPaymentBill(toDelete);
	}

	@Override
	public boolean deleteUnpassReceiptBill(ReceiptBillVO toDelete) throws RemoteException {
		return service.deleteUnpassReceiptBill(toDelete);
	}

	@Override
	public boolean doneCashExpenseBill(CashExpenseBillVO done) throws RemoteException {
		return service.doneCashExpenseBill(done);
	}

	@Override
	public boolean donePaymentBill(PaymentBillVO done) throws RemoteException {
		return service.donePaymentBill(done);
	}

	@Override
	public boolean doneReceiptBill(ReceiptBillVO done) throws RemoteException {
		return service.doneReceiptBill(done);
	}

}
