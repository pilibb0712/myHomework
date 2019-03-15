package blStubs.billStubs.viewBillBlStubs;

import java.rmi.RemoteException;
import java.util.ArrayList;

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
	public PaymentBillVO getPaymentBill(BillStateEnum billState, String billId) throws RemoteException {
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
		
		PaymentBillVO toReturn=new PaymentBillVO("FKD-20160530-00001",
				maker1,"一号账户",entries,7500.0,"送货上门",date);
		
		return toReturn;
	}

	@Override
	public ReceiptBillVO getReceiptBill(BillStateEnum billState, String billId) throws RemoteException {
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
		
		ReceiptBillVO toReturn=new ReceiptBillVO("SKD-20160530-00001",
				maker1,"一号账户",entries,7500.0,"送货上门",date);
		
		return toReturn;
	}

	@Override
	public CashExpenseBillVO getCashExpenseBill(BillStateEnum billState, String billId) throws RemoteException {
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
		
		CashExpenseBillVO toReturn=new CashExpenseBillVO("XJFYD-20160530-00001",
				maker1,"一号账户",entries,7500.0,"送货上门",date);
		
		return toReturn;
	}
}
