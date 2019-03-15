/**
 * 仓库管理员进行商品信息的管理
 * @author BeibeiZhang
 * @version 2017.11.19
 *
 */
package blService.commodityBlService;

import java.rmi.Remote;

import java.rmi.RemoteException;
import java.util.ArrayList;

import vo.*;

public interface GoodsInforBlService extends Remote{
	public boolean ifGoodsExist(String goodsName,String goodsType) throws RemoteException;
	//通过名称加型号检查某个商品是否存在
	public boolean ifGoodsExist(String goodsID) throws RemoteException;
	//通过ID检查某个商品是否存在
	public ArrayList<GoodsVO> fuzzyEnquiry(String keyWord) throws RemoteException;
	//查找商品时可以进行模糊查询，获得相关商品列表
	public GoodsVO goodsINfor(String goodsTag) throws RemoteException;
	//获取一个商品对象，以获得该商品的信息
	public boolean modifyGoodsInfor(GoodsVO vo) throws RemoteException;
	//修改某个商品的信息，不能修改库存

}
