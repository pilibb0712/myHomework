package assistant.convertors;

import po.BankAccountPO;
import vo.BankAccountVO;


/**
 * 银行账户VOPO转换器
 * @author guxinyu
 * @version 2017.12.4
 *
 */
public class BankAccountPOVOConvertor {
	public static BankAccountPO bankAccountVOtoPO(BankAccountVO toConvert){
		if(toConvert==null)
			return null;

		String name=toConvert.getAccountName();
		double sum=toConvert.getRemainingSum();
		
		BankAccountPO toReturn=new BankAccountPO(name, sum);
		
		return toReturn;
	}
	
	public static BankAccountVO bankAccountPOtoVO(BankAccountPO toConvert){
		if(toConvert==null)
			return null;
		
		String name=toConvert.getAccountName();
		double sum=toConvert.getRemainingSum();
		
		BankAccountVO toReturn=new BankAccountVO(name, sum);
		
		return toReturn;
	}
}
