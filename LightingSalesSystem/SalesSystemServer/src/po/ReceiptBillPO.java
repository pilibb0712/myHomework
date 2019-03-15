package po;

import java.util.ArrayList;
import assistant.utility.Date;

/**
 * �տ
 * @author guxinyu
 * @version 2017.11.7
 *
 */
public class ReceiptBillPO extends BillPO{
	private static final long serialVersionUID = 3L;

	private String bankAccount;
	private ArrayList<BillEntryPO> entries;
	private double sum;
	
	public ReceiptBillPO(){
		
	}
	
	public ReceiptBillPO(String bId, UserInfoPO optor, String bankAcc, ArrayList<BillEntryPO> entry, double s, String com, Date d){
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
