package vo;

import java.io.Serializable;

/**
 * 账单期初信息中银行账户信息中的一条
 * @author guxinyu
 * @version 2017.12.4
 *
 */
public class SingleBankAccountInitialInfoVO implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 5965393932495152005L;
	private String bankAccountName="";
	private String bankAccountSum="";
	
	public SingleBankAccountInitialInfoVO(){
		
	}
	
	public SingleBankAccountInitialInfoVO(String accName,String accSum){
		setBankAccountName(accName);
		setBankAccountSum(accSum);
	}

	public String getBankAccountName() {
		return bankAccountName;
	}

	public void setBankAccountName(String bankAccountName) {
		this.bankAccountName = bankAccountName;
	}

	public String getBankAccountSum() {
		return bankAccountSum;
	}

	public void setBankAccountSum(String bankAccountSum) {
		this.bankAccountSum = bankAccountSum;
	}
	
	

}
