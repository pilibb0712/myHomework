package blService.listBlService;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.ArrayList;
import vo.ClassificationVO;
import vo.CustomerInfoVO;
import vo.GoodsVO;
import vo.SalesDetailsListVO;
import vo.UserVO;

/**
 * 查看销售明细表逻辑接口
 * @author guxinyu
 * @version 2017.12.16
 *
 */
public interface SalesDetailsListBlService extends Remote{
	/**
	 * 读取销售明细表
	 * @return 返回销售明细表的VO
	 * @throws RemoteException
	 */
	public SalesDetailsListVO getSalesDetailsList() throws RemoteException;
	
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
	 * @throws Exception
	 */
	public ArrayList<UserVO> getManagerList() throws Exception;
	
	/**
	 * 获取客户列表
	 * @return 客户信息VO集合
	 * @throws RemoteException
	 */
	public ArrayList<CustomerInfoVO> getCustomerList() throws RemoteException;
	
	/**
	 * 获取商品分类列表
	 * @return 商品分类列表
	 * @throws RemoteException
	 */
	public ArrayList<ClassificationVO> getCommodityClassification() throws RemoteException;
	
	/**
	 * 获取商品列表
	 * @return 商品列表
	 * @throws RemoteException
	 */
	public ArrayList<GoodsVO> getCommodities() throws RemoteException;
}
