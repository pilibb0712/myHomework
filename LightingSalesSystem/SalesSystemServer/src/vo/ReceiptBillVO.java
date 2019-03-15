package vo;

import java.util.ArrayList;

import assistant.utility.Date;

/**
 * �տ
 * @author guxinyu
 * @version 2017.12.6
 *
 */
public class ReceiptBillVO extends BillVO{
	/**
	 * 
	 */
	private static final long serialVersionUID = 4218223016602946664L;
	private String bankAccount;
	private ArrayList<BillEntryVO> entries;
	private double sum;
	
	public ReceiptBillVO(){
		
	}
	
	public ReceiptBillVO(UserInfoVO optor, String bankAcc, ArrayList<BillEntryVO> entry, double s, String com, Date d){
		setCreater(optor);
		setBankAccount(bankAcc);
		setEntries(entry);
		setSum(s);
		setCreaterComment(com);
		setCreateDate(d);
	}
	
	public String getBankAccount() {
		if(bankAccount==null)
			return "";
		else
			return bankAccount;
	}

	public void setBankAccount(String bankAccount) {
		this.bankAccount = bankAccount;
	}

	public ArrayList<BillEntryVO> getEntries() {
		return entries;
	}

	public void setEntries(ArrayList<BillEntryVO> entries) {
		this.entries = entries;
	}

	public double getSum() {
		return sum;
	}

	public void setSum(double sum) {
		this.sum = sum;
	}

}
