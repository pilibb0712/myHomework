package blDrivers.financeBlDrivers;

import java.rmi.RemoteException;
import java.util.ArrayList;

import assistant.type.BillCategoryEnum;
import assistant.type.UserPositionEnum;
import blImpl.financeBl.NewFinanceBillsBlController;
import blService.financeBlService.NewFinanceBillsBlService;
import vo.BillEntryVO;
import vo.CashExpenseBillVO;
import vo.UserInfoVO;

public class NewFinanceBillsBlService_Driver {
	private static NewFinanceBillsBlService service=new NewFinanceBillsBlController();
	
	public static void main(String[] args){
		try {
		//	test_saveCashExpenseBillDraft();
			test_newCashExpenseBill();
		} catch (RemoteException e) {
			e.printStackTrace();
		}
	}
	
	public static void test_saveCashExpenseBillDraft() throws RemoteException{
		CashExpenseBillVO ceBill=new CashExpenseBillVO();
		ceBill.setCategoryEnum(BillCategoryEnum.CASH_EXPENSE_BILL);
		ceBill.setSum(3000);
		ceBill.setBankAccount("account1");
		ArrayList<BillEntryVO> en=new ArrayList<BillEntryVO>();
		BillEntryVO entry=new BillEntryVO("客厅大灯","3000");
		en.add(entry);
		ceBill.setEntries(en);
		ceBill.setCreaterComment("送货上门");
		UserInfoVO user=new UserInfoVO("财务人员","123456",UserPositionEnum.FINANCE);
		ceBill.setCreater(user);
		
		
		if(service.saveCashExpenseBillDraft(ceBill)){
			System.out.println(ceBill.getId()+" is saved as a draft.");
		}else{
			System.out.println("Fail to save "+ceBill.getId()+" as a draft.");
		}
		
	}
	
	public static void test_newCashExpenseBill() throws RemoteException{
		CashExpenseBillVO ceBill=new CashExpenseBillVO();
		ceBill.setCategoryEnum(BillCategoryEnum.CASH_EXPENSE_BILL);
		ceBill.setSum(3000);
		ceBill.setBankAccount("account1");
		ArrayList<BillEntryVO> en=new ArrayList<BillEntryVO>();
		BillEntryVO entry=new BillEntryVO("客厅大灯","3000");
		en.add(entry);
		ceBill.setEntries(en);
		ceBill.setCreaterComment("送货上门");
		UserInfoVO user=new UserInfoVO("财务人员","123456",UserPositionEnum.FINANCE);
		ceBill.setCreater(user);
		
		
		if(service.newCashExpenseBill(ceBill)){
			System.out.println(ceBill.getId()+" is submitted.");
		}else{
			System.out.println("Fail to submit "+ceBill.getId());
		}
		
	}
}
