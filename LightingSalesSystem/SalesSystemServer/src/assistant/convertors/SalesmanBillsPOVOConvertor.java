/**
 * 
 */
package assistant.convertors;

import java.util.ArrayList;
import po.BillPO;
import po.PurchaseBillPO;
import po.PurchaseReturnBillPO;
import po.SalesBillPO;
import po.SalesGoodsPO;
import po.SalesReturnBillPO;
import vo.BillVO;
import vo.PurchaseBillVO;
import vo.PurchaseReturnBillVO;
import vo.SalesBillVO;
import vo.SalesGoodsVO;
import vo.SalesReturnBillVO;

/**
 * gxy改了
 * @author 王宁一 161250140
 * @version 2017.12.12
 * 进货销售人员单据po vo转换
 *
 */
public class SalesmanBillsPOVOConvertor {
	
	/**
	 * 单据共有属性bill PO转VO
	 * @param BillVO, BillPO
	 * @return
	 */
	private static void setVO(BillVO billVO, BillPO billPO) {
		billVO.setCategoryEnum(billPO.getCategoryEnum());
		billVO.setApproveDate(billPO.getApproveDate());
		billVO.setApprover(UserInfoPOVOConvertor.poToVO(billPO.getApprover()));
		billVO.setApproverComment(billPO.getApproverComment());
		billVO.setBillStateEnum(billPO.getBillStateEnum());
		billVO.setCreateDate(billPO.getCreateDate());
		billVO.setCreater(UserInfoPOVOConvertor.poToVO(billPO.getCreater()));
		billVO.setCreaterComment(billPO.getCreaterComment());
		billVO.setExecutor(UserInfoPOVOConvertor.poToVO(billPO.getExecutor()));
		billVO.setFinishDate(billPO.getFinishDate());		
		billVO.setId(billPO.getId());
	}
	
	/**
	 * 单据共有属性bill VO转PO
	 * @param BillPO, BillVO
	 * @return
	 */
	private static void setPO(BillPO billPO, BillVO billVO) {
		billPO.setCategoryEnum(billVO.getCategoryEnum());
		billPO.setApproveDate(billVO.getApproveDate());
		billPO.setApprover(UserInfoPOVOConvertor.voToPO(billVO.getApprover()));
		billPO.setApproverComment(billVO.getApproverComment());
		billPO.setBillStateEnum(billVO.getBillStateEnum());
		billPO.setCreateDate(billVO.getCreateDate());
		billPO.setCreater(UserInfoPOVOConvertor.voToPO(billVO.getCreater()));
		billPO.setCreaterComment(billVO.getCreaterComment());
		billPO.setExecutor(UserInfoPOVOConvertor.voToPO(billVO.getExecutor()));
		billPO.setFinishDate(billVO.getFinishDate());		
		billPO.setId(billVO.getId());
	}
	
	private static SalesGoodsPO salesGoodsVOtoPO(SalesGoodsVO vo){
		if(vo==null){return null;}
		SalesGoodsPO goods=new SalesGoodsPO();
		
		goods.setGoodsName(vo.getGoodsName());
		goods.setGoodsId(vo.getGoodsId());
		goods.setGoodsType(vo.getGoodsType());
		goods.setNumber(vo.getNumber());
		goods.setUnitPrice(vo.getUnitPrice());
		goods.setSum(vo.getSum());
		goods.setComment(vo.getComment());
		
		return goods;
	}
	
	private static SalesGoodsVO salesGoodsPOtoVO(SalesGoodsPO po){
		if(po==null){return null;}
		SalesGoodsVO goods=new SalesGoodsVO();
		
		goods.setGoodsName(po.getGoodsName());
		goods.setGoodsId(po.getGoodsId());
		goods.setGoodsType(po.getGoodsType());
		goods.setNumber(po.getNumber());
		goods.setUnitPrice(po.getUnitPrice());
		goods.setSum(po.getSum());
		goods.setComment(po.getComment());
		
		return goods;
	}

	/**
	 * 进货单PO转VO
	 * @param PurchaseBillPO
	 * @return PurchaseBillVO
	 */
	public static PurchaseBillVO purchaseBillPOtoVO(PurchaseBillPO billPO) {
		if(billPO==null){return null;}
		PurchaseBillVO billVO=new PurchaseBillVO();
		setVO(billVO, billPO);
		
		billVO.setSupplier(CustomerPOVOConvertor.customerPOtoVO(billPO.getSupplier()));
		billVO.setWare(billPO.getWare());
		ArrayList<SalesGoodsVO> goodsVO=new ArrayList<SalesGoodsVO>();
		ArrayList<SalesGoodsPO> goodsPO=billPO.getGoodsList();
		for(int i=0;i<goodsPO.size();i++){
			SalesGoodsVO eachVO=salesGoodsPOtoVO(goodsPO.get(i));
			goodsVO.add(eachVO);
		}
		billVO.setGoodsList(goodsVO);
		billVO.setMoney(billPO.getMoney());
		
		return billVO;
	}
	
	/**
	 * 进货单VO转PO
	 * @param PurchaseBillVO
	 * @return PurchaseBillPO
	 */
	public static PurchaseBillPO purchaseBillVOtoPO(PurchaseBillVO billVO) {
		if(billVO==null){return null;}
		PurchaseBillPO billPO=new PurchaseBillPO();
		setPO(billPO, billVO);
		
		billPO.setSupplier(CustomerPOVOConvertor.customerVOtoPO(billVO.getSupplier()));
		billPO.setWare(billVO.getWare());
		ArrayList<SalesGoodsPO> goodsPO=new ArrayList<SalesGoodsPO>();
		ArrayList<SalesGoodsVO> goodsVO=billVO.getGoodsList();
		for(int i=0;i<goodsVO.size();i++){
			SalesGoodsPO eachPO=salesGoodsVOtoPO(goodsVO.get(i));
			goodsPO.add(eachPO);
		}
		billPO.setGoodsList(goodsPO);
		billPO.setMoney(billVO.getMoney());
		
		return billPO;
	}
	
	/**
	 * 进货退货单PO转VO
	 * @param PurchaseReturnBillPO
	 * @return PurchaseReturnBillVO
	 */
	public static PurchaseReturnBillVO purchaseReturnBillPOtoVO(PurchaseReturnBillPO billPO) {
		if(billPO==null){return null;}
		PurchaseReturnBillVO billVO=new PurchaseReturnBillVO();
		setVO(billVO, billPO);
		
		billVO.setSupplier(CustomerPOVOConvertor.customerPOtoVO(billPO.getSupplier()));
		billVO.setWare(billPO.getWare());
		ArrayList<SalesGoodsVO> goodsVO=new ArrayList<SalesGoodsVO>();
		ArrayList<SalesGoodsPO> goodsPO=billPO.getGoodsList();
		for(int i=0;i<goodsPO.size();i++){
			SalesGoodsVO eachVO=salesGoodsPOtoVO(goodsPO.get(i));
			goodsVO.add(eachVO);
		}
		billVO.setGoodsList(goodsVO);
		billVO.setMoney(billPO.getMoney());
		
		return billVO;
	}
	
	/**
	 * 进货退货单VO转PO
	 * @param PurchaseReturnBillVO
	 * @return PurchaseReturnBillPO
	 */
	public static PurchaseReturnBillPO purchaseReturnBillVOtoPO(PurchaseReturnBillVO billVO) {
		if(billVO==null){return null;}
		PurchaseReturnBillPO billPO=new PurchaseReturnBillPO();
		setPO(billPO, billVO);
		
		billPO.setSupplier(CustomerPOVOConvertor.customerVOtoPO(billVO.getSupplier()));
		billPO.setWare(billVO.getWare());
		ArrayList<SalesGoodsPO> goodsPO=new ArrayList<SalesGoodsPO>();
		ArrayList<SalesGoodsVO> goodsVO=billVO.getGoodsList();
		for(int i=0;i<goodsVO.size();i++){
			SalesGoodsPO eachPO=salesGoodsVOtoPO(goodsVO.get(i));
			goodsPO.add(eachPO);
		}
		billPO.setGoodsList(goodsPO);
		billPO.setMoney(billVO.getMoney());
		
		return billPO;
	}
	
	/**
	 * 出货单PO转VO
	 * @param SalesBillPO
	 * @return SalesBillVO
	 */
	public static SalesBillVO salesBillPOtoVO(SalesBillPO billPO) {
		if(billPO==null){return null;}
		SalesBillVO billVO=new SalesBillVO();
		setVO(billVO, billPO);
		
		billVO.setBusinessman(UserInfoPOVOConvertor.poToVO(billPO.getBusinessman()));
		billVO.setCustomer(CustomerPOVOConvertor.customerPOtoVO(billPO.getCustomer()));
		billVO.setWare(billPO.getWare());
		ArrayList<SalesGoodsVO> goodsVO=new ArrayList<SalesGoodsVO>();
		ArrayList<SalesGoodsPO> goodsPO=billPO.getGoodsList();
		for(int i=0;i<goodsPO.size();i++){
			SalesGoodsVO eachVO=salesGoodsPOtoVO(goodsPO.get(i));
			goodsVO.add(eachVO);
		}
		billVO.setGoodsList(goodsVO);
		billVO.setCoupon(billPO.getCoupon());
		billVO.setDiscount(billPO.getDiscount());
		billVO.setMoney(billPO.getMoney());
		billVO.setOriMoney(billPO.getOriMoney());
		
		return billVO;
	}
	
	/**
	 * 出货单VO转PO
	 * @param SalesBillVO
	 * @return SalesBillPO
	 */
	public static SalesBillPO salesBillVOtoPO(SalesBillVO billVO) {
		if(billVO==null){return null;}
		SalesBillPO billPO=new SalesBillPO();
		setPO(billPO, billVO);
		
		billPO.setBusinessman(UserInfoPOVOConvertor.voToPO(billVO.getBusinessman()));
		billPO.setCustomer(CustomerPOVOConvertor.customerVOtoPO(billVO.getCustomer()));
		billPO.setWare(billVO.getWare());
		ArrayList<SalesGoodsPO> goodsPO=new ArrayList<SalesGoodsPO>();
		ArrayList<SalesGoodsVO> goodsVO=billVO.getGoodsList();
		for(int i=0;i<goodsVO.size();i++){
			SalesGoodsPO eachPO=salesGoodsVOtoPO(goodsVO.get(i));
			goodsPO.add(eachPO);
		}
		billPO.setGoodsList(goodsPO);
		billPO.setCoupon(billVO.getCoupon());
		billPO.setDiscount(billVO.getDiscount());
		billPO.setMoney(billVO.getMoney());
		billPO.setOriMoney(billVO.getOriMoney());
		
		return billPO;
	}
	
	/**
	 * 出货退货单PO转VO
	 * @param SalesReturnBillPO
	 * @return SalesReturnBillVO
	 */
	public static SalesReturnBillVO salesReturnBillPOtoVO(SalesReturnBillPO billPO) {
		if(billPO==null){return null;}
		SalesReturnBillVO billVO=new SalesReturnBillVO();
		setVO(billVO, billPO);
		
		billVO.setCustomer(CustomerPOVOConvertor.customerPOtoVO(billPO.getCustomer()));
		billVO.setBusinessman(UserInfoPOVOConvertor.poToVO(billPO.getBusinessman()));
		billVO.setWare(billPO.getWare());
		ArrayList<SalesGoodsPO> goodsPO=billPO.getGoodsList();
		ArrayList<SalesGoodsVO> goodsVO=new ArrayList<SalesGoodsVO>();
		for(int i=0;i<goodsPO.size();i++){
			SalesGoodsVO eachVO=salesGoodsPOtoVO(goodsPO.get(i));
			goodsVO.add(eachVO);
		}
		billVO.setGoodsList(goodsVO);
		billVO.setMoney(billPO.getMoney());
		
		return billVO;
	}
	
	/**
	 * 出货退货单VO转PO
	 * @param SalesReturnBillVO
	 * @return SalesReturnBillPO
	 */
	public static SalesReturnBillPO salesReturnBillVOtoPO(SalesReturnBillVO billVO) {
		if(billVO==null){return null;}
		SalesReturnBillPO billPO=new SalesReturnBillPO();
		setPO(billPO, billVO);
		
		billPO.setCustomer(CustomerPOVOConvertor.customerVOtoPO(billVO.getCustomer()));
		billPO.setBusinessman(UserInfoPOVOConvertor.voToPO(billVO.getBusinessman()));
		billPO.setWare(billVO.getWare());
		ArrayList<SalesGoodsVO> goodsVO=billVO.getGoodsList();
		ArrayList<SalesGoodsPO> goodsPO=new ArrayList<SalesGoodsPO>();
		for(int i=0;i<goodsVO.size();i++){
			SalesGoodsPO eachPO=salesGoodsVOtoPO(goodsVO.get(i));
			goodsPO.add(eachPO);
		}
		billPO.setGoodsList(goodsPO);
		billPO.setMoney(billVO.getMoney());
		
		return billPO;
	}
}
