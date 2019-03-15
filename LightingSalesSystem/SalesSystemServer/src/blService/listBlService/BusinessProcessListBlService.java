package blService.listBlService;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.ArrayList;
import vo.BusinessProcessListVO;
import vo.CustomerInfoVO;
import vo.UserVO;

/**
 * 查看经营历程表逻辑接口
 * @author guxinyu
 * @version 2017.12.23
 *
 */
public interface BusinessProcessListBlService extends Remote{
	/**
	 * 读取经营历程表
	 * @return 返回经营历程表的VO
	 * @throws RemoteException
	 */
	public BusinessProcessListVO getBusinessProcessList() throws RemoteException;
	
	/**
	 * 获取仓库管理员列表
	 * @return 仓库管理员VO集合
	 * @throws RemoteException
	 */
	public ArrayList<UserVO> getWareKeeperList() throws RemoteException;
	
	/**
	 * 获取销售进货员列表
	 * @return 销售进货员VO集合
	 * @throws RemoteException
	 */
	public ArrayList<UserVO> getSalesmanList() throws RemoteException;
	
	/**
	 * 获取财务人员列表
	 * @return 财务人员VO集合
	 * @throws RemoteException
	 */
	public ArrayList<UserVO> getFinancialStaffList() throws RemoteException;
	
	/**
	 * 获取总经理列表
	 * @return 总经理VO集合
	 * @throws RemoteException
	 */
	public ArrayList<UserVO> getManagerList() throws RemoteException;
	
	/**
	 * 获取客户列表
	 * @return 客户信息VO集合
	 * @throws RemoteException
	 */
	public ArrayList<CustomerInfoVO> getCustomerList() throws RemoteException;

}
