package test.promotionBlTest;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

import assistant.utility.Date;
import blImpl.promotionBl.ComboStrategy;
import vo.ComboStrategyVO;
/**
 * @author zhangao
 * ²âÊÔÂÊ100
 * ¸öÊý4
 * */
public class ComboStrategyTest {

	@Before
	public void setUp() throws Exception {
		
	}

	/**
	 * T140900
	 * */
	@Test
	public void testIsInDateArea1() {
		ComboStrategyVO strategyVO = new ComboStrategyVO();
		strategyVO.setStartDate(new Date(2017,10,10,0,0));
		strategyVO.setEndDate(new Date(2018,5,5,5,5));
		ComboStrategy strategy = new ComboStrategy(strategyVO);
		assertEquals(true, strategy.isInDateArea(new Date(2018,1,1,0,0)));
	}
	
	/**
	 * T140901
	 * */
	@Test
	public void testIsInDateArea2() {
		ComboStrategyVO strategyVO = new ComboStrategyVO();
		strategyVO.setStartDate(new Date(2017,10,10,0,0));
		strategyVO.setEndDate(new Date(2018,5,5,5,5));
		ComboStrategy strategy = new ComboStrategy(strategyVO);
		assertEquals(false, strategy.isInDateArea(new Date(2016,1,1,0,0)));
	}
	
	/**
	 * T140902
	 * */
	@Test
	public void testIsInDateArea3() {
		ComboStrategyVO strategyVO = new ComboStrategyVO();
		strategyVO.setStartDate(new Date(2017,10,10,0,0));
		strategyVO.setEndDate(new Date(2018,5,5,5,5));
		ComboStrategy strategy = new ComboStrategy(strategyVO);
		assertEquals(false, strategy.isInDateArea(new Date(2019,1,1,0,0)));
	}
	

	@Test
	public void testCalcDiscount1(){
		ComboStrategyVO strategyVO = new ComboStrategyVO();
		strategyVO.setDiscount(0.5);
		ComboStrategy strategy = new ComboStrategy(strategyVO);
		assertEquals(500,(int) (strategy.calcDisCount(1000)+0.5));
	}
	
}
