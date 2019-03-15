package test.adminBlTest;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

import assistant.type.UserPositionEnum;
import blImpl.adminBl.GetUserBl;
import vo.UserVO;


/**
 * @author zhangao
 * 测试用例个数:5
 * 覆盖率100%
 * */
public class GetUserBlTest {
	private GetUserBl bl = new GetUserBl();
	@Before
	public void setUp() throws Exception {
	}
	

	/**
	 * T140000
	 * */
	@Test
	public void test1() {
		for(UserVO userVO:bl.getManager()){
			assertEquals(UserPositionEnum.MANAGER, userVO.getPosition());
		}
	}

	/**
	 * T140100
	 * */
	@Test
	public void test2() {
		for(UserVO userVO:bl.getAdmin()){
			assertEquals(UserPositionEnum.ADMIN, userVO.getPosition());
		}
	}
	
	/**
	 * T140200
	 * */
	@Test
	public void test3() {
		for(UserVO userVO:bl.getFianceStaff()){
			assertEquals(UserPositionEnum.FINANCE, userVO.getPosition());
		}
	}
	
	/**
	 * T140300
	 * */
	@Test
	public void test4() {
		for(UserVO userVO:bl.getSalesman()){
			assertEquals(UserPositionEnum.SALESMAN, userVO.getPosition());
		}
	}
	
	
	/**
	 * T140400
	 * */
	@Test
	public void test5() {
		for(UserVO userVO:bl.getWarekeeper()){
			assertEquals(UserPositionEnum.WAREKEPPER, userVO.getPosition());
		}
	}
}
