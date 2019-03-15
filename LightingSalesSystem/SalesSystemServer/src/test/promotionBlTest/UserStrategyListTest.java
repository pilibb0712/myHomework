package test.promotionBlTest;

import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.Before;
import org.junit.Test;

import assistant.type.CustomerLevelEnum;
import blImpl.promotionBl.UserStrategy;
import blImpl.promotionBl.UserStrategyList;

public class UserStrategyListTest {

	private UserStrategyList bl = new UserStrategyList();
	
	@Before
	public void setUp() throws Exception {
	}

	/**
	 * T141500
	 * */
	@Test
	public void testCalcBestUserStrategy1() {
		ArrayList<UserStrategy> list = new ArrayList<>();
		bl = new UserStrategyList(list);
		assertEquals(null, bl.calcBestUserStrategy(CustomerLevelEnum.VIP2, 1000));
	}
	
	/**
	 * T141501
	 * */
	@Test 
	public void testCalcBestUserStrategy2() {
		assertEquals("user1", bl.calcBestUserStrategy(CustomerLevelEnum.VIP2, 1000).getName());
	}

}
