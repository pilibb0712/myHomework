package assistant.convertors;

import java.util.ArrayList;

import po.GiftPO;
import po.TotalStrategyPO;
import vo.GiftVO;
import vo.TotalStrategyVO;

/**
 * @author zhangao 161250193
 * @version 2017.12.3
 * TotalStrategyPO和VO的转换器
 */
public class TotalStrategyPOVOConvertor {
	/**
	 * 将用户促销策略vo转换为po
	 * @param TotalStrategyVO vo 需要转换的vo
	 * @return TotalStrategyPO 转换之后的PO
	 */
	public static TotalStrategyPO voToPO(TotalStrategyVO vo){
		if(vo==null){return null;}
		TotalStrategyPO po = new TotalStrategyPO();
		po.setCoupon(vo.getCoupon());
		po.setTotalConsumption(vo.getTotalConsumption());
		po.setDiscount(vo.getDiscount());
		po.setEndDate(vo.getEndDate());
		
		ArrayList<GiftVO> giftVOs = vo.getGifts();
		ArrayList<GiftPO> giftPOs = GiftListPOVOConvertor.giftListVOToPO(giftVOs);
		po.setGifts(giftPOs);
		
		po.setId(vo.getId());
		po.setName(vo.getName());
		po.setRemark(vo.getRemark());
		po.setStartDate(vo.getStartDate());
		po.setStrategyCategoryEnum(vo.getStrategyCategoryEnum());
		return po;
	}
	
	/**
	 * 将用户促销策略po转换为vo
	 * @param TotalStrategyPO po 需要转换的po
	 * @return TotalStrategyVO 转换之后的VO
	 */
	public static TotalStrategyVO poToVO(TotalStrategyPO po){
		if(po==null){return null;}
		TotalStrategyVO vo = new TotalStrategyVO();
		vo.setCoupon(po.getCoupon());
		vo.setTotalConsumption(po.getTotalConsumption());
		vo.setDiscount(po.getDiscount());
		vo.setEndDate(po.getEndDate());
		
		ArrayList<GiftPO> giftPOs = po.getGifts();
		ArrayList<GiftVO> giftVOs = GiftListPOVOConvertor.giftListPOToVO(giftPOs);
		vo.setGifts(giftVOs);
		
		vo.setId(po.getId());
		vo.setName(po.getName());
		vo.setRemark(po.getRemark());
		vo.setStartDate(po.getStartDate());
		vo.setStrategyCategoryEnum(po.getStrategyCategoryEnum());
		return vo;
	}
}
