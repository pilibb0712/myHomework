package blStubs.billStubs.billStateBlStubs;

import java.rmi.RemoteException;
import java.util.ArrayList;

import assistant.type.BillCategoryEnum;
import assistant.type.BillStateEnum;
import assistant.utility.Date;
import blService.billService.billStateBlService.FinancialStaffBillStateBlService;
import vo.BillEntryVO;
import vo.CashExpenseBillVO;
import vo.PaymentBillVO;
import vo.PurchaseBillVO;
import vo.PurchaseReturnBillVO;
import vo.ReceiptBillVO;
import vo.SalesBillVO;
import vo.SalesReturnBillVO;
import vo.UserInfoVO;

/**
 * 财务人员单据状态逻辑接口桩
 * @author guxinyu
 * @version 2017.12.9
 *
 */
public class FinancialStaffBillStateBlService_Stub implements FinancialStaffBillStateBlService{

	@Override
	public ArrayList<PurchaseBillVO> getPurchaseBillList(BillStateEnum state) throws RemoteException {
		// TODO Auto-generated method stub
		return null;
	}
	
	private PurchaseBillVO newAPurchaseBill(){
		
		return null;
	}

	@Override
	public ArrayList<PurchaseReturnBillVO> getPurchaseReturnBillList(BillStateEnum state) throws RemoteException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ArrayList<SalesBillVO> getSalesBillList(BillStateEnum state) throws RemoteException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ArrayList<SalesReturnBillVO> getSalesReturnBillList(BillStateEnum state) throws RemoteException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ArrayList<CashExpenseBillVO> getCashExpenseBillList(BillStateEnum state) throws RemoteException {
		ArrayList<CashExpenseBillVO> billList=new ArrayList<CashExpenseBillVO>();
		
		for(int i=0;i<50;i++){
			billList.add(newACashExpenseBill());
		}
		
		return billList;
	}
	
	private CashExpenseBillVO newACashExpenseBill(){
		BillEntryVO entry1=new BillEntryVO("客厅大灯","5000");
		BillEntryVO entry2=new BillEntryVO("床头灯","500");
		BillEntryVO entry3=new BillEntryVO("过道灯","2000");
		ArrayList<BillEntryVO> entries=new ArrayList<BillEntryVO>();
		entries.add(entry1);
		entries.add(entry2);
		entries.add(entry3);
		UserInfoVO maker1=new UserInfoVO();
		maker1.setName("张三");
		maker1.setId("123456");
		Date date=new Date();
		CashExpenseBillVO bill1=new CashExpenseBillVO("XJFYD-20160530-00001",
				maker1,"一号账户",entries,7500.0,
				"不想送货上门"
				,date);
		bill1.setApproveDate(new Date());
		UserInfoVO approver=new UserInfoVO();
		approver.setName("总经理");
		approver.setId("000002");
		bill1.setApprover(approver);
		bill1.setApproverComment("通过");
		bill1.setBillStateEnum(BillStateEnum.DONE);
		bill1.setCategoryEnum(BillCategoryEnum.CASH_EXPENSE_BILL);
		UserInfoVO executor=new UserInfoVO();
		executor.setName("总经理");
		executor.setId("000002");
		bill1.setExecutor(executor);
		bill1.setFinishDate(new Date());
		
		return bill1;
	}

	@Override
	public ArrayList<PaymentBillVO> getPaymentBillList(BillStateEnum state) throws RemoteException {
		ArrayList<PaymentBillVO> billList=new ArrayList<PaymentBillVO>();
		
		for(int i=0;i<50;i++){
			billList.add(newAPaymentBill());
		}
		
		return billList;
	}
	
	private PaymentBillVO newAPaymentBill(){
		BillEntryVO entry1=new BillEntryVO("客厅大灯","5000");
		BillEntryVO entry2=new BillEntryVO("床头灯","500");
		BillEntryVO entry3=new BillEntryVO("过道灯","2000");
		ArrayList<BillEntryVO> entries=new ArrayList<BillEntryVO>();
		entries.add(entry1);
		entries.add(entry2);
		entries.add(entry3);
		UserInfoVO maker1=new UserInfoVO();
		maker1.setName("张三");
		maker1.setId("123456");
		Date date=new Date();
		PaymentBillVO bill1=new PaymentBillVO("XJFYD-20160530-00001",
				maker1,"一号账户",entries,7500.0,
				"不想送货上门"
				,date);
		bill1.setApproveDate(new Date());
		UserInfoVO approver=new UserInfoVO();
		approver.setName("总经理");
		approver.setId("000002");
		bill1.setApprover(approver);
		bill1.setApproverComment("通过");
		bill1.setBillStateEnum(BillStateEnum.DONE);
		bill1.setCategoryEnum(BillCategoryEnum.CASH_EXPENSE_BILL);
		UserInfoVO executor=new UserInfoVO();
		executor.setName("总经理");
		executor.setId("000002");
		bill1.setExecutor(executor);
		bill1.setFinishDate(new Date());
		
		return bill1;
	}

	@Override
	public ArrayList<ReceiptBillVO> getReceiptBillList(BillStateEnum state) throws RemoteException {
		ArrayList<ReceiptBillVO> billList=new ArrayList<ReceiptBillVO>();
		
		for(int i=0;i<50;i++){
			billList.add(newAReceiptBill());
		}
		
		return billList;
	}
	
	private ReceiptBillVO newAReceiptBill(){
		BillEntryVO entry1=new BillEntryVO("客厅大灯","5000");
		BillEntryVO entry2=new BillEntryVO("床头灯","500");
		BillEntryVO entry3=new BillEntryVO("过道灯","2000");
		ArrayList<BillEntryVO> entries=new ArrayList<BillEntryVO>();
		entries.add(entry1);
		entries.add(entry2);
		entries.add(entry3);
		UserInfoVO maker1=new UserInfoVO();
		maker1.setName("张三");
		maker1.setId("123456");
		Date date=new Date();
		ReceiptBillVO bill1=new ReceiptBillVO("XJFYD-20160530-00001",
				maker1,"一号账户",entries,7500.0,
				"不想送货上门"
				,date);
		bill1.setApproveDate(new Date());
		UserInfoVO approver=new UserInfoVO();
		approver.setName("总经理");
		approver.setId("000002");
		bill1.setApprover(approver);
		bill1.setApproverComment("通过");
		bill1.setBillStateEnum(BillStateEnum.DONE);
		bill1.setCategoryEnum(BillCategoryEnum.CASH_EXPENSE_BILL);
		UserInfoVO executor=new UserInfoVO();
		executor.setName("总经理");
		executor.setId("000002");
		bill1.setExecutor(executor);
		bill1.setFinishDate(new Date());
		
		return bill1;
	}

	@Override
	public boolean deleteUnpassCashExpenseBill(CashExpenseBillVO toDelete) throws RemoteException {
		System.out.println("Deleted");
		return true;
	}

	@Override
	public boolean deleteUnpassPaymentBill(PaymentBillVO toDelete) throws RemoteException {
		System.out.println("Deleted");
		return true;
	}

	@Override
	public boolean deleteUnpassReceiptBill(ReceiptBillVO toDelete) throws RemoteException {
		System.out.println("Deleted");
		return true;
	}
}
