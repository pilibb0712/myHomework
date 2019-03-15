/**
 * 
 */
package test.customerBlTest;

import static org.junit.Assert.*;

import java.rmi.RemoteException;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import blImpl.customerBl.CustomerBlController;
import vo.CustomerVO;

/**
 * T1600
 * @author cosx
 *
 */
public class CustomerBlTest {

	private CustomerBlController CustomerBl=new CustomerBlController();
	private CustomerVO vo;
	
	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {
		vo=new CustomerVO();
		vo.setId("i88888");
		vo.setName("Name");
		vo.setShouldPay(0);
		CustomerBl.AddCustomer(vo);
	}

	/**
	 * @throws java.lang.Exception
	 */
	@After
	public void tearDown() throws Exception {
		CustomerBl.DelCustomer("o99999", "Name1");
		CustomerBl.DelCustomer("i88888", "Name");
	}

	/**
	 * T160001
	 * Test method for {@link blImpl.customerBl.CustomerBlController#ModifyCustomerMoney(java.lang.String, double)}.
	 */
	@Test
	public void testModifyCustomerMoney() {
		CustomerBl.ModifyCustomerMoney("i88888", 1000);
		double money=CustomerBl.QueryCustomer("i88888").get(0).getShouldPay();
		assertEquals(1000, money, 0.0);
	}

	/**
	 * T160002
	 * Test method for {@link blImpl.customerBl.CustomerBlController#AddCustomer(vo.CustomerVO)}.
	 */
	@Test
	public void testAddCustomer() {
		String name=CustomerBl.QueryCustomer("Name").get(0).getName();
		assertEquals("Name", name);
	}

	/**
	 * T160003
	 * Test method for {@link blImpl.customerBl.CustomerBlController#DelCustomer(java.lang.String, java.lang.String)}.
	 */
	@Test
	public void testDelCustomer() {
		CustomerBl.DelCustomer("i88888", "Name");
		assertNull(CustomerBl.QueryCustomer("i88888"));
	}

	/**
	 * T160004
	 * Test method for {@link blImpl.customerBl.CustomerBlController#ModCustomer(vo.CustomerVO)}.
	 */
	@Test
	public void testModCustomer() {
		CustomerVO vo=CustomerBl.QueryCustomer("o99999").get(0);
		vo.setName("Name1");
		CustomerBl.ModCustomer(vo);
		String name=CustomerBl.QueryCustomer("o99999").get(0).getName();
		assertEquals("Name1", name);
	}

	/**
	 * T160005
	 * Test method for {@link blImpl.customerBl.CustomerBlController#QueryCustomer(java.lang.String)}.
	 */
	@Test
	public void testQueryCustomer() {
		CustomerVO vo=new CustomerVO();
		vo.setId("o99999");
		vo.setName("NameTest");
		CustomerBl.AddCustomer(vo);
		String name=CustomerBl.QueryCustomer("o99999").get(0).getName();
		assertEquals("NameTest", name);
	}

	/**
	 * T160006
	 * Test method for {@link blImpl.customerBl.CustomerBlController#GetCustomers()}.
	 */
	@Test
	public void testGetCustomers() {
		assertNotNull(CustomerBl.GetCustomers());
	}

	/**
	 * T160007
	 * Test method for {@link blImpl.customerBl.CustomerBlController#GetUsers()}.
	 */
	@Test
	public void testGetUsers() {
		try {
			assertNotNull(CustomerBl.GetUsers());
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * T160008
	 * Test method for {@link blImpl.customerBl.CustomerBlController#getUserByName(java.lang.String)}.
	 */
	@Test
	public void testGetUserByName() {
		try {
			assertNotNull(CustomerBl.getUserByName("a_salseman"));
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
