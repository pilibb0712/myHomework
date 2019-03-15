/**
 * 出入库情况数据操作区
 * @author BeibeiZhang
 * @version 2017.11.19
 *
 */
package dataService.commodityDataService;

import java.util.ArrayList;

import assistant.utility.Date;
import po.StockInOutPO;

public interface StockInOutRecordService {
	public ArrayList<StockInOutPO> finds(String goodsID);
	//获得所有该商品的出入库情况信息对象
	public ArrayList<StockInOutPO> finds(Date beginTime,Date endTime);
	//获得某一时间段的所有出入库情况信息对象
	public boolean insert(StockInOutPO po);
	//增加一条新的出入库情况信息对象
	//？？？要不要给删除的方法，防止硬盘溢出？？？
}
