package rmi.remoteHelper;

import rmi.register.commodityRegister.ActualInventoryCheckRegister;
import rmi.register.commodityRegister.AddGoodsRegister;
import rmi.register.commodityRegister.DeleteGoodsRegister;
import rmi.register.commodityRegister.GoodsClassificationRegister;
import rmi.register.commodityRegister.GoodsInforRegister;
import rmi.register.commodityRegister.InventoryCheckRegister;
import rmi.register.commodityRegister.SaveBillRegister;
import rmi.register.commodityRegister.StockCheckRegister;

public class CommodityRemoteHelper {
	private ActualInventoryCheckRegister actualInventoryRegister=new ActualInventoryCheckRegister();
	private AddGoodsRegister addGoodsRegister=new AddGoodsRegister();
	private DeleteGoodsRegister deleteGoodsRegister=new DeleteGoodsRegister();
	private GoodsClassificationRegister goodsClassificationRegister=new GoodsClassificationRegister();
	private GoodsInforRegister goodsInforRegister=new GoodsInforRegister();
	private InventoryCheckRegister inventoryCheckRegister=new InventoryCheckRegister();
	private SaveBillRegister saveBillRegister=new SaveBillRegister();
	private StockCheckRegister stockCheckRegister=new StockCheckRegister();
public CommodityRemoteHelper(){
	actualInventoryRegister.register();
	addGoodsRegister.register();
	deleteGoodsRegister.register();
	goodsClassificationRegister.register();
	goodsInforRegister.register();
	inventoryCheckRegister.register();
	saveBillRegister.register();
	stockCheckRegister.register();
}
}
