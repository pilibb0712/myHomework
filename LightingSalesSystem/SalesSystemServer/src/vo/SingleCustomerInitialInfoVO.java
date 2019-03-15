package vo;

import java.io.Serializable;

/**
 * 期初信息中客户信息中的一条
 * @author guxinyu
 * @version 2017.12.4
 *
 */
public class SingleCustomerInitialInfoVO implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 5103883358534124693L;
	private String customerClass="";
	private String customerName="";
	private String customerContact="";
	private String customerReceive="";//应收
	private String customerPay="";//应付
	
	public SingleCustomerInitialInfoVO(){
		
	}
	
	public SingleCustomerInitialInfoVO(String cusClass, String cusName,String cusContact,String cusReceive,
			String cusPay){
		setCustomerClass(cusClass);
		setCustomerName(cusName);
		setCustomerContact(cusContact);
		setCustomerReceive(cusReceive);
		setCustomerPay(cusPay);
	}

	public String getCustomerClass() {
		return customerClass;
	}

	public void setCustomerClass(String customerClass) {
		this.customerClass = customerClass;
	}

	public String getCustomerName() {
		return customerName;
	}

	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}

	public String getCustomerContact() {
		return customerContact;
	}

	public void setCustomerContact(String customerContact) {
		this.customerContact = customerContact;
	}

	public String getCustomerReceive() {
		return customerReceive;
	}

	public void setCustomerReceive(String customerReceive) {
		this.customerReceive = customerReceive;
	}

	public String getCustomerPay() {
		return customerPay;
	}

	public void setCustomerPay(String customerPay) {
		this.customerPay = customerPay;
	}
	
	
	
}
