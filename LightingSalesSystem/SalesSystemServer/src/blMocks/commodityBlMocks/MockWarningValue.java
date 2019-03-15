package blMocks.commodityBlMocks;

import blImpl.commodityBl.WarningValue;

//2017.11.06 Beibei Zhang
public class MockWarningValue extends WarningValue{
	public int getWarningValue(String goodsID){
		return 20;
	}
}
