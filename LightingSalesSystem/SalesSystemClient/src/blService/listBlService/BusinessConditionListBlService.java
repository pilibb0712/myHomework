package blService.listBlService;

import java.rmi.Remote;
import java.rmi.RemoteException;

import vo.BusinessConditionListVO;

/**
 * 经营情况表逻辑接口
 * @author guxinyu
 * @version 2017.11.7
 *
 */
public interface BusinessConditionListBlService extends Remote{
	/**
	 * 读取经营情况表
	 * @return 返回经营情况表的VO
	 * @throws RemoteException
	 */
	public BusinessConditionListVO getBusinessConditionList() throws RemoteException;
}
