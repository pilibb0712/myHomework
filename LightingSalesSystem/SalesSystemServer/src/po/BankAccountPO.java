package po;

import java.io.Serializable;

/**
 * 银行账户
 * @author guxinyu 
 * @version 2017.11.7
 *
 */
public class BankAccountPO implements Serializable{
	
	private static final long serialVersionUID = -5809782578272943999L;
	
	private String accountName;
	private double remainingSum;
	

	public BankAccountPO(){
		
	}

	public BankAccountPO(String accN, double remSum){
		setAccountName(accN);
		setRemainingSum(remSum);
	}

	public String getAccountName() {
		return accountName;
	}

	public void setAccountName(String accountName) {
		this.accountName = accountName;//需补充：检查该账户名是否已经存在
	}

	public double getRemainingSum() {
		return remainingSum;
	}

	public void setRemainingSum(double remainingSum) {
		this.remainingSum = remainingSum;
	}
}
