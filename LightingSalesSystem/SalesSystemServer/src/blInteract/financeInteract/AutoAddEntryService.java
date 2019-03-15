package blInteract.financeInteract;

import java.rmi.RemoteException;

import vo.SingleEntryVO;

public interface AutoAddEntryService {
	/**
	 * 当有收款单和付款单通过审核时，自动增加账单内容
	 * @param toAdd为要增加的内容的VO
	 * @return 返回值表明是否增加成功
	 * @throws RemoteException
	 */
	public boolean autoAddEntry(SingleEntryVO toAdd) throws RemoteException;
}
