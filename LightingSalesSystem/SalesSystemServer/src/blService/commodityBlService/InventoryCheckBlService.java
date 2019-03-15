/**
 * 仓库管理员进行库存盘点
 * @author BeibeiZhang
 * @version 2017.11.19
 *
 */
package blService.commodityBlService;

import java.rmi.Remote;

import java.rmi.RemoteException;

import vo.*;


public interface InventoryCheckBlService extends Remote{
	public InventoryCheckSheetVO inventoryCheckSheet() throws RemoteException;
	//获得库存快照对象
	}
