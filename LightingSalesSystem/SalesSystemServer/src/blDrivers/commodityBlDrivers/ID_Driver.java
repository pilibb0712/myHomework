package blDrivers.commodityBlDrivers;
//2017.11.06 Beibei Zhang
import blImpl.commodityBl.ID;

public class ID_Driver {
public void drive(ID id){
	//test ID.rootClassificationID();
	String rootID=id.rootClassificationID();
	System.out.println("The ID of a new root classification is: "+rootID);
	
	//test ID.sonClassificationID(String fatherClassificationTag);
	String sonClassificationID=id.sonClassificationID("1");
	System.out.println("The ID of a new son classification of classification 1 is: "+sonClassificationID);
	
	//test ID.newGoodsID(String classificationTag);
	String newGoodsID=id.newGoodsID("11");
	System.out.println("The ID of a new goods of classification 11 is: "+newGoodsID);
	
}
}
