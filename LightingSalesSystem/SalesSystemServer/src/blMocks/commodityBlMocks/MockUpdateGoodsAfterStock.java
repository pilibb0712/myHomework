package blMocks.commodityBlMocks;

import blImpl.commodityBl.UpdateGoodsAfterStock;
import vo.StockInOutVO;

public class MockUpdateGoodsAfterStock extends UpdateGoodsAfterStock{
	public boolean updateAmountAndPrice(StockInOutVO vo){
		return true;
	}
	public boolean update(StockInOutVO vo){
		return true;
	}

}
