package blImpl.bill.viewBillBl;

import java.rmi.RemoteException;

import assistant.convertors.CommodityPOVOConvertor;
import blInteract.billBlInteract.BillBlInteractServiceFactory;
import blInteract.billBlInteract.InventoryLossBillBlService;
import blInteract.billBlInteract.InventoryOverBillBlService;
import blInteract.billBlInteract.InventoryWarningBillBlService;
import blService.billService.viewBillBlService.ViewWareKeeperBillBlService;
import po.InventoryLossBillPO;
import po.InventoryOverBillPO;
import po.InventoryWarningBillPO;
import vo.InventoryLossBillVO;
import vo.InventoryOverBillVO;
import vo.InventoryWarningBillVO;;

public class ViewWareKeeperBillBlController implements ViewWareKeeperBillBlService{
    private BillBlInteractServiceFactory factory=new BillBlInteractServiceFactory();
	@Override
	public InventoryWarningBillVO getInventoryWarningBill(String billId) throws RemoteException {
		// TODO Auto-generated method stub
		InventoryWarningBillBlService service=factory.getInventoryWarningBillBlService();
		InventoryWarningBillPO billPO=service.getBillById(billId);
		InventoryWarningBillVO billVO=CommodityPOVOConvertor.inventoryWarningBillPOtoVO(billPO);
		return billVO;
	}

	@Override
	public InventoryLossBillVO getInventoryLossBill(String billId) throws RemoteException {
		// TODO Auto-generated method stub
		InventoryLossBillBlService service=factory.getInventoryLossBillBlService();
		InventoryLossBillPO billPO=service.getBillById(billId);
		InventoryLossBillVO billVO=CommodityPOVOConvertor.inventoryLossBillPOtoVO(billPO);
		return billVO;
	}

	@Override
	public InventoryOverBillVO getInventoryOverBill(String billId) throws RemoteException {
		// TODO Auto-generated method stub
		InventoryOverBillBlService service=factory.getInventoryOverBillBlService();
		InventoryOverBillPO billPO=service.getBillById(billId);
		InventoryOverBillVO billVO=CommodityPOVOConvertor.inventoryOverBillPOtoVO(billPO);
		return billVO;
	}

}
