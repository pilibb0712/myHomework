package test.promotionBlTest;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.Optional;

import org.junit.Test;

import assistant.utility.Date;
import blImpl.promotionBl.TotalStrategy;
import vo.GiftBillVO;
import vo.GiftVO;
import vo.TotalStrategyVO;

/**
 * ²âÊÔÂÊ100
 * ¸öÊý7
 * */
public class TotalStrategyTest {


	@Test
	public void testIsInDateArea1() {
		TotalStrategyVO totalStrategyVO = new TotalStrategyVO();
		totalStrategyVO.setStartDate(new Date(2017,10,10,0,0));
		totalStrategyVO.setEndDate(new Date(2018,5,5,5,5));
		TotalStrategy strategy = new TotalStrategy(totalStrategyVO);
		assertEquals(true, strategy.isInDateArea(new Date(2018,1,1,0,0)));
	}
	
	@Test
	public void testIsInDateArea2() {
		TotalStrategyVO totalStrategyVO = new TotalStrategyVO();
		totalStrategyVO.setStartDate(new Date(2017,10,10,0,0));
		totalStrategyVO.setEndDate(new Date(2018,5,5,5,5));
		TotalStrategy strategy = new TotalStrategy(totalStrategyVO);
		assertEquals(false, strategy.isInDateArea(new Date(2016,1,1,0,0)));
	}
	
	@Test
	public void testIsInDateArea3() {
		TotalStrategyVO totalStrategyVO = new TotalStrategyVO();
		totalStrategyVO.setStartDate(new Date(2017,10,10,0,0));
		totalStrategyVO.setEndDate(new Date(2018,5,5,5,5));
		TotalStrategy Strategy = new TotalStrategy(totalStrategyVO);
		assertEquals(false, Strategy.isInDateArea(new Date(2019,1,1,0,0)));
	}
	
	@Test
	public void testCalcCoupon1(){
		TotalStrategyVO totalStrategyVO = new TotalStrategyVO();
		totalStrategyVO.setCoupon(2000);
		TotalStrategy strategy = new TotalStrategy(totalStrategyVO);
		assertEquals(2000,(int) (strategy.calcCoupon()+0.5));
	}
	
	@Test
	public void testCalcDiscount1(){
		TotalStrategyVO totalStrategyVO = new TotalStrategyVO();
		totalStrategyVO.setDiscount(0.5);
		TotalStrategy strategy = new TotalStrategy(totalStrategyVO);
		assertEquals(500,(int) (strategy.calcDisCount(1000)+0.5));
	}
	
	@Test
	public void testCalcGiftValue1(){
		TotalStrategyVO totalStrategyVO = new TotalStrategyVO();
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
		
		totalStrategyVO.setGifts(gifts);;
		TotalStrategy strategy = new TotalStrategy(totalStrategyVO);
		assertEquals(30000,(int) (strategy.calcGiftValue()));
	}
	
	@Test 
	public void testGenerateGiftBill(){
		TotalStrategyVO TotalStrategyVO = new TotalStrategyVO();
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
		
		TotalStrategyVO.setGifts(gifts);;
		TotalStrategy strategy = new TotalStrategy(TotalStrategyVO);
		GiftBillVO giftBillVO = strategy.generateGiftBill();
		Optional<String> result = giftBillVO.getGifts().stream().map(g->g.getName()).reduce((xx,yy)->(xx+yy));
		assertEquals("123", result.get());
	}

}
