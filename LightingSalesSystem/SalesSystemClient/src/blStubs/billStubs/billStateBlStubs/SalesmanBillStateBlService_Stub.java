package blStubs.billStubs.billStateBlStubs;

import java.rmi.RemoteException;
import java.util.ArrayList;

import assistant.type.BillStateEnum;
import blService.billService.billStateBlService.SalesmanBillStateBlService;
import vo.PurchaseBillVO;
import vo.PurchaseReturnBillVO;
import vo.SalesBillVO;
import vo.SalesReturnBillVO;

/**
 * 销售进货人员单据状态逻辑接口桩
 * @author guxinyu
 * @version 2017.12.22
 *
 */
public class SalesmanBillStateBlService_Stub implements SalesmanBillStateBlService{

	@Override
	public ArrayList<PurchaseBillVO> getPurchaseBillList(BillStateEnum state) throws RemoteException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean deletePurchaseBill(PurchaseBillVO bill) throws RemoteException {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public ArrayList<PurchaseReturnBillVO> getPurchaseReturnBillList(BillStateEnum state) throws RemoteException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean deletePurchaseReturnBill(PurchaseReturnBillVO bill) throws RemoteException {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public ArrayList<SalesBillVO> getSalesBillList(BillStateEnum state) throws RemoteException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean deleteSalesBill(SalesBillVO bill) throws RemoteException {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public ArrayList<SalesReturnBillVO> getSalesReturnBillList(BillStateEnum state) throws RemoteException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean deleteSalesReturnBill(SalesReturnBillVO bill) throws RemoteException {
		// TODO Auto-generated method stub
		return false;
	}
		
}
