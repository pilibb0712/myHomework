package blImpl.promotionBl;

import assistant.convertors.GiftBillPOVOConvertor;
import blInteract.billBlInteract.BillBlInteractServiceFactory;
import blInteract.billBlInteract.GiftBillBlService;
import blInteract.promotionBlInteract.TotalStrategyForSalesManService;
import po.GiftBillPO;
import vo.GiftBillVO;
import vo.TotalStrategyVO;

public class TotalStrategyForSalesManBl implements TotalStrategyForSalesManService{
	/**
	 * 得到当前最好的促销策略
	 * 总的降价额度:赠品总价+降价额度+赠送代金券数量,最大
	 * @param customerLevel 用户的等级
	 * @param consumption用户的总消费额
	 * @return 符合条件的最好的总价促销策略
	 * */
	@Override
	public TotalStrategyVO getBestStrategy(double consumption) {
		TotalStrategyList totalStrategyList = new TotalStrategyList();
		return totalStrategyList.calcBestTotalStrategy(consumption);
	}

	
	/**
	 * 根据某一个促销策略生成相应的库存赠送单
	 * @param TotalStrategy vo 需要生成库存赠送单的总价促销策略
	 * @return 生成的库存赠送单
	 * */
	@Override
	public GiftBillVO generateGiftBillByTotalStrategy(TotalStrategyVO vo) {
		TotalStrategy totalStrategy = new TotalStrategy(vo);
		GiftBillVO giftBillVO = totalStrategy.generateGiftBill();
		
		//将该促销策略提交审批
		GiftBillBlService service = new BillBlInteractServiceFactory().getGiftBillBlService();
		GiftBillPO po = GiftBillPOVOConvertor.giftBillVOtoPO(giftBillVO);
		service.createBill(po);
		
		return giftBillVO;
	}

}
