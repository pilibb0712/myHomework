package test.commodityTest.commodityBlTest;

import java.rmi.RemoteException;

import blImpl.commodityBl.ActualInventoryCheckController;

public class TestActualInventoryCheck {
public static void main(String[] args){
	ActualInventoryCheckController actualCheck=new ActualInventoryCheckController();
	try{
	int dValue=actualCheck.actualInventoryCheck("G00000000", 60);
	System.out.println(dValue);
	}catch(RemoteException e){
		e.printStackTrace();
	}
}
}
