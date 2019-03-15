package blInteract.promotionBlInteract;

import assistant.type.CustomerLevelEnum;
import vo.GiftBillVO;
import vo.TotalStrategyVO;
import vo.UserStrategyVO;

public interface UserStrategyForSalesManService {
	/**
	 * 得到当前最好的促销策略
	 * 总的降价额度:赠品总价+降价额度+赠送代金券数量,最大
	 * @param customerLevel 用户的等级
	 * @param consumption用户的总消费额
	 * @return 符合条件的最好的用户促销策略
	 * */
	public UserStrategyVO getBestStrategy(CustomerLevelEnum customerLevel,double consumption);
	
	/**
	 * 根据某一个促销策略生成相应的库存赠送单
	 * 并将该单据提交审批
	 * @param UserStrategy vo 需要生成库存赠送单的总价促销策略
	 * @return 生成的库存赠送单
	 * */
	public GiftBillVO generateGiftBillByTotalStrategy(UserStrategyVO vo);
}
