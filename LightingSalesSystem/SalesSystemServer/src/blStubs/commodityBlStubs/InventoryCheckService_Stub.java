package blStubs.commodityBlStubs;

import java.rmi.RemoteException;
import java.util.ArrayList;

import blService.commodityBlService.InventoryCheckBlService;
import vo.InventoryCheckSheetVO;
//do some change 2017.11.06
public class InventoryCheckService_Stub implements InventoryCheckBlService{
	public InventoryCheckSheetVO inventoryCheckSheet() throws RemoteException{
		InventoryCheckSheetVO inventoryCheckSheet=new InventoryCheckSheetVO();
		ArrayList<String[]> sheet=new ArrayList<String[]>();
		String[] infor={"AAa","00001A","100","100","2017-11-06"};
		sheet.add(infor);
		inventoryCheckSheet.setInventoryCheckSheet(sheet);
		return inventoryCheckSheet;
	}
	
}
