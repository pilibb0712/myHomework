package assistant.convertors;

import java.util.ArrayList;

import po.GiftBillPO;
import po.GiftPO;
import po.UserInfoPO;
import vo.GiftBillVO;
import vo.GiftVO;
import vo.UserInfoVO;

public class GiftBillPOVOConvertor {
	public static GiftBillPO giftBillVOtoPO(GiftBillVO vo){
		if(vo==null){return null;}
		GiftBillPO po = new GiftBillPO();
		po.setApproveDate(vo.getApproveDate());
		UserInfoVO approver= vo.getApprover();
		po.setApprover(UserInfoPOVOConvertor.voToPO(approver));
		po.setApproverComment(vo.getApproverComment());
		po.setBillStateEnum(vo.getBillStateEnum());
		po.setCategoryEnum(vo.getCategoryEnum());
		po.setCreateDate(vo.getCreateDate());
		
		UserInfoVO creater = vo.getCreater();
		po.setCreater(UserInfoPOVOConvertor.voToPO(creater));
		
		UserInfoVO executor = vo.getExecutor();
		po.setExecutor(UserInfoPOVOConvertor.voToPO(executor));
		
		po.setFinishDate(vo.getFinishDate());
		po.setId(vo.getId());
		
		ArrayList<GiftVO> giftsVo = vo.getGifts();
		ArrayList<GiftPO> giftsPo = GiftListPOVOConvertor.giftListVOToPO(giftsVo);
		po.setGifts(giftsPo);
		
		return po;
	}
	
	public static GiftBillVO giftBillPOtoVO(GiftBillPO po){
		if(po==null){return null;}
		GiftBillVO vo = new GiftBillVO();
		vo.setApproveDate(po.getApproveDate());
		UserInfoPO approver= po.getApprover();
		vo.setApprover(UserInfoPOVOConvertor.poToVO(approver));
		vo.setApproverComment(po.getApproverComment());
		vo.setBillStateEnum(po.getBillStateEnum());
		vo.setCategoryEnum(po.getCategoryEnum());
		vo.setCreateDate(po.getCreateDate());
		
		UserInfoPO creater = po.getCreater();
		vo.setCreater(UserInfoPOVOConvertor.poToVO(creater));
		
		UserInfoPO executor = po.getExecutor();
		vo.setExecutor(UserInfoPOVOConvertor.poToVO(executor));
		
		vo.setFinishDate(po.getFinishDate());
		vo.setId(po.getId());
		
		ArrayList<GiftPO> giftsPO = po.getGifts();
		ArrayList<GiftVO> giftsvo = GiftListPOVOConvertor.giftListPOToVO(giftsPO);
		vo.setGifts(giftsvo);
		
		return vo;
	}
}
