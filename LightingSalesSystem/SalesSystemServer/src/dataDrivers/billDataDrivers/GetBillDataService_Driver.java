package dataDrivers.billDataDrivers;

import java.util.ArrayList;
import assistant.type.UserPositionEnum;
import dataImpl.billData.GetBillData;
import dataService.billDataService.GetBillDataService;
import po.BillPO;
import po.CashExpenseBillPO;
import po.GiftBillPO;

public class GetBillDataService_Driver{
	
	GetBillDataService getBillDataService = new GetBillData();
	
	/**
	 * 驱动测试根据单据名称和Id读取一张单据。<br/>
	 */
	public void driveGetBillByNameAndId(){
		String billName ;
		String billId ;
		BillPO billPO;
		
		billName = "gift_bill";
		billId = "KCZSD_2017-11-28_14_04_13";
		billPO = getBillDataService.getBillByNameAndId(billName, billId);
		if(billPO==null){System.out.println("不存在该单据");}
		else{System.out.println("该单据为"+billPO.getId());}
		
//		billName = "gift_bill";
//		billId = "KCZSD-20171109-00003";
//		billPO = getBillDataService.getBillByNameAndId(billName, billId);
//		if(billPO==null){System.out.println("不存在该单据");}
//		else{System.out.println("该单据为"+billPO.getId());}
		
//		billName = "cash_expense_bill";
//		billId = "XJFYD-20171119-00003";
//		billPO = getBillDataService.getBillByNameAndId(billName, billId);
//		if(billPO==null){System.out.println("不存在该单据");}
//		else{System.out.println("该单据为"+billPO.getId()+" account"+((CashExpenseBillPO)billPO).getBankAccount());}
		
//		billName = "gift_bill";
//		billId = "KCZSD-20171119-00001";
//		billPO = getBillDataService.getBillByNameAndId(billName, billId);
//		if(billPO==null){System.out.println("不存在该单据");}
//		else{System.out.println("该单据为"+billPO.getId()+" 单据状态为"+billPO.getBillStateEnum().toString());}
	}
	
	/**
	 * 驱动测试根据单据名称和状态读取一系列单据。<br/>
	 */
	public void driveGetBillListByStateAndName(){
		String billState = "tbd";
		String billName = "gift_bill";
		ArrayList<BillPO> bills = getBillDataService.getBillListByStateAndName(billState, billName);
		for(int i=0;i<bills.size();i++){
			System.out.println(bills.get(i).getId());
		}
	}
	
	/**
	 * 驱动测试根据单据状态读取一系列单据。<br/>
	 */
	public void driveGetBillListByState(){
		String billState = "tbd";
		ArrayList<BillPO> billPOs = getBillDataService.getBillListByState(billState);
		for(int i=0;i<billPOs.size();i++){
			System.out.println(billPOs.get(i).getId());
		}
	}
	
	public static void main(String [] args){
		GetBillDataService_Driver getBillDataService_Driver=new GetBillDataService_Driver();
		getBillDataService_Driver.driveGetBillByNameAndId();
	//	getBillDataService_Driver.driveGetBillListByStateAndName();
	//	getBillDataService_Driver.driveGetBillListByState();
	}
}
