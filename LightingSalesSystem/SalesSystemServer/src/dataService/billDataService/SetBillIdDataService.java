package dataService.billDataService;

import po.BillPO;

public interface SetBillIdDataService {
	/**
	 * 更新当前的bill生成record。<br/>
	 * @param BillPO当前需要更新的Bill记录
	 * @return boolean 是否更新成功
	 */
	public boolean updateRecord(BillPO bill);
	
	/**
	 * 生成bill的Id。<br/>
	 * @param GiftBillPO bill 需要设定Id的单据 
	 * @return void
	 */
	public void setBillId(BillPO bill);
}
