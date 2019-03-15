package assistant.convertors;

import po.UserPO;
import vo.UserVO;

public class UserPOVOConvertor {
	public static UserPO voToPO(UserVO vo){
		if(vo==null){return null;}
		UserPO po= new UserPO();
		po.setAge(vo.getAge());
		po.setDescription(vo.getDescription());
		po.setId(vo.getId());
		po.setName(vo.getName());
		po.setPosition(vo.getPosition());
		return po;
	}
	public static UserVO poToVO(UserPO po){
		if(po==null){return null;}
		UserVO vo= new UserVO();
		vo.setAge(po.getAge());
		vo.setDescription(po.getDescription());
		vo.setId(po.getId());
		vo.setName(po.getName());
		vo.setPosition(po.getPosition());
		return vo;
	}
}
