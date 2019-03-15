package dataService.billDataService;

import java.util.ArrayList;
import assistant.type.UserPositionEnum;
import po.BillPO;

public interface GetBillDataService{
	 public BillPO getBillByNameAndId(String billName,String billId);
	 
	 //应用于approve和list
	 public ArrayList<BillPO> getBillListByState(String billState);
	 public ArrayList<BillPO> getBillListByStateAndName(String billState,String billName);
}
