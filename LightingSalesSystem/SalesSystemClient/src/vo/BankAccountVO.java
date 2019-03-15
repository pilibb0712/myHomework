package vo;

import java.io.Serializable;

/**
 * “¯––’Àªß
 * @author guxinyu
 * @version 2017.12.20
 *
 */
public class BankAccountVO implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 5699009250153677802L;
	private boolean isSelected;
	private String accountName;
	private double remainingSum;
	
	public BankAccountVO(String accN, double remSum){
		setAccountName(accN);
		setRemainingSum(remSum);
	}
	
	public double getRemainingSum() {
		return remainingSum;
	}
	public void setRemainingSum(double remainingSum) {
		this.remainingSum = remainingSum;
	}
	public String getAccountName() {
		return accountName;
	}
	public void setAccountName(String accountName) {
		this.accountName = accountName;
	}
	public void setSelected(boolean se){
		this.isSelected=se;
	}
	public boolean isSelected(){
		return isSelected;
	}
}
