package blDrivers.commodityBlDrivers;

import blImpl.commodityBl.WarningValue;

//2017.11.06 Beibei Zhang
public class WarningValue_Driver {
public void drive(WarningValue warningValue){
	//test getWaningValue(String goodsID)
	int warningvalue=warningValue.getWarningValue("11001");
	System.out.println("The warning value of Goods 11001 is£º"+warningvalue);
}
}
