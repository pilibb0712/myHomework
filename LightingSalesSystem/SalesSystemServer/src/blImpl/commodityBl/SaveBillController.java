package blImpl.commodityBl;

import blInteract.billBlInteract.BillBlInteractServiceFactory;
import blInteract.billBlInteract.InventoryLossBillBlService;
import blInteract.billBlInteract.InventoryOverBillBlService;
import blInteract.billBlInteract.InventoryWarningBillBlService;
import blService.commodityBlService.SaveBillBlService;
import po.InventoryLossBillPO;
import po.InventoryOverBillPO;
import po.InventoryWarningBillPO;

import java.rmi.RemoteException;

import assistant.convertors.CommodityPOVOConvertor;
import vo.InventoryLossBillVO;
import vo.InventoryOverBillVO;
import vo.InventoryWarningBillVO;

public class SaveBillController implements SaveBillBlService{
    private BillBlInteractServiceFactory billFactory=new BillBlInteractServiceFactory();
	@Override
	public boolean saveInventoryWarningBill(InventoryWarningBillVO bill) throws RemoteException {
		// TODO Auto-generated method stub
		InventoryWarningBillPO warningBill=CommodityPOVOConvertor.inventoryWarningBillVOtoPO(bill);
        InventoryWarningBillBlService warningBillSer=billFactory.getInventoryWarningBillBlService();
        warningBillSer.createBill(warningBill);
		return true;
	}

	@Override
	public boolean saveInventoryLossBill(InventoryLossBillVO bill) throws RemoteException {
		// TODO Auto-generated method stub
		InventoryLossBillPO lossBill=CommodityPOVOConvertor.inventoryLossBillVOtoPO(bill);
        InventoryLossBillBlService lossBillSer=billFactory.getInventoryLossBillBlService();
        lossBillSer.createBill(lossBill);
		return true;
	}

	@Override
	public boolean saveInventoryOverBill(InventoryOverBillVO bill) throws RemoteException {
		// TODO Auto-generated method stub
		InventoryOverBillPO overBill=CommodityPOVOConvertor.inventoryOverBillVOtoPO(bill);
        InventoryOverBillBlService overBillSer=billFactory.getInventoryOverBillBlService();
        overBillSer.createBill(overBill);
		return true;
	}

}
