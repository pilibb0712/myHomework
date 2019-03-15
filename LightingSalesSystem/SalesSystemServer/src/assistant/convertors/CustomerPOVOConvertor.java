/**
 * 
 */
package assistant.convertors;

import po.CustomerInfoPO;
import po.CustomerPO;
import vo.CustomerInfoVO;
import vo.CustomerVO;

/**
 * gxy改了
 * 客户信息PO VO转换
 * @author 王宁一
 * @version 2017.12.13
 *
 */
public class CustomerPOVOConvertor {

	/**
	 * 客户信息 PO 转至 VO
	 * @param CustomerPO
	 * @return CustomerVO
	 */
	public static CustomerVO customerPOtoVO(CustomerPO po) {
		if(po==null){return null;}
		CustomerVO vo=new CustomerVO();
		
		vo.setId(po.getId());
		vo.setType(po.getType());
		vo.setLevel(po.getLevel());
		vo.setName(po.getName());
		vo.setTel(po.getTel());
		vo.setAddress(po.getAddress());
		vo.setPost(po.getPost());
		vo.setEmail(po.getEmail());
		vo.setShouldReceiveCredit(po.getShouldReceiveCredit());
		vo.setShouldReceive(po.getShouldReceive());
		vo.setShouldPay(po.getShouldPay());
		vo.setDefaultSalesman(UserInfoPOVOConvertor.poToVO(po.getDefaultSalesman()));
		
		return vo;
	}
	
	/**
	 * 客户信息 VO 转至 PO
	 * @param CustomerVO
	 * @return CustomerPO
	 */
	public static CustomerPO customerVOtoPO(CustomerVO vo) {
		if(vo==null){return null;}
		CustomerPO po=new CustomerPO();
		
		po.setId(vo.getId());
		po.setType(vo.getType());
		po.setLevel(vo.getLevel());
		po.setName(vo.getName());
		po.setTel(vo.getTel());
		po.setAddress(vo.getAddress());
		po.setPost(vo.getPost());
		po.setEmail(vo.getEmail());
		po.setShouldReceiveCredit(vo.getShouldReceiveCredit());
		po.setShouldReceive(vo.getShouldReceive());
		po.setShouldPay(vo.getShouldPay());
		po.setDefaultSalesman(UserInfoPOVOConvertor.voToPO(vo.getDefaultSalesman()));
		
		return po;
	}
	
	public static CustomerInfoPO customerInfoVOtoPO(CustomerInfoVO vo){
		if(vo==null){return null;}
		CustomerInfoPO po=new CustomerInfoPO();
		
		po.setId(vo.getId());
		po.setName(vo.getName());
		po.setType(vo.getType());
		po.setLevel(vo.getLevel());
		
		return po;
	}
	
	public static CustomerInfoVO customerInfoPOtoVO(CustomerInfoPO po){
		if(po==null){return null;}
		CustomerInfoVO vo=new CustomerInfoVO();
		
		vo.setId(po.getId());
		vo.setName(po.getName());
		vo.setType(po.getType());
		vo.setLevel(po.getLevel());
		
		return vo;
	}
}
