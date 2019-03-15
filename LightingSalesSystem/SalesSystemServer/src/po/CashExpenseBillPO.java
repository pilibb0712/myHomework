package po;

import java.util.ArrayList;
import assistant.utility.Date;

/**
 * 现金费用单
 * @author guxinyu
 * @version 2017.12.4
 *
 */
public class CashExpenseBillPO extends BillPO{
	private static final long serialVersionUID = 1L;
	
	private String bankAccount;
	private ArrayList<BillEntryPO> entries;//条目清单
	private double sum;
	
	public CashExpenseBillPO(){
		
	}
	
	public CashExpenseBillPO(String bId, UserInfoPO optor, String bankAcc, ArrayList<BillEntryPO> entry, double s, String createrComment ,Date d){
		setId(bId);
		setCreater(optor);
		setBankAccount(bankAcc);
		setEntries(entry);
		setSum(s);
		setCreaterComment(createrComment);
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
