
package blImpl.bill.billBl;

import java.util.ArrayList;

import dataImpl.billData.GetBillData;
import dataService.billDataService.GetBillDataService;
import po.BillPO;

/**
 * @author zhangao 161250193
 * @version 2017.11.19
 * 得到单据的具体实现
 */


public class GetBillBl {

	GetBillDataService getBillDataService = new GetBillData();//查找单据的数据层对象
	
	/**
	 * 根据单据名称和Id读取一张单据。<br/>
	 * @param billName 要读取的单据名称
	 * @param billId 要读取的单据Id
	 * @return BillVo读取的单据
	 */
	public BillPO getBillByNameAndId(String billName, String billId) {
		BillPO billPO = getBillDataService.getBillByNameAndId(billName, billId);
		return billPO;
	}


	/**
	 * 根据单据状态读取一系列单据。<br/>
	 * @param billState 要读取的单据状态
	 * @return ArrayList<BillPO>读取的单据列表
	 */
	public ArrayList<BillPO> getBillListByState(String billState) {
		ArrayList<BillPO> billPOs = getBillDataService.getBillListByState(billState);
		return billPOs;
	}


	/**
	 * 根据单据名称和状态读取一系列单据。<br/>
	 * 专门用于billState部分
	 * @param billState 要读取的单据状态
	 * @param billName 要读取的单据名称
	 * @return ArrayList<BillPO>读取的单据列表
	 */
	public ArrayList<BillPO> getBillListByStateAndName(String billState, String billName) {
		ArrayList<BillPO> billPOs = getBillDataService.getBillListByStateAndName(billState, billName);
		return billPOs;
	}

}
