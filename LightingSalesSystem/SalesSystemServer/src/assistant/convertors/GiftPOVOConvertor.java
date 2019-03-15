package assistant.convertors;

import po.GiftPO;
import vo.GiftVO;

public class GiftPOVOConvertor {
	
	public static GiftVO giftPOtoVO(GiftPO po){
		if(po==null){return null;}
		GiftVO vo = new GiftVO();
		vo.setGiftAmount(po.getGiftAmount());
		vo.setId(po.getId());
		vo.setName(po.getName());
		vo.setPrice(po.getPrice());
		return vo;
	}
	
	public static GiftPO giftVOtoVPO(GiftVO vo){
		if(vo==null){return null;}
		GiftPO po = new GiftPO();
		po.setGiftAmount(vo.getGiftAmount());
		po.setId(vo.getId());
		po.setName(vo.getName());
		po.setPrice(vo.getPrice());
		return po;
	}
}
