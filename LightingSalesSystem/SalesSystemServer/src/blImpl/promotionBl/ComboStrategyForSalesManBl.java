package blImpl.promotionBl;

import java.util.ArrayList;

import blInteract.promotionBlInteract.ComboStrategyForSalesManService;
import vo.ComboStrategyVO;
import vo.SalesGoodsVO;
import vo.SalesManCommodityVO;

public class ComboStrategyForSalesManBl implements ComboStrategyForSalesManService{

	/**
	 * 得到当前最好的促销策略
	 * 总的降价额度:赠品总价+降价额度+赠送代金券数量,最大
	 * @param salesGoods 卖出的物品列表
	 * @param consumption 卖出的商品未打折前的总价
	 * */
	@Override
	public ComboStrategyVO getBestStrategy(ArrayList<SalesGoodsVO> salesManCommodityVOs, double consumption) {
		ComboStrategyList comboStrategyList = new ComboStrategyList();
		return comboStrategyList.calcBestComboStrategy(salesManCommodityVOs, consumption);
	}

}
