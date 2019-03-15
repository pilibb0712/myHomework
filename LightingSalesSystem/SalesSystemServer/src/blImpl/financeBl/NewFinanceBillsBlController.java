package blImpl.financeBl;

import java.rmi.RemoteException;
import assistant.convertors.FinanceBillsPOVOConvertor;
import assistant.convertors.UserInfoPOVOConvertor;
import java.util.ArrayList;
import blInteract.billBlInteract.BillBlInteractServiceFactory;
import blInteract.billBlInteract.CashExpenseBillBlService;
import blInteract.billBlInteract.PaymentBillBlService;
import blInteract.billBlInteract.ReceiptBillBlService;
import blService.financeBlService.NewFinanceBillsBlService;
import po.CashExpenseBillPO;
import po.PaymentBillPO;
import po.ReceiptBillPO;
import po.UserInfoPO;
import vo.CashExpenseBillVO;
import vo.PaymentBillVO;
import vo.ReceiptBillVO;
import vo.UserInfoVO;

/**
 * 新建财务类单据的控制模块
 * @author guxinyu 
 * @version 2017.12.12
 *
 */
public class NewFinanceBillsBlController implements NewFinanceBillsBlService{
	private BillBlInteractServiceFactory factory=new BillBlInteractServiceFactory();
	private CashExpenseBillBlService cebl=factory.getCashExpenseBillBlService();
	private PaymentBillBlService pbl=factory.getPaymentBillBlService();
	private ReceiptBillBlService rbl=factory.getReceiptBillBlService();
	
	@Override
	public boolean newCashExpenseBill(CashExpenseBillVO toSave) throws RemoteException {
		CashExpenseBillPO po=FinanceBillsPOVOConvertor.cashExpenseBillVOtoPO(toSave);
		
		if(cebl.createBill(po))
			return true;
		else
			return false;
	}

	@Override
	public boolean saveCashExpenseBillDraft(CashExpenseBillVO toSave) throws RemoteException {
		CashExpenseBillPO po=FinanceBillsPOVOConvertor.cashExpenseBillVOtoPO(toSave);
		
		if(cebl.updateDraftBill(po))
			return true;
		else 
			return false;
	}

	@Override
	public ArrayList<CashExpenseBillVO> getCashExpenseBillDraftList(UserInfoVO creator) throws RemoteException {
		UserInfoPO userPO=UserInfoPOVOConvertor.voToPO(creator);
		
		ArrayList<CashExpenseBillPO> drafts=cebl.getDraftBillsListByCreater(userPO);
		
		ArrayList<CashExpenseBillVO> draftsVO=new ArrayList<CashExpenseBillVO>();
		for(int i=0;i<drafts.size();i++){
			CashExpenseBillVO each=FinanceBillsPOVOConvertor.cashExpenseBillPOtoVO(drafts.get(i));
			draftsVO.add(each);
		}
		
		return draftsVO;
	}

	@Override
	public boolean newPaymentBill(PaymentBillVO toSave) throws RemoteException {
		PaymentBillPO po=FinanceBillsPOVOConvertor.paymentBillVOtoPO(toSave);
		
		if(pbl.createBill(po))
			return true;
		else
			return false;
	}

	@Override
	public boolean savePaymentBillDraft(PaymentBillVO toSave) throws RemoteException {
		PaymentBillPO po=FinanceBillsPOVOConvertor.paymentBillVOtoPO(toSave);
		
		if(pbl.updateDraftBill(po))
			return true;
		else
			return false;
	}

	@Override
	public ArrayList<PaymentBillVO> getPaymentBillDraftList(UserInfoVO creator) throws RemoteException {
		UserInfoPO userPO=UserInfoPOVOConvertor.voToPO(creator);
		
		ArrayList<PaymentBillPO> drafts=pbl.getDraftBillsListByCreater(userPO);
		
		ArrayList<PaymentBillVO> draftsVO=new ArrayList<PaymentBillVO>();
 		for(int i=0;i<drafts.size();i++){
			PaymentBillVO each=FinanceBillsPOVOConvertor.paymentBillPOtoVO(drafts.get(i));
			draftsVO.add(each);
		}
		
		return draftsVO;
	}

	@Override
	public boolean newReceiptBill(ReceiptBillVO toSave) throws RemoteException {
		ReceiptBillPO po=FinanceBillsPOVOConvertor.receiptBillVOtoPO(toSave);
		
		if(rbl.createBill(po))
			return true;
		else
			return false;
	}

	@Override
	public boolean saveReceiptBillDraft(ReceiptBillVO toSave) throws RemoteException {
		ReceiptBillPO po=FinanceBillsPOVOConvertor.receiptBillVOtoPO(toSave);
		
		if(rbl.updateDraftBill(po))
			return true;
		else
			return false;
	}

	@Override
	public ArrayList<ReceiptBillVO> getReceiptBillDraftList(UserInfoVO creator) throws RemoteException {
		UserInfoPO userPO=UserInfoPOVOConvertor.voToPO(creator);
		
		ArrayList<ReceiptBillPO> drafts=rbl.getDraftBillsListByCreater(userPO);
		
		ArrayList<ReceiptBillVO> draftsVO=new ArrayList<ReceiptBillVO>();
		for(int i=0;i<draftsVO.size();i++){
			ReceiptBillVO each=FinanceBillsPOVOConvertor.receiptBillPOtoVO(drafts.get(i));
			draftsVO.add(each);
		}
		
		return draftsVO;
	}

	@Override
	public boolean deleteCashExpenseBillDraft(CashExpenseBillVO toDelete) throws RemoteException {
		CashExpenseBillPO po=FinanceBillsPOVOConvertor.cashExpenseBillVOtoPO(toDelete);
		
		if(cebl.hideBill(po))
			return true;
		else
			return false;
	}

	@Override
	public boolean deletePaymentBillDraft(PaymentBillVO toDelete) throws RemoteException {
		PaymentBillPO po=FinanceBillsPOVOConvertor.paymentBillVOtoPO(toDelete);
		
		if(pbl.hideBill(po))
			return true;
		else
			return false;
	}

	@Override
	public boolean deleteReceiptBillDraft(ReceiptBillVO toDelete) throws RemoteException {
		ReceiptBillPO po=FinanceBillsPOVOConvertor.receiptBillVOtoPO(toDelete);
		
		if(rbl.hideBill(po))
			return true;
		else
			return false;
	}

}
