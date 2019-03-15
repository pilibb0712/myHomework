package blService.financeBlService;

import java.rmi.Remote;
import java.rmi.RemoteException;
import vo.InOutDetailsVO;
import vo.InitialInfoVO;

/**
 * 账单管理逻辑接口
 * @author guxinyu
 * @version 2017.12.23
 *
 */
public interface InOutBlService extends Remote{
	/**
	 * 新建账单时保存期初信息
	 * @param initialInfo为新建账单时填写的期初信息的VO
	 * @return 返回的布尔值表明这个账单是否新建成功
	 * @throws RemoteException
	 */
	public boolean newInOut(InitialInfoVO initialInfo) throws RemoteException;
	
	/**
	 * 查看期初信息
	 * @param inOutYear为需要查看期初信息的年份，即账单名称
	 * @return 返回该年期初信息的VO
	 * @throws RemoteException
	 */
	public InitialInfoVO viewInitialInformation(String inOutYear) throws RemoteException;
	
	/**
	 * 查看账单明细
	 * @param inOutYear为要查看明细的账单的年份，即账单名称
	 * @return 返回值为该年账单明细的VO
	 * @throws RemoteException
	 */
	public InOutDetailsVO viewInOutDetails(String inOutYear) throws RemoteException;
	
	/**
	 * 读取账单列表
	 * @return 返回所有账单的名称，以String数组存储
	 * @throws RemoteException
	 */
	public String[] viewInOutList() throws RemoteException;
}
