package blImpl.bill.billBl;
import dataImpl.billData.DeleteDraftBillData;
import dataService.billDataService.DeleteDraftBillDataService;

/**
 * 采用单件模式统一对草稿单据进行删除
 * @author zhangao 161250193
 * @version 2017.11.22
 * 草稿单据的删除
 * 用于废弃的草稿状态单据的删除
 */

public class DeleteDraftBillBl {
	private static DeleteDraftBillBl deleteDraftBillBl;//单件对象 
	
	private DeleteDraftBillDataService deleteDraftBillDataService 
		= new DeleteDraftBillData();//删除草稿单据的数据层对象
	
	//构造方法
	private DeleteDraftBillBl(){
		
	}
	
	/**
	 * 取得单件对象引用的接口
	 */
	public static DeleteDraftBillBl getInstance(){
		if(deleteDraftBillBl==null){
			return new DeleteDraftBillBl();
		}
		return deleteDraftBillBl;
	}
	
	/**
	 * 根据单据的名称(类型)，编号 删除某一张的单据草稿。<br/>
	 * @param String billName 单据的名称(类型)
	 * @param String billId 单据的编号
	 * @return boolean 是否增加成功
	 */
	 public boolean deleteDraftBill(String billName,String billId) {
			return deleteDraftBillDataService.deleteDraftBill(billName, billId);
	}
}
