package blInteract.financeInteract;

import java.rmi.RemoteException;

/**
 * 提供给总经理的更新银行账户余额的接口
 * @author guxinyu
 * @version 2017.12.11
 *
 */
public interface BankAccountSumUpdateBlService {
	/**
	 * 更新银行账户余额
	 * @param accountName 需要更新的账户名称
	 * @param change 需要变更的数目，比如，需要减少1000元即为-1000.0，需要增加5000元即为5000.0
	 * @return 返回值表明是否更新成功
	 * @throws RemoteException
	 */
	public boolean updateRemainingSum(String accountName,double change);
}
