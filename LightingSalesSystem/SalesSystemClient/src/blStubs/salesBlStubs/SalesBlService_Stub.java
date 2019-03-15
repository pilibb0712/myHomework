package blStubs.salesBlStubs;

import java.rmi.RemoteException;
import java.util.ArrayList;

import blService.salesBlService.SalesBlService;
import vo.*;


public class SalesBlService_Stub implements SalesBlService {

	/**
	 * 新建一张供应商进货单
	 * @param PurchaseBillVO
	 * @return 返回一个布尔值表示是否操作成功，如果成功则返回True
	 */
	@Override
	public boolean NewPurchase(PurchaseBillVO vo) {
		return false;
		// TODO Auto-generated method stub
	}

	/**
	 * 新建一张供应商进货单草稿
	 * @param PurchaseBillVO
	 * @return boolean, true表示操作成功
	 */
	@Override
	public boolean NewPurchaseDraft(PurchaseBillVO vo) {
		return false;
	}
	
	/**
	 * 对已经存在草稿进行内容更新
	 * @param PurchaseBillVO
	 * @return boolean, true表示操作成功
	 */
	@Override
	public boolean UpdatePurchaseDraft(PurchaseBillVO vo) {
		return false;
	}
	
	/**
	 * 通过Id删除一张进货单草稿
	 * @param String
	 * @return boolean, true表示操作成功
	 */
	@Override
	public boolean DeletePurchaseDraft(String Id) {
		return true;
	}
	
	/**
	 * 将草稿提交为正式单据
	 * @param String
	 * @return boolean, true表示操作成功
	 */
	@Override
	public boolean SubmitPurchase(String Id) {
		return false;
	}
	
	/**
	 * 新建一张供应商进货退货单
	 * @param PurchaseReturnBillVO
	 * @return 返回一个布尔值表示是否操作成功，如果成功则返回True
	 */
	@Override
	public boolean NewPurReturn(PurchaseReturnBillVO vo) {
		return false;
	}
	
	/**
	 * 新建一张供应商进货退货单草稿
	 * @param PurchaseReturnBillVO
	 * @return boolean, true表示操作成功
	 */
	@Override
	public boolean NewPurReturnDraft(PurchaseReturnBillVO vo) {
		return false;
	}
	
	/**
	 * 更新一张供应商进货退货单草稿
	 * @param PurchaseReturnBillVO
	 * @return boolean, true表示操作成功
	 */
	@Override
	public boolean UpdatePurReturnDraft(PurchaseReturnBillVO vo) {
		return false;
	}

	/**
	 * 通过Id删除一张进货退货单草稿
	 * @param String
	 * @return boolean, true表示操作成功
	 */
	@Override
	public boolean DeletePurReturnDraft(String Id) {
		return true;
	}
	
	/**
	 * 将草稿提交为正式单据
	 * @param String
	 * @return boolean, true表示操作成功
	 */
	@Override
	public boolean SubmitPurReturn(String Id) {
		return false;
	}
	
	/**
	 * 新建一张销售商出货单
	 * @param SalesBillVO
	 * @return 返回一个布尔值表示是否操作成功，如果成功则返回True
	 */
	@Override
	public boolean NewSales(SalesBillVO vo) {
		return false;
	}

	/**
	 * 新建一张销售商出货单草稿
	 * @param SalesBillVO
	 * @return boolean, true表示操作成功
	 */
	@Override
	public boolean NewSalesDraft(SalesBillVO vo) {
		return false;
	}
	
	/**
	 * 更新一张销售商出货单草稿
	 * @param SalesBillVO
	 * @return boolean, true表示操作成功
	 */
	@Override
	public boolean UpdateSalesDraft(SalesBillVO vo) {
		return false;
	}
	
	/**
	 * 通过Id删除一张出货单草稿
	 * @param String
	 * @return boolean, true表示操作成功
	 */
	@Override
	public boolean DeleteSalesDraft(String Id) {
		return true;
	}
	
	/**
	 * 将草稿提交为正式单据
	 * @param String
	 * @return boolean, true表示操作成功
	 */
	@Override
	public boolean SubmitSales(String Id) {
		return false;
	}
	
	/**
	 * 新建一张销售商出货退货单
	 * @param SalesReturnBillVO
	 * @return 返回一个布尔值表示是否操作成功，如果成功则返回True
	 */
	@Override
	public boolean NewSalReturn(SalesReturnBillVO vo) {
		return false;
	}

	/**
	 * 新建一张销售商出货退货单草稿
	 * @param SalesReturnBillVO
	 * @return boolean, true表示操作成功
	 */
	@Override
	public boolean NewSalReturnDraft(SalesReturnBillVO vo) {
		return false;
	}
	
	/**
	 * 更新一张销售商出货退货单草稿
	 * @param SalesReturnBillVO
	 * @return boolean, true表示操作成功
	 */
	@Override
	public boolean UpdateSalReturnDraft(SalesReturnBillVO vo) {
		return false;
	}
	
	/**
	 * 通过Id删除一张出货退货单草稿
	 * @param String
	 * @return boolean, true表示操作成功
	 */
	@Override
	public boolean DeleteSalReturnDraft(String Id) {
		return true;
	}
	
	/**
	 * 将草稿提交为正式单据
	 * @param String
	 * @return boolean, true表示操作成功
	 */
	@Override
	public boolean SubmitSalReturn(String Id) {
		return false;
	}
	
	/**
	 * 制定单据时提供商品列表
	 * @return ArrayList<GoodsVO>，ArrayList中每一项为一种商品
	 */
	@Override
	public ArrayList<GoodsVO> CommoditySelect() {
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
	 * @see blService.salesBlService.SalesBlService#getPurchaseDraftBillsList(vo.UserInfoVO)
	 * @param UserInfo
	 * @return
	 * @throws RemoteException
	 */
	@Override
	public ArrayList<PurchaseBillVO> getPurchaseDraftBillsList(UserInfoVO UserInfo) throws RemoteException {
		// TODO Auto-generated method stub
		PurchaseBillVO vo=new PurchaseBillVO();
		ArrayList<PurchaseBillVO> test=new ArrayList<>();
		test.add(vo);
		return test;
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
		SalesBillVO draft=new SalesBillVO();
		ArrayList<SalesBillVO> drafts=new ArrayList<>();
		drafts.add(draft);
		return drafts;
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

}
