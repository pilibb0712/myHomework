package blStubs.billStubs.viewBillBlStubs;

import java.rmi.RemoteException;
import java.util.ArrayList;

import assistant.type.BillCategoryEnum;
import assistant.type.BillStateEnum;
import assistant.utility.Date;
import blService.billService.viewBillBlService.ViewFinanceStaffBillBlService;
import vo.BillEntryVO;
import vo.CashExpenseBillVO;
import vo.PaymentBillVO;
import vo.ReceiptBillVO;
import vo.UserInfoVO;
/**
 * 查看财务类单据逻辑接口的桩
 * @author guxinyu
 * @version 2017.12.6
 *
 */
public class ViewFinanceStaffBillBlService_Stub implements ViewFinanceStaffBillBlService{
	@Override
	public PaymentBillVO getPaymentBill(String billId) throws RemoteException {
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
		
		PaymentBillVO toReturn=new PaymentBillVO(maker1,"一号账户",entries,7500.0,"送货上门",date);
		toReturn.setApproveDate(new Date());
		UserInfoVO approver=new UserInfoVO();
		approver.setName("总经理");
		approver.setId("000002");
		toReturn.setApprover(approver);
		toReturn.setApproverComment("通过");
		toReturn.setBillStateEnum(BillStateEnum.DONE);
		toReturn.setCategoryEnum(BillCategoryEnum.CASH_EXPENSE_BILL);
		UserInfoVO executor=new UserInfoVO();
		executor.setName("总经理");
		executor.setId("000002");
		toReturn.setExecutor(executor);
		toReturn.setFinishDate(new Date());
		
		return toReturn;
	}

	@Override
	public ReceiptBillVO getReceiptBill(String billId) throws RemoteException {
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
		
		ReceiptBillVO toReturn=new ReceiptBillVO(maker1,"一号账户",entries,7500.0,"送货上门",date);
		toReturn.setApproveDate(new Date());
		UserInfoVO approver=new UserInfoVO();
		approver.setName("总经理");
		approver.setId("000002");
		toReturn.setApprover(approver);
		toReturn.setApproverComment("通过");
		toReturn.setBillStateEnum(BillStateEnum.DONE);
		toReturn.setCategoryEnum(BillCategoryEnum.CASH_EXPENSE_BILL);
		UserInfoVO executor=new UserInfoVO();
		executor.setName("总经理");
		executor.setId("000002");
		toReturn.setExecutor(executor);
		toReturn.setFinishDate(new Date());
		
		return toReturn;
	}

	@Override
	public CashExpenseBillVO getCashExpenseBill(String billId) throws RemoteException {
		BillEntryVO entry1=new BillEntryVO("客厅大灯","5000");
		BillEntryVO entry2=new BillEntryVO("床头灯","500");
		BillEntryVO entry3=new BillEntryVO("过道灯","2000");
		BillEntryVO entry4=new BillEntryVO("过道灯","2000");
		BillEntryVO entry5=new BillEntryVO("客厅大灯","5000");
		BillEntryVO entry6=new BillEntryVO("床头灯","500");
		BillEntryVO entry7=new BillEntryVO("过道灯","2000");
		BillEntryVO entry8=new BillEntryVO("客厅大灯","5000");
		BillEntryVO entry9=new BillEntryVO("床头灯","500");
		BillEntryVO entry10=new BillEntryVO("过道灯","2000");
		BillEntryVO entry11=new BillEntryVO("客厅大灯","5000");
		BillEntryVO entry12=new BillEntryVO("床头灯","500");
		BillEntryVO entry13=new BillEntryVO("过道灯","2000");
		ArrayList<BillEntryVO> entries=new ArrayList<BillEntryVO>();
		entries.add(entry1);
		entries.add(entry2);
		entries.add(entry3);
		entries.add(entry4);
		entries.add(entry5);
		entries.add(entry6);
		entries.add(entry7);
		entries.add(entry8);
		entries.add(entry9);
		entries.add(entry10);
		entries.add(entry11);
		entries.add(entry12);
		entries.add(entry13);
		UserInfoVO maker1=new UserInfoVO();
		maker1.setName("张三");
		maker1.setId("123456");
		
		Date date=new Date();
		
		CashExpenseBillVO toReturn=new CashExpenseBillVO(maker1,"一号账户",entries,7500.0,
				"不想送货上门不想送货上门不想送货上门不想送货上门不想送货上门不想送货上门不想送货上门不想送货上门不想送货上门不想送货上门不想送货上门不想送货上门"
				,date);
		toReturn.setApproveDate(new Date());
		UserInfoVO approver=new UserInfoVO();
		approver.setName("总经理");
		approver.setId("000002");
		toReturn.setApprover(approver);
		toReturn.setApproverComment("通过通过通过通过通过通过通过通过通过通过通过通过通过通过通过通过通过通过通过通过通过通过通过通过通过通过通过通过");
		toReturn.setBillStateEnum(BillStateEnum.DONE);
		toReturn.setCategoryEnum(BillCategoryEnum.CASH_EXPENSE_BILL);
		UserInfoVO executor=new UserInfoVO();
		executor.setName("总经理");
		executor.setId("000002");
		toReturn.setExecutor(executor);
		toReturn.setFinishDate(new Date());
		
		return toReturn;
	}
}
