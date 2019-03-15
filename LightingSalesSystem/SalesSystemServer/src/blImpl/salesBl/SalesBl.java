package blImpl.salesBl;

import java.rmi.RemoteException;
import java.util.ArrayList;

import assistant.convertors.SalesmanBillsPOVOConvertor;
import assistant.convertors.UserInfoPOVOConvertor;
import assistant.type.CustomerLevelEnum;
import vo.ComboStrategyVO;
import vo.GiftBillVO;
import vo.GoodsVO;
import vo.PurchaseBillVO;
import vo.PurchaseReturnBillVO;
import vo.SalesBillVO;
import vo.SalesGoodsVO;
import vo.SalesReturnBillVO;
import vo.TotalStrategyVO;
import blInteract.billBlInteract.BillBlInteractServiceFactory;
import blInteract.billBlInteract.PurchaseBillBlService;
import blInteract.billBlInteract.PurchaseReturnBillBlService;
import blInteract.billBlInteract.SalesBillBlService;
import blInteract.billBlInteract.SalesReturnBillBlService;
import blInteract.commodityInteract.CommodityBlInteractServiceFactory;
import blInteract.commodityInteract.GoodsListService;
import blInteract.promotionBlInteract.ComboStrategyForSalesManService;
import blInteract.promotionBlInteract.PromotionBlInteractFactory;
import blInteract.promotionBlInteract.TotalStrategyForSalesManService;
import blInteract.promotionBlInteract.UserStrategyForSalesManService;
import po.PurchaseBillPO;
import po.PurchaseReturnBillPO;
import po.SalesBillPO;
import po.SalesReturnBillPO;
import po.UserInfoPO;
import vo.UserInfoVO;
import vo.UserStrategyVO;

/**
 * 进货销售人员制定单据的业务逻辑
 * @author 王宁一
 * @version 2017.11.7
 *
 */

public class SalesBl {
	
	BillBlInteractServiceFactory factory=new BillBlInteractServiceFactory();
	PurchaseBillBlService purBillBlService=factory.getPurchaseBillBlService();
	PurchaseReturnBillBlService purReBillBlService=factory.getPurchaseReturnBillBlService();
	SalesBillBlService salesBillBlService=factory.getSalesBillBlService();
	SalesReturnBillBlService salesReBillBlService=factory.getSalesReturnBillBlService();
	CommodityBlInteractServiceFactory GoodsFactory=new CommodityBlInteractServiceFactory();
	GoodsListService goodsListService=GoodsFactory.getGoodsListBlService();
	TotalStrategyForSalesManService totalStrategyService=PromotionBlInteractFactory.getTotalStrategyForSalesManService();
	ComboStrategyForSalesManService comboStrategyService=PromotionBlInteractFactory.getComboStrategyForSalesManService();
	UserStrategyForSalesManService userStrategyService=PromotionBlInteractFactory.getUserStrategyForSalesManService();
	
	
	/**
	 * 新建一张供应商进货单
	 * @param PurchaseBillVO
	 * @return boolean, true表示操作成功
	 */
	protected boolean NewPurchase(PurchaseBillVO vo) {
		PurchaseBillPO bill=SalesmanBillsPOVOConvertor.purchaseBillVOtoPO(vo);
		boolean success=purBillBlService.createBill(bill);
		return success;
	}
	
	/**
	 * 新建一张供应商进货单草稿
	 * @param PurchaseBillVO
	 * @return boolean, true表示操作成功
	 */
	protected boolean NewPurchaseDraft(PurchaseBillVO vo) {
		PurchaseBillPO bill=SalesmanBillsPOVOConvertor.purchaseBillVOtoPO(vo);
		boolean success=purBillBlService.saveAsDraftBill(bill);
		return success;
	}
	
	/**
	 * 对已经存在草稿进行内容更新
	 * @param PurchaseBillVO
	 * @return boolean, true表示操作成功
	 */
	protected boolean UpdatePurchaseDraft(PurchaseBillVO vo) {
		PurchaseBillPO bill=SalesmanBillsPOVOConvertor.purchaseBillVOtoPO(vo);
		boolean success=purBillBlService.updateDraftBill(bill);
		return success;
	}
	
	
	/**
	 * 通过Id删除一张进货单草稿
	 * @param String
	 * @return boolean, true表示操作成功
	 */
	protected boolean DeletePurchaseDraft(String Id) {
		boolean success=purBillBlService.deleteDraftBill(Id);
		return success;
	}
	
	/**
	 * 将草稿提交为正式单据
	 * @param String
	 * @return boolean, true表示操作成功
	 */
	protected boolean SubmitPurchase(String Id) {
		boolean success=purBillBlService.submitBill(Id);
		return success;
	}
	
	/**
	 * 新建一张供应商进货退货单
	 * @param PurchaseReturnBillVO
	 * @return boolean, true表示操作成功
	 */
	protected boolean NewPurReturn(PurchaseReturnBillVO vo) {
		PurchaseReturnBillPO bill=SalesmanBillsPOVOConvertor.purchaseReturnBillVOtoPO(vo);
		boolean success=purReBillBlService.createBill(bill);
		return success;		
	}
	
	/**
	 * 新建一张供应商进货退货单草稿
	 * @param PurchaseReturnBillVO
	 * @return boolean, true表示操作成功
	 */
	protected boolean NewPurReturnDraft(PurchaseReturnBillVO vo) {
		PurchaseReturnBillPO bill=SalesmanBillsPOVOConvertor.purchaseReturnBillVOtoPO(vo);
		boolean success=purReBillBlService.saveAsDraftBill(bill);
		return success;
	}
	
	/**
	 * 更新一张供应商进货退货单草稿
	 * @param PurchaseReturnBillVO
	 * @return boolean, true表示操作成功
	 */
	protected boolean UpdatePurReturnDraft(PurchaseReturnBillVO vo) {
		PurchaseReturnBillPO bill=SalesmanBillsPOVOConvertor.purchaseReturnBillVOtoPO(vo);
		boolean success=purReBillBlService.updateDraftBill(bill);
		return success;
	}
	
	/**
	 * 通过Id删除一张进货退货单草稿
	 * @param String
	 * @return boolean, true表示操作成功
	 */
	protected boolean DeletePurReturnDraft(String Id) {
		boolean success=purReBillBlService.deleteDraftBill(Id);
		return success;
	}
	
	/**
	 * 将草稿提交为正式单据
	 * @param String
	 * @return boolean, true表示操作成功
	 */
	protected boolean SubmitPurReturn(String Id) {
		boolean success=purReBillBlService.submitBill(Id);
		return success;
	}
	
	/**
	 * 新建一张销售商出货单
	 * @param SalesBillVO
	 * @return boolean, true表示操作成功
	 */
	protected boolean NewSales(SalesBillVO vo) {
		SalesBillPO bill = SalesmanBillsPOVOConvertor.salesBillVOtoPO(vo);
		boolean success = salesBillBlService.createBill(bill);
		return success;
	}
	
	/**
	 * 新建一张销售商出货单草稿
	 * @param SalesBillVO
	 * @return boolean, true表示操作成功
	 */
	protected boolean NewSalesDraft(SalesBillVO vo) {
		SalesBillPO bill = SalesmanBillsPOVOConvertor.salesBillVOtoPO(vo);
		boolean success = salesBillBlService.saveAsDraftBill(bill);
		return success;
	}
	
	/**
	 * 更新一张销售商出货单草稿
	 * @param SalesBillVO
	 * @return boolean, true表示操作成功
	 */
	protected boolean UpdateSalesDraft(SalesBillVO vo) {
		SalesBillPO bill = SalesmanBillsPOVOConvertor.salesBillVOtoPO(vo);
		boolean success = salesBillBlService.updateDraftBill(bill);
		return success;
	}
	
	/**
	 * 通过Id删除一张出货单草稿
	 * @param String
	 * @return boolean, true表示操作成功
	 */
	protected boolean DeleteSalesDraft(String Id) {
		boolean success=salesBillBlService.deleteDraftBill(Id);
		return success;
	}
	
	/**
	 * 将草稿提交为正式单据
	 * @param String
	 * @return boolean, true表示操作成功
	 */
	protected boolean SubmitSales(String Id) {
		boolean success=salesBillBlService.submitBill(Id);
		return success;
	}
	
	/**
	 * 新建一张销售商出货退货单
	 * @param SalesReturnBillVO
	 * @return boolean, true表示操作成功
	 */
	protected boolean NewSalReturn(SalesReturnBillVO vo) {
		SalesReturnBillPO bill=SalesmanBillsPOVOConvertor.salesReturnBillVOtoPO(vo);
		boolean success=salesReBillBlService.createBill(bill);
		return success;
	}
	
	/**
	 * 新建一张销售商出货退货单草稿
	 * @param SalesReturnBillVO
	 * @return boolean, true表示操作成功
	 */
	protected boolean NewSalReturnDraft(SalesReturnBillVO vo) {
		SalesReturnBillPO bill=SalesmanBillsPOVOConvertor.salesReturnBillVOtoPO(vo);
		boolean success=salesReBillBlService.saveAsDraftBill(bill);
		return success;
	}
	
	/**
	 * 更新一张销售商出货退货单草稿
	 * @param SalesReturnBillVO
	 * @return boolean, true表示操作成功
	 */
	protected boolean UpdateSalReturnDraft(SalesReturnBillVO vo) {
		SalesReturnBillPO bill=SalesmanBillsPOVOConvertor.salesReturnBillVOtoPO(vo);
		boolean success=salesReBillBlService.updateDraftBill(bill);
		return success;
	}
	
	/**
	 * 通过Id删除一张出货退货单草稿
	 * @param String
	 * @return boolean, true表示操作成功
	 */
	protected boolean DeleteSalReturnDraft(String Id) {
		boolean success=salesReBillBlService.deleteDraftBill(Id);
		return success;
	}
	
	/**
	 * 将草稿提交为正式单据
	 * @param String
	 * @return boolean, true表示操作成功
	 */
	protected boolean SubmitSalReturn(String Id) {
		boolean success=salesReBillBlService.submitBill(Id);
		return success;
	}
	
	/**
	 * 制定单据时提供商品列表
	 * @return ArrayList<GoodsVO>，ArrayList中每一项为一种商品
	 */
	protected ArrayList<GoodsVO> CommoditySelect() {
		
		ArrayList<GoodsVO> goods=goodsListService.getGoodsList();
		return goods;
	}
	/**
	 * @see blService.salesBlService.SalesBlService#getPurchaseDraftBillsList(po.UserInfoVO)
	 * @param UserInfo
	 * @return
	 * @throws RemoteException
	 */
	protected ArrayList<PurchaseBillVO> getPurchaseDraftBillsList(UserInfoVO UserInfo) {
		// TODO Auto-generated method stub
		UserInfoPO user=UserInfoPOVOConvertor.voToPO(UserInfo);
		if(purBillBlService.getDraftBillsListByCreater(user)!=null) {
			ArrayList<PurchaseBillPO> pos=purBillBlService.getDraftBillsListByCreater(user);
			ArrayList<PurchaseBillVO> drafts=new ArrayList<>();
			for(int i=0;i<pos.size();i++) {
				drafts.add(SalesmanBillsPOVOConvertor.purchaseBillPOtoVO(pos.get(i)));
			}
			return drafts;
		}else {
			return null;
		}		
	}

	/**
	 * @see blService.salesBlService.SalesBlService#getPurchaseTBDBillsList(po.UserInfoVO)
	 * @param UserInfo
	 * @return
	 * @throws RemoteException
	 */
	protected ArrayList<PurchaseBillVO> getPurchaseTBDBillsList(UserInfoVO UserInfo) {
		// TODO Auto-generated method stub
		UserInfoPO user=UserInfoPOVOConvertor.voToPO(UserInfo);
		ArrayList<PurchaseBillPO> pos=purBillBlService.getTBDBillsListByCreater(user);
		ArrayList<PurchaseBillVO> list=new ArrayList<>();
		for(int i=0;i<pos.size();i++) {
			list.add(SalesmanBillsPOVOConvertor.purchaseBillPOtoVO(pos.get(i)));
		}
		return list;
	}

	/**
	 * @see blService.salesBlService.SalesBlService#getPurchaseDeniedBillsListByCreater(po.UserInfoVO)
	 * @param UserInfo
	 * @return
	 * @throws RemoteException
	 */
	protected ArrayList<PurchaseBillVO> getPurchaseDeniedBillsListByCreater(UserInfoVO UserInfo) {
		// TODO Auto-generated method stub
		UserInfoPO user=UserInfoPOVOConvertor.voToPO(UserInfo);
		ArrayList<PurchaseBillPO> pos=purBillBlService.getDeniedBillsListByCreater(user);
		ArrayList<PurchaseBillVO> list=new ArrayList<>();
		for(int i=0;i<pos.size();i++) {
			list.add(SalesmanBillsPOVOConvertor.purchaseBillPOtoVO(pos.get(i)));
		}
		return list;
	}
	
	/**
	 * @see blService.salesBlService.SalesBlService#getPurchaseReturnDraftBillsList(po.UserInfoVO)
	 * @param UserInfo
	 * @return
	 * @throws RemoteException
	 */
	public ArrayList<PurchaseReturnBillVO> getPurchaseReturnDraftBillsList(UserInfoVO UserInfo) {
		// TODO Auto-generated method stub
		UserInfoPO user=UserInfoPOVOConvertor.voToPO(UserInfo);
		ArrayList<PurchaseReturnBillPO> pos=purReBillBlService.getDraftBillsListByCreater(user);
		ArrayList<PurchaseReturnBillVO> drafts=new ArrayList<>();
		for(int i=0;i<pos.size();i++) {
			drafts.add(SalesmanBillsPOVOConvertor.purchaseReturnBillPOtoVO(pos.get(i)));
		}
		
		return drafts;
	}

	/**
	 * @see blService.salesBlService.SalesBlService#getPurchaseReturnTBDBillsList(po.UserInfoVO)
	 * @param UserInfo
	 * @return
	 * @throws RemoteException
	 */
	public ArrayList<PurchaseReturnBillVO> getPurchaseReturnTBDBillsList(UserInfoVO UserInfo) {
		// TODO Auto-generated method stub
		UserInfoPO user=UserInfoPOVOConvertor.voToPO(UserInfo);
		ArrayList<PurchaseReturnBillPO> pos=purReBillBlService.getTBDBillsListByCreater(user);
		ArrayList<PurchaseReturnBillVO> list=new ArrayList<>();
		for(int i=0;i<pos.size();i++) {
			list.add(SalesmanBillsPOVOConvertor.purchaseReturnBillPOtoVO(pos.get(i)));
		}
		return list;
	}

	/**
	 * @see blService.salesBlService.SalesBlService#getPurchaseReturnDeniedBillsListByCreater(po.UserInfoVO)
	 * @param UserInfo
	 * @return
	 * @throws RemoteException
	 */
	public ArrayList<PurchaseReturnBillVO> getPurchaseReturnDeniedBillsListByCreater(UserInfoVO UserInfo) {
		// TODO Auto-generated method stub
		UserInfoPO user=UserInfoPOVOConvertor.voToPO(UserInfo);
		ArrayList<PurchaseReturnBillPO> pos=purReBillBlService.getDeniedBillsListByCreater(user);
		ArrayList<PurchaseReturnBillVO> list=new ArrayList<>();
		for(int i=0;i<pos.size();i++) {
			list.add(SalesmanBillsPOVOConvertor.purchaseReturnBillPOtoVO(pos.get(i)));
		}
		return list;
	}
	
	/**
	 * @see blService.salesBlService.SalesBlService#getSalesDraftBillsList(po.UserInfoVO)
	 * @param UserInfo
	 * @return
	 * @throws RemoteException
	 */
	public ArrayList<SalesBillVO> getSalesDraftBillsList(UserInfoVO UserInfo) {
		// TODO Auto-generated method stub
		UserInfoPO user=UserInfoPOVOConvertor.voToPO(UserInfo);
		ArrayList<SalesBillPO> pos=salesBillBlService.getDraftBillsListByCreater(user);
		ArrayList<SalesBillVO> drafts=new ArrayList<>();
		for(int i=0;i<pos.size();i++) {
			drafts.add(SalesmanBillsPOVOConvertor.salesBillPOtoVO(pos.get(i)));
		}		
		return drafts;
	}

	/**
	 * @see blService.salesBlService.SalesBlService#getSalesTBDBillsList(po.UserInfoVO)
	 * @param UserInfo
	 * @return
	 * @throws RemoteException
	 */
	public ArrayList<SalesBillVO> getSalesTBDBillsList(UserInfoVO UserInfo) {
		// TODO Auto-generated method stub
		UserInfoPO user=UserInfoPOVOConvertor.voToPO(UserInfo);
		ArrayList<SalesBillPO> pos=salesBillBlService.getTBDBillsListByCreater(user);
		ArrayList<SalesBillVO> list=new ArrayList<>();
		for(int i=0;i<pos.size();i++) {
			list.add(SalesmanBillsPOVOConvertor.salesBillPOtoVO(pos.get(i)));
		}		
		return list;
	}

	/**
	 * @see blService.salesBlService.SalesBlService#getSalesDeniedBillsListByCreater(po.UserInfoVO)
	 * @param UserInfo
	 * @return
	 * @throws RemoteException
	 */
	public ArrayList<SalesBillVO> getSalesDeniedBillsListByCreater(UserInfoVO UserInfo){
		// TODO Auto-generated method stub
		UserInfoPO user=UserInfoPOVOConvertor.voToPO(UserInfo);
		ArrayList<SalesBillPO> pos=salesBillBlService.getDeniedBillsListByCreater(user);
		ArrayList<SalesBillVO> list=new ArrayList<>();
		for(int i=0;i<pos.size();i++) {
			list.add(SalesmanBillsPOVOConvertor.salesBillPOtoVO(pos.get(i)));
		}		
		return list;
	}
	
	/**
	 * @see blService.salesBlService.SalesBlService#getSalesReturnDeniedBillsListByCreater(po.UserInfoVO)
	 * @param UserInfo
	 * @return
	 * @throws RemoteException
	 */
	public ArrayList<SalesReturnBillVO> getSalesReturnDeniedBillsListByCreater(UserInfoVO UserInfo) {
		// TODO Auto-generated method stub
		UserInfoPO user=UserInfoPOVOConvertor.voToPO(UserInfo);
		ArrayList<SalesReturnBillPO> pos=salesReBillBlService.getDeniedBillsListByCreater(user);
		ArrayList<SalesReturnBillVO> list=new ArrayList<>();
		for(int i=0;i<pos.size();i++) {
			list.add(SalesmanBillsPOVOConvertor.salesReturnBillPOtoVO(pos.get(i)));
		}		
		return list;
	}

	/**
	 * @see blService.salesBlService.SalesBlService#getSalesReturnDraftBillsList(po.UserInfoVO)
	 * @param UserInfo
	 * @return
	 * @throws RemoteException
	 */
	public ArrayList<SalesReturnBillVO> getSalesReturnDraftBillsList(UserInfoVO UserInfo) {
		// TODO Auto-generated method stub
		UserInfoPO user=UserInfoPOVOConvertor.voToPO(UserInfo);
		ArrayList<SalesReturnBillPO> pos=salesReBillBlService.getDraftBillsListByCreater(user);
		ArrayList<SalesReturnBillVO> list=new ArrayList<>();
		for(int i=0;i<pos.size();i++) {
			list.add(SalesmanBillsPOVOConvertor.salesReturnBillPOtoVO(pos.get(i)));
		}		
		return list;
	}

	/**
	 * @see blService.salesBlService.SalesBlService#getSalesReturnTBDBillsList(po.UserInfoVO)
	 * @param UserInfo
	 * @return
	 * @throws RemoteException
	 */
	public ArrayList<SalesReturnBillVO> getSalesReturnTBDBillsList(UserInfoVO UserInfo) {
		// TODO Auto-generated method stub
		UserInfoPO user=UserInfoPOVOConvertor.voToPO(UserInfo);
		ArrayList<SalesReturnBillPO> pos=salesReBillBlService.getTBDBillsListByCreater(user);
		ArrayList<SalesReturnBillVO> list=new ArrayList<>();
		for(int i=0;i<pos.size();i++) {
			list.add(SalesmanBillsPOVOConvertor.salesReturnBillPOtoVO(pos.get(i)));
		}		
		return list;
	}
	
	public TotalStrategyVO getTotalStrategy(double consumption) {
		
		TotalStrategyVO strategy=totalStrategyService.getBestStrategy(consumption);
		return strategy;
	}
	
	public ComboStrategyVO getComboStrategy(ArrayList<SalesGoodsVO> salesManCommodityVOs, double consumption) {
		
		ComboStrategyVO strategy=comboStrategyService.getBestStrategy(salesManCommodityVOs, consumption);
		return strategy;
	}
	
	public UserStrategyVO getUserStrategy(CustomerLevelEnum customerLevel, double consumption) {
		
		UserStrategyVO strategy=userStrategyService.getBestStrategy(customerLevel, consumption);
		return strategy;
	}
	
	public GiftBillVO generateGiftBillByTotalStrategy(TotalStrategyVO vo) {
		GiftBillVO gift=totalStrategyService.generateGiftBillByTotalStrategy(vo);
		return gift;
	}
	
	public GiftBillVO generateGiftBillByUserStrategy(UserStrategyVO vo) {
		GiftBillVO gift=userStrategyService.generateGiftBillByTotalStrategy(vo);
		return gift;
	}
}





























