package rmi.dataRemoteObject.financeRemoteObject;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;

import blImpl.financeBl.NewFinanceBillsBlController;
import blService.financeBlService.NewFinanceBillsBlService;
import vo.CashExpenseBillVO;
import vo.PaymentBillVO;
import vo.ReceiptBillVO;
import vo.UserInfoVO;

public class NewFinanceBillsRemoteObject extends UnicastRemoteObject implements NewFinanceBillsBlService{

	/**
	 * 
	 */
	private static final long serialVersionUID = 2070468375762088686L;
	
	public NewFinanceBillsRemoteObject() throws RemoteException {
		super();
	}

	NewFinanceBillsBlService service=new NewFinanceBillsBlController();
	
	@Override
	public boolean newCashExpenseBill(CashExpenseBillVO toSave) throws RemoteException {
		return service.newCashExpenseBill(toSave);
	}

	@Override
	public boolean saveCashExpenseBillDraft(CashExpenseBillVO toSave) throws RemoteException {
		return service.saveCashExpenseBillDraft(toSave);
	}

	@Override
	public ArrayList<CashExpenseBillVO> getCashExpenseBillDraftList(UserInfoVO creator) throws RemoteException {
		return service.getCashExpenseBillDraftList(creator);
	}

	@Override
	public boolean newPaymentBill(PaymentBillVO toSave) throws RemoteException {
		return service.newPaymentBill(toSave);
	}

	@Override
	public boolean savePaymentBillDraft(PaymentBillVO toSave) throws RemoteException {
		return service.savePaymentBillDraft(toSave);
	}

	@Override
	public ArrayList<PaymentBillVO> getPaymentBillDraftList(UserInfoVO creator) throws RemoteException {
		return service.getPaymentBillDraftList(creator);
	}

	@Override
	public boolean newReceiptBill(ReceiptBillVO toSave) throws RemoteException {
		return service.newReceiptBill(toSave);
	}

	@Override
	public boolean saveReceiptBillDraft(ReceiptBillVO toSave) throws RemoteException {
		return service.saveReceiptBillDraft(toSave);
	}

	@Override
	public ArrayList<ReceiptBillVO> getReceiptBillDraftList(UserInfoVO creator) throws RemoteException {
		return service.getReceiptBillDraftList(creator);
	}

	@Override
	public boolean deleteCashExpenseBillDraft(CashExpenseBillVO toDelete) throws RemoteException {
		return service.deleteCashExpenseBillDraft(toDelete);
	}

	@Override
	public boolean deletePaymentBillDraft(PaymentBillVO toDelete) throws RemoteException {
		return service.deletePaymentBillDraft(toDelete);
	}

	@Override
	public boolean deleteReceiptBillDraft(ReceiptBillVO toDelete) throws RemoteException {
		return service.deleteReceiptBillDraft(toDelete);
	}

}
