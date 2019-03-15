package dataService.financeDataService;

import po.*;

/**
 * 财务数据操作数据库接口
 * @author guxinyu
 * @version 2017.12.4
 *
 */
public interface InOutDataService{
	
	/**
	 * 读取账单列表，每年的账单以该年的年份命名，如：2016的账单名称为"2016"
	 * @return 账单名称的String数组
	 */
	public String[] readInOutList();
	
	/**
	 * 当有收款单和付款单通过审核后，调用此接口在该年账单中插入该笔交易信息
	 * @param toInsert为需要被插入的单笔交易的PO
	 * @return 返回的布尔值表明是否插入成功
	 */
	public boolean insert (SingleEntryPO toInsert);
	
	/**
	 * 当新建账单时，保存期初信息，并生成该套账的文件
	 * @param toInsert为要保存的期初信息的PO
	 * @return 返回的布尔值表明是否保存成功
	 */
	public boolean insert (InitialInfoPO toInsert);
	
	/**
	 * 读取某一套账单的期初信息
	 * @param inOutYea 账单名称，即年份
	 * @return 期初信息PO
	 */
	public InitialInfoPO readInitialInfo(String inOutYear);
	
	/**
	 * 读取某一套账单的明细
	 * @param inOutYear 账单名称，即年份
	 * @return 账单明细PO
	 */
	public InOutDetailsPO readDetails(String inOutYear);

}
