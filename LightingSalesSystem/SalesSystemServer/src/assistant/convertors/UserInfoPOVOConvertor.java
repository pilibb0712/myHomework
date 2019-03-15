package assistant.convertors;


import po.UserInfoPO;
import vo.UserInfoVO;

public class UserInfoPOVOConvertor {
	public static UserInfoVO poToVO(UserInfoPO po){
		if(po==null){return null;}
		UserInfoVO vo = new UserInfoVO();
		vo.setId(po.getId());
		vo.setName(po.getName());
		vo.setUserPositionEnum(po.getUserPositionEnum());
		return vo;
	}
	
	public static UserInfoPO voToPO(UserInfoVO vo){
		if(vo==null){return null;}
		UserInfoPO po = new UserInfoPO();
		po.setId(vo.getId());
		po.setName(vo.getName());
		po.setUserPositionEnum(vo.getUserPositionEnum());
		return po;
	}
}
