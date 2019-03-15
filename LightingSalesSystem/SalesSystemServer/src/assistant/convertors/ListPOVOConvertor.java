package assistant.convertors;

import java.util.ArrayList;
import assistant.type.BillCategoryEnum;
import assistant.utility.Date;
import po.BusinessConditionListPO;
import po.BusinessProcessListPO;
import po.CashExpenseBillPO;
import po.CustomerPO;
import po.GiftBillPO;
import po.GoodsPO;
import po.InventoryLossBillPO;
import po.InventoryOverBillPO;
import po.InventoryWarningBillPO;
import po.PaymentBillPO;
import po.PurchaseBillPO;
import po.PurchaseReturnBillPO;
import po.ReceiptBillPO;
import po.SalesBillPO;
import po.SalesDetailsListPO;
import po.SalesGoodsPO;
import po.SalesReturnBillPO;
import po.UserInfoPO;
import vo.BusinessConditionListVO;
import vo.BusinessProcessListBillVO;
import vo.BusinessProcessListVO;
import vo.CashExpenseBillVO;
import vo.GiftBillVO;
import vo.IncomeCostVO;
import vo.InventoryLossBillVO;
import vo.InventoryOverBillVO;
import vo.InventoryWarningBillVO;
import vo.PaymentBillVO;
import vo.PurchaseBillVO;
import vo.PurchaseReturnBillVO;
import vo.ReceiptBillVO;
import vo.SalesBillVO;
import vo.SalesDetailsListSalesBillVO;
import vo.SalesDetailsListVO;
import vo.SalesReturnBillVO;

/**
 * 三张表的POVOConvertor
 * @author guxinyu
 * @version 2017.12.23
 *
 */
public class ListPOVOConvertor {

	public static SalesDetailsListVO salesDetailsListPOtoVO(SalesDetailsListPO po){
		if(po==null)
			return null;
		
		ArrayList<SalesBillPO> billPOs=po.getSalesBills();
		
		ArrayList<SalesDetailsListSalesBillVO> billVOs=new ArrayList<SalesDetailsListSalesBillVO>();
		
		if(billPOs!=null){
			for(int i=0;i<billPOs.size();i++){
				SalesBillPO eachPO=billPOs.get(i);
			
				Date d=eachPO.getCreateDate();
				String date=d.getYMDDate();
				String billId=eachPO.getId();
				int ware=eachPO.getWare();
				String userId=eachPO.getBusinessman().getId();
				String customerId=eachPO.getCustomer().getId();
			
				ArrayList<SalesGoodsPO> goods=billPOs.get(i).getGoodsList();
			
				for(int j=0;j<goods.size();j++){
					SalesGoodsPO eachGoods=goods.get(j);
				
					SalesDetailsListSalesBillVO each=new SalesDetailsListSalesBillVO();
				
					String comId=eachGoods.getGoodsId();
					String comName=eachGoods.getGoodsName();
					String comType=eachGoods.getGoodsType();
					String num=String.valueOf(eachGoods.getNumber());
					String unitPrice=String.valueOf(eachGoods.getUnitPrice());
					String sum=String.valueOf(eachGoods.getSum());
				
					each.setCommodityId(comId);
					each.setBillId(billId);
					each.setDate(date);
					each.setWare(String.valueOf(ware));
					each.setOperatorId(userId);
					each.setCustomerId(customerId);
					each.setCommodityName(comName);
					each.setCommodityType(comType);
					each.setNumber(num);
					each.setUnitPrice(unitPrice);
					each.setSum(sum);
				
					billVOs.add(each);
				}
			}
		}
	
		SalesDetailsListVO list=new SalesDetailsListVO(billVOs);
		return list;
	}
	
	public static BusinessProcessListVO businessProcessListPOtoVO(BusinessProcessListPO po){
		if(po==null)
			return null;
		
		ArrayList<InventoryWarningBillPO> inventoryWarningBills=po.getInventoryWarningBills();
		ArrayList<InventoryOverBillPO> inventoryOverBills=po.getInventoryOverBills();
		ArrayList<InventoryLossBillPO> inventoryLossBills=po.getInventoryLossBills();
		ArrayList<PurchaseBillPO> purchaseBills=po.getPurchaseBills();
		ArrayList<PurchaseReturnBillPO> purchaseReturnBills=po.getPurchaseReturnBills();
		ArrayList<SalesBillPO> salesBills=po.getSalesBills();
		ArrayList<SalesReturnBillPO> salesReturnBills=po.getSalesReturnBills();
		ArrayList<ReceiptBillPO> receiptBills=po.getReceiptBills();
		ArrayList<PaymentBillPO> paymentBills=po.getPaymentBills();
		ArrayList<CashExpenseBillPO> cashExpenseBills=po.getCashExpenseBills();
		ArrayList<GiftBillPO> giftBills=po.getGiftBills();
		
		ArrayList<BusinessProcessListBillVO> bills=new ArrayList<BusinessProcessListBillVO>();
		ArrayList<InventoryWarningBillVO> inventoryWarningVO=new ArrayList<InventoryWarningBillVO>();
		ArrayList<InventoryOverBillVO> inventoryOverVO=new ArrayList<InventoryOverBillVO>();
		ArrayList<InventoryLossBillVO> inventoryLossVO=new ArrayList<InventoryLossBillVO>();
		ArrayList<PurchaseBillVO> purchaseVO=new ArrayList<PurchaseBillVO>();
		ArrayList<PurchaseReturnBillVO> purchaseReturnVO=new ArrayList<PurchaseReturnBillVO>();
		ArrayList<SalesBillVO> salesVO=new ArrayList<SalesBillVO>();
		ArrayList<SalesReturnBillVO> salesReturnVO=new ArrayList<SalesReturnBillVO>();
		ArrayList<ReceiptBillVO> receiptVO=new ArrayList<ReceiptBillVO>();
		ArrayList<PaymentBillVO> paymentVO=new ArrayList<PaymentBillVO>();
		ArrayList<CashExpenseBillVO> cashExpenseVO=new ArrayList<CashExpenseBillVO>();
		ArrayList<GiftBillVO> giftVO=new ArrayList<GiftBillVO>();
		
		if(inventoryWarningBills!=null){
			for(int i=0;i<inventoryWarningBills.size();i++){
				InventoryWarningBillPO eachPO=inventoryWarningBills.get(i);
			
				InventoryWarningBillVO vo=CommodityPOVOConvertor.inventoryWarningBillPOtoVO(eachPO);
				inventoryWarningVO.add(vo);
			
				Date d=eachPO.getCreateDate();
				String date=d.getYMDDate();
				BillCategoryEnum name=eachPO.getCategoryEnum();
				String billName=name.toChineseString();
				String billId=eachPO.getId();
				UserInfoPO c=eachPO.getCreater();
				String creator=c.getName()+" "+c.getId();
				String customerId="NONEXIST";
				String userId="NONEXIST";
				String ware="NONEXIST";
			
				BusinessProcessListBillVO eachVO=new BusinessProcessListBillVO(
						date, billName, billId, creator,customerId, userId, ware
						);
			
				bills.add(eachVO);
			}
		}
		
		if(inventoryOverBills!=null){
		for(int i=0;i<inventoryOverBills.size();i++){
			InventoryOverBillPO eachPO=inventoryOverBills.get(i);
			
			InventoryOverBillVO vo=CommodityPOVOConvertor.inventoryOverBillPOtoVO(eachPO);
			inventoryOverVO.add(vo);
			
			Date d=eachPO.getCreateDate();
			String date=d.getYMDDate();
			BillCategoryEnum name=eachPO.getCategoryEnum();
			String billName=name.toChineseString();
			String billId=eachPO.getId();
			UserInfoPO c=eachPO.getCreater();
			String creator=c.getName()+" "+c.getId();
			String customerId="NONEXIST";
			String userId="NONEXIST";
			String ware="NONEXIST";
			
			BusinessProcessListBillVO eachVO=new BusinessProcessListBillVO(
					date, billName, billId, creator,customerId, userId, ware
					);
			
			bills.add(eachVO);
		}
		}
		
		if(inventoryLossBills!=null){
		for(int i=0;i<inventoryLossBills.size();i++){
			InventoryLossBillPO eachPO=inventoryLossBills.get(i);
			
			InventoryLossBillVO vo=CommodityPOVOConvertor.inventoryLossBillPOtoVO(eachPO);
			inventoryLossVO.add(vo);
			
			Date d=eachPO.getCreateDate();
			String date=d.getYMDDate();
			BillCategoryEnum name=eachPO.getCategoryEnum();
			String billName=name.toChineseString();
			String billId=eachPO.getId();
			UserInfoPO c=eachPO.getCreater();
			String creator=c.getName()+" "+c.getId();
			String customerId="NONEXIST";
			String userId="NONEXIST";
			String ware="NONEXIST";
			
			BusinessProcessListBillVO eachVO=new BusinessProcessListBillVO(
					date, billName, billId, creator,customerId, userId, ware
					);
			
			bills.add(eachVO);
		}
		}
		
		if(purchaseBills!=null){
		for(int i=0;i<purchaseBills.size();i++){
			PurchaseBillPO eachPO=purchaseBills.get(i);
			
			PurchaseBillVO vo=SalesmanBillsPOVOConvertor.purchaseBillPOtoVO(eachPO);
			purchaseVO.add(vo);
			
			Date d=eachPO.getCreateDate();
			String date=d.getYMDDate();
			BillCategoryEnum name=eachPO.getCategoryEnum();
			String billName=name.toChineseString();
			String billId=eachPO.getId();
			UserInfoPO c=eachPO.getCreater();
			String creator=c.getName()+" "+c.getId();
			CustomerPO cusPO=eachPO.getSupplier();
			String customerId=cusPO.getId();
			String userId="NONEXIST";
			String ware=String.valueOf(eachPO.getWare());
			
			BusinessProcessListBillVO eachVO=new BusinessProcessListBillVO(
					date, billName, billId, creator,customerId, userId, ware
					);
			
			bills.add(eachVO);
		}
		}
		
		if(purchaseReturnBills!=null){
		for(int i=0;i<purchaseReturnBills.size();i++){
			PurchaseReturnBillPO eachPO=purchaseReturnBills.get(i);
			
			PurchaseReturnBillVO vo=SalesmanBillsPOVOConvertor.purchaseReturnBillPOtoVO(eachPO);
			purchaseReturnVO.add(vo);
			
			Date d=eachPO.getCreateDate();
			String date=d.getYMDDate();
			BillCategoryEnum name=eachPO.getCategoryEnum();
			String billName=name.toChineseString();
			String billId=eachPO.getId();
			UserInfoPO c=eachPO.getCreater();
			String creator=c.getName()+" "+c.getId();
			CustomerPO cusPO=eachPO.getSupplier();
			String customerId=cusPO.getId();
			String userId="NONEXIST";
			String ware=String.valueOf(eachPO.getWare());
			
			BusinessProcessListBillVO eachVO=new BusinessProcessListBillVO(
					date, billName, billId, creator,customerId, userId, ware
					);
			
			bills.add(eachVO);
		}
		}
		
		if(salesBills!=null){
		for(int i=0;i<salesBills.size();i++){
			SalesBillPO eachPO=salesBills.get(i);
			
			SalesBillVO vo=SalesmanBillsPOVOConvertor.salesBillPOtoVO(eachPO);
			salesVO.add(vo);
			
			Date d=eachPO.getCreateDate();
			String date=d.getYMDDate();
			BillCategoryEnum name=eachPO.getCategoryEnum();
			String billName=name.toChineseString();
			String billId=eachPO.getId();
			UserInfoPO c=eachPO.getCreater();
			String creator=c.getName()+" "+c.getId();
			CustomerPO cusPO=eachPO.getCustomer();
			String customerId=cusPO.getId();
			String userId="NONEXIST";
			String ware=String.valueOf(eachPO.getWare());
			
			BusinessProcessListBillVO eachVO=new BusinessProcessListBillVO(
					date, billName, billId, creator,customerId, userId, ware
					);
			
			bills.add(eachVO);
		}
		}
		
		if(salesReturnBills!=null){
		for(int i=0;i<salesReturnBills.size();i++){
			SalesReturnBillPO eachPO=salesReturnBills.get(i);
			
			SalesReturnBillVO vo=SalesmanBillsPOVOConvertor.salesReturnBillPOtoVO(eachPO);
			salesReturnVO.add(vo);
			
			Date d=eachPO.getCreateDate();
			String date=d.getYMDDate();
			BillCategoryEnum name=eachPO.getCategoryEnum();
			String billName=name.toChineseString();
			String billId=eachPO.getId();
			UserInfoPO c=eachPO.getCreater();
			String creator=c.getName()+" "+c.getId();
			CustomerPO cusPO=eachPO.getCustomer();
			String customerId=cusPO.getId();
			String userId="NONEXIST";
			String ware=String.valueOf(eachPO.getWare());
			
			BusinessProcessListBillVO eachVO=new BusinessProcessListBillVO(
					date, billName, billId, creator,customerId, userId, ware
					);
			
			bills.add(eachVO);
		}
		}
		
		if(receiptBills!=null){
		for(int i=0;i<receiptBills.size();i++){
			ReceiptBillPO eachPO=receiptBills.get(i);
			
			ReceiptBillVO vo=FinanceBillsPOVOConvertor.receiptBillPOtoVO(eachPO);
			receiptVO.add(vo);
			
			Date d=eachPO.getCreateDate();
			String date=d.getYMDDate();
			BillCategoryEnum name=eachPO.getCategoryEnum();
			String billName=name.toChineseString();
			String billId=eachPO.getId();
			UserInfoPO c=eachPO.getCreater();
			String creator=c.getName()+" "+c.getId();
			String customerId="NONEXIST";
			String userId="NONEXIST";
			String ware="NONEXIST";
			
			BusinessProcessListBillVO eachVO=new BusinessProcessListBillVO(
					date, billName, billId, creator,customerId, userId, ware
					);
			
			bills.add(eachVO);
		}
		}
		
		if(paymentBills!=null){
		for(int i=0;i<paymentBills.size();i++){
			PaymentBillPO eachPO=paymentBills.get(i);
			
			PaymentBillVO vo=FinanceBillsPOVOConvertor.paymentBillPOtoVO(eachPO);
			paymentVO.add(vo);
			
			Date d=eachPO.getCreateDate();
			String date=d.getYMDDate();
			BillCategoryEnum name=eachPO.getCategoryEnum();
			String billName=name.toChineseString();
			String billId=eachPO.getId();
			UserInfoPO c=eachPO.getCreater();
			String creator=c.getName()+" "+c.getId();
			String customerId="NONEXIST";
			String userId="NONEXIST";
			String ware="NONEXIST";
			
			BusinessProcessListBillVO eachVO=new BusinessProcessListBillVO(
					date, billName, billId, creator,customerId, userId, ware
					);
			
			bills.add(eachVO);
		}
		}
		
		if(cashExpenseBills!=null){
		for(int i=0;i<cashExpenseBills.size();i++){
			CashExpenseBillPO eachPO=cashExpenseBills.get(i);
			
			CashExpenseBillVO vo=FinanceBillsPOVOConvertor.cashExpenseBillPOtoVO(eachPO);
			cashExpenseVO.add(vo);
			
			Date d=eachPO.getCreateDate();
			String date=d.getYMDDate();
			BillCategoryEnum name=eachPO.getCategoryEnum();
			String billName=name.toChineseString();
			String billId=eachPO.getId();
			UserInfoPO c=eachPO.getCreater();
			String creator=c.getName()+" "+c.getId();
			String customerId="NONEXIST";
			String userId="NONEXIST";
			String ware="NONEXIST";
			
			BusinessProcessListBillVO eachVO=new BusinessProcessListBillVO(
					date, billName, billId, creator,customerId, userId, ware
					);
			
			bills.add(eachVO);
		}
		}
		
		if(giftBills!=null){
		for(int i=0;i<giftBills.size();i++){
			GiftBillPO eachPO=giftBills.get(i);
			
			GiftBillVO vo=null;
			giftVO.add(vo);
			
			Date d=eachPO.getCreateDate();
			String date=d.getYMDDate();
			BillCategoryEnum name=eachPO.getCategoryEnum();
			String billName=name.toChineseString();
			String billId=eachPO.getId();
			UserInfoPO c=eachPO.getCreater();
			String creator=c.getName()+" "+c.getId();
			String customerId="NONEXIST";
			String userId="NONEXIST";
			String ware="NONEXIST";
			
			BusinessProcessListBillVO eachVO=new BusinessProcessListBillVO(
					date, billName, billId, creator,customerId, userId, ware
					);
			
			bills.add(eachVO);
		}
		}
		
		BusinessProcessListVO vo=new BusinessProcessListVO(bills,
				inventoryWarningVO,inventoryOverVO,inventoryLossVO,
				purchaseVO,purchaseReturnVO,salesVO,salesReturnVO,
				receiptVO,paymentVO,cashExpenseVO,giftVO);
		
		for(int i=0;i<bills.size();i++){
			System.out.println(bills.get(i).getBillId());
		}
		
		return vo;
	}
	
	public static BusinessConditionListVO businessConditionListPOtoVO(BusinessConditionListPO po){
		if(po==null)
			return null;
		
		ArrayList<PurchaseBillPO> purchaseBills=po.getPurchaseBills();
		ArrayList<PurchaseReturnBillPO> purchaseReturnBills=po.getPurchaseReturnBills();
		ArrayList<SalesBillPO> salesBills=po.getSalesBills();
		ArrayList<SalesReturnBillPO> salesReturnBills=po.getSalesReturnBills();
		ArrayList<InventoryOverBillPO> inventoryOverBills=po.getInventoryOverBills();
		ArrayList<InventoryLossBillPO> inventoryLossBills=po.getInventoryLossBills();
		ArrayList<GiftBillPO> giftBills=po.getGiftBills();
		ArrayList<GoodsPO> goodsList=po.getGoodsList();
		
		if(purchaseBills==null)
			purchaseBills=new ArrayList<PurchaseBillPO>();
		if(purchaseReturnBills==null)
			purchaseReturnBills=new ArrayList<PurchaseReturnBillPO>();
		if(salesBills==null)
			salesBills=new ArrayList<SalesBillPO>();
		if(salesReturnBills==null)
			salesReturnBills=new ArrayList<SalesReturnBillPO>();
		if(inventoryOverBills==null)
			inventoryOverBills=new ArrayList<InventoryOverBillPO>();
		if(inventoryLossBills==null)
			inventoryLossBills=new ArrayList<InventoryLossBillPO>();
		if(giftBills==null)
			giftBills=new ArrayList<GiftBillPO>();
		
		int purSize=purchaseBills.size();
		int purReSize=purchaseReturnBills.size();
		int salesSize=salesBills.size();
		int salesReSize=salesReturnBills.size();
		int overSize=inventoryOverBills.size();
		int lossSize=inventoryLossBills.size();
		int giftSize=giftBills.size();
		int goodsSize=goodsList.size();
		
		int maxSize=Math.max(purSize, purReSize);
		maxSize=Math.max(maxSize, salesSize);
		maxSize=Math.max(maxSize, salesReSize);
		maxSize=Math.max(maxSize, overSize);
		maxSize=Math.max(maxSize, lossSize);
		maxSize=Math.max(maxSize, giftSize);
		maxSize=Math.max(maxSize, goodsSize);
		
		ArrayList<IncomeCostVO> ics=new ArrayList<IncomeCostVO>();
		
		double afterAllowance=0;
		double allowance=0;
		double totalCost=0;
		
		for(int i=0;i<maxSize;i++){
			IncomeCostVO ic=new IncomeCostVO();
			
			if(i>=salesSize){//销售收入、代金券差额
				ic.setSalesEarning("");
				ic.setCouponGapEarning("");
			}else{
				SalesBillPO bill=salesBills.get(i);
				double sum=bill.getMoney();
				afterAllowance+=sum;
				ic.setSalesEarning(String.valueOf(sum));
				
				double actual=bill.getActualMoney();
				double coupon=bill.getCoupon();
				double gap=actual+coupon-sum;
				
				if(gap>0){
					ic.setCouponGapEarning(String.valueOf(gap));
					afterAllowance+=gap;
				}else{
					ic.setCouponGapEarning("");
				}
				
				//顺带计算折让金额
				double discount=bill.getDiscount();
				allowance+=discount;
			}
			
			if(i>=overSize){//商品报溢收入
				ic.setInventoryOverEarning("");
			}else{
				InventoryOverBillPO bill=inventoryOverBills.get(i);
				int num=Integer.parseInt(bill.getOverAmount());
				double unitPrice=Double.parseDouble(bill.getGoodsPrice());
				
				double total=num*unitPrice;
				afterAllowance+=total;
				ic.setInventoryOverEarning(String.valueOf(total));
			}
			
			if(i>=goodsSize){//成本调价收入
				ic.setCostAjdustEarning("");
			}else{
				GoodsPO goods=goodsList.get(i);
				
				double currentCost=Double.parseDouble(goods.getCurrentCostPrice());
				double cost=Double.parseDouble(goods.getCostPrice());
				
				if(currentCost>cost){
					afterAllowance+=currentCost-cost;
					ic.setCostAjdustEarning(String.valueOf(currentCost-cost));
				}else{
					ic.setCostAjdustEarning("");
				}
			}
			
			if(i>=purReSize){//进货退货差价
				ic.setPurchaseReturnGapEarning("");
			}else{
				PurchaseReturnBillPO bill=purchaseReturnBills.get(i);
				
				ArrayList<SalesGoodsPO> goods=bill.getGoodsList();
				
				double total=0;
				for(int j=0;j<goods.size();j++){
					SalesGoodsPO each=goods.get(j);
					
					String id=each.getGoodsId();
					double price=each.getUnitPrice();
					
					for(int k=0;k<goodsList.size();k++){
						GoodsPO g=goodsList.get(k);
						
						if(g.getNumber().equals(id)){
							double cost=Double.parseDouble(g.getCostPrice());
							
							if(price>cost){
								total+=price-cost;
							}
							break;
						}
					}
					
				}
				
				afterAllowance+=total;
				ic.setPurchaseReturnGapEarning(String.valueOf(total));
			}
			
			if(i>=purSize){//销售成本
				ic.setSalesCost("");
			}else{
				PurchaseBillPO bill=purchaseBills.get(i);
				double cost=bill.getMoney();
				
				totalCost+=cost;
				ic.setSalesCost(String.valueOf(cost));
			}
			
			if(i>=lossSize){//商品报损成本
				ic.setInventoryLossCost("");
			}else{
				InventoryLossBillPO bill=inventoryLossBills.get(i);
				int lossAmount=Integer.parseInt(bill.getLossAmount());
				double unitPrice=Double.parseDouble(bill.getGoodsPrice());
				
				double sum=lossAmount*unitPrice;
				
				totalCost+=sum;
				ic.setInventoryLossCost(String.valueOf(sum));
			}
			
			if(i>=giftSize){//库存赠送成本
				ic.setGiftCost("");
			}else{
				GiftBillPO bill=giftBills.get(i);
				double cost=bill.getTotalPrice();
				
				totalCost+=cost;
				ic.setGiftCost(String.valueOf(cost));
			}
			
			ics.add(ic);
		}
		
		for(int i=0;i<salesReturnBills.size();i++){
			SalesReturnBillPO bill=salesReturnBills.get(i);
			double sum=bill.getMoney();
			afterAllowance-=sum;
		}
		
		for(int i=0;i<purchaseReturnBills.size();i++){
			PurchaseReturnBillPO bill=purchaseReturnBills.get(i);
			double sum=bill.getMoney();
			totalCost-=sum;
		}
		
		double profit=afterAllowance-totalCost;
		
		BusinessConditionListVO vo=new BusinessConditionListVO(
				ics, afterAllowance, allowance, totalCost, profit);
		return vo;
	}
}
