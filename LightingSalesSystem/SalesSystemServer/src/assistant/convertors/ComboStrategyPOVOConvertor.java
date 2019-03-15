package assistant.convertors;

import java.util.ArrayList;

import po.ComboStrategyPO;
import po.GiftPO;
import vo.ComboStrategyVO;
import vo.GiftVO;

/**
 * @author zhangao 161250193
 * @version 2017.12.3
 * ComboStrategyPO和VO的转换器
 */
public class ComboStrategyPOVOConvertor {
	/**
	 * 将用户促销策略vo转换为po
	 * @param ComboStrategyVO vo 需要转换的vo
	 * @return ComboStrategyPO 转换之后的PO
	 */
	public static ComboStrategyPO voToPO(ComboStrategyVO vo){
		if(vo==null){return null;}
		ComboStrategyPO po = new ComboStrategyPO();
		
		ArrayList<GiftVO> giftVOs = vo.getComboCommodityList();
		ArrayList<GiftPO> giftPOs = GiftListPOVOConvertor.giftListVOToPO(giftVOs);
		po.setComboCommodityList(giftPOs);
		
		po.setDiscount(vo.getDiscount());
		po.setEndDate(vo.getEndDate());
		po.setId(vo.getId());
		po.setName(vo.getName());
		po.setRemark(vo.getRemark());
		po.setStartDate(vo.getStartDate());
		po.setStrategyCategoryEnum(vo.getStrategyCategoryEnum());
		return po;
	}
	
	
	/**
	 * 将用户促销策略po转换为vo
	 * @param ComboStrategyPO po 需要转换的po
	 * @return ComboStrategyVO 转换之后的VO
	 */
	public static ComboStrategyVO poToVO(ComboStrategyPO po){
		if(po==null){return null;}
		ComboStrategyVO vo = new ComboStrategyVO();
		
		ArrayList<GiftPO> giftPOs = po.getComboCommodityList();
		ArrayList<GiftVO> giftVOs = GiftListPOVOConvertor.giftListPOToVO(giftPOs);
		vo.setComboCommodityList(giftVOs);
		
		vo.setDiscount(po.getDiscount());
		vo.setEndDate(po.getEndDate());
		vo.setId(po.getId());
		vo.setName(po.getName());
		vo.setRemark(po.getRemark());
		vo.setStartDate(po.getStartDate());
		vo.setStrategyCategoryEnum(po.getStrategyCategoryEnum());
		return vo;
	}
}
