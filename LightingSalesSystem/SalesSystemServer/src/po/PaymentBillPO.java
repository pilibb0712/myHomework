package po;

import java.util.ArrayList;
import assistant.utility.Date;

/**
 * ¸¶¿îµ¥
 * @author guxinyu
 * @version 2017.12.6
 *
 */
public class PaymentBillPO extends BillPO{

	/**
	 * 
	 */
	private static final long serialVersionUID = -4724633606849033802L;
	private String bankAccount;
	private ArrayList<BillEntryPO> entries;
	private double sum;
	
	public PaymentBillPO(){
		
	}
	
	public PaymentBillPO(String bId, UserInfoPO optor, String bankAcc, ArrayList<BillEntryPO> entry, double s, String com, Date d){
		setId(bId);
		setCreater(optor);
		setBankAccount(bankAcc);
		setEntries(entry);
		setSum(s);
		setCreaterComment(com);
		setCreateDate(d);
	}

	public String getBankAccount() {
		return bankAccount;
	}

	public void setBankAccount(String bankAccount) {
		this.bankAccount = bankAccount;
	}

	public ArrayList<BillEntryPO> getEntries() {
		return entries;
	}

	public void setEntries(ArrayList<BillEntryPO> entries) {
		this.entries = entries;
	}

	public double getSum() {
		return sum;
	}

	public void setSum(double sum) {
		this.sum = sum;
	}
}
