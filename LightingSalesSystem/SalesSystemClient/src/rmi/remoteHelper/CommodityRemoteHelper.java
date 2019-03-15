package rmi.remoteHelper;

import blService.commodityBlService.ActualInventoryCheckBlService;
import blService.commodityBlService.AddGoodsBlService;
import blService.commodityBlService.DeleteGoodsBlService;
import blService.commodityBlService.GoodsClassificationBlService;
import blService.commodityBlService.GoodsInforBlService;
import blService.commodityBlService.InventoryCheckBlService;
import blService.commodityBlService.SaveBillBlService;
import blService.commodityBlService.StockCheckBlService;
import rmi.linker.commodityServiceLinker.ActualInventoryCheckServiceLinker;
import rmi.linker.commodityServiceLinker.AddGoodsServiceLinker;
import rmi.linker.commodityServiceLinker.DeleteGoodsServiceLinker;
import rmi.linker.commodityServiceLinker.GoodsClassificationServiceLinker;
import rmi.linker.commodityServiceLinker.GoodsInforServiceLinker;
import rmi.linker.commodityServiceLinker.InventoryCheckServiceLinker;
import rmi.linker.commodityServiceLinker.SaveBillServiceLinker;
import rmi.linker.commodityServiceLinker.StockCheckServiceLinker;

public class CommodityRemoteHelper {
private static CommodityRemoteHelper remoteHelper =new CommodityRemoteHelper();

	private CommodityRemoteHelper(){

	}

	public static CommodityRemoteHelper getInstance(){
		return remoteHelper;
	}

	public ActualInventoryCheckBlService getActualInventoryCheckBlService(){
		return ActualInventoryCheckServiceLinker.getInstance().getActualInventoryCheckBlService();
	}

	public AddGoodsBlService getAddGoodsBlService(){
		return AddGoodsServiceLinker.getInstance().getAddGoodsBlService();
	}

	public DeleteGoodsBlService getDeleteGoodsBlService(){
		return DeleteGoodsServiceLinker.getInstance().getDeleteGoodsBlService();
	}

	public GoodsClassificationBlService getGoodsClassificationBlService(){
		return GoodsClassificationServiceLinker.getInstance().getGoodsClassificationBlService();
	}

	public GoodsInforBlService getGoodsInforBlService(){
		return GoodsInforServiceLinker.getInstance().getGoodsInforBlService();
	}

	public InventoryCheckBlService getInventoryCheckBlService(){
		return InventoryCheckServiceLinker.getInstance().getInventoryCheckBlService();
	}

	public SaveBillBlService getSaveBillBlService(){
		return SaveBillServiceLinker.getInstance().getSaveBillBlService();
	}

	public StockCheckBlService getStockCheckBlService(){
		return StockCheckServiceLinker.getInstance().getStockCheckBlService();
	}
}
