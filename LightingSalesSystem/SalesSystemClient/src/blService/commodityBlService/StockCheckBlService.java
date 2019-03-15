/**
 * 仓库管理员进行库存审查，获得某时间段内的出入库情况
 * @author BeibeiZhang
 * @version 2017.11.19
 *
 */
package blService.commodityBlService;

import java.rmi.Remote;

import java.rmi.RemoteException;

import assistant.utility.Date;
import vo.StockCheckSheetVO;

public interface StockCheckBlService extends Remote{
	public StockCheckSheetVO stockCheckSheet(Date beginTime,Date endTime) throws RemoteException;
    //获得库存审查对象
}
