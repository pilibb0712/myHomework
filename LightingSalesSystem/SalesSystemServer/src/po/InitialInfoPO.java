package po;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * 账单期初信息
 * @author guxinyu
 * @version 2017.12.4
 *
 */
public class InitialInfoPO implements Serializable{

	private static final long serialVersionUID = 3L;
	
	private String year;
	private ArrayList<SingleCommodityInitialInfoPO> commodityInfos=new ArrayList<SingleCommodityInitialInfoPO>();
	private ArrayList<SingleCustomerInitialInfoPO> customerInfos=new ArrayList<SingleCustomerInitialInfoPO>();
	private ArrayList<SingleBankAccountInitialInfoPO> bankAccountInfos=new ArrayList<SingleBankAccountInitialInfoPO>();
	
	public InitialInfoPO(){
		
	}
	
	public InitialInfoPO(ArrayList<SingleCommodityInitialInfoPO> comInfos,
			ArrayList<SingleCustomerInitialInfoPO> cusInfos,
			ArrayList<SingleBankAccountInitialInfoPO> bankInfos){
		setCommodityInfos(comInfos);
		setCustomerInfos(cusInfos);
		setBankAccountInfos(bankInfos);
	}
	
	public String getYear() {
		return year;
	}

	public void setYear(String year) {
		this.year = year;
	}
	
	public ArrayList<SingleCommodityInitialInfoPO> getCommodityInfos() {
		return commodityInfos;
	}

	public void setCommodityInfos(ArrayList<SingleCommodityInitialInfoPO> commodityInfos) {
		this.commodityInfos = commodityInfos;
	}

	public ArrayList<SingleCustomerInitialInfoPO> getCustomerInfos() {
		return customerInfos;
	}

	public void setCustomerInfos(ArrayList<SingleCustomerInitialInfoPO> customerInfos) {
		this.customerInfos = customerInfos;
	}

	public ArrayList<SingleBankAccountInitialInfoPO> getBankAccountInfos() {
		return bankAccountInfos;
	}

	public void setBankAccountInfos(ArrayList<SingleBankAccountInitialInfoPO> bankAccountInfos) {
		this.bankAccountInfos = bankAccountInfos;
	}
}
