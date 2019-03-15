package blStubs.listBlStubs;

import java.util.ArrayList;

import blService.listBlService.BusinessConditionListBlService;
import vo.BusinessConditionListVO;
import vo.IncomeCostVO;

/**
 * 经营情况表逻辑接口的桩
 * @author guxinyu
 * @version 2017.12.23
 *
 */
public class BusinessConditionListBlService_Stub implements BusinessConditionListBlService{
	@Override
	public BusinessConditionListVO getBusinessConditionList(){
		ArrayList<IncomeCostVO> ic=new ArrayList<IncomeCostVO>();
		
		for(int i=0;i<50;i++){
			ic.add(get());
		}
		
		BusinessConditionListVO list=new BusinessConditionListVO(ic,0,0,0,0);
		return list;
	}
	
	private IncomeCostVO get(){
		IncomeCostVO vo=new IncomeCostVO();
		vo.setSalesEarning("5000");
		vo.setCostAjdustEarning("2000");
		vo.setCouponGapEarning("1000");
		vo.setGiftCost("1000");
		vo.setInventoryLossCost("2000");
		vo.setInventoryOverEarning("1500");
		vo.setPurchaseReturnGapEarning("3000");
		vo.setSalesCost("1233");
		return vo;
	}
	
}
