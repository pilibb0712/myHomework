package blImpl.commodityBl;

import java.rmi.RemoteException;

import blService.commodityBlService.DeleteGoodsBlService;
import dataImpl.commodityData.GoodsData;
import dataImpl.commodityData.StockInOutRecord;
import po.GoodsPO;
import po.StockInOutPO;
import assistant.convertors.CommodityPOVOConvertor;
import vo.GoodsVO;
import vo.StockInOutVO;;

public class DeleteGoodsController implements DeleteGoodsBlService{

	@Override
	public boolean delete(StockInOutVO vo) throws RemoteException {
		// TODO Auto-generated method stub
		UpdateGoodsAfterStock updateGoods=new UpdateGoodsAfterStock();
		updateGoods.update(vo);
		//update the amount and price of theGoods

		CommodityPOVOConvertor voTOpo=new CommodityPOVOConvertor();
		StockInOutPO stockPO=voTOpo.StockInOutVOtoPO(vo);
		StockInOutRecord stockRecords=new StockInOutRecord();
		stockRecords.insert(stockPO);
		//update stockInOutRecord
		return true;
	}

	@Override
	public boolean obsoleteGoods(GoodsVO vo) throws RemoteException {
		// TODO Auto-generated method stub

		UpdateClassificationOfGoods update=new UpdateClassificationOfGoods();
		update.updateOldClassification(vo);
		//update goodsList of the classification of the goods

		CommodityPOVOConvertor voTOpo=new CommodityPOVOConvertor();
		GoodsPO po=voTOpo.GoodsVOtoPO(vo);
		GoodsData goodsData=new GoodsData();
		goodsData.delete(po);
		//update goodsData
        //这一步必须要在update goodsList之后，因为在update goodsList中会需要通过goodsData获得该商品的po

		return true;
	}

	@Override
	public int compareTowarning(String goodsID, int currentAmount) throws RemoteException {
		// TODO Auto-generated method stub
		WarningValue warningObj=new WarningValue();
		int warningValue=warningObj.getWarningValue(goodsID);
		int dValue=currentAmount-warningValue;
		//currentValue:the amount of the goods after stockOut
		//dValue:
		//negative:currentAmount<warningValue
		//active:currentAmount>warningValue
		//0:currentAmount=warningValue

		//<=0:warning situation
		return dValue;
	}

}
