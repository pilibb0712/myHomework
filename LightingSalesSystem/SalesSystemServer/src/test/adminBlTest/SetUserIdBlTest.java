package test.adminBlTest;

import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;

import blImpl.adminBl.SetUserIdBl;


/**
 * @author zhangao
 * ²âÊÔÂÊ100%
 * ²âÊÔÓÃÀı£º4
 * */
public class SetUserIdBlTest {
	private String target = "00024";
	private SetUserIdBl bl = SetUserIdBl.getInstance();
	@Before
	public void setUp() throws Exception {
	}

	
	/**
	 * T140700
	 * */
	@Test
	public void test1() {
		assertEquals(target, bl.generateId());
	}
	
	/**
	 * T140701
	 * */
	@Test
	public void test2() {
		assertEquals(target, bl.generateId());
	}
	
	/**
	 * T140702
	 * */
	@Test
	public void test3() {
		assertEquals(target, bl.generateId());
	}
	
	/**
	 * T140703
	 * */
	@Test
	public void test4() {
		assertEquals(target, bl.generateId());
	}
}
