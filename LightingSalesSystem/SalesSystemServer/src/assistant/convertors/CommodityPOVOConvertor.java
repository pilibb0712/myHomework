/**
 * A new function(change vo to po or change po to vo) added
 * because it is too trouble to change vo to po or change po to vo in every case
 * write a public class to do the work and be easy to modify
 * @author BeibeiZhang
 * @version 2017.11.28
 *
 */


package assistant.convertors;

import po.ClassificationPO;
import po.GoodsPO;
import po.InventoryLossBillPO;
import po.InventoryOverBillPO;
import po.InventoryWarningBillPO;
import po.StockInOutPO;
import po.UserInfoPO;
import vo.ClassificationVO;
import vo.GoodsVO;
import vo.InventoryLossBillVO;
import vo.InventoryOverBillVO;
import vo.InventoryWarningBillVO;
import vo.StockInOutVO;
import vo.UserInfoVO;

public class CommodityPOVOConvertor {

	public static ClassificationPO classificationVOtoPO(ClassificationVO vo){
	          ClassificationPO po=new ClassificationPO();
	          po.setName(vo.getName());
	          po.setFatherClassification(vo.getFatherClassification());
	          po.setGoodsList(vo.getGoodsList());
	          po.setNumber(vo.getNumber());
	          po.setSonClassification(vo.getSonClassifacation());
	          return po;
     }

	public static ClassificationVO classificationPOtoVO(ClassificationPO po){
    	 ClassificationVO vo=new ClassificationVO();
    		vo.setName(po.getName());
    		vo.setFatherClassification(po.getFatherClassification());
    		vo.setGoodsList(po.getGoodsList());
    		vo.setNumber(po.getNumber());
    		vo.setSonClassification(po.getSonClassifacation());
    		return vo;
     }

	public static GoodsPO GoodsVOtoPO(GoodsVO vo){
    	GoodsPO po=new GoodsPO();
 		po.setName(vo.getName());
 		po.setNumber(vo.getNumber());
 		po.setClassification(vo.getClassification());
 		po.setType(vo.getType());
 		po.setAmount(vo.getAmount());
 		po.setCostPrice(vo.getCostPrice());
 		po.setSellingPrice(vo.getSellingPrice());
 		po.setCurrentCostPrice(vo.getCurrentCostPrice());
 		po.setCurrentSellingPrice(vo.getCurrentSellingPrice());
 		return po;
     }
     public static GoodsVO GoodsPOtoVO(GoodsPO po){
    	GoodsVO vo=new GoodsVO();
  		vo.setName(po.getName());
  		vo.setNumber(po.getNumber());
  		vo.setClassification(po.getClassification());
  		vo.setType(po.getType());
  		vo.setAmount(po.getAmount());
  		vo.setCostPrice(po.getCostPrice());
  		vo.setSellingPrice(po.getSellingPrice());
  		vo.setCurrentCostPrice(po.getCurrentCostPrice());
  		vo.setCurrentSellingPrice(po.getCurrentSellingPrice());
  		return vo;
     }
     public static StockInOutPO StockInOutVOtoPO(StockInOutVO vo){
    		StockInOutPO po=new StockInOutPO();
    		po.setAmount(vo.getAmount());
    		po.setCurrentCostPrice(vo.getCurrentCostPrice());
    		po.setCurrentSellingPrice(vo.getCurrentSellingPrice());
    		po.setGoodsID(vo.getGoodsID());
    		po.setGoodsName(vo.getGoodsName());
    		po.setOperatorName(vo.getOperatorName());
    		po.setProductionDate(vo.getProductionDate());
            po.setTime(vo.getTime());
            return po;
     }
     public static StockInOutVO StockInOutPOtoVO(StockInOutPO po){
    	 StockInOutVO vo=new StockInOutVO();
 		 vo.setAmount(po.getAmount());
 		 vo.setCurrentCostPrice(po.getCurrentCostPrice());
 		 vo.setCurrentSellingPrice(po.getCurrentSellingPrice());
 		 vo.setGoodsID(po.getGoodsID());
 		 vo.setGoodsName(po.getGoodsName());
 		 vo.setOperatorName(po.getOperatorName());
 		 vo.setProductionDate(po.getProductionDate());
         vo.setTime(po.getTime());
         return vo;
     }
     public static InventoryWarningBillPO inventoryWarningBillVOtoPO(InventoryWarningBillVO vo){

    	 InventoryWarningBillPO po=new InventoryWarningBillPO();

    	 po.setId(vo.getId());
 		 po.setBillStateEnum(vo.getBillStateEnum());
 		 po.setApproveDate(vo.getApproveDate());

 		 UserInfoPOVOConvertor convertor=new UserInfoPOVOConvertor();
   	     UserInfoPO approverPO=convertor.voToPO(vo.getApprover());

 		 po.setApprover(approverPO);
 		 po.setApproverComment(vo.getApproverComment());
 		 po.setCreateDate(vo.getCreateDate());
 		 po.setCreaterComment(vo.getCreaterComment());

 		 UserInfoPO executorPO=convertor.voToPO(vo.getExecutor());
 		 po.setExecutor(executorPO);
 		 po.setFinishDate(vo.getFinishDate());

    	 UserInfoPO creatorPO=convertor.voToPO(vo.getCreater());
    	 po.setCreater(creatorPO);

         po.setCategoryEnum(vo.getCategoryEnum());
         po.setGoodsID(vo.getGoodsID());
         po.setGoodsName(vo.getGoodsName());
         po.setGoodsType(vo.getGoodsType());
         po.setGoodsPrice(vo.getGoodsPrice());
         po.setSystemAmount(vo.getSystemAmount());
         po.setWarningAmount(vo.getWarningAmount());
         po.setLossAmount(vo.getLossAmount());
         return po;
    }

     public static InventoryWarningBillVO inventoryWarningBillPOtoVO(InventoryWarningBillPO po){
    	 InventoryWarningBillVO vo=new InventoryWarningBillVO();

    	 vo.setId(po.getId());
 		 vo.setBillStateEnum(po.getBillStateEnum());
 		 vo.setApproveDate(po.getApproveDate());

 		 UserInfoPOVOConvertor convertor=new UserInfoPOVOConvertor();
   	     UserInfoVO approverVO=convertor.poToVO(po.getApprover());

   	     vo.setApprover(approverVO);
		 vo.setApproverComment(po.getApproverComment());
		 vo.setCreateDate(po.getCreateDate());
		 vo.setCreaterComment(po.getCreaterComment());

		 UserInfoVO executorVO=convertor.poToVO(po.getExecutor());
		 vo.setExecutor(executorVO);
		 vo.setFinishDate(po.getFinishDate());

    	 UserInfoVO creatorVO=convertor.poToVO(po.getCreater());
    	 vo.setCreater(creatorVO);

         vo.setCategoryEnum(po.getCategoryEnum());
         vo.setGoodsID(po.getGoodsID());
         vo.setGoodsName(po.getGoodsName());
         vo.setGoodsType(po.getGoodsType());
         vo.setGoodsPrice(po.getGoodsPrice());
         vo.setSystemAmount(po.getSystemAmount());
         vo.setWarningAmount(po.getWarningAmount());
         vo.setLossAmount(po.getLossAmount());
         return vo;
    }

     public static InventoryLossBillPO inventoryLossBillVOtoPO(InventoryLossBillVO vo){
    	 InventoryLossBillPO po=new InventoryLossBillPO();

    	 po.setId(vo.getId());
 		 po.setBillStateEnum(vo.getBillStateEnum());
 		 po.setApproveDate(vo.getApproveDate());

 		 UserInfoPOVOConvertor convertor=new UserInfoPOVOConvertor();
   	     UserInfoPO approverPO=convertor.voToPO(vo.getApprover());

   	     po.setApprover(approverPO);
		 po.setApproverComment(vo.getApproverComment());
		 po.setCreateDate(vo.getCreateDate());
		 po.setCreaterComment(vo.getCreaterComment());

		 UserInfoPO executorPO=convertor.voToPO(vo.getExecutor());
		 po.setExecutor(executorPO);
		 po.setFinishDate(vo.getFinishDate());

    	 UserInfoPO creatorPO=convertor.voToPO(vo.getCreater());
    	 po.setCreater(creatorPO);

         po.setCategoryEnum(vo.getCategoryEnum());
         po.setGoodsID(vo.getGoodsID());
         po.setGoodsName(vo.getGoodsName());
         po.setGoodsType(vo.getGoodsType());
         po.setGoodsPrice(vo.getGoodsPrice());
         po.setSystemAmount(vo.getSystemAmount());
         po.setRealAmount(vo.getRealAmount());
         po.setLossAmount(vo.getLossAmount());
         return po;
    }

     public static InventoryLossBillVO inventoryLossBillPOtoVO(InventoryLossBillPO po){
    	 InventoryLossBillVO vo=new InventoryLossBillVO();

    	 vo.setId(po.getId());
 		 vo.setBillStateEnum(po.getBillStateEnum());
 		 vo.setApproveDate(po.getApproveDate());

 		 UserInfoPOVOConvertor convertor=new UserInfoPOVOConvertor();
   	     UserInfoVO approverVO=convertor.poToVO(po.getApprover());

   	     vo.setApprover(approverVO);
		 vo.setApproverComment(po.getApproverComment());
		 vo.setCreateDate(po.getCreateDate());
		 vo.setCreaterComment(po.getCreaterComment());

		 UserInfoVO executorVO=convertor.poToVO(po.getExecutor());
		 vo.setExecutor(executorVO);
		 vo.setFinishDate(po.getFinishDate());

    	 UserInfoVO creatorVO=convertor.poToVO(po.getCreater());
    	 vo.setCreater(creatorVO);

         vo.setCategoryEnum(po.getCategoryEnum());
         vo.setGoodsID(po.getGoodsID());
         vo.setGoodsName(po.getGoodsName());
         vo.setGoodsType(po.getGoodsType());
         vo.setGoodsPrice(po.getGoodsPrice());
         vo.setSystemAmount(po.getSystemAmount());
         vo.setRealAmount(po.getRealAmount());
         vo.setLossAmount(po.getLossAmount());
         return vo;
    }

     public static InventoryOverBillPO inventoryOverBillVOtoPO(InventoryOverBillVO vo){
    	 InventoryOverBillPO po=new InventoryOverBillPO();

    	 po.setId(vo.getId());
 		 po.setBillStateEnum(vo.getBillStateEnum());
 		 po.setApproveDate(vo.getApproveDate());

 		 UserInfoPOVOConvertor convertor=new UserInfoPOVOConvertor();
   	     UserInfoPO approverPO=convertor.voToPO(vo.getApprover());

   	     po.setApprover(approverPO);
		 po.setApproverComment(vo.getApproverComment());
		 po.setCreateDate(vo.getCreateDate());
		 po.setCreaterComment(vo.getCreaterComment());

		 UserInfoPO executorPO=convertor.voToPO(vo.getExecutor());
		 po.setExecutor(executorPO);
		 po.setFinishDate(vo.getFinishDate());

    	 UserInfoPO creatorPO=convertor.voToPO(vo.getCreater());
    	 po.setCreater(creatorPO);

         po.setCategoryEnum(vo.getCategoryEnum());
         po.setGoodsID(vo.getGoodsID());
         po.setGoodsName(vo.getGoodsName());
         po.setGoodsType(vo.getGoodsType());
         po.setGoodsPrice(vo.getGoodsPrice());
         po.setSystemAmount(vo.getSystemAmount());
         po.setRealAmount(vo.getRealAmount());
         po.setOverAmount(vo.getOverAmount());
         return po;
    }

     public static InventoryOverBillVO inventoryOverBillPOtoVO(InventoryOverBillPO po){
    	 InventoryOverBillVO vo=new InventoryOverBillVO();

    	 vo.setId(po.getId());
 		 vo.setBillStateEnum(po.getBillStateEnum());
 		 vo.setApproveDate(po.getApproveDate());

 		 UserInfoPOVOConvertor convertor=new UserInfoPOVOConvertor();
   	     UserInfoVO approverVO=convertor.poToVO(po.getApprover());

   	     vo.setApprover(approverVO);
		 vo.setApproverComment(po.getApproverComment());
		 vo.setCreateDate(po.getCreateDate());
		 vo.setCreaterComment(po.getCreaterComment());

		 UserInfoVO executorVO=convertor.poToVO(po.getExecutor());
		 vo.setExecutor(executorVO);
		 vo.setFinishDate(po.getFinishDate());

    	 UserInfoVO creatorVO=convertor.poToVO(po.getCreater());
    	 vo.setCreater(creatorVO);

         vo.setCategoryEnum(po.getCategoryEnum());
         vo.setGoodsID(po.getGoodsID());
         vo.setGoodsName(po.getGoodsName());
         vo.setGoodsType(po.getGoodsType());
         vo.setGoodsPrice(po.getGoodsPrice());
         vo.setSystemAmount(po.getSystemAmount());
         vo.setRealAmount(po.getRealAmount());
         vo.setOverAmount(po.getOverAmount());
         return vo;
    }

}
