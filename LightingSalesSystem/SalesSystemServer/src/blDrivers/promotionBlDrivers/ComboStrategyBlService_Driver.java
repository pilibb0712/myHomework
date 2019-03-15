package blDrivers.promotionBlDrivers;

import static org.junit.Assert.assertEquals;

import java.rmi.RemoteException;
import java.util.ArrayList;

import org.junit.Before;
import org.junit.Test;

import assistant.exception.Myexception;
import assistant.type.StrategyCategoryEnum;
import assistant.utility.Date;
import blImpl.promotionBl.ComboStrategyBlController;
import blService.promotionBlService.ComboStrategyBlService;
import vo.ComboStrategyVO;

public class ComboStrategyBlService_Driver {

	private ComboStrategyBlService service = new ComboStrategyBlController(); 
	
	@Before
	public void setUp() throws Exception {
	}
	
	/**
	 * T241100
	 * */
	@Test
	public  void driveAddStrategy() throws RemoteException, Myexception{
		ComboStrategyVO vo = new ComboStrategyVO();
		vo.setId("2017-1204-02-58-48");
		vo.setDiscount(0.8);
		vo.setStartDate(new Date(2017,12,4,2,52));
		vo.setEndDate(new Date(2017,12,27,2,52));
		vo.setName("用户促销策略Vip4");
		vo.setRemark("针对高端会员1");
		vo.setStrategyCategoryEnum(StrategyCategoryEnum.COMBOSTRATEGY);
		assertEquals(true, service.addStrategy(vo));
	}
	
	/**
	 * T240800
	 * 缺陷1:新增时没有默认设置促销策略种类
	 * */
	@Test
	public void driveGetStrategyList() throws RemoteException, Myexception{
		ArrayList<ComboStrategyVO> list = service.getStrategyList();
		for(ComboStrategyVO vo:list){
			assertEquals(StrategyCategoryEnum.COMBOSTRATEGY, vo.getStrategyCategoryEnum());
		}
	}
	
	/**
	 * 更新
	 * T241000
	 * */
	@Test
	public void driveUpdate() throws RemoteException, Myexception{
		ComboStrategyVO vo = new ComboStrategyVO();
		vo.setId("2017-1204-02-58-48");
		vo.setDiscount(0.8);
		vo.setStartDate(new Date(2017,12,4,2,52));
		vo.setEndDate(new Date(2017,12,27,2,52));
		vo.setName("update用户促销策略Vip4");
		vo.setRemark("针对高端会员1");
		vo.setStrategyCategoryEnum(StrategyCategoryEnum.USRESTRATEGY);
		service.updateStrategy(vo);
		ComboStrategyVO updatedVo= service.getStrategyList().stream()
				.filter(v->v.getId().equals("2017-1204-02-58-48"))
				.findFirst().get(); //lambda表达式拿到该促销策略
		assertEquals("update用户促销策略Vip4", updatedVo.getName());
	}
	
	
	/**
	 * T240900
	 * T240901
	 * */
	@Test
	public void driveDelete() throws RemoteException, Myexception{
		assertEquals(true, service.deleteStrategy("2017-1204-02-58-48"));
		assertEquals(false, service.deleteStrategy("666"));
	}
}
