package dataImpl.billData;

import java.io.File;

import assistant.type.BillStateEnum;
import assistant.utility.ReadAndWriteBillPOSerUtility;
import dataService.billDataService.ProcessBillDataService;
import po.BillPO;

/**
 * @author zhangao 161250193
 * @version 2017.11.9
 * 单据状态的改变
 * 用于单据审批通过，审批不通过之类的操作
 */

public class ProcessBillData implements ProcessBillDataService{
	private static final String ROOT_PATH = "data"+File.separator+"bill"+File.separator;//根目录地址
	private static final String FILE_EXTENSION = ".ser";
	
	
	/**
	 * 改变单据的状态。<br/>
	 * 包括单据状态和储存的位置
	 * @param BillPO bill 要改变的单据信息
	 * @param BillStateEnum from 改变前的单据状态
	 * @param BillStateEnum to 目标单据状态(改变后的单据状态)
	 * @return boolean 是写文件成功
	 */
	@Override
	public boolean changeBillState(BillPO bill, BillStateEnum from, BillStateEnum to) {
		String billCategory = bill.getCategoryEnum().toString() + File.separator;//单据地址中类型部分
		String fromBillState = from.toString() + File.separator;//单据地址中现有的单据状态部分
		String toBillState = to.toString() + File.separator;//单据地址中目标单据状态部分
		String billId = bill.getId() + FILE_EXTENSION;//单据地址中单据Id部分
		String fromPath = ROOT_PATH + billCategory +fromBillState + billId;
		String toPath = ROOT_PATH + billCategory +toBillState + billId;
		bill.setBillStateEnum(to);
		File file = new File(fromPath);
		if(!file.exists()){
			System.out.println(fromPath);
			System.out.println("file!exist");
			return false;	
		}//原有的单据不存在

		bill.setBillStateEnum(to);//单据状态改变为目标状态
		file.delete();//删除旧文件
		if(from!=BillStateEnum.DENIED){writeObj(bill, toPath);}//写新的文件,如果是审批未通过单据则删除
		return true;
	}
	
	
	/**
	 * 写bill的ser文件。<br/>
	 * @param GiftBillPO bill 需要新建的单据 
	 * @return boolean 是写文件成功
	 */
	private boolean writeObj(BillPO bill,String path){
		return ReadAndWriteBillPOSerUtility.writeObj(bill, path);
	}
}
