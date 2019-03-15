package test.promotionBlTest;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;

import org.junit.Before;
import org.junit.Test;

import blImpl.promotionBl.TotalStrategy;
import blImpl.promotionBl.TotalStrategyList;

public class TotalStrategyListTest {

	private TotalStrategyList bl = new TotalStrategyList();
	
	@Before
	public void setUp() throws Exception {
	}

	@Test
	public void testCalcBestUserStrategy1() {
		ArrayList<TotalStrategy> list = new ArrayList<>();
		bl = new TotalStrategyList(list);
		assertEquals(null, bl.calcBestTotalStrategy(1000000));
	}
	
	@Test 
	public void testCalcBestUserStrategy2() {
		System.out.println(bl.calcBestTotalStrategy(100000).getName());
		assertEquals("×Ü¼Û1", bl.calcBestTotalStrategy(100000).getName());
	}

}
