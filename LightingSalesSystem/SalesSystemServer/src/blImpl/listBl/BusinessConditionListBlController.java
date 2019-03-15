package blImpl.listBl;

import assistant.convertors.ListPOVOConvertor;
import blService.listBlService.BusinessConditionListBlService;
import po.BusinessConditionListPO;
import vo.BusinessConditionListVO;

/**
 * 经营情况表Controller
 * @author guxinyu
 * @version 2017.12.23
 *
 */
public class BusinessConditionListBlController implements BusinessConditionListBlService{
	private BusinessConditionListBl listBl=BusinessConditionListBl.getInstance();
	
	@Override
	public BusinessConditionListVO getBusinessConditionList(){
		BusinessConditionListPO po=listBl.getList();
		
		BusinessConditionListVO vo=ListPOVOConvertor.businessConditionListPOtoVO(po);
		
		return vo;
	}
	
}
