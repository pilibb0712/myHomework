/**
 * 商品数据操作区
 * @author BeibeiZhang
 * @version 2017.11.19
 *
 */
package dataService.commodityDataService;

import java.util.ArrayList;

import po.GoodsPO;

public interface GoodsDataService {
	public ArrayList<GoodsPO> finds();
	//获得所有商品对象
	public ArrayList<GoodsPO> fuzzyFinds(String keyWord);
	//根据关键词进行模糊查找
	public GoodsPO find(String goodsName,String goodsType);
	//根据名称加型号获得某一商品对象
	public GoodsPO find(String goodsTag);
	//根据ID获得某一商品对象
	public boolean insert(GoodsPO po);
	//增加一商品对象
	public boolean delete(GoodsPO po);
	//删除一商品对象
	public boolean update(GoodsPO po);
    //更新商品对象，更新该商品信息
}
