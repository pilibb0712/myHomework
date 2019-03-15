package blImpl.bill.billBl;

import assistant.type.BillStateEnum;
import assistant.utility.Date;
import dataImpl.billData.ProcessBillData;
import dataService.billDataService.ProcessBillDataService;
import po.BillPO;

/**
 * @author zhangao 161250193
 * 通过单据审批的具体操作
 * @version 2017.11.30
 */


public class ProcessBillBl {
	
	private static ProcessBillBl processBillBl;//单件对象
	
 //	private GetBillDataService getBillDataService = new GetBillData();//查单据的数据层接口对象
	private ProcessBillDataService processBillDataService = new ProcessBillData();//改变单据状态的数据层接口对象
	
	//构造方法
	private ProcessBillBl(){
	}
	//取得单件对象
	public static ProcessBillBl getInstance(){
		if(processBillBl == null){
			processBillBl = new ProcessBillBl();
		}
		return processBillBl;
	}
	

	/**
	 * 根据单据的名称(类型)，编号 通过某一张的单据审批。<br/>
	 * @param String billName 单据的名称(类型)
	 * @param String billId 单据的编号
	 * @return boolean 是否通过审批成功
	 */
	public boolean passBill(BillPO bill) {
		BillStateEnum from = BillStateEnum.TBD;
		BillStateEnum to = BillStateEnum.TODO;//提交后变为审批通过但是仍未执行单据
		
		Date approveDate =new Date();
		bill.setApproveDate(approveDate);//设置审批完成日期
		
		return processBillDataService.changeBillState(bill, from, to);
	}

	/**
	 * 根据单据的名称(类型)，编号 拒绝通过某一张的单据审批。<br/>
	 * @param String billName 单据的名称(类型)
	 * @param String billId 单据的编号
	 * @return boolean 是否拒绝通过审批成功
	 */
	public boolean denyBill(BillPO bill) {
		BillStateEnum from = BillStateEnum.TBD;
		BillStateEnum to = BillStateEnum.DENIED;//提交后变为审批未通过单据
		
		Date approveDate =new Date();
		bill.setApproveDate(approveDate);//设置审批完成日期
		bill.setFinishDate(approveDate);//设置完成日期未审批日期
		
		return processBillDataService.changeBillState(bill, from, to);
	}

	/**
	 * 根据单据的名称(类型)，编号 将某一张的单据标记为完成状态。<br/>
	 * @param String billName 单据的名称(类型)
	 * @param String billId 单据的编号
	 * @return boolean 是否将某一张的单据标记为完成状态成功
	 */
	public boolean doneBill(BillPO bill) {
		BillStateEnum from = BillStateEnum.TODO;
		BillStateEnum to = BillStateEnum.DONE;//提交后变为已完成单据
		bill.setFinishDate(new Date());//设置完成日期
		return processBillDataService.changeBillState(bill, from, to);
	}

	/**
	 * 根据单据的名称(类型)，编号 隐藏某一张的单据审批。<br/>
	 * @param bill要隐藏的单据
	 * @return boolean 是否隐藏成功
	 */
	public boolean hideBill(BillPO bill) {
		BillStateEnum from = bill.getBillStateEnum();
		BillStateEnum to = BillStateEnum.HIDDEN;//提交后变为隐藏单据
		return processBillDataService.changeBillState(bill, from, to);
	}
}


