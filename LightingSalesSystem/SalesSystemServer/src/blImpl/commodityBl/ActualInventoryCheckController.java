/**
 * 仓库管理员进行实际库存的审查录入
 * @author BeibeiZhang
 * @version 2017.11.19  2017.11.28
 *
 */
package blImpl.commodityBl;

import java.rmi.RemoteException;

import blService.commodityBlService.ActualInventoryCheckBlService;
import dataImpl.commodityData.GoodsData;
import po.GoodsPO;

public class ActualInventoryCheckController implements ActualInventoryCheckBlService{

	/**
	 * public int actualInventoryCheck(String goodsTag, int actualAmount) throws RemoteException
	 * @param1 String:to find out the goods
	 * @param2 int:actualAmount of the goods in the warehouse
	 * @return int:the D-value between actualAmount and systemAmount
	 * <0:means less |dValue| pieces of the goods
	 * >0:means over dValue pieces of the goods；
	 * =0:means equal
	 */
	@Override
	public int actualInventoryCheck(String goodsID, int actualAmount) throws RemoteException {
		// TODO Auto-generated method stub
		GoodsData goodsData=new GoodsData();
		GoodsPO theGoods=goodsData.find(goodsID);
		int systemAmount=theGoods.getAmount();
		int dValue=actualAmount-systemAmount;
		return dValue;
	}
	//记录实际库存
	//传入商品ID与实际库存量，然后后台与系统原先库存对比,差值为实际值减去系统库存
	//之后进行库存出单，出单后通过出入库修改系统库存


}
