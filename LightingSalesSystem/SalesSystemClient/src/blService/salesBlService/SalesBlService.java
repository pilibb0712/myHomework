package blService.salesBlService;

import java.rmi.Remote;

import java.rmi.RemoteException;
import java.util.ArrayList;

import assistant.type.CustomerLevelEnum;
import vo.PurchaseReturnBillVO;
import vo.ComboStrategyVO;
import vo.GiftBillVO;
import vo.GoodsVO;
import vo.PurchaseBillVO;
import vo.SalesReturnBillVO;
import vo.TotalStrategyVO;
import vo.UserInfoVO;
import vo.UserStrategyVO;
import vo.SalesBillVO;
import vo.SalesGoodsVO;


public interface SalesBlService extends Remote {

	/**
	 * 新建一张供应商进货单
	 * @param PurchaseBillVO
	 * @return 返回一个布尔值表示是否操作成功，如果成功则返回True
	 * @throws RemoteException
	 */
	public boolean NewPurchase(PurchaseBillVO vo) throws RemoteException;
	
	/**
	 * 新建一张供应商进货单草稿
	 * @param PurchaseBillVO
	 * @return boolean, true表示操作成功
	 */
	public boolean NewPurchaseDraft(PurchaseBillVO vo) throws RemoteException;
	
	/**
	 * 对已经存在草稿进行内容更新
	 * @param PurchaseBillVO
	 * @return boolean, true表示操作成功
	 */
	public boolean UpdatePurchaseDraft(PurchaseBillVO vo) throws RemoteException;
	
	/**
	 * 通过Id删除一张进货单草稿
	 * @param String
	 * @return boolean, true表示操作成功
	 */
	public boolean DeletePurchaseDraft(String Id) throws RemoteException;
	
	/**
	 * 将草稿提交为正式单据
	 * @param String
	 * @return boolean, true表示操作成功
	 */
	public boolean SubmitPurchase(String Id) throws RemoteException;
	
	/**
	 * 新建一张供应商进货退货单
	 * @param PurchaseReturnBillVO
	 * @return 返回一个布尔值表示是否操作成功，如果成功则返回True
	 * @throws RemoteException
	 */
	public boolean NewPurReturn(PurchaseReturnBillVO vo) throws RemoteException;
	
	/**
	 * 新建一张供应商进货退货单草稿
	 * @param PurchaseReturnBillVO
	 * @return boolean, true表示操作成功
	 */
	public boolean NewPurReturnDraft(PurchaseReturnBillVO vo) throws RemoteException;
	
	/**
	 * 更新一张供应商进货退货单草稿
	 * @param PurchaseReturnBillVO
	 * @return boolean, true表示操作成功
	 */
	public boolean UpdatePurReturnDraft(PurchaseReturnBillVO vo) throws RemoteException;
	
	/**
	 * 通过Id删除一张进货退货单草稿
	 * @param String
	 * @return boolean, true表示操作成功
	 */
	public boolean DeletePurReturnDraft(String Id) throws RemoteException;
	
	/**
	 * 将草稿提交为正式单据
	 * @param String
	 * @return boolean, true表示操作成功
	 */
	public boolean SubmitPurReturn(String Id) throws RemoteException;
	
	/**
	 * 新建一张销售商出货单
	 * @param SalesBillVO
	 * @return 返回一个布尔值表示是否操作成功，如果成功则返回True
	 * @throws RemoteException
	 */
	public boolean NewSales(SalesBillVO vo) throws RemoteException;
	
	/**
	 * 获得草稿
	 * @param UserInfoVO
	 * @return ArrayList<PurchaseBillPO>
	 * @throws RemoteException
	 */
	public ArrayList<PurchaseBillVO> getPurchaseDraftBillsList(UserInfoVO UserInfo) throws RemoteException;
	
	/**
	 * 获得待审批单据
	 * @param UserInfo
	 * @return ArrayList<PurchaseBillPO>
	 * @throws RemoteException
	 */
	public ArrayList<PurchaseBillVO> getPurchaseTBDBillsList(UserInfoVO UserInfo) throws RemoteException;
	
	/**
	 * 获得拒绝单据
	 * @param UserInfo
	 * @return ArrayList<PurchaseBillPO>
	 * @throws RemoteException
	 */
	public ArrayList<PurchaseBillVO> getPurchaseDeniedBillsListByCreater(UserInfoVO UserInfo) throws RemoteException;
	
	/**
	 * 获得草稿
	 * @param UserInfoVO
	 * @return ArrayList<PurchaseReturnBillPO>
	 * @throws RemoteException
	 */
	public ArrayList<PurchaseReturnBillVO> getPurchaseReturnDraftBillsList(UserInfoVO UserInfo) throws RemoteException;
	
	/**
	 * 获得待审批单据
	 * @param UserInfo
	 * @return ArrayList<PurchaseReturnBillPO>
	 * @throws RemoteException
	 */
	public ArrayList<PurchaseReturnBillVO> getPurchaseReturnTBDBillsList(UserInfoVO UserInfo) throws RemoteException;
	
	/**
	 * 获得拒绝单据
	 * @param UserInfo
	 * @return ArrayList<PurchaseReturnBillPO>
	 * @throws RemoteException
	 */
	public ArrayList<PurchaseReturnBillVO> getPurchaseReturnDeniedBillsListByCreater(UserInfoVO UserInfo) throws RemoteException;
	
	/**
	 * 获得草稿
	 * @param UserInfoVO
	 * @return ArrayList<SalesBillPO>
	 * @throws RemoteException
	 */
	public ArrayList<SalesBillVO> getSalesDraftBillsList(UserInfoVO UserInfo) throws RemoteException;
	
	/**
	 * 获得待审批单据
	 * @param UserInfo
	 * @return ArrayList<SalesBillPO>
	 * @throws RemoteException
	 */
	public ArrayList<SalesBillVO> getSalesTBDBillsList(UserInfoVO UserInfo) throws RemoteException;
	
	/**
	 * 获得拒绝单据
	 * @param UserInfo
	 * @return ArrayList<SalesReturnBillPO>
	 * @throws RemoteException
	 */
	public ArrayList<SalesReturnBillVO> getSalesReturnDeniedBillsListByCreater(UserInfoVO UserInfo) throws RemoteException;
	
	/**
	 * 获得草稿
	 * @param UserInfoVO
	 * @return ArrayList<SalesReturnBillPO>
	 * @throws RemoteException
	 */
	public ArrayList<SalesReturnBillVO> getSalesReturnDraftBillsList(UserInfoVO UserInfo) throws RemoteException;
	
	/**
	 * 获得待审批单据
	 * @param UserInfo
	 * @return ArrayList<SalesReturnBillPO>
	 * @throws RemoteException
	 */
	public ArrayList<SalesReturnBillVO> getSalesReturnTBDBillsList(UserInfoVO UserInfo) throws RemoteException;
	
	/**
	 * 获得拒绝单据
	 * @param UserInfo
	 * @return ArrayList<SalesBillPO>
	 * @throws RemoteException
	 */
	public ArrayList<SalesBillVO> getSalesDeniedBillsListByCreater(UserInfoVO UserInfo) throws RemoteException;
	
	/**
	 * 新建一张销售商出货单草稿
	 * @param SalesBillVO
	 * @return boolean, true表示操作成功
	 */
	public boolean NewSalesDraft(SalesBillVO vo) throws RemoteException;
	
	/**
	 * 更新一张销售商出货单草稿
	 * @param SalesBillVO
	 * @return boolean, true表示操作成功
	 */
	public boolean UpdateSalesDraft(SalesBillVO vo) throws RemoteException;
	
	/**
	 * 通过Id删除一张出货单草稿
	 * @param String
	 * @return boolean, true表示操作成功
	 */
	public boolean DeleteSalesDraft(String Id) throws RemoteException;
	
	/**
	 * 将草稿提交为正式单据
	 * @param String
	 * @return boolean, true表示操作成功
	 */
	public boolean SubmitSales(String Id) throws RemoteException;
	
	/**
	 * 新建一张销售商出货退货单
	 * @param SalesReturnBillVO
	 * @return 返回一个布尔值表示是否操作成功，如果成功则返回True
	 * @throws RemoteException
	 */
	public boolean NewSalReturn(SalesReturnBillVO vo) throws RemoteException;
	
	/**
	 * 新建一张销售商出货退货单草稿
	 * @param SalesReturnBillVO
	 * @return boolean, true表示操作成功
	 */
	public boolean NewSalReturnDraft(SalesReturnBillVO vo) throws RemoteException;
	
	/**
	 * 更新一张销售商出货退货单草稿
	 * @param SalesReturnBillVO
	 * @return boolean, true表示操作成功
	 */
	public boolean UpdateSalReturnDraft(SalesReturnBillVO vo) throws RemoteException;
	
	/**
	 * 通过Id删除一张出货退货单草稿
	 * @param String
	 * @return boolean, true表示操作成功
	 */
	public boolean DeleteSalReturnDraft(String Id) throws RemoteException;
	
	/**
	 * 将草稿提交为正式单据
	 * @param String
	 * @return boolean, true表示操作成功
	 */
	public boolean SubmitSalReturn(String Id) throws RemoteException;
	
	/**
	 * 制定单据时提供商品列表
	 * @return ArrayList<GoodsVO>，ArrayList中每一项为一种商品
	 * @throws RemoteException
	 */
	public ArrayList<GoodsVO> CommoditySelect() throws RemoteException;
	

	/*
	 * 以下为促销策略部分
	 */
	
	/**
	 * 根据消费额得到促销策略
	 * @param consumption
	 * @return TotalStrategyVO
	 * @throws RemoteException
	 */
	public TotalStrategyVO getTotalStrategy(double consumption) throws RemoteException;
	
	/**
	 * 根据商品列表、消费额得到促销策略
	 * @param salesManCommodityVOs
	 * @param consumption
	 * @return ComboStrategyVO
	 * @throws RemoteException
	 */
	public ComboStrategyVO getComboStrategy(ArrayList<SalesGoodsVO> salesManCommodityVOs,double consumption) throws RemoteException;
	
	/**
	 * 根据客户级别、消费额得到促销策略
	 * @param customerLevel
	 * @param consumption
	 * @return UserStrategyVO
	 * @throws RemoteException
	 */
	public UserStrategyVO getUserStrategy(CustomerLevelEnum customerLevel,double consumption) throws RemoteException;
	
	/**
	 * 根据TotalStrategy生成赠送单
	 * @param vo
	 * @return GiftBillVO
	 * @throws RemoteException
	 */
	public GiftBillVO generateGiftBillByTotalStrategy(TotalStrategyVO vo) throws RemoteException;
	
	/**
	 * 根据UserStrategy生成赠送单
	 * @param vo
	 * @return GiftBillVO
	 * @throws RemoteException
	 */
	public GiftBillVO generateGiftBillByUserStrategy(UserStrategyVO vo) throws RemoteException;
}
