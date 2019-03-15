package blMocks.commodityBlMocks;

import blImpl.commodityBl.ID;

public class MockID extends ID{
	public String rootClassificationID(){
		return "1";
	}
	public String sonClassificationID(String fatherClassification){
		return "11";
	}
	public String newGoodsID(String classification){
		return "11001";
	}

}
