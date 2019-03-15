/**
 * 仓库管理员进行增加新商品与商品入库
 * @author BeibeiZhang
 * @version 2017.11.19
 *
 */
package blService.commodityBlService;

import java.rmi.Remote;

import java.rmi.RemoteException;

import vo.*;

//do some change 2017.10.30
public interface AddGoodsBlService extends Remote{

public boolean addNewGoods(GoodsVO goodsVO,StockInOutVO stockVO) throws RemoteException;
//添加新商品，更新商品数据
public boolean add(StockInOutVO vo) throws RemoteException;
//商品入库，修改库存数据
}
