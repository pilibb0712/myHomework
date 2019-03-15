package blImpl.commodityBl;

import dataImpl.commodityData.GoodsData;
import po.GoodsPO;
import vo.StockInOutVO;

public class UpdateGoodsAfterStock {
	private boolean updateAmountAndPrice(StockInOutVO vo){
		//测完了之后改成private
		// TODO Auto-generated method stub
				String goodsID=vo.getGoodsID();
				int stockAmount=vo.getAmount();
				//stockIN:stockAmount>0
				//stockOUT:stockAmount<0
				GoodsData goodsData=new GoodsData();
				GoodsPO theGoods=goodsData.find(goodsID);
				if(theGoods==null){
					return false;
					//false means not find the goods
					//but in theory, the case will not happen
					//because ui do the check of existence each time before add/delete...
				}
				int preAmount=theGoods.getAmount();
				int newAmount=preAmount+stockAmount;
				theGoods.setAmount(newAmount);
				//update the amount of the goods

				String currentCost=vo.getCurrentCostPrice();
				String currentSelling=vo.getCurrentSellingPrice();
				if(currentCost==null){
					if(currentSelling==null){

					}else{
						theGoods.setCurrentSellingPrice(currentSelling);
					}
				}else{
						theGoods.setCurrentCostPrice(currentCost);
				}
				//update the price

                goodsData.update(theGoods);
		        return true;
	}
	public boolean update(StockInOutVO vo){
		updateAmountAndPrice(vo);
		return true;
	}
}
