package blImpl.customerBl;

import java.rmi.RemoteException;
import java.util.ArrayList;

import blInteract.customerInteract.GetCustomerService;
import blInteract.customerInteract.ModifyCustomerMoneyService;
import blService.customerBlService.CustomerBlService;
import vo.CustomerInfoVO;
import vo.CustomerVO;
import vo.UserInfoVO;

/**
 * 进货销售人员客户管理的控制器
 * @author 王宁一
 * @version 2017.11.7
 *
 */

public class CustomerBlController implements CustomerBlService, ModifyCustomerMoneyService ,GetCustomerService{


	private CustomerBl Impl=new CustomerBl();
	
	/**
	 * 修改客户的应收应付
	 * @param 客户编号（字符串）
	 * @param 修改的数额
	 * @return 返回一个布尔值表示是否操作成功，如果成功则返回True
	 */
	@Override
	public boolean ModifyCustomerMoney(String number, double amount) {
		// TODO Auto-generated method stub
		boolean success=Impl.ModifyCustomerMoney(number, amount);
		return success;
	}

	/**
	 * 增加一位客户
	 * @param CustomerVO
	 * @return 返回一个布尔值表示是否操作成功，如果成功则返回True
	 */
	@Override
	public boolean AddCustomer(CustomerVO vo) {
		// TODO Auto-generated method stub
		boolean success=Impl.AddCustomer(vo);
		return success;
	}

	/**
	 * 删除一位客户
	 * @param 一个客户的ID
	 * @param 一个客户的姓名
	 * 只有ID和姓名都匹配之后才能进行删除操作
	 * @return 返回一个布尔值表示是否操作成功，如果成功则返回True
	 */
	@Override
	public boolean DelCustomer(String number, String name) {
		// TODO Auto-generated method stub
		boolean success=Impl.DelCustomer(number, name);
		return success;
	}

	/**
	 * 修改一位客户的客户信息（应收应付除外）
	 * @param CustomerVO
	 * @return 返回一个布尔值表示是否操作成功，如果成功则返回True
	 */
	@Override
	public boolean ModCustomer(CustomerVO vo) {
		// TODO Auto-generated method stub
		boolean success=Impl.ModCustomer(vo);
		return success;
	}

	/**
	 * 查询客户信息，支持模糊查找
	 * @param 关键字段
	 * @return ArrayList<CustomerVO>，如果没有找到则返回null
	 */
	@Override
	public ArrayList<CustomerVO> QueryCustomer(String name) {
		// TODO Auto-generated method stub
		if(Impl.QueryCustomer(name)!=null) {
			ArrayList<CustomerVO> customerVOs=new ArrayList<CustomerVO>();
			customerVOs=Impl.QueryCustomer(name);
			
			return customerVOs;
		}
		return null;
	}
	
	/**
	 * 得到所有客户的部分信息（供财务人员）
	 * @return ArrayList<CustomerInfoVO>
	 */
	@Override
	public ArrayList<CustomerInfoVO> GetCustomers(){
		ArrayList<CustomerInfoVO> customers=new ArrayList<CustomerInfoVO>();
		customers=Impl.GetCustomers();
		return customers;
	}

	/**
	 * @see blService.customerBlService.CustomerBlService#GetUsers()
	 * @return ArrayList<UserInfoVO>
	 * @throws RemoteException
	 */
	@Override
	public ArrayList<UserInfoVO> GetUsers() throws RemoteException {
		ArrayList<UserInfoVO> users=new ArrayList<>();
		users=Impl.GetUsers();
		return users;
	}

	/**
	 * 根据姓名拿到操作员
	 * @see blService.customerBlService.CustomerBlService#getUserByName(java.lang.String)
	 * @param name
	 * @return
	 * @throws RemoteException
	 */
	@Override
	public UserInfoVO getUserByName(String name) throws RemoteException {
		UserInfoVO result=new UserInfoVO();
		result=Impl.getUserByName(name);
		return result;
	}
	
}
