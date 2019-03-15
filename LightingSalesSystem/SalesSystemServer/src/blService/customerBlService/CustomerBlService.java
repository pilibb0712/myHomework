package blService.customerBlService;

import java.rmi.Remote;

import java.rmi.RemoteException;
import java.util.ArrayList;

import vo.CustomerVO;
import vo.UserInfoVO;

/**
 * 进货销售人员客户管理的业务逻辑接口
 * @author 王宁一
 * @version 2017.11.7
 *
 */

public interface CustomerBlService extends Remote{

	/**
	 * 增加新客户
	 * @param CustomerVO
	 * @return boolean, true表示操作成功
	 * @throws RemoteException
	 */
	public boolean AddCustomer(CustomerVO vo) throws RemoteException;
	
	/**
	 * 删除客户
	 * @param 客户ID
	 * @param 客户姓名
	 * 删除为敏感操作, 必须编号和姓名完全匹配才能删除
	 * @return boolean, true表示操作成功
	 * @throws RemoteException
	 */
	public boolean DelCustomer(String number, String name) throws RemoteException;
	
	/**
	 * 修改客户信息
	 * @param CustomerVO
	 * @return boolean, true表示操作成功
	 * @throws RemoteException
	 */
	public boolean ModCustomer(CustomerVO vo) throws RemoteException;
	
	/**
	 * 得到业务员
	 * @return ArrayList<UserInfoVO>
	 * @throws RemoteException
	 */
	public ArrayList<UserInfoVO> GetUsers() throws RemoteException;
	
	/**
	 * 查询客户信息
	 * @param 关键词, 可以是ID和姓名关键词
	 * @return ArrayList<CustomerVO>
	 * @throws RemoteException
	 */
	public ArrayList<CustomerVO> QueryCustomer(String name) throws RemoteException;
	
	/**
	 * 根据姓名得到客户
	 * @param name
	 * @return
	 * @throws RemoteException
	 */
	public UserInfoVO getUserByName(String name) throws RemoteException;	
}

