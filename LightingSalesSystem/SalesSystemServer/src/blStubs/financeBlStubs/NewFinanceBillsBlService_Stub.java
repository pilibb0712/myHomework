package blStubs.financeBlStubs;

import java.rmi.RemoteException;
import java.util.ArrayList;

import assistant.utility.Date;
import blService.financeBlService.NewFinanceBillsBlService;
import vo.BillEntryVO;
import vo.CashExpenseBillVO;
import vo.PaymentBillVO;
import vo.ReceiptBillVO;

/**
 * 新建财务类单据逻辑接口的桩
 * @author guxinyu
 * @version 2017.12.8
 *
 */
public class NewFinanceBillsBlService_Stub implements NewFinanceBillsBlService{

	@Override
	public boolean newCashExpenseBill(CashExpenseBillVO toSave) throws RemoteException {
		System.out.println(toSave.getId()+"is successfully saved.");
		return true;
	}

	@Override
	public boolean newPaymentBill(PaymentBillVO toSave) throws RemoteException {
		System.out.println(toSave.getId()+"is successfully saved.");
		return true;
	}

	@Override
	public boolean newReceiptBill(ReceiptBillVO toSave) throws RemoteException {
		System.out.println(toSave.getId()+"is successfully saved.");
		return true;
	}

	@Override
	public ArrayList<CashExpenseBillVO> getCashExpenseBillDraftList() throws RemoteException {
		CashExpenseBillVO bill1=new CashExpenseBillVO();
		bill1.setBankAccount("account1");
		bill1.setId("XJFYD-20171201-00001");
		bill1.setCreateDate(new Date());
		ArrayList<BillEntryVO> entries1=new ArrayList<BillEntryVO>();
		entries1.add(new BillEntryVO("客厅大灯", "5000"));
		entries1.add(new BillEntryVO("床头灯", "1000"));
		entries1.add(new BillEntryVO("过道灯", "2000"));
		entries1.add(new BillEntryVO("厨房灯", "500"));
		entries1.add(new BillEntryVO("台灯", "150"));
		bill1.setEntries(entries1);
		bill1.setSum(8650.0);
		bill1.setCreaterComment("送货上门");
		
		CashExpenseBillVO bill2=new CashExpenseBillVO();
		bill2.setId("XJFYD-20171201-00002");
		bill2.setBankAccount("account2");
		bill2.setCreateDate(new Date());
		ArrayList<BillEntryVO> entries2=new ArrayList<BillEntryVO>();
		entries2.add(new BillEntryVO("客厅大灯", "5000"));
		entries2.add(new BillEntryVO("床头灯", "1000"));
		entries2.add(new BillEntryVO("过道灯", "2000"));
		entries2.add(new BillEntryVO("厨房灯", "500"));
		entries2.add(new BillEntryVO("台灯", "150"));
		bill2.setEntries(entries2);
		bill2.setSum(8650.0);
		bill2.setCreaterComment("送货上门");
		
		CashExpenseBillVO bill3=new CashExpenseBillVO();
		bill3.setId("XJFYD-20171201-00003");
		bill3.setBankAccount("account3");
		bill3.setCreateDate(new Date());
		ArrayList<BillEntryVO> entries3=new ArrayList<BillEntryVO>();
		entries3.add(new BillEntryVO("客厅大灯", "5000"));
		entries3.add(new BillEntryVO("床头灯", "1000"));
		entries3.add(new BillEntryVO("过道灯", "2000"));
		entries3.add(new BillEntryVO("厨房灯", "500"));
		entries3.add(new BillEntryVO("台灯", "150"));
		bill3.setEntries(entries3);
		bill3.setSum(8650.0);
		bill3.setCreaterComment("送货上门");
		
		ArrayList<CashExpenseBillVO> bills=new ArrayList<CashExpenseBillVO>();
		bills.add(bill1);
		bills.add(bill2);
		bills.add(bill3);
		
		return bills;
	}

	@Override
	public ArrayList<PaymentBillVO> getPaymentBillDraftList() throws RemoteException {
		PaymentBillVO bill1=new PaymentBillVO();
		bill1.setId("FKD-20171201-00001");
		bill1.setBankAccount("account1");
		bill1.setCreateDate(new Date());
		ArrayList<BillEntryVO> entries1=new ArrayList<BillEntryVO>();
		entries1.add(new BillEntryVO("客厅大灯", "5000"));
		entries1.add(new BillEntryVO("床头灯", "1000"));
		entries1.add(new BillEntryVO("过道灯", "2000"));
		entries1.add(new BillEntryVO("厨房灯", "500"));
		entries1.add(new BillEntryVO("台灯", "150"));
		bill1.setEntries(entries1);
		bill1.setSum(8650.0);
		bill1.setCreaterComment("送货上门");
		
		PaymentBillVO bill2=new PaymentBillVO();
		bill2.setId("FKD-20171201-00002");
		bill2.setBankAccount("account2");
		bill2.setCreateDate(new Date());
		ArrayList<BillEntryVO> entries2=new ArrayList<BillEntryVO>();
		entries2.add(new BillEntryVO("客厅大灯", "5000"));
		entries2.add(new BillEntryVO("床头灯", "1000"));
		entries2.add(new BillEntryVO("过道灯", "2000"));
		entries2.add(new BillEntryVO("厨房灯", "500"));
		entries2.add(new BillEntryVO("台灯", "150"));
		bill2.setEntries(entries2);
		bill2.setSum(8650.0);
		bill2.setCreaterComment("送货上门");
		
		PaymentBillVO bill3=new PaymentBillVO();
		bill3.setId("FKD-20171201-00003");
		bill3.setBankAccount("account3");
		bill3.setCreateDate(new Date());
		ArrayList<BillEntryVO> entries3=new ArrayList<BillEntryVO>();
		entries3.add(new BillEntryVO("客厅大灯", "5000"));
		entries3.add(new BillEntryVO("床头灯", "1000"));
		entries3.add(new BillEntryVO("过道灯", "2000"));
		entries3.add(new BillEntryVO("厨房灯", "500"));
		entries3.add(new BillEntryVO("台灯", "150"));
		bill3.setEntries(entries3);
		bill3.setSum(8650.0);
		bill3.setCreaterComment("送货上门");
		
		ArrayList<PaymentBillVO> bills=new ArrayList<PaymentBillVO>();
		bills.add(bill1);
		bills.add(bill2);
		bills.add(bill3);
		
		return bills;
	}

	@Override
	public ArrayList<ReceiptBillVO> getReceiptBillDraftList() throws RemoteException {
		ReceiptBillVO bill1=new ReceiptBillVO();
		bill1.setId("SKD-20171201-00001");
		bill1.setBankAccount("account1");
		bill1.setCreateDate(new Date());
		ArrayList<BillEntryVO> entries1=new ArrayList<BillEntryVO>();
		entries1.add(new BillEntryVO("客厅大灯", "5000"));
		entries1.add(new BillEntryVO("床头灯", "1000"));
		entries1.add(new BillEntryVO("过道灯", "2000"));
		entries1.add(new BillEntryVO("厨房灯", "500"));
		entries1.add(new BillEntryVO("台灯", "150"));
		bill1.setEntries(entries1);
		bill1.setSum(8650.0);
		bill1.setCreaterComment("送货上门");
		
		ReceiptBillVO bill2=new ReceiptBillVO();
		bill2.setId("SKD-20171201-00002");
		bill2.setBankAccount("account2");
		bill2.setCreateDate(new Date());
		ArrayList<BillEntryVO> entries2=new ArrayList<BillEntryVO>();
		entries2.add(new BillEntryVO("客厅大灯", "5000"));
		entries2.add(new BillEntryVO("床头灯", "1000"));
		entries2.add(new BillEntryVO("过道灯", "2000"));
		entries2.add(new BillEntryVO("厨房灯", "500"));
		entries2.add(new BillEntryVO("台灯", "150"));
		bill2.setEntries(entries2);
		bill2.setSum(8650.0);
		bill2.setCreaterComment("送货上门");
		
		ReceiptBillVO bill3=new ReceiptBillVO();
		bill3.setId("SKD-20171201-00003");
		bill3.setBankAccount("account3");
		bill3.setCreateDate(new Date());
		ArrayList<BillEntryVO> entries3=new ArrayList<BillEntryVO>();
		entries3.add(new BillEntryVO("客厅大灯", "5000"));
		entries3.add(new BillEntryVO("床头灯", "1000"));
		entries3.add(new BillEntryVO("过道灯", "2000"));
		entries3.add(new BillEntryVO("厨房灯", "500"));
		entries3.add(new BillEntryVO("台灯", "150"));
		bill3.setEntries(entries3);
		bill3.setSum(8650.0);
		bill3.setCreaterComment("送货上门");
		
		ArrayList<ReceiptBillVO> bills=new ArrayList<ReceiptBillVO>();
		bills.add(bill1);
		bills.add(bill2);
		bills.add(bill3);
		
		return bills;
	}

	@Override
	public boolean saveCashExpenseBillDraft(CashExpenseBillVO toSave) throws RemoteException {
		System.out.println("Draft Saved.");
		return true;
	}

	@Override
	public boolean savePaymentBillDraft(PaymentBillVO toSave) throws RemoteException {
		System.out.println("Draft Saved.");
		return true;
	}

	@Override
	public boolean saveReceiptBillDraft(ReceiptBillVO toSave) throws RemoteException {
		System.out.println("Draft Saved.");
		return true;
	}

}
