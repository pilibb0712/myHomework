package assistant.convertors;

import java.util.ArrayList;

import po.InOutDetailsPO;
import po.InitialInfoPO;
import po.SingleBankAccountInitialInfoPO;
import po.SingleCommodityInitialInfoPO;
import po.SingleCustomerInitialInfoPO;
import po.SingleEntryPO;
import vo.InOutDetailsVO;
import vo.InitialInfoVO;
import vo.SingleBankAccountInitialInfoVO;
import vo.SingleCommodityInitialInfoVO;
import vo.SingleCustomerInitialInfoVO;
import vo.SingleEntryVO;

/**
 * 账单相关povo转换器
 * @author guxinyu
 * @version 2017.12.4
 *
 */
public class InOutPOVOConvertor {
	
	public static SingleCommodityInitialInfoPO singleCommodityInitialInfoVOtoPO(SingleCommodityInitialInfoVO toConvert){
		if(toConvert==null)
			return null;
		
		SingleCommodityInitialInfoPO toReturn=new SingleCommodityInitialInfoPO(
				toConvert.getCommodityClass(),
				toConvert.getCommodityName(),
				toConvert.getCommodityType(),
				toConvert.getPurchasePrice(),
				toConvert.getSellingPrice(),
				toConvert.getRecentPurchasePrice(),
				toConvert.getRecentSellingPrice()
				);
		
		return toReturn;
	}
	
	public static SingleCommodityInitialInfoVO singleCommodityInitialInfoPOtoVO(SingleCommodityInitialInfoPO toConvert){
		if(toConvert==null)
			return null;
		
		SingleCommodityInitialInfoVO toReturn=new SingleCommodityInitialInfoVO(
				toConvert.getCommodityClass(),
				toConvert.getCommodityName(),
				toConvert.getCommodityType(),
				toConvert.getPurchasePrice(),
				toConvert.getSellingPrice(),
				toConvert.getRecentPurchasePrice(),
				toConvert.getRecentSellingPrice()
				);
		
		return toReturn;
	}
	
	public static SingleCustomerInitialInfoPO singleCustomerInitialInfoVOtoPO(SingleCustomerInitialInfoVO toConvert){
		if(toConvert==null)
			return null;
		
		SingleCustomerInitialInfoPO toReturn=new SingleCustomerInitialInfoPO(
				toConvert.getCustomerClass(),
				toConvert.getCustomerName(),
				toConvert.getCustomerContact(),
				toConvert.getCustomerReceive(),
				toConvert.getCustomerPay()
				);
		
		return toReturn;
	}
	
	public static SingleCustomerInitialInfoVO singleCustomerInitialInfoPOtoVO(SingleCustomerInitialInfoPO toConvert){
		if(toConvert==null)
			return null;
		
		SingleCustomerInitialInfoVO toReturn=new SingleCustomerInitialInfoVO(
				toConvert.getCustomerClass(),
				toConvert.getCustomerName(),
				toConvert.getCustomerContact(),
				toConvert.getCustomerReceive(),
				toConvert.getCustomerPay()
				);
		
		return toReturn;
	}
	
	public static SingleBankAccountInitialInfoVO singleBankAccountInitialInfoPOtoVO(SingleBankAccountInitialInfoPO toConvert){
		if(toConvert==null)
			return null;
		
		SingleBankAccountInitialInfoVO toReturn=new SingleBankAccountInitialInfoVO(
				toConvert.getBankAccountName(),
				toConvert.getBankAccountSum()
				);
		
		return toReturn;
	}
	
	public static SingleBankAccountInitialInfoPO singleBankAccountInitialInfoVOtoPO(SingleBankAccountInitialInfoVO toConvert){
		if(toConvert==null)
			return null;
		
		SingleBankAccountInitialInfoPO toReturn=new SingleBankAccountInitialInfoPO(
				toConvert.getBankAccountName(),
				toConvert.getBankAccountSum()
				);
		
		return toReturn;
	}
	
	public static ArrayList<SingleCommodityInitialInfoPO> singleCommodityInitialInfoListVOtoPO(ArrayList<SingleCommodityInitialInfoVO> toConvert){
		if(toConvert==null)
			return null;
		
		ArrayList<SingleCommodityInitialInfoPO> toReturn=new ArrayList<SingleCommodityInitialInfoPO>();
		
		for(int i=0;i<toConvert.size();i++){
			SingleCommodityInitialInfoPO each=singleCommodityInitialInfoVOtoPO(toConvert.get(i));
			toReturn.add(each);
		}
		
		return toReturn;
	}
	
	public static ArrayList<SingleCommodityInitialInfoVO> singleCommodityInitialInfoListPOtoVO(ArrayList<SingleCommodityInitialInfoPO> toConvert){
		if(toConvert==null)
			return null;
		
		ArrayList<SingleCommodityInitialInfoVO> toReturn=new ArrayList<SingleCommodityInitialInfoVO>();
		
		for(int i=0;i<toConvert.size();i++){
			SingleCommodityInitialInfoVO each=singleCommodityInitialInfoPOtoVO(toConvert.get(i));
			toReturn.add(each);
		}
		
		return toReturn;
	}
	
	public static ArrayList<SingleCustomerInitialInfoPO> singleCustomerInitialInfoListVOtoPO(ArrayList<SingleCustomerInitialInfoVO> toConvert){
		if(toConvert==null)
			return null;
		
		ArrayList<SingleCustomerInitialInfoPO> toReturn=new ArrayList<SingleCustomerInitialInfoPO>();
		
		for(int i=0;i<toConvert.size();i++){
			SingleCustomerInitialInfoPO each=singleCustomerInitialInfoVOtoPO(toConvert.get(i));
			toReturn.add(each);
		}
		
		return toReturn;
	}
	
	public static ArrayList<SingleCustomerInitialInfoVO> singleCustomerInitialInfoListPOtoVO(ArrayList<SingleCustomerInitialInfoPO> toConvert){
		if(toConvert==null)
			return null;
		
		ArrayList<SingleCustomerInitialInfoVO> toReturn=new ArrayList<SingleCustomerInitialInfoVO>();
		
		for(int i=0;i<toConvert.size();i++){
			SingleCustomerInitialInfoVO each=singleCustomerInitialInfoPOtoVO(toConvert.get(i));
			toReturn.add(each);
		}
		
		return toReturn;
	}
	
	public static ArrayList<SingleBankAccountInitialInfoPO> singleBankAccountInitialInfoListVOtoPO(ArrayList<SingleBankAccountInitialInfoVO> toConvert){
		if(toConvert==null)
			return null;
		
		ArrayList<SingleBankAccountInitialInfoPO> toReturn=new ArrayList<SingleBankAccountInitialInfoPO>();
		
		for(int i=0;i<toConvert.size();i++){
			SingleBankAccountInitialInfoPO each=singleBankAccountInitialInfoVOtoPO(toConvert.get(i));
			toReturn.add(each);
		}
		
		return toReturn;
	}
	
	public static ArrayList<SingleBankAccountInitialInfoVO> singleBankAccountInitialInfoListPOtoVO(ArrayList<SingleBankAccountInitialInfoPO> toConvert){
		if(toConvert==null)
			return null;
		
		ArrayList<SingleBankAccountInitialInfoVO> toReturn=new ArrayList<SingleBankAccountInitialInfoVO>();
		
		for(int i=0;i<toConvert.size();i++){
			SingleBankAccountInitialInfoVO each=singleBankAccountInitialInfoPOtoVO(toConvert.get(i));
			toReturn.add(each);
		}
		
		return toReturn;
	}
	
	public static InitialInfoPO initialInfoVOtoPO(InitialInfoVO toConvert){
		if(toConvert==null)
			return null;
		
		InitialInfoPO toReturn=new InitialInfoPO(
				singleCommodityInitialInfoListVOtoPO(toConvert.getCommodityInfos()),
				singleCustomerInitialInfoListVOtoPO(toConvert.getCustomerInfos()),
				singleBankAccountInitialInfoListVOtoPO(toConvert.getBankAccountInfos())
				);
		toReturn.setYear(toConvert.getYear());
		
		return toReturn;
	}
	
	public static InitialInfoVO initialInfoPOtoVO(InitialInfoPO toConvert){
		if(toConvert==null)
			return null;
		
		InitialInfoVO toReturn=new InitialInfoVO(
				singleCommodityInitialInfoListPOtoVO(toConvert.getCommodityInfos()),
				singleCustomerInitialInfoListPOtoVO(toConvert.getCustomerInfos()),
				singleBankAccountInitialInfoListPOtoVO(toConvert.getBankAccountInfos())
				);
		toReturn.setYear(toConvert.getYear());
		
		return toReturn;
	}
	
	public static SingleEntryPO singleEntryVOtoPO(SingleEntryVO toConvert){
		if(toConvert==null)
			return null;
		
		SingleEntryPO toReturn=new SingleEntryPO(
				toConvert.getBelongedBill(),
				toConvert.getDate(),
				toConvert.getEvent(),
				toConvert.getAmount()
				);
		
		return toReturn;
	}
	
	public static SingleEntryVO singleEntryPOtoVO(SingleEntryPO toConvert){
		if(toConvert==null)
			return null;
		
		SingleEntryVO toReturn=new SingleEntryVO(
				toConvert.getBelongedBill(),
				toConvert.getDate(),
				toConvert.getEvent(),
				toConvert.getAmount()
				);
		
		return toReturn;
	}
	
	public static InOutDetailsPO inOutDetailsVOtoPO(InOutDetailsVO toConvert){
		if(toConvert==null)
			return null;
		
		ArrayList<SingleEntryPO> entriesPO=new ArrayList<SingleEntryPO>();
		ArrayList<SingleEntryVO> entriesVO=toConvert.getEntries();
		if(entriesVO!=null){
			for(int i=0;i<entriesVO.size();i++){
				SingleEntryPO each=singleEntryVOtoPO(entriesVO.get(i));
				entriesPO.add(each);
			}
		}
		
		InOutDetailsPO toReturn=new InOutDetailsPO(entriesPO);
		
		return toReturn;
	}
	
	public static InOutDetailsVO inOutDetailsPOtoVO(InOutDetailsPO toConvert){
		if(toConvert==null)
			return null;
		
		ArrayList<SingleEntryVO> entriesVO=new ArrayList<SingleEntryVO>();
		ArrayList<SingleEntryPO> entriesPO=toConvert.getEntries();
		if(entriesPO!=null){
			for(int i=0;i<entriesPO.size();i++){
				SingleEntryVO each=singleEntryPOtoVO(entriesPO.get(i));
				entriesVO.add(each);
			}
		}
		
		InOutDetailsVO toReturn=new InOutDetailsVO(entriesVO);
		
		return toReturn;
	}
}
