package blMocks.commodityBlMocks;

import blImpl.commodityBl.UpdateClassificationOfGoods;
import vo.GoodsVO;

public class MockUpdateClassificationOfGoods extends UpdateClassificationOfGoods{
	public boolean updateOldClassification(GoodsVO goods){
		return true;
	}
	public boolean updateNewClassification(GoodsVO goods){
		return true;
	}
	public boolean updateGoodsClassification(GoodsVO goods){
		return true;
	}
	public boolean updateClassificationOfGoods(GoodsVO goods){
		return true;
	}
}
