package blImpl.commodityBl;

import java.rmi.RemoteException;

import blService.commodityBlService.InventoryCheckBlService;
import vo.InventoryCheckSheetVO;

public class InventoryCheckController implements InventoryCheckBlService{

	@Override
	public InventoryCheckSheetVO inventoryCheckSheet() throws RemoteException {
		// TODO Auto-generated method stub
		CheckSheet checkSheet=new CheckSheet();
		InventoryCheckSheetVO invenCheckSh=checkSheet.getInventoryCheckSheet();
		return invenCheckSh;
	}



}
