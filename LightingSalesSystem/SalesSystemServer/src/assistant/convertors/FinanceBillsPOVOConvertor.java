package assistant.convertors;

import java.util.ArrayList;

import po.BillEntryPO;
import po.CashExpenseBillPO;
import po.PaymentBillPO;
import po.ReceiptBillPO;
import vo.BillEntryVO;
import vo.CashExpenseBillVO;
import vo.PaymentBillVO;
import vo.ReceiptBillVO;

public class FinanceBillsPOVOConvertor {
	public static BillEntryPO billEntryVOtoPO(BillEntryVO toConvert){
		if(toConvert==null)
			return null;
		
		BillEntryPO toReturn=new BillEntryPO(toConvert.getEntryName(),toConvert.getEntryMoney());
		return toReturn;
	}
	
	public static BillEntryVO billEntryPOtoVO(BillEntryPO toConvert){
		if(toConvert==null)
			return null;
		
		BillEntryVO toReturn=new BillEntryVO(toConvert.getEntryName(),toConvert.getEntryMoney());
		return toReturn;
	}
	
	public static CashExpenseBillPO cashExpenseBillVOtoPO(CashExpenseBillVO toConvert){
		if(toConvert==null)
			return null;
		
		CashExpenseBillPO toReturn=new CashExpenseBillPO();
		
		toReturn.setApproveDate(toConvert.getApproveDate());
		toReturn.setApprover(UserInfoPOVOConvertor.voToPO(toConvert.getApprover()));
		toReturn.setApproverComment(toConvert.getApproverComment());
		toReturn.setBankAccount(toConvert.getBankAccount());
		toReturn.setBillStateEnum(toConvert.getBillStateEnum());
		toReturn.setCategoryEnum(toConvert.getCategoryEnum());
		toReturn.setCreateDate(toConvert.getCreateDate());
		toReturn.setCreater(UserInfoPOVOConvertor.voToPO(toConvert.getCreater()));
		toReturn.setCreaterComment(toConvert.getCreaterComment());
		ArrayList<BillEntryPO> entryPO=new ArrayList<BillEntryPO>();
		ArrayList<BillEntryVO> entryVO=toConvert.getEntries();
		if(entryVO!=null){
			for(int i=0;i<entryVO.size();i++)
				entryPO.add(billEntryVOtoPO(entryVO.get(i)));
		}
		toReturn.setEntries(entryPO);
		toReturn.setExecutor(UserInfoPOVOConvertor.voToPO(toConvert.getExecutor()));
		toReturn.setFinishDate(toConvert.getFinishDate());
		toReturn.setId(toConvert.getId());
		toReturn.setSum(toConvert.getSum());
		
		return toReturn;
	}
	
	public static CashExpenseBillVO cashExpenseBillPOtoVO(CashExpenseBillPO toConvert){
		if(toConvert==null)
			return null;
		
		CashExpenseBillVO toReturn=new CashExpenseBillVO();
		
		toReturn.setApproveDate(toConvert.getApproveDate());
		toReturn.setApprover(UserInfoPOVOConvertor.poToVO(toConvert.getApprover()));
		toReturn.setApproverComment(toConvert.getApproverComment());
		toReturn.setBankAccount(toConvert.getBankAccount());
		toReturn.setBillStateEnum(toConvert.getBillStateEnum());
		toReturn.setCategoryEnum(toConvert.getCategoryEnum());
		toReturn.setCreateDate(toConvert.getCreateDate());
		toReturn.setCreater(UserInfoPOVOConvertor.poToVO(toConvert.getCreater()));
		toReturn.setCreaterComment(toConvert.getCreaterComment());
		ArrayList<BillEntryVO> entryVO=new ArrayList<BillEntryVO>();
		ArrayList<BillEntryPO> entryPO=toConvert.getEntries();
		if(entryPO!=null){
			for(int i=0;i<entryPO.size();i++)
				entryVO.add(billEntryPOtoVO(entryPO.get(i)));
		}
		toReturn.setEntries(entryVO);
		toReturn.setExecutor(UserInfoPOVOConvertor.poToVO(toConvert.getExecutor()));
		toReturn.setFinishDate(toConvert.getFinishDate());
		toReturn.setId(toConvert.getId());
		toReturn.setSum(toConvert.getSum());
		
		return toReturn;
	}
	
	public static PaymentBillPO paymentBillVOtoPO(PaymentBillVO toConvert){
		if(toConvert==null)
			return null;
		
		PaymentBillPO toReturn=new PaymentBillPO();
		
		toReturn.setApproveDate(toConvert.getApproveDate());
		toReturn.setApprover(UserInfoPOVOConvertor.voToPO(toConvert.getApprover()));
		toReturn.setApproverComment(toConvert.getApproverComment());
		toReturn.setBankAccount(toConvert.getBankAccount());
		toReturn.setBillStateEnum(toConvert.getBillStateEnum());
		toReturn.setCategoryEnum(toConvert.getCategoryEnum());
		toReturn.setCreateDate(toConvert.getCreateDate());
		toReturn.setCreater(UserInfoPOVOConvertor.voToPO(toConvert.getCreater()));
		toReturn.setCreaterComment(toConvert.getCreaterComment());
		ArrayList<BillEntryPO> entryPO=new ArrayList<BillEntryPO>();
		ArrayList<BillEntryVO> entryVO=toConvert.getEntries();
		if(entryVO!=null){
			for(int i=0;i<entryVO.size();i++)
				entryPO.add(billEntryVOtoPO(entryVO.get(i)));
		}	
		toReturn.setEntries(entryPO);
		toReturn.setExecutor(UserInfoPOVOConvertor.voToPO(toConvert.getExecutor()));
		toReturn.setFinishDate(toConvert.getFinishDate());
		toReturn.setId(toConvert.getId());
		toReturn.setSum(toConvert.getSum());
		
		return toReturn;
	}
	
	public static PaymentBillVO paymentBillPOtoVO(PaymentBillPO toConvert){
		if(toConvert==null)
			return null;
		
		PaymentBillVO toReturn=new PaymentBillVO();
		
		toReturn.setApproveDate(toConvert.getApproveDate());
		toReturn.setApprover(UserInfoPOVOConvertor.poToVO(toConvert.getApprover()));
		toReturn.setApproverComment(toConvert.getApproverComment());
		toReturn.setBankAccount(toConvert.getBankAccount());
		toReturn.setBillStateEnum(toConvert.getBillStateEnum());
		toReturn.setCategoryEnum(toConvert.getCategoryEnum());
		toReturn.setCreateDate(toConvert.getCreateDate());
		toReturn.setCreater(UserInfoPOVOConvertor.poToVO(toConvert.getCreater()));
		toReturn.setCreaterComment(toConvert.getCreaterComment());
		ArrayList<BillEntryVO> entryVO=new ArrayList<BillEntryVO>();
		ArrayList<BillEntryPO> entryPO=toConvert.getEntries();
		if(entryPO!=null){
			for(int i=0;i<entryPO.size();i++)
				entryVO.add(billEntryPOtoVO(entryPO.get(i)));
		}
		toReturn.setEntries(entryVO);
		toReturn.setExecutor(UserInfoPOVOConvertor.poToVO(toConvert.getExecutor()));
		toReturn.setFinishDate(toConvert.getFinishDate());
		toReturn.setId(toConvert.getId());
		toReturn.setSum(toConvert.getSum());
		
		return toReturn;
	}
	
	public static ReceiptBillPO receiptBillVOtoPO(ReceiptBillVO toConvert){
		if(toConvert==null)
			return null;
		
		ReceiptBillPO toReturn=new ReceiptBillPO();
		
		toReturn.setApproveDate(toConvert.getApproveDate());
		toReturn.setApprover(UserInfoPOVOConvertor.voToPO(toConvert.getApprover()));
		toReturn.setApproverComment(toConvert.getApproverComment());
		toReturn.setBankAccount(toConvert.getBankAccount());
		toReturn.setBillStateEnum(toConvert.getBillStateEnum());
		toReturn.setCategoryEnum(toConvert.getCategoryEnum());
		toReturn.setCreateDate(toConvert.getCreateDate());
		toReturn.setCreater(UserInfoPOVOConvertor.voToPO(toConvert.getCreater()));
		toReturn.setCreaterComment(toConvert.getCreaterComment());
		ArrayList<BillEntryPO> entryPO=new ArrayList<BillEntryPO>();
		ArrayList<BillEntryVO> entryVO=toConvert.getEntries();
		if(entryVO!=null){
			for(int i=0;i<entryVO.size();i++)
				entryPO.add(billEntryVOtoPO(entryVO.get(i)));
		}
		toReturn.setEntries(entryPO);
		toReturn.setExecutor(UserInfoPOVOConvertor.voToPO(toConvert.getExecutor()));
		toReturn.setFinishDate(toConvert.getFinishDate());
		toReturn.setId(toConvert.getId());
		toReturn.setSum(toConvert.getSum());
		
		return toReturn;
	}
	
	public static ReceiptBillVO receiptBillPOtoVO(ReceiptBillPO toConvert){
		if(toConvert==null)
			return null;
		
		ReceiptBillVO toReturn=new ReceiptBillVO();
		
		toReturn.setApproveDate(toConvert.getApproveDate());
		toReturn.setApprover(UserInfoPOVOConvertor.poToVO(toConvert.getApprover()));
		toReturn.setApproverComment(toConvert.getApproverComment());
		toReturn.setBankAccount(toConvert.getBankAccount());
		toReturn.setBillStateEnum(toConvert.getBillStateEnum());
		toReturn.setCategoryEnum(toConvert.getCategoryEnum());
		toReturn.setCreateDate(toConvert.getCreateDate());
		toReturn.setCreater(UserInfoPOVOConvertor.poToVO(toConvert.getCreater()));
		toReturn.setCreaterComment(toConvert.getCreaterComment());
		ArrayList<BillEntryVO> entryVO=new ArrayList<BillEntryVO>();
		ArrayList<BillEntryPO> entryPO=toConvert.getEntries();
		if(entryPO!=null){
			for(int i=0;i<entryPO.size();i++)
				entryVO.add(billEntryPOtoVO(entryPO.get(i)));
		}
		toReturn.setEntries(entryVO);
		toReturn.setExecutor(UserInfoPOVOConvertor.poToVO(toConvert.getExecutor()));
		toReturn.setFinishDate(toConvert.getFinishDate());
		toReturn.setId(toConvert.getId());
		toReturn.setSum(toConvert.getSum());
		
		return toReturn;
	}
}
