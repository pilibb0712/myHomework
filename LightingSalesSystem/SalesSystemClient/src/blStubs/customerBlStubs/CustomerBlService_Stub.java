package blStubs.customerBlStubs;
import java.rmi.RemoteException;
import java.util.ArrayList;

import assistant.type.UserPositionEnum;
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

public class CustomerBlService_Stub implements CustomerBlService, ModifyCustomerMoneyService ,GetCustomerService{

	
	/**
	 * 修改客户的应收应付
	 * @param 客户编号（字符串）
	 * @param 修改的数额
	 * @return 返回一个布尔值表示是否操作成功，如果成功则返回True
	 */
	@Override
	public boolean ModifyCustomerMoney(String number, double amount) {
		// TODO Auto-generated method stub
		//boolean success=Impl.ModifyCustomerMoney(number, amount);
		if(number.equals("i00001")&&amount==10000) {
			return true;
		}
		return false;	
	}

	/**
	 * 增加一位客户
	 * @param CustomerVO
	 * @return 返回一个布尔值表示是否操作成功，如果成功则返回True
	 */
	@Override
	public boolean AddCustomer(CustomerVO vo) {
		// TODO Auto-generated method stub
		//boolean success=Impl.AddCustomer(vo);
		if(vo.getId().equals("i00001")) {
			return true;
		}
		return false;		
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
//		boolean success=Impl.DelCustomer(number, name);
//		return success;
		if(number.equals("i00001")&&name.equals("No.1")) {
			return true;
		}
		return false;	
	}

	/**
	 * 修改一位客户的客户信息（应收应付除外）
	 * @param CustomerVO
	 * @return 返回一个布尔值表示是否操作成功，如果成功则返回True
	 */
	@Override
	public boolean ModCustomer(CustomerVO vo) {
		// TODO Auto-generated method stub
		if(vo.getId().equals("i00001")) {
			return true;
		}
		return false;	
	}

	/**
	 * 查询客户信息，支持模糊查找
	 * @param 关键字段
	 * @return ArrayList<CustomerVO>，如果没有找到则返回null
	 */
	@Override
	public ArrayList<CustomerVO> QueryCustomer(String name) {
		// TODO Auto-generated method stub
		ArrayList<CustomerVO> array=new ArrayList<CustomerVO>();
		CustomerVO vo=new CustomerVO();
		array.add(vo);
		return array;
	}
	
	/**
	 * 得到所有客户的部分信息（供财务人员）
	 * @return ArrayList<CustomerInfoVO>
	 */
	@Override
	public ArrayList<CustomerInfoVO> GetCustomers(){
		ArrayList<CustomerInfoVO> customers=new ArrayList<CustomerInfoVO>();
		CustomerInfoVO infoVO=new CustomerInfoVO();
		customers.add(infoVO);
		return customers;
	}

	/**
	 * @see blService.customerBlService.CustomerBlService#GetUsers()
	 * @return
	 * @throws RemoteException
	 */
	@Override
	public ArrayList<UserInfoVO> GetUsers() throws RemoteException {
		// TODO Auto-generated method stub
		ArrayList<UserInfoVO> users=new ArrayList<>();
		UserInfoVO vo1=new UserInfoVO();
		vo1.setId("00001");
		vo1.setName("People1");
		vo1.setUserPositionEnum(UserPositionEnum.MANAGER);
		UserInfoVO vo2=new UserInfoVO();
		vo1.setId("00002");
		vo2.setName("People2");
		vo2.setUserPositionEnum(UserPositionEnum.SALESMAN);
		users.add(vo1);
		users.add(vo2);
		return users;
	}
	
}
