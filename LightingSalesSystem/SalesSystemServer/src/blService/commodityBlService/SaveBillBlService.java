/**
 * 仓库管理员进行库存出单后保存单据，实际上在逻辑层是一种单据保存，保存在待审核文件中，等待总经理审核
 * @author BeibeiZhang
 * @version 2017.11.19
 *
 */package blService.commodityBlService;

import java.rmi.Remote;

import java.rmi.RemoteException;

import vo.*;

public interface SaveBillBlService extends Remote{
	public boolean saveInventoryWarningBill(InventoryWarningBillVO  bill) throws RemoteException;
	//保存库存警戒单
	public boolean saveInventoryLossBill(InventoryLossBillVO  bill) throws RemoteException;
	//保存库存报损单
	public boolean saveInventoryOverBill(InventoryOverBillVO  bill) throws RemoteException;
    //保存库存报溢单
}
