/**
 * 仓库管理员进行实际库存的审查录入
 * @author BeibeiZhang
 * @version 2017.11.19
 *
 */
package blService.commodityBlService;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface ActualInventoryCheckBlService extends Remote{
	public int actualInventoryCheck(String goodsTag,int actualAmount) throws RemoteException;
	//记录实际库存
	//传入商品ID与实际库存量，然后后台与系统原先库存对比
	//之后进行库存出单，出单后通过出入库修改系统库存
}
