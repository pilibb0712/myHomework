package blImpl.listBl;

import java.util.ArrayList;

import assistant.convertors.CommodityPOVOConvertor;
import assistant.type.BillStateEnum;
import blInteract.billBlInteract.BillBlInteractServiceFactory;
import blInteract.billBlInteract.GiftBillBlService;
import blInteract.billBlInteract.InventoryLossBillBlService;
import blInteract.billBlInteract.InventoryOverBillBlService;
import blInteract.billBlInteract.PurchaseBillBlService;
import blInteract.billBlInteract.PurchaseReturnBillBlService;
import blInteract.billBlInteract.SalesBillBlService;
import blInteract.billBlInteract.SalesReturnBillBlService;
import blInteract.commodityInteract.CommodityBlInteractServiceFactory;
import blInteract.commodityInteract.GoodsListService;
import po.BusinessConditionListPO;
import po.GiftBillPO;
import po.GoodsPO;
import po.InventoryLossBillPO;
import po.InventoryOverBillPO;
import po.PurchaseBillPO;
import po.PurchaseReturnBillPO;
import po.SalesBillPO;
import po.SalesReturnBillPO;
import vo.GoodsVO;

/**
 * 经营情况表逻辑实现
 * @author guxinyu
 * @version 2017.12.23
 *
 */
public class BusinessConditionListBl {
	private static BusinessConditionListBl bl;//单件对象
	
	private BillBlInteractServiceFactory billFactory=new BillBlInteractServiceFactory();
	private PurchaseBillBlService purchaseBillService=billFactory.getPurchaseBillBlService();
	private PurchaseReturnBillBlService purchaseReturnBillService=billFactory.getPurchaseReturnBillBlService();
	private SalesBillBlService salesBillService=billFactory.getSalesBillBlService();
	private SalesReturnBillBlService salesReturnBillService=billFactory.getSalesReturnBillBlService();
	private InventoryOverBillBlService inventoryOverBillService=billFactory.getInventoryOverBillBlService();
	private InventoryLossBillBlService inventoryLossBillService=billFactory.getInventoryLossBillBlService();
	private GiftBillBlService giftBillService=billFactory.getGiftBillBlService();
	
	private CommodityBlInteractServiceFactory commodityService=new CommodityBlInteractServiceFactory();
	private GoodsListService goodsListService=commodityService.getGoodsListBlService();
	
	private BusinessConditionListBl(){
		
	}
	
	public static BusinessConditionListBl getInstance(){
		if(bl==null)
			bl=new BusinessConditionListBl();
		
		return bl;
	}
	
	public BusinessConditionListPO getList(){
		ArrayList<PurchaseBillPO> purBill=purchaseBillService.getBillListByState(BillStateEnum.DONE);
		ArrayList<PurchaseReturnBillPO> purReBill=purchaseReturnBillService.getBillListByState(BillStateEnum.DONE);
		ArrayList<SalesBillPO> salesBill=salesBillService.getBillListByState(BillStateEnum.DONE);
		ArrayList<SalesReturnBillPO> salesReBill=salesReturnBillService.getBillListByState(BillStateEnum.DONE);
		ArrayList<InventoryOverBillPO> overBill=inventoryOverBillService.getBillListByState(BillStateEnum.DONE);
		ArrayList<InventoryLossBillPO> lossBill=inventoryLossBillService.getBillListByState(BillStateEnum.DONE);
		ArrayList<GiftBillPO> giftBill=giftBillService.getBillListByState(BillStateEnum.DONE);
		ArrayList<GoodsVO> goods=goodsListService.getGoodsList();
		
		ArrayList<GoodsPO> goodsPO=new ArrayList<GoodsPO>();
		
		for(int i=0;i<goods.size();i++){
			GoodsPO po=CommodityPOVOConvertor.GoodsVOtoPO(goods.get(i));
			goodsPO.add(po);
		}
		
		BusinessConditionListPO list=new BusinessConditionListPO(
				purBill, purReBill, salesBill, salesReBill, overBill, lossBill,
				giftBill, goodsPO);
		
		return list;
	}
}
