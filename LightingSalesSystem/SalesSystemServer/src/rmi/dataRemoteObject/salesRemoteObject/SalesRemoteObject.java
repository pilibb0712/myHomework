/**
 * 
 */
package rmi.dataRemoteObject.salesRemoteObject;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;

import assistant.type.CustomerLevelEnum;
import blImpl.salesBl.SalesBlController;
import blService.salesBlService.SalesBlService;
import vo.ComboStrategyVO;
import vo.GiftBillVO;
import vo.GoodsVO;
import vo.PurchaseBillVO;
import vo.PurchaseReturnBillVO;
import vo.SalesBillVO;
import vo.SalesGoodsVO;
import vo.SalesReturnBillVO;
import vo.TotalStrategyVO;
import vo.UserInfoVO;
import vo.UserStrategyVO;

/**
 * @author ÍõÄþÒ»
 *
 */
public class SalesRemoteObject extends UnicastRemoteObject implements SalesBlService{

	/**
	 * 
	 */
	private static final long serialVersionUID = -1202075738500367834L;

	public SalesRemoteObject() throws RemoteException{
		super();
	}
	
	SalesBlService service=new SalesBlController();

	/**
	 * @see blService.salesBlService.SalesBlService#NewPurchase(vo.PurchaseBillVO)
	 * @param vo
	 * @return
	 * @throws RemoteException
	 */
	@Override
	public boolean NewPurchase(PurchaseBillVO vo) throws RemoteException {
		return service.NewPurchase(vo);
	}

	/**
	 * @see blService.salesBlService.SalesBlService#NewPurchaseDraft(vo.PurchaseBillVO)
	 * @param vo
	 * @return
	 * @throws RemoteException
	 */
	@Override
	public boolean NewPurchaseDraft(PurchaseBillVO vo) throws RemoteException {
		return service.NewPurchaseDraft(vo);
	}

	/**
	 * @see blService.salesBlService.SalesBlService#UpdatePurchaseDraft(vo.PurchaseBillVO)
	 * @param vo
	 * @return
	 * @throws RemoteException
	 */
	@Override
	public boolean UpdatePurchaseDraft(PurchaseBillVO vo) throws RemoteException {
		return service.UpdatePurchaseDraft(vo);
	}

	/**
	 * @see blService.salesBlService.SalesBlService#DeletePurchaseDraft(java.lang.String)
	 * @param Id
	 * @return
	 * @throws RemoteException
	 */
	@Override
	public boolean DeletePurchaseDraft(String Id) throws RemoteException {
		return service.DeletePurchaseDraft(Id);
	}

	/**
	 * @see blService.salesBlService.SalesBlService#SubmitPurchase(java.lang.String)
	 * @param Id
	 * @return
	 * @throws RemoteException
	 */
	@Override
	public boolean SubmitPurchase(String Id) throws RemoteException {
		return service.SubmitPurchase(Id);
	}

	/**
	 * @see blService.salesBlService.SalesBlService#NewPurReturn(vo.PurchaseReturnBillVO)
	 * @param vo
	 * @return
	 * @throws RemoteException
	 */
	@Override
	public boolean NewPurReturn(PurchaseReturnBillVO vo) throws RemoteException {
		return service.NewPurReturn(vo);
	}

	/**
	 * @see blService.salesBlService.SalesBlService#NewPurReturnDraft(vo.PurchaseReturnBillVO)
	 * @param vo
	 * @return
	 * @throws RemoteException
	 */
	@Override
	public boolean NewPurReturnDraft(PurchaseReturnBillVO vo) throws RemoteException {
		return service.NewPurReturnDraft(vo);
	}

	/**
	 * @see blService.salesBlService.SalesBlService#UpdatePurReturnDraft(vo.PurchaseReturnBillVO)
	 * @param vo
	 * @return
	 * @throws RemoteException
	 */
	@Override
	public boolean UpdatePurReturnDraft(PurchaseReturnBillVO vo) throws RemoteException {
		return service.UpdatePurReturnDraft(vo);
	}

	/**
	 * @see blService.salesBlService.SalesBlService#DeletePurReturnDraft(java.lang.String)
	 * @param Id
	 * @return
	 * @throws RemoteException
	 */
	@Override
	public boolean DeletePurReturnDraft(String Id) throws RemoteException {
		return service.DeletePurReturnDraft(Id);
	}

	/**
	 * @see blService.salesBlService.SalesBlService#SubmitPurReturn(java.lang.String)
	 * @param Id
	 * @return
	 * @throws RemoteException
	 */
	@Override
	public boolean SubmitPurReturn(String Id) throws RemoteException {
		return service.SubmitPurReturn(Id);
	}

	/**
	 * @see blService.salesBlService.SalesBlService#NewSales(vo.SalesBillVO)
	 * @param vo
	 * @return
	 * @throws RemoteException
	 */
	@Override
	public boolean NewSales(SalesBillVO vo) throws RemoteException {
		return service.NewSales(vo);
	}

	/**
	 * @see blService.salesBlService.SalesBlService#getPurchaseDraftBillsList(vo.UserInfoVO)
	 * @param UserInfo
	 * @return
	 * @throws RemoteException
	 */
	@Override
	public ArrayList<PurchaseBillVO> getPurchaseDraftBillsList(UserInfoVO UserInfo) throws RemoteException {
		return service.getPurchaseDraftBillsList(UserInfo);
	}

	/**
	 * @see blService.salesBlService.SalesBlService#getPurchaseTBDBillsList(vo.UserInfoVO)
	 * @param UserInfo
	 * @return
	 * @throws RemoteException
	 */
	@Override
	public ArrayList<PurchaseBillVO> getPurchaseTBDBillsList(UserInfoVO UserInfo) throws RemoteException {
		return service.getPurchaseTBDBillsList(UserInfo);
	}

	/**
	 * @see blService.salesBlService.SalesBlService#getPurchaseDeniedBillsListByCreater(vo.UserInfoVO)
	 * @param UserInfo
	 * @return
	 * @throws RemoteException
	 */
	@Override
	public ArrayList<PurchaseBillVO> getPurchaseDeniedBillsListByCreater(UserInfoVO UserInfo) throws RemoteException {
		return service.getPurchaseDeniedBillsListByCreater(UserInfo);
	}

	/**
	 * @see blService.salesBlService.SalesBlService#getPurchaseReturnDraftBillsList(vo.UserInfoVO)
	 * @param UserInfo
	 * @return
	 * @throws RemoteException
	 */
	@Override
	public ArrayList<PurchaseReturnBillVO> getPurchaseReturnDraftBillsList(UserInfoVO UserInfo) throws RemoteException {
		return service.getPurchaseReturnDraftBillsList(UserInfo);
	}

	/**
	 * @see blService.salesBlService.SalesBlService#getPurchaseReturnTBDBillsList(vo.UserInfoVO)
	 * @param UserInfo
	 * @return
	 * @throws RemoteException
	 */
	@Override
	public ArrayList<PurchaseReturnBillVO> getPurchaseReturnTBDBillsList(UserInfoVO UserInfo) throws RemoteException {
		return service.getPurchaseReturnTBDBillsList(UserInfo);
	}

	/**
	 * @see blService.salesBlService.SalesBlService#getPurchaseReturnDeniedBillsListByCreater(vo.UserInfoVO)
	 * @param UserInfo
	 * @return
	 * @throws RemoteException
	 */
	@Override
	public ArrayList<PurchaseReturnBillVO> getPurchaseReturnDeniedBillsListByCreater(UserInfoVO UserInfo)
			throws RemoteException {
		return service.getPurchaseReturnDeniedBillsListByCreater(UserInfo);
	}

	/**
	 * @see blService.salesBlService.SalesBlService#getSalesDraftBillsList(vo.UserInfoVO)
	 * @param UserInfo
	 * @return
	 * @throws RemoteException
	 */
	@Override
	public ArrayList<SalesBillVO> getSalesDraftBillsList(UserInfoVO UserInfo) throws RemoteException {
		return service.getSalesDraftBillsList(UserInfo);
	}

	/**
	 * @see blService.salesBlService.SalesBlService#getSalesTBDBillsList(vo.UserInfoVO)
	 * @param UserInfo
	 * @return
	 * @throws RemoteException
	 */
	@Override
	public ArrayList<SalesBillVO> getSalesTBDBillsList(UserInfoVO UserInfo) throws RemoteException {
		return service.getSalesTBDBillsList(UserInfo);
	}

	/**
	 * @see blService.salesBlService.SalesBlService#getSalesReturnDeniedBillsListByCreater(vo.UserInfoVO)
	 * @param UserInfo
	 * @return
	 * @throws RemoteException
	 */
	@Override
	public ArrayList<SalesReturnBillVO> getSalesReturnDeniedBillsListByCreater(UserInfoVO UserInfo)
			throws RemoteException {
		return service.getSalesReturnDeniedBillsListByCreater(UserInfo);
	}

	/**
	 * @see blService.salesBlService.SalesBlService#getSalesReturnDraftBillsList(vo.UserInfoVO)
	 * @param UserInfo
	 * @return
	 * @throws RemoteException
	 */
	@Override
	public ArrayList<SalesReturnBillVO> getSalesReturnDraftBillsList(UserInfoVO UserInfo) throws RemoteException {
		return service.getSalesReturnDraftBillsList(UserInfo);
	}

	/**
	 * @see blService.salesBlService.SalesBlService#getSalesReturnTBDBillsList(vo.UserInfoVO)
	 * @param UserInfo
	 * @return
	 * @throws RemoteException
	 */
	@Override
	public ArrayList<SalesReturnBillVO> getSalesReturnTBDBillsList(UserInfoVO UserInfo) throws RemoteException {
		return service.getSalesReturnTBDBillsList(UserInfo);
	}

	/**
	 * @see blService.salesBlService.SalesBlService#getSalesDeniedBillsListByCreater(vo.UserInfoVO)
	 * @param UserInfo
	 * @return
	 * @throws RemoteException
	 */
	@Override
	public ArrayList<SalesBillVO> getSalesDeniedBillsListByCreater(UserInfoVO UserInfo) throws RemoteException {
		return service.getSalesDeniedBillsListByCreater(UserInfo);
	}

	/**
	 * @see blService.salesBlService.SalesBlService#NewSalesDraft(vo.SalesBillVO)
	 * @param vo
	 * @return
	 * @throws RemoteException
	 */
	@Override
	public boolean NewSalesDraft(SalesBillVO vo) throws RemoteException {
		return service.NewSalesDraft(vo);
	}

	/**
	 * @see blService.salesBlService.SalesBlService#UpdateSalesDraft(vo.SalesBillVO)
	 * @param vo
	 * @return
	 * @throws RemoteException
	 */
	@Override
	public boolean UpdateSalesDraft(SalesBillVO vo) throws RemoteException {
		return service.UpdateSalesDraft(vo);
	}

	/**
	 * @see blService.salesBlService.SalesBlService#DeleteSalesDraft(java.lang.String)
	 * @param Id
	 * @return
	 * @throws RemoteException
	 */
	@Override
	public boolean DeleteSalesDraft(String Id) throws RemoteException {
		return service.DeleteSalesDraft(Id);
	}

	/**
	 * @see blService.salesBlService.SalesBlService#SubmitSales(java.lang.String)
	 * @param Id
	 * @return
	 * @throws RemoteException
	 */
	@Override
	public boolean SubmitSales(String Id) throws RemoteException {
		return service.SubmitSales(Id);
	}

	/**
	 * @see blService.salesBlService.SalesBlService#NewSalReturn(vo.SalesReturnBillVO)
	 * @param vo
	 * @return
	 * @throws RemoteException
	 */
	@Override
	public boolean NewSalReturn(SalesReturnBillVO vo) throws RemoteException {
		return service.NewSalReturn(vo);
	}

	/**
	 * @see blService.salesBlService.SalesBlService#NewSalReturnDraft(vo.SalesReturnBillVO)
	 * @param vo
	 * @return
	 * @throws RemoteException
	 */
	@Override
	public boolean NewSalReturnDraft(SalesReturnBillVO vo) throws RemoteException {
		return service.NewSalReturnDraft(vo);
	}

	/**
	 * @see blService.salesBlService.SalesBlService#UpdateSalReturnDraft(vo.SalesReturnBillVO)
	 * @param vo
	 * @return
	 * @throws RemoteException
	 */
	@Override
	public boolean UpdateSalReturnDraft(SalesReturnBillVO vo) throws RemoteException {
		return service.UpdateSalReturnDraft(vo);
	}

	/**
	 * @see blService.salesBlService.SalesBlService#DeleteSalReturnDraft(java.lang.String)
	 * @param Id
	 * @return
	 * @throws RemoteException
	 */
	@Override
	public boolean DeleteSalReturnDraft(String Id) throws RemoteException {
		return service.DeleteSalReturnDraft(Id);
	}

	/**
	 * @see blService.salesBlService.SalesBlService#SubmitSalReturn(java.lang.String)
	 * @param Id
	 * @return
	 * @throws RemoteException
	 */
	@Override
	public boolean SubmitSalReturn(String Id) throws RemoteException {
		return service.SubmitSalReturn(Id);
	}

	/**
	 * @see blService.salesBlService.SalesBlService#CommoditySelect()
	 * @return
	 * @throws RemoteException
	 */
	@Override
	public ArrayList<GoodsVO> CommoditySelect() throws RemoteException {
		return service.CommoditySelect();
	}

	/**
	 * @see blService.salesBlService.SalesBlService#getTotalStrategy(double)
	 * @param consumption
	 * @return
	 * @throws RemoteException
	 */
	@Override
	public TotalStrategyVO getTotalStrategy(double consumption) throws RemoteException {
		return service.getTotalStrategy(consumption);
	}

	/**
	 * @see blService.salesBlService.SalesBlService#getComboStrategy(java.util.ArrayList, double)
	 * @param salesManCommodityVOs
	 * @param consumption
	 * @return
	 * @throws RemoteException
	 */
	@Override
	public ComboStrategyVO getComboStrategy(ArrayList<SalesGoodsVO> salesManCommodityVOs, double consumption)
			throws RemoteException {
		return service.getComboStrategy(salesManCommodityVOs, consumption);
	}

	/**
	 * @see blService.salesBlService.SalesBlService#getUserStrategy(assistant.type.CustomerLevelEnum, double)
	 * @param customerLevel
	 * @param consumption
	 * @return
	 * @throws RemoteException
	 */
	@Override
	public UserStrategyVO getUserStrategy(CustomerLevelEnum customerLevel, double consumption) throws RemoteException {
		return service.getUserStrategy(customerLevel, consumption);
	}

	/**
	 * @see blService.salesBlService.SalesBlService#generateGiftBillByTotalStrategy(vo.TotalStrategyVO)
	 * @param vo
	 * @return
	 * @throws RemoteException
	 */
	@Override
	public GiftBillVO generateGiftBillByTotalStrategy(TotalStrategyVO vo) throws RemoteException {
		return service.generateGiftBillByTotalStrategy(vo);
	}

	/**
	 * @see blService.salesBlService.SalesBlService#generateGiftBillByUserStrategy(vo.UserStrategyVO)
	 * @param vo
	 * @return
	 * @throws RemoteException
	 */
	@Override
	public GiftBillVO generateGiftBillByUserStrategy(UserStrategyVO vo) throws RemoteException {
		return service.generateGiftBillByUserStrategy(vo);
	}
	
	
}
