package blStubs.billStubs.billStateBlStubs;

import java.rmi.RemoteException;
import java.util.ArrayList;

import assistant.type.BillStateEnum;
import assistant.utility.Date;
import blService.billService.billStateBlService.WareKeeperBillStateBlService;
import vo.GiftBillVO;
import vo.InventoryLossBillVO;
import vo.InventoryOverBillVO;
import vo.InventoryWarningBillVO;
import vo.PurchaseBillVO;
import vo.PurchaseReturnBillVO;
import vo.SalesBillVO;
import vo.SalesReturnBillVO;
import vo.UserInfoVO;

/**
 * 库存管理人员单据状态逻辑接口的桩
 * @author guxinyu
 * @version 2017.12.22
 *
 */
public class WareKeeperBillStateBlService_Stub implements WareKeeperBillStateBlService{

	@Override
	public ArrayList<InventoryWarningBillVO> getInventoryWarningList(BillStateEnum state) throws RemoteException {
		ArrayList<InventoryWarningBillVO> bills=new ArrayList<InventoryWarningBillVO>();
		
		for(int i=0;i<50;i++){
			bills.add(newAInventoryWarningBill());
		}
		
		return bills;
	}
	
	private InventoryWarningBillVO newAInventoryWarningBill(){
		InventoryWarningBillVO bill1=new InventoryWarningBillVO();
		bill1.setApproveDate(new Date());
		bill1.setBillStateEnum(BillStateEnum.DENIED);
		bill1.setId("KCBJD-20170203-15615");
		bill1.setCreateDate(new Date());
		UserInfoVO creator=new UserInfoVO();
		creator.setName("张三");
		creator.setId("00003");
		bill1.setCreater(creator);
		
		return bill1;
	}

	@Override
	public ArrayList<InventoryLossBillVO> getInventoryLossList(BillStateEnum state) throws RemoteException {
		ArrayList<InventoryLossBillVO> bills=new ArrayList<InventoryLossBillVO>();
		
		for(int i=0;i<50;i++){
			bills.add(newAInventoryLossBill());
		}
		
		return bills;
	}
	
	private InventoryLossBillVO newAInventoryLossBill(){
		InventoryLossBillVO bill1=new InventoryLossBillVO();
		bill1.setApproveDate(new Date());
		bill1.setBillStateEnum(BillStateEnum.DENIED);
		bill1.setId("KCBSD-20170203-15615");
		bill1.setCreateDate(new Date());
		UserInfoVO creator=new UserInfoVO();
		creator.setName("张三");
		creator.setId("00003");
		bill1.setCreater(creator);
		
		return bill1;
	}

	@Override
	public ArrayList<InventoryOverBillVO> getInventoryOverList(BillStateEnum state) throws RemoteException {
		ArrayList<InventoryOverBillVO> bills=new ArrayList<InventoryOverBillVO>();
		
		for(int i=0;i<50;i++){
			bills.add(newAInventoryOverBill());
		}
		
		return bills;
	}
	
	private InventoryOverBillVO newAInventoryOverBill(){
		InventoryOverBillVO bill1=new InventoryOverBillVO();
		bill1.setApproveDate(new Date());
		bill1.setBillStateEnum(BillStateEnum.DENIED);
		bill1.setId("KCBYD-20170203-15615");
		bill1.setCreateDate(new Date());
		UserInfoVO creator=new UserInfoVO();
		creator.setName("张三");
		creator.setId("00003");
		bill1.setCreater(creator);
		
		return bill1;
	}


	@Override
	public boolean deleteUnpassInventoryWarningBill(InventoryWarningBillVO bill) throws RemoteException {
		
		return true;
	}

	@Override
	public boolean deleteUnpassInventoryLossBill(InventoryLossBillVO bill) throws RemoteException {
		
		return true;
	}

	@Override
	public boolean deleteUnpassInventoryOverBill(InventoryOverBillVO bill) throws RemoteException {
		
		return true;
	}

	@Override
	public boolean doneInventoryLossBill(InventoryLossBillVO bill) throws RemoteException {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean doneInventoryOverBill(InventoryOverBillVO bill) throws RemoteException {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public ArrayList<PurchaseBillVO> getPurchaseBillList(BillStateEnum state) throws RemoteException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean donePurchaseBill(PurchaseBillVO bill) throws RemoteException {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public ArrayList<PurchaseReturnBillVO> getPurchaseReturnBillList(BillStateEnum state) throws RemoteException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean donePurchaseReturnBill(PurchaseReturnBillVO bill) throws RemoteException {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public ArrayList<SalesBillVO> getSalesBillList(BillStateEnum state) throws RemoteException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean doneSalesBill(SalesBillVO bill) throws RemoteException {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public ArrayList<SalesReturnBillVO> getSalesReturnBillList(BillStateEnum state) throws RemoteException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean doneSalesReturnBill(SalesReturnBillVO bill) throws RemoteException {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public ArrayList<GiftBillVO> getGiftBillList(BillStateEnum state) throws RemoteException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean doneGiftBill(GiftBillVO bill) throws RemoteException {
		// TODO Auto-generated method stub
		return false;
	}
	
}
