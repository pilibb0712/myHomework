/**
 * 仓库管理员进行删除商品、商品出库、库存报警出单
 * @author BeibeiZhang
 * @version 2017.11.19
 *
 */
package blService.commodityBlService;

import java.rmi.Remote;

import java.rmi.RemoteException;

import vo.*;


public interface DeleteGoodsBlService extends Remote{
	public boolean delete(StockInOutVO vo) throws RemoteException;
	//商品出库，修改系统库存
	public boolean obsoleteGoods(GoodsVO vo) throws RemoteException;
	//删除旧商品，更新商品数据
	public int compareTowarning(String goodsTag,int currentAmount) throws RemoteException;
    //完成出库后与警戒值比较，为负则低于库存警戒值，要出库存警戒单
	//但库存警戒单是为了让总经理去进货的，它不会直接影响出入库
}
