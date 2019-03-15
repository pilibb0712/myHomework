package test.promotionBlTest;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.Optional;

import org.junit.Before;
import org.junit.Test;

import assistant.utility.Date;
import blImpl.promotionBl.UserStrategy;
import vo.GiftBillVO;
import vo.GiftVO;
import vo.UserStrategyVO;

/**
 * ²âÊÔÂÊ100
 * ¸öÊý6
 * */
public class UserStrategyTest {

	@Before
	public void setUp() throws Exception {
		
	}

	/**
	 * T141300
	 * */
	@Test
	public void testIsInDateArea1() {
		UserStrategyVO userStrategyVO = new UserStrategyVO();
		userStrategyVO.setStartDate(new Date(2017,10,10,0,0));
		userStrategyVO.setEndDate(new Date(2018,5,5,5,5));
		UserStrategy userStrategy = new UserStrategy(userStrategyVO);
		assertEquals(true, userStrategy.isInDateArea(new Date(2018,1,1,0,0)));
	}
	
	/**
	 * T141301
	 * */
	@Test
	public void testIsInDateArea2() {
		UserStrategyVO userStrategyVO = new UserStrategyVO();
		userStrategyVO.setStartDate(new Date(2017,10,10,0,0));
		userStrategyVO.setEndDate(new Date(2018,5,5,5,5));
		UserStrategy userStrategy = new UserStrategy(userStrategyVO);
		assertEquals(false, userStrategy.isInDateArea(new Date(2016,1,1,0,0)));
	}
	
	/**
	 * T141302
	 * */
	@Test
	public void testIsInDateArea3() {
		UserStrategyVO userStrategyVO = new UserStrategyVO();
		userStrategyVO.setStartDate(new Date(2017,10,10,0,0));
		userStrategyVO.setEndDate(new Date(2018,5,5,5,5));
		UserStrategy userStrategy = new UserStrategy(userStrategyVO);
		assertEquals(false, userStrategy.isInDateArea(new Date(2019,1,1,0,0)));
	}
	
	/**
	 * T141100
	 * */
	@Test
	public void testCalcCoupon1(){
		UserStrategyVO userStrategyVO = new UserStrategyVO();
		userStrategyVO.setCoupon(2000);
		UserStrategy userStrategy = new UserStrategy(userStrategyVO);
		assertEquals(2000,(int) (userStrategy.calcCoupon()+0.5));
	}
	
	/**
	 * T141000
	 * */
	@Test
	public void testCalcDiscount1(){
		UserStrategyVO userStrategyVO = new UserStrategyVO();
		userStrategyVO.setDiscount(0.5);
		UserStrategy userStrategy = new UserStrategy(userStrategyVO);
		assertEquals(500,(int) (userStrategy.calcDisCount(1000)+0.5));
	}
	
	/**
	 * T141200
	 * */
	@Test
	public void testCalcGiftValue1(){
		UserStrategyVO userStrategyVO = new UserStrategyVO();
		ArrayList<GiftVO> gifts = new ArrayList<>();
		GiftVO gift1 = new GiftVO();
		gift1.setGiftAmount(100);
		gift1.setPrice(100);
		GiftVO gift2 = new GiftVO();
		gift2.setGiftAmount(100);
		gift2.setPrice(100);
		GiftVO gift3 = new GiftVO();
		gift3.setGiftAmount(100);
		gift3.setPrice(100);
		gifts.add(gift1);
		gifts.add(gift2);
		gifts.add(gift3);
		
		userStrategyVO.setGifts(gifts);;
		UserStrategy userStrategy = new UserStrategy(userStrategyVO);
		assertEquals(30000,(int) (userStrategy.calcGiftValue()));
	}
	
	/**
	 * T141400
	 * */
	@Test 
	public void testGenerateGiftBill(){
		UserStrategyVO userStrategyVO = new UserStrategyVO();
		ArrayList<GiftVO> gifts = new ArrayList<>();
		GiftVO gift1 = new GiftVO();
		gift1.setName("1");
		GiftVO gift2 = new GiftVO();
		gift2.setName("2");
		GiftVO gift3 = new GiftVO();
		gift3.setName("3");
		gifts.add(gift1);
		gifts.add(gift2);
		gifts.add(gift3);
		
		userStrategyVO.setGifts(gifts);;
		UserStrategy userStrategy = new UserStrategy(userStrategyVO);
		GiftBillVO giftBillVO = userStrategy.generateGiftBill();
		Optional<String> result = giftBillVO.getGifts().stream().map(g->g.getName()).reduce((xx,yy)->(xx+yy));
		assertEquals("123", result.get());
	}
}
