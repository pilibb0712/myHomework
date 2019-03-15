package blInteract.promotionBlInteract;

import vo.GiftBillVO;
import vo.TotalStrategyVO;

/**
 * 促销策略给销售人员提供服务
 * 包括得到符合条件最佳策略即总的降价额度最大
 * 以及得到总的降价额度:赠品总价+降价额度+赠送代金券数量
 * @author zhangao
 * @version 2018.1.3
 * */
public interface TotalStrategyForSalesManService {
	/**
	 * 得到当前最好的促销策略
	 * 总的降价额度:赠品总价+降价额度+赠送代金券数量,最大
	 * @param customerLevel 用户的等级
	 * @param consumption用户的总消费额
	 * @return 符合条件的最好的总价促销策略
	 * */
	public TotalStrategyVO getBestStrategy(double consumption);
	
	/**
	 * 根据某一个促销策略生成相应的库存赠送单
	 * @param TotalStrategy vo 需要生成库存赠送单的总价促销策略
	 * @return 生成的库存赠送单
	 * */
	public GiftBillVO generateGiftBillByTotalStrategy(TotalStrategyVO vo);
}
