package vo;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * 账单期初信息
 * @author guxinyu
 * @version 2017.12.4
 *
 */
public class InitialInfoVO implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 9170423271497199062L;
	private String year;
	private ArrayList<SingleCommodityInitialInfoVO> commodityInfos=new ArrayList<SingleCommodityInitialInfoVO>();
	private ArrayList<SingleCustomerInitialInfoVO> customerInfos=new ArrayList<SingleCustomerInitialInfoVO>();
	private ArrayList<SingleBankAccountInitialInfoVO> bankAccountInfos=new ArrayList<SingleBankAccountInitialInfoVO>();
	
	public InitialInfoVO(){
		
	}
	
	public InitialInfoVO(ArrayList<SingleCommodityInitialInfoVO> comInfos,
			ArrayList<SingleCustomerInitialInfoVO> cusInfos,
			ArrayList<SingleBankAccountInitialInfoVO> bankInfos){
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
	
	public ArrayList<SingleCommodityInitialInfoVO> getCommodityInfos() {
		return commodityInfos;
	}

	public void setCommodityInfos(ArrayList<SingleCommodityInitialInfoVO> commodityInfos) {
		this.commodityInfos = commodityInfos;
	}

	public ArrayList<SingleCustomerInitialInfoVO> getCustomerInfos() {
		return customerInfos;
	}

	public void setCustomerInfos(ArrayList<SingleCustomerInitialInfoVO> customerInfos) {
		this.customerInfos = customerInfos;
	}

	public ArrayList<SingleBankAccountInitialInfoVO> getBankAccountInfos() {
		return bankAccountInfos;
	}

	public void setBankAccountInfos(ArrayList<SingleBankAccountInitialInfoVO> bankAccountInfos) {
		this.bankAccountInfos = bankAccountInfos;
	}

	
}
