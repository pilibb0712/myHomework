package blImpl.promotionBl;

import java.util.ArrayList;

import assistant.convertors.GiftBillPOVOConvertor;
import assistant.type.BillCategoryEnum;
import assistant.type.CustomerLevelEnum;
import assistant.utility.Date;
import blInteract.billBlInteract.BillBlInteractServiceFactory;
import blInteract.billBlInteract.GiftBillBlService;
import blInteract.promotionBlInteract.UserStrategyForSalesManService;
import po.GiftBillPO;
import vo.GiftBillVO;
import vo.GiftVO;
import vo.UserStrategyVO;

public class UserStrategyForSalesManBl implements UserStrategyForSalesManService{

	/**
	 * 得到当前最好的促销策略
	 * 总的降价额度:赠品总价+降价额度+赠送代金券数量,最大
	 * */
	@Override
	public UserStrategyVO getBestStrategy(CustomerLevelEnum customerLevel,double consumption) {
		UserStrategyList userStrategyList = new UserStrategyList();
		return userStrategyList.calcBestUserStrategy(customerLevel, consumption);
	}

	/**
	 * 根据某一个促销策略生成相应的库存赠送单
	 * 并将该单据提交审批
	 * @param UserStrategy vo 需要生成库存赠送单的总价促销策略
	 * @return 生成的库存赠送单
	 * */
	@Override
	public GiftBillVO generateGiftBillByTotalStrategy(UserStrategyVO vo) {
		UserStrategy userStrategy = new UserStrategy(vo);
		GiftBillVO giftBillVO = userStrategy.generateGiftBill();
		
		//将该促销策略提交审批
		GiftBillBlService service = new BillBlInteractServiceFactory().getGiftBillBlService();
		GiftBillPO po = GiftBillPOVOConvertor.giftBillVOtoPO(giftBillVO);
		service.createBill(po);
		
		return giftBillVO;
	}

}
