package test.adminBlTest;

import static org.junit.Assert.*;

import java.rmi.RemoteException;

import org.junit.Before;
import org.junit.Test;

import assistant.exception.Myexception;
import blImpl.adminBl.GetUserInfoByUserNameBl;

/**
 * 测试率:100%
 * */
public class GetUserInfoByNameBlTest {
	private  GetUserInfoByUserNameBl bl = new GetUserInfoByUserNameBl();
	
	@Before
	public void setUp() throws Exception {
	}

	
	/**
	 * T140600
	 * */
	@Test
	public void test1() throws RemoteException, Myexception {
		assertEquals("张傲", bl.getUserInfoByUserName("张傲").getName());
	}

	/**
	 * T140601
	 * */
	@Test
	public void test2() throws RemoteException, Myexception {
		assertEquals("张贝贝", bl.getUserInfoByUserName("张贝贝").getName());
	}
	
	/**
	 * T140602
	 * */
	@Test
	public void test3() throws RemoteException, Myexception {
		assertEquals("王宁一", bl.getUserInfoByUserName("王宁一").getName());
	}
	
	/**
	 * T140603
	 * */
	@Test
	public void test4() throws RemoteException, Myexception {
		assertEquals(null, bl.getUserInfoByUserName("rhweuifhiweu"));
	}
	
	/**
	 * T140604
	 * */
	@Test
	public void test5() throws RemoteException, Myexception {
		assertEquals(null, bl.getUserInfoByUserName(""));
	}
}
