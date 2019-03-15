package blInteract.commodityInteract;

import blImpl.commodityBl.ClassificationListImpl;
import blImpl.commodityBl.GoodsListImpl;

public class CommodityBlInteractServiceFactory {
public ClassificationListService getClassificationListBlService(){
	ClassificationListService service=new ClassificationListImpl();
	return service;
}
public GoodsListService getGoodsListBlService(){
	GoodsListService service=new GoodsListImpl();
	return service;
}
}
