/**
 * 商品类别数据操作区
 * @author BeibeiZhang
 * @version 2017.11.19
 *
 */
package dataService.commodityDataService;

import java.util.ArrayList;

import po.ClassificationPO;


public interface GoodsClassificationDataService {
	public ArrayList<ClassificationPO> finds();
	//获得所有商品类别对象
	public ClassificationPO find(String classificationTag);
	//获得某商品类别对象
	public boolean insert(ClassificationPO po);
	//增加新的商品类别对象
	public boolean delete(ClassificationPO po);
	//删除旧的商品类别对象
	public boolean update(ClassificationPO po);
    //更新商品类别信息，更新商品类别对象
}
