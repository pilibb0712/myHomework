package blInteract.promotionBlInteract;

import java.util.ArrayList;

import vo.ComboStrategyVO;
import vo.SalesGoodsVO;
import vo.SalesManCommodityVO;


/**
 * 促销策略给销售人员提供服务
 * 包括得到符合条件最佳策略即总的降价额度最大
 * 以及得到总的降价额度:赠品总价+降价额度+赠送代金券数量
 * @author zhangao
 * @version 2018.1.3
 * */
public interface ComboStrategyForSalesManService {
	/**
	 * 得到当前最好的促销策略
	 * 总的降价额度:赠品总价+降价额度+赠送代金券数量,最大
	 * @param salesGoods 卖出的物品列表
	 * @param consumption 卖出的商品未打折前的总价
	 * */
	public ComboStrategyVO getBestStrategy(ArrayList<SalesGoodsVO> salesManCommodityVOs,double consumption);
}
