package test.promotionBlTest;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;

import org.junit.Before;
import org.junit.Test;

import blImpl.promotionBl.ComboStrategy;
import blImpl.promotionBl.ComboStrategyList;
import vo.SalesGoodsVO;

public class ComboStrategyListTest {
	private ComboStrategyList bl = new ComboStrategyList();
	
	@Before
	public void setUp() throws Exception {
	}

	/**
	 * T140800
	 * */
	@Test
	public void testCalcBestUserStrategy1() {
		ArrayList<ComboStrategy> list = new ArrayList<>();
		bl = new ComboStrategyList(list);
		assertEquals(null, bl.calcBestComboStrategy(null, 1000));
	}
	
	
	/**
	 * T140801
	 * */
	@Test 
	public void testCalcBestUserStrategy2() {
		System.out.println();
		ArrayList<SalesGoodsVO> salesManCommodityVOs = new ArrayList<>();
		SalesGoodsVO salesGoodsVO1 = new SalesGoodsVO();
		salesGoodsVO1.setGoodsId("");
		salesGoodsVO1.setNumber(500);
		assertEquals("combo1", bl.calcBestComboStrategy(salesManCommodityVOs , 2000).getName());
	}
}
