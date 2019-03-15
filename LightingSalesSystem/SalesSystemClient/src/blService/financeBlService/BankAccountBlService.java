package blService.financeBlService;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.ArrayList;
import assistant.exception.AccountNameExistException;
import vo.BankAccountVO;


/**
 * 银行账户管理逻辑接口
 * @author guxinyu
 * @version 2017.12.30
 *
 */
public interface BankAccountBlService extends Remote{
	/**
	 * 新建银行账户时，此接口将该银行账户保存
	 * @param toSave需要被保存的银行账户的VO
	 * @return 返回的布尔值表明银行账户是否保存成功
	 * @throws RemoteException
	 */
	public boolean newBankAccount(BankAccountVO toSave) throws RemoteException, AccountNameExistException;
	
	/**
	 * 获得银行账户列表
	 * @return 返回所有银行账户的VO，用ArrayList存储
	 * @throws RemoteException
	 */
	public ArrayList<BankAccountVO> viewBankAccounts() throws RemoteException;
	
	/**
	 * 更新银行账户名
	 * @param oldAccountName为更新前的账户名
	 * @param newAccountName为更新后的账户名
	 * @return 返回的布尔值表明是否更新成功
	 * @throws RemoteException
	 */
	public boolean updateAccount(String oldAccountName, String newAccountName) throws RemoteException, AccountNameExistException;
	
	/**
	 * 删除银行账户
	 * @param toDelete为需要被删除的银行账户的名称
	 * @return 返回的布尔值表明是否删除成功
	 * @throws RemoteException
	 */
	public boolean deleteAccount(String toDelete) throws RemoteException;
	
}
