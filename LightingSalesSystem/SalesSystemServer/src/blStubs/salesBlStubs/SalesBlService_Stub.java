package blStubs.salesBlStubs;

import java.rmi.RemoteException;
import java.util.ArrayList;

import blService.salesBlService.SalesBlService;
import vo.*;


public class SalesBlService_Stub implements SalesBlService {

	PurchaseBillVO purchaseBill;
	PurchaseReturnBillVO purchaseReturnBill;
	SalesBillVO salesBill;
	SalesReturnBillVO SalesReturnBill;
	
	public SalesBlService_Stub(PurchaseBillVO Bill) {
		
		purchaseBill=Bill;
	}
	public SalesBlService_Stub(PurchaseReturnBillVO Bill) {
		
		purchaseReturnBill=Bill;
	}
	public SalesBlService_Stub(SalesBillVO Bill) {
		
		salesBill=Bill;
	}
	public SalesBlService_Stub(SalesReturnBillVO Bill) {

		SalesReturnBill=Bill;
	}
	
	@Override
	public boolean NewPurchase(PurchaseBillVO vo){
		// TODO Auto-generated method stub
		if(vo.getId().equals("JHD-20170101-00001")) {
			return true;
		}
		return false;
	}

	@Override
	public boolean NewPurReturn(PurchaseReturnBillVO vo){
		// TODO Auto-generated method stub
		if(vo.getId().equals("JHTHD-20170101-00001")) {
			return true;
		}
		return false;
	}

	@Override
	public boolean NewSales(SalesBillVO vo) {
		// TODO Auto-generated method stub
		if(vo.getId().equals("XSD-20170101-00001")) {
			return true;
		}
		return false;
	}

	@Override
	public boolean NewSalReturn(SalesReturnBillVO vo) {
		// TODO Auto-generated method stub
		if(vo.getId().equals("XSTHD-20170101-00001")) {
			return true;
		}
		return false;
	}

	@Override
	public ArrayList<GoodsVO> CommoditySelect(){
		ArrayList<GoodsVO> goods=new ArrayList<>();
		for(int i=0;i<5;i++) {
			GoodsVO vo=new GoodsVO();
			vo.setName("Commodity"+i);
			vo.setNumber("0000"+i);
			vo.setType("TypeA");
			vo.setCostPrice(""+100*i);
			vo.setSellingPrice(""+200*i);
			goods.add(vo);
		}
		return goods;
	}
	/**
	 * @see blService.salesBlService.SalesBlService#NewPurchaseDraft(vo.PurchaseBillVO)
	 * @param vo
	 * @return
	 * @throws RemoteException
	 */
	@Override
	public boolean NewPurchaseDraft(PurchaseBillVO vo) throws RemoteException {
		// TODO Auto-generated method stub
		return false;
	}
	/**
	 * @see blService.salesBlService.SalesBlService#UpdatePurchaseDraft(vo.PurchaseBillVO)
	 * @param vo
	 * @return
	 * @throws RemoteException
	 */
	@Override
	public boolean UpdatePurchaseDraft(PurchaseBillVO vo) throws RemoteException {
		// TODO Auto-generated method stub
		return false;
	}
	/**
	 * @see blService.salesBlService.SalesBlService#DeletePurchaseDraft(java.lang.String)
	 * @param Id
	 * @return
	 * @throws RemoteException
	 */
	@Override
	public boolean DeletePurchaseDraft(String Id) throws RemoteException {
		// TODO Auto-generated method stub
		return false;
	}
	/**
	 * @see blService.salesBlService.SalesBlService#SubmitPurchase(java.lang.String)
	 * @param Id
	 * @return
	 * @throws RemoteException
	 */
	@Override
	public boolean SubmitPurchase(String Id) throws RemoteException {
		// TODO Auto-generated method stub
		return false;
	}
	/**
	 * @see blService.salesBlService.SalesBlService#NewPurReturnDraft(vo.PurchaseReturnBillVO)
	 * @param vo
	 * @return
	 * @throws RemoteException
	 */
	@Override
	public boolean NewPurReturnDraft(PurchaseReturnBillVO vo) throws RemoteException {
		// TODO Auto-generated method stub
		return false;
	}
	/**
	 * @see blService.salesBlService.SalesBlService#UpdatePurReturnDraft(vo.PurchaseReturnBillVO)
	 * @param vo
	 * @return
	 * @throws RemoteException
	 */
	@Override
	public boolean UpdatePurReturnDraft(PurchaseReturnBillVO vo) throws RemoteException {
		// TODO Auto-generated method stub
		return false;
	}
	/**
	 * @see blService.salesBlService.SalesBlService#DeletePurReturnDraft(java.lang.String)
	 * @param Id
	 * @return
	 * @throws RemoteException
	 */
	@Override
	public boolean DeletePurReturnDraft(String Id) throws RemoteException {
		// TODO Auto-generated method stub
		return false;
	}
	/**
	 * @see blService.salesBlService.SalesBlService#SubmitPurReturn(java.lang.String)
	 * @param Id
	 * @return
	 * @throws RemoteException
	 */
	@Override
	public boolean SubmitPurReturn(String Id) throws RemoteException {
		// TODO Auto-generated method stub
		return false;
	}
	/**
	 * @see blService.salesBlService.SalesBlService#getPurchaseDraftBillsList(vo.UserInfoVO)
	 * @param UserInfo
	 * @return
	 * @throws RemoteException
	 */
	@Override
	public ArrayList<PurchaseBillVO> getPurchaseDraftBillsList(UserInfoVO UserInfo) throws RemoteException {
		// TODO Auto-generated method stub
		return null;
	}
	/**
	 * @see blService.salesBlService.SalesBlService#getPurchaseTBDBillsList(vo.UserInfoVO)
	 * @param UserInfo
	 * @return
	 * @throws RemoteException
	 */
	@Override
	public ArrayList<PurchaseBillVO> getPurchaseTBDBillsList(UserInfoVO UserInfo) throws RemoteException {
		// TODO Auto-generated method stub
		return null;
	}
	/**
	 * @see blService.salesBlService.SalesBlService#getPurchaseDeniedBillsListByCreater(vo.UserInfoVO)
	 * @param UserInfo
	 * @return
	 * @throws RemoteException
	 */
	@Override
	public ArrayList<PurchaseBillVO> getPurchaseDeniedBillsListByCreater(UserInfoVO UserInfo) throws RemoteException {
		// TODO Auto-generated method stub
		return null;
	}
	/**
	 * @see blService.salesBlService.SalesBlService#getPurchaseReturnDraftBillsList(vo.UserInfoVO)
	 * @param UserInfo
	 * @return
	 * @throws RemoteException
	 */
	@Override
	public ArrayList<PurchaseReturnBillVO> getPurchaseReturnDraftBillsList(UserInfoVO UserInfo) throws RemoteException {
		// TODO Auto-generated method stub
		return null;
	}
	/**
	 * @see blService.salesBlService.SalesBlService#getPurchaseReturnTBDBillsList(vo.UserInfoVO)
	 * @param UserInfo
	 * @return
	 * @throws RemoteException
	 */
	@Override
	public ArrayList<PurchaseReturnBillVO> getPurchaseReturnTBDBillsList(UserInfoVO UserInfo) throws RemoteException {
		// TODO Auto-generated method stub
		return null;
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
		// TODO Auto-generated method stub
		return null;
	}
	/**
	 * @see blService.salesBlService.SalesBlService#getSalesDraftBillsList(vo.UserInfoVO)
	 * @param UserInfo
	 * @return
	 * @throws RemoteException
	 */
	@Override
	public ArrayList<SalesBillVO> getSalesDraftBillsList(UserInfoVO UserInfo) throws RemoteException {
		// TODO Auto-generated method stub
		return null;
	}
	/**
	 * @see blService.salesBlService.SalesBlService#getSalesTBDBillsList(vo.UserInfoVO)
	 * @param UserInfo
	 * @return
	 * @throws RemoteException
	 */
	@Override
	public ArrayList<SalesBillVO> getSalesTBDBillsList(UserInfoVO UserInfo) throws RemoteException {
		// TODO Auto-generated method stub
		return null;
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
		// TODO Auto-generated method stub
		return null;
	}
	/**
	 * @see blService.salesBlService.SalesBlService#getSalesReturnDraftBillsList(vo.UserInfoVO)
	 * @param UserInfo
	 * @return
	 * @throws RemoteException
	 */
	@Override
	public ArrayList<SalesReturnBillVO> getSalesReturnDraftBillsList(UserInfoVO UserInfo) throws RemoteException {
		// TODO Auto-generated method stub
		return null;
	}
	/**
	 * @see blService.salesBlService.SalesBlService#getSalesReturnTBDBillsList(vo.UserInfoVO)
	 * @param UserInfo
	 * @return
	 * @throws RemoteException
	 */
	@Override
	public ArrayList<SalesReturnBillVO> getSalesReturnTBDBillsList(UserInfoVO UserInfo) throws RemoteException {
		// TODO Auto-generated method stub
		return null;
	}
	/**
	 * @see blService.salesBlService.SalesBlService#getSalesDeniedBillsListByCreater(vo.UserInfoVO)
	 * @param UserInfo
	 * @return
	 * @throws RemoteException
	 */
	@Override
	public ArrayList<SalesBillVO> getSalesDeniedBillsListByCreater(UserInfoVO UserInfo) throws RemoteException {
		// TODO Auto-generated method stub
		return null;
	}
	/**
	 * @see blService.salesBlService.SalesBlService#NewSalesDraft(vo.SalesBillVO)
	 * @param vo
	 * @return
	 * @throws RemoteException
	 */
	@Override
	public boolean NewSalesDraft(SalesBillVO vo) throws RemoteException {
		// TODO Auto-generated method stub
		return false;
	}
	/**
	 * @see blService.salesBlService.SalesBlService#UpdateSalesDraft(vo.SalesBillVO)
	 * @param vo
	 * @return
	 * @throws RemoteException
	 */
	@Override
	public boolean UpdateSalesDraft(SalesBillVO vo) throws RemoteException {
		// TODO Auto-generated method stub
		return false;
	}
	/**
	 * @see blService.salesBlService.SalesBlService#DeleteSalesDraft(java.lang.String)
	 * @param Id
	 * @return
	 * @throws RemoteException
	 */
	@Override
	public boolean DeleteSalesDraft(String Id) throws RemoteException {
		// TODO Auto-generated method stub
		return false;
	}
	/**
	 * @see blService.salesBlService.SalesBlService#SubmitSales(java.lang.String)
	 * @param Id
	 * @return
	 * @throws RemoteException
	 */
	@Override
	public boolean SubmitSales(String Id) throws RemoteException {
		// TODO Auto-generated method stub
		return false;
	}
	/**
	 * @see blService.salesBlService.SalesBlService#NewSalReturnDraft(vo.SalesReturnBillVO)
	 * @param vo
	 * @return
	 * @throws RemoteException
	 */
	@Override
	public boolean NewSalReturnDraft(SalesReturnBillVO vo) throws RemoteException {
		// TODO Auto-generated method stub
		return false;
	}
	/**
	 * @see blService.salesBlService.SalesBlService#UpdateSalReturnDraft(vo.SalesReturnBillVO)
	 * @param vo
	 * @return
	 * @throws RemoteException
	 */
	@Override
	public boolean UpdateSalReturnDraft(SalesReturnBillVO vo) throws RemoteException {
		// TODO Auto-generated method stub
		return false;
	}
	/**
	 * @see blService.salesBlService.SalesBlService#DeleteSalReturnDraft(java.lang.String)
	 * @param Id
	 * @return
	 * @throws RemoteException
	 */
	@Override
	public boolean DeleteSalReturnDraft(String Id) throws RemoteException {
		// TODO Auto-generated method stub
		return false;
	}
	/**
	 * @see blService.salesBlService.SalesBlService#SubmitSalReturn(java.lang.String)
	 * @param Id
	 * @return
	 * @throws RemoteException
	 */
	@Override
	public boolean SubmitSalReturn(String Id) throws RemoteException {
		// TODO Auto-generated method stub
		return false;
	}

}
