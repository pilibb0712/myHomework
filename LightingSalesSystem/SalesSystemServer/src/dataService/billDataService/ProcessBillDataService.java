package dataService.billDataService;

import assistant.type.BillStateEnum;
import po.BillPO;

public interface ProcessBillDataService {
	 public boolean changeBillState(BillPO bill,BillStateEnum from,BillStateEnum to);
}
