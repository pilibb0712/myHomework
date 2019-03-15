/**
 * 仓库管理员进行增加新商品与商品入库
 * @author BeibeiZhang
 * @version 2017.11.19  2017.11.28
 *
 */
package blImpl.commodityBl;

import java.rmi.RemoteException;

import blService.commodityBlService.AddGoodsBlService;
import dataImpl.commodityData.GoodsData;
import dataImpl.commodityData.StockInOutRecord;
import po.GoodsPO;
import po.StockInOutPO;
import assistant.convertors.CommodityPOVOConvertor;
import vo.GoodsVO;
import vo.StockInOutVO;

public class AddGoodsController implements AddGoodsBlService{

	/**
	 * public boolean addNewGoods(GoodsVO goodsVO,StockInOutVO vo) throws RemoteException
	 * @param1 GoodsVO:to insert a new goods
	 * @param2 StockInOutVO:to insert a new stockInRecord
	 * @return boolean
	 * @version 2017.11.28
	 *
	 */
	@Override
	public boolean addNewGoods(GoodsVO goodsVO,StockInOutVO stockVO) throws RemoteException {
		// TODO Auto-generated method stub

		String classification=goodsVO.getClassification();
		ID setID=new ID();
		String id=setID.newGoodsID(classification);
		goodsVO.setNumber(id);
		//set id of the new goods
		stockVO.setGoodsID(id);
		//新商品在入库时还没有编号，此时才有编号，故这时才能设置这条出入库信息的商品编号

		GoodsData goodsData=new GoodsData();
		CommodityPOVOConvertor turn=new CommodityPOVOConvertor();
	    GoodsPO theGoods=turn.GoodsVOtoPO(goodsVO);
		goodsData.insert(theGoods);
		//update GoodsData

		UpdateClassificationOfGoods updateClassification=new UpdateClassificationOfGoods();
		updateClassification.updateNewClassification(goodsVO);
		//update the goodsList of the classification


		StockInOutRecord stockRecords=new StockInOutRecord();
		StockInOutPO stockRecord=turn.StockInOutVOtoPO(stockVO);
		stockRecords.insert(stockRecord);
		//update stockInOutRecord

		return true;
	}


	/**
	 * public boolean add(StockInOutVO vo) throws RemoteException
	 * @param StockInOutVO:to insert a new stockInRecord;update the amount of theGoods
	 * @return boolean
	 * @version 2017.11.28
	 *
	 */
	@Override
	public boolean add(StockInOutVO vo) throws RemoteException {
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

}
