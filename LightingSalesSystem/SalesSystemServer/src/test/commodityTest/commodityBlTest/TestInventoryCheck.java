package test.commodityTest.commodityBlTest;

import java.rmi.RemoteException;
import java.util.ArrayList;

import blImpl.commodityBl.InventoryCheckController;
import vo.InventoryCheckSheetVO;

public class TestInventoryCheck {
public void testInventoryCheckSheet(){
	try{
		InventoryCheckController service=new InventoryCheckController();
		InventoryCheckSheetVO sheetVO=service.inventoryCheckSheet();
		ArrayList<String[]> sheet=sheetVO.getInventoryCheckSheet();
		for(int i=0;i<=sheet.size()-1;i++){
			String[] content=sheet.get(i);
			for(int j=0;j<=content.length-1;j++){
				System.out.print(content[j]+"  ");
			}
			System.out.println();
		}
	}catch(RemoteException e){
		e.printStackTrace();
	}
}
public static void main(String[] args){
	TestInventoryCheck test=new TestInventoryCheck();
	test.testInventoryCheckSheet();
}
}
